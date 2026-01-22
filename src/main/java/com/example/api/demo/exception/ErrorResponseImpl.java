/**
 * 
 */
package com.example.api.demo.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

/**
 * 
 */
public class ErrorResponseImpl implements ErrorResponse {
    private String code;
    private String message;

    public ErrorResponseImpl(String code, String message) {
        this.code = code;
        this.message = message;
    }


	@Override
	public HttpStatusCode getStatusCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProblemDetail getBody() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}


	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}


	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
