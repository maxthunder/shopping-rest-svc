package shopping.controller;

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
import shopping.model.ShirtRef;
import shopping.service.ShirtRefService;

import java.util.Arrays;

import static org.mockito.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShirtRefControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Mock
	private ShirtRefService shirtRefService;

	@InjectMocks
	private ShirtRefController shirtRefController;

	public ShirtRefControllerTest() {}

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(shirtRefController)
				.build();
	}

	@Test
	public void testGetShirtRefs() throws Exception {
		Mockito.when(this.shirtRefService.getAllShirtRefs())
				.thenReturn(Arrays.asList(TestUtils.getShirtRef(1)));

		this.mockMvc.perform(get("/shopping/shirts"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testGetShirtRef() throws Exception {
		Mockito.when(this.shirtRefService.getShirtRefById(anyInt()))
				.thenReturn(TestUtils.getShirtRef(1));

		this.mockMvc.perform(get("/shopping/shirts/1"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testCreateShirtRef() throws Exception {
		Mockito.when(this.shirtRefService.saveShirtRef(any(ShirtRef.class)))
				.thenReturn(TestUtils.getShirtRef(1));

		this.mockMvc.perform(post("/shopping/shirts")
				.param("name", "name1")
				.param("size", "size1")
				.param("style", "style1"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testUpdateShirtRef() throws Exception {
		Mockito.when(this.shirtRefService.updateShirtRef(any(ShirtRef.class)))
				.thenReturn(TestUtils.getShirtRef(1));

		this.mockMvc.perform(put("/shopping/shirts/1")
				.param("name", "name1")
				.param("size", "size1")
				.param("style", "style1"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteShirtRef() throws Exception {
		Mockito.when(this.shirtRefService.deleteShirtRef(anyInt(), anyString()))
				.thenReturn(TestUtils.getShirtRef(1));

		this.mockMvc.perform(delete("/shopping/shirts/1/name1"))
				.andDo(print())
				.andExpect(status().isOk());
	}
}
