package com.iteye.weimingtom.myosotis.mkscript.vm;

public class EffectWipeOut extends CViewEffect {
	public EffectWipeOut(CMainWin win, CDrawImage dst, CImage src) {
		super(win, 1000 / 8, dst, src);
	}
	
	@Override 
	public boolean Step() {
		Dst.WipeOut(EffectRect, EffectCnt);
		Window.Repaint(EffectRect);
		if (++EffectCnt >= 8) {
			return false;
		}
		return true;
	}
}
