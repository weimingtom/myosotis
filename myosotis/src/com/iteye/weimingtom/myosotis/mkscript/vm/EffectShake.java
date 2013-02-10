package com.iteye.weimingtom.myosotis.mkscript.vm;

public class EffectShake extends CViewEffect {
	public EffectShake(CMainWin win, CDrawImage dst) {
		super(win, 1000 / 24, dst);
	}
	
	@Override 
	public boolean Step() {
		return false;
	}
}
