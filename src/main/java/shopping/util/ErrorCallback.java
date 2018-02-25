package shopping.util;

import java.util.ArrayList;
import java.util.List;

public class ErrorCallback {

	public static class Error {

		public Error(String errorMessage, String statusCode) {
			this.errorMessage = errorMessage;
			this.statusCode = statusCode;
		}

		private String errorMessage;
		private String statusCode;

		public String getErrorMessage() {
			return errorMessage;
		}
		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public String getStatusCode() {
			return statusCode;
		}
		public void setStatusCode(String statusCode) {
			this.statusCode = statusCode;
		}
	}

	private final List<Error> errors = new ArrayList<>();

	public List<Error> add(Error error) {
		errors.add(error);
		return errors;
	}
}
