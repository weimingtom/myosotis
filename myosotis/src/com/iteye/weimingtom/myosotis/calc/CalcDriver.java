package com.iteye.weimingtom.myosotis.calc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class CalcDriver {
	private Map<String, Integer> values = new HashMap<String, Integer>();

	public void calc(String str) {
		ANTLRStringStream input = new ANTLRStringStream(str);
		CalcLexer lexer = new CalcLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CalcParser parser = new CalcParser(tokens);
		try {
			parser.compilationUnit();
		} catch (RecognitionException e) {
			e.printStackTrace();
		}
	}
	
	public void assign(String value, Node node) {
		values.put(value, (Integer) node.expr(this));
	}

	public void print(Node node) {
		System.out.println(node.expr(this));
	}

	public void list() {
		Iterator<Map.Entry<String, Integer>> iter = values.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Integer> entry = iter.next();
			String key = (String) entry.getKey();
			Integer val = (Integer) entry.getValue();
			System.out.println(key + " = " + val);
		}
	}

	public int value(String name) {
		return (Integer) values.get(name);
	}
}
