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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shopping.model.ShirtOrder;
import shopping.service.ShirtOrderService;

import java.util.List;

@RestController
@RequestMapping({"${spring.data.rest.base-path}/shirtOrders"})
public class ShirtOrderController extends ControllerBase {

	private final ShirtOrderService shirtOrderService;

	@Autowired
	public ShirtOrderController(ShirtOrderService shirtOrderService) {
		this.shirtOrderService = shirtOrderService;
	}

	/**
	 * GET all Shirt Orders
	 */

	@GetMapping(value = "")
	@ApiOperation(value = "", notes = "Gets all shirt orders.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = ShirtOrder.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})

	public List<ShirtOrder> getShirtOrders() {
		return shirtOrderService.getAllShirtOrders();
	}



	/**
	 * GET Shirt order by ID
	 */

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "/{id}", notes = "Gets shirt order by ID.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = ShirtOrder.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public ShirtOrder getShirtOrder(
			@ApiParam(name = "id",value = "ID of shirt order",required = true) @PathVariable("id") Integer id) {

		return shirtOrderService.getShirtOrder(id);
	}

	/**
	 * POST Shirt order
	 */

	@PostMapping(value = "")
	@ApiOperation(value = "", notes = "Creates new shirt order.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = ShirtOrder.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public ShirtOrder createShirtOrder(// TODO  replace String -> ShirtOrder
			@ApiParam(name = "customerId",value = "ID of customer",required = true) @RequestParam("customerId") Integer customerId,
			@ApiParam(name = "shirtRefId",value = "ID of shirt ref",required = true) @RequestParam("shirtRefId") Integer shirtRefId) {

		return shirtOrderService.saveShirtOrder(customerId, shirtRefId);
	}

	/**
	 * PUT ShirtOrder
	 */
//
//	@PutMapping(value = "/{id}")
//	@ApiOperation(value = "/{id}", notes = "Creates/updates shirt order at specified ID.")
//	@ApiResponses({
//			@ApiResponse(code = 200, message = "Success", response = ShirtOrder.class),
//			@ApiResponse(code = 400, message = "Bad Request"),
//			@ApiResponse(code = 404, message = "Not Found"),
//			@ApiResponse(code = 500, message = "Internal Server Error")
//	})
//	public ShirtOrder updateShirtOrder(
//			@ApiParam(name = "id",value = "ID of shirt order",required = true) @PathVariable("id") Integer shirtOrderId,
//			@ApiParam(name = "customerId",value = "ID of customer",required = true) @RequestParam("customerId") Integer customerId,
//			@ApiParam(name = "shirtRefId",value = "ID of shirt ref",required = true) @RequestParam("shirtRefId") Integer shirtRefId) {
//
//		return shirtOrderService.updateShirtOrder(shirtOrderId, customerId, shirtRefId);
//	}

	/**
	 * DELETE ShirtOrder
	 */

	@DeleteMapping(value = "/{id}/{name}")
	@ApiOperation(value = "/{id}/{name}", notes = "Delete shirt order by ID and name (to protect against accidental deletions).")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = ShirtOrder.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public ShirtOrder deleteShirtOrder(
			@ApiParam(name = "id",value = "ID of shirt order",required = true) @PathVariable("id") Integer id) {

		return shirtOrderService.deleteShirtOrder(id);
	}

}
