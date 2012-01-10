package com.iteye.weimingtom.myosotis.mkscript.coder;

import com.iteye.weimingtom.myosotis.mkscript.command.GotoCommand;
import com.iteye.weimingtom.myosotis.mkscript.command.IfCommand;
import com.iteye.weimingtom.myosotis.mkscript.script.CommandBuffer;
import com.iteye.weimingtom.myosotis.mkscript.script.Lexer;
import com.iteye.weimingtom.myosotis.mkscript.script.MakeScript;
import com.iteye.weimingtom.myosotis.mkscript.script.ScriptType;
import com.iteye.weimingtom.myosotis.mkscript.script.ValueOrNumber;

public class ElseCmd implements ICodeGenerator {

	@Override
	public void exec(Lexer lexer, MakeScript ms) {
		if (ms.then_nest.size() == 0) {
			ms.NormalError("\"if\", \"else\" nest error.");
			return;
		}
		//FIXME:
		int idx = ms.then_nest.pop();
		String else_label = ms.FmtThenLabel(idx);
		GotoCommand cp1 = (GotoCommand)CommandBuffer.NewCommand(ScriptType.GOTO_CMD);
		String goto_label;
		ms.then_nest.push(idx + 1);
		goto_label = ms.FmtThenLabel(idx | 0xffff);
		ms.FindLabel(goto_label, ms.command_buffer.position() + 4);
		CommandBuffer.WriteCommand(ScriptType.GOTO_CMD, ms.command_buffer);
		ms.AddLabel(else_label);
		String p = lexer.GetString();
		if (p == null) {
			return;
		} else if ("if".equals(p.toLowerCase())) {
			ValueOrNumber val1 = new ValueOrNumber();
			ValueOrNumber val2 = new ValueOrNumber();
			boolean b1 = ms.GetValueOrNumber(val1, lexer);
			String op = lexer.GetString();
			boolean b2 = ms.GetValueOrNumber(val2, lexer);
			if (!b1 || !b2 || op == null) {
				ms.NormalError("syntax error (in else if command)");
				return;
			}
			IfCommand cp2 = (IfCommand)CommandBuffer.NewCommand(ScriptType.IF_TRUE_CMD);
			cp2.type = ms.NegBoolOp(op);
			cp2.flag = 0;
			if (val1.isvalue) {
				cp2.flag |= 1;
			}
			cp2.value1 = val1.value;
			if (val2.isvalue) {
				cp2.flag |= 2;
			}
			cp2.value2 = val2.value;
			String p2 = lexer.GetString();
			if (p2 == null || !"then".equals(p2.toLowerCase()) ) {
				ms.NormalError("syntax error");
				return;
			}
			String label = ms.FmtThenLabel(idx + 1);
			ms.FindLabel(label, ms.command_buffer.position() + 12); //p2.goto_label);
			CommandBuffer.WriteCommand(ScriptType.IF_TRUE_CMD, ms.command_buffer);
		} else {
			ms.NormalError("syntax error (in else command)");
			return;
		}
	}
}

