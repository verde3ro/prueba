package com.apps.model;

import com.apps.response.ProductPriceResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "PRICES")
@NamedQuery(name = "Price.findAll", query = "SELECT p FROM Price p")
@NamedNativeQuery(name = "findPriceByStartDateAndProductIdAndBrandId",
		query = "SELECT PRODUCT_ID, BRAND_ID, PRICE_LIST, TO_CHAR(START_DATE, 'DD/MM/YYYY HH24:MI:SS') START_DATE, TO_CHAR(END_DATE, 'DD/MM/YYYY HH24:MI:SS') END_DATE, PRICE FROM PRICES WHERE " +
				":applyDate BETWEEN START_DATE AND END_DATE AND PRODUCT_ID = :productId AND BRAND_ID = :brandId " +
				"ORDER BY PRIORITY DESC LIMIT 1",
		resultSetMapping = "ProductPriceResponse"
)
@SqlResultSetMapping(name = "ProductPriceResponse",
		classes = @ConstructorResult(
				targetClass = ProductPriceResponse.class,
				columns = {
						@ColumnResult(name = "PRODUCT_ID", type = Integer.class),
						@ColumnResult(name = "BRAND_ID", type = Integer.class),
						@ColumnResult(name = "PRICE_LIST", type = Short.class),
						@ColumnResult(name = "START_DATE", type = String.class),
						@ColumnResult(name = "END_DATE", type = String.class),
						@ColumnResult(name = "PRICE", type = BigDecimal.class)
				})
)
public class Price implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PricePK id;

	@Column(name = "CURR", nullable = false, length = 10)
	private String curr;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATE", nullable = false)
	private Date endDate;

	@Column(name = "PRICE", nullable = false, precision = 10, scale = 2)
	private BigDecimal price;

	@Column(name = "PRIORITY", nullable = false)
	private short priority;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_DATE", nullable = false)
	private Date startDate;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRAND_ID", nullable = false, insertable = false, updatable = false)
	private Brand brand;

}
