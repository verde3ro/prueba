package com.apps.controller;

import com.apps.exception.InternalException;
import com.apps.request.ProductPriceRequest;
import com.apps.response.ProductPriceResponse;
import com.apps.service.IPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/price")
public class PriceController {

	private final IPriceService priceService;

	@Autowired
	public PriceController(IPriceService priceService) {
		this.priceService = priceService;
	}

	@PostMapping(value = "applyPrice", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> applyPrice(@Valid @RequestBody ProductPriceRequest productPriceRequest, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = bindingResult.getFieldErrors()
						.stream()
						.collect(Collectors.toMap(FieldError::getField, fieldError -> (fieldError.getDefaultMessage() != null) ? fieldError.getDefaultMessage() : ""));

				return ResponseEntity
						.badRequest()
						.body(createMapResult("Validación de campo fallida", "validation_failed", errors));
			}

			List<ProductPriceResponse> productPrice = priceService.applyPrice(productPriceRequest);

			if (productPrice.isEmpty()) {
				return ResponseEntity
						.noContent()
						.build();
			}

			return ResponseEntity
					.ok()
					.body(createMapResult("Aplicación de tarifa satisfactoria", "success", productPrice));
		} catch (InternalException ex) {
			return ResponseEntity
					.internalServerError()
					.body(createMapResult("Error en la aplicación", "error", ex.getMessage()));
		}
	}

	private Map<String, Object> createMapResult(String message, String code, Object detail) {
		Map<String, Object> mapResult = new HashMap<>();

		mapResult.put("message", message);
		mapResult.put("code", code);
		mapResult.put("detail", detail);

		return mapResult;
	}

}
