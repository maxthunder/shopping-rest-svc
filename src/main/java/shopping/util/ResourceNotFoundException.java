package shopping.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String resourceMsg, Object identifier) {
		super(resourceMsg + " <" + (identifier != null ? identifier.toString() : null) + "> not found in database.");
	}

	public  ResourceNotFoundException(String resource) {
		super(resource);
	}
}
