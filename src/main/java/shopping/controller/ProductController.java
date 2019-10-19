package shopping.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shopping.model.Product;
import shopping.service.ProductService;

import java.util.List;

@RestController
@RequestMapping({"/shopping/products"})
@CrossOrigin("http://localhost:4200")
public class ProductController extends ControllerBase {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * GET all Products
     */

    @GetMapping(value = "")
    @ApiOperation(value = "", notes = "Gets all products.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Product.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
