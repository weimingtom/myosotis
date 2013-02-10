package com.iteye.weimingtom.myosotis.mkscript.vm;

public class CGameLoadAction extends CGameLoadSaveAction {

	@Override
	protected void DoLoadSave() {
		Parent.LoadGame(Selection);
	}
}
