package com.custompromoengine.rule;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.custompromoengine.model.ProductOrdered;
import com.custompromoengine.model.Promo;
import com.custompromoengine.service.PromoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleEngineTest {
	

	@Autowired
	private  RuleEngine ruleEngine;
	
	private ArrayList<Promo> promolist = new ArrayList<Promo>();
	private ArrayList<ProductOrdered> listOfProducts = new ArrayList<ProductOrdered>();
	private List<ProductOrdered> productlistWithOffer = new ArrayList<ProductOrdered>();
	private List<ProductOrdered> productlistWithOutOffer = new ArrayList<ProductOrdered>();

	@Before
	public void before() {
		ArrayList<Promo> promolist_temp = new ArrayList<Promo>();
		Promo PROMO_A = new Promo("PROMO_A", true, 3, 130.00, null, new ArrayList<String>(Arrays.asList("A")));
		Promo PROMO_B = new Promo("PROMO_B", true, 2, 45.00, null, new ArrayList<String>(Arrays.asList("B")));
		Promo PROMO_CD = new Promo("PROMO_CD", true, 2, 30.00, null, new ArrayList<String>(Arrays.asList("C", "D")));
		promolist_temp.add(PROMO_A);
		promolist_temp.add(PROMO_B);
		promolist_temp.add(PROMO_CD);
		this.setPromolist(promolist_temp);


	}

	public ArrayList<Promo> getPromolist() {
		return promolist;
	}

	public void setPromolist(ArrayList<Promo> promolist) {
		this.promolist = promolist;
	}

	public ArrayList<ProductOrdered> getListOfProducts() {
		return listOfProducts;
	}

	public void setListOfProducts(ArrayList<ProductOrdered> listOfProducts) {
		this.listOfProducts = listOfProducts;
	}

	public List<ProductOrdered> getProductlistWithOffer() {
		return productlistWithOffer;
	}

	public void setProductlistWithOffer(List<ProductOrdered> productlistWithOffer) {
		this.productlistWithOffer = productlistWithOffer;
	}

	public List<ProductOrdered> getProductlistWithOutOffer() {
		return productlistWithOutOffer;
	}

	public void setProductlistWithOutOffer(List<ProductOrdered> productlistWithOutOffer) {
		this.productlistWithOutOffer = productlistWithOutOffer;
	}
	
	@Test
	public void calculaProductpriceWithoutOffer() {
		ProductOrdered Product_A = new ProductOrdered("A", "pen", "ink pen", 2, 50.00, true);
		ProductOrdered Product_B = new ProductOrdered("B", "exercise copy", "camlin exercise copy", 1, 30.00, true);
		ArrayList<ProductOrdered> orderlist_temp = new ArrayList<ProductOrdered>();
		orderlist_temp.add(Product_A);
		orderlist_temp.add(Product_B);
		this.setListOfProducts(orderlist_temp);
		productlistWithOutOffer.addAll(orderlist_temp);
		Double price = ruleEngine.calculaProductpriceWithoutOffer(productlistWithOutOffer);
		assertEquals(new Double(130.00),price);
	}
	
	@Test
	public void calculaProductpriceWithOffer() {
		ProductOrdered Product_A = new ProductOrdered("A", "pen", "ink pen", 3, 50.00, true);
		ProductOrdered Product_B = new ProductOrdered("B", "exercise copy", "camlin exercise copy", 4, 30.00, true);
		ArrayList<ProductOrdered> orderlist_temp = new ArrayList<ProductOrdered>();
		orderlist_temp.add(Product_A);
		orderlist_temp.add(Product_B);
		this.setListOfProducts(orderlist_temp);
		productlistWithOffer.addAll(orderlist_temp);
		Double price = ruleEngine.calculaProductpriceWithOffer(promolist, productlistWithOffer);
		assertEquals(new Double(220.00),price);
	}

	@Test
	public void segregateOrderedPoducts() {

		ProductOrdered Product_A = new ProductOrdered("A", "pen", "ink pen", 5, 50.00, true);
		ProductOrdered Product_B = new ProductOrdered("B", "exercise copy", "camlin exercise copy", 5, 30.00, true);
		ProductOrdered Product_C = new ProductOrdered("C", "eraser", "camlin eraser", 1, 20.00, true);
		ArrayList<ProductOrdered> orderlist_temp = new ArrayList<ProductOrdered>();
		orderlist_temp.add(Product_A);
		orderlist_temp.add(Product_B);
		orderlist_temp.add(Product_C);
		this.setListOfProducts(orderlist_temp);
		ruleEngine.segregateOrderedPoducts(listOfProducts, promolist, productlistWithOffer, productlistWithOutOffer);
		ArrayList<Integer> productWithOfferquantity = new ArrayList<>();
		productlistWithOffer.forEach(product -> {
			productWithOfferquantity.add(product.getQuantity());
		});
		ArrayList<Integer> productWithoutOfferquantity = new ArrayList<>();
		productlistWithOutOffer.forEach(product -> {
			productWithoutOfferquantity.add(product.getQuantity());
		});

		int productWithOfferquantityVal = productWithOfferquantity.stream().mapToInt(Integer::intValue).sum();
		int productWithoutOfferquantityVal = productWithoutOfferquantity.stream().mapToInt(Integer::intValue).sum();
		boolean flag = productWithOfferquantityVal == 7 && productWithoutOfferquantityVal == 4;
		assertEquals(flag, true);
	}

}
