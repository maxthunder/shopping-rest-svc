package shopping.dao;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shopping.model.Customer;

import javax.transaction.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@Repository
@Transactional
public class CustomerDAOTest {

	@Autowired
	private ICustomerDAO customerDAO;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	@Ignore
	public void getAllCustomers() throws Exception {
		assertThat(customerDAO.getAllCustomers(), is(not(nullValue())));
	}

	@Test
	public void getCustomerById() throws Exception {
	}

	@Test
	public void getCustomerByName() throws Exception {
	}

	@Test
	public void getCustomerByIdAndName() throws Exception {
	}

	@Test
	public void saveOrUpdateCustomer() throws Exception {
	}

}
