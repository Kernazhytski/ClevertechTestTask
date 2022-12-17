package com.example.ClevertechTestTask.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Card {
    @Id
    private Long cardNumber;

    public Long getCardNumber() {
        return cardNumber;
    }

    public Card() {
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public float getPercents() {
        return percents;
    }

    public Card(Long cardNumber, float percents) {
        this.cardNumber = cardNumber;
        this.percents = percents;
    }

    public void setPercents(float percents) {
        this.percents = percents;
    }

    private float percents;
}
