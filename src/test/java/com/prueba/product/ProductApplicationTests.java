package com.prueba.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
	private static final int BRAND_VALUE = 1;

	@Autowired
	private MockMvc mvc;

	@Test
	void test1() throws Exception {
		final String date = "2020-06-14T10:00:00Z";

		String responseJsonString = mvc.perform(
				MockMvcRequestBuilders
						.get(PATH + PRODUCT_ID + "?"
								+ BRAND + EQUAL + BRAND_VALUE + AND
								+ APPLICATION_DATE + EQUAL + date)
				)
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		assertTrue(responseJsonString.contains("\"productId\":35455"));
		assertTrue(responseJsonString.contains("\"finalPrice\":35.50"));
	}

	@Test
	void test2() throws Exception {
		final String date = "2020-06-14T16:00:00Z";

		String responseJsonString = mvc.perform(
						MockMvcRequestBuilders
								.get(PATH + PRODUCT_ID + "?"
										+ BRAND + EQUAL + BRAND_VALUE + AND
										+ APPLICATION_DATE + EQUAL + date)
				)
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		assertTrue(responseJsonString.contains("\"productId\":35455"));
		assertTrue(responseJsonString.contains("\"finalPrice\":25.45"));
	}

	@Test
	void test3() throws Exception {
		final String date = "2020-06-14T21:00:00Z";

		String responseJsonString = mvc.perform(
						MockMvcRequestBuilders
								.get(PATH + PRODUCT_ID + "?"
										+ BRAND + EQUAL + BRAND_VALUE + AND
										+ APPLICATION_DATE + EQUAL + date)
				)
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		assertTrue(responseJsonString.contains("\"productId\":35455"));
		assertTrue(responseJsonString.contains("\"finalPrice\":35.50"));
	}

	@Test
	void test4() throws Exception {
		final String date = "2020-06-15T10:00:00Z";

		String responseJsonString = mvc.perform(
						MockMvcRequestBuilders
								.get(PATH + PRODUCT_ID + "?"
										+ BRAND + EQUAL + BRAND_VALUE + AND
										+ APPLICATION_DATE + EQUAL + date)
				)
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		assertTrue(responseJsonString.contains("\"productId\":35455"));
		assertTrue(responseJsonString.contains("\"finalPrice\":30.50"));
	}

	@Test
	void test5() throws Exception {
		final String date = "2020-06-16T21:00:00Z";

		String responseJsonString = mvc.perform(
						MockMvcRequestBuilders
								.get(PATH + PRODUCT_ID + "?"
										+ BRAND + EQUAL + BRAND_VALUE + AND
										+ APPLICATION_DATE + EQUAL + date)
				)
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		assertTrue(responseJsonString.contains("\"productId\":35455"));
		assertTrue(responseJsonString.contains("\"finalPrice\":38.95"));
	}
}
