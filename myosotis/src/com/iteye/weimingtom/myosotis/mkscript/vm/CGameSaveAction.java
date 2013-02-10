package com.iteye.weimingtom.myosotis.mkscript.vm;

public class CGameSaveAction extends CGameLoadSaveAction{

	@Override
	protected void DoLoadSave() {
		Parent.SaveGame(Selection, Flags);
	}
}
