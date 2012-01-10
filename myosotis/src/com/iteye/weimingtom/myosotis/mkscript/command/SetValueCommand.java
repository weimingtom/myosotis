package com.iteye.weimingtom.myosotis.mkscript.command;

public class SetValueCommand extends Command {
	public short value_addr; //short
	public int set_value;
	
	public SetValueCommand(byte type) {
		super(type);
	}

	@Override
	public String toString() {
		return "[SetValueCommand] { value_addr: " + value_addr +
			", set_value: " + set_value +
			" }";
	}
}
