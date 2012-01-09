package com.iteye.weimingtom.myosotis.memorybasic;

public class NodeValue {
	public static final int CONST = 0;
	public static final int VALUE = 1;
	public static final int TEMP = 2;
	
	private int type;
	private int value;
	private Compiler c;
	
	public NodeValue() {
		this.type = -1;
		this.value = -1;
		this.c = null;
	}
	
	public NodeValue(int type, int value) {
		this.type = type;
		this.value = value;
		this.c = null;
	}
	
	public NodeValue(Compiler c, int type, int value) {
		this.type = type;
		this.value = value;
		this.c = c;
	}

	public int getType() {
		return type;
	}

	//FIXME:
	public int getValue() {
		if (this.type == NodeValue.TEMP) {
			return this.c.GetTempAddr(value);
		}
		return this.value;
	}

	public Compiler getC() {
		return c;
	}
	
	public static NodeValue MakeNodeValue(Compiler c, Node node) {
		switch (node.getOp()) {
			case Node.OP_CONST:
				return new NodeValue(CONST, node.getValue());

			case Node.OP_VALUE:
				ValueTag tag = c.GetValueTag(node.getStr());
				if (tag == null) {
					c.error("variable " +  node.getStr() + " is not defined");
					return new NodeValue(VALUE, 0);
				}
				return new NodeValue(NodeValue.VALUE, tag.getAddr());
		}
		return node.analyze(c);
	}
	
	public static NodeValue MakeTempValue(Compiler c, int value) {
		if (value < 0) {
			return new NodeValue(c, TEMP, c.AllocTempValue());
		}
		return new NodeValue(VALUE, value);
	}

	public void ReleaseTempValue() {
		this.c.ReleaseTempValue(this.value);
	}

	public void UseTempValue() {
		this.c.UseTempValue(this.value);
	}

	public int value() {
		if (this.type == TEMP) {
			return this.c.GetTempAddr(this.value);
		}
		return this.value;
	}
	
	public static Node MakeNode(Compiler c, int op, Node left, Node right) {
		if (right == null) {
			switch (op) {
			  case Node.OP_NEG:
				if (left.getOp() == Node.OP_CONST) {
					left.setValue(-left.getValue());
					return left;
				}
				break;
			}
			return new Node(op, left);
		}
		if (left.getOp() == Node.OP_CONST && right.getOp() == Node.OP_CONST) {
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
						c.error("divide 0 error");
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
}
