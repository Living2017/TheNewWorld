package TheNewWorld.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;

import TheNewWorld.MainApp;
import TheNewWorld.model.Role;
import TheNewWorld.util.DateUtil;
import TheNewWorld.util.RoleUtil;
import TheNewWorld.util.SoundUtil;
import TheNewWorld.util.VoiceUtil;
import TheNewWorld.util.WorldUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainController {

	public MainController() {
		init();
	}

	private MainApp ma;

	public void setMa(MainApp ma) {
		this.ma = ma;
	}

	@FXML
	private Label Role1;
	@FXML
	private Label CreateRole;
	@FXML
	private Label RoleList;
	@FXML
	private Label deleteRole;
	@FXML
	private TextArea textAreaShow;
	@FXML
	private TextField textFieldInput;
	@FXML
	private Button buttonConfirm;

	private String showInfo;
	private String InputInfo;

	private static String SAVE_CONTROL = "save";
	private static String SHOW_CONTROL = "show";
	private static String ROLE_CONTROL = "role";
	// 雷电术
	@SuppressWarnings("unused")
	private static String THUNDER_ATTACK = "thunder";



	public void init() {
		@SuppressWarnings("unused")
		HashMap<String,String> a=RoleUtil.roleNamePathMap;
	}
	
	@FXML
	public void handelFighting() {
		String message = ma.handelFighting();
		if(message!=null &&!"null".equals(message)) {
			textAreaShow.appendText("\n"+message);
		}
	}
	
	@FXML
	public void handelCreateRole() {
		try {
			WorldUtil.initWorld();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String message = ma.CreateRoleDetail();
		if(message !=null) {
			if(message.contains("创建成功")) {
				init();
				String name = message.split("\\[")[1].split("\\]")[0];
				Role1.setText("角色:"+name);
				textAreaShow.appendText("\n"+message);
				showRoleDetail(name);
			}else {
				textAreaShow.appendText("\n"+message);
			}
		}
	}
	@FXML
	public void handelRoleList() {
		RoleUtil.init();
		String name = ma.handleRoleList();
		if(name!=null&&!"null".equals(name)) {
			Role1.setText("角色:"+name);
			showRoleDetail(name);
		}
	}
	@FXML
	public void handelDeleteRole() {
		String[] s =Role1.getText().split("\\:");
		if(s!=null && s.length==2) {
			String message = ma.handelDeleteRole(s[1]);
			if(message!=null&&message.contains("成功")) {
				Role1.setText("角色");
			}
		}
	}
	

	@FXML
	public void handleButtonConfirm() {
		InputInfo = textFieldInput.getText().trim();

		if (InputInfo.startsWith("@@")) {// 控制语句
			String controlInfo = InputInfo.substring(2).trim();
			showInfo = "\r\n\r\n";
			showInfo += "[操作]：" + controlInfo;
			showInfo += " ---> ";
			textAreaShow.appendText(showInfo);
			if (SAVE_CONTROL.equals(controlInfo.toLowerCase())) {
				saveInfo(textAreaShow.getText());
				showInfo += "\r\n操作成功";
			} else if (SHOW_CONTROL.equals(controlInfo.toLowerCase())) {
				try {
					showRoleList();
					showInfo += "\r\n操作成功";
				} catch (IOException e) {
					showInfo += "\r\n操作失败\r\n";
					showInfo += e.getLocalizedMessage();
					e.printStackTrace();
				}
			} else if (controlInfo.toLowerCase().startsWith(ROLE_CONTROL + "@")) {
				
				String[] str = controlInfo.split("@");
				if (str.length == 2) {
					showRoleDetail(str[1]);

				}
			}

		} else if (InputInfo.startsWith("##")) {// 放技能或攻击
			String attacklInfo = InputInfo.substring(2).trim();
			showInfo = "\r\n\r\n";
			showInfo += "[释放技能]：" + attacklInfo;
			showInfo += " ---> ";
			textAreaShow.appendText(showInfo);
			SoundUtil su = new SoundUtil();
			/*
			 * if(THUNDER_ATTACK.equals(attacklInfo.toLowerCase())){
			 * 
			 * }
			 */
			VoiceUtil.speak(attacklInfo);
			if (su.playSound(attacklInfo)) {
				showInfo = "技能释放成功";
			} else {
				showInfo = "那是一个技能？";
				VoiceUtil.speak("呵呵");
			}

		}

		else {// 非控制语句
			showInfo = "\r\n[普通]：" + InputInfo;
		}

		textAreaShow.appendText(showInfo);
		//这个代码啊啊啊啊，半夜1：38了啊
		//textFieldInput.setText("");
	}

	public void showRoleDetail(String name) {
		if (RoleUtil.roleNamePathMap.containsKey(name)) {
			String rolePath = RoleUtil.roleNamePathMap.get(name);
			File f = new File(rolePath);
			FileInputStream fi = null;
			InputStreamReader is = null;
			try {
				fi = new FileInputStream(f);
				is = new InputStreamReader(fi);
				String roleInfoTemp = null;
	/*				

				byte[] b = new byte[fi.available()];
				int i = 0;
				while ((i = is.read()) != -1) {
					roleInfo += (char) i;
				}
	*/			
				BufferedReader br = new BufferedReader(is);
				Role role = Role.class.newInstance();
				while((roleInfoTemp=br.readLine())!=null){
					String[] roleInfoArr = roleInfoTemp.split("\\=");
					String key = roleInfoArr[0].split("\\(")[0];
					String value = roleInfoArr[1];

					String MethodName = "set"+key.substring(0,1).toUpperCase()+key.substring(1);
					Method method = null;
					Object[] arguments =null;
					if(
						"id".equals(key) ||
						"name".equals(key) ||
						"gender".equals(key) ||
						"vocation".equals(key) 
						 ) {
						method = Role.class.getMethod(MethodName, new Class[] {String.class});
						arguments = new Object[]{value};
					}else if(
							"attackRate".equals(key)||
							"attackSpeed".equals(key)||
							"attackDistance".equals(key)||
							"pace".equals(key)
							){
						method = Role.class.getMethod(MethodName, new Class[] {Double.class});
						arguments = new Object[]{Double.valueOf(value)};
					}else {
						method = Role.class.getMethod(MethodName, new Class[] {Integer.class});
						arguments = new Object[]{Integer.valueOf(value)};
					}
					method.invoke(role, arguments);
					
				}
				ma.ShowRoleDetail(role);

			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(is!=null) {
						is.close();
					}
					if(fi!=null) {
						fi.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}

	}

	public void attack(String attackType) {

	}

	/**
	 * 保存信息
	 * 
	 * @param info
	 */
	public void saveInfo(String info) {
		File logDir = new File(MainApp.userDir + "\\resources\\log");
		showInfo = "";
		if (logDir.mkdirs()) {
			showInfo += "\r\n初始化日志成功\r\n ";
			System.out.println("初始化日志");
		}
		File logFile = new File(logDir.getAbsolutePath() + "\\" + DateUtil.getDateTime() + ".logdata");
		try {
			if (logFile.createNewFile()) {
				showInfo += "\r\n创建日志文件成功,文件路径：" + logFile.getAbsolutePath() + "\r\n ";
			}
			FileOutputStream fos = new FileOutputStream(logFile);
			fos.write(info.getBytes());
			fos.flush();
			fos.close();
			showInfo += "\r\n日志保存成功,文件名：" + logFile.getName() + "\r\n ";
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 显示角色列表
	 * 
	 * @throws IOException
	 */
	public void showRoleList() throws IOException {
		if (RoleUtil.rolesFile.length == 0) {
			showInfo = "\r\n无可用角色，请创建一个角色！";
		} else {
			showInfo = "\r\n\r\n角色列表：\r\n";
			for (File roleFile : RoleUtil.rolesFile) {
				String roleFileName = roleFile.getName();
				String roleName = roleFileName.split("\\.")[0];
				showInfo += roleName + "\t";
			}
		}
	}

	@FXML
	public void handleKeyboardEvent(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			handleButtonConfirm();
		}
		 
	}

	public TextField getTextFieldInput() {
		return textFieldInput;
	}

	public void setTextFieldInput(TextField textFieldInput) {
		this.textFieldInput = textFieldInput;
	}

	public String getShowInfo() {
		return showInfo;
	}

	public void setShowInfo(String showInfo) {
		this.showInfo = showInfo;
	}

	public Label getRole1() {
		return Role1;
	}

	public void setRole1(Label role1) {
		Role1 = role1;
	}

	
	
	
}
