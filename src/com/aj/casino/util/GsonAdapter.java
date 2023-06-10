package com.aj.casino.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.asm.ASMException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/* start Tom */
public class GsonAdapter<T> extends TypeAdapter<T> {

	private Class cls;
	
	public GsonAdapter() {
		super();
	}

	public GsonAdapter(Class cls) {
		super();
		this.cls = cls;
	}
	
	
	@Override
	public void write(JsonWriter arg0, T arg1) throws IOException {
		try {
			arg0.beginObject();
			Class<? extends Object> class1 = arg1.getClass();
			Field[] names = class1.getDeclaredFields();
			for (Field Fields : names) {
				String nameStr = Fields.getName();
				String name = nameStr.replaceFirst(nameStr.substring(0, 1), nameStr.substring(0, 1).toUpperCase());
				Method method = class1.getMethod("get" + name);
				Object invoke = method.invoke(arg1);

				if (invoke instanceof String) {
					String value = (String) invoke;
					arg0.name(nameStr).value(value);

				}
				if (invoke instanceof Integer) {
					//other Integer
					Integer value = (Integer) invoke;
					if (nameStr.equals("totalRecords") || nameStr.equals("totalPage") || nameStr.equals("pageNo")) {
						arg0.name(nameStr).value(value);
						
                    //check have status cls
					} else if (this.cls != null) {
						Map<Integer, String> statusMap = getStatusMap(name,class1.getSimpleName());
						// check have status Map
						if(statusMap!=null) {
							if (statusMap.containsKey(invoke))
								arg0.name(nameStr).value(statusMap.get(invoke));
							else  
								arg0.name(nameStr).value(value);
					    }else {
					    	arg0.name(nameStr).value(value);
						}
					} else {
						arg0.name(nameStr).value(value);

					}
				}
				if (invoke instanceof BigDecimal) {
					BigDecimal value = (BigDecimal) invoke;
					arg0.name(nameStr).value(DataUtil2.amountAdd4Zero(value));
				}
				if (invoke instanceof Date) {
					Date value = (Date) invoke;
					arg0.name(nameStr).value(DataUtil2.stringDataFormat(value));
				}
				if (invoke instanceof Long) {
					Long value = (Long) invoke;
					arg0.name(nameStr).value(value);
				}

				if (invoke instanceof List) {
					arg0.name(nameStr).beginArray();
					List<T> value = (List<T>) invoke;
					for (int i = 0; i < value.size(); i++) {
						write(arg0, value.get(i));
					}
					arg0.endArray();
				}
			}
			arg0.endObject();

		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void write(JsonWriter arg0, List<T> arg1) throws IOException {
			arg0.beginArray();
			List<T> value = (List<T>) arg1;
			for (int i = 0; i < value.size(); i++) {
				write(arg0, value.get(i));
			}
			arg0.endArray();

	}
	@Override
	public T read(JsonReader arg0) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	private Map<Integer,String> getStatusMap(String name,String clsName) {
		//funcName = clsName+fieldName 
		try {
	    	   Method method = this.cls.getMethod("get"+clsName+name+"Map");
               Object instance = this.cls.newInstance();
               Object invoke = method.invoke(instance);
               if(invoke instanceof Map) {
            	   return (Map<Integer,String>)invoke;
               }
		return null;
		} catch (NoSuchMethodException | ASMException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (InstantiationException | IllegalAccessException |IllegalArgumentException |InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
	}
	
}
/* end Tom */

