package com.iteye.weimingtom.myosotis.mkscript.script;

import java.nio.ByteBuffer;

public class Test {
	
	public static void test1() {
		FileReader fileReader = new FileReader(/*"main.txt");*/"sample3.txt");
		while (true) {
			String str = fileReader.GetString();
			if (str == null) {
				break;
			}
			System.out.println(str);
			System.out.println("line:" + fileReader.GetLineNo());
			//Lexer lexer;
			new Lexer(str);
		}
	}
	
	public static void test2() {
		MakeScript makeScript = new MakeScript();
		makeScript.ReadScript("sample3.txt");
		makeScript.dumpBuffer();
	}
	
	public static void test3() {
		ByteBuffer buffer = ByteBuffer.allocate(255);
		buffer.clear();
		for (int i = 0; i < 30; i++) {
			buffer.put((byte) 0);
		}
		System.out.println("capacity:" + buffer.capacity());
		System.out.println("arrayOffset:" + buffer.arrayOffset());
		System.out.println("array().length:" + buffer.array().length);
		System.out.println("position:" + buffer.position());
		
		System.out.println("out => " + ("; t".charAt(0) == ';'));
	}
	
	public static void main(String[] args) {
		FileLoader.insertRes("main.txt", "main.txt");
		FileLoader.insertRes("sample1old.txt", "sample1old.txt");
		FileLoader.insertRes("sample3.txt", "sample3.txt");
		
		test2();
	}
}
