package com.ey.javatest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@SpringBootTest
public class JavatestApplicationTests {

	@Autowired
	private WebApplicationContext applicationContext;


	private MockMvc mockMvc;

	String request_ok = "{\"name\": \"dummy1\",\"email\": \"test@test.cl\",\"password\": \"FFFw2ww2w\",\"phones\": [{\"number\": \"1234567\",\"citycode\": \"1\",\"countrycode\": \"57\"}]}";
	String mail_request_ok = "test@test.cl";
	String request_reg_email_error = "{\"name\": \"dummy2\",\"email\": \"test@test.cl\",\"password\": \"FFFw2ww2w\",\"phones\": [{\"number\": \"1234567\",\"citycode\": \"1\",\"countrycode\": \"57\"}]}";
	String request_email_format_error = "{\"name\": \"dummy3\",\"email\": \"testtest.cl\",\"password\": \"FFFw2ww2w\",\"phones\": [{\"number\": \"1234567\",\"citycode\": \"1\",\"countrycode\": \"57\"}]}";
	String request_password_error = "{\"name\": \"dummy4\",\"email\": \"test@test.cl\",\"password\": \"w2ww2w\",\"phones\": [{\"number\": \"1234567\",\"citycode\": \"1\",\"countrycode\": \"57\"}]}";

	@Test
	public void contextLoads() {

	}

	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
	}

	@Test
	public void registerUser_ok() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createuser").accept(MediaType.APPLICATION_JSON)
				.content(request_ok).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

	@Test
	public void registerUser_reg_email_error() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createuser").accept(MediaType.APPLICATION_JSON)
				.content(request_reg_email_error).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

	}

	@Test
	public void registerUser_email_format_error() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createuser").accept(MediaType.APPLICATION_JSON)
				.content(request_email_format_error).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

	}

	@Test
	public void registerUser_password_error() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createuser").accept(MediaType.APPLICATION_JSON)
				.content(request_password_error).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

	}
	
	@Test
	public void registerUser_method_not_allowed() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/createuser").accept(MediaType.APPLICATION_JSON)
				.content(request_ok).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), response.getStatus());

	}
	
	@Test
	public void registerUser_xml_mediatype() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/createuser").accept(MediaType.APPLICATION_JSON)
				.content(request_ok).contentType(MediaType.APPLICATION_XML);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), response.getStatus());

	}

}