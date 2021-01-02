package com.verwaltungsplatform.model;

import java.util.Random;

public class PasswordCode {
	
	private int code;
	
	public PasswordCode() {
		
		Random passwordCode = new Random();
		code = passwordCode.nextInt(9999);
		
		while (code < 1000) {
			code = passwordCode.nextInt(9999);
		}
	}
	
	public int getCode() {
		return code;
	}
	
	public boolean isCodeCorrect(int inCode) {
		
		if( inCode == code) return true;
		else return false;
		
	}

}
