package TheNewWorld.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getDateTime() {
		Date d = new  Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		return sdf.format(d);
	}
}
