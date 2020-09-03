package com.custompromoengine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class FinalOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id") 
    private long id;
    
    @Column(name = "description")  
    private String description;
    
    @Column(name = "total_price") 
    private Double totalprice;
   

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}
    

   
}
