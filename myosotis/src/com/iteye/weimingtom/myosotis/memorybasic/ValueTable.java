package com.iteye.weimingtom.myosotis.memorybasic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ValueTable {
	private Map<String, ValueTag> variables = new HashMap<String, ValueTag>();
	private int addr;
	
	public ValueTable() {
		this.addr = 0;
	}
	
	public ValueTable(int start_addr) {
		this.addr = start_addr;
	}
	
	public void dump() {
		Set<Map.Entry<String, ValueTag>> entries = variables.entrySet();
		Iterator<Map.Entry<String, ValueTag>> it = entries.iterator();
		while (it.hasNext()) {
			Map.Entry<String, ValueTag> entry = it.next();
			String key = (String)entry.getKey();
			ValueTag value = (ValueTag)entry.getValue();
			System.out.println(key + 
					", addr = " + value.getAddr() + 
					", size = " + value.getSize());
		}
	}
	
	public boolean add(String name) {
		return add(name, 0);
	}
	
	public boolean add(String name, int size) {
		//FIXME:
		if (this.variables.put(name, 
				new ValueTag(this.addr, size)) == null) {
			this.addr += size;
			return true;
		}
		return false;
	}
	
	public ValueTag find(String name) {
		return this.variables.get(name);
	}
	
	public boolean add_arg(String name, int addr) {
		//FIXME:
		return this.variables.put(name, new ValueTag(addr, 1)) == null ? 
				true : false;
	}
	
	public int size() {
		return this.addr;
	}
	
	public void clear() {
		this.variables.clear();
		this.addr = 0;
	}
}
