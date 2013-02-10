package com.iteye.weimingtom.myosotis.mkscript.vm;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import com.iteye.weimingtom.myosotis.mkscript.command.CalcValueCommand;
import com.iteye.weimingtom.myosotis.mkscript.command.ClearCommand;
import com.iteye.weimingtom.myosotis.mkscript.command.Command;
import com.iteye.weimingtom.myosotis.mkscript.command.ExecCommand;
import com.iteye.weimingtom.myosotis.mkscript.command.GotoCommand;
import com.iteye.weimingtom.myosotis.mkscript.command.IfCommand;
import com.iteye.weimingtom.myosotis.mkscript.command.LoadCommand;
import com.iteye.weimingtom.myosotis.mkscript.command.MenuCommand;
import com.iteye.weimingtom.myosotis.mkscript.command.MenuItemCommand;
import com.iteye.weimingtom.myosotis.mkscript.command.ModeCommand;
import com.iteye.weimingtom.myosotis.mkscript.command.MusicCommand;
import com.iteye.weimingtom.myosotis.mkscript.command.SetValueCommand;
import com.iteye.weimingtom.myosotis.mkscript.command.SleepCommand;
import com.iteye.weimingtom.myosotis.mkscript.command.SoundCommand;
import com.iteye.weimingtom.myosotis.mkscript.command.TextCommand;
import com.iteye.weimingtom.myosotis.mkscript.command.UpdateCommand;
import com.iteye.weimingtom.myosotis.mkscript.command.WipeinCommand;
import com.iteye.weimingtom.myosotis.mkscript.command.WipeoutCommand;
import com.iteye.weimingtom.myosotis.mkscript.script.CommandBuffer;
import com.iteye.weimingtom.myosotis.mkscript.script.ScriptType;

public class CScriptAction extends CAction {
	private static void trace(String str) {
		System.out.println(str);
	}
	
	private static final boolean NO_BYTEARRY_CLEAR = true;

	public static final int BreakGame = -1;
	public static final int Continue = 0;
	public static final int WaitNextIdle = 1;
	public static final int WaitKeyPressed = 2;
	public static final int WaitTimeOut = 3;
	public static final int WaitMenuDone = 4;
	public static final int WaitWipeDone = 5;
	public static final int WaitWaveDone = 6;
	
	protected static final int FileNoError = 0;
	protected static final int FileCannotOpen = 1;
	protected static final int NotEnoughMemory = 2;
	protected static final int FileCannotRead = 3;
	
	public CParams Params = new CParams();
	protected boolean Pressed;
	protected int MenuSelect;
	protected int MenuAnserAddr;
	protected int PlayMode;
	protected ByteBuffer script_buffer;// = new ByteArray();
	protected ScriptData current = new ScriptData();
	protected int position;
	protected int status;
	
	public CScriptAction() {
		super(true);
	}
	
	public boolean IsSaveLoadOK() {
		return PlayMode != ScriptType.MODE_SYSTEM && 
			(status == WaitKeyPressed || status == WaitMenuDone);
	}
	
	protected Command GetCommand() {
		script_buffer.position(current.commands + this.position);
		trace("CScriptAction::GetCommand() at " + this.position + " in script_buffer" +
			" , length == " + script_buffer.array().length); 
		CommandBuffer.ReadCommand((byte)0, script_buffer);
		Command p = CommandBuffer.GetCommand();
		this.position += p.size;
		trace("p.size == " + p.size + " this.position == " + this.position);
		return p;
	}
	
	protected void GotoCommand2(int next) {
		this.position = next;
	}
	
	protected String GetString(int size) {
		trace("CScriptAction::GetString() " + size);
		if (size == 0)
			return null;
		script_buffer.position(current.commands + this.position);
		//FIXME:
		byte[] bytes_temp = new byte[size];
		script_buffer.get(bytes_temp);
		int bytes_len = 0;
		for (bytes_len = 0; bytes_len < bytes_temp.length; bytes_len++) {
			if (bytes_temp[bytes_len] == 0) {
				break;
			}
		}
		byte[] bytes = new byte[bytes_len];
		System.arraycopy(bytes_temp, 0, bytes, 0, bytes_len);
		String p = null;
		try {
			p = new String(bytes, "gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.position += size;
		return p;
	}
	
	protected int GetValue(int value_addr) {
		return Params.value_tab[value_addr];
	}
	
	protected int GetValue2(int addr, int flag) {
		if (flag != 0)
			return addr;
		return Params.value_tab[addr];
	}
	
	protected void SetValue(int value_addr, int set_value) {
		Params.value_tab[value_addr] = set_value;
	}
	
	protected void CalcValue(int value_addr, int calc_value) {
		Params.value_tab[value_addr] += calc_value;
	}
	
	protected void ClearAllValues() {
		Params.Clear();
	}
	
	@Override 
	public void Initialize(CMainWin parent, int param1, int param2) {
		super.Initialize(parent, param1, param2);
		status = Continue;
		Pressed = false;
		MenuSelect = -1;
		PlayMode = param1;
		Params.Clear();
		script_buffer = null;
	}
	
	@Override 
	public void Pause() {
		switch (status) {
		case WaitMenuDone:
			if (MenuSelect >= 0) {
				Parent.SelectMenu(MenuSelect, false);
				MenuSelect = -1;
			}
			break;
		}
	}
	
	public Point GetCursorPos() {
		//FIXME:
		return null;
	}
	
	@Override 
	public void Resume() {
		switch (status) {
		case WaitMenuDone: {
				Point point = GetCursorPos();
				Parent.ScreenToClient(point);
				MenuSelect = Parent.GetMenuSelect(point);
				if (MenuSelect >= 0)
					Parent.SelectMenu(MenuSelect, true);
			}
			break;
		}
	}
	
	@Override 
	public void LButtonDown(int modKeys, Point point) {
		switch (status) {
			case WaitMenuDone:
				Pressed = true;
				break;
		}
	}
	
	@Override 
	public void LButtonUp(int modKeys, Point point) {
		switch (status) {
		case WaitKeyPressed:
			Parent.HideWaitMark();
			status = Continue;
			break;
		
		case WaitMenuDone:
			if (Pressed) {
				Pressed = false;
				MouseMove(modKeys, point);
				if (MenuSelect >= 0) {
					SetValue(MenuAnserAddr, Parent.GetMenuAnser(MenuSelect));
					Parent.HideMenuWindow();
					status = Continue;
				}
				MenuSelect = -1;
			}
			break;
		}
	}
	
	@Override 
	public void RButtonDown(int modKeys, Point point) {
		switch (status) {
		case WaitKeyPressed:
			Parent.FlipMessageWindow();
			break;
		}
	}
	
	@Override 
	public void MouseMove(int modKeys, Point point) {
		switch (status) {
		case WaitMenuDone: {
				int sel = Parent.GetMenuSelect(point);
				if (sel != MenuSelect) {
					Parent.SelectMenu(MenuSelect, false);
					MenuSelect = sel;
					Parent.SelectMenu(MenuSelect, true);
				}
			}
			break;
		}
	}
	
	@Override 
	public void KeyDown(int key) {
		switch (key) {
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_SPACE:
			switch (status) {
			case WaitKeyPressed:
				Parent.HideWaitMark();
				status = Continue;
				break;
			
			case WaitMenuDone:
				if (MenuSelect >= 0) {
					SetValue(MenuAnserAddr, Parent.GetMenuAnser(MenuSelect));
					Parent.HideMenuWindow();
					status = Continue;
					MenuSelect = -1;
				}
				break;
			}
			break;

		case KeyEvent.VK_ESCAPE:
			switch (status) {
			case WaitKeyPressed:
				Parent.FlipMessageWindow();
				break;
			}
			break;

		case KeyEvent.VK_UP:
			if (status == WaitMenuDone) {	
				Parent.SelectMenu(MenuSelect, false);
				MenuSelect--;
				if (MenuSelect < 0)
					MenuSelect = Parent.GetMenuItemCount() - 1;
				Parent.SelectMenu(MenuSelect, true);
			}
			break;
			
		case KeyEvent.VK_DOWN:
			if (status == WaitMenuDone) {	
				Parent.SelectMenu(MenuSelect, false);
				MenuSelect++;
				if (MenuSelect >= Parent.GetMenuItemCount())
					MenuSelect = 0;
				Parent.SelectMenu(MenuSelect, true);
			}
			break;
		}
	}
	
	@Override 
	public boolean IdleAction() {
		if (status == Continue) {
			do {
				status = Step();				
			} while (status == Continue);
			if (status == BreakGame) {			
				Abort();
			} else if (status == WaitNextIdle) {	
				status = Continue;
				return true;
			} else if (status == WaitWipeDone) {	
				return true;
			}
		} else {
			//trace("NOTE:CScriptAction::IdleAction status != Continue");
		}
		return false;
	}
	
	@Override 
	public void TimedOut(int timerId) {
		switch (timerId) {
		case CMainWin.TimerSleep:
			if (status == WaitTimeOut)
				status = Continue;
			break;
		}
	}
	
	@Override 
	public void WipeDone() {
		if (status == WaitWipeDone)
			status = Continue;
	}
	
	@Override 
	public void WaveDone() {
		if (status == WaitWaveDone)
			status = Continue;
	}
	
	public void Abort() {
		if (status == WaitMenuDone)
			Parent.HideMenuWindow();
		Parent.HideMessageWindow();
		
		status = BreakGame;
		if (NO_BYTEARRY_CLEAR) {
			script_buffer.position(0);
			script_buffer.clear();
		} else {
			//FIXME:
		}
		Parent.SetAction(CMainWin.ActionScriptDone);
	}
	
	public int LoadFile(String name) {
		trace("CScriptAction::LoadFile " + name);
		String path = CConfig.SCRIPTPATH + name + ".scr";
		script_buffer = null;
		CFile file = new CFile(path);
		if (!file.IsOk())
			return FileCannotOpen;
		int length = file.GetFileSize();
		script_buffer = ByteBuffer.allocate(length); //FIXME:
		script_buffer.order(ByteOrder.LITTLE_ENDIAN);
		if (file.Read(script_buffer, length) != length)
			return FileCannotRead;
		return FileNoError;
	}
	
	public boolean Load(String name) {
		if (name.length() > 16) {  //FIXME:
			Params.last_script = name.substring(0, 16);
		} else {
			Params.last_script = name;
		}
		trace("Params.last_script = " + Params.last_script);		
		switch (LoadFile(name)) {
		case FileCannotOpen:
			Parent.MessageBox("脚本 [" + name + "] 无法开启。");
			return false;
		
		case NotEnoughMemory:
			Parent.MessageBox("内存不足, 无法读取脚本[" + name + "]。");
			return false;
		
		case FileCannotRead:
			Parent.MessageBox("无法读取脚本。[" + name + "]");
			return false;
		}
		trace("CScriptAction::Load " + " " + "LoadFile(\"" + name + "\") success");
		byte[] bytes = new byte[8];
		script_buffer.position(0); // FIXME:
		script_buffer.get(bytes);
		//trace("CScriptAction::Load() magic[8] == " + Arrays.toString(bytes));
		String magic = null;
		try {
			magic = new String(bytes, "gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int ncommand = script_buffer.getInt();
		trace("magic == " + magic);
		trace("ncommand == " + ncommand);
		if (!ScriptType.SCRIPT_MAGIC.equals(magic)) {
			Parent.MessageBox("没有脚本数据。[" + name + "]");
			return false;
		}
		trace("CScriptAction::Load " + "magic correct"); 
		current.ncommand = ncommand;
		current.commands = script_buffer.position();
		this.position = 0;
		return true;
	}
	
	public boolean Setup(CParams param) {
		Params = param;
		this.position = Params.script_pos;
		if (current.ncommand < this.position) {
			Parent.MessageBox("读取的数据异常。");
			return false;
		}
		if (param.last_bgm != 0)
			Parent.StartMusic(param.last_bgm);
		switch (param.show_flag) {
		case CParams.SHOWCG_IMAGE:
			//FIXME: .length() > 0
			if (param.last_bg.length() > 0)
				LoadGraphic(param.last_bg, ScriptType.POSITION_BACK);
			if (param.last_overlap.length() > 0) {
				LoadGraphic(param.last_overlap, ScriptType.POSITION_OVERLAP);
			} else if (param.last_center.length() > 0) {
				LoadGraphic(param.last_center, ScriptType.POSITION_CENTER);
			} else {
				if (param.last_left.length() > 0)
					LoadGraphic(param.last_left, ScriptType.POSITION_LEFT);
				if (param.last_right.length() > 0)
					LoadGraphic(param.last_right, ScriptType.POSITION_RIGHT);
			}
			status = WipeIn();
			break;

		case CParams.SHOWCG_BLACKNESS:
			CutOut();
			status = Continue;
			break;
		
		case CParams.SHOWCG_WHITENESS:
			CutOut(true);
			status = Continue;
			break;
		}
		return true;
	}
	
	public void ASSERT() {
		//FIXME:
	}
	
	public int Step() {
		assert script_buffer != null;
		int last_pos = this.position;
		Command cmd = GetCommand();
		switch (cmd.type) {
		case ScriptType.SET_VALUE_CMD:
			SetValue(((SetValueCommand)cmd).value_addr, ((SetValueCommand)cmd).set_value);
			break;
		
		case ScriptType.CALC_VALUE_CMD:
			CalcValue(((CalcValueCommand)cmd).value_addr, ((CalcValueCommand)cmd).add_value);
			break;

		case ScriptType.TEXT_CMD:
			Params.script_pos = last_pos;
			Parent.WriteMessage(GetString(((TextCommand)cmd).msg_len));
			return WaitKeyPressed;
		
		case ScriptType.CLEAR_TEXT_CMD:
			Parent.ClearMessage();
			return WaitNextIdle;
		
		case ScriptType.MUSIC_CMD:
			Params.last_bgm = ((MusicCommand)cmd).number;
			Parent.StartMusic(((MusicCommand)cmd).number);
			break;
		
		case ScriptType.STOPM_CMD:
			Params.last_bgm = 0;
			Parent.StopMusic();
			break;
		
		case ScriptType.SOUND_CMD:
			if (Parent.StartWave(GetString(((SoundCommand)cmd).path_len)))
				return WaitWaveDone;
			return Continue;
		
		case ScriptType.SLEEP_CMD:
			Parent.SetTimer(CMainWin.TimerSleep, ((SleepCommand)cmd).time * 1000);
			return WaitTimeOut;
		
		case ScriptType.GOTO_CMD:
			GotoCommand2(((GotoCommand)cmd).goto_label);
			break;
		
		case ScriptType.IF_TRUE_CMD:
			if (GetValue2(((IfCommand)cmd).value1, ((IfCommand)cmd).flag & 1) == 
				GetValue2(((IfCommand)cmd).value2, ((IfCommand)cmd).flag & 2))
				GotoCommand2(((IfCommand)cmd).goto_label);
			break;
		
		case ScriptType.IF_FALSE_CMD:
			if (GetValue2(((IfCommand)cmd).value1, ((IfCommand)cmd).flag & 1) != 
				GetValue2(((IfCommand)cmd).value2, ((IfCommand)cmd).flag & 2))
				GotoCommand2(((IfCommand)cmd).goto_label);
			break;
			
		case ScriptType.IF_BIGGER_CMD:
			if (GetValue2(((IfCommand)cmd).value1, ((IfCommand)cmd).flag & 1) > 
				GetValue2(((IfCommand)cmd).value2, ((IfCommand)cmd).flag & 2))
				GotoCommand2(((IfCommand)cmd).goto_label);
			break;
		
		case ScriptType.IF_SMALLER_CMD:
			if (GetValue2(((IfCommand)cmd).value1, ((IfCommand)cmd).flag & 1) < 
				GetValue2(((IfCommand)cmd).value2, ((IfCommand)cmd).flag & 2))
				GotoCommand2(((IfCommand)cmd).goto_label);
			break;
		
		case ScriptType.IF_BIGGER_EQU_CMD:
			if (GetValue2(((IfCommand)cmd).value1, ((IfCommand)cmd).flag & 1) >= 
				GetValue2(((IfCommand)cmd).value2, ((IfCommand)cmd).flag & 2))
				GotoCommand2(((IfCommand)cmd).goto_label);
			break;
		
		case ScriptType.IF_SMALLER_EQU_CMD:
			if (GetValue2(((IfCommand)cmd).value1, ((IfCommand)cmd).flag & 1) <= 
				GetValue2(((IfCommand)cmd).value2, ((IfCommand)cmd).flag & 2))
				GotoCommand2(((IfCommand)cmd).goto_label);
			break;
			
		case ScriptType.MENU_INIT_CMD:
			Params.script_pos = last_pos;
			Parent.ClearMenuItemCount();
			break;
		
		case ScriptType.MENU_ITEM_CMD:
			Parent.SetMenuItem(GetString(((MenuItemCommand)cmd).label_len), ((MenuItemCommand)cmd).number);
			break;
		
		case ScriptType.MENU_CMD:
			MenuSelect = -1;
			MenuAnserAddr = ((MenuCommand)cmd).value_addr;
			Parent.OpenMenu();
			return WaitMenuDone;
		
		case ScriptType.EXEC_CMD:
			if (!Load(GetString(((ExecCommand)cmd).path_len)))
				return BreakGame;
			PlayMode = ScriptType.MODE_SCENARIO;
			break;
		
		case ScriptType.LOAD_CMD:
			return LoadGraphic(GetString(((LoadCommand)cmd).path_len), ((LoadCommand)cmd).flag);
		
		case ScriptType.UPDATE_CMD:
			return UpdateImage(((UpdateCommand)cmd).flag);
		
		case ScriptType.CLEAR_CMD:
			return Clear(((ClearCommand)cmd).pos);
		
		case ScriptType.CUTIN_CMD:
			return CutIn();
		
		case ScriptType.CUTOUT_CMD:
			return CutOut();
		
		case ScriptType.FADEIN_CMD:
			return FadeIn();
		
		case ScriptType.FADEOUT_CMD:
			return FadeOut();
		
		case ScriptType.WIPEIN_CMD:
			return WipeIn(((WipeinCommand)cmd).pattern);
		
		case ScriptType.WIPEOUT_CMD:
			return WipeOut(((WipeoutCommand)cmd).pattern);
		
		case ScriptType.WHITEIN_CMD:
			return WhiteIn();
		
		case ScriptType.WHITEOUT_CMD:
			return WhiteOut();
		
		case ScriptType.SHAKE_CMD:
			Parent.Shake();
			return WaitWipeDone;
			
		case ScriptType.FLASH_CMD:
			Parent.Flash();
			return WaitWipeDone;
		
		case ScriptType.MODE_CMD:
			PlayMode = ((ModeCommand)cmd).mode;
			break;
		
		case ScriptType.SYS_LOAD_CMD:
			Parent.SetAction(CMainWin.ActionGameLoad);
			return WaitNextIdle;
		
		case ScriptType.SYS_EXIT_CMD:
			Parent.SendMessage(CWindow.WM_CLOSE);
			return WaitNextIdle;
		
		case ScriptType.SYS_CLEAR_CMD:
			ClearAllValues();
			break;
		
		case ScriptType.END_CMD:
			return BreakGame;
		
		default:
			assert false;
			break;
		}
		return Continue;
	}
	
	public int LoadGraphic(String file, int pos) {
		boolean result = false;
		switch (pos) {
		case ScriptType.POSITION_BACK:
			trace("CScriptAction::LoadGraphic POSITION_BACK :" + file);
			Params.ClearOverlapCG();
			Parent.ClearOverlap();
			// no break
		
		case ScriptType.POSITION_BACKONLY:
			trace("CScriptAction::LoadGraphic POSITION_BACKONLY :" + file);
			Params.SetBackCG(file);
			result = Parent.LoadImageBack(file);
			break;
		
		case ScriptType.POSITION_CENTER:
			trace("CScriptAction::LoadGraphic POSITION_CENTER :" + file);
			Params.SetCenterCG(file);
			result = Parent.LoadImageCenter(file);
			break;
		
		case ScriptType.POSITION_LEFT:
			trace("CScriptAction::LoadGraphic POSITION_LEFT :" + file);
			Params.SetLeftCG(file);
			result = Parent.LoadImageLeft(file);
			break;
		
		case ScriptType.POSITION_RIGHT:
			trace("CScriptAction::LoadGraphic POSITION_RIGHT :" + file);
			Params.SetRightCG(file);
			result = Parent.LoadImageRight(file);
			break;
		
		case ScriptType.POSITION_OVERLAP:
			trace("CScriptAction::LoadGraphic POSITION_OVERLAP :" + file);
			Params.SetOverlapCG(file);
			result = Parent.LoadImageOverlap(file);
			break;
		}
		if (!result) {
			Parent.MessageBox("文件无法读取。[" + file + "]");
			if (PlayMode == ScriptType.MODE_SYSTEM)
				Parent.SendMessage(CWindow.WM_CLOSE);
			return BreakGame;
		}
		return Continue;
	}
	
	public int Clear(int pos) {
		switch (pos) {
		case ScriptType.POSITION_BACK:
			Params.ClearOverlapCG();
			Parent.ClearOverlap();
			// no break
		
		case ScriptType.POSITION_BACKONLY:
			Params.ClearBackCG();
			Parent.ClearBack();
			break;
		
		case ScriptType.POSITION_CENTER:
			Params.ClearCenterCG();
			Parent.ClearCenter();
			break;
		
		case ScriptType.POSITION_LEFT:
			Params.ClearLeftCG();
			Parent.ClearLeft();
			break;
		
		case ScriptType.POSITION_RIGHT:
			Params.ClearRightCG();
			Parent.ClearRight();
			break;
		
		case ScriptType.POSITION_OVERLAP:
			Params.ClearOverlapCG();
			Parent.ClearOverlap();
			break;
		}
		return Continue;
	}
	
	public int UpdateImage(int flag) {
		Params.SetShowFlag();
		Rectangle rect = Parent.GetInvalidRect();
		if (rect.isEmpty())
			return Continue;
		switch (flag) {
		case ScriptType.UPDATE_NOW:
			trace("CScriptAction::UpdateImage() UPDATE_NOW " + rect); 
			Parent.CutIn(rect);
			return WaitNextIdle;
			
		case ScriptType.UPDATE_OVERLAP:
			//FIXME:
			trace("CScriptAction::UpdateImage() UPDATE_OVERLAP " + rect); 
			Parent.MixFade(rect);
			break;
			//Parent.CutIn(rect);
			//return WaitNextIdle;
			
		case ScriptType.UPDATE_WIPE:
			trace("CScriptAction::UpdateImage() UPDATE_WIPE " + rect); 
			Parent.WipeIn(rect);
			break;
		}
		return WaitWipeDone;
	}
	
	public int FadeIn() {
		Params.SetShowFlag();
		Parent.FadeIn();
		return WaitWipeDone;
	}
	
	public int FadeOut() {
		Params.ResetShowFlag();
		Parent.FadeOut();
		return WaitWipeDone;
	}
	
	public int CutIn() {
		Params.SetShowFlag();
		Parent.CutIn2();
		return WaitNextIdle;
	}

	public int CutOut() {
		return CutOut(false);
	}
	
	public int CutOut(boolean white) {
		Params.ResetShowFlag(white);
		Parent.CutOut(white);
		return WaitNextIdle;
	}

	public int WipeIn() {
		return WipeIn(1);
	}
	
	public int WipeIn(int pattern) {
		Params.SetShowFlag();
		Parent.WipeIn2(pattern);
		return WaitWipeDone;
	}

	public int WipeOut(int pattern) {
		Params.ResetShowFlag();
		Parent.WipeOut(pattern);
		return WaitWipeDone;
	}
	
	public int WhiteIn() {
		Params.SetShowFlag();
		Parent.WhiteIn();
		return WaitWipeDone;
	}

	public int WhiteOut() {
		Params.ResetShowFlag(true);
		Parent.WhiteOut();
		return WaitWipeDone;
	}
}
