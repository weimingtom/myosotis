package com.iteye.weimingtom.myosotis.mkscript.vm;

import java.awt.Rectangle;

public abstract class CViewEffect {
	private static final Rectangle default_rect = new Rectangle(0, 0, CConfig.WindowWidth, CConfig.WindowHeight);
	
	protected CMainWin Window;
	protected CDrawImage Dst;
	protected CImage Src;

	protected int TimeBase;
	protected int EffectCnt;
	protected Rectangle EffectRect;
	protected long lastTime;
	
	public abstract boolean Step();

	public CViewEffect(CMainWin win, int step, CDrawImage dst) {
		this(win, step, dst, null, CViewEffect.default_rect);
	}
	
	public CViewEffect(CMainWin win, int step, CDrawImage dst, CImage src) {
		this(win, step, dst, src, CViewEffect.default_rect);
	}
	
	public CViewEffect(CMainWin win, int step, CDrawImage dst, CImage src, Rectangle rect) {
		Window = win;
		Dst = dst;
		Src = src;
		TimeBase = step;
		//FXIME:???
		EffectRect = (Rectangle)rect.clone(); 
		EffectCnt = 0;
		lastTime = 0;
	}
	
	public boolean Step2(long time) {
		if (TimeBase <= time - lastTime) {
			lastTime = time;
			return Step();
		}
		return true;
	}
}
