package com.example.ClevertechTestTask;

import com.example.ClevertechTestTask.DAO.CardDAO;
import com.example.ClevertechTestTask.DAO.ItemsDAO;
import com.example.ClevertechTestTask.controllers.CheckController;
import com.example.ClevertechTestTask.models.Items;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@SpringBootTest()
public class TestGetRequests {

    @Autowired
    private ItemsDAO itemsDAO;

    @Autowired
    private CardDAO cardDAO;

    @Autowired
    private CheckController controller;

    private HashMap<Items, Integer> list = new HashMap<Items, Integer>();

    private List<Items> listItems;

    @PostConstruct
    public void init() {
        listItems=itemsDAO.getListItems();
    }

    @Test
    public void contextLoads() throws Exception {
        Assertions.assertNotNull(controller);
    }

    @Test
    void Test1() {

        controller.getCheck(new ArrayList<Long>() {
        }, new ArrayList<Integer>() {
        }, new ArrayList<Long>() {
        }, null);

        list.put(listItems.stream().filter(item -> 1l == item.getId()).findFirst().orElse(null),2);
        list.put(listItems.stream().filter(item -> 5l == item.getId()).findFirst().orElse(null),10);

        System.out.println(itemsDAO.getList());

        for(Map.Entry<Items,Integer> entry : list.entrySet()) {
            Assertions.assertEquals(entry.getValue(), itemsDAO.getList().get(entry.getKey()));
        }

        Assertions.assertNotEquals(30f,cardDAO.getPercents());

        itemsDAO.clearItems();
        cardDAO.deleteCard();
    }


}
