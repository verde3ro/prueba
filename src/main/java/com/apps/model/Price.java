package com.apps.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
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
