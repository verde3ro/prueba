package com.apps.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceResponse implements Serializable {

	private static final long serialVersionUID = 6760181177310803347L;
	private Integer productId;
	private Integer brandId;
	private Short priceList;
	private Date endDate;
	private Date startDate;
	private BigDecimal price;

}
