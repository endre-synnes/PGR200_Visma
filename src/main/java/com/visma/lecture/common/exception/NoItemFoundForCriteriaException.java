package com.visma.lecture.common.exception;

/**
 * Dedicated exception
 *
 * @author Leo-Andreas Ervik
 */
public class NoItemFoundForCriteriaException extends RuntimeException {

	public NoItemFoundForCriteriaException(String message) {
		super(message);
	}

	public NoItemFoundForCriteriaException(String message, Throwable cause) {
		super(message, cause);
	}
}
