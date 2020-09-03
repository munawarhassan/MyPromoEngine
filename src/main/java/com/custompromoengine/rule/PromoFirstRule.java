package com.custompromoengine.rule;

import java.util.List;

import com.custompromoengine.model.ProductOrdered;


public class PromoFirstRule implements BaseRule {

	@Override
	public void evaluateCondition(ProductOrdered productOrdered, List<ProductOrdered> productlistWithOffer,
			List<ProductOrdered> productlistWithOutOffer) {
	    String skuId =    productOrdered.getSkuId(); 
	    String name = productOrdered.getName();
	    String description = productOrdered.getDescription();
	    int quantity = productOrdered.getQuantity();
	    Double price = productOrdered.getPrice();
		boolean offervailable = productOrdered.isOffervailable();		
		
		int offer_not_eligible =productOrdered.getQuantity() % 3;
		int offer_eligible = productOrdered.getQuantity() - offer_not_eligible;	
		ProductOrdered tempProductOffered = new ProductOrdered( skuId, name, description, quantity, price, offervailable);		
		tempProductOffered.setQuantity(offer_eligible);
		productlistWithOffer.add(tempProductOffered);
		ProductOrdered tempProductNotOffered1 = new ProductOrdered( skuId, name, description, quantity, price, offervailable);		
		tempProductNotOffered1.setQuantity(offer_not_eligible);
		productlistWithOutOffer.add(tempProductNotOffered1);	
		
	}

	@Override
	public void evaluateCondition(List<ProductOrdered> productOrdered_list, List<ProductOrdered> productlistWithOffer,
			List<ProductOrdered> productlistWithOutOffer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Double calculateOfferedPrice(ProductOrdered productOrdered) {
		Double price = (productOrdered.getQuantity()/3) * 130.00;
		return price;
	}

	
}
