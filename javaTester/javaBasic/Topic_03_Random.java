package javaBasic;

import java.util.Random;

public class Topic_03_Random {

	public static void main(String[] args) {
		Random rand = new Random();
		rand.nextInt(99999);
		System.out.println(rand.nextInt(99999));
	}

}
