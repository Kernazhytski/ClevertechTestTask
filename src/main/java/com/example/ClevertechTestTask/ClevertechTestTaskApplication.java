package com.example.ClevertechTestTask;

import com.example.ClevertechTestTask.DAO.CardDAO;
import com.example.ClevertechTestTask.DAO.ItemsDAO;
import com.example.ClevertechTestTask.check.Check;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ClevertechTestTaskApplication {

    private static ItemsDAO itemsDAO;
    private static CardDAO cardDAO;
    private static Check check;

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(ClevertechTestTaskApplication.class, args);

        itemsDAO = app.getBean(ItemsDAO.class);
        cardDAO = app.getBean(CardDAO.class);
        check = app.getBean(Check.class);


        for (int i = 0; i < args.length; i++) {
            Request(args[i]);
        }
        check.makeCheck();

    }

    public static void Request(String req) {
        try {
            if (req.length() >= 3) {
                if (req.length() >= 4) {
                    if (req.substring(0, 4).equals("card")) {
                        addCard(req.substring(5));
                    } else {
                        addItem(req);
                    }
                } else {
                    addItem(req);
                }
            } else {
                throw new Exception(req + " is a wrong entry");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addCard(String cardNumber) {
        cardDAO.setPercents(Long.parseLong(cardNumber));
    }

    public static void addItem(String item) {
        try {
            itemsDAO.addItem(Long.parseLong(item.substring(0, item.indexOf('-'))),
                    Integer.parseInt(item.substring(item.indexOf('-') + 1)));
        } catch (NumberFormatException e) {
            System.out.println(item + " is a wrong entry");
        }
    }
}
