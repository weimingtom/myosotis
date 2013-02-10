package com.iteye.weimingtom.myosotis.mkscript.vm;

public class CMci {
	protected CWindow Window;
	protected String device;
	protected String errTitle;
	protected int Id;
	
	public int GetId() { 
		return Id; 
	}
	
	public CMci(String device, String errTitle) {
		this.Id = 0;
		this.device = device;
		this.errTitle = errTitle;
	}
	
	public boolean Open(CWindow window) {
		Window = window;
		return true;
	}
	
	public boolean Close() {
		return true;
	}
	
	public boolean Play(int no) {
		return false;
	}
	
	public boolean Play2(String name) {
		return false;
	}
	
	public boolean Replay() {
		return false;
	}
	
	public boolean Stop() {
		return false;
	}

	public void MciErrorMessageBox(int err) {
		
	}
	
	public boolean MciOpen(String command) {
		return false;
	}
	
	public boolean MciOpen2() {
		return false;
	}
	
	public boolean MciOpen3(String dev, String element) {
		return false;
	}
	
	public boolean MciClose() {
		return false;
	}
	
	public boolean MciPlay(String request) {
		return false;
	}
	
	public boolean MciStop() {
		return false;
	}
	
	public boolean MciSet(String request) {
		return false;
	}
	
	public boolean MciStatus(String request, String result, int resultlen) {
		return false;
	}
}
