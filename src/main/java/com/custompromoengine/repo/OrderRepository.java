package com.custompromoengine.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.custompromoengine.model.FinalOrder;

@Repository
public interface OrderRepository extends JpaRepository<FinalOrder, Integer> {
}
