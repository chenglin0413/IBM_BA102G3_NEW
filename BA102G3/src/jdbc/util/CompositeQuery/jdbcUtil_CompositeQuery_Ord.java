/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
*/

package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Ord {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("ord_id".equals(columnName) || "user_id".equals(columnName) || "store_id".equals(columnName) || "ord_total".equals(columnName)|| "ord_bill".equals(columnName)|| "ord_grant".equals(columnName)|| "ord_status".equals(columnName)|| "ord_sscore".equals(columnName)|| "ord_rpstatus".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("ord_rpcomm".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("ord_date".equals(columnName)||"ord_rpdate".equals(columnName))                          // 用於Oracle的date
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
		map.put("ord_id", new String[] { "2400001" });
		map.put("store_id", new String[] { "1000002" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的keyy

		String finalSQL = "select * from ord "
				          + jdbcUtil_CompositeQuery_Ord.get_WhereCondition(map)
				          + "order by ord_id";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
