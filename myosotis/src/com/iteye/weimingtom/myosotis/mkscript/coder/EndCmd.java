package com.iteye.weimingtom.myosotis.mkscript.coder;

import com.iteye.weimingtom.myosotis.mkscript.script.CommandBuffer;
import com.iteye.weimingtom.myosotis.mkscript.script.Lexer;
import com.iteye.weimingtom.myosotis.mkscript.script.MakeScript;
import com.iteye.weimingtom.myosotis.mkscript.script.ScriptType;

public class EndCmd implements ICodeGenerator {

	@Override
	public void exec(Lexer lexer, MakeScript ms) {
		if (lexer.GetString() != null) {
			ms.NormalError("syntax error");
			return;
		}
		CommandBuffer.WriteCommand(ScriptType.END_CMD, ms.command_buffer);
	}
}
