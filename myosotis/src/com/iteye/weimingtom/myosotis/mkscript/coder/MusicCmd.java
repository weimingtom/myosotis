package com.iteye.weimingtom.myosotis.mkscript.coder;

import com.iteye.weimingtom.myosotis.mkscript.command.MusicCommand;
import com.iteye.weimingtom.myosotis.mkscript.script.CommandBuffer;
import com.iteye.weimingtom.myosotis.mkscript.script.Lexer;
import com.iteye.weimingtom.myosotis.mkscript.script.MakeScript;
import com.iteye.weimingtom.myosotis.mkscript.script.ScriptType;

public class MusicCmd implements ICodeGenerator {

	@Override
	public void exec(Lexer lexer, MakeScript ms) {
		//int value;
		//bool isval;
		double value = lexer.GetValue();
		if (Double.isNaN(value) || value <= 0 || lexer.GetString() != null) {
			ms.NormalError("syntax error (in music command)");
			return;
		}
		MusicCommand cp = (MusicCommand)CommandBuffer.NewCommand(ScriptType.MUSIC_CMD);
		cp.number = (int)value;
		CommandBuffer.WriteCommand(ScriptType.MUSIC_CMD, ms.command_buffer);
	}
}
