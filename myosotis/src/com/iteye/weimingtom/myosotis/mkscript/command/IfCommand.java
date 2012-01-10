package com.iteye.weimingtom.myosotis.mkscript.command;

public class IfCommand extends Command {
	public byte flag;
	public int value1;
	public int value2;
	public int goto_label;
	
	public IfCommand(byte type) {
		super(type);
	}

	@Override
	public String toString() {
		return "[IfCommand] { flag: " + flag +
			", value1: " + value1 +
			", value2: " + value2 +
			" }";
	}
}
