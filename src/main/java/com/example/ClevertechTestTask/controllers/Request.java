package com.example.ClevertechTestTask.controllers;

import com.example.ClevertechTestTask.DAO.CardDAO;
import com.example.ClevertechTestTask.DAO.ItemsDAO;
import com.example.ClevertechTestTask.check.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Request {

    @Autowired
    private ItemsDAO itemsDAO;


    @Autowired
    private CardDAO cardDAO;

    public void getRequest(String req) {
        try {
            if (req.length() >= 3) {
                if (req.length() >= 4) {
                    if (req.startsWith("card-")) {
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

    public  void addCard(String cardNumber) {
        cardDAO.setPercents(Long.parseLong(cardNumber));
    }

    public  void addItem(String item) {
        try {
            if(Integer.parseInt(item.substring(item.indexOf('-') + 1))<1){
                throw new Exception("The quantity can't be zero");
            }
            itemsDAO.addItem(Long.parseLong(item.substring(0, item.indexOf('-'))),
                    Integer.parseInt(item.substring(item.indexOf('-') + 1)));
        } catch (NumberFormatException e) {
            System.out.println(item + " is a wrong entry");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
