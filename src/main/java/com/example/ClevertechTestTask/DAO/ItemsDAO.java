package com.example.ClevertechTestTask.DAO;

import com.example.ClevertechTestTask.models.Items;
import com.example.ClevertechTestTask.repo.ItemsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;


@Component
public class ItemsDAO {

    @Autowired
    private ItemsRepo itemsRepo;

    private HashMap<Items, Integer> list = new HashMap<Items, Integer>();

    public void addItem(Long id, Integer num) {
        Items items = itemsRepo.findAll().stream().filter(item -> id == item.getId()).findFirst().orElse(null);
        if(items==null){
            System.out.println("Item with id "+id+" doesn't exist");
        }
        else if (list.containsKey(items)) {
            list.put(items, list.get(items) + num);
        } else if (items != null) {
            list.put(items, num);
        }
    }

    public HashMap<Items, Integer> getList() {
        return list;
    }

    public void addItem(Long id) {
        addItem(id, 1);
    }


}
