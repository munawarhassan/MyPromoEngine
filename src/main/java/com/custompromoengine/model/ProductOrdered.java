package com.custompromoengine.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
public class ProductOrdered {

	private String skuId;
	private String name;
	private String description;
	private int quantity;
	private Double price;
	private boolean offervailable;
}
