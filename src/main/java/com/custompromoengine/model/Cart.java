package com.custompromoengine.model;

import java.util.List;

public class Cart {

 List<ProductOrdered> products; 
 
public List<ProductOrdered> getProducts() {
	return products;
}
public void setProducts(List<ProductOrdered> products) {
	this.products = products;
} 
}
