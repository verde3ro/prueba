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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class PriceServiceImpl implements IPriceService {

	private final IPriceRepository priceRepository;

	@Autowired
	public PriceServiceImpl(IPriceRepository priceRepository) {
		this.priceRepository = priceRepository;
	}

	@Override
	public ProductPriceResponse applyPrice(ProductPriceRequest productPriceRequest) {
		try {
			DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
			Date applyDate = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(productPriceRequest.getApplyDate().trim());
			Date onlyDate = formatter.parse(formatter.format(applyDate));

			return priceRepository
					.applyPrice(applyDate, productPriceRequest.getProductId(), productPriceRequest.getBrandId(), onlyDate)
					.orElse(null);
		} catch (ParseException ex) {
			log.error("Ocurrió un error al recuperar la fecha de aplicación..", ex);
			throw new InternalException("Ocurrió un error al recuperar la fecha de aplicación.");
		} catch (HibernateException ex) {
			log.error("Ocurrió un error al obtener las tarifas.", ex);
			throw new InternalException("Ocurrió un error al obtener las tarifas.");
		}
	}

}
