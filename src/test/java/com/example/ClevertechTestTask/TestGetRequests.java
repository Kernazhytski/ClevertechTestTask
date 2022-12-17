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

import java.util.*;


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
        controller.getCheck(Arrays.asList(1l,5l),
                Arrays.asList(2,10),
                Arrays.asList(1234l,12345l),
                null);

        list.put(listItems.stream().filter(item -> 1l == item.getId()).findFirst().orElse(null),2);
        list.put(listItems.stream().filter(item -> 5l == item.getId()).findFirst().orElse(null),10);

        for(Map.Entry<Items,Integer> entry : list.entrySet()) {
            Assertions.assertEquals(entry.getValue(), itemsDAO.getList().get(entry.getKey()));
        }

        Assertions.assertEquals(30f,cardDAO.getPercents());

        itemsDAO.clearItems();
        cardDAO.deleteCard();
    }

    @Test
    void Test2() {
        controller.getCheck(Arrays.asList(1l,5l,2l,14l),
                Arrays.asList(2,10,1,13),
                Arrays.asList(7777l),
                null);

        list.put(listItems.stream().filter(item -> 1l == item.getId()).findFirst().orElse(null),2);
        list.put(listItems.stream().filter(item -> 5l == item.getId()).findFirst().orElse(null),10);
        list.put(listItems.stream().filter(item -> 2l == item.getId()).findFirst().orElse(null),1);

        for(Map.Entry<Items,Integer> entry : list.entrySet()) {
            Assertions.assertEquals(entry.getValue(), itemsDAO.getList().get(entry.getKey()));
        }
        Assertions.assertTrue(itemsDAO.getList().size()==3);

        Assertions.assertEquals(50f,cardDAO.getPercents());

        itemsDAO.clearItems();
        cardDAO.deleteCard();
    }

    @Test
    void Test3() {
        controller.getCheck(Arrays.asList(1l,5l),
                Arrays.asList(2,10),
                Arrays.asList(12345l),
                null);

        list.put(listItems.stream().filter(item -> 1l == item.getId()).findFirst().orElse(null),2);
        list.put(listItems.stream().filter(item -> 5l == item.getId()).findFirst().orElse(null),10);

        for(Map.Entry<Items,Integer> entry : list.entrySet()) {
            Assertions.assertEquals(entry.getValue(), itemsDAO.getList().get(entry.getKey()));
        }

        Assertions.assertNotEquals(30f,cardDAO.getPercents());

        itemsDAO.clearItems();
        cardDAO.deleteCard();
    }


}
