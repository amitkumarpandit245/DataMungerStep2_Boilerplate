package com.stackroute.datamunger.query.parser;

/*
 * This class is used for storing name of field, condition and value for 
 * each conditions
 * generate getter and setter for this class,
 * Also override toString method
 * */

public class Restriction {
	private String fieldName;
	private String conditionName;
	private String conditionValue;
	// Write logic for constructor
	public Restriction(String name, String value, String condition) {
		super();
		this.conditionName=name;
		this.conditionValue=value;
		this.fieldName=name;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getConditionName() {
		return conditionName;
	}
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	public String getConditionValue() {
		return conditionValue;
	}
	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}
	@Override
	public String toString() {
		return "Restriction [fieldName=" + fieldName + ", conditionName=" + conditionName + ", conditionValue="
				+ conditionValue + "]";
	}
	

}