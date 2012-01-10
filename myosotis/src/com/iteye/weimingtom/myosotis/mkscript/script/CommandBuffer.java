package com.iteye.weimingtom.myosotis.mkscript.script;

import java.nio.ByteBuffer;

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

public class CommandBuffer {
	private static final boolean DEBUG_WRITE_COMMAND = true;
	
	private static SetValueCommand setValueCmd = new SetValueCommand(ScriptType.SET_VALUE_CMD);
	private static CalcValueCommand calcValueCmd = new CalcValueCommand(ScriptType.CALC_VALUE_CMD);
	private static TextCommand textCmd = new TextCommand(ScriptType.TEXT_CMD);
	private static GotoCommand gotoCmd = new GotoCommand(ScriptType.GOTO_CMD);
	private static IfCommand ifCmd = new IfCommand((byte)0);
	private static MenuItemCommand menuItemCmd = new MenuItemCommand(ScriptType.MENU_ITEM_CMD);
	private static MenuCommand menuCmd = new MenuCommand(ScriptType.MENU_CMD);
	private static ExecCommand execCmd = new ExecCommand(ScriptType.EXEC_CMD);
	private static LoadCommand loadCmd = new LoadCommand(ScriptType.LOAD_CMD);
	private static UpdateCommand updateCmd = new UpdateCommand(ScriptType.UPDATE_CMD);
	private static ClearCommand clearCmd = new ClearCommand(ScriptType.CLEAR_CMD);
	private static MusicCommand musicCmd = new MusicCommand(ScriptType.MUSIC_CMD);
	private static SoundCommand soundCmd = new SoundCommand(ScriptType.SOUND_CMD);
	private static SleepCommand sleepCmd = new SleepCommand(ScriptType.SLEEP_CMD);
	private static WipeinCommand wipeinCmd = new WipeinCommand(ScriptType.WIPEIN_CMD);
	private static WipeoutCommand wipeoutCmd = new WipeoutCommand(ScriptType.WIPEOUT_CMD);
	private static ModeCommand modeCmd = new ModeCommand(ScriptType.MODE_CMD);
	
	private static Command normalCmd = new Command((byte)0);
	private static Command current_read_cmd;
	
	public CommandBuffer() {
		
	}
	
	public static Command GetCommand() {
		return current_read_cmd;
	}
	
	public static Command NewCommand(int id) {
		switch(id) {
			case ScriptType.SET_VALUE_CMD:
				return setValueCmd;
			
			case ScriptType.CALC_VALUE_CMD:
				return calcValueCmd;
			
			case ScriptType.TEXT_CMD:
				return textCmd;
		
			case ScriptType.CLEAR_TEXT_CMD:
				return clearCmd;
			
			case ScriptType.GOTO_CMD:
				return gotoCmd;
				
			case ScriptType.IF_TRUE_CMD:
			case ScriptType.IF_FALSE_CMD:
			case ScriptType.IF_BIGGER_CMD:
			case ScriptType.IF_SMALLER_CMD:
			case ScriptType.IF_BIGGER_EQU_CMD:
			case ScriptType.IF_SMALLER_EQU_CMD:
				return ifCmd;
			
			case ScriptType.MENU_ITEM_CMD:
				return menuItemCmd;
				
			case ScriptType.MENU_CMD:
				return menuCmd;
			
			case ScriptType.EXEC_CMD:
				return execCmd;
				
			case ScriptType.LOAD_CMD:
				return loadCmd;
				
			case ScriptType.UPDATE_CMD:
				return updateCmd;
			
			case ScriptType.CLEAR_CMD:
				return clearCmd;
			
			case ScriptType.MUSIC_CMD:
				return musicCmd;
				
			case ScriptType.SOUND_CMD:
				return soundCmd;
				
			case ScriptType.SLEEP_CMD:
				return sleepCmd;
				
			case ScriptType.WIPEIN_CMD:
				return wipeinCmd;
				
			case ScriptType.WIPEOUT_CMD:
				return wipeoutCmd;
			
			case ScriptType.MODE_CMD:
				return modeCmd;
				
			default:
				break;
		}
		return null;
	}
	
	public static int WriteCommand(byte id, ByteBuffer bytes) {
		return ReadWriteCommand(id, bytes, true);
	}
	
	public static int ReadCommand(byte id, ByteBuffer bytes) {
		return ReadWriteCommand(id, bytes, false);
	}
	
	private static int ReadWriteCommand(byte id, ByteBuffer bytes, boolean isWrite) {
		int nBytes = 0;
		if (isWrite == false) {
			id = bytes.get();
			nBytes = bytes.get();
			current_read_cmd = null;
		}
		switch(id) {
			case ScriptType.SET_VALUE_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<SET_VALUE_CMD : " + setValueCmd);
				}
				if (isWrite) {
					bytes.put(setValueCmd.type); //1
					bytes.put((byte)8); //2
					bytes.putShort(setValueCmd.value_addr); //4
					bytes.putInt(setValueCmd.set_value); //8
				} else {
					setValueCmd.type = id;
					setValueCmd.value_addr = bytes.getShort();
					setValueCmd.set_value = bytes.getInt();
					setValueCmd.size = nBytes;
					current_read_cmd = setValueCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.CALC_VALUE_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<CALC_VALUE_CMD : " + calcValueCmd);
				}
				if (isWrite) {
					bytes.put(calcValueCmd.type); //1
					bytes.put((byte)8); //2
					bytes.putShort(calcValueCmd.value_addr); //4
					bytes.putInt(calcValueCmd.add_value); //8
				} else {
					calcValueCmd.type = id;
					calcValueCmd.value_addr = bytes.getShort();
					calcValueCmd.add_value = bytes.getInt();
					calcValueCmd.size = nBytes;
					current_read_cmd = calcValueCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.TEXT_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<TEXT_CMD : " + textCmd);
				}
				if (isWrite) {
					bytes.put(textCmd.type); //1
					bytes.put((byte)4); //2
					bytes.putShort(textCmd.msg_len); //4
					bytes.put(textCmd.bytes.toByteArray());
				} else {
					textCmd.msg_len = bytes.getShort();
					textCmd.size = nBytes;
					current_read_cmd = textCmd;
					return nBytes;
				}
				break;
		
			case ScriptType.CLEAR_TEXT_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<CLEAR_TEXT_CMD : ");
				}
				if (isWrite) {
					bytes.put(ScriptType.CLEAR_TEXT_CMD); //1
					bytes.put((byte)4); //2
					bytes.put((byte)0); //3 
					bytes.put((byte)0); //4
				} else {
					bytes.position(bytes.position() + 2); //TODO:
					normalCmd.size = nBytes;
					current_read_cmd = normalCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.GOTO_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<GOTO_CMD : " + gotoCmd);
				}
				if (isWrite) {
					bytes.put(gotoCmd.type); //1
					bytes.put((byte)8); //2
					bytes.put((byte)0); //3
					bytes.put((byte)0); //4
					bytes.position(bytes.position() + 4); //8 //label position: +4 //FIXME:
				} else {
					bytes.position(bytes.position() + 2);
					gotoCmd.goto_label = bytes.getInt();
					gotoCmd.size = nBytes;
					current_read_cmd = gotoCmd;
					return nBytes;
				}
				break;
				
			case ScriptType.IF_TRUE_CMD:
			case ScriptType.IF_FALSE_CMD:
			case ScriptType.IF_BIGGER_CMD:
			case ScriptType.IF_SMALLER_CMD:
			case ScriptType.IF_BIGGER_EQU_CMD:
			case ScriptType.IF_SMALLER_EQU_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<IF_XXX_CMD : " + ifCmd);
				}
				if (isWrite) {
					bytes.put(ifCmd.type); //1
					bytes.put((byte)16); //2
					bytes.put(ifCmd.flag); //3
					bytes.put((byte)0); //4
					bytes.putInt(ifCmd.value1); //8
					bytes.putInt(ifCmd.value2); //12
					bytes.position(bytes.position() + 4); //16 //label position: +12 //FIXME:
				} else {
					ifCmd.type = id;
					ifCmd.flag = bytes.get();
					bytes.position(bytes.position() + 1);
					ifCmd.value1 = bytes.getInt();
					ifCmd.value2 = bytes.getInt();
					ifCmd.goto_label = bytes.getInt();
					ifCmd.size = nBytes;
					current_read_cmd = ifCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.MENU_INIT_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<MENU_INIT_CMD : ");
				}
				if (isWrite) {
					bytes.put(ScriptType.MENU_INIT_CMD); //1
					bytes.put((byte)4); //2
					bytes.put((byte)0); //3
					bytes.put((byte)0); //4
				} else {
					bytes.position(bytes.position() + 2);
					normalCmd.type = ScriptType.MENU_INIT_CMD;
					normalCmd.size = nBytes;
					current_read_cmd = normalCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.MENU_ITEM_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<MENU_ITEM_CMD : " + menuItemCmd);
				}
				if (isWrite) {
					bytes.put(menuItemCmd.type); //1
					bytes.put((byte)4); //2
					bytes.put(menuItemCmd.number); //3
					bytes.put(menuItemCmd.label_len); //4
					bytes.put(menuItemCmd.bytes.toByteArray());
				} else {
					menuItemCmd.number = bytes.get();
					menuItemCmd.label_len = bytes.get();
					menuItemCmd.size = nBytes;
					current_read_cmd = menuItemCmd;
					return nBytes;
				}
				break;
				
			case ScriptType.MENU_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<MENU_CMD : " + menuCmd);
				}
				if (isWrite) {
					bytes.put(menuCmd.type); //1
					bytes.put((byte)4); //2
					bytes.putShort(menuCmd.value_addr); //4
				} else {
					menuCmd.value_addr = bytes.getShort();
					menuCmd.size = nBytes;
					current_read_cmd = menuCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.EXEC_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<EXEC_CMD : " + execCmd);
				}
				if (isWrite) {
					bytes.put(execCmd.type); //1
					bytes.put((byte)4); //2
					bytes.put((byte)0); //3
					bytes.put(execCmd.path_len); //4
					bytes.put(execCmd.bytes.toByteArray());
				} else {
					bytes.position(bytes.position() + 1);
					execCmd.path_len = bytes.get();
					execCmd.size = nBytes;
					current_read_cmd = execCmd;
					return nBytes;
				}
				break;
				
			case ScriptType.LOAD_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<LOAD_CMD : " + loadCmd);
				}
				if (isWrite) {
					bytes.put(loadCmd.type); //1
					bytes.put((byte)4); //2
					bytes.put(loadCmd.flag); //3
					bytes.put(loadCmd.path_len); //4
					bytes.put(loadCmd.bytes.toByteArray());
				} else {
					loadCmd.flag = bytes.get();
					loadCmd.path_len = bytes.get();
					loadCmd.size = nBytes;
					current_read_cmd = loadCmd;
					return nBytes;
				}
				break;
				
			case ScriptType.UPDATE_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<UPDATE_CMD : " + updateCmd);
				}
				if (isWrite) {
					bytes.put(updateCmd.type); //1
					bytes.put((byte)4); //2
					bytes.put(updateCmd.flag); //3
					bytes.put((byte)0); //4
				} else {
					updateCmd.flag = bytes.get();
					updateCmd.size = nBytes;
					current_read_cmd = updateCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.CLEAR_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<CLEAR_CMD : " + clearCmd);
				}
				if (isWrite) {
					bytes.put(clearCmd.type); //1
					bytes.put((byte)4); //2
					bytes.put(clearCmd.pos); //3
					bytes.put((byte)0); //4
				} else {
					clearCmd.pos = bytes.get();
					clearCmd.size = nBytes;
					current_read_cmd = clearCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.MUSIC_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<MUSIC_CMD : " + musicCmd);
				}
				if (isWrite) {
					bytes.put(musicCmd.type); //1
					bytes.put((byte)8); //2
					bytes.put((byte)0); //3
					bytes.put((byte)0); //4
					bytes.putInt(musicCmd.number); //8
				} else {
					bytes.position(bytes.position() + 2);
					musicCmd.number = bytes.getInt();
					musicCmd.size = nBytes;
					current_read_cmd = musicCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.STOPM_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<STOPM_CMD : ");
				}
				if (isWrite) {
					bytes.put(ScriptType.STOPM_CMD); //1
					bytes.put((byte)4); //2
					bytes.put((byte)0); //3
					bytes.put((byte)0); //4
				} else {
					bytes.position(bytes.position() + 2);
					normalCmd.type = ScriptType.STOPM_CMD;
					normalCmd.size = nBytes;
					current_read_cmd = normalCmd;
					return nBytes;
				}
				break;
				
			case ScriptType.SOUND_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<SOUND_CMD : " + soundCmd);
				}
				if (isWrite) {
					bytes.put(soundCmd.type); //1
					bytes.put((byte)4); //2
					bytes.put((byte)0); //3
					bytes.put(soundCmd.path_len); //4
					bytes.put(soundCmd.bytes.toByteArray());
				} else {
					bytes.position(bytes.position() + 1);
					soundCmd.path_len = bytes.get();
					soundCmd.size = nBytes;
					current_read_cmd = soundCmd;
					return nBytes;
				}
				break;
				
			case ScriptType.SLEEP_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<SLEEP_CMD : " + sleepCmd);
				}
				if (isWrite) {
					bytes.put(sleepCmd.type); //1 //FIXME:
					bytes.put((byte)8); //2
					bytes.put((byte)0); //3
					bytes.put((byte)0); //4
					bytes.putInt(sleepCmd.time); //8
				} else {
					bytes.position(bytes.position() + 2);
					sleepCmd.time = bytes.getInt();
					sleepCmd.size = nBytes;
					current_read_cmd = sleepCmd;
					return nBytes;
				}
				break;
				
			case ScriptType.FADEIN_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<FADEIN_CMD : ");
				}
				if (isWrite) {
					bytes.put(ScriptType.FADEIN_CMD); //1
					bytes.put((byte)4); //2
					bytes.put((byte)0); //3
					bytes.put((byte)0); //4
				} else {
					bytes.position(bytes.position() + 2);
					normalCmd.type = ScriptType.FADEIN_CMD;
					normalCmd.size = nBytes;
					current_read_cmd = normalCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.FADEOUT_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<FADEOUT_CMD : ");
				}
				if (isWrite) {
					bytes.put(ScriptType.FADEOUT_CMD); //1
					bytes.put((byte)4); //2
					bytes.put((byte)0); //3
					bytes.put((byte)0); //4
				} else {
					bytes.position(bytes.position() + 2);
					normalCmd.type = ScriptType.FADEOUT_CMD;
					normalCmd.size = nBytes;
					current_read_cmd = normalCmd;
					return nBytes;
				}
				break;
				
			case ScriptType.WIPEIN_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<WIPEIN_CMD : " + wipeinCmd);
				}
				if (isWrite) {
					bytes.put(wipeinCmd.type); //1
					bytes.put((byte)4); //2
					bytes.put((byte)0); //3
					bytes.put(wipeinCmd.pattern); //4
				} else {
					bytes.position(bytes.position() + 1);
					wipeinCmd.pattern = bytes.get();
					wipeinCmd.size = nBytes;
					current_read_cmd = wipeinCmd;
					return nBytes;
				}
				break;
				
			case ScriptType.WIPEOUT_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<WIPEOUT_CMD : " + wipeoutCmd);
				}
				if (isWrite) {
					bytes.put(wipeoutCmd.type); //1
					bytes.put((byte)4); //2
					bytes.put((byte)0); //3
					bytes.put(wipeoutCmd.pattern); //4
				} else {
					bytes.position(bytes.position() + 1);
					wipeoutCmd.pattern = bytes.get();
					wipeoutCmd.size = nBytes;
					current_read_cmd = wipeoutCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.CUTIN_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<CUTIN_CMD : ");
				}
				if (isWrite) {
					bytes.put(ScriptType.CUTIN_CMD); //1
					bytes.put((byte)4); //2
					bytes.put((byte)0); //3
					bytes.put((byte)0); //4
				} else {
					bytes.position(bytes.position() + 2);
					normalCmd.type = ScriptType.CUTIN_CMD;
					normalCmd.size = nBytes;
					current_read_cmd = normalCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.CUTOUT_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<CUTOUT_CMD : ");
				}
				if (isWrite) {
					bytes.put(ScriptType.CUTOUT_CMD); //1
					bytes.put((byte)4); //2
					bytes.put((byte)0); //3
					bytes.put((byte)0); //4
				} else {
					bytes.position(bytes.position() + 2);
					normalCmd.type = ScriptType.CUTOUT_CMD;
					normalCmd.size = nBytes;
					current_read_cmd = normalCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.WHITEIN_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<WHITEIN_CMD : ");
				}
				if (isWrite) {
					bytes.put(ScriptType.WHITEIN_CMD); //1
					bytes.put((byte)4); //2
					bytes.put((byte)0); //3
					bytes.put((byte)0); //4
				} else {
					bytes.position(bytes.position() + 2);
					normalCmd.type = ScriptType.WHITEIN_CMD;
					normalCmd.size = nBytes;
					current_read_cmd = normalCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.WHITEOUT_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<WHITEOUT_CMD : ");
				}
				if (isWrite) {
					bytes.put(ScriptType.WHITEOUT_CMD); //1
					bytes.put((byte)4); //2
					bytes.put((byte)0); //3
					bytes.put((byte)0); //4
				} else {
					bytes.position(bytes.position() + 2);
					normalCmd.type = ScriptType.WHITEOUT_CMD;
					normalCmd.size = nBytes;
					current_read_cmd = normalCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.FLASH_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<FLASH_CMD : ");
				}
				if (isWrite) {
					bytes.put(ScriptType.FLASH_CMD); //1
					bytes.put((byte)4); //2
					bytes.put((byte)0); //3
					bytes.put((byte)0); //4
				} else {
					bytes.position(bytes.position() + 2);
					normalCmd.type = ScriptType.FLASH_CMD;
					normalCmd.size = nBytes;
					current_read_cmd = normalCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.SHAKE_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<SHAKE_CMD : ");
				}
				if (isWrite) {
					bytes.put(ScriptType.SHAKE_CMD); //1
					bytes.put((byte)4); //2
					bytes.put((byte)0); //3
					bytes.put((byte)0); //4
				} else {
					bytes.position(bytes.position() + 2);
					normalCmd.type = ScriptType.SHAKE_CMD;
					normalCmd.size = nBytes;
					current_read_cmd = normalCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.MODE_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<MODE_CMD : " + modeCmd);
				}
				if (isWrite) {
					bytes.put(modeCmd.type); //1
					bytes.put((byte)4); //2
					bytes.put(modeCmd.mode); //3
					bytes.put((byte)0); //4
				} else {
					modeCmd.mode = bytes.get();
					bytes.position(bytes.position() + 1);
					modeCmd.size = nBytes;
					current_read_cmd = modeCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.SYS_LOAD_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<SYS_LOAD_CMD : ");
				}
				if (isWrite) {
					bytes.put(ScriptType.SYS_LOAD_CMD); //1
					bytes.put((byte)4); //2
					bytes.put((byte)0); //3
					bytes.put((byte)0); //4
				} else {
					bytes.position(bytes.position() + 2);
					normalCmd.type = ScriptType.SYS_LOAD_CMD;
					normalCmd.size = nBytes;
					current_read_cmd = normalCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.SYS_EXIT_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<SYS_EXIT_CMD : ");
				}
				if (isWrite) {
					bytes.put(ScriptType.SYS_EXIT_CMD); //1
					bytes.put((byte)4); //2
					bytes.put((byte)0); //3
					bytes.put((byte)0); //4
				} else {
					bytes.position(bytes.position() + 2);
					normalCmd.type = ScriptType.SYS_EXIT_CMD;
					normalCmd.size = nBytes;
					current_read_cmd = normalCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.SYS_CLEAR_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<SYS_CLEAR_CMD : ");
				}
				if (isWrite) {
					bytes.put(ScriptType.SYS_CLEAR_CMD); //1
					bytes.put((byte)4); //2
					bytes.put((byte)0); //3
					bytes.put((byte)0); //4
				} else {
					bytes.position(bytes.position() + 2);
					normalCmd.type = ScriptType.SYS_CLEAR_CMD;
					normalCmd.size = nBytes;
					current_read_cmd = normalCmd;
					return nBytes;
				}
				break;
			
			case ScriptType.END_CMD:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<END_CMD : ");
				}
				if (isWrite) {
					bytes.put(ScriptType.END_CMD); //1
					bytes.put((byte)4); //2
					bytes.put((byte)0); //3
					bytes.put((byte)0); //4
				} else {
					bytes.position(bytes.position() + 2);
					normalCmd.type = ScriptType.END_CMD;
					normalCmd.size = nBytes;
					current_read_cmd = normalCmd;
					return nBytes;
				}
				break;
				
			default:
				if (DEBUG_WRITE_COMMAND) {
					trace("<<defalut : ");
				}
				break;
		}
		return 0;
	}
	
	private static void trace(String str) {
		System.out.println(str);
	}
}
