package shopping.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import shopping.util.ErrorCallback;
import shopping.util.BadRequestException;

public abstract class ControllerBase {

//	@ExceptionHandler(BadRequestException.class)
//	@ResponseBody
//	public ResponseEntity<ErrorCallback> handleException(final BadRequestException re) {
//		ErrorCallback errorCallback = new ErrorCallback();
//
//		ErrorCallback.Error error = new ErrorCallback.Error(re.getMessage(), String.valueOf(re.getHttpStatus().value()));
//		errorCallback.add(error);
//
//		return new ResponseEntity<>(errorCallback, re.getHttpStatus());
//	}
//

}
