package com.iteye.weimingtom.myosotis.mkscript.command;

public class ClearCommand extends Command {
	public byte pos;
	
	public ClearCommand(byte type) {
		super(type);
	}
	
	@Override
	public String toString() {
		return "[ClearCommand] { pos: " + pos + 
			" }";
	}
}
