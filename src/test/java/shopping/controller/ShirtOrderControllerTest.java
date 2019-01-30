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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import shopping.service.ShirtOrderService;

import static org.mockito.Matchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static shopping.TestUtils.getShirtOrder;
import static shopping.TestUtils.getShirtOrderInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShirtOrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private ShirtOrderService shirtOrderService;

	@InjectMocks
	private ShirtOrderController shirtOrderController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(shirtOrderController)
				.build();
	}

	@Test
	public void testGetShirtOrders() throws Exception {
		Mockito.when(this.shirtOrderService.getAllShirtOrders())
				.thenReturn(Arrays.asList(getShirtOrderInfo(1)));

		this.mockMvc.perform(get("/shopping/shirtOrders"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testGetShirtOrder() throws Exception {
		Mockito.when(this.shirtOrderService.getShirtOrderById(anyInt()))
				.thenReturn(getShirtOrder(1));

		this.mockMvc.perform(get("/shopping/shirtOrders/1"))
				.andDo(print())
				.andExpect(status().isOk());
	}
}
