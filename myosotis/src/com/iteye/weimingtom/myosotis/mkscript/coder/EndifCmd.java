package com.iteye.weimingtom.myosotis.mkscript.coder;

import com.iteye.weimingtom.myosotis.mkscript.script.Lexer;
import com.iteye.weimingtom.myosotis.mkscript.script.MakeScript;

public class EndifCmd implements ICodeGenerator {

	@Override
	public void exec(Lexer lexer, MakeScript ms) {
		if (lexer.GetString() != null) {
			ms.NormalError("syntax error (in endif command)");
			return;
		}
		if (ms.then_nest.size() == 0) {
			ms.NormalError("\"if\", \"endif\" nest error.");
			return;
		}
		String tmp;
		//FIXME:
		int idx = ms.then_nest.pop();
		tmp = ms.FmtThenLabel(idx);
		ms.AddLabel(tmp);
		if ((idx & 0xffff) != 0) {
			tmp = ms.FmtThenLabel(idx | 0xffff);
			ms.AddLabel(tmp);
		}
	}
}
