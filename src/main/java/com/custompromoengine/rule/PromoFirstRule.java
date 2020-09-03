package com.custompromoengine.rule;

import java.util.List;

import com.custompromoengine.model.ProductOrdered;


public class PromoFirstRule implements BaseRule {

	@Override
	public void evaluateCondition(ProductOrdered productOrdered, List<ProductOrdered> productlistWithOffer,
			List<ProductOrdered> productlistWithOutOffer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void evaluateCondition(List<ProductOrdered> productOrdered_list, List<ProductOrdered> productlistWithOffer,
			List<ProductOrdered> productlistWithOutOffer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Double calculateOfferedPrice(ProductOrdered productOrdered) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
