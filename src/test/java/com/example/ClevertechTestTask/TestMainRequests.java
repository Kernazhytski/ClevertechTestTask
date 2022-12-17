package com.example.ClevertechTestTask;

import com.example.ClevertechTestTask.DAO.CardDAO;
import com.example.ClevertechTestTask.DAO.ItemsDAO;
import com.example.ClevertechTestTask.controllers.Request;
import com.example.ClevertechTestTask.models.Card;
import com.example.ClevertechTestTask.models.Items;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest()
class TestMainRequests {

	@Autowired
	private ItemsDAO itemsDAO;

	@Autowired
	private CardDAO cardDAO;

	@Autowired
	private Request request;

	private HashMap<Items, Integer> list = new HashMap<Items, Integer>();

	@Test
	void Test1() {
		request.getRequest("2-1");
		request.getRequest("5-4");


		list.put(new Items(2l,"Tomato",2,true),1);
		list.put(new Items(3l,"Beer",1.5f,true),4);



		for(Map.Entry<Items,Integer> entry : itemsDAO.getList().entrySet()) {
			Assertions.assertEquals(entry.getValue(), itemsDAO.getList().get(entry.getKey()));
		}
		request.getRequest("card-1234");
		Card card = new Card(1234l,30f);
		Assertions.assertEquals(card.getPercents(),cardDAO.getPercents());

		itemsDAO.clearItems();
		cardDAO.deleteCard();
	}

	@Test
	void Test2() {
		request.getRequest("2-1");
		request.getRequest("5-4");
		request.getRequest("3-0");
		request.getRequest("1-2");

		list.put(new Items(2l,"Tomato",2,true),1);
		list.put(new Items(3l,"Beer",1.5f,true),4);
		list.put(new Items(1l,"Kola",1.15f,false),2);

		for(Map.Entry<Items,Integer> entry : itemsDAO.getList().entrySet()) {
			Assertions.assertEquals(entry.getValue(), itemsDAO.getList().get(entry.getKey()));
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

		list.put(new Items(2l,"Tomato",2,true),1);
		list.put(new Items(3l,"Beer",1.5f,true),4);
		list.put(new Items(1l,"Kola",1.15f,false),2);

		for(Map.Entry<Items,Integer> entry : itemsDAO.getList().entrySet()) {
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
