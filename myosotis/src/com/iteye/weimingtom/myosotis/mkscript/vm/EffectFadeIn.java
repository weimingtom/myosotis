package com.iteye.weimingtom.myosotis.mkscript.vm;

public class EffectFadeIn extends CViewEffect {
	private static final int STEP = 16;

	public EffectFadeIn(CMainWin win, CDrawImage dst, CImage src) {
		super(win, 1000 / STEP, dst, src);
	}
	
	@Override 
	public boolean Step() {
		Dst.FadeFromBlack(Src, EffectRect, EffectCnt);
		Window.Repaint(EffectRect);
		if (++EffectCnt >= STEP) {
			return false;
		}
		return true;
	}
}
