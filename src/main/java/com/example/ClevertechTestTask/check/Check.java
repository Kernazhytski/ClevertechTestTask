package com.example.ClevertechTestTask.check;

import com.example.ClevertechTestTask.DAO.CardDAO;
import com.example.ClevertechTestTask.DAO.ItemsDAO;
import com.example.ClevertechTestTask.models.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Component
public class Check {

    @Autowired
    private CardDAO cardDAO;

    @Autowired
    private ItemsDAO itemsDAO;

    public Check() {
    }

    public void makeCheck() {
        String format = "*%1$-5s*%2$-20s*%3$-6s*\n";

        HashMap<Items, Integer> items = itemsDAO.getList();

        System.out.println("*".repeat(35));
        System.out.println("*"+center("CASH RECEIPT",33)+"*");
        System.out.println("*"+center("Super market Clevertech",33)+"*");
        System.out.println("*".repeat(35));
        System.out.format(format,
                center("QTY", 5),
                center("Description", 20),
                center("Total", 6));
        for (Map.Entry<Items, Integer> item : items.entrySet()) {
            System.out.format(format,
                    center(item.getValue().toString(), 5),
                    center(item.getKey().getName(), 20),
                    center(String.valueOf(item.getKey().getCost()*item.getValue()), 6));
        }
        System.out.println("*".repeat(35));
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
}
