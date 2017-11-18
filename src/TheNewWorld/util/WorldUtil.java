package TheNewWorld.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

import TheNewWorld.MainApp;

public class WorldUtil {

	static {try {
		nameMap = new HashMap<String,String>();
		cnameMap = new HashMap<String,String>();
		world = new HashMap<String,String>();
		worldObject = new JSONObject();
		initWorld();
	} catch (Exception e) {
		e.printStackTrace();
	}}
	public static HashMap<String,String> world;
	public static HashMap<String,String> nameMap;
	public static HashMap<String,String> cnameMap;
	public static JSONObject worldObject;
	
	public static void initWorld() throws Exception {
		File f = new File(MainApp.userDir+"\\src\\world.properties");
		FileInputStream fis = new FileInputStream(f);
		InputStreamReader isr = new InputStreamReader(fis);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(isr);
		String ls = null;
		while((ls=br.readLine()) != null) {
			
			if("".equals(ls.trim())) {continue;}
			
			String[] lsa = ls.split("\\=");
			String lsaKey = lsa[0];
			String lsaValue = lsa[1];
			world.put(lsaKey, lsaValue);
			String[] lsaa = lsaKey.split("\\.");
			String mKey = lsaa[0];
			String sKey = lsaa[1];
			saveJosn(worldObject, mKey, sKey, lsaValue);
		}
		System.out.println(worldObject.toString());
	}
	
	public static void saveJosn(JSONObject jsonObject,String mKey,String sKey,String lsaValue) {
		if(mKey.contains("world")) {
			JSONArray jsonArray = new JSONArray();
			JSONArray jsonArray2 = new JSONArray();
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("CName", lsaValue);
			jsonObject2.put(sKey, jsonArray2);
			jsonArray.put(jsonObject2);
			jsonObject.put(mKey, jsonArray);
			jsonObject.put("CName", "新新世界");
		}else {
			getHelper(jsonObject, mKey, sKey,lsaValue);
		}
		
	}
	
	public static void getHelper(JSONObject jsonObject,String key,String value,String lsaValue) {
		
		if(jsonObject.has(key)) {

			Double i  = null;
			try {
				i  =Double.valueOf(lsaValue);
			} catch (Exception e) {	}
			if(i!=null) {
				Object object=jsonObject.get(key);
				if(object instanceof JSONObject) {
					((JSONObject)object).put(value, lsaValue);
				}
				else if(object instanceof JSONArray) {
					JSONObject jsonObject2 = new JSONObject();
					jsonObject2.put(value,lsaValue);
					jsonObject.put(key,jsonObject2);
				}
			}else {
				JSONObject jsonObject2 = new JSONObject();
				jsonObject2.put("CName",lsaValue);
				jsonObject2.put(value,new JSONArray());
				jsonObject.getJSONArray(key).put(jsonObject2);
				nameMap.put(value, lsaValue);
				cnameMap.put(lsaValue,value);
			}
			
		}else {
			for (String iterable_element : jsonObject.keySet()) {
				Object object =jsonObject.get(iterable_element);
				if(object instanceof String) {
					if(((String)object).equals(key)) {
						JSONObject jsonObject2 = new JSONObject();
						jsonObject2.put(key, value);
						JSONArray jsonArray = new JSONArray();
						jsonArray.put(jsonObject2);
					}
				}else if(object instanceof JSONArray){
					for (int i = 0; i < ((JSONArray)object).length(); i++) {
						getHelper(((JSONArray) object).getJSONObject(i), key, value,lsaValue);
					}
				}else {
				}
			}
			
		}
	}
	
	public static void main(String[] args) {
		System.out.println(world);
		System.out.println(worldObject);
	}
}
