package com.iteye.weimingtom.myosotis.memorybasic;

public class MemoryBasicTest {
	private static String prog1 =
		"a = rand(10)\n" +
		"end\n";
	
	private static String prog = 
			"\' test program \n" +
			"rem this is a basic program \n" +
			"\n" +
			"a = rand(10)\n" + 
			"b = rand(10)\n" +
			"  \n" +
			"a = a + b\n" +
			"\n" +
			"c = a + 3 * b + 4 * a\n" +
			"\n" +
			"if a == b then\n" +
			"	c = a + b\n" +
			"else\n" +
			"	c = a - b\n" +
			"endif\n" +
			"\n" +
			"for i=1 to 10\n" +
			"	a = a + 1\n" +
			"next\n" +
			"\n" +
			"while a != 0\n" +
			"	a = a - 1\n" +
			"wend\n" +
			"\n" +
			"end\n";
	
	public static void main(String[] args) {
		Compiler driver = new Compiler();
		VMData data = new VMData();
		boolean compile_result = driver.compile(prog, data);
		if (compile_result) {
			driver.debug_dump();
		}
		if (compile_result) {
			VCPU machine = new VCPU(data);
			int result = machine.run();
			System.out.println("result = " + result);
		}
	}
}
