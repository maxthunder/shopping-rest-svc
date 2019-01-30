package shopping.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.rules.ExpectedException;
import shopping.TestUtils;
import shopping.dao.IBaseDAO;
import shopping.dao.ICustomerDAO;
import shopping.model.Customer;
import shopping.util.BadRequestException;
import shopping.util.ResourceNotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
	@Rule public ExpectedException expectedException = ExpectedException.none();

	@Mock private IBaseDAO baseDAO;
	@Mock private ICustomerDAO customerDAO;

	@InjectMocks private CustomerService customerService;

	@Test
	public void getAllCustomers() throws Exception {
		List<Customer> customers = Arrays.asList(TestUtils.getCustomer(1), TestUtils.getCustomer(2), TestUtils.getCustomer(3));
		when(customerDAO.getAllCustomers()).thenReturn(customers);
		assertThat("Return of all customer should not be empty (technically exception will get thrown)",
				customerService.getAllCustomers(), is(not(nullValue())));
		verify(customerDAO).getAllCustomers();
	}

	@Test
	public void getCustomer() throws Exception {
		Integer customerId = 1;
		Customer expected = TestUtils.getCustomer(customerId);
		when(customerDAO.getCustomerById(customerId)).thenReturn(expected);
		assertCustomerFields(expected, customerService.getCustomer(customerId));
		verify(customerDAO).getCustomerById(anyInt());


	}

	@Test
	public void getCustomer_CustomerNotFound() throws Exception {
		Integer customerId = 1;
		when(customerDAO.getCustomerById(customerId)).thenReturn(null);
		expectedException.expect(ResourceNotFoundException.class);
		expectedException.expectMessage("Customer at ID <"+customerId+"> not found in database.");
		customerService.getCustomer(customerId);
	}

	@Test
	public void saveCustomer() throws Exception {
		String customerName = "customer1";
		Customer expected = TestUtils.getCustomer(1);
		when(customerDAO.getCustomerByName(customerName)).thenReturn(null);
		when(customerDAO.saveOrUpdateCustomer(customerName)).thenReturn(expected);
		assertCustomerFields(expected, customerService.saveCustomer(customerName));
		verify(customerDAO).getCustomerByName(anyString());
	}

	@Test
	public void saveCustomer_CustomerNameAlreadyExists() throws Exception {
		String customerName = "customer1";
		when(customerDAO.getCustomerByName(customerName)).thenReturn(new Customer());
		expectedException.expect(BadRequestException.class);
		expectedException.expectMessage("Customer at name <"+customerName+"> already exists in database.");
		customerService.saveCustomer(customerName);
	}

	@Test
	public void updateCustomer() throws Exception {
		String customerName = "customer2";
		Customer input = TestUtils.getCustomer(1);
		when(customerDAO.getCustomerById(input.getCustomerId())).thenReturn(input);
		when(customerDAO.getCustomerByName(customerName)).thenReturn(null);

		Customer expected = new Customer(input.getCustomerId(), customerName);
		when(customerDAO.saveOrUpdateCustomer(input.getCustomerId(), customerName)).thenReturn(expected);

		assertCustomerFields(expected, customerService.updateCustomer(input.getCustomerId(), customerName));
		verify(customerDAO).getCustomerById(anyInt());
		verify(customerDAO).getCustomerByName(anyString());
	}

	@Test
	public void updateCustomer_CustomerIDNotFound() throws Exception {
		String customerName = "customer2";
		Customer input = TestUtils.getCustomer(1);
		when(customerDAO.getCustomerById(input.getCustomerId())).thenReturn(null);
		expectedException.expect(ResourceNotFoundException.class);
		expectedException.expectMessage("Customer at ID <"+input.getCustomerId()+"> not found in database.");
		customerService.updateCustomer(input.getCustomerId(), customerName);
	}

	@Test
	public void updateCustomer_CustomerNameAlreadyExists() throws Exception {
		String customerName = "customer2";
		Customer input = TestUtils.getCustomer(1);
		when(customerDAO.getCustomerById(input.getCustomerId())).thenReturn(input);
		when(customerDAO.getCustomerByName(customerName)).thenReturn(new Customer());
		expectedException.expect(BadRequestException.class);
		expectedException.expectMessage("Customer at name <"+customerName+"> already exists in database.");
		customerService.updateCustomer(input.getCustomerId(), customerName);
	}

	@Test
	public void deleteCustomer() throws Exception {
		Customer expected = TestUtils.getCustomer(1);
		when(customerDAO.getCustomerByIdAndName(expected.getCustomerId(), expected.getCustomerName())).thenReturn(expected);
		doNothing().when(baseDAO).delete(any());
		assertCustomerFields(expected, customerService.deleteCustomer(expected.getCustomerId(), expected.getCustomerName()));
	}

	@Test
	public void deleteCustomer_CustomerIDNameComboNotFound() throws Exception {
		Customer expected = TestUtils.getCustomer(1);
		when(customerDAO.getCustomerByIdAndName(expected.getCustomerId(), expected.getCustomerName())).thenReturn(null);
		expectedException.expect(ResourceNotFoundException.class);
		expectedException.expectMessage("Customer at <ID, Name> : <"+expected.getCustomerId()+","
				+expected.getCustomerName()+"> not found in database.");
		assertCustomerFields(expected, customerService.deleteCustomer(expected.getCustomerId(), expected.getCustomerName()));
	}

	private void assertCustomerFields(Customer expected, Customer actual) {
		assertThat("Customer IDs should match.", actual.getCustomerId(), is(expected.getCustomerId()));
		assertThat("Customer names should match.", actual.getCustomerName(), is(expected.getCustomerName()));
	}

}
