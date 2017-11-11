package TheNewWorld.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import TheNewWorld.MainApp;
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
	
	@SuppressWarnings("unused")
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
	//雷电术
	@SuppressWarnings("unused")
	private static String THUNDER_ATTACK = "thunder";
	
	
	
	
	
	
	@FXML
	public void handleButtonConfirm() {
		InputInfo = textFieldInput.getText().trim();
		
		if(InputInfo.startsWith("@@")) {//控制语句
			String controlInfo = InputInfo.substring(2).trim();
			showInfo="\r\n\r\n";
			showInfo+="[操作]："+controlInfo;
			showInfo+=" ---> ";
			textAreaShow.appendText(showInfo);
			if(SAVE_CONTROL.equals(controlInfo.toLowerCase())) {
				saveInfo(textAreaShow.getText());
				showInfo+="\r\n操作成功";
			}else if(SHOW_CONTROL.equals(controlInfo.toLowerCase())) {
				try {
					showRoleList();
					showInfo+="\r\n操作成功";
				} catch (IOException e) {
					showInfo+="\r\n操作失败\r\n";
					showInfo+=e.getLocalizedMessage();
					e.printStackTrace();
				}
			}
			
		}
		else if(InputInfo.startsWith("##")) {//放技能或攻击
			String attacklInfo = InputInfo.substring(2).trim();
			showInfo="\r\n\r\n";
			showInfo+="[释放技能]："+attacklInfo;
			showInfo+=" ---> ";
			textAreaShow.appendText(showInfo);
			SoundUtil su = new SoundUtil();
		/*	if(THUNDER_ATTACK.equals(attacklInfo.toLowerCase())){
				
			}*/
			VoiceUtil.speak(attacklInfo);
			if(su.playSound(attacklInfo)) {
				showInfo="技能释放成功";
			}else {
				showInfo="那是一个技能？";
				VoiceUtil.speak("呵呵");
			}
			
		}
		
		else {//非控制语句
			showInfo = "\r\n[普通]："+InputInfo;
		}
		
		
		textAreaShow.appendText(showInfo);
		textFieldInput.setText("");
	}
	
	public void attack(String attackType) {
		
	}
	
	/**
	 * 保存信息
	 * @param info
	 */
	public void saveInfo(String info) {
		File logDir = new File(MainApp.userDir+"\\resources\\log");
		showInfo = "";
		if(logDir.mkdirs()) {
			showInfo+="\r\n初始化日志成功\r\n ";
			System.out.println("初始化日志");
		}
		File logFile = new File(logDir.getAbsolutePath()+"\\"+DateUtil.getDateTime()+".logdata");
		try {
			if(logFile.createNewFile()) {
				showInfo+="\r\n创建日志文件成功,文件路径："+logFile.getAbsolutePath()+"\r\n ";
			}
			FileOutputStream fos = new FileOutputStream(logFile);
			fos.write(info.getBytes());
			fos.flush();
			fos.close();
			showInfo+="\r\n日志保存成功,文件名："+logFile.getName()+"\r\n ";
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 显示角色列表
	 * @throws IOException
	 */
	public void showRoleList() throws IOException {
		RoleUtil ru = new  RoleUtil();
		File[] rolesFile = ru.file.listFiles();
		if(rolesFile.length == 0) {
			showInfo = "\r\n无可用角色，请创建一个角色！";
		}else {
			showInfo = "\r\n\r\n角色列表：\r\n";
			for (File roleFile : rolesFile) {
				String roleFileName = roleFile.getName();
				String roleName = roleFileName.split("\\.")[0];
				showInfo+=roleName+"\t";
			}
		}
	}
	
	@FXML
	public void handleKeyboardEvent(KeyEvent event) {
		if(event.getCode() == KeyCode.ENTER) {
			handleButtonConfirm();
		}
	}
	
	
	
}
