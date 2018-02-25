package shopping.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
//
//	public ResourceNotFoundException(String resource, String identifier) {
//		this(resource, (Object) identifier);
//	}
//
//	public ResourceNotFoundException(String resource, Integer identifier) {
//		this(resource, (Object) identifier);
//	}

	public ResourceNotFoundException(String resource, Object identifier) {
		super(resource + " <"+identifier.toString()+"> not found in database.");
	}
}
