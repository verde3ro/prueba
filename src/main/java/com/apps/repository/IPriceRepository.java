package com.apps.repository;

import com.apps.model.Price;
import com.apps.model.PricePK;
import com.apps.response.ProductPriceResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface IPriceRepository extends JpaRepository<Price, PricePK> {

	@Query(name = "findPriceByStartDateAndProductIdAndBrandId", nativeQuery = true)
	Optional<ProductPriceResponse> applyPrice(@Param("applyDate") Date applyDate, @Param("productId") Integer productId,
	                                          @Param("brandId") Integer brandId, @Param("onlyDate") Date onlyDate);

}