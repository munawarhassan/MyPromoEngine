package com.custompromoengine.rule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.custompromoengine.model.ProductOrdered;
import com.custompromoengine.model.Promo;

/**
 * @author SKHassan
 *
 */

public class RuleEngine implements BaseRule {

	@Override
	public Double calculaProductpriceWithOffer(List<Promo> activePromolist, List<ProductOrdered> productlistWithOffer) {
		HashMap<String, ProductOrdered> productOrderedmap = new HashMap<>();
		productlistWithOffer.stream().forEach(product -> {
			productOrderedmap.put(product.getSkuId(), product);
		});
		Double price = 0.0;
		ArrayList<Double> tempPriceList = new ArrayList<>();
		ArrayList<Integer> productquantity = new ArrayList<>();

		activePromolist.forEach(promo -> {
			List<ProductOrdered> tempOfferedProductList = new ArrayList<ProductOrdered>();
			List<String> skuids = promo.getSkuList();
			skuids.forEach(skuid -> {
				if (null != productOrderedmap.get(skuid))
					tempOfferedProductList.add(productOrderedmap.get(skuid));
			});
			if (promo.getDiscountpercent() != null) {
				tempOfferedProductList.forEach(product -> {
					tempPriceList.add((product.getPrice()) * ((100.00 - promo.getDiscountpercent()) / 100)
							* product.getQuantity());
				});
			} else {
				if (tempOfferedProductList.size() == 1) {
					if (null != promo.getUnitofferprice()) {
						tempPriceList
								.add(((tempOfferedProductList.get(0).getQuantity()) / promo.getMinpurchaseforoffer())
										* promo.getUnitofferprice());
					}
				} else if (tempOfferedProductList.size() > 1) {
					tempOfferedProductList.forEach(product -> {
						productquantity.add(product.getQuantity());
					});
					Integer combinedquantity = productquantity.stream().mapToInt(Integer::intValue).sum();
					if (null != promo.getUnitofferprice()) {
						tempPriceList
								.add(combinedquantity * (promo.getUnitofferprice() / promo.getMinpurchaseforoffer()));

					} else if (null == promo.getUnitofferprice() && promo.getDiscountpercent() > 0) {

						tempOfferedProductList.forEach(product -> {
							tempPriceList.add(product.getQuantity() * product.getPrice()
									* ((100 - promo.getDiscountpercent()) / 100));
						});

					}

				}
			}

		});

		price = tempPriceList.stream().filter(i -> i > tempPriceList.size()).mapToDouble(i -> i).sum();
		return price;
	}

	private ProductOrdered retunDummyProduct(ProductOrdered dummyProduct) {
		return new ProductOrdered(dummyProduct.getSkuId(), dummyProduct.getName(), dummyProduct.getDescription(),
				dummyProduct.getQuantity(), dummyProduct.getPrice(), dummyProduct.isOffervailable());
	}

	@Override
	public void segregateOrderedPoducts(List<ProductOrdered> productOrdered_list, List<Promo> activePromoList,
			List<ProductOrdered> productlistWithOffer, List<ProductOrdered> productlistWithOutOffer) {
		HashMap<String, ProductOrdered> productOrderedmap = new HashMap<>();

		productOrdered_list.forEach(product -> {
			if (product.isOffervailable())
				productOrderedmap.put(product.getSkuId(), product);
		});

		activePromoList.forEach(rule -> {
			System.out.println("rule name = " + rule.getName());
			List<String> skuids = rule.getSkuList();
			List<ProductOrdered> tempProductList = new ArrayList<ProductOrdered>();
			ArrayList<Integer> sizelist = new ArrayList<>();
			skuids.forEach(skuid -> {
				if (null != productOrderedmap.get(skuid)) {
					tempProductList.add(productOrderedmap.get(skuid));
					sizelist.add(productOrderedmap.get(skuid).getQuantity());
				}
			});

			if (rule.getDiscountpercent() != null) {
				productlistWithOffer.addAll(tempProductList);

			} else {
				if (tempProductList.size() == skuids.size()) {
					Collections.sort(sizelist);
					tempProductList.forEach(tempProduct -> {
						ProductOrdered withOffer = retunDummyProduct(tempProduct);
						ProductOrdered withOutOffer = retunDummyProduct(tempProduct);
						int offer_not_eligible = 0;
						int offer_eligible = 0;
						if (skuids.size() > 1) {
							offer_not_eligible = tempProduct.getQuantity() - sizelist.get(0);
							offer_eligible = tempProduct.getQuantity() - offer_not_eligible;
						} else {
							offer_not_eligible = sizelist.get(0) % rule.getMinpurchaseforoffer();
							offer_eligible = sizelist.get(0) - offer_not_eligible;
						}
						if (offer_eligible > 0) {
							withOffer.setQuantity(offer_eligible);
							productlistWithOffer.add(withOffer);
						}
						if (offer_not_eligible > 0) {
							withOutOffer.setQuantity(offer_not_eligible);
							productlistWithOutOffer.add(withOutOffer);
						}
					});

				} else {
					tempProductList.parallelStream().forEach(tempProduct -> {
						ProductOrdered withOutOffer = retunDummyProduct(tempProduct);
						productlistWithOutOffer.add(withOutOffer);

					});
				}
			}
		});

	}

}
