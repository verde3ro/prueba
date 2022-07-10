package com.apps.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "BRANDS")
@NamedQuery(name = "Brand.findAll", query = "SELECT b FROM Brand b")
public class Brand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;

	@Column(name = "NAME", length = 50)
	private String name;

	@OneToMany(mappedBy = "brand")
	private List<Price> prices;

	public Price addPrice(Price price) {
		getPrices().add(price);
		price.setBrand(this);

		return price;
	}

	public Price removePrice(Price price) {
		getPrices().remove(price);
		price.setBrand(null);

		return price;
	}

}
