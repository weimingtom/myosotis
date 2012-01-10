package com.iteye.weimingtom.myosotis.mkscript.coder;

import com.iteye.weimingtom.myosotis.mkscript.command.MenuCommand;
import com.iteye.weimingtom.myosotis.mkscript.command.MenuItemCommand;
import com.iteye.weimingtom.myosotis.mkscript.script.CommandBuffer;
import com.iteye.weimingtom.myosotis.mkscript.script.Lexer;
import com.iteye.weimingtom.myosotis.mkscript.script.MakeScript;
import com.iteye.weimingtom.myosotis.mkscript.script.ScriptType;

public class MenuCmd implements ICodeGenerator {

	@Override
	public void exec(Lexer lexer, MakeScript ms) {
		String p = lexer.GetString();
		if (p == null || lexer.GetString() != null) {
			ms.NormalError("syntax error (in menu command)");
			return;
		}
		int	value_addr = ms.FindValue(p);
		CommandBuffer.WriteCommand(ScriptType.MENU_INIT_CMD, ms.command_buffer);
		String str;
		for (int no = 0; (str = ms.reader.GetString()) != null; no++) {
			if (MakeScript.DEBUG_MENU) {
				ms.trace("-->menu:" + str + "length=" + str.length());
			}
			if (str.toLowerCase().equals("end")) {
				break;
			}
			MenuItemCommand ip = (MenuItemCommand)CommandBuffer.NewCommand(ScriptType.MENU_ITEM_CMD);
			ip.label_len = (byte)ip.AddMessage(str, 255);
			ip.number = (byte)(no + 1);
			CommandBuffer.WriteCommand(ScriptType.MENU_ITEM_CMD, ms.command_buffer);
		}
		MenuCommand op = (MenuCommand)CommandBuffer.NewCommand(ScriptType.MENU_CMD);
		op.value_addr = (short)value_addr;
		CommandBuffer.WriteCommand(ScriptType.MENU_CMD, ms.command_buffer);
	}
}

