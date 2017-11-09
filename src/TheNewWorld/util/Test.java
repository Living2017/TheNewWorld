package TheNewWorld.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javafx.scene.image.Image;

public class Test {

	public static void main(String[] args) throws IOException {
		//RoleUtil ru = new RoleUtil();
		//ru.generateRole(" ads ", "ds", "male");
/*		String userDir = System.getProperty("user.dir");
		File f = new File(userDir + "\\database");
		boolean b = f.mkdirs();
		
		System.out.println(b);
		System.out.println(f.getAbsolutePath());*/
		Date d = new  Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		System.out.println(sdf.format(d));
	
	}
}
