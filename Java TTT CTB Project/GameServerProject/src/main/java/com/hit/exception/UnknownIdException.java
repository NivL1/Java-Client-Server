package com.hit.exception;

import java.io.Serializable;

public class UnknownIdException extends Exception implements Serializable {


	public UnknownIdException(String message,Throwable err) {
		super(message, err);
	}
	
	public UnknownIdException(Throwable err) {
		super(err);
	} 
	
}
