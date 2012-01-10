package com.iteye.weimingtom.myosotis.mkscript.coder;

import com.iteye.weimingtom.myosotis.mkscript.command.LoadCommand;
import com.iteye.weimingtom.myosotis.mkscript.script.CommandBuffer;
import com.iteye.weimingtom.myosotis.mkscript.script.Lexer;
import com.iteye.weimingtom.myosotis.mkscript.script.MakeScript;
import com.iteye.weimingtom.myosotis.mkscript.script.ScriptType;

public class LoadCmd implements ICodeGenerator {

	@Override
	public void exec(Lexer lexer, MakeScript ms) {
		String p1 = lexer.GetString();
		String p2 = lexer.GetString();
		if (p1 == null || p2 == null || lexer.GetString() != null) {
			ms.NormalError("syntax error (in load command)");
			return;
		}
		LoadCommand cp = (LoadCommand)CommandBuffer.NewCommand(ScriptType.LOAD_CMD);
		cp.flag = (byte)ms.GetPosition(p1);
		cp.path_len = (byte)cp.AddMessage(p2, 255);
		CommandBuffer.WriteCommand(ScriptType.LOAD_CMD, ms.command_buffer);
	}
}
