package com.custompromoengine.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.custompromoengine.exception.PromoEngineException;
import com.custompromoengine.model.Cart;
import com.custompromoengine.model.FinalOrder;
import com.custompromoengine.model.ProductOrdered;
import com.custompromoengine.model.Promo;
import com.custompromoengine.service.PromoService;

@RestController
@RequestMapping("/promoengine")
public class PromoController {
	
	@Autowired
	private PromoService promoservice;
	
	private static Logger log = LoggerFactory.getLogger(PromoController.class);
	
	@RequestMapping(path = "/default", method = RequestMethod.GET)
	public String getDefaultMessage() {
		return	"Hello , I am ready for Promo serevice";
	}	

	@RequestMapping(path = "/addpromo", method = RequestMethod.POST)	
    public ResponseEntity<?> addpromo(@RequestBody Promo promo){
		log.info("*Inside add Promo**");
		return promoservice.addPromo(promo);
    }
	
	@RequestMapping(path = "/updatepromo", method = RequestMethod.PUT)	
    public ResponseEntity<?> updatepromo(@RequestBody Promo promo){		
		log.info("*Inside update Promo**");
		return promoservice.updatepromo(promo);
    }
	
	@RequestMapping(path = "/getpromo/{promoname}", method = RequestMethod.GET)	
    public Promo getpromo(@PathVariable String promoname) throws PromoEngineException{
		Promo result = promoservice.getpromo(promoname);
		if(null !=result) {
			return result;
		}else {
			throw new PromoEngineException("No Data Found");
		}
    }
	
	//public ResponseEntity<?> confirmorder(@RequestBody Cart cart){
	@RequestMapping(path = "/confirmorder", method = RequestMethod.POST)
	public FinalOrder confirmorder(@RequestBody Cart cart) throws PromoEngineException{ 
		
		log.info("*Inside confirmorder**");
		return promoservice.confirmorder(cart);
		
		
    }
}
