package com.custompromoengine.rule;

import java.util.List;

import com.custompromoengine.model.ProductOrdered;

public class PromoThirdRule implements BaseRule{

	@Override
	public void evaluateCondition(ProductOrdered productOrdered, List<ProductOrdered> productlistWithOffer,
			List<ProductOrdered> productlistWithOutOffer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void evaluateCondition(List<ProductOrdered> productOrdered_list, List<ProductOrdered> productlistWithOffer,
			List<ProductOrdered> productlistWithOutOffer) {

	 	
		if (productOrdered_list.size()==1) {
			productlistWithOutOffer.addAll(productOrdered_list);
		}else if (productOrdered_list.size()==2) {
			int quantity_C = 0;
			int quantity_D = 0;		
		
			ProductOrdered tempProductOffered_D  = null;
			ProductOrdered tempProductOffered_C  = null;
			ProductOrdered tempProductNotOffered_D  = null;
			ProductOrdered tempProductNotOffered_C  = null;
			
			for(ProductOrdered productOrdered : productOrdered_list) {	
				String skuId =    productOrdered.getSkuId(); 
			    String name = productOrdered.getName();
			    String description = productOrdered.getDescription();
			    int quantity = productOrdered.getQuantity();
			    Double price = productOrdered.getPrice();
				boolean offervailable = productOrdered.isOffervailable();	
				
				if(productOrdered.getSkuId().equalsIgnoreCase("C")){
					quantity_C =  productOrdered.getQuantity();
					tempProductOffered_C = new ProductOrdered( skuId, name, description, quantity, price, offervailable);
					tempProductNotOffered_C = new ProductOrdered( skuId, name, description, quantity, price, offervailable);
				}else {
					quantity_D = productOrdered.getQuantity();
					tempProductOffered_D = new ProductOrdered( skuId, name, description, quantity, price, offervailable);
					tempProductNotOffered_D = new ProductOrdered( skuId, name, description, quantity, price, offervailable);
				}
			}
			if(quantity_C < quantity_D) {				
				tempProductOffered_C.setQuantity(quantity_C);
				productlistWithOffer.add(tempProductOffered_C);
				tempProductOffered_D.setQuantity(quantity_C);
				tempProductNotOffered_D.setQuantity(quantity_D - quantity_C);
				productlistWithOutOffer.add(tempProductNotOffered_D);
				
			}else if(quantity_C > quantity_D) {				
				tempProductOffered_D.setQuantity(quantity_D);
				productlistWithOffer.add(tempProductOffered_D);				
				tempProductNotOffered_C.setQuantity(quantity_D - quantity_C);
				productlistWithOutOffer.add(tempProductNotOffered_C);
				
			}
			
		}
		
		
	}

	@Override
	public Double calculateOfferedPrice(ProductOrdered productOrdered) {
		Double price =  productOrdered.getQuantity() * (30.00 /2);
		return price;
	}
}
