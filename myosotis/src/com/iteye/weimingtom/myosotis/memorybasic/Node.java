package com.iteye.weimingtom.myosotis.memorybasic;

public class Node {
	public static final int OP_NEG = 0;
	public static final int OP_PLUS = 1;
	public static final int OP_MINUS = 2;
	public static final int OP_TIMES = 3;
	public static final int OP_DIVIDE = 4;
	public static final int OP_MOD = 5;
	public static final int OP_EQ = 6;
	public static final int OP_NE = 7;
	public static final int OP_GT = 8;
	public static final int OP_GE = 9;
	public static final int OP_LT = 10;
	public static final int OP_LE = 11;
	public static final int OP_VALUE = 12;
	public static final int OP_CONST = 13;
	public static final int OP_RANDFUNC = 14;	
	
	private int op;
	private int value;
	private String str;
	private Node left;
	private Node right;

	public Node(int op, Node left) {
		this.op = op;
		this.left = left;
		this.right = null;
		this.value = 0;
		this.str = null;
	}
	
	public Node(int op, Node left, Node right) {
		this.op = op;
		this.left = left;
		this.right = right;
		this.value = 0;
		this.str = null;
	}
	
	public Node(int op, int value){
		this.op = op;
		this.left = null;
		this.right = null;
		this.value = value;
		this.str = null;		
	}
	
	public Node(int op, String str) {
		this.op = op;
		this.left = null;
		this.right = null;
		this.value = 0;
		this.str = str;			
	}
	
	public Node(int op, String str, Node node) {
		this.op = op;
		this.left = node;
		this.right = null;
		this.value = 0;
		this.str = str;			
	}

	public int getOp() {
		return op;
	}

	public int getValue() {
		return value;
	}

	public String getStr() {
		return str;
	}

	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}
	
	public void setOp(int op) {
		this.op = op;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public NodeValue analyze(Compiler c) {
		return analyze(c, -1);
	}
	
	public static Node MakeNode(Compiler c, int op, Node left) {
		return MakeNode(c, op, left, null);
	}
	
	public static Node MakeNode(Compiler c, int op, Node left, Node right) {
		if (right == null) {
			switch (op) {
			case Node.OP_NEG:
				if (left.getOp() == Node.OP_CONST) {				// ’è”‰‰ŽZ‚ðŒvŽZ‚·‚é
					left.setValue(-left.getValue());
					return left;
				}
				break;
			}
			return new Node(op, left);
		}
		if (left.getOp() == Node.OP_CONST && right.getOp() == OP_CONST) {
			switch (op) {
			case Node.OP_EQ:
				left.setValue((left.getValue() == right.getValue())? 1: 0);
				break;

			case Node.OP_NE:
				left.setValue((left.getValue() != right.getValue())? 1: 0);
				break;

			case Node.OP_GT:
				left.setValue((left.getValue() > right.getValue())? 1: 0);
				break;

			case Node.OP_GE:
				left.setValue((left.getValue() >= right.getValue())? 1: 0);
				break;

			case Node.OP_LT:
				left.setValue((left.getValue() < right.getValue())? 1: 0);
				break;

			case Node.OP_LE:
				left.setValue((left.getValue() <= right.getValue())? 1: 0);
				break;

			case Node.OP_MINUS:
				left.setValue(left.getValue() - right.getValue());
				break;

			case Node.OP_PLUS:
				left.setValue(left.getValue() + right.getValue());
				break;

			case Node.OP_TIMES:
				left.setValue(left.getValue() * right.getValue());
				break;

			case Node.OP_DIVIDE:
				if (right.getValue() == 0) {
					c.error("devide 0 error");
				} else {
					left.setValue(left.getValue() / right.getValue());
				}
				break;

			case Node.OP_MOD:
				if (right.getValue() == 0) {
					c.error("mod 0 error");
				} else {
					left.setValue(left.getValue() % right.getValue());
				}
				break;
				
			default:
				return new Node(op, left, right);
			}
			right = null;
			return left;
		}
		return new Node(op, left, right);
	}
	
	public NodeValue analyze(Compiler c, int ret) {
		switch (op) {
			case Node.OP_NEG:
				return c.MakeNeg(ret, NodeValue.MakeNodeValue(c, this.left));

			case Node.OP_RANDFUNC:
				return c.MakeRand(ret, NodeValue.MakeNodeValue(c, this.left));

			case Node.OP_CONST:
				return c.MakeMoveC(ret, value);

			case Node.OP_VALUE:
				return c.MakeMoveV(ret, NodeValue.MakeNodeValue(c, this));
		}
		NodeValue left = NodeValue.MakeNodeValue(c, this.left);
		NodeValue right = NodeValue.MakeNodeValue(c, this.right);
		return c.MakeOp(this.op, ret, left, right);
	}
}
