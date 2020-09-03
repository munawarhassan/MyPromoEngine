package com.custompromoengine.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.custompromoengine.model.Promo;


@Repository
public interface PromoRepository extends JpaRepository<Promo, Integer> {
	
	 Promo  findByName(String name);
	 List<Promo> findAll();
	

}
