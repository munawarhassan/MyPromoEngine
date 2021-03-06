package com.custompromoengine.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.custompromoengine.exception.PromoEngineException;
import com.custompromoengine.model.Cart;
import com.custompromoengine.model.FinalOrder;
import com.custompromoengine.model.Promo;
import com.custompromoengine.service.PromoService;

import lombok.RequiredArgsConstructor;

/**
 * @author SKHassan
 *
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/promoengine")
public class PromoController {

	final private PromoService promoservice;

	private static Logger log = LoggerFactory.getLogger(PromoController.class);

	@RequestMapping(path = "/addpromo", method = RequestMethod.POST)
	public ResponseEntity<?> addpromo(@RequestBody Promo promo) {
		log.info("*Inside add Promo**");
		return promoservice.addPromo(promo);
	}

	@RequestMapping(path = "/updatepromo", method = RequestMethod.PUT)
	public ResponseEntity<?> updatepromo(@RequestBody Promo promo) {
		log.info("*Inside update Promo**");
		return promoservice.updatepromo(promo);
	}

	

	@RequestMapping(path = "/confirmorder", method = RequestMethod.POST)
	public Double confirmorder(@RequestBody Cart cart) throws PromoEngineException {

		log.info("*Inside confirmorder**");
		return promoservice.confirmorder(cart);

	}
}
