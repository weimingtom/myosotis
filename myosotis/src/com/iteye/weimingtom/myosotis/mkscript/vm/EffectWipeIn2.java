package com.iteye.weimingtom.myosotis.mkscript.vm;

import java.awt.Rectangle;

public class EffectWipeIn2 extends CViewEffect {
	public EffectWipeIn2(CMainWin win, CDrawImage dst, CImage src, Rectangle rect) {
		super(win, 1000 / 20, dst, src, rect);
	}
	
	@Override 
	public boolean Step() {
		boolean result = Dst.WipeIn2(Src, EffectRect, EffectCnt++);
		Window.Repaint(EffectRect);
		return result;
	}
}
