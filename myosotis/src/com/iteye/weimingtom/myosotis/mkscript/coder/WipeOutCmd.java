package com.iteye.weimingtom.myosotis.mkscript.coder;

import com.iteye.weimingtom.myosotis.mkscript.command.WipeoutCommand;
import com.iteye.weimingtom.myosotis.mkscript.script.CommandBuffer;
import com.iteye.weimingtom.myosotis.mkscript.script.Lexer;
import com.iteye.weimingtom.myosotis.mkscript.script.MakeScript;
import com.iteye.weimingtom.myosotis.mkscript.script.ScriptType;

public class WipeOutCmd implements ICodeGenerator {

	@Override
	public void exec(Lexer lexer, MakeScript ms) {
		double value = lexer.GetValue();
		if (Double.isNaN(value) || value <= 0 || value > 2 || lexer.GetString() != null) {
			ms.NormalError("syntax error (in wipeout command)");
			return;
		}
		WipeoutCommand cp = (WipeoutCommand)CommandBuffer.NewCommand(ScriptType.WIPEOUT_CMD);
		cp.pattern = (byte)value;
		CommandBuffer.WriteCommand(ScriptType.WIPEOUT_CMD, ms.command_buffer);
	}
}
