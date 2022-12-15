package com.example.ClevertechTestTask.DAO;

import com.example.ClevertechTestTask.models.Card;
import com.example.ClevertechTestTask.models.Items;
import com.example.ClevertechTestTask.repo.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CardDAO {

    @Autowired
    private CardRepo cardRepo;

    private float percents = 0;

    public float getPercents() {
        return percents;
    }

    public void setPercents(Long cardNumber) {
        if (percents == 0) {
            Card card = cardRepo.findAll().stream().filter(item -> cardNumber.equals(item.getCardNumber())).findFirst().orElse(null);
            if (card!=null){
                this.percents = card.getPercents();}
            else{
                System.out.println("Can't find card with number "+cardNumber);
            }
        }else{
            System.out.println("You can't use more than 1 card");
        }
    }
}
