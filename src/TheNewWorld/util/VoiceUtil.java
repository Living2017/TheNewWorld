package TheNewWorld.util;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class VoiceUtil {
	
	public static void main(String[] args) {
		//System.out.println(java.library.path);
		speak("你好");
	}
	
	
	public static void speak(String info) {
		
		//ActiveXComponent axc = new ActiveXComponent("Sapi.SpVoice");
		ActiveXComponent axc = new ActiveXComponent("ScriptControl");
		Dispatch dispatch = axc.getObject();
		try {
			//音量0-100
			axc.setProperty("Volume", new Variant(100));
			//朗读速度-10到+10
			axc.setProperty("Rate", new Variant(-2));
			Variant defalutVoice = axc.getProperty("Voice");
			Dispatch dispDefaultVoice = defalutVoice.toDispatch();
			Variant allVoices = Dispatch.call(dispatch, "GetVoices");
			Dispatch dispVoices = allVoices.toDispatch();
			Dispatch setVoice = Dispatch.call(dispVoices, "Item", new Variant(1)).toDispatch();
			ActiveXComponent voiceActivex = new ActiveXComponent(dispDefaultVoice);
			ActiveXComponent setVoiceActivex = new ActiveXComponent(setVoice);
			Variant item = Dispatch.call(setVoiceActivex, "GetDescription");
			//执行朗读
			Dispatch.call(dispatch, "Speak", new Variant(info));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dispatch.safeRelease();
			axc.safeRelease();
		}
	}

}
