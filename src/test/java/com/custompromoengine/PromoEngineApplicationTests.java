package com.custompromoengine;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.custompromoengine.model.Cart;	
import com.custompromoengine.model.FinalOrder;
import com.custompromoengine.model.ProductOrdered;
import com.custompromoengine.model.Promo;
import com.custompromoengine.service.PromoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PromoEngineApplicationTests {

	@Autowired
	private  PromoService promoservice;
	
	private 
	ArrayList<Promo> promolist = new ArrayList<Promo>();
	
	@Before
	public void before() {
		System.out.println("Before");
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
		
	}
	public ArrayList<Promo> getPromolist() {
		return promolist;
	}

	public void setPromolist(ArrayList<Promo> promolist) {
		this.promolist = promolist;
	}

	@Test
	public void TestScenareo1() {
		ProductOrdered  Product_A = new ProductOrdered("A","pen", "ink pen", 1, 50.00,true);
		ProductOrdered  Product_B = new ProductOrdered("B","exercise copy", "camlin exercise copy", 1, 30.00,true);
		ProductOrdered  Product_C = new ProductOrdered("C","eraser", "camlin eraser", 1, 20.00,true);
		ArrayList<ProductOrdered> listOfProducts = new ArrayList<ProductOrdered>();		
		listOfProducts.add(Product_A);
		listOfProducts.add(Product_B);
		listOfProducts.add(Product_C);
		Cart cart = new Cart();
		cart.setProducts(listOfProducts);		
		promoservice.setDefaultPromolist(this.getPromolist());		
		FinalOrder order = promoservice.confirmorder(cart);
		Double price = order.getTotalprice();
		assertEquals(new Double(100.00),price);
	}
	
    @Test
	public void TestScenareo2() {
		ProductOrdered  Product_A = new ProductOrdered("A","pen", "ink pen", 5, 50.00,true);
		ProductOrdered  Product_B = new ProductOrdered("B","exercise copy", "camlin exercise copy", 5, 30.00,true);
		ProductOrdered  Product_C = new ProductOrdered("C","eraser", "camlin eraser", 1, 20.00,true);
		ArrayList<ProductOrdered> listOfProducts = new ArrayList<ProductOrdered>();	
		listOfProducts.add(Product_A);
		listOfProducts.add(Product_B);
		listOfProducts.add(Product_C);
		Cart cart = new Cart();
		cart.setProducts(listOfProducts);		
		promoservice.setDefaultPromolist(this.getPromolist());		
		FinalOrder order = promoservice.confirmorder(cart);
		Double price = order.getTotalprice();
		assertEquals(new Double(370.00),price);
	}
	
	
	@Test
	public void TestScenareo3() {
		ProductOrdered  Product_A = new ProductOrdered("A","pen", "ink pen", 3, 50.00,true);
		ProductOrdered  Product_B = new ProductOrdered("B","exercise copy", "camlin exercise copy", 5, 30.00,true);		
		ProductOrdered  Product_C = new ProductOrdered("C","eraser", "camlin eraser", 1, 20.00,true);
		ProductOrdered  Product_D = new ProductOrdered("D","ruler", "camlin ruler", 1, 15.00,true);
		ArrayList<ProductOrdered> listOfProducts = new ArrayList<ProductOrdered>();		
		listOfProducts.add(Product_A);
		listOfProducts.add(Product_B);
		listOfProducts.add(Product_C);
		listOfProducts.add(Product_D);
		Cart cart = new Cart();
		cart.setProducts(listOfProducts);		
		promoservice.setDefaultPromolist(this.getPromolist());		
		FinalOrder order = promoservice.confirmorder(cart);
		Double price = order.getTotalprice();
		assertEquals(new Double(280.00),price);
	}
	
	//@Test
	public void TestScenareo4() {
		ProductOrdered  Product_A = new ProductOrdered("A","pen", "ink pen", 3, 50.00,true);
		ProductOrdered  Product_B = new ProductOrdered("B","exercise copy", "camlin exercise copy", 5, 30.00,true);		
		ProductOrdered  Product_C = new ProductOrdered("C","eraser", "camlin eraser", 1, 20.00,true);
		ProductOrdered  Product_D = new ProductOrdered("D","ruler", "camlin ruler", 1, 15.00,true);
		ArrayList<ProductOrdered> listOfProducts = new ArrayList<ProductOrdered>();		
		listOfProducts.add(Product_A);
		listOfProducts.add(Product_B);
		listOfProducts.add(Product_C);
		listOfProducts.add(Product_D);
		Cart cart = new Cart();
		cart.setProducts(listOfProducts);		
		promoservice.setDefaultPromolist(this.getPromolist());		
		FinalOrder order = promoservice.confirmorder(cart);
		Double price = order.getTotalprice();
		assertEquals(new Double(301.50),price);
	}
	
	

}
