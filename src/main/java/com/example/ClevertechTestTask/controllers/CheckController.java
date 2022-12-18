package com.example.ClevertechTestTask.controllers;

import com.example.ClevertechTestTask.DAO.CardDAO;
import com.example.ClevertechTestTask.DAO.ItemsDAO;
import com.example.ClevertechTestTask.check.Check;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("")
public class CheckController {

    @Autowired
    private ItemsDAO itemsDAO;

    @Autowired
    private CardDAO cardDAO;

    @Autowired
    private Check check;

    @GetMapping("/check")
    @ResponseBody
    public void getCheck(@RequestParam(value = "itemId", required = false) List<Long> idList,
                         @RequestParam(value = "quantity", required = false) List<Integer> quantityList,
                         @RequestParam(value = "card", required = false) List<Long> cards,
                         HttpServletResponse response) {
        itemsDAO.clearItems();
        cardDAO.deleteCard();
        try {
            if (idList.size() == quantityList.size()) {
                for (int i = 0; i < idList.size(); i++) {
                    itemsDAO.addItem(idList.get(i), quantityList.get(i));
                }
                if (cards != null) {
                    for (Long number : cards) {
                        cardDAO.setPercents(number);
                    }
                }
                check.makeCheck();

                File file = new File("src/main/java/com/example/ClevertechTestTask/files/Check.txt");
                if (file.exists()) {
                    String mimeType = URLConnection.guessContentTypeFromName(file.getName());
                    response.setContentType(mimeType);


                    response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));


                    response.setContentLength((int) file.length());

                    InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

                    FileCopyUtils.copy(inputStream, response.getOutputStream());
                } else {
                    System.out.print("File doesn't exist");
                }
            } else {
                System.out.println("The number of id must equal the number of quantities");
            }
        } catch (NullPointerException e){}
        catch (Exception e) {
            System.out.println("No items has been added");
        }
    }


}
