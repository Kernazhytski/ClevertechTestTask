package com.example.ClevertechTestTask.requestBody;

public class RequestItem {
    private Long id;
    private Integer num;

    public RequestItem(Long id) {
        this.id = id;
    }

    public RequestItem(Long id, Integer num) {
        this.id = id;
        this.num = num;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
