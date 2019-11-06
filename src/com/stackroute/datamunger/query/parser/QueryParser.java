package com.stackroute.datamunger.query.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/*There are total 4 DataMungerTest file:
 * 
 * 1)DataMungerTestTask1.java file is for testing following 4 methods
 * a)getBaseQuery()  b)getFileName()  c)getOrderByClause()  d)getGroupByFields()
 * 
 * Once you implement the above 4 methods,run DataMungerTestTask1.java
 * 
 * 2)DataMungerTestTask2.java file is for testing following 2 methods
 * a)getFields() b) getAggregateFunctions()
 * 
 * Once you implement the above 2 methods,run DataMungerTestTask2.java
 * 
 * 3)DataMungerTestTask3.java file is for testing following 2 methods
 * a)getRestrictions()  b)getLogicalOperators()
 * 
 * Once you implement the above 2 methods,run DataMungerTestTask3.java
 * 
 * Once you implement all the methods run DataMungerTest.java.This test case consist of all
 * the test cases together.
 */

public class QueryParser {

	private QueryParameter queryParameter = new QueryParameter();

	/*
	 * This method will parse the queryString and will return the object of
	 * QueryParameter class
	 */
	public QueryParameter parseQuery(String queryString) {
		queryParameter.setBaseQuery(getBaseQuery(queryString));
		queryParameter.setFileName(getFileName( queryString));
		queryParameter.setAggregateFunctions(getAggregateFunctions(queryString));
		queryParameter.setRestriction(getConditions(queryString));
		queryParameter.setFields(getFields(queryString));
		queryParameter.setGroupByfields(getGroupByFields(queryString));
		queryParameter.setLogicalOperations(getLogicalOperators(queryString));
		queryParameter.setOrderByFields(getOrderByFields(queryString));
		return queryParameter;
	}

	/*
	 * Extract the name of the file from the query. File name can be found after the
	 * "from" clause.
	 */
	public String getFileName(String queryString) {
		String s1 = queryString.toLowerCase();
		String[] s2= s1.split(" from ");
		String[] result= s2[1].split(" ");
		return result[0].trim();
	}

	/*
	 * 
	 * Extract the baseQuery from the query.This method is used to extract the
	 * baseQuery from the query string. BaseQuery contains from the beginning of the
	 * query till the where clause
	 */
	public String getBaseQuery(String queryString) {
		queryString = queryString.toLowerCase();
		String[] s1;
		String result="";
		if(queryString.contains("where"))
		{
			s1 = queryString.split(" where ");
		    result = s1[0];  
		}
	    else if(queryString.contains("group by")){
		s1= queryString.split(" group by ");
		result = s1[0];
		
		}
		
	    else if(queryString.contains("order by")) {
		
		s1= queryString.split(" order by ");
		result = s1[0];
	}
		
	  else {
		
		result = queryString;
	   }
		return result.trim();
	  }
	/*
	 * extract the order by fields from the query string. Please note that we will
	 * need to extract the field(s) after "order by" clause in the query, if at all
	 * the order by clause exists. For eg: select city,winner,team1,team2 from
	 * data/ipl.csv order by city from the query mentioned above, we need to extract
	 * "city". Please note that we can have more than one order by fields.
	 */

	public List<String> getOrderByFields(String queryString) {
        queryString = queryString.toLowerCase();
        List<String> orderByFields = new ArrayList<String>();
        int index = queryString.indexOf("order by");
        if(index == -1) {
        	return null;
        }
        
        String s1 = queryString.substring(index+9);
        String[] orderByQuery = s1.toString().split(",");
        if(orderByQuery.length == 1) {
        	orderByFields.add(orderByQuery[0]);
        }
        else {
        	for(int i=0; i<orderByQuery.length;i++) {
        		orderByFields.add(orderByQuery[i]);
        	}
        }
      //  List<String> orderByFields = Arrays.asList(s1.toString().trim().split(","));
       
		return orderByFields;
	}
	/*
	 * Extract the group by fields from the query string. Please note that we will
	 * need to extract the field(s) after "group by" clause in the query, if at all
	 * the group by clause exists. For eg: select city,max(win_by_runs) from
	 * data/ipl.csv group by city from the query mentioned above, we need to extract
	 * "city". Please note that we can have more than one group by fields.
	 */
	public List<String> getGroupByFields(String queryString) {

		queryString = queryString.toLowerCase();
		String[] temp;
		String groupByQuery = ""; 
		        
		        int index = queryString.indexOf("group by");
		        if(index == -1) {
		        	return null;
		        }
		        
		        String s1 = queryString.substring(index+9);
		        if(s1.contains(" order by ")) {
		        	temp = s1.split(" order by ");
		        	groupByQuery = temp[0];
		        }
		        else {
		        	groupByQuery = s1;
		        }
		        
		        List<String> groupByFields = Arrays.asList(groupByQuery.toString().split(","));
		       
				return groupByFields;
			}
	/*
	 * Extract the selected fields from the query string. Please note that we will
	 * need to extract the field(s) after "select" clause followed by a space from
	 * the query string. For eg: select city,win_by_runs from data/ipl.csv from the
	 * query mentioned above, we need to extract "city" and "win_by_runs". Please
	 * note that we might have a field containing name "from_date" or "from_hrs".
	 * Hence, consider this while parsing.
	 */
	public List<String> getFields(String queryString) {
        queryString = queryString.toLowerCase();
        String[] s1 = queryString.split(" from ");
        s1 = s1[0].split(" ");
        List<String> fileds = Arrays.asList(s1[1].split(","));
       
		return fileds;
	}
	/*
	 * Extract the conditions from the query string(if exists). for each condition,
	 * we need to capture the following: 1. Name of field 2. condition 3. value
	 * 
	 * For eg: select city,winner,team1,team2,player_of_match from data/ipl.csv
	 * where season >= 2008 or toss_decision != bat
	 * 
	 * here, for the first condition, "season>=2008" we need to capture: 1. Name of
	 * field: season 2. condition: >= 3. value: 2008
	 * 
	 * the query might contain multiple conditions separated by OR/AND operators.
	 * Please consider this while parsing the conditions.
	 * 
	 */
	public List<Restriction> getConditions(String queryString) {
		List<Restriction> conditions = null;
		
		String[] whereQuery;
		
		String tempString;
		String[] conditionQuery;
		String[] getCondition = null;
		if (queryString.contains("where")) {
			conditions = new ArrayList<Restriction>();
			whereQuery = queryString.trim().split("where ");
			if (whereQuery[1].contains("group by")) {
				conditionQuery = whereQuery[1].trim().split("group by");
				tempString = conditionQuery[0];
			} else if (whereQuery[1].contains("order by")) {
				conditionQuery = whereQuery[1].trim().split("order by");
				tempString = conditionQuery[0];
			} else {
				tempString = whereQuery[1];
			}
			getCondition = tempString.trim().split(" and | or ");
			
			String[] condSplit = null;
			if (getCondition != null) {
				for (int i = 0; i < getCondition.length; i++) {
					if (getCondition[i].contains("=")) {
						condSplit = getCondition[i].trim().split("\\W+");
						conditions.add(new Restriction(condSplit[0].trim(), condSplit[1].trim(), "="));
					} else if (getCondition[i].contains(">")) {
						condSplit = getCondition[i].trim().split("\\W+");
						conditions.add(new Restriction(condSplit[0].trim(), condSplit[1].trim(), ">"));
					} else if (getCondition[i].contains("<")) {
						condSplit = getCondition[i].trim().split("\\W+");
						conditions.add(new Restriction(condSplit[0].trim(), condSplit[1].trim(), "<"));
					}

				}
			}

		}
		return conditions;
	}
	/*
	 * Extract the logical operators(AND/OR) from the query, if at all it is
	 * present. For eg: select city,winner,team1,team2,player_of_match from
	 * data/ipl.csv where season >= 2008 or toss_decision != bat and city =
	 * bangalore
	 * 
	 * The query mentioned above in the example should return a List of Strings
	 * containing [or,and]
	 */
public List<String> getLogicalOperators(String queryString) {

		
		queryString = queryString.toLowerCase(); 
		
		String[] temp = queryString.split(" ");
		
		String getString = "";
		List<String> logicalOperators = new ArrayList<>();
		
		if(queryString.contains(" where ")) {
			
			
			for(int i=0; i<temp.length;i++) {
				
				if(temp[i].matches("and|or|not")){
					
					getString+= temp[i] + " ";
				}
			}
			String[] temp1 = getString.toString().split(" ");
			if(temp1.length == 1) {
				logicalOperators.add(temp1[0]);
			}
			else {
				for(int i=0;i<temp1.length;i++) {
					logicalOperators.add(temp1[i]);
				}
			}
			//logicalOperators = Arrays.asList(getString.toString().split(" "));
			return logicalOperators;
			
		}

		
		for(String s1 : getString.toString().split(" ")) {
			System.out.println(s1);
		}
		return null;
	}
	/*
	 * Extract the aggregate functions from the query. The presence of the aggregate
	 * functions can determined if we have either "min" or "max" or "sum" or "count"
	 * or "avg" followed by opening braces"(" after "select" clause in the query
	 * string. in case it is present, then we will have to extract the same. For
	 * each aggregate functions, we need to know the following: 1. type of aggregate
	 * function(min/max/count/sum/avg) 2. field on which the aggregate function is
	 * being applied.
	 * 
	 * Please note that more than one aggregate function can be present in a query.
	 * 
	 * 
	 */

public List<AggregateFunction> getAggregateFunctions(String queryString) {
	final List<AggregateFunction> aggregate = new ArrayList<AggregateFunction>();
	
	final int selectIndex = queryString.toLowerCase(Locale.US).indexOf("select");
	final int fromIndex = queryString.toLowerCase(Locale.US).indexOf(" from");
	final String query = queryString.toLowerCase(Locale.US).substring(selectIndex + 7, fromIndex);
	String[] aggQuery = null;
	aggQuery = query.split(",");
	for (int i = 0; i < aggQuery.length; i++) {
		if (aggQuery[i].startsWith("max(") && aggQuery[i].endsWith(")")
				|| aggQuery[i].startsWith("min(") && aggQuery[i].endsWith(")")
				|| aggQuery[i].startsWith("avg(") && aggQuery[i].endsWith(")")
				|| aggQuery[i].startsWith("sum") && aggQuery[i].endsWith(")")) {
			aggregate.add(new AggregateFunction(aggQuery[i].substring(4, aggQuery[i].length() - 1),
					aggQuery[i].substring(0, 3)));
			
		} else if (aggQuery[i].startsWith("count(") && aggQuery[i].endsWith(")")) {
			aggregate.add(new AggregateFunction(aggQuery[i].substring(6, aggQuery[i].length() - 1),
					aggQuery[i].substring(0, 5)));
			
		}

	}
	return aggregate;

}
}