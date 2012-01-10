package com.iteye.weimingtom.myosotis.mkscript.script;

public class LabelRef {
	public LabelRef next;
	public int label_ref_address;
	
	public LabelRef(LabelRef next, int label_ref_address)  {
		this.next = next;
		this.label_ref_address = label_ref_address;
	}
}
