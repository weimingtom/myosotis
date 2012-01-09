package com.iteye.weimingtom.myosotis.memorybasic;

public class ValueTag {
	private int addr;
	private int size;
	
	public ValueTag() {
		this.addr = -1;
		this.size = 1;
	}
	
	public ValueTag(int addr, int size)  {
		this.addr = addr;
		this.size = size;
	}

	public int getAddr() {
		return addr;
	}

	public void setAddr(int addr) {
		this.addr = addr;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}

