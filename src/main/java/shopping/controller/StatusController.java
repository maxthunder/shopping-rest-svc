package shopping.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shopping.model.Customer;
import shopping.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping({"/shopping/status"})
@CrossOrigin("http://localhost:4200")
public class StatusController extends ControllerBase {

	/**
	 * GET status of service
	 */

	@GetMapping(value = "")
	@ApiOperation(value = "", notes = "Gets status.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})

	public ResponseEntity<?> getStatus() {
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

}
