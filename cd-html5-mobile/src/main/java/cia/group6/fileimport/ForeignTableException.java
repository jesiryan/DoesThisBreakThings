package cia.group6.fileimport;

public class ForeignTableException extends Exception {

	public ForeignTableException() {
	}

	public ForeignTableException(String message) {
		super(message);
	}

	public ForeignTableException(Throwable cause) {
		super(cause);
	}

	public ForeignTableException(String message, Throwable cause) {
		super(message, cause);
	}

	public ForeignTableException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
