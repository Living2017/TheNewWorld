package TheNewWorld.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularUtil {

	public static boolean isChinesePattern(String string) {
		String regularExpression = "^[\\u4e00-\\u9fa5]+$";
		Pattern pattern = Pattern.compile(regularExpression);
		//Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(string);
		return matcher.matches();
	}
	
	public static void main(String[] args) {
		System.out.println(isChinesePattern("弄"));
	}
}
