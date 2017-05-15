package zp.base.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.$Gson$Types;

import zp.base.utils.JsonCheck.JSON_TYPE;
/**
 * 如果json key是java关键字的话要加入  @SerializedName("xxx")
 *  
 *  e.g
 *  @SerializedName("new")
    private boolean new;
 * One step at a time,there's no need to rush.
 * @author zhang
 * @date 2015-6-9
 */
public class JsonUtils {
	private static Gson gson;
	static {
		gson = new GsonBuilder().create();
	}

	/**
	 * 把对象转换成json字符串
	 * 
	 * @param t
	 * @return
	 */
	public static <T> String getJson(T t) {
		return gson.toJson(t);
	}
	/**
	 * 把对象集合转换成json字符串
	 * 
	 * @param t
	 * @return
	 */
	public static <T> String getJson(List<T> ts) {
		return gson.toJson(ts);
	}

	/**
	 * 根据key转换json中包含的实体对象
	 * 
	 * @param t
	 *            实体对象类型
	 * @param json
	 * @param key
	 * @return
	 */
	public static <T> T getEntity(Class<T> tOfClass, String json) {
		return getEntity(tOfClass, json, null);
	}
	/**
	 * 根据key转换json中包含的实体对象
	 * 
	 * @param t
	 *            实体对象类型
	 * @param json
	 * @param key
	 * @return
	 */
	public static <T> T getEntity(Class<T> tOfClass, String json, String key) {
		
		if(JsonCheck.getJSONType(json)==JSON_TYPE.JSON_TYPE_ERROR)
		{
			try
			{
				return tOfClass.newInstance();
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		Object subJsonObj = getJsonFromKey(json, key);
		if (subJsonObj != null) {
			return gson.fromJson(subJsonObj.toString(), tOfClass);
		}
		return gson.fromJson(json, tOfClass);
	}
	/**
	 * 根据key转换json中包含的实体对象
	 * 
	 * @param t
	 *            实体对象类型
	 * @param json
	 * @return
	 */
	public static <T> List<T> getEntities(Class<T> clazzOfT, String json) {
		return getEntities(clazzOfT, json, null);
	}
	/**
	 * 根据key转换json中包含的实体对象
	 * 
	 * @param t
	 *            实体对象类型
	 * @param json
	 * @param key
	 * @return
	 */
	public static <T> List<T> getEntities(Class<T> clazzOfT, String json,
			String key) {
		if(JsonCheck.getJSONType(json)==JSON_TYPE.JSON_TYPE_ERROR)
		{
			return new ArrayList<T>();
		}
		
		Object subJsonObj = getJsonFromKey(json, key);
		/**
		 * Type type=new TypeToke<ArrayList<T>>(){}.getType();
		 * It's doesn't work;
		 *	下面两种方式可以实现动态加载
		 */
		// 1自定义实现
		// Type type = new ListParameterizedType(clazzOfT);
		
		//2 Gson 提供类
		Type type = $Gson$Types.newParameterizedTypeWithOwner(null,
				ArrayList.class, clazzOfT);
		if (subJsonObj != null) {
			return gson.fromJson(subJsonObj.toString(), type);
		}
		return gson.fromJson(json, type);
	}

	// 标识是否根据key找到对应的值
	private static boolean isFound = false;

	/**
	 * 根据key取出json中的org.json.JSONArray或者org.json.JSONObject对象
	 * 
	 * @param json
	 *            json类型的数据
	 * @param key
	 * @return
	 */
	public static Object getJsonFromKey(String json, String key) {
		if (StringUtils.isEmpty(key)) {
			return json;
		}
		if(JsonCheck.getJSONType(json)==JSON_TYPE.JSON_TYPE_ERROR)
		{
			return json;
		}
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser.parse(json);
		return getJsonElement(jsonElement, key);
	}
	/**
	 * 根据key查到json元素下面的子元素
	 * @param jsonElement
	 * @param key
	 * @return
	 */
	private static JsonElement getJsonElement(JsonElement jsonElement,
			String key) {
		if(jsonElement==null)
			return null;
		// 如果元素是一个jsonObject对象
		if (jsonElement.isJsonObject()) {
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			// 如果是子节点元素就可以直接获取
			if (jsonObject.has(key)) {
				isFound=true;
				return jsonObject.get(key);
			}
			// 如果是子节点的子点元素就要遍历取值
			Iterator<Entry<String, JsonElement>> iterator = jsonObject
					.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, JsonElement> elementEntry = iterator.next();
				String keyTemp = elementEntry.getKey();
				JsonElement element=elementEntry.getValue();
				if (keyTemp.equals(key)) {
					isFound=true;
					return element;
				}else {
					JsonElement foundTemp=getJsonElement(element, key);
					if(isFound)
					{
						return foundTemp;
					}
				}
			}
			// 如果元素是一个JsonArray对象
		} else if (jsonElement.isJsonArray()) {
			JsonArray jsonArray = jsonElement.getAsJsonArray();
			for (int i = 0; i < jsonArray.size(); i++) {
				JsonElement element = jsonArray.get(i);
				JsonElement foundTemp=getJsonElement(element, key);
				if(isFound)
				{
					return foundTemp;
				}
			}
		}
		return jsonElement;
	}

	// 得到参数类型
	private static class ListParameterizedType implements ParameterizedType {

		private Type type;

		private ListParameterizedType(Type type) {
			this.type = type;
		}

		@Override
		public Type[] getActualTypeArguments() {
			return new Type[] { type };
		}

		@Override
		public Type getRawType() {
			return ArrayList.class;
		}

		@Override
		public Type getOwnerType() {
			return null;
		}

	}
}
