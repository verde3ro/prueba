package com.apps.service;


import com.apps.request.ProductPriceRequest;
import com.apps.response.ProductPriceResponse;

public interface IPriceService {

	ProductPriceResponse applyPrice(ProductPriceRequest productPriceRequest);

}
