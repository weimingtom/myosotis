package com.iteye.weimingtom.myosotis.calc;

public class CalcTest {
	
	public static void main(String[] args) {
		String prog = 
			"a = 10\n" + //
			"b = 20\n" + //
			"c = a + b * 2\n" + // 50
			"d = (a + 1) * (-b + 2)\n" + // 11 * -18 = -198
			"print a + b * -c\n" + // 10 + 20 * -50 = -990
			"list\n" + //
			"a = -1\n" + //
			"print a\n";
		CalcDriver driver = new CalcDriver();
		driver.calc(prog);
	}
}
