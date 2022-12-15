package com.example.ClevertechTestTask.repo;

import com.example.ClevertechTestTask.models.Card;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CardRepo extends CrudRepository<Card, Long> {
    List<Card> findAll();
}
