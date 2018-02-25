package shopping.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shopping.model.ShirtOrder;
import shopping.service.ShirtOrderService;

@RestController
@RequestMapping({"${spring.data.rest.base-path}/shirtOrders"})
public class ShirtOrderController extends ControllerBase {

	@Autowired
	private ShirtOrderService shirtOrderService;

	@ApiOperation(value = "", notes = "Get all shirt orders.")
	@RequestMapping(method = RequestMethod.GET, value = "", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = ShirtOrder.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Failure")
	}) public Iterable<ShirtOrder> getShirtOrders() {
		return this.shirtOrderService.getAllShirtOrders();
	}

	@ApiOperation(value = "/{id}", notes = "Get all shirt orders.")
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = ShirtOrder.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Failure")
	}) public ShirtOrder getShirtOrder(
			@ApiParam(name = "id",value = "id of shirt order",required = true) @PathVariable("id") Integer id) {
		return this.shirtOrderService.getShirtOrder(id);
	}

	@ApiOperation(value = "", notes = "Create new shirt order.")
	@RequestMapping(method = RequestMethod.POST, value = "", produces = "application/json")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = ShirtOrder.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Failure")
	})
	public ShirtOrder createShirtOrder(
			@ApiParam(name = "shirtName",value = "Name of shirt",required = true) @RequestParam("shirtName") String shirtName,
			@ApiParam(name = "size",value = "Size of shirt",required = true) @RequestParam("size") String size,
			@ApiParam(name = "customerId",value = "ID of customer",required = true) @RequestParam("customerId") Integer customerId) {
		return this.shirtOrderService.createShirtOrder(shirtName, size, customerId);
	}
}
