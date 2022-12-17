package com.example.ClevertechTestTask;

import com.example.ClevertechTestTask.DAO.CardDAO;
import com.example.ClevertechTestTask.DAO.ItemsDAO;
import com.example.ClevertechTestTask.controllers.Request;
import com.example.ClevertechTestTask.models.Card;
import com.example.ClevertechTestTask.models.Items;
import com.example.ClevertechTestTask.repo.ItemsRepo;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class TestMainRequests {

	@Autowired
	private ItemsDAO itemsDAO;

	@Autowired
	private CardDAO cardDAO;

	@Autowired
	private Request request;

	@Autowired
	private ItemsRepo itemsRepo;

	private HashMap<Items, Integer> list = new HashMap<Items, Integer>();

	private List<Items> listItems;

	@PostConstruct
	public void init() {
		listItems=itemsDAO.getListItems();
	}

	@Test
	void Test1() {
		request.getRequest("2-1");
		request.getRequest("5-4");

		list.put(listItems.stream().filter(item -> 2l == item.getId()).findFirst().orElse(null),1);
		list.put(listItems.stream().filter(item -> 5l == item.getId()).findFirst().orElse(null),4);

		for(Map.Entry<Items,Integer> entry : list.entrySet()) {
			Assertions.assertEquals(entry.getValue(), itemsDAO.getList().get(entry.getKey()));
		}
		request.getRequest("card-1234");

		Assertions.assertEquals(30f,cardDAO.getPercents());

		itemsDAO.clearItems();
		cardDAO.deleteCard();
	}

	@Test
	void Test2() {
		request.getRequest("2-1");
		request.getRequest("5-4");
		request.getRequest("3-0");
		request.getRequest("1-2");

		list.put(listItems.stream().filter(item -> 2l == item.getId()).findFirst().orElse(null),1);
		list.put(listItems.stream().filter(item -> 5l == item.getId()).findFirst().orElse(null),4);
		list.put(listItems.stream().filter(item -> 1l == item.getId()).findFirst().orElse(null),2);

		for(Map.Entry<Items,Integer> entry : itemsDAO.getList().entrySet()) {
			Assertions.assertEquals(entry.getValue(), list.get(entry.getKey()));
		}
		request.getRequest("card-7777");
		Assertions.assertEquals(50f,cardDAO.getPercents());
		request.getRequest("card-6666");
		Assertions.assertNotEquals(6f,cardDAO.getPercents());

		itemsDAO.clearItems();
		cardDAO.deleteCard();
	}

	@Test
	void Test3() {
		request.getRequest("2-1");
		request.getRequest("5-4");
		request.getRequest("qweqrqt11");
		request.getRequest("1-2");
		request.getRequest("12");

		list.put(listItems.stream().filter(item -> 2l == item.getId()).findFirst().orElse(null),1);
		list.put(listItems.stream().filter(item -> 5l == item.getId()).findFirst().orElse(null),4);
		list.put(listItems.stream().filter(item -> 1l == item.getId()).findFirst().orElse(null),2);

		for(Map.Entry<Items,Integer> entry : list.entrySet()) {
			Assertions.assertEquals(entry.getValue(), itemsDAO.getList().get(entry.getKey()));
		}
		request.getRequest("card-7777");
		Assertions.assertEquals(50f,cardDAO.getPercents());
		request.getRequest("card6666");
		Assertions.assertNotEquals(0,cardDAO.getPercents());

		itemsDAO.clearItems();
		cardDAO.deleteCard();
	}

	@Test
	void Test4() {
		request.getRequest("2-1");
		request.getRequest("5-4");
		request.getRequest("1-2");
		request.getRequest("1-2");

		list.put(listItems.stream().filter(item -> 2l == item.getId()).findFirst().orElse(null),1);
		list.put(listItems.stream().filter(item -> 5l == item.getId()).findFirst().orElse(null),4);
		list.put(listItems.stream().filter(item -> 1l == item.getId()).findFirst().orElse(null),4);

		for(Map.Entry<Items,Integer> entry : list.entrySet()) {
			Assertions.assertEquals(entry.getValue(), itemsDAO.getList().get(entry.getKey()));
		}
		request.getRequest("card-7777");
		Assertions.assertEquals(50f,cardDAO.getPercents());
		request.getRequest("card6666");
		Assertions.assertNotEquals(0,cardDAO.getPercents());

		itemsDAO.clearItems();
		cardDAO.deleteCard();
	}

}
