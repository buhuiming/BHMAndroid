package com.bhm.sdk.bhmlibrary.crash;

final class QuitCockroachException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuitCockroachException(String message) {
        super(message);
    }
}