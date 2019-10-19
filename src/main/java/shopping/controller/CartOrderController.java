package shopping.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopping.model.CartOrder;
import shopping.model.CartOrderInfo;
import shopping.service.CartOrderService;

import javax.validation.Valid;

@RestController
@RequestMapping({"/shopping/cartOrders"})
public class CartOrderController extends ControllerBase {

	private final CartOrderService CartOrderService;

	@Autowired
	public CartOrderController(CartOrderService CartOrderService) {
		this.CartOrderService = CartOrderService;
	}

	/**
	 * POST Phone Cart Order
	 */
	@PostMapping(value = "")
	@ApiOperation(value = "", notes = "Creates new phone cart order.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = CartOrderInfo.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public CartOrder createCartOrder(
			@ApiParam(value = "phone cart order data json", required = true)
				@Valid @RequestBody CartOrderInfo input) {
		return CartOrderService.saveCartOrder(input);
	}

}
