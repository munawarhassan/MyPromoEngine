package com.custompromoengine.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.custompromoengine.exception.PromoEngineException;
import com.custompromoengine.model.Cart;
import com.custompromoengine.model.FinalOrder;
import com.custompromoengine.model.ProductOrdered;
import com.custompromoengine.model.Promo;
import com.custompromoengine.repo.OrderRepository;
import com.custompromoengine.repo.PromoRepository;
import com.custompromoengine.rule.BaseRule;
import com.custompromoengine.rule.PromoFirstRule;
import com.custompromoengine.rule.PromoForthRule;
import com.custompromoengine.rule.PromoSecondRule;
import com.custompromoengine.rule.PromoThirdRule;

@Service
public class PromoService {
	
	 public ResponseEntity<?> addPromo(Promo promo){ 
		 return null;
	 }
	 
	 
		public ResponseEntity<?>  updatepromo(Promo updatedpromo){
			return null;
		}
		
		public Promo getpromo(String promoname){		
			return null;
		}


		 public ResponseEntity<?> confirmorder(Cart cart) {
			 
			 return null;
		}	
		
		
		
			 
}
