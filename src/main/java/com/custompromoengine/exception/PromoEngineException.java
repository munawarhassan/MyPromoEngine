package com.custompromoengine.exception;

/**
 * @author SKHassan
 *
 */
public class PromoEngineException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	String reason;

	public PromoEngineException(String reason) {
		super();
		this.reason = reason;
	}

}
