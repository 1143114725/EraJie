package com.erajie.rxutils;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author EraJieZhang
 * @data 2019/12/8
 */
public class GsonUtil {
	
	
	private static Gson gson = null;
	static {
		if (gson == null) {
			gson = new Gson();
		}
	}
	
	
	public GsonUtil() {
	}

	/**
	 * 获取属性
	 * @param json {“name”:"data",“name”:"data",“name”:"data"}
	 * @param attr 属性名
	 * @return	对应属性值
	 */
	public static String getattr(String json,String attr){
		JSONObject jsonObject = null;
		String unid = "";
		try {
			jsonObject = new JSONObject(json);
			unid = jsonObject.optString(attr);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return unid;
	}
	
	/**
	 * 将object对象转成json字符串
	 *
	 * @param object
	 * @return
	 */
	public static String GsonToString(Object object) {
		String gsonString = null;
		if (gson != null) {
			gsonString = gson.toJson(object);
		}
		return gsonString;
	}
	
	
	/**
	 * 将gsonString转成泛型bean
	 *
	 * @param gsonString
	 * @param cls
	 * @return
	 */
	public static <T> T GsonToBean(String gsonString, Class<T> cls) {
		T t = null;
		if (gson != null) {
			t = gson.fromJson(gsonString, cls);
		}
		return t;
	}
	
	
	/**
	 * 转成list
	 * 泛型在编译期类型被擦除导致报错
	 * @param gsonString
	 * @param cls
	 * @return
	 */
	public static <T> List<T> GsonToList(String gsonString, Class<T> cls) {
		List<T> list = null;
		if (gson != null) {
			list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
			}.getType());
		}
		return list;
	}
	
	
	/**
	 * 转成list
	 * 解决泛型问题
	 * @param json
	 * @param cls
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> JsonToList(String json, Class<T> cls) {
		Gson gson = new Gson();
		List<T> list = new ArrayList<T>();
		JsonArray array = new JsonParser().parse(json).getAsJsonArray();
		for(final JsonElement elem : array){
			list.add(gson.fromJson(elem, cls));
		}
		return list;
	}
	
	
	
	
	/**
	 * 转成list中有map的
	 *
	 * @param gsonString
	 * @return
	 */
	public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
		List<Map<String, T>> list = null;
		if (gson != null) {
			list = gson.fromJson(gsonString,
				new TypeToken<List<Map<String, T>>>() {
				}.getType());
		}
		return list;
	}
	
	
	/**
	 * 转成map的
	 *
	 * @param gsonString
	 * @return
	 */
	public static <T> Map<String, T> GsonToMaps(String gsonString) {
		Map<String, T> map = null;
		if (gson != null) {
			map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
			}.getType());
		}
		return map;
	}
	
	/**
	 * 把一个bean（或者其他的字符串什么的）转成json
	 * @param object
	 * @return
	 */
	public static String BeanToJson(Object object){
		return gson.toJson(object);
	}

}