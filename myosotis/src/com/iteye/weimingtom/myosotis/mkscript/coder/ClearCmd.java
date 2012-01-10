package com.iteye.weimingtom.myosotis.mkscript.coder;

import com.iteye.weimingtom.myosotis.mkscript.command.ClearCommand;
import com.iteye.weimingtom.myosotis.mkscript.script.CommandBuffer;
import com.iteye.weimingtom.myosotis.mkscript.script.Lexer;
import com.iteye.weimingtom.myosotis.mkscript.script.MakeScript;
import com.iteye.weimingtom.myosotis.mkscript.script.ScriptType;

public class ClearCmd implements ICodeGenerator {

	@Override
	public void exec(Lexer lexer, MakeScript ms) {
		String p = lexer.GetString();
		if (p == null || lexer.GetString() != null) {
			ms.NormalError("syntax error (in clear command)");
			return;
		}
		if ("text".equals(p.toLowerCase())) {
			CommandBuffer.WriteCommand(ScriptType.CLEAR_TEXT_CMD, ms.command_buffer);
		} else {
			ClearCommand cp = (ClearCommand) CommandBuffer.NewCommand(ScriptType.CLEAR_CMD);
			cp.pos = (byte)ms.GetPosition(p);
			CommandBuffer.WriteCommand(ScriptType.CLEAR_CMD, ms.command_buffer);
		}
	}
}
