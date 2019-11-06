package com.stackroute.datamunger.query.parser;

import java.util.List;

/* 
 * This class will contain the elements of the parsed Query String such as conditions,
 * logical operators,aggregate functions, file name, fields group by fields, order by
 * fields, Query Type
 * */

public class QueryParameter {
	private String fileName;
	private String baseQuery;
	private List<Restriction> restriction;
	private List<String> logicalOperations;
	private List<String> fields;
	private List<AggregateFunction> aggregateFunctions;
	private List<String> groupByfields;
	private List<String> orderByFields;
	
	
	public QueryParameter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QueryParameter(String fileName, String baseQuery, List<Restriction> restriction,
			List<String> logicalOperations, List<String> fields, List<AggregateFunction> aggregateFunctions,
			List<String> groupByfields, List<String> orderByFields) {
		super();
		this.fileName = fileName;
		this.baseQuery = baseQuery;
		this.restriction = restriction;
		this.logicalOperations = logicalOperations;
		this.fields = fields;
		this.aggregateFunctions = aggregateFunctions;
		this.groupByfields = groupByfields;
		this.orderByFields = orderByFields;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setBaseQuery(String baseQuery) {
		this.baseQuery = baseQuery;
	}

	public void setRestriction(List<Restriction> restriction) {
		this.restriction = restriction;
	}

	public void setLogicalOperations(List<String> logicalOperations) {
		this.logicalOperations = logicalOperations;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	public void setAggregateFunctions(List<AggregateFunction> aggregateFunctions) {
		this.aggregateFunctions = aggregateFunctions;
	}

	public void setGroupByfields(List<String> groupByfields) {
		this.groupByfields = groupByfields;
	}

	public void setOrderByFields(List<String> orderByFields) {
		this.orderByFields = orderByFields;
	}

	public String getFileName() {
		return this.fileName;
	}

	public String getBaseQuery() {
		return this.baseQuery;
	}

	public List<Restriction> getRestrictions() {
		return this.restriction;
	}

	public List<String> getLogicalOperators() {
		return this.logicalOperations;
	}

	public List<String> getFields() {
		return this.fields;
	}

	public List<AggregateFunction> getAggregateFunctions() {
		return this.aggregateFunctions;
	}

	public List<String> getGroupByFields() {
		return this.groupByfields;
	}

	public List<String> getOrderByFields() {
		return this.orderByFields;
	}
}