package shopping.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shopping.model.Customer;
import shopping.service.CustomerService;
import shopping.util.ErrorCallback;

import java.util.List;

@RestController
@RequestMapping({"${spring.data.rest.base-path}/customers"})
public class CustomerController extends ControllerBase {

	private final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * GET all Customers
	 */

	@GetMapping(value = "")
	@ApiOperation(value = "", notes = "Gets all customers.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = Customer.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})

	public List<Customer> getCustomers() {
		return customerService.getAllCustomers();
	}

	/**
	 * GET Customer by ID
	 */

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "/{id}", notes = "Gets customer by customer ID.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = Customer.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public Customer getCustomer(
			@ApiParam(name = "id",value = "ID of customer",required = true) @PathVariable("id") Integer id) {

		return customerService.getCustomer(id);
	}

	/**
	 * POST Customer
	 */

	@PostMapping(value = "")
	@ApiOperation(value = "", notes = "Creates new customer.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = Customer.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public Customer createCustomer(// TODO  replace String -> Customer
			@ApiParam(name = "name",value = "Name of customer",required = true) @RequestParam("name") String name) {

		return customerService.saveCustomer(name);
	}

	/**
	 * PUT Customer
	 */

	@PutMapping(value = "/{id}")
	@ApiOperation(value = "/{id}", notes = "Creates/updates customer at specified ID.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = Customer.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public Customer updateCustomer(
			@ApiParam(name = "id",value = "ID of customer",required = true) @PathVariable("id") Integer id,
			@ApiParam(name = "name",value = "Name of customer",required = true) @RequestParam("name") String name) {

		return customerService.updateCustomer(id, name);
	}

	/**
	 * DELETE Customer
	 */

	@DeleteMapping(value = "/{id}/{name}")
	@ApiOperation(value = "/{id}/{name}", notes = "Delete customer by ID and name (to protect against accidental deletions).")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = Customer.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public Customer deleteCustomer(
			@ApiParam(name = "id",value = "ID of customer",required = true) @PathVariable("id") Integer id,
			@ApiParam(name = "name",value = "Name of customer",required = true) @PathVariable("name") String name) {

		return customerService.deleteCustomer(id, name);
	}
}
