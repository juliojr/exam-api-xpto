package com.xpto.cities.util;

import java.util.ArrayList;
import java.util.List;

public class Util {
	
	public static final <T>List<T> castCollection(List<?> srcList, Class<T> clas){
	    List<T> list =new ArrayList<T>();
	    for (Object obj : srcList) {
	    if(obj!=null && clas.isAssignableFrom(obj.getClass()))
	        list.add(clas.cast(obj));
	    }
	    return list;
	}

}
