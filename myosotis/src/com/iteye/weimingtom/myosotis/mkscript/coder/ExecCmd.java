package com.iteye.weimingtom.myosotis.mkscript.coder;

import com.iteye.weimingtom.myosotis.mkscript.command.ExecCommand;
import com.iteye.weimingtom.myosotis.mkscript.script.CommandBuffer;
import com.iteye.weimingtom.myosotis.mkscript.script.Lexer;
import com.iteye.weimingtom.myosotis.mkscript.script.MakeScript;
import com.iteye.weimingtom.myosotis.mkscript.script.ScriptType;

public class ExecCmd implements ICodeGenerator {

	@Override
	public void exec(Lexer lexer, MakeScript ms) {
		String p = lexer.GetString();
		if (p == null || lexer.GetString() != null) {
			ms.NormalError("syntax error (in exec command)");
			return;
		}
		ExecCommand cp = (ExecCommand)CommandBuffer.NewCommand(ScriptType.EXEC_CMD);
		cp.path_len = (byte)cp.AddMessage(p, 255);
		CommandBuffer.WriteCommand(ScriptType.EXEC_CMD, ms.command_buffer);
	}
}
