package com.iteye.weimingtom.myosotis.mkscript.vm;

public class EffectFlash extends CViewEffect {
	public EffectFlash(CMainWin win, CDrawImage dst) {
		super(win, 1000 / 24, dst);
	}
	
	@Override 
	public boolean Step() {
		return false;
	}
}
