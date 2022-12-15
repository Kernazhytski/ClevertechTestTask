package com.example.ClevertechTestTask;

import com.example.ClevertechTestTask.DAO.ItemsDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(args = {"1-3","4-2"})
class ClevertechTestTaskApplicationTests {

	@Autowired
	private ItemsDAO itemsDAO;

	@Test
	void contextLoads() {
		System.out.println(itemsDAO.getList());
	}

}
