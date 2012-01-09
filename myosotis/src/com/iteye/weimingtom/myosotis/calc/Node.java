package com.iteye.weimingtom.myosotis.calc;

public class Node {
	public static final int OP_NEG = 0;
	public static final int OP_PLUS = 1;
	public static final int OP_MINUS = 2;
	public static final int OP_TIMES = 3;
	public static final int OP_DIVIDE = 4;
	public static final int OP_VALUE = 5;
	public static final int OP_CONST = 6;

	public int op;
	public int value;
	public String string;
	public Node left;
	public Node right;

	protected Node() {

	}

	public int expr(CalcDriver driver) {
		switch (op) {
		case OP_PLUS:
			return left.expr(driver) + right.expr(driver);

		case OP_MINUS:
			return left.expr(driver) - right.expr(driver);

		case OP_TIMES:
			return left.expr(driver) * right.expr(driver);

		case OP_DIVIDE:
			return left.expr(driver) / right.expr(driver);

		case OP_CONST:
			return value;

		case OP_VALUE:
			return driver.value(string);

		case OP_NEG:
			return -left.expr(driver);

		default:
			return 0;// error
		}
	}
}
