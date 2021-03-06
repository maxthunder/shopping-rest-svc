package shopping.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopping.model.CartOrderExport;
import shopping.model.CartOrderInfo;
import shopping.service.CartOrderService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping({"/shopping/cartOrders"})
@CrossOrigin("http://localhost:4200")
public class CartOrderController extends ControllerBase {

	private final CartOrderService cartOrderService;

	@Autowired
	public CartOrderController(CartOrderService CartOrderService) {
		this.cartOrderService = CartOrderService;
	}

	/**
	 * Get Cart Order
	 */
	@GetMapping(value = "")
	@ApiOperation(value = "", notes = "Get cart order.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = CartOrderExport.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public List<CartOrderExport> getCartOrder(
			@ApiParam(value = "customerId")
				@RequestParam(value = "customerId", required = false) Integer customerId) {
		return cartOrderService.getAllCartOrders(customerId);
	}

	/**
	 * POST Cart Order
	 */
	@PostMapping(value = "")
	@ApiOperation(value = "", notes = "Creates new cart order.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = CartOrderExport.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public CartOrderExport createCartOrder(
			@ApiParam(value = "pojo of cart order", required = true)
				@Valid @RequestBody CartOrderInfo pojo) {
		return cartOrderService.saveCartOrder(pojo);
	}

}
