package com.iteye.weimingtom.myosotis.mkscript.vm;

import java.awt.Rectangle;

public class EffectWipeIn extends CViewEffect {
	public EffectWipeIn(CMainWin win, CDrawImage dst, CImage src, Rectangle rect) {
		super(win, 1000 / 8, dst, src, rect);
	}
	
	@Override 
	public boolean Step() {
		Dst.WipeIn(Src, EffectRect, EffectCnt);
		Window.Repaint(EffectRect);
		if (++EffectCnt >= 8)
			return false;
		return true;
	}
}
