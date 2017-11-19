package TheNewWorld.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import TheNewWorld.MainApp;
import TheNewWorld.model.Role;

public class RoleUtil {
	
	private static String message ;
	private static HashMap<String,String> ids ;
	private static JSONObject vocationAttributes;
	
	public static File file ;
	public static HashMap<String, String> roleNamePathMap;
	public static HashMap<String, String> nameVocationMap;
	public static File[] rolesFile;

	static {
		init() ;
	}

	public static void init() {
		message = "" ;
		ids = new HashMap<String,String>();
		file = new File(MainApp.userDir + "\\database\\role") ;
		
		roleNamePathMap = new HashMap<String, String>();
		nameVocationMap = new HashMap<String, String>();

		rolesFile = file.listFiles();
		for (File file : rolesFile) {
			String name = file.getName().split("\\.")[0];
			roleNamePathMap.put(name, file.getAbsolutePath());
			FileInputStream fileInputStream =null;
			InputStreamReader inputStreamReader= null;
			BufferedReader bReader = null;
			try {
				fileInputStream = new FileInputStream(file);
				inputStreamReader = new InputStreamReader(fileInputStream);
				bReader=new BufferedReader(inputStreamReader);
				String string=null;
				while((string=bReader.readLine()) != null) {
					if(string.contains("vocation")) {
						String vocation=string.split("\\=")[1];
						nameVocationMap.put(name, vocation);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					bReader.close();
					inputStreamReader.close();
					fileInputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static boolean delteRole(String name) {
		init();
		String path = roleNamePathMap.get(name);
		File f = new File(path);
		return f.delete();
	}

	//生成角色
	public String generateRole(String name,String vocation,String gender) throws IOException {
		
		message = "" ;
		
		if(!checkOK(name)) {
			return message ;
		}
		
		Role role = new Role() ;
		role.setName(name);
		role.setVocation(vocation);
		role.setGender(gender);
		
		
		
		String id = generateID();
		role.setId(id);
		role.setAttack(1         + AlgorithmUtil.randomInt());
		role.setDefense(0        + AlgorithmUtil.randomInt());
		role.setIntelligence(1   + AlgorithmUtil.randomInt());
		role.setLevel(0);
		role.setLife(10);
		role.setMana(0           + AlgorithmUtil.randomInt());
		role.setNimble(1         + AlgorithmUtil.randomInt());
		role.setPhysique(1       + AlgorithmUtil.randomInt());
		role.setPower(1 	     + AlgorithmUtil.randomInt());
		
		getVocationAttributes(WorldUtil.cnameMap.get(vocation));
		
		role.setAttackDistance(Double.valueOf(vocationAttributes.getString("attackDistance"))); 
		role.setAttackSpeed(Double.valueOf(vocationAttributes.getString("attackSpeed")));
		role.setPace(Double.valueOf(vocationAttributes.getString("pace")));
		role.setAttackRate(AlgorithmUtil.calcAttackRate(role.getAttackSpeed()));
		
		if(saveRole(role)) {
			message = "角色["+name+"]创建成功";
		}else {
			message = "角色["+name+"]创建失败";
		}
		
		return message;
		
	}
	
	public static void getVocationAttributes(String vocation) {
		JSONArray jsonArray=WorldUtil.worldObject.getJSONArray("world").getJSONObject(0).getJSONArray("vocation");
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			if(jsonObject.has(vocation)) {
				vocationAttributes= jsonObject.getJSONObject(vocation);
				break;
			}
		}
		
	}
	
	private boolean saveRole(Role role) throws IOException {
		String rolePath = file.getAbsolutePath();
		File roleFile = new File(rolePath+"\\"+role.getName()+"."+role.getId()+".role");
		if(roleFile.createNewFile()) {
			FileOutputStream fos = new FileOutputStream(roleFile);
			String roleInfo = 
					"id(ID)="+role.getId()+"\r\n"+
					"name(姓名)="+role.getName()  +"\r\n" +
					"gender(性别)="+role.getGender()  +"\r\n" +
					"vocation(职业)="+role.getVocation()  +"\r\n" +
					"level(等级)="+role.getLevel()  +"\r\n" +
					"life(生命值)="+role.getLife()  +"\r\n" +
					"mana(魔力值)="+role.getMana()  +"\r\n" + 
					"attack(攻击力)="+role.getAttack()  +"\r\n" + 
					"defense(防御力)="+role.getDefense()  +"\r\n" + 
					"physique(体质)="+role.getPhysique()  +"\r\n" + 
					"power(力量)="+role.getPower()  +"\r\n" + 
					"nimble(敏捷)="+role.getNimble()  +"\r\n" + 
					"intelligence(智力)="+role.getIntelligence()  +"\r\n" + 
					"attackDistance(攻击距离)="+role.getAttackDistance()  +"\r\n" + 
					"attackRate(攻击频率)="+role.getAttackRate()  +"\r\n" + 
					"attackSpeed(攻击速度)="+role.getAttackSpeed()  +"\r\n" + 
					"pace(移动速度)="+role.getPace() ;
			fos.write(roleInfo.getBytes());
			fos.flush();
			fos.close();
			System.out.println("角色["+role.getName()+"]创建成功");
			return true;
		}else{
			System.out.println("角色["+role.getName()+"]创建失败");
			return false;
		}
	}
	
	private String generateID() {
		String id = UUID.randomUUID().toString();
		if(ids.containsKey(id)) {
			generateID();
		}
		return id ;
	}
	
	private boolean checkOK(String name) throws IOException {
		
		if("".equals(name.trim())) {
			message = "姓名不能为空" ;
			System.out.println("姓名不能为空");
			return false ;
		}
		
		if (file.mkdirs()) {
			System.out.println("初始化数据库文件");
		}else {
			File[] roles = file.listFiles();
			for (File role : roles) {
				String roleNameID = role.getName() ;
				String roleName = roleNameID.split("\\.")[0] ;
				String roleID = roleNameID.split("\\.")[1] ;
				ids.put(roleID, roleName);
				if(name.equals(roleName)) {
					message = "角色名["+name+"]已经存在" ;
					System.out.println("角色名["+name+"]已经存在");
					return false ;
				}
			}
		}
		
		return true;
		
	}
	

}
