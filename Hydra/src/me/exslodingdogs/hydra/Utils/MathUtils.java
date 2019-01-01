package me.exslodingdogs.hydra.Utils;

public class MathUtils {
	
	public static boolean isRoughtlyEqual(double d1, double d2) {
		return Math.abs(d1-d2) < 0.001;
	}

}
