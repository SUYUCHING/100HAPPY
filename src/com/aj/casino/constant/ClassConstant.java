package com.aj.casino.constant;

import java.util.HashMap;
import java.util.Map;

public class ClassConstant {

	public final static Integer CLASS_CN_A=1;
	public final static Integer CLASS_CN_B=2;
	public final static Integer CLASS_CN_C=3;
	public final static Integer CLASS_CN_D=4;
	public final static Integer CLASS_CN_E=5;
	
	public final static Integer CLASS_SQUAT_A=20;
	public final static Integer CLASS_SQUAT_B=21;
	public final static Integer CLASS_SQUAT_C=22;
	public final static Integer CLASS_SQUAT_D=23;
	public final static Integer CLASS_SQUAT_E=24;
	
	public final static Map<Integer,String> CLASS_MAP=new HashMap<Integer,String>();
	
	static {
		CLASS_MAP.put(CLASS_CN_A, "中華百家A");
		CLASS_MAP.put(CLASS_CN_B, "中華百家B");
		CLASS_MAP.put(CLASS_CN_C, "中華百家C");
		CLASS_MAP.put(CLASS_CN_D, "中華百家D");
		CLASS_MAP.put(CLASS_CN_E, "中華百家E");

		CLASS_MAP.put(CLASS_SQUAT_A, "瞇牌百家A");
		CLASS_MAP.put(CLASS_SQUAT_B, "瞇牌百家B");
		CLASS_MAP.put(CLASS_SQUAT_C, "瞇牌百家C");
		CLASS_MAP.put(CLASS_SQUAT_D, "瞇牌百家D");
		CLASS_MAP.put(CLASS_SQUAT_E, "瞇牌百家E");
	}
}
