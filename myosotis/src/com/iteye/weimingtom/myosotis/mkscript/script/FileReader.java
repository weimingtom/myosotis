package com.iteye.weimingtom.myosotis.mkscript.script;

public class FileReader {
	private static final boolean DEBUG_GETSTRING = false;
	
	private String filename;
	private int lineno;
	private FileHandle fp;
	private String readBuffer;
	
	public String GetString() {
		this.lineno++;
		this.readBuffer = this.fp.getLineString();
		if (this.readBuffer == null) {
			return null;
		}
		if (DEBUG_GETSTRING) {
			System.out.println("_readBuffer:" + this.readBuffer);
			if (this.readBuffer.startsWith("goto")) {
				try {
					throw new Exception();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		while (this.readBuffer.length() > 0 && 
			(this.readBuffer.endsWith("\n") || 
			 this.readBuffer.endsWith("\r"))) {
			this.readBuffer = this.readBuffer.substring(0, this.readBuffer.length() - 1);
		}
		return this.readBuffer;
	}
	
	public String GetFileName() {
		return this.filename; 
	}
	
	public int GetLineNo() {
		return this.lineno; 
	}
	
	public boolean IsOpen() { 
		return this.fp != null; 
	}
	
	public FileReader(String filename) {
		this.filename = filename;
		this.lineno = 0;
		this.fp = FileLoader.open(filename, "r");
	}
}
