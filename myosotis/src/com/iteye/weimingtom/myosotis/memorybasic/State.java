package com.iteye.weimingtom.myosotis.memorybasic;

public class State {
	public static final int STATE_IF = 0;
	public static final int STATE_FOR = 1;
	public static final int STATE_WHILE = 2;
	
	private int state;
	private int label1;
	private int label2;
	private NodeValue counter;
	private Node end;
	private Node step;
	
	public State(int state, int label) {
		this.state = state;
		this.label1 = label;
		this.label2 = 0;
		this.end = null;
		this.step = null;
	}
	
	public State(int state, int label1, int label2) {
		this.state = state;
		this.label1 = label1;
		this.label2 = label2;
		this.end = null;
		this.step = null;
	}
	
	public State(int state, int label, NodeValue counter, Node end, Node step) {
		this.state = state;
		this.label1 = label;
		this.label2 = 0;
		this.counter = counter;
		this.end = end;
		this.step = step;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getLabel1() {
		return label1;
	}

	public void setLabel1(int label1) {
		this.label1 = label1;
	}

	public int getLabel2() {
		return label2;
	}

	public void setLabel2(int label2) {
		this.label2 = label2;
	}

	public NodeValue getCounter() {
		return counter;
	}

	public void setCounter(NodeValue counter) {
		this.counter = counter;
	}

	public Node getEnd() {
		return end;
	}

	public void setEnd(Node end) {
		this.end = end;
	}

	public Node getStep() {
		return step;
	}

	public void setStep(Node step) {
		this.step = step;
	}
}
