package com.iteye.weimingtom.myosotis.mkscript.coder;

import com.iteye.weimingtom.myosotis.mkscript.command.WipeinCommand;
import com.iteye.weimingtom.myosotis.mkscript.script.CommandBuffer;
import com.iteye.weimingtom.myosotis.mkscript.script.Lexer;
import com.iteye.weimingtom.myosotis.mkscript.script.MakeScript;
import com.iteye.weimingtom.myosotis.mkscript.script.ScriptType;

public class WipeInCmd implements ICodeGenerator {

	@Override
	public void exec(Lexer lexer, MakeScript ms) {
		//var value:int;
		//var isval:Boolean
		double value = lexer.GetValue();
		if (Double.isNaN(value) || value <= 0 || value > 2 || lexer.GetString() != null) {
			ms.NormalError("syntax error (in wipein command)");
			return;
		}
		WipeinCommand cp = (WipeinCommand)CommandBuffer.NewCommand(ScriptType.WIPEIN_CMD);
		cp.pattern = (byte)value;
		CommandBuffer.WriteCommand(ScriptType.WIPEIN_CMD, ms.command_buffer);
	}
}
