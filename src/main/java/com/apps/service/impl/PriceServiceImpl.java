package com.apps.service.impl;

import com.apps.exception.InternalException;
import com.apps.repository.IPriceRepository;
import com.apps.request.ProductPriceRequest;
import com.apps.response.ProductPriceResponse;
import com.apps.service.IPriceService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PriceServiceImpl implements IPriceService {

	private final IPriceRepository priceRepository;

	@Autowired
	public PriceServiceImpl(IPriceRepository priceRepository) {
		this.priceRepository = priceRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public List<ProductPriceResponse> applyPrice(ProductPriceRequest productPriceRequest) {
		try {
			return priceRepository
					.applyPrice((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(productPriceRequest.getApplyDate().trim()), productPriceRequest.getProductId(), productPriceRequest.getBrandId())
					.sorted(Comparator.comparing(ProductPriceResponse::getStartDate).reversed())
					.collect(Collectors.toList());
		} catch (ParseException ex) {
			log.error("Ocurrió un error al recuperar la fecha de aplicación..", ex);
			throw new InternalException("Ocurrió un error al recuperar la fecha de aplicación.");
		} catch (HibernateException ex) {
			log.error("Ocurrió un error al obtener las tarifas.", ex);
			throw new InternalException("Ocurrió un error al obtener las tarifas.");
		}
	}

}
