package com.iteye.weimingtom.myosotis.mkscript.coder;

import com.iteye.weimingtom.myosotis.mkscript.command.CalcValueCommand;
import com.iteye.weimingtom.myosotis.mkscript.command.SetValueCommand;
import com.iteye.weimingtom.myosotis.mkscript.script.CommandBuffer;
import com.iteye.weimingtom.myosotis.mkscript.script.Lexer;
import com.iteye.weimingtom.myosotis.mkscript.script.MakeScript;
import com.iteye.weimingtom.myosotis.mkscript.script.ScriptType;

public class SetCmd implements ICodeGenerator {
	@Override
	public void exec(Lexer lexer, MakeScript ms) {
		String p1 = lexer.GetString();
		String p2 = lexer.GetString();
		double value = lexer.GetValue(); // b3
		String str = lexer.GetString();
		if (p1 == null || p2 == null || Double.isNaN(value) || str != null) {
			System.out.println(str);
			ms.NormalError("syntax error");
			return;
		}
		if("=".equals(p2)) {
			SetValueCommand cp1 = (SetValueCommand)CommandBuffer.NewCommand(ScriptType.SET_VALUE_CMD);
			cp1.value_addr = (short)ms.FindValue(p1);
			cp1.set_value = (int)value;
			CommandBuffer.WriteCommand(ScriptType.SET_VALUE_CMD, ms.command_buffer);
		} else if("+".equals(p2)) { 
			CalcValueCommand cp2 = (CalcValueCommand)CommandBuffer.NewCommand(ScriptType.CALC_VALUE_CMD);
			cp2.value_addr = (short)ms.FindValue(p1);
			cp2.add_value = (int)value;
			CommandBuffer.WriteCommand(ScriptType.CALC_VALUE_CMD, ms.command_buffer);
		} else if("-".equals(p2)) { 
			CalcValueCommand cp3 = (CalcValueCommand) CommandBuffer.NewCommand(ScriptType.CALC_VALUE_CMD);
			cp3.value_addr = (short)ms.FindValue(p1);
			cp3.add_value = (int)-value;
			CommandBuffer.WriteCommand(ScriptType.CALC_VALUE_CMD, ms.command_buffer);
		} else {
			ms.NormalError("syntax error");
		}
	}
}
