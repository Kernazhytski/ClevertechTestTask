package com.example.ClevertechTestTask.repo;

import com.example.ClevertechTestTask.models.Items;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemsRepo extends CrudRepository<Items, Long> {
    List<Items> findAll();
}
