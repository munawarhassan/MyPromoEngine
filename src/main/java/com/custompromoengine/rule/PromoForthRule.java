package com.custompromoengine.rule;

import java.util.List;

import com.custompromoengine.model.ProductOrdered;

public class PromoForthRule implements BaseRule{

	@Override
	public void evaluateCondition(ProductOrdered productOrdered, List<ProductOrdered> productlistWithOffer,
			List<ProductOrdered> productlistWithOutOffer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void evaluateCondition(List<ProductOrdered> productOrdered_list, List<ProductOrdered> productlistWithOffer,
			List<ProductOrdered> productlistWithOutOffer) {
		productlistWithOffer.addAll(productOrdered_list);
		
	}

	@Override
	public Double calculateOfferedPrice(ProductOrdered productOrdered) {
		Double price = ( productOrdered.getQuantity() * productOrdered.getPrice()) * 0.9;
		return price;
	}

}
