package com.iteye.weimingtom.myosotis.mkscript.coder;

import com.iteye.weimingtom.myosotis.mkscript.command.SleepCommand;
import com.iteye.weimingtom.myosotis.mkscript.script.CommandBuffer;
import com.iteye.weimingtom.myosotis.mkscript.script.Lexer;
import com.iteye.weimingtom.myosotis.mkscript.script.MakeScript;
import com.iteye.weimingtom.myosotis.mkscript.script.ScriptType;

public class WaitCmd implements ICodeGenerator {

	@Override
	public void exec(Lexer lexer, MakeScript ms) {
		//var value:int;
		//var isval:Boolean = 
		double value = lexer.GetValue();
		if (Double.isNaN(value) || value <= 0 || lexer.GetString() != null) {
			ms.NormalError("syntax error (in wait command)");
			return;
		}
		SleepCommand cp = (SleepCommand)CommandBuffer.NewCommand(ScriptType.SLEEP_CMD);
		cp.time = (int)value;
		CommandBuffer.WriteCommand(ScriptType.SLEEP_CMD, ms.command_buffer);
	}
}
