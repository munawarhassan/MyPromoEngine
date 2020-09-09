package com.custompromoengine.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.custompromoengine.rule.RuleEngine;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author SKHassan
 *
 */
@Getter
@Setter
@RequiredArgsConstructor
@Service
public class PromoService {
	final private PromoRepository promoRepo;
	final private OrderRepository orderRepo;
	private static Logger log = LoggerFactory.getLogger(PromoRepository.class);

	private List<ProductOrdered> productlistWithOffer = new ArrayList<ProductOrdered>();
	private List<ProductOrdered> productlistWithOutOffer = new ArrayList<ProductOrdered>();

	private List<Promo> defaultPromolist = new ArrayList<Promo>();

	/**
	 * @param promoname
	 * @return
	 */
	public Promo getpromo(String promoname) {
		Promo promo = promoRepo.findByName(promoname);
		return promo;
	}

	/**
	 * @param promo
	 * @return
	 */
	public ResponseEntity<?> addPromo(Promo promo) {
		try {
			promoRepo.save(promo);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PromoEngineException(ex.getMessage()));
		}

		return ResponseEntity.ok("successfully added in DB");
	}

	/**
	 * @param updatedpromo
	 * @return
	 */
	public ResponseEntity<?> updatepromo(Promo updatedpromo) {
		try {
			Promo promo = promoRepo.findByName(updatedpromo.getName());

			if (promo != null) {
				promoRepo.save(updatedpromo);
				log.info(" Record Updated Successfully");
				return ResponseEntity.ok("Record Updated Successfully");
			} else {
				return ResponseEntity.ok("Record Not Found");
			}
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PromoEngineException(ex.getMessage()));
		}
	}

	/**
	 * @param cart
	 * @return
	 * @throws PromoEngineException
	 */
	public FinalOrder confirmorder(Cart cart) throws PromoEngineException {
		try {
			Double price = 0.0;
			List<Promo> promolist = promoRepo.findAll();
			if (promolist == null || promolist.size() == 0) { // Added for Junit Testing
				promolist = this.getDefaultPromolist();

			}

			promolist.removeIf(promo -> (!promo.isActive()));
			findProductsWithOffer(promolist, cart);
			findProductsWithNoOffer(promolist, cart);
			if (productlistWithOffer.size() > 0) {
				price = price + calculaProductpriceWithOffer(promolist, productlistWithOffer);
			}
			if (productlistWithOutOffer.size() > 0) {
				price = price + calculaProductpriceWithoutOffer(productlistWithOutOffer);
			}

			FinalOrder myorder = new FinalOrder();
			String description = "";
			for (ProductOrdered productOrdered : cart.getProducts()) {
				description = description + "  " + productOrdered.getQuantity() + " " + productOrdered.getName() + ", ";
			}
			myorder.setDescription(description);
			myorder.setTotalprice(price);
			orderRepo.save(myorder);
			return myorder;
		} catch (Exception ex) {
			throw new PromoEngineException(ex.getMessage());
		}
	}

	/**
	 * @param activePromolist
	 * @param productlistWithOffer
	 * @return
	 */
	private Double calculaProductpriceWithOffer(List<Promo> activePromolist,
			List<ProductOrdered> productlistWithOffer) {
		BaseRule rule = new RuleEngine();
		return rule.calculaProductpriceWithOffer(activePromolist, productlistWithOffer);

	}

	/**
	 * @param productlistWithOutOffer
	 * @return
	 */
	private Double calculaProductpriceWithoutOffer(List<ProductOrdered> productlistWithOutOffer) {

		Double price = 0.0;
		ArrayList<Double> tempPriceList = new ArrayList<>();

		productlistWithOutOffer.forEach(product -> {
			tempPriceList.add(product.getQuantity() * product.getPrice());
		});

		price = tempPriceList.stream().filter(i -> i > tempPriceList.size()).mapToDouble(i -> i).sum();
		return price;
	}

	/**
	 * @param activePromoList
	 * @param cart
	 */
	private void findProductsWithOffer(List<Promo> activePromoList, Cart cart) {
		productlistWithOffer.clear();
		productlistWithOutOffer.clear();
		BaseRule rule = new RuleEngine();
		rule.segregateOrderedPoducts(cart.getProducts(), activePromoList, productlistWithOffer,
				productlistWithOutOffer);
	}

	/**
	 * @param activePromoList
	 * @param cart
	 */
	private void findProductsWithNoOffer(List<Promo> activePromoList, Cart cart) {
		for (ProductOrdered pruductordered : cart.getProducts()) {
			if (!(pruductordered.isOffervailable())) {
				productlistWithOutOffer.add(pruductordered);
			}
		}

	}

}
