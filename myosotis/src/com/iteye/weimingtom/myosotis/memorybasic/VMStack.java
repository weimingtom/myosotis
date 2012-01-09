package com.iteye.weimingtom.myosotis.memorybasic;

public class VMStack {
	public static final int SIZE = VCPU.STACK_SIZE;
	
	private Integer[] data = new Integer[SIZE];
	private int size = 0;
	
	public VMStack() {
		
	}
	
	public void push(int value) throws StackOverflowException {
		if (SIZE <= size) {
			throw new StackOverflowException();
		}
		this.data[size++] = new Integer(value);
	}

	public void pop() {
		data[--size] = null;
	}

	public void pop(int count) throws StackOverflowException {
		resize(size - count);
	}

	public void resize(int newsize) throws StackOverflowException {
		int oldsize = this.size;
		if (oldsize > newsize) {
			for (int i = newsize; i < oldsize; ++i) {
				this.data[i] = null;
			}
		}
		if (oldsize < newsize) {
			if (SIZE < newsize) {
				throw new StackOverflowException();
			}
			for (int i = oldsize; i < newsize; ++i) {
				this.data[i] = new Integer(0);
			}
		}
		this.size = newsize;
	}
	
	public Integer top() { 
		return data[this.size - 1]; 
	}
	
	public boolean overflow() { 
		return this.size >= SIZE; 
	}
	
	public boolean empty() { 
		return this.size == 0; 
	}
	
	public int size() { 
		return this.size; 
	}

	public Integer get(int index) { 
		return this.data[index]; 
	}
}
