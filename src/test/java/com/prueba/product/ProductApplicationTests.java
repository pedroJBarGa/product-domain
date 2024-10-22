package com.prueba.product;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ProductApplicationTests {

	private static final String PATH = "/api/product-service/v1/products/";
	private static final String BRAND = "brand";
	private static final String APPLICATION_DATE = "applicationDate";
	private static final String AND = "&";
	private static final String EQUAL = "=";
	private static final long PRODUCT_ID = 35455;
	private static final long PRODUCT_ID_NOT_FOUND = 35456;
	private static final int BRAND_VALUE = 1;
	private static final String GENERAL_VALUES_IN_RESPONSE = "\"productId\":35455,\"brandId\":1,";
	private static final String LABEL_RATE_APPLY = "\"rateApply\":";
	private static final String LABEL_START_DATE = ",\"startDate\":";
	private static final String LABEL_END_DATE = ",\"endDate\":";
	private static final String LABEL_FINAL_PRICE = ",\"finalPrice\":";

	@Autowired
	private MockMvc mvc;

	@Test
	void getProduct_ShouldReturnProductWithPrice35Point50() throws Exception {
		final String date = "2020-06-14T10:00:00Z";
		final String rateApply = "1";
		final String starDate = "\"2020-06-14T00:00:00Z\"";
		final String endDate = "\"2020-12-31T23:59:59Z\"";
		final String price = "35.50";

		MockHttpServletResponse response = testProductByProductIdAndBrandIdAndDate(PRODUCT_ID, date);

		validateResponse(response, starDate, endDate, price, rateApply);
	}

	@Test
	void getProduct_ShouldReturnProductWithPrice25Point45() throws Exception {
		final String date = "2020-06-14T16:00:00Z";
		final String rateApply = "2";
		final String starDate = "\"2020-06-14T15:00:00Z\"";
		final String endDate = "\"2020-06-14T18:30:00Z\"";
		final String price = "25.45";

		MockHttpServletResponse response = testProductByProductIdAndBrandIdAndDate(PRODUCT_ID, date);

		validateResponse(response, starDate, endDate, price, rateApply);
	}

	@Test
	void getProduct_ShouldReturnProductWithPrice35Point50And21Hours() throws Exception {
		final String date = "2020-06-14T21:00:00Z";
		final String rateApply = "1";
		final String starDate = "\"2020-06-14T00:00:00Z\"";
		final String endDate = "\"2020-12-31T23:59:59Z\"";
		final String price = "35.50";

		MockHttpServletResponse response = testProductByProductIdAndBrandIdAndDate(PRODUCT_ID, date);

		validateResponse(response, starDate, endDate, price, rateApply);
	}

	@Test
	void getProduct_ShouldReturnProductWithPrice30Point50() throws Exception {
		final String date = "2020-06-15T10:00:00Z";
		final String rateApply = "3";
		final String starDate = "\"2020-06-15T00:00:00Z\"";
		final String endDate = "\"2020-06-15T11:00:00Z\"";
		final String price = "30.50";

		MockHttpServletResponse response = testProductByProductIdAndBrandIdAndDate(PRODUCT_ID, date);

		validateResponse(response, starDate, endDate, price, rateApply);
	}

	@Test
	void getProduct_ShouldReturnProductWithPrice38Point95() throws Exception {
		final String date = "2020-06-16T21:00:00Z";
		final String rateApply = "4";
		final String starDate = "\"2020-06-15T16:00:00Z\"";
		final String endDate = "\"2020-12-31T23:59:59Z\"";
		final String price = "38.95";

		MockHttpServletResponse response = testProductByProductIdAndBrandIdAndDate(PRODUCT_ID, date);

		validateResponse(response, starDate, endDate, price, rateApply);
	}

	@Test
	void whenProductIdNotExistThenResponseNotFound() throws Exception {
		final String date = "2020-06-16T21:00:00Z";

		MockHttpServletResponse response = mvc.perform(
						MockMvcRequestBuilders
								.get(PATH + PRODUCT_ID_NOT_FOUND + "?"
										+ BRAND + EQUAL + BRAND_VALUE + AND
										+ APPLICATION_DATE + EQUAL + date)
				)
				.andExpect(status().isNotFound())
				.andReturn().getResponse();

		assertEquals(response.getStatus(), 404);

	}

	private MockHttpServletResponse testProductByProductIdAndBrandIdAndDate(final long productId, final String date) throws Exception {
		return mvc.perform(
						MockMvcRequestBuilders
								.get(PATH + productId + "?"
										+ BRAND + EQUAL + BRAND_VALUE + AND
										+ APPLICATION_DATE + EQUAL + date)
				)
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers
						.content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
	}

	private void validateResponse(MockHttpServletResponse response, final String startDate, final String endDate,
								  final String price, final String rateApply)
			throws UnsupportedEncodingException {

		String responseOk = "{" + GENERAL_VALUES_IN_RESPONSE + LABEL_RATE_APPLY + rateApply + LABEL_START_DATE +
				startDate + LABEL_END_DATE + endDate + LABEL_FINAL_PRICE + price + "}";
		assertEquals(response.getStatus(), 200);


		assertEquals(response.getContentAsString(), responseOk);

	}
}
