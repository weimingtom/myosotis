package com.iteye.weimingtom.myosotis.simplecalc;

public class Parser {
	private Token next = Token.EOF;
	private String input;
	private int position;
	
	/*
	 * statement ::= <value> "=" <expr>
	 *             | "print" <expr>
	 *             | "list"
	 */
	protected boolean parse(SimpleCalcDriver driver, String str) {
		if (str == null) {
			return false;
		}
		this.input = str;
		this.position = 0;
		
		Token token;
		while (true) {
			token = getToken();
			if (token.getType() == Node.OP_EOF) {
				break;
			} else if (token.getType() == Node.OP_LIST) {
				driver.list();
			} else if (token.getType() == Node.OP_PRINT) {
				Node node = exprAddSub();
				driver.print(node);
				node = null;
			} else if (token.getType() == Node.OP_VALUE) {
				Token equ = getToken();
				if (equ.getType() != Node.OP_EQU) {
					return false;
				}
				Node node = exprAddSub();
				driver.assign(token.getStr(), node);
				node = null;
			} else if (token.getType() != Node.OP_NEWLINE) {
				return false;
			}
			token = getToken();
			if (token.getType() != Node.OP_NEWLINE) {
				ungetToken(token);
			}
		}
		return true;
	}
	
	/*
	 * 	expr ::= <muldiv> (("+" | "-") <muldiv>)*
	 */
	private Node exprAddSub() {
		Node left = exprMulDiv();
		if (left == null) {
			return null;
		}
		while (true) {
			Token token = getToken();
			if (token.getType() != Node.OP_PLUS && token.getType() != Node.OP_MINUS) {
				ungetToken(token);
				break;
			}
			Node right = exprMulDiv();
			if (right == null) {
				left = null;
				return null;
			}
			left = new Node(token.getType(), left, right);
		}
		return left;
	}
	
	/**
	 * 	muldiv ::= <term> (("*" | "/") <term>)*
	 */
	private Node exprMulDiv() {
		Node left = term();
		if (left == null) {
			return null;
		}
		while (true) {
			Token token = getToken();
			if (token.getType() != Node.OP_TIMES && token.getType() != Node.OP_DIVIDE) {
				ungetToken(token);
				break;
			}
			Node right = term();
			if (right == null) {
				left = null;
				return null;
			}
			left = new Node(token.getType(), left, right);
		}
		return left;
	}

	/**
	 * 	term ::= "-" <term> | "(" <expr> ")" | <number> | <value>
	 */
	private Node term() {
		Token token = getToken();
		if (token.getType() == Node.OP_MINUS) {
			return new Node(Node.OP_NEG, term());
		} else if (token.getType() == Node.OP_OPAR) {
			Node node = exprAddSub();
			if (node == null) {
				return null;
			}
			token = getToken();
			if (token.getType() != Node.OP_CPAR) {
				node = null;
				ungetToken(token);
				return null;
			}
			return node;
		} else if (token.getType() == Node.OP_CONST) {
			return new Node(Node.OP_CONST, Integer.parseInt(token.getStr()));
		} else if (token.getType() == Node.OP_VALUE) {
			return new Node(Node.OP_VALUE, token.getStr());
		}
		return null;
	}
	
	private static boolean isSpace(char c) {
		return c == ' ' || c == '\t';
	}

	private static boolean isNum(char c) {
		return c >= '0' && c <= '9';
	}

	private static boolean isAlpha(char c) {
		return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == '_';
	}
	
	private char getc() {
		//TODO: EOF
		char c = '\0'; //EOF
		if (this.position < this.input.length()) {
			c = this.input.charAt(position);
			this.position++;
		}
		return c;
	}
	
	private void ungetc(char c) {
		this.position--;
	}
	
	private Token getToken() {
		if (this.next.getType() != Node.OP_EOF) {
			int type = next.getType();
			String str = next.getStr();
			this.next = Token.EOF;
			return new Token(type, str);
		}
		return getNextToken();
	}
	
	private void ungetToken(Token token) {
		this.next = new Token(token.getType(), token.getStr());
	}
	
	/**
	 * '+'						OP_PLUS
	 * '-'						OP_MINUS
	 * '*'						OP_TIMES
	 * '/'						OP_DIVIDE
	 * '('						OP_OPAR
	 * ')'						OP_CPAR
	 * '='						OP_EQU
	 * '\n'						OP_NEWLINE
	 * EOF						OP_EOF
	 *
	 * "print"					OP_PRINT
	 * "list"					OP_LIST
	 * [0-9]+					OP_CONST
	 * [a-zA-Z_][a-zA-Z0-9_]*	OP_VALUE
	 */
	private Token getNextToken() {
		char c;
		do {
			c = getc();
		} while (isSpace(c));
		String str = new String();
		switch (c) {
			case '+':		
				return new Token(Node.OP_PLUS, "+");
			
			case '-':		
				return new Token(Node.OP_MINUS, "-");
		  
			case '*':		
				return new Token(Node.OP_TIMES, "*");
		  
			case '/':		
				return new Token(Node.OP_DIVIDE, "/");
		  
			case '(':		
				return new Token(Node.OP_OPAR, "(");
		  
			case ')':		
				return new Token(Node.OP_CPAR, ")");
		  
			case '=':		
				return new Token(Node.OP_EQU, "=");
		  
			case '\n':	
				return new Token(Node.OP_NEWLINE, "<NEWLINE>");
		  
			case '\0': //EOF
				return new Token(Node.OP_EOF, "<EOF>");
		}
		if (isNum(c)) {
			while (isNum(c)) {
				str += c;
				c = getc();
			}
			ungetc(c);
			return new Token(Node.OP_CONST, str);
		}
		if (isAlpha(c)) {
			while (isAlpha(c) || isNum(c)) {
				str += c;
				c = getc();
			}
			ungetc(c);
			/**
			 * NOTE: C++ "==" => Java "equals"
			 */
			if ("print".equals(str)) {
				return new Token(Node.OP_PRINT, str);
			} else if ("list".equals(str)) {
				return new Token(Node.OP_LIST, str);
			} else {
				return new Token(Node.OP_VALUE, str);
			}
		}
		return new Token(Node.OP_ERROR, "<ERROR>");
	}
}
