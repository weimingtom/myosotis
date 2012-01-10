package com.iteye.weimingtom.myosotis.mkscript.coder;

import com.iteye.weimingtom.myosotis.mkscript.script.Lexer;
import com.iteye.weimingtom.myosotis.mkscript.script.MakeScript;

public interface ICodeGenerator {
	public void exec(Lexer lexer, MakeScript ms);
}
