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
	private TextArea textAreaShow;
	@FXML
	private TextField textFieldInput;
	@FXML
	private Button buttonConfirm;
	@FXML
	private Label label1;

	private String showInfo;
	private String InputInfo;

	private static String SAVE_CONTROL = "save";
	private static String SHOW_CONTROL = "show";
	private static String ROLE_CONTROL = "role";
	// 雷电术
	@SuppressWarnings("unused")
	private static String THUNDER_ATTACK = "thunder";

	private static HashMap<String, String> roleNamePathMap;

	private static RoleUtil roleUtil;
	private static File[] rolesFile;

	public void init() {
		roleNamePathMap = new HashMap<String, String>();

		try {
			roleUtil = new RoleUtil();
		} catch (IOException e) {
			e.printStackTrace();
		}
		rolesFile = roleUtil.file.listFiles();
		for (File file : rolesFile) {
			roleNamePathMap.put(file.getName().split("\\.")[0], file.getAbsolutePath());
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
		textFieldInput.setText("");
	}

	public void showRoleDetail(String name) {
		if (roleNamePathMap.containsKey(name)) {
			String rolePath = roleNamePathMap.get(name);
			File f = new File(rolePath);
			try {
				FileInputStream fi = new FileInputStream(f);
				InputStreamReader is = new InputStreamReader(fi);
				String roleInfo = "";
				String roleInfoTemp = null;
	/*				

				byte[] b = new byte[fi.available()];
				int i = 0;
				while ((i = is.read()) != -1) {
					roleInfo += (char) i;
				}
	*/			
				@SuppressWarnings("resource")
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
					}else {
						method = Role.class.getMethod(MethodName, new Class[] {Integer.class});
						arguments = new Object[]{Integer.valueOf(value)};
					}
					method.invoke(role, arguments);
					
					roleInfo +="\r\n"+ roleInfoTemp;
				}
				ma.ShowRoleDetail(role);
				System.out.println("---------");
				System.out.println(roleInfo);
				System.out.println("---------");

			} catch (Exception e) {
				e.printStackTrace();
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
		if (rolesFile.length == 0) {
			showInfo = "\r\n无可用角色，请创建一个角色！";
		} else {
			showInfo = "\r\n\r\n角色列表：\r\n";
			for (File roleFile : rolesFile) {
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

	
	
}