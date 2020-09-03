package com.custompromoengine.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "promo" )
public class Promo {


	@Id
    @Column(name = "name") 
    private String name;

    @Column(name = "active")
    private boolean active;    
    

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name = "skuList")
    @ElementCollection(targetClass=String.class)
    private List<String> skuList;
  

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public List<String> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<String> skuList) {
		this.skuList = skuList;
	}	

	
   
}
