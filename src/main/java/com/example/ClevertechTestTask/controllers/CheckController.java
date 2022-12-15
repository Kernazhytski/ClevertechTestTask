package com.example.ClevertechTestTask.controllers;

import com.example.ClevertechTestTask.requestBody.RequestItem;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("")
public class CheckController {

    @GetMapping("")
    @ResponseBody
    public void getCheck(@RequestParam(value = "itemId",required = false) List<Long> idList,
                         @RequestParam(value = "quantity",required = false) List<Long> quntityList){}

    @GetMapping("/check")
    @ResponseBody
    public void getCheck(@RequestParam LinkedMultiValueMap<String,Long> ex){
        System.out.println(ex);
    }

}
