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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import shopping.TestUtils;
import shopping.service.CustomerService;

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

	public CustomerControllerTest() {
	}

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(new Object[]{this.customerController}).build();
	}

	@Test
	public void testGetCustomers() throws Exception {
		Mockito.when(this.customerService.getAllCustomers()).thenReturn(Arrays.asList(TestUtils.getCustomer(1), TestUtils.getCustomer(2), TestUtils.getCustomer(3)));
		this.mockMvc.perform(MockMvcRequestBuilders.get("/customers", new Object[0]).accept(new MediaType[]{MediaType.APPLICATION_JSON})).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
		Mockito.verify(this.customerService).getAllCustomers();
	}
}
