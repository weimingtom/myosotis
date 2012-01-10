package com.iteye.weimingtom.myosotis.mkscript.coder;

import com.iteye.weimingtom.myosotis.mkscript.command.IfCommand;
import com.iteye.weimingtom.myosotis.mkscript.script.CommandBuffer;
import com.iteye.weimingtom.myosotis.mkscript.script.Lexer;
import com.iteye.weimingtom.myosotis.mkscript.script.MakeScript;
import com.iteye.weimingtom.myosotis.mkscript.script.ScriptType;
import com.iteye.weimingtom.myosotis.mkscript.script.ValueOrNumber;

public class IfCmd implements ICodeGenerator {

	@Override
	public void exec(Lexer lexer, MakeScript ms) {
		ValueOrNumber val1 = new ValueOrNumber();
		ValueOrNumber val2 = new ValueOrNumber();
		boolean b1 = ms.GetValueOrNumber(val1, lexer);
		String op = lexer.GetString();
		boolean b2 = ms.GetValueOrNumber(val2, lexer);
		if (!b1 || !b2 || op == null) {
			ms.NormalError("syntax error (in if command)");
			return;
		}
		IfCommand cp = (IfCommand)CommandBuffer.NewCommand(ScriptType.IF_TRUE_CMD);
		cp.flag = 0;
		if (val1.isvalue) {
			cp.flag |= 1;
		}
		cp.value1 = val1.value;
		if (val2.isvalue) {
			cp.flag |= 2;
		}
		cp.value2 = val2.value;
		String p = lexer.GetString();
		String label = null;
		if (p != null) {
			if ("goto".equals(p.toLowerCase())) { 	
				// if-goto
				label = lexer.GetString();
				cp.type = ms.BoolOp(op);
			} else if ("then".equals(p.toLowerCase())) {
				// if-then
				label = ms.ThenLabel();
				cp.type = ms.NegBoolOp(op);
			}
		}
		if (label == null || lexer.GetString() != null) {
			ms.NormalError("syntax error");
			return;
		}
		ms.FindLabel(label, ms.command_buffer.position() + 12); // cp.goto_label);
		CommandBuffer.WriteCommand(ScriptType.IF_TRUE_CMD, ms.command_buffer);
	}
}
