package com.mindtree.fudo.exceptions;

public class RecordAlreadyExistsException extends MyApplicationException {

	public RecordAlreadyExistsException() {
		super();
	}

	public RecordAlreadyExistsException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public RecordAlreadyExistsException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public RecordAlreadyExistsException(String arg0) {
		super(arg0);
	}

	public RecordAlreadyExistsException(Throwable arg0) {
		super(arg0);
	}

}
