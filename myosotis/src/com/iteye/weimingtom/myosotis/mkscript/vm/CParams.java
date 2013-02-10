package com.iteye.weimingtom.myosotis.mkscript.vm;

public class CParams {
	public static final int PARAMS_MAX_SAVE = 10;
	public static final int PARAMS_MAX_VALUES = CConfig.MAX_VALUES;
	
	public static final int SHOWCG_BLACKNESS = 0;
	public static final int SHOWCG_IMAGE = 1;
	public static final int SHOWCG_WHITENESS = 2;
	
	public int save_month = 0;
	public int save_date = 0;
	public int save_hour = 0;
	public int save_minute = 0;
	public int script_pos = 0;
	public String last_script = "";
	public String last_bg = "";
	public String last_center = "";
	public String last_left = "";
	public String last_right = "";
	public String last_overlap = "";
	public int last_bgm = 0;
	public int show_flag = 0;
	public int[] value_tab = new int[PARAMS_MAX_VALUES];			

	public CParams() {
		
	}
	
	public void Clear() {
		save_month = 0;
		save_date = 0;
		save_hour = 0;
		save_minute = 0;
		script_pos = 0;
		last_script = "";
		last_bg = "";
		last_center = "";
		last_left = "";
		last_right = "";
		last_overlap = "";
		last_bgm = 0;
		show_flag = 0;
		for (int i = 0; i < PARAMS_MAX_VALUES; ++i) {
			value_tab[i] = 0;
		}
	}
	
	public boolean Load(int no) {
		//TODO:
		return false;
	}
	
	public boolean Save(int no) {
		//TODO:
		return false;
	}

	public void ClearBackCG() {
		last_bg = "";
	}

	public void ClearLeftCG() {
		last_left = "";
		last_center = "";
		last_overlap = "";
	}
	
	public void ClearRightCG() {
		last_right = "";
		last_center = "";
		last_overlap = "";
	}

	public void ClearCenterCG() {
		last_left = "";
		last_right = "";
		last_center = "";
		last_overlap = "";
	}

	public void ClearOverlapCG() {
		last_left = "";
		last_right = "";
		last_center = "";
		last_overlap = "";
	}
	
	public void SetBackCG(String file) {
		last_bg = file;
	}
	
	public void SetLeftCG(String file) {
		last_center = "";
		last_overlap = "";
		last_left = file;
	}
	
	public void SetRightCG(String file) {
		last_center = "";
		last_overlap = "";
		last_right = file;
	}
	
	public void SetCenterCG(String file) {
		ClearOverlapCG();
		last_center = file;
	}
	
	public void SetOverlapCG(String file) {
		ClearOverlapCG();
		last_overlap = file;
	}

	public void SetShowFlag() {
		show_flag = SHOWCG_IMAGE;
	}
	
	public void ResetShowFlag() {
		ResetShowFlag(false);
	}
	
	public void ResetShowFlag(boolean white) {
		show_flag = white? SHOWCG_WHITENESS: SHOWCG_BLACKNESS;
	}
}
