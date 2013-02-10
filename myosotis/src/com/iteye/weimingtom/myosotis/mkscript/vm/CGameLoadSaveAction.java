package com.iteye.weimingtom.myosotis.mkscript.vm;

import java.awt.Point;
import java.awt.event.KeyEvent;

public abstract class CGameLoadSaveAction extends CAction {
	protected int Selection;
	protected boolean Pressed;
	protected boolean CancelPressed;
	protected int Flags;
	
	public CGameLoadSaveAction() {
		
	}
	
	@Override 
	public void Initialize(CMainWin parent, int param1, int param2) {
		super.Initialize(parent, param1, param2);
		Selection = -1;
		Pressed = false;
		CancelPressed = false;
		Flags = 0;
	}
	
	@Override
	public void Pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void LButtonDown(int modKeys, Point point) {
		Pressed = true;
	}

	@Override
	public void LButtonUp(int modKeys, Point point) {
		Pressed = false;
		if (Selection >= 0) {
			DoLoadSave();
		}
	}

	@Override
	public void RButtonDown(int modKeys, Point point) {
		CancelPressed = true;
	}

	@Override
	public void RButtonUp(int modKeys, Point point) {
		if (CancelPressed) {
			Parent.CancelLoadSaveMenu(Flags);
		}
	}

	@Override
	public void MouseMove(int modKeys, Point point) {
		int sel = Parent.GetLoadSaveSelect(point);
		if (sel != Selection) {
			Parent.SelectLoadSaveMenu(Selection, false);
			Selection = sel;
			Parent.SelectLoadSaveMenu(Selection, true);
		}
	}

	@Override
	public void KeyDown(int key) {
		int sel;
		switch (key) {
			case KeyEvent.VK_ENTER:
			case KeyEvent.VK_SPACE: // 执行装入存储
				if (Selection >= 0)
					DoLoadSave();
				break;
			
			case KeyEvent.VK_ESCAPE: // 取消
				Parent.CancelLoadSaveMenu(Flags);
				break;

			case KeyEvent.VK_UP:	// 选前一项
				{
					sel = Parent.PrevLoadSaveSelect(Selection);
					if (sel != Selection) 
					{
						Parent.SelectLoadSaveMenu(Selection, false);
						Selection = sel;
						Parent.SelectLoadSaveMenu(Selection, true);
					}
				}
				break;

			case KeyEvent.VK_DOWN:
				{
					sel = Parent.NextLoadSaveSelect(Selection);
					if (sel != Selection) 
					{
						Parent.SelectLoadSaveMenu(Selection, false);
						Selection = sel;
						Parent.SelectLoadSaveMenu(Selection, true);
					}
				}
				break;
		}
	}

	@Override
	public void TimedOut(int timerId) {
		switch (timerId) {
			case CMainWin.TimerSleep:
				Flags |= CMainWin.IS_TIMEDOUT;
				break;
		}
	}

	@Override
	public boolean IdleAction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void MusicDone(int music) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void WipeDone() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void WaveDone() {
		// TODO Auto-generated method stub
		
	}

	protected abstract void DoLoadSave();
}
