package com.iteye.weimingtom.myosotis.mkscript.vm;

public class EffectWipeOut2 extends CViewEffect {
	public EffectWipeOut2(CMainWin win, CDrawImage dst, CImage src) {
		super(win, 1000 / 20, dst, src);
	}
	
	@Override 
	public boolean Step() {
		boolean result = Dst.WipeOut2(EffectRect, EffectCnt++);
		Window.Repaint(EffectRect);
		return result;
	}
}
