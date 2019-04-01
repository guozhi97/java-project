package com.ctgu.util.enums;


public enum StateEnum {
	INVALID("0"),	//否
	VALID("1");		//是
	
	private String value;
	private StateEnum(String value){
		this.value=value;
	}
	@Override
	public String toString(){
		return this.value;
	}
}
