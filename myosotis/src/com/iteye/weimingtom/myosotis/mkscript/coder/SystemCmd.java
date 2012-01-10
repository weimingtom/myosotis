package com.iteye.weimingtom.myosotis.mkscript.coder;

import com.iteye.weimingtom.myosotis.mkscript.script.CommandBuffer;
import com.iteye.weimingtom.myosotis.mkscript.script.Lexer;
import com.iteye.weimingtom.myosotis.mkscript.script.MakeScript;
import com.iteye.weimingtom.myosotis.mkscript.script.ScriptType;

public class SystemCmd implements ICodeGenerator {

	@Override
	public void exec(Lexer lexer, MakeScript ms) {
		String p = lexer.GetString();
		if (p == null || lexer.GetString() != null) {
			ms.NormalError("syntax error");
			return;
		}
		if ("load".equals(p.toLowerCase())) {
			CommandBuffer.WriteCommand(ScriptType.SYS_LOAD_CMD, ms.command_buffer);
		} else if ("exit".equals(p.toLowerCase())) {
			CommandBuffer.WriteCommand(ScriptType.SYS_EXIT_CMD, ms.command_buffer);
		} else if ("clear".equals(p.toLowerCase())) {
			CommandBuffer.WriteCommand(ScriptType.SYS_EXIT_CMD, ms.command_buffer);
		} else {
			ms.NormalError("syntax error");
		}
	}
}
