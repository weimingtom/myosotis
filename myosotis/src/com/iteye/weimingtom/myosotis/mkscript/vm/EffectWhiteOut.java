package com.iteye.weimingtom.myosotis.mkscript.vm;

public class EffectWhiteOut extends CViewEffect {
	public EffectWhiteOut(CMainWin win, CDrawImage dst, CImage src) {
		super(win, 1000 / 16, dst, src);
	}
	
	@Override 
	public boolean Step() {
		Dst.FadeToWhite(Src, EffectRect, EffectCnt);
		Window.Repaint(EffectRect);
		if (++EffectCnt >= 16) {
			return false;
		}
		return true;
	}
}
