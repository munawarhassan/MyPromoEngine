package com.custompromoengine.model;

import java.util.List;

import javax.persistence.*;

import lombok.*;


@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "promo" )
public class Promo {


	@Id
    @Column(name = "name") 
    private String name;

    @Column(name = "active")
    private boolean active;   
    
    @Column(name = "minpurchaseforoffer")
    private int minpurchaseforoffer;
    
    @Column(name = "unitofferprice")
    private Double unitofferprice;
    
    @Column(name = "discountpercent")
    private Double   discountpercent;
    
    
    @Column(name = "skuList")
    @ElementCollection(targetClass=String.class)
    private List<String> skuList;


	public Promo() {
		super();
		// TODO Auto-generated constructor stub
	}
   
}
