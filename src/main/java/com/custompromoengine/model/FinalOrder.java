package com.custompromoengine.model;

import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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
}
