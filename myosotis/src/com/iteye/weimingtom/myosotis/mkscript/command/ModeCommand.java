package com.iteye.weimingtom.myosotis.mkscript.command;

public class ModeCommand extends Command {
	public byte mode;

	public ModeCommand(byte type) {
		super(type);
	}
	
	@Override
	public String toString() {
		return "[ModeCommand] { mode: " + mode +
			" }";
	}
}
