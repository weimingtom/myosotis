package com.iteye.weimingtom.myosotis.memorybasic;

public class ValueNode extends Node {
	public ValueNode(String name, Node node) {
		super(Node.OP_VALUE, name, node);
	}
	
	public ValueNode(String name) {
		super(Node.OP_VALUE, name, null);
	}
}
