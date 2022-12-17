package com.example.ClevertechTestTask;


import com.example.ClevertechTestTask.check.Check;

import com.example.ClevertechTestTask.controllers.Request;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ClevertechTestTaskApplication {


    private static Check check;
    private static Request Request;

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(ClevertechTestTaskApplication.class, args);

        check = app.getBean(Check.class);
        Request = app.getBean(com.example.ClevertechTestTask.controllers.Request.class);


        for (int i = 0; i < args.length; i++) {
            Request.getRequest(args[i]);
        }
        check.makeCheck();

    }


}
