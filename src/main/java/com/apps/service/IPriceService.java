package com.apps.service;


import com.apps.request.ProductPriceRequest;
import com.apps.response.ProductPriceResponse;

import java.util.List;


public interface IPriceService {

	List<ProductPriceResponse> applyPrice(ProductPriceRequest productPriceRequest);

}
