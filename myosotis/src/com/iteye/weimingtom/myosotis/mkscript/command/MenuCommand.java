package com.iteye.weimingtom.myosotis.mkscript.command;

public class MenuCommand extends Command {
	public short value_addr;
	
	public MenuCommand(byte type) {
		super(type);
	}

	@Override
	public String toString() {
		return "[MenuCommand] { value_addr: " + value_addr +
			" }";
	}
}
