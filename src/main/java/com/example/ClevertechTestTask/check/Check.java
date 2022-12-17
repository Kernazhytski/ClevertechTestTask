package com.example.ClevertechTestTask.check;

import com.example.ClevertechTestTask.DAO.CardDAO;
import com.example.ClevertechTestTask.DAO.ItemsDAO;
import com.example.ClevertechTestTask.models.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class Check {

    @Autowired
    private CardDAO cardDAO;

    @Autowired
    private ItemsDAO itemsDAO;

    private String format = "*%1$-5s*%2$-20s*%3$-5s*%4$-6s*\n";
    private float total;


    public void makeCheck() {
        String postfix;
        total = 0;
        HashMap<Items, Integer> items = itemsDAO.getList();

        try (FileWriter writer = new FileWriter("src/main/java/com/example/ClevertechTestTask/files/Check.txt", false)) {
            writer.write("*".repeat(50)+"\n");
            writer.write("*" + center("CASH RECEIPT", 48) + "*"+"\n");
            writer.write("*" + center("Super market Clevertec", 48) + "*"+"\n");
            writer.write("*".repeat(50)+"\n");
            writer.write(String.format(format,
                    center("QTY", 5),
                    center("Description", 20),
                    center("Cost", 9),
                    center("Total", 11)));
            for (Map.Entry<Items, Integer> item : items.entrySet()) {
                postfix = item.getKey().isAction() ? "(a)" : "";
                writer.write(String.format(format,
                        center(item.getValue().toString(), 5),
                        center(item.getKey().getName() + postfix, 20),
                        center("$" + String.valueOf(roundedCost(priseCount(item.getKey()))), 9),
                        center("$" + String.valueOf(roundedCost(roundedCost(priseCount(item.getKey())) * item.getValue())), 11)));
                total += roundedCost(roundedCost(priseCount(item.getKey())) * item.getValue());
            }
            writer.write("*".repeat(50)+"\n");
            if (itemsDAO.getActionItemsTotal() > 5) {
                writer.write("*" + center("Action products received a 10% discount", 48) + "*"+"\n");
            }
            if (cardDAO.isExist()) {
                writer.write("*" + center("Card with " + cardDAO.getPercents() + "% discount used", 48) + "*"+"\n");
                total = roundedCost(total - total * cardDAO.getPercents() / 100);
            }
            writer.write("* TOTAL" + left("$"+String.valueOf(total), 41) + " *"+"\n");
            writer.write("*".repeat(50)+"\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }



    }



    private float priseCount(Items item) {
        if (itemsDAO.getActionItemsTotal() > 5 && item.isAction()) {
            return (float) (item.getCost() - item.getCost() * 0.1);
        }
        return item.getCost();
    }

    private float roundedCost(Float cost) {
        return (float) Math.ceil(cost * 100) / 100;
    }

    public String center(String s, int size) {
        char pad = ' ';
        if (s == null || size <= s.length())
            return s;

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()) / 2; i++) {
            sb.append(pad);
        }
        sb.append(s);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
    }

    public String left(String s, int size) {
        char pad = ' ';
        if (s == null || size <= s.length())
            return s;

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()); i++) {
            sb.append(pad);
        }
        sb.append(s);
        return sb.toString();
    }
}
