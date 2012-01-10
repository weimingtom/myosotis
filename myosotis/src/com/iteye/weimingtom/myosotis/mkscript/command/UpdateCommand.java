package com.iteye.weimingtom.myosotis.mkscript.command;

public class UpdateCommand extends Command {
	public byte flag;

	public UpdateCommand(byte type) {
		super(type);
	}

	@Override
	public String toString() {
		return "[UpdateCommand] { flag: " + flag +
			" }";
	}
}
