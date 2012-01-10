package com.iteye.weimingtom.myosotis.mkscript.command;

public class WipeoutCommand extends Command {
	public byte pattern;

	public WipeoutCommand(byte type) {
		super(type);
	}

	@Override
	public String toString() {
		return "[WipeoutCommand] { pattern: " + pattern +
			" }";
	}
}
