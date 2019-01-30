package shopping.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopping.model.ShirtRef;
import shopping.model.ShirtRefBuilder;
import shopping.service.ShirtRefService;

import java.util.List;

@RestController
@RequestMapping({"/shopping/shirts"})
public class ShirtRefController extends ControllerBase {

	private final ShirtRefService shirtRefService;

	@Autowired
	public ShirtRefController(ShirtRefService shirtRefService) {
		this.shirtRefService = shirtRefService;
	}

	/**
	 * GET all ShirtRefs
	 */

	@GetMapping(value = "")
	@ApiOperation(value = "", notes = "Gets all shirtRefs.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = ShirtRef.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})

	public List<ShirtRef> getShirtRefs() {
		return shirtRefService.getAllShirtRefs();
	}

	/**
	 * GET ShirtRef by ID
	 */

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "/{id}", notes = "Gets shirtRef by shirtRef ID.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = ShirtRef.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public ShirtRef getShirtRef(
			@ApiParam(name = "id",value = "ID of shirtRef",required = true) @PathVariable("id") Integer id) {

		return shirtRefService.getShirtRefById(id);
	}

	/**
	 * POST ShirtRef
	 */

	@PostMapping(value = "")
	@ApiOperation(value = "", notes = "Creates new shirtRef.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = ShirtRef.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public ShirtRef createShirtRef(// TODO  replace String -> ShirtRef
			@ApiParam(name = "name",value = "Name of shirt",required = true) @RequestParam("name") String name,
			@ApiParam(name = "size",value = "Size of shirt",required = true) @RequestParam("size") String size,
			@ApiParam(name = "style",value = "Style of shirt",required = true) @RequestParam("style") String style) {

		return shirtRefService.saveShirtRef(new ShirtRef(name, size, style));
	}

	/**
	 * PUT ShirtRef
	 */

	@PutMapping(value = "/{id}")
	@ApiOperation(value = "/{id}", notes = "Creates/updates shirtRef at specified ID.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = ShirtRef.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public ShirtRef updateShirtRef(
			@ApiParam(name = "id",value = "ID of shirtRef",required = true) @PathVariable("id") Integer id,
			@ApiParam(name = "name",value = "Name of shirtRef",required = true) @RequestParam("name") String name,
			@ApiParam(name = "size",value = "Size of shirt",required = true) @RequestParam("size") String size,
			@ApiParam(name = "style",value = "Style of shirt",required = true) @RequestParam("style") String style) {

		ShirtRef shirtRef = new ShirtRefBuilder()
				.id(id)
				.shirtName(name)
				.size(size)
				.style(style)
				.build();
		return shirtRefService.updateShirtRef(shirtRef);
	}

	/**
	 * DELETE ShirtRef
	 */

	@DeleteMapping(value = "/{id}/{name}")
	@ApiOperation(value = "/{id}/{name}", notes = "Deletes shirtRef by ID and name (to protect against accidental deletions).")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = ShirtRef.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	public ShirtRef deleteShirtRef(
			@ApiParam(name = "id",value = "ID of shirtRef",required = true) @PathVariable("id") Integer id,
			@ApiParam(name = "name",value = "Name of shirtRef",required = true) @PathVariable("name") String name) {

		return shirtRefService.deleteShirtRef(id, name);
	}
}
