package com.pwc.denali2.estimator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User Not Found")
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 4657491283614755649L;

	public BusinessException(String msg) {
		super(msg + "not valid");
	}
}
