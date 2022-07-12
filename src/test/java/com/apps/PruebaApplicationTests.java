package com.apps;

import com.apps.request.ProductPriceRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class PruebaApplicationTests {

	@Autowired
	private WebTestClient webTestClient;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Order(1)
	void testStatusOkDayOneHourOne() throws Exception {
		ProductPriceRequest productPriceRequest = new ProductPriceRequest("2020-06-14 10:00:00", 35455, 1);

		webTestClient.post().uri("/api/price/applyPrice")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(objectMapper.writeValueAsString(productPriceRequest))
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.code").isEqualTo("success")
				.jsonPath("$.detail.price").isEqualTo(35.50D);
	}

	@Test
	@Order(2)
	void testStatusOkDayOneHourTwo() throws Exception {
		ProductPriceRequest productPriceRequest = new ProductPriceRequest("2020-06-14 16:00:00", 35455, 1);

		webTestClient.post().uri("/api/price/applyPrice")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(objectMapper.writeValueAsString(productPriceRequest))
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.code").isEqualTo("success")
				.jsonPath("$.detail.price").isEqualTo(25.45D);
	}

	@Test
	@Order(3)
	void testStatusOkDayOneHourThree() throws Exception {
		ProductPriceRequest productPriceRequest = new ProductPriceRequest("2020-06-14 21:00:00", 35455, 1);

		webTestClient.post().uri("/api/price/applyPrice")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(objectMapper.writeValueAsString(productPriceRequest))
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.code").isEqualTo("success")
				.jsonPath("$.detail.price").isEqualTo(35.50D);
	}

	@Test
	@Order(4)
	void testStatusOkDayTwo() throws Exception {
		ProductPriceRequest productPriceRequest = new ProductPriceRequest("2020-06-15 10:00:00", 35455, 1);

		webTestClient.post().uri("/api/price/applyPrice")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(objectMapper.writeValueAsString(productPriceRequest))
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.code").isEqualTo("success")
				.jsonPath("$.detail.price").isEqualTo(30.50D);
	}

	@Test
	@Order(5)
	void testStatusOkDayThree() throws Exception {
		ProductPriceRequest productPriceRequest = new ProductPriceRequest("2020-06-16 21:00:00", 35455, 1);

		webTestClient.post().uri("/api/price/applyPrice")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(objectMapper.writeValueAsString(productPriceRequest))
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.code").isEqualTo("success")
				.jsonPath("$.detail.price").isEqualTo(38.95D);
	}

	@Test
	@Order(6)
	void testStatusInternalError() throws Exception {
		ProductPriceRequest productPriceRequest = new ProductPriceRequest("2020-06-16 21:00", 35455, 1);

		webTestClient.post().uri("/api/price/applyPrice")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(objectMapper.writeValueAsString(productPriceRequest))
				.exchange()
				.expectStatus().is5xxServerError()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.code").isEqualTo("error")
				.jsonPath("$.message").isEqualTo("Error en la aplicación");
	}

	@Test
	@Order(7)
	void testStatusBadRequest() throws Exception {
		ProductPriceRequest productPriceRequest = new ProductPriceRequest("2020-06-15 10:00:00", 0, -1);

		webTestClient.post().uri("/api/price/applyPrice")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(objectMapper.writeValueAsString(productPriceRequest))
				.exchange()
				.expectStatus().isBadRequest()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.code").isEqualTo("validation_failed");
	}

}
