package com.custompromoengine.rule;

import java.util.List;

import com.custompromoengine.model.ProductOrdered;

public   interface BaseRule { 
	public  void evaluateCondition(ProductOrdered productOrdered,
			List<ProductOrdered> productlistWithOffer,List<ProductOrdered> productlistWithOutOffer);
	public void evaluateCondition(List<ProductOrdered>productOrdered_list,
			List<ProductOrdered> productlistWithOffer,List<ProductOrdered> productlistWithOutOffer);
	
	public Double calculateOfferedPrice (ProductOrdered productOrdered);
	
	//public Double calculateNoOfferedPrice (ProductOrdered productOrdered);
	

}
