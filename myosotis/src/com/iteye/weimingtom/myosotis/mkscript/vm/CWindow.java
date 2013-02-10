package com.iteye.weimingtom.myosotis.mkscript.vm;

import java.awt.Point;

import com.iteye.weimingtom.firetree.MainFrame;

public class CWindow extends MainFrame {
	private static void trace(String str) {
		System.out.println(str);
	}
	
	private static final long serialVersionUID = 1L;
	
	public static final int MB_ICONQUESTION = 0x00000020;
	public static final int MB_OKCANCEL = 0x00000001;
	public static final int IDOK = 1;
	public static final int WM_CLOSE = 0x0010;
	
	public static final int WINDOW_WIDTH = 640;
	public static final int WINDOW_HEIGHT = 480;
	public static final String WINDOW_TITLE = "CWindow";
	
	public void OnLButtonUp(int modKeys, Point point) {
		
	}
	
	public void OnLButtonDown(int modKeys, Point point) {
		
	}
	
	public void OnRButtonUp(int modKeys, Point point) {
		
	}
	
	public void OnMouseMove(int modKeys, Point point) {
		
	}
	
	public boolean OnIdle(int count) {
		return false;
	}
	
	public boolean OnCreate() {
		return true;
	}
	
	public void OnPaint() {
		
	}

	
	public void PostQuitMessage(int n) {
		
	}
	
	public void OnDestroy() {
		PostQuitMessage(0);
	}
	
	public void MessageBox(String str) {
		trace("NOTE:[MessageBox] -> " + str);
	}
	
	public int MessageBox2(String str, String title, int style) {
		trace("NOTE:[MessageBox2] -> " + str);
		return IDOK;
	}
	
	public boolean KillTimer(int idTimer) {
		return true;
	}
	
	public void PostMessage(int i) {
		
	}
	
	public void SendMessage(int i) {
		
	}
	
	
	public void SetTimer(int id, int time) {
		
	}
	
	public void ScreenToClient(Point pt) {
		
	}
	
	public CWindow() {
		//FIXME:
		super(WINDOW_TITLE, WINDOW_WIDTH, WINDOW_HEIGHT, 24);
	}

	@Override
	protected void onInit() {
		super.onInit();
		this.OnCreate();
	}

	@Override
	protected void onTick() {
		super.onTick();
		this.OnIdle(0); //FIXME:
	}

	@Override
	protected void onExit() {
		super.onExit();
		this.OnDestroy();
	}

	@Override
	protected void onMouseDown(int x, int y) {
		super.onMouseDown(x, y);
		this.OnLButtonDown(0, new Point(x, y));
	}

	@Override
	protected void onMouseUp(int x, int y) {
		super.onMouseUp(x, y);
		this.OnLButtonUp(0, new Point(x, y));
	}

	@Override
	protected void onMouseMove(int x, int y) {
		super.onMouseMove(x, y);
		this.OnMouseMove(0, new Point(x, y));
	}
	
	public static final void main(String[] args) {
		new CWindow().start();
	}
}
