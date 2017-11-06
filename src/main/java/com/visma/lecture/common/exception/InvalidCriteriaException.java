package com.visma.lecture.common.exception;

/**
 * Dedicated exception
 *
 * @author Leo-Andreas Ervik
 */
public class InvalidCriteriaException extends RuntimeException {

	public InvalidCriteriaException(String message) {
		super(message);
	}

	public InvalidCriteriaException(String message, Throwable cause) {
		super(message, cause);
	}
}
