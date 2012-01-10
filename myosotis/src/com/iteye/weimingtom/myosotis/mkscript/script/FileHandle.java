package com.iteye.weimingtom.myosotis.mkscript.script;

public class FileHandle {
	private String filename;
	private String content;
	private String options;
	private String[] lines;
	private int currentLine;
	
	public String getLineString() {
		String str;
		if (this.currentLine >= this.lines.length) {
			str = null;
		} else {
			str = this.lines[this.currentLine];
			this.currentLine++;
		}
		return str;
	}
	
	public FileHandle(String filename, String content) {
		this(filename, content, "r");
	}
	
	public FileHandle(String filename, String content, String options) {
		this.filename = filename;
		this.content = content;
		this.options = options;
		this.lines = content.split("\\n");
		this.currentLine = 0;
	}
}
