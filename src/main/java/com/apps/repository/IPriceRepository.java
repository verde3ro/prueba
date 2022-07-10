package com.apps.repository;

import com.apps.model.Price;
import com.apps.model.PricePK;
import com.apps.response.ProductPriceResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.stream.Stream;

public interface IPriceRepository extends JpaRepository<Price, PricePK> {

	@Query("SELECT NEW com.apps.response.ProductPriceResponse(p.id.productId, p.id.brandId, p.id.priceList, p.startDate, p.endDate, p.price) " +
			"FROM Price p WHERE p.startDate >= :applyDate AND p.id.productId = :productId AND p.id.brandId = :brandId")
	Stream<ProductPriceResponse> applyPrice(@Param("applyDate") Date applyDate, @Param("productId") Integer productId, @Param("brandId") Integer brandId);

}