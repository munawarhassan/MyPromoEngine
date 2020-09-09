package com.custompromoengine.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.custompromoengine.PromoEngineAbstractTest;
import com.custompromoengine.model.Cart;
import com.custompromoengine.model.ProductOrdered;
import com.custompromoengine.service.PromoService;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PromoControllerTest extends PromoEngineAbstractTest{
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PromoService promoservice;
	
	
	private String orderJsonInString;
	
	
	@Before
	public void before() {
		//orderJsonInString = "{\"products\":[{\"skuId\":\"A\",\"name\":\"PEN\",\"description\":\"pen\",\"quantity\":1,\"price\":50,\"offervailable\":true},{\"skuId\":\"B\",\"name\":\"PENCIL\",\"description\":\"pencil\",\"quantity\":1,\"price\":30,\"offervailable\":true},{\"skuId\":\"C\",\"name\":\"ERASER\",\"description\":\"eraser\",\"quantity\":1,\"price\":20,\"offervailable\":true}]}";
	}
	
	@Test
	public void confirmorder() throws Exception {
		
		String uri = "/promoengine/confirmorder";
		ProductOrdered  Product_A = new ProductOrdered("A","pen", "ink pen", 1, 50.00,true);
		ProductOrdered  Product_B = new ProductOrdered("B","exercise copy", "camlin exercise copy", 1, 30.00,true);
		ProductOrdered  Product_C = new ProductOrdered("C","eraser", "camlin eraser", 1, 20.00,true);
		ArrayList<ProductOrdered> listOfProducts = new ArrayList<ProductOrdered>();		
		listOfProducts.add(Product_A);
		listOfProducts.add(Product_B);
		listOfProducts.add(Product_C);
		Cart cart = new Cart();
		cart.setProducts(listOfProducts);
		
		String inputJson = super.mapToJson(cart);
		   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
		      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		   
		 
		
		Mockito.when(promoservice.confirmorder(Mockito.any())).thenReturn(100.00);

		MvcResult result = mockMvc.perform(post("/promoengine/confirmorder").accept(MediaType.APPLICATION_JSON)
				.content(orderJsonInString).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
		assertEquals("Result does not match", "100.0", result.getResponse().getContentAsString());

		
	}

}
