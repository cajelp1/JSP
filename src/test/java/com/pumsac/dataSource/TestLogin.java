package com.pumsac.dataSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.pumsac.controller.CommonsController;

@ContextConfiguration("classpath:com/pumsac/context/servlet-context.xml")
public class TestLogin {
	
	//@Autowired private CommonsController2 commonsController2;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setUpController() throws Exception {
		System.out.println("�½�Ʈ");
		
		mockMvc = MockMvcBuilders.standaloneSetup(new CommonsController()).build();
	}
	
	@Test
	@DisplayName(value = "�α��� �� ȭ�� �׽�Ʈ")	
	public void getLoginForm() throws Exception {
		mockMvc.perform(get("/commons/login")).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	@DisplayName(value="�α���  POST �׽�Ʈ")
	public void postLoginForm() throws Exception {
	  mockMvc.perform(post("/commons/login").param("email", "admin@admin.com")).andExpect(status().isOk()).andDo(print());	
	}
	
}
