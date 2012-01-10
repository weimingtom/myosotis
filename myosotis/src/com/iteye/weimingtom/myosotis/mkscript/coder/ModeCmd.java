package com.iteye.weimingtom.myosotis.mkscript.coder;

import com.iteye.weimingtom.myosotis.mkscript.command.ModeCommand;
import com.iteye.weimingtom.myosotis.mkscript.script.CommandBuffer;
import com.iteye.weimingtom.myosotis.mkscript.script.Lexer;
import com.iteye.weimingtom.myosotis.mkscript.script.MakeScript;
import com.iteye.weimingtom.myosotis.mkscript.script.ScriptType;

public class ModeCmd implements ICodeGenerator {

	@Override
	public void exec(Lexer lexer, MakeScript ms) {
		String p = lexer.GetString();
		if (p == null || lexer.GetString() != null) {
			ms.NormalError("syntax error");
			return;
		}
		if ("system".equals(p.toLowerCase())) {
			ModeCommand cp1 = (ModeCommand)CommandBuffer.NewCommand(ScriptType.MODE_CMD);
			cp1.mode = ScriptType.MODE_SYSTEM;
			CommandBuffer.WriteCommand(ScriptType.MODE_CMD, ms.command_buffer);
		} else if ("scenario".equals(p.toLowerCase())) {
			ModeCommand cp2 = (ModeCommand)CommandBuffer.NewCommand(ScriptType.MODE_CMD);
			cp2.mode = ScriptType.MODE_SCENARIO;
			CommandBuffer.WriteCommand(ScriptType.MODE_CMD, ms.command_buffer);
		} else {
			ms.NormalError("syntax error");
		}
	}
}
