package com.iteye.weimingtom.myosotis.simplecalc;

public class Node {
	public static final int OP_EOF = -1;
	
	public static final int OP_NEG = 0;
	public static final int OP_PLUS = 1;
	public static final int OP_MINUS = 2;
	public static final int OP_TIMES = 3;
	public static final int OP_DIVIDE = 4;
	public static final int OP_VALUE = 5;
	public static final int OP_CONST = 6;
	
	public static final int OP_OPAR = 7;
	public static final int OP_CPAR = 8;
	public static final int OP_EQU = 9;
	public static final int OP_NEWLINE = 10;
	public static final int OP_PRINT = 11;
	public static final int OP_LIST = 12;
	public static final int OP_ERROR = 13;
	
	public int op;
	public int value;
	public String string;
	public Node left;
	public Node right;

	protected Node(int op, Node left) {
		this.op = op;
		this.left = left;
		this.right = null;
		this.value = 0;
		this.string = null;
	}
	
	public Node(int op, Node left, Node right) {
		this.op = op;
		this.left = left;
		this.right = right;
		this.value = 0;
		this.string = null;
	}
	
	public Node(int op, int value) {
		this.op = op;
		this.left = null;
		this.right = null;
		this.value = value;
		this.string = null;		
	}
	
	public Node(int op, String str){
		this.op = op;
		this.left = null;
		this.right = null;
		this.value = 0;
		this.string = str;	
	}
	
	public int expr(SimpleCalcDriver driver) {
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
