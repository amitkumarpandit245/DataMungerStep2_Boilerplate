package com.stackroute.datamunger.query.parser;

/* This class is used for storing name of field, aggregate function for 
 * each aggregate function
 * generate getter and setter for this class,
 * Also override toString method
 * */

public class AggregateFunction {
	private String fieldName;
	private String aggregateFunction;
	// Write logic for constructor
	public AggregateFunction(String field, String function) {
		super();
		this.fieldName=field;
		this.aggregateFunction=function;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getAggregateFunction() {
		return aggregateFunction;
	}
	public void setAggregateFunction(String aggregateFunction) {
		this.aggregateFunction = aggregateFunction;
	}
	@Override
	public String toString() {
		return "AggregateFunction [fieldName=" + fieldName + ", aggregateFunction=" + aggregateFunction + "]";
	}
	

}