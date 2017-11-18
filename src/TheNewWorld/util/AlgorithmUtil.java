package TheNewWorld.util;

import java.math.BigDecimal;
import java.util.Random;

public class AlgorithmUtil {

	public static int randomInt() {
		Random random = new Random();
		int i = random.nextInt(10);
		return i;
	}
	public static int randomInt(int bound) {
		Random random = new Random();
		int i = random.nextInt(bound);
		return i;
	}
	public static double randomDouble() {
		Random random = new Random();
		double i = random.nextDouble();
		BigDecimal bg = new BigDecimal(i);
		return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public static double calcAttackRate(double attackSpeed) {
		BigDecimal bg = new BigDecimal(1/attackSpeed);
		return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static void main(String[] args) {
		System.out.println(randomDouble());
	}
}
