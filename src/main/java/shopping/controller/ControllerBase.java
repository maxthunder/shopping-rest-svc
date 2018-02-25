package shopping.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import shopping.util.ErrorCallback;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class ControllerBase {

	@RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
	@ApiOperation(value = "Controller Options", notes = "Returns available controller options.", response = String.class)
	@ApiResponses({
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorCallback.class),
			@ApiResponse(code = 200, message = "Ok", response = ErrorCallback.class) })

	public ResponseEntity<String> getOptions(HttpServletRequest request) {
		HttpHeaders httpHeaders = new HttpHeaders();

		Set<HttpMethod> allows = new HashSet<>();
		allows.add(HttpMethod.GET);
		allows.add(HttpMethod.POST);
		allows.add(HttpMethod.PUT);
		allows.add(HttpMethod.PATCH);
		allows.add(HttpMethod.DELETE);
		allows.add(HttpMethod.OPTIONS);
		httpHeaders.setAllow(allows);

		List<MediaType> mediaTypes = new ArrayList<>();
		mediaTypes.add(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(mediaTypes);

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setContentLength(0);

		return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
	}

}
