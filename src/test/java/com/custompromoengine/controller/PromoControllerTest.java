package com.custompromoengine.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.custompromoengine.PromoEngineAbstractTest;
import com.custompromoengine.model.Cart;
import com.custompromoengine.model.ProductOrdered;
import com.custompromoengine.model.Promo;
import com.custompromoengine.service.PromoService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PromoControllerTest extends PromoEngineAbstractTest {

	@Autowired
	private PromoService promoservice;
	
	private 
	ArrayList<Promo> promolist = new ArrayList<Promo>();
	
	public ArrayList<Promo> getPromolist() {
		return promolist;
	}

	public void setPromolist(ArrayList<Promo> promolist) {
		this.promolist = promolist;
	}

	@Override
	@Before
	public void setUp() {
		super.setUp();
		ArrayList<Promo> promolist_temp = new ArrayList<Promo>();		
		Promo PROMO_A = new Promo("PROMO_A",true,3,130.00,null,new ArrayList<String>(Arrays.asList("A")));
		Promo PROMO_B = new Promo("PROMO_B",true,2, 45.00,null,new ArrayList<String>(Arrays.asList("B")));
		Promo PROMO_CD = new Promo("PROMO_CD",true,2,30.00,null,new ArrayList<String>(Arrays.asList("C","D")));
		Promo PROMO_ALL = new Promo("PROMO_ALL",false,1,null,10.00,new ArrayList<String>(Arrays.asList("A","B","C","D")));	
		promolist_temp.add(PROMO_A);
		promolist_temp.add(PROMO_B);
		promolist_temp.add(PROMO_CD);
		promolist_temp.add(PROMO_ALL);
		this.setPromolist(promolist_temp);
		promoservice.setDefaultPromolist(this.getPromolist());
	}

	@Test
	public void confirmorder() throws Exception {

		String uri = "/promoengine/confirmorder";
		ProductOrdered Product_A = new ProductOrdered("A", "pen", "ink pen", 1, 50.00, true);
		ProductOrdered Product_B = new ProductOrdered("B", "exercise copy", "camlin exercise copy", 1, 30.00, true);
		ProductOrdered Product_C = new ProductOrdered("C", "eraser", "camlin eraser", 1, 20.00, true);
		ArrayList<ProductOrdered> listOfProducts = new ArrayList<ProductOrdered>();
		listOfProducts.add(Product_A);
		listOfProducts.add(Product_B);
		listOfProducts.add(Product_C);
		Cart cart = new Cart();
		cart.setProducts(listOfProducts);

		String inputJson = super.mapToJson(cart);
		
		  MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
		  .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn(
		  );
		 
		  int status = mvcResult.getResponse().getStatus();
		  String content = mvcResult.getResponse().getContentAsString();
		  assertEquals(200, status);
		  assertEquals(new Double(100.00),new Double(content));
	}

}
