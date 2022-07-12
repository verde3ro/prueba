package com.apps.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Embeddable
public class PricePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "BRAND_ID", insertable = false, updatable = false, nullable = false)
	private Integer brandId;

	@Column(name = "PRODUCT_ID", insertable = false, updatable = false, nullable = false)
	private Integer productId;

	@Column(name = "PRICE_LIST", insertable = false, updatable = false, nullable = false)
	private Short priceList;

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PricePK)) {
			return false;
		}
		PricePK castOther = (PricePK) other;
		return
				(Objects.equals(this.brandId, castOther.brandId))
						&& (Objects.equals(this.productId, castOther.productId))
						&& (Objects.equals(this.priceList, castOther.priceList));
	}

	public int hashCode() {
		final var prime = 31;
		var hash = 17;
		hash = hash * prime + this.brandId;
		hash = hash * prime + this.productId;
		hash = hash * prime + ((int) this.priceList);

		return hash;
	}

}