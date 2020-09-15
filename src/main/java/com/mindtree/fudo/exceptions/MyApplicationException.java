package com.mindtree.fudo.exceptions;

public class MyApplicationException extends Exception {

	public MyApplicationException() {
		super();
	}

	public MyApplicationException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public MyApplicationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MyApplicationException(String arg0) {
		super(arg0);
	}

	public MyApplicationException(Throwable arg0) {
		super(arg0);
	}

}
