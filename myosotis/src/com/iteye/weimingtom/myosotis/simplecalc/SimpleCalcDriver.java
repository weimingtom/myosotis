package com.iteye.weimingtom.myosotis.simplecalc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SimpleCalcDriver {
	private Map<String, Integer> values = new HashMap<String, Integer>();

	public boolean calc(String str) {
		Parser parser = new Parser();
		return parser.parse(this, str);
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
