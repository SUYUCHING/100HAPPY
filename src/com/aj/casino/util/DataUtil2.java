package com.aj.casino.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import com.aj.casino.constant.FormatConstant;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class DataUtil2 {
	/*Start Tom */
	public static boolean checkDateFormat(String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FormatConstant.DATA_FORMAT);
		boolean format;
		try {
			simpleDateFormat.parse(date);
			format = true;
		} catch (ParseException e) {
			format = false;
		}
		return format;
	}
	
	public static boolean checkDate(String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FormatConstant.DATA_FORMAT);
		simpleDateFormat.setLenient(false);
		boolean format;
		try {
			simpleDateFormat.parse(date);
			format = true;
		} catch (ParseException e) {
			format = false;
		}
		return format;
	}

	public static Date dataFormat(String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FormatConstant.DATA_FORMAT);
		Date dateFormat = null;
		try {
			dateFormat = simpleDateFormat.parse(date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		return dateFormat;
	}
	public static String fileDataFormat(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FormatConstant.DATE_MILLISECOND);
		String dateFormat = simpleDateFormat.format(date);
		

		return dateFormat;
	}
	public static final String QUERY_SELECT="select";
	public static final String QUERY_FROM="from";
	public static final String QUERY_GROUP="group";
	public static final String QUERY_ORDER="order";
	
	public static StringBuffer updateQuery(String replace, StringBuffer strBuff) {
		Integer indexSelect=strBuff.indexOf(QUERY_SELECT);
		Integer indexSelect2=strBuff.indexOf(QUERY_SELECT,indexSelect+QUERY_SELECT.length());
		if (indexSelect2 == -1)
			strBuff.replace(0, strBuff.indexOf(QUERY_FROM), replace);
		if (indexSelect2.compareTo(indexSelect) > 0)
			strBuff.replace(0, indexSelect2, replace);
		if (strBuff.indexOf(QUERY_ORDER) != -1)
			strBuff.delete(strBuff.indexOf(QUERY_ORDER), strBuff.length());
		if (strBuff.indexOf(QUERY_GROUP) != -1)
			strBuff.delete(strBuff.indexOf(QUERY_GROUP), strBuff.length());
		
		return strBuff;
	}
	
    
 
	public static Method getMethodByClass(Class cls,String name) throws NoSuchMethodException, SecurityException {
		return cls.getMethod("get" + name);
	}
	
	public static List sortData(String sortType, Boolean isAscending, List list) {
		// small to large =true
		// Large to small = false
		try {
			if (sortType != null && sortType.length() > 0) {
				String name = sortType.replaceFirst(sortType.substring(0, 1), sortType.substring(0, 1).toUpperCase());
				Method method = getMethodByClass(list.get(0).getClass(),name);
				Object invoke =method.invoke(list.get(0));
				for (int j = 0; j < list.size() - 1; j++) {
					for (int i = 0; i < list.size() - 1 - j; i++) {
						if (invoke instanceof Long) {
							Long value = (Long) method.invoke(list.get(i));
							Long value2 = (Long) method.invoke(list.get(i + 1));
							if (isAscending) {
								if (value.compareTo(value2) > 0)
									Collections.swap(list, i, i + 1);
							} else {
								if (value.compareTo(value2) < 0)
									Collections.swap(list, i, i + 1);
							}
						}
						if (invoke instanceof Date) {
							Date value = (Date) method.invoke(list.get(i));
							Date value2 = (Date) method.invoke(list.get(i + 1));
							if (isAscending) {
								if (value.compareTo(value2) > 0)
									Collections.swap(list, i, i + 1);
							} else {
								if (value.compareTo(value2) < 0)
									Collections.swap(list, i, i + 1);
							}
						}
						if (invoke instanceof String) {
							String value = (String) method.invoke(list.get(i));
							String value2 = (String) method.invoke(list.get(i + 1));
							if (isAscending) {
								if (value.compareTo(value2) > 0)
									Collections.swap(list, i, i + 1);
							} else {
								if (value.compareTo(value2) < 0)
									Collections.swap(list, i, i + 1);
							}
						}
						if (invoke instanceof BigDecimal) {
							BigDecimal value = (BigDecimal) method.invoke(list.get(i));
							BigDecimal value2 = (BigDecimal) method.invoke(list.get(i + 1));
							if (isAscending) {
								if (value.compareTo(value2) > 0)
									Collections.swap(list, i, i + 1);
							} else {
								if (value.compareTo(value2) < 0)
									Collections.swap(list, i, i + 1);
							}
						}
						if (invoke instanceof Integer) {
							Integer value = (Integer) method.invoke(list.get(i));
							Integer value2 = (Integer) method.invoke(list.get(i + 1));
							if (isAscending) {
								if (value.compareTo(value2) > 0)
									Collections.swap(list, i, i + 1);
							} else {
								if (value.compareTo(value2) < 0)
									Collections.swap(list, i, i + 1);
							}
						}
					}
				}
			}
			return list;
		  } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  return null;
	}
	
	public static String stringDataFormat(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FormatConstant.DATE_DISPLAY);
		String	dateFormat = simpleDateFormat.format(date);
		return dateFormat;
	}

	public static Date dataFormat(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FormatConstant.DATA_FORMAT);
		String dateStr = simpleDateFormat.format(date);
		Date dates = null;
		try {
			dates = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dates;
	}

	public static String amountAdd4Zero(BigDecimal amount) {
		   BigDecimal b=amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		  return new DecimalFormat("#,##0.00").format(b);
//		return amount.setScale(4).toString();
		
	}
	public static JsonArray listObjectArrayToJsonArray(List<Object[]> list, String... names) {
		Iterator<Object[]> it = list.iterator();
		JsonArray jsonArray = new JsonArray();
		while (it.hasNext()) {
			Object[] obj = it.next();
			JsonObject jsonObject = new JsonObject();
			for (int i = 0; i < obj.length; i++) {
				if (obj[i] instanceof BigDecimal) {
					BigDecimal value = (BigDecimal) obj[i];
					jsonObject.addProperty(names[i], value);
				} else if (obj[i] instanceof Integer) {
					Integer value = (Integer) obj[i];
					jsonObject.addProperty(names[i], value);
				}else if(obj[i] instanceof Date){
				 	Date date=(Date)obj[i];
				 	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FormatConstant.DATA_FORMAT);
					String value = simpleDateFormat.format(date);
					jsonObject.addProperty(names[i], value);
				}else {
					jsonObject.addProperty(names[i], obj[i].toString());
				}

			}
			jsonArray.add(jsonObject);
		}

		return jsonArray;
	}
	
	public static boolean checkNumeral(String number) {
		boolean check=true;
		try {
			 new BigDecimal(number);
		} catch (NumberFormatException e) {
			check=false;
		}
		return check;
		
	}
	
	public static boolean checkInteger(String number) {
		boolean check=true;
		try {
			 new Integer(number);
		} catch (NumberFormatException e) {
			check=false;
		}
		return check;
		
	}
	public static Integer recordToPageNo(Integer start,Integer maxResult) {
			
			Integer pageNo=start/maxResult;
		    
		    return pageNo+1;
		}
	public static Integer recordsToTotalPage(Integer totalRecords,Integer maxResult) {
		
		Integer totalPage=totalRecords/maxResult;
		totalPage = ((totalRecords%maxResult)==0)? totalPage : totalPage+1;
	    
	    return totalPage;
	}
	
	public static Date lastDate(Date date) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(new Date());
		instance.add(Calendar.YEAR, -1);
//		Calendar instance = Calendar.getInstance();
//		instance.set(Calendar.HOUR,-12);
//		instance.set(Calendar.MINUTE,0);
//		instance.set(Calendar.SECOND,0);
//		instance.set(Calendar.MILLISECOND,0);
		return instance.getTime();
	}
	
	public static void print(List<String[]> list) {
		
		list.forEach(val->{
			for(String str:val) {
				System.out.print(str+",");
			}
			System.out.println();
		});
	}
	
	/* Tom End */
	
	
}
