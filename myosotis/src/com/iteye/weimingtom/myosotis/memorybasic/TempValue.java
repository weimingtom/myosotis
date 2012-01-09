package com.iteye.weimingtom.myosotis.memorybasic;

public class TempValue {
	private int ref;
	private int addr;
	
	public TempValue(int ref, int addr) {
		this.ref = ref;
		this.addr = addr;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getAddr() {
		return addr;
	}

	public void setAddr(int addr) {
		this.addr = addr;
	}
}
