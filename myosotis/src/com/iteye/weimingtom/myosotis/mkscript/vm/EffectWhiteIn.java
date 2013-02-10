package com.iteye.weimingtom.myosotis.mkscript.vm;

public class EffectWhiteIn extends CViewEffect {
	public EffectWhiteIn(CMainWin win, CDrawImage dst, CImage src) {
		super(win, 1000 / 16, dst, src);
	}
	
	@Override 
	public boolean Step() {
		Dst.FadeFromWhite(Src, EffectRect, EffectCnt);
		Window.Repaint(EffectRect);
		if (++EffectCnt >= 16)
			return false;
		return true;
	}
}
