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
	
	@Autowired
	private PromoRepository promoRepo;	
	
	@Autowired
	private OrderRepository orderRepo;	
	
	private static Logger log = LoggerFactory.getLogger(PromoRepository.class);
	
	private List<ProductOrdered> productlistWithOffer = new ArrayList<ProductOrdered>();
	private List<ProductOrdered> productlistWithOutOffer = new ArrayList<ProductOrdered>();
	
	
	 public List<ProductOrdered> getProductlistWithOffer() {
			return productlistWithOffer;
		}


		public void setProductlistWithOffer(List<ProductOrdered> productlistWithOffer) {
			this.productlistWithOffer = productlistWithOffer;
		}


		public List<ProductOrdered> getProductlistWithOutOffer() {
			return productlistWithOutOffer;
		}


		public void setProductlistWithOutOffer(List<ProductOrdered> productlistWithOutOffer) {
			this.productlistWithOutOffer = productlistWithOutOffer;
		}

	
	
	 public ResponseEntity<?> addPromo(Promo promo){ 
		 try {
			 promoRepo.save(promo); 
			 log.info(" Record Updated Successfully");
		 }catch (Exception ex) {			 
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PromoEngineException(ex.getMessage()));		 
		 }
		 
		  return  ResponseEntity.ok("successfully added in DB");
	 }
	 
	 
		public ResponseEntity<?>  updatepromo(Promo updatedpromo){
			try {			
				Promo  promo = promoRepo.findByName(updatedpromo.getName());
				
				if(promo !=null) {
					promoRepo.save(updatedpromo);
					log.info(" Record Updated Successfully");
					return ResponseEntity.ok("Record Updated Successfully");
				}else {
					return ResponseEntity.ok("Record Not Found");
				}
			} catch (Exception ex) {			
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PromoEngineException(ex.getMessage()));
			}
		}
		
		public Promo getpromo(String promoname){		
			Promo  promo = promoRepo.findByName(promoname);
			return promo;
		}


		 public ResponseEntity<?> confirmorder(Cart cart) {
				Double price = 0.0;
				
				try{List<Promo>  promolist= promoRepo.findAll();
				 promolist.removeIf(promo -> (!promo.isActive()));		 
				 findProductsWithOffer(promolist,cart);
				 findProductsWithNoOffer(promolist,cart);
				 
				 return  ResponseEntity.ok(cart.getProducts());
				 }catch (Exception ex) {			
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PromoEngineException(ex.getMessage()));
					}
		}
		 
		 
		 private void findProductsWithOffer(List<Promo> activePromoList,Cart cart) {				
				 BaseRule br = null;
				 List<ProductOrdered> tempProductList=  null;	
				 for(Promo promo : activePromoList) {	
					 if(promo.getName().equalsIgnoreCase("PROMO_A")) {					 
						  br = new PromoFirstRule();
						  tempProductList= new ArrayList<ProductOrdered>();
						  tempProductList.addAll(cart.getProducts());					 
						  tempProductList.removeIf(product -> (!(product.getSkuId().equalsIgnoreCase("A"))));						  
							  br.evaluateCondition(tempProductList.get(0),productlistWithOffer,productlistWithOutOffer);					 
						 		
					 }else if(promo.getName().equalsIgnoreCase("PROMO_B")) {					 
						  br = new PromoSecondRule();	
						  tempProductList= new ArrayList<ProductOrdered>();
						  tempProductList.addAll(cart.getProducts());					  
						  tempProductList.removeIf(product -> (!(product.getSkuId().equalsIgnoreCase("B"))));						  
							  br.evaluateCondition(tempProductList.get(0),productlistWithOffer,productlistWithOutOffer);	
					 }else if(promo.getName().equalsIgnoreCase("PROMO_CD")) {					 
						  br = new PromoThirdRule();	
						  tempProductList= new ArrayList<ProductOrdered>();
						  tempProductList.addAll(cart.getProducts());
						  tempProductList.removeIf(product -> (product.getSkuId().equalsIgnoreCase("A")));
						  tempProductList.removeIf(product -> (product.getSkuId().equalsIgnoreCase("B")));
						  br.evaluateCondition(tempProductList,productlistWithOffer,productlistWithOutOffer);
					 }else if(promo.getName().equalsIgnoreCase("PROMO_ALL")) {					 
						  br = new PromoForthRule();	
						  tempProductList= new ArrayList<ProductOrdered>();
						  tempProductList.addAll(cart.getProducts());	
						  br.evaluateCondition(tempProductList,productlistWithOffer,productlistWithOutOffer);
					 }
					 
				 }
			 
			 
			}
			
			private void findProductsWithNoOffer(List<Promo> activePromoList,Cart cart) {
				 for(ProductOrdered pruductordered : cart.getProducts()) {
					 if(!(pruductordered.isOffervailable())) {
						 productlistWithOutOffer.add(pruductordered);
					 }
				 }
				 
			}
		
		
	
			 
}
