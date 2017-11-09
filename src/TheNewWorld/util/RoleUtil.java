package TheNewWorld.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import TheNewWorld.MainApp;
import TheNewWorld.model.Role;

public class RoleUtil {
	
	private String message ;
	private HashMap<String,String> ids ;
	
	public File file ;
	
	public RoleUtil() throws IOException {
		
		message = "" ;
		ids = new HashMap<String,String>();
		
		file = new File(MainApp.userDir + "\\database\\role") ;
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
		role.setAttack(1);
		role.setAttackDistance(1);
		role.setAttackRate(1);
		role.setAttackSpeed(1);
		role.setDefense(0);
		role.setIntelligence(1);
		role.setLevel(0);
		role.setLife(10);
		role.setMana(0);
		role.setNimble(1);
		role.setPace(1);
		role.setPhysique(1);
		role.setPower(1);
		
		saveRole(role);
		
		return message;
		
	}
	
	private void saveRole(Role role) throws IOException {
		String rolePath = file.getAbsolutePath();
		System.out.println("rolePath="+rolePath);
		File roleFile = new File(rolePath+"\\"+role.getName()+"."+role.getId()+".role");
		if(roleFile.createNewFile()) {
			FileOutputStream fos = new FileOutputStream(roleFile);
			String roleInfo = 
					"id="+role.getId()+"\r\n"+
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
		}else{
			System.out.println("角色["+role.getName()+"]创建失败");
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
