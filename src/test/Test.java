package test;

import java.io.File;

public class Test {

	public static void main(String[] args) {
		File f = new File("abc.txt");
		String name = f.getName();
		String suffix = name.substring(0,name.lastIndexOf("."));
		System.out.println(suffix);
	}

}
