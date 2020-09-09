package com.custompromoengine.rule;

import java.util.List;

import com.custompromoengine.model.ProductOrdered;
import com.custompromoengine.model.Promo;

/**
 * @author SKHassan
 *
 */


public   interface BaseRule { 
	public void segregateOrderedPoducts(List<ProductOrdered>productOrdered_list, List<Promo> activePromoList,
			List<ProductOrdered> productlistWithOffer,List<ProductOrdered> productlistWithOutOffer);
	
	public Double calculaProductpriceWithOffer( List<Promo> activePromolist, List<ProductOrdered> productlistWithOffer) ;

}
