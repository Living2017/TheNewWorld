package TheNewWorld.util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JFrame;

public class SoundUtil extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean playSound(String fileName){
		setVisible(false);
		URL url=null;
		try {
			url = new URL("file:\\D:\\wav\\"+fileName+".wav");
			File f = new File(url.getFile());
			if(!f.exists()) {
				return false;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	    AudioClip audio1=Applet.newAudioClip(url);
	    audio1.play();
	    return true;
	}
}