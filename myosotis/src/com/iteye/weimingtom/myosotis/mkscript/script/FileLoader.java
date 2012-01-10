package com.iteye.weimingtom.myosotis.mkscript.script;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FileLoader {
	private static Map<String, String> contents = new HashMap<String, String>();
	private static Map<String, String> classes = new HashMap<String, String>();
	
	public static FileHandle open(String name) {
		return open(name, "r");
	}
	
	public static void insertRes(String name, String resName) {
		contents.put(name, resToUTF8(resName));
		classes.put(name, resName);
	}
	
	public static FileHandle open(String name, String options) {
		if (contents.get(name) == null) {
			return null;
		}
		FileHandle fileHandle = new FileHandle(name, contents.get(name), options);
		return fileHandle;
	}
	
	public static String resToUTF8(String resName) {
		InputStream input = (new FileLoader()).getClass().getResourceAsStream(resName);
		if (input != null) {
			try {
				byte[] bytes = new byte[input.available()];
				input.read(bytes);
				return new String(bytes, "UTF8");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static final void main(String[] args) {
		System.out.println(resToUTF8("main.txt"));
	}
}
