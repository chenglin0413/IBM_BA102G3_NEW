/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
*/

package com.prod.controller;

import java.util.*;

public class jdbcUtil_CompositeQuery_Prod {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("prod_id".equals(columnName) ||  "store_id".equals(columnName) || "prod_price".equals(columnName)|| "prod_soldcount".equals(columnName)|| "prod_status".equals(columnName)|| "prod_count".equals(columnName)|| "prod_score".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("prod_name".equals(columnName) || "prod_descript".equals(columnName) || "prod_sort".equals(columnName) || "prod_format".equals(columnName) || "prod_brand".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("prod_updatetime".equals(columnName))                          // 用於Oracle的date
			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("prod_id", new String[] { "2200001" });
		map.put("store_id", new String[] { "2000001" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的keyy

		String finalSQL = "select * from prod "
				          + jdbcUtil_CompositeQuery_Prod.get_WhereCondition(map)
				          + "order by prod_id";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
