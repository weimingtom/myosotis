package com.iteye.weimingtom.myosotis.mkscript.script;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.iteye.weimingtom.myosotis.mkscript.coder.ClearCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.CutInCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.CutOutCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.ElseCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.EndCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.EndifCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.ExecCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.FadeInCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.FadeOutCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.FlashCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.GotoCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.ICodeGenerator;
import com.iteye.weimingtom.myosotis.mkscript.coder.IfCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.LoadCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.MenuCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.ModeCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.MusicCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.SetCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.ShakeCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.SoundCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.StopmCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.SystemCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.TextCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.UpdateCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.WaitCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.WhiteInCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.WhiteOutCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.WipeInCmd;
import com.iteye.weimingtom.myosotis.mkscript.coder.WipeOutCmd;

public class MakeScript {
	public static final boolean DEBUG_MENU = false; //-->menu:
	private static final int BYTES_MAX = 65536;
	private static final boolean DEBUG_NORMAL_ERROR = true;
	private static final boolean DEBUG_FATAL_ERROR = true;
	private static final boolean DEBUG_NOTICE = true;
	private static final boolean DEBUG_FMT_THEN_LABEL = false;
	private static final boolean DEBUG_FMT_ENDIF = false;
	private static final boolean DEBUG_REF_VALUE = true;
	private static final boolean DEBUG_WRITE_LABEL = true;
	private static final int MAX_COMMAND = 65536;
	private static final int MsgNotice = 0;
	private static final int MsgError = 1;
	private static final int MsgFatal = 2;
	private int nerror;
	private int then_index;
	public Stack<Integer> then_nest = new Stack<Integer>();
	public ByteBuffer command_buffer = ByteBuffer.allocate(BYTES_MAX);
	private boolean add_value;
	private ArrayList<String> value_name = new ArrayList<String>();
	private static final int MAX_VALUES	= 100;
	public static final int MAX_TEXTLINE = 4;
	private ArrayList<Label> labels = new ArrayList<Label>();
	private Map<String, ICodeGenerator> cmd_table = new HashMap<String, ICodeGenerator>();
	public FileReader reader;
	
	public boolean IsError() { 
		return nerror != 0; 
	}
	
	public MakeScript() {
		reader = null;
		nerror = 0;
		then_index = 0;
		command_buffer.position(0);
		add_value = false;
		
		cmd_table.put("set", new SetCmd());
		cmd_table.put("calc", new SetCmd());
		cmd_table.put("text", new TextCmd());
		cmd_table.put("goto", new GotoCmd());
		cmd_table.put("if", new IfCmd());
		cmd_table.put("else", new ElseCmd());
		cmd_table.put("endif", new EndifCmd());
		cmd_table.put("menu", new MenuCmd());
		cmd_table.put("exec", new ExecCmd());
		cmd_table.put("load", new LoadCmd());
		cmd_table.put("update", new UpdateCmd());
		cmd_table.put("clear", new ClearCmd());
		cmd_table.put("music", new MusicCmd());
		cmd_table.put("stopm", new StopmCmd());
		cmd_table.put("wait", new WaitCmd());
		cmd_table.put("sound", new SoundCmd());
		cmd_table.put("fadein", new FadeInCmd());
		cmd_table.put("fadeout", new FadeOutCmd());
		cmd_table.put("wipein", new WipeInCmd());
		cmd_table.put("wipeout", new WipeOutCmd());
		cmd_table.put("cutin", new CutInCmd());
		cmd_table.put("cutout", new CutOutCmd());
		cmd_table.put("whitein", new WhiteInCmd());
		cmd_table.put("whiteout", new WhiteOutCmd());
		cmd_table.put("flash", new FlashCmd());
		cmd_table.put("shake", new ShakeCmd());
		cmd_table.put("mode", new ModeCmd());
		cmd_table.put("system", new SystemCmd());
		cmd_table.put("end", new EndCmd());
		
		command_buffer.order(ByteOrder.LITTLE_ENDIAN);
		command_buffer.clear();
	}
	
	public boolean OpenValueTable() {
		FileHandle fin = FileLoader.open(GetValueFile(), "r");	
		if (fin == null) {
			return false;
		}
		String str;
		while ((str = fin.getLineString()) != null) {
			value_name.add(str);
			trace(str);
		}
		return true;
	}
	
	public boolean CloseValueTable() {
		return false;
	}
	
	public int FindValue(String value) {
		int i = value_name.indexOf(value);
		if (i != -1) {
			return i;
		}
		if (value_name.size() >= MAX_VALUES) {	
			NormalError("too meny values.");
			return -1;
		}
		value_name.add(value);
		add_value = true;
		return value_name.size() - 1;
	}

	public String GetValueFile() {
		return "value.txt";
	}
	
	public void OutputMessage(int code, String str) {
		trace(str);
	}
	
	public String GetErrorPrefix() {
		if (reader == null) {
			return "";
		}
		//FXIME:
		return "[[" + reader.GetFileName() + ":" + reader.GetLineNo() + "]]";
	}
	
	public void ErrorMessage(String str) {
		nerror++;
		OutputMessage(MsgError, GetErrorPrefix() + str);
	}

	public void NormalError(String str) {
		if (DEBUG_NORMAL_ERROR) {
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		nerror++;
		OutputMessage(MsgError, "[" + GetErrorPrefix() + "]"+ str);
	}

	public void FatalError(String str) {
		if (DEBUG_FATAL_ERROR){
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		nerror++;
		OutputMessage(MsgFatal, GetErrorPrefix() + str);
	}
	
	public void Notice(String str) {
		if (DEBUG_NOTICE) {
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		OutputMessage(MsgNotice, GetErrorPrefix() + str);
	}

	public String ThenLabel() {
		int idx = then_index++ << 16;	//FIXME:	
		then_nest.push(idx);
		return FmtThenLabel(idx);
	}

	public void AddLabel(String label) {
		for (Label lp : labels) {
			if (lp.label.equals(label)) {
				if (lp.ref == null) {
					// redefine label 
					NormalError("label " + label + 
						" redefinition line " + lp.line + 
						" and " + reader.GetLineNo());
				} else {
					// delete label
					LabelRef chain = lp.ref;
					lp.line = reader.GetLineNo();
					lp.ref = null;
					lp.jmp_addr = command_buffer.position();
					while (chain != null) {
						if (DEBUG_REF_VALUE) {
							trace("===>ref label_ref_address:" + chain.label_ref_address);
						}
						LabelRef next = chain.next;
						if (DEBUG_WRITE_LABEL) {
							trace("<<<< write address: " + chain.label_ref_address + " , value: " + command_buffer.position());
						}
						WriteIntByAddress(chain.label_ref_address, command_buffer.position());
						chain = null;
						chain = next;
					}
					if (DEBUG_REF_VALUE) {
						trace("===>set label_ref:" + label + "\t" +
								lp.line + "\t" +
								command_buffer.position());
					}
				}
				return;
			}
		}
		//new label
		labels.add(new Label(label, reader.GetLineNo(), command_buffer.position(), null)); // ncommand, null));
		if (DEBUG_REF_VALUE) {
			trace("===>new Label:" + label + "\t" + 
					reader.GetLineNo() + "\t" +
					command_buffer.position());
		}
	}
	
	public void FindLabel(String label, int reference_address) {
		if (DEBUG_WRITE_LABEL) {
			trace("<<<< write address: " + reference_address + " , value: " + 0);
		}
		WriteIntByAddress(reference_address, 0);
		for (Label lp : labels) {
			if (lp.label.equals(label)) {
				if (lp.ref != null) {
					lp.ref = new LabelRef(lp.ref, reference_address);
					if (DEBUG_REF_VALUE) {
						trace("===>new LabelRef:" + "\t" + 
								lp.label + "\t" + 
								lp.jmp_addr);
					}
				} else {				
					if (DEBUG_WRITE_LABEL) {
						trace("<<<< write address: " + reference_address + 
								", value: " + lp.jmp_addr + "\t" + lp.label);
					}
					WriteIntByAddress(reference_address, lp.jmp_addr);
				}
				return;
			}
		}
		LabelRef chain = new LabelRef(null, reference_address);
		labels.add(new Label(label, reader.GetLineNo(), 0, chain));
		if (DEBUG_REF_VALUE) {
			trace("===>new Label:" + label + "\t" + reader.GetLineNo());
		}
	}
	
	public void LabelCheck() {
		for (Label lp : labels) {
			if (lp.ref != null) { 
				String label = lp.label;
				switch (label.charAt(0)) {
				case '#':
					ErrorMessage("can't find \"endif\". (line " + lp.line + ")");
					break;
					
				default:
					ErrorMessage("label " + label + " undefined. (line " + lp.line + ")");
					break;
				}
				LabelRef chain = lp.ref;
				while (chain != null) {
					LabelRef next = chain.next;
					chain = null;
					chain = next;
				}
			}
		}
	}
	
	public void SetLabel(Lexer lexer) {
		if (lexer.NumToken() != 1) {
			NormalError("too meny parameter");
			return;
		}
		//FIXME:
		String p = lexer.GetString().substring(1);
		AddLabel(p);
	}
	
	//FIXME:
	public boolean ChkKeyword(String str, String keyword) {
		for (int pos = 0; pos < str.length(); ++pos) {
			if (Character.toLowerCase(str.charAt(pos)) != keyword.charAt(pos)) {
				return false;
			}
		}
		return true;
	}

	public int GetPosition(String str) {
		if (ChkKeyword(str, "center")) {
			return ScriptType.POSITION_CENTER;
		}
		if (ChkKeyword(str, "left")) {
			return ScriptType.POSITION_LEFT;
		}
		if (ChkKeyword(str, "right")) {
			return ScriptType.POSITION_RIGHT;
		}
		if (ChkKeyword(str, "bg") || ChkKeyword(str, "back")) {
			return ScriptType.POSITION_BACK;
		}
		if (ChkKeyword(str, "bgo") || ChkKeyword(str, "backonly")) {
			return ScriptType.POSITION_BACKONLY;
		}
		if (ChkKeyword(str, "overlap")) {
			return ScriptType.POSITION_OVERLAP;
		}
		NormalError("syntax error (position)");
		return ScriptType.POSITION_BACK;
	}

	public int GetUpdateType(String str) {
		if (ChkKeyword(str, "cut") || ChkKeyword(str, "now")) {
			return ScriptType.UPDATE_NOW;
		}
		if (ChkKeyword(str, "overlap")) {
			return ScriptType.UPDATE_OVERLAP;
		}
		if (ChkKeyword(str, "wipe")) {
			return ScriptType.UPDATE_WIPE;
		}
		NormalError("syntax error (update type)");
		return ScriptType.UPDATE_NOW;
	}

	public boolean GetValueOrNumber(ValueOrNumber value, Lexer lexer) {
		int type = lexer.GetType();
		if (type == Lexer.IsString) {
			String p = lexer.GetString();
			value.value = FindValue(p);
			value.isvalue = false;
		} else {
			double result = lexer.GetValue();
			if (Double.isNaN(result)) {	
				return false;
			}
			value.value = (int)result;
			value.isvalue = true;
		}
		return true;
	}

	public byte BoolOp(String op) {
		if ("==".equals(op)) { 
			return ScriptType.IF_TRUE_CMD;
		} else if ("!=".equals(op)) { 
			return ScriptType.IF_FALSE_CMD;
		} else if ("<=".equals(op)) {
			return ScriptType.IF_SMALLER_EQU_CMD;
		} else if (">=".equals(op)) { 
			return ScriptType.IF_BIGGER_EQU_CMD;
		} else if ("<".equals(op)) {
			return ScriptType.IF_SMALLER_CMD;
		} else if (">".equals(op)) {
			return ScriptType.IF_BIGGER_CMD;
		}
		NormalError("syntax error");
		return -1;
	}

	public byte NegBoolOp(String op) {
		if ("==".equals(op)) {
			return ScriptType.IF_FALSE_CMD;
		} else if ("!=".equals(op)) {
			return ScriptType.IF_TRUE_CMD;
		} else if ("<=".equals(op)) {
			return ScriptType.IF_BIGGER_CMD;
		} else if (">=".equals(op)) {
			return ScriptType.IF_SMALLER_CMD;
		} else if ("<".equals(op)) {
			return ScriptType.IF_BIGGER_EQU_CMD;
		} else if (">".equals(op)) {
			return ScriptType.IF_SMALLER_EQU_CMD;
		}
		NormalError("syntax error");
		return -1;
	}
	
	public boolean ChkTermination(String str) {
		return str.charAt(0) == '.';
	}
		
	public ICodeGenerator ParseCommand(Lexer lexer) {
		String command = lexer.GetString(0);
		ICodeGenerator p = cmd_table.get(command);
		if (p != null) {
			return p;
		}
		if (lexer.NumToken() >= 3) {
			String p2 = lexer.GetString(1);
			lexer.GetType(0);
			if ("+".equals(p2) || "-".equals(p2) || "=".equals(p2)) {
				//FIXME:
				return new SetCmd();
			}
		}
		NormalError("syntax error (command syntax)");
		return null;
	}

	public void ParserString(String str) {
		Lexer lexer = new Lexer(str);
		if (lexer.NumToken() == 0) {
			return;
		}
		int	type = lexer.GetType();
		if (type == Lexer.IsLabel) {
			SetLabel(lexer);
		} else {
			ICodeGenerator commandFunc = ParseCommand(lexer);
			if (commandFunc != null) {
				commandFunc.exec(lexer, this);
			}
		}
	}

	public int ReadScript(String name) {
		FileReader Reader = new FileReader(name);
		reader = Reader;
		try {
			OpenValueTable();
			String str;
			while ((str = reader.GetString()) != null) {
				ParserString(str);
			}
			CommandBuffer.WriteCommand(ScriptType.END_CMD, command_buffer);
			LabelCheck();
			if (nerror != 0) {
				Notice("I have " + nerror + " error" + (nerror == 1? "": "s") + " found.");
			}
			if (nerror == 0 && add_value) {
				CloseValueTable();
			}
		} catch (Exception e) {
			FatalError(e.getMessage());
			e.printStackTrace();
		}
		return nerror;
	}
	
	public int WriteScript(String name) {
		return -1;
	}
	
	public String FmtThenLabel(int i) {
		if (DEBUG_FMT_THEN_LABEL) {
			if (i == 0xFFFF) {
				try {
					throw new Error();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		String str = Integer.toHexString(i);
		String zero = "";
		if (str.length() < 8) {
			for (int n = 0; n < 8 - str.length(); n++) {
				zero += "0";
			}
		}
		if(DEBUG_FMT_ENDIF) {
			trace("#endif#" + zero + str);
		}
		return "#endif#" + zero + str;
	}
	
	private void WriteIntByAddress(int pos, int value) {
		int oldpos = command_buffer.position();
		command_buffer.position(pos);
		command_buffer.putInt(value);
		command_buffer.position(oldpos);
	}
	
	public ByteBuffer duplicateBuffer() {
		ByteBuffer bytes = ByteBuffer.allocate(BYTES_MAX);
		bytes.order(ByteOrder.LITTLE_ENDIAN);
		try {
			bytes.put(ScriptType.SCRIPT_MAGIC.getBytes("gbk"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 8 - ScriptType.SCRIPT_MAGIC.length(); ++i) {
			bytes.put((byte)0);
		}
		trace("header magic == " + ScriptType.SCRIPT_MAGIC);	
		bytes.putInt(command_buffer.position());
		trace("header ncommand == " + (command_buffer.position()));
		bytes.put(command_buffer.array(), 0, command_buffer.position());
		return bytes;
	}
	
	public void dumpBuffer() {
		ByteBuffer bytes = duplicateBuffer();
		int num = bytes.position();
		trace("dumpBuffer: " + num);
		String str = "";
		bytes.position(0);
		if (num > 0) {
			for (int i = 0; i < num; i++) {
				int value = bytes.get() & 0xFF;
				if (i % 16 == 0) {
					String address = Integer.toHexString(i);
					for (int k = 0; k < 8 - address.length(); k++) {
						str += "0";
					}
					str += address + ":";
				}
				if(value >= 16) {
					str += Integer.toHexString(value) + " ";
				} else {
					str += "0" + Integer.toHexString(value) + " ";
				}
				if (i % 16 == 15) {
					str += '\n';
				}
			}
		}
		bytes.position(num);
		trace(str);
	}
	
	public void trace(String str) {
		System.out.println(str);
	}
}
