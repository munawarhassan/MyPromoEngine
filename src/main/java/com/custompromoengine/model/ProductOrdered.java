package com.custompromoengine.model;

public class ProductOrdered {

    private String skuId;    
    private String name;
    private String description;
    private int quantity;
	private Double price;
	
	public String getSkuId() {
		return skuId;
	}
	
	
	
	public ProductOrdered() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}   

   
}
