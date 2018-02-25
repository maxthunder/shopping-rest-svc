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
import shopping.model.ShirtOrderInfo;
import shopping.service.ShirtOrderService;

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

	public ShirtOrderControllerTest() {
	}

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(new Object[]{this.shirtOrderController}).build();
	}

	@Test
	public void testGetShirtOrders() throws Exception {
		Mockito.when(this.shirtOrderService.getAllShirtOrders())
				.thenReturn(Arrays.asList(new ShirtOrderInfo(), new ShirtOrderInfo(), new ShirtOrderInfo()));
		this.mockMvc.perform(MockMvcRequestBuilders.get("/shirtOrders", new Object[0])
				.accept(new MediaType[]{MediaType.APPLICATION_JSON}))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.contentType("application/json;charset=UTF-8"));
		Mockito.verify(this.shirtOrderService).getAllShirtOrders();
	}
}
