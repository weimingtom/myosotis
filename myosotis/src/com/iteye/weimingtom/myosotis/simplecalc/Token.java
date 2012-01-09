package com.iteye.weimingtom.myosotis.simplecalc;

public class Token {
	public static Token EOF = new Token(Node.OP_EOF, "<EOF>");
	
	private int type;
	private String str;
	
	protected Token(int type, String str) {
		this.type = type;
		this.str = str;
		//System.out.println(str);
	}

	public int getType() {
		return type;
	}

	public String getStr() {
		return str;
	}
}
