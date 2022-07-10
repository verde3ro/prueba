package com.apps.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceRequest implements Serializable {

	private static final long serialVersionUID = -7363978898266362457L;
	@NotNull(message = "La fecha de aplicación no debe ser nula")
	private String applyDate;
	@NotNull(message = "El id del producto no debe ser nulo")
	@Positive(message = "El id del producto debe ser mayor a 0")
	private Integer productId;
	@NotNull(message = "El id de la cadena no debe ser nulo")
	@Positive(message = "El id de la cadena debe ser mayor a 0")
	private Integer brandId;

}
