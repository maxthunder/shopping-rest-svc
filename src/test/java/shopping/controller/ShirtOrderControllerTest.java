package shopping.controller;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import shopping.TestUtils;
import shopping.service.ShirtOrderService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShirtOrderControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Mock
	private ShirtOrderService shirtOrderService;

	public ShirtOrderControllerTest() {}

	@Test
	public void testGetShirtOrder() throws Exception {
		Mockito.when(this.shirtOrderService.getAllShirtOrders())
				.thenReturn(Arrays.asList(TestUtils.getShirtOrderInfo(1)));

		this.mockMvc.perform(get("/shopping/shirtOrders"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content()
						.contentType("application/json;charset=UTF-8"));
	}

	// TODO: 2019-01-24 add other CRUD tests
}
