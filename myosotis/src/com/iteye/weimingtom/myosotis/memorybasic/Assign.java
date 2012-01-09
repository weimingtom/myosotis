package com.iteye.weimingtom.myosotis.memorybasic;

public class Assign {
	private int op;
	private Node value;
	private Node expr;
	
	public Assign(int op, Node value, Node expr) {
		this.op = op;
		this.value = value;
		this.expr = expr;
	}
	
	/**	
	 *  a = b
	 *	>	mov a, b
	 */
	public NodeValue analyze(Compiler c) {
		ValueTag tag = c.GetValueTag(value.getStr());
		if (tag == null) {
			tag = c.AddValue(value.getStr());
		}
		if (tag == null) {
			c.error("variable " + value.getStr() + " is not defined");
			return new NodeValue();
		}
		return expr.analyze(c, tag.getAddr());
	}
}
