package com.iteye.weimingtom.myosotis.mkscript.coder;

import com.iteye.weimingtom.myosotis.mkscript.command.TextCommand;
import com.iteye.weimingtom.myosotis.mkscript.script.CommandBuffer;
import com.iteye.weimingtom.myosotis.mkscript.script.Lexer;
import com.iteye.weimingtom.myosotis.mkscript.script.MakeScript;
import com.iteye.weimingtom.myosotis.mkscript.script.ScriptType;

public class TextCmd implements ICodeGenerator {

	@Override
	public void exec(Lexer lexer, MakeScript ms) {
		if (lexer.GetString() != null) {
			ms.NormalError("syntax error (in text command)");
			return;
		}
		TextCommand cp = (TextCommand)CommandBuffer.NewCommand(ScriptType.TEXT_CMD);
		String work = "";
		for (int i = 0; ; i++) {
			String str;
			if ((str = ms.reader.GetString()) == null) {
				ms.NormalError("syntax error (text syntax)");
				break;
			}
			if (ms.ChkTermination(str)) {
				break;
			}
			work += str;
			work += '\n';
			if (i >= MakeScript.MAX_TEXTLINE) {
				ms.NormalError("text line overflow");
				break;
			}
		}
		cp.msg_len = (byte)cp.AddMessage(work, 255);
		CommandBuffer.WriteCommand(ScriptType.TEXT_CMD, ms.command_buffer);
	}
}
