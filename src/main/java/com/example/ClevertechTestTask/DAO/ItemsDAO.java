package com.example.ClevertechTestTask.DAO;

import com.example.ClevertechTestTask.models.Items;
import com.example.ClevertechTestTask.repo.ItemsRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Component
public class ItemsDAO {

    @Autowired
    private ItemsRepo itemsRepo;

    private Items items;

    private Long actionItemsTotal = 0l;

    private HashMap<Items, Integer> list = new HashMap<Items, Integer>();

    private List<Items> listItems;

    public List<Items> getListItems() {
        return listItems;
    }

    @PostConstruct
    public void init() {
        listItems=itemsRepo.findAll();
    }

    public void addItem(Long id, Integer num) {
        items = listItems.stream().filter(item -> id == item.getId()).findFirst().orElse(null);
        if (items == null) {
            System.out.println("Item with id " + id + " doesn't exist");
            return;
        } else if (list.containsKey(items)) {
            list.put(items, list.get(items) + num);
        } else if (items != null) {
            list.put(items, num);
        }
        if(items.isAction()){
        actionItemsTotal += num.longValue();}
    }

    public Long getActionItemsTotal() {
        return actionItemsTotal;
    }

    public HashMap<Items, Integer> getList() {
        return list;
    }

    public void clearItems() {
        list.clear();
        actionItemsTotal = 0l;
    }


}
