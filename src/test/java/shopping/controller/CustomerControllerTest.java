package shopping.controller;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import shopping.TestUtils;
import shopping.service.CustomerService;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private CustomerService customerService;

	@InjectMocks
	private CustomerController customerController;

	public CustomerControllerTest() {}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController)
				.build();
	}

	@Test
	public void testGetCustomers() throws Exception {
		Mockito.when(this.customerService.getAllCustomers())
				.thenReturn(Arrays.asList(TestUtils.getCustomer(1), TestUtils.getCustomer(2), TestUtils.getCustomer(3)));

		this.mockMvc.perform(get("/shopping/customers"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testGetCustomer() throws Exception {
		Mockito.when(this.customerService.getCustomer(anyInt()))
				.thenReturn(TestUtils.getCustomer(1));

		this.mockMvc.perform(get("/shopping/customers/1"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testCreateCustomer() throws Exception {
		Mockito.when(this.customerService.saveCustomer(anyString()))
				.thenReturn(TestUtils.getCustomer(1));

		this.mockMvc.perform(post("/shopping/customers")
				.param("name", "name1"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testUpdateCustomer() throws Exception {
		Mockito.when(this.customerService.updateCustomer(anyInt(), anyString()))
				.thenReturn(TestUtils.getCustomer(1));

		this.mockMvc.perform(put("/shopping/customers/1")
				.param("name", "name1"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteCustomer() throws Exception {
		Mockito.when(this.customerService.deleteCustomer(anyInt(), anyString()))
				.thenReturn(TestUtils.getCustomer(1));

		this.mockMvc.perform(delete("/shopping/customers/1/name1"))
				.andDo(print())
				.andExpect(status().isOk());
	}
}
