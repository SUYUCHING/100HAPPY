package com.aj.module.security;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SecureSession implements Serializable {

	
	protected Map<String,Object> parameter = new HashMap<String, Object>();

	public void setParameter(String id,Object object){
		parameter.put(id, object);
	}
	
	public Object getParameter(String id){
		return parameter.get(id);
	}	
	
}
