package com.iteye.weimingtom.myosotis.mkscript.vm;

import java.awt.Point;

public class CAction {
	private void error() {
		try {
			throw new RuntimeException("abstract function");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected boolean ScriptRunning;
	protected CMainWin Parent;
	protected int Param1;
	protected int Param2;
	
	public boolean IsScriptRunning() { 
		return ScriptRunning; 
	}
	
	public CAction() {
		this(false);
	}
	
	public CAction(boolean scriptRun) {
		ScriptRunning = scriptRun;
	}
	
	public void Initialize(CMainWin parent) {
		Initialize(parent, 0, 0);
	}
	
	public void Initialize(CMainWin parent, int param1) {
		Initialize(parent, param1, 0);
	}	
	
	public void Initialize(CMainWin parent, int param1, int param2) {
		Parent = parent;
		Param1 = param1;
		Param2 = param2;
	}
	
	public void Pause() {
		error();
	}
	
	public void Resume() {
		error();
	}
	
	public void LButtonDown(int modKeys, Point point) {
		error();
	}
	
	public void LButtonUp(int modKeys, Point point) {
		error();
	}
	
	public void RButtonDown(int modKeys, Point point) {
		error();
	}
	
	public void RButtonUp(int modKeys, Point point) {
		error();
	}
	
	public void MouseMove(int modKeys, Point point) {
		error();
	}
	
	public void KeyDown(int key) {
		error();
	}
	
	public void TimedOut(int timerId) {
		error();
	}

	public boolean IdleAction() {
		error();
		return false;
	}

	public void MusicDone(int music) {
		error();
	}
	
	public void WipeDone() {
		error();
	}
	
	public void WaveDone() {
		error();
	}
}
