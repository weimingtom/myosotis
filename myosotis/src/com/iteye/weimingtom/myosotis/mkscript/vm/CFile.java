package com.iteye.weimingtom.myosotis.mkscript.vm;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Hashtable;
import java.util.Map;
import java.util.zip.ZipFile;

import javax.imageio.ImageIO;

public class CFile {
	private final static String ZIPFILE = "data.zip"; 
	
	private static void trace(String str) {
		System.out.println(str);
	}
	
	private static Map<String, Class> _classes = new Hashtable<String, Class>();
	private static Map<String, BufferedImage> _bitmaps = new Hashtable<String, BufferedImage>();
	
	private static Map<String, Class> _dat_classes = new Hashtable<String, Class>();
	private static Map<String, ByteBuffer> _dat_bytes = new Hashtable<String, ByteBuffer>();
	
	public static void loadBitmap(String name, Class cls) {
		_classes.put(name, cls);
		try {
			_bitmaps.put(name, ImageIO.read(cls.getResourceAsStream(name)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadBitmap(String name, String path) {
		InputStream instr = null;
		ZipFile zipFile = null;
		try {
			File file = new File(ZIPFILE);
			if (file.exists() && file.canRead()) {
				zipFile = new ZipFile(ZIPFILE);
				instr = zipFile.getInputStream(zipFile.getEntry(path));
			} else {
				instr = new FileInputStream(path);
			}
			_bitmaps.put(name, ImageIO.read(instr));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (instr != null) {
				try {
					instr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (zipFile != null) {
				try {
					zipFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void loadData(String name, Class<?> cls) {
		_dat_classes.put(name, cls);
		InputStream instr = cls.getResourceAsStream(name);
		byte[] bytes = null;
		try {
			bytes = new byte[instr.available()];
			instr.read(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		_dat_bytes.put(name, ByteBuffer.wrap(bytes));
	}
	
	public static void loadData(String name, String path) {
		InputStream instr = null;
		ZipFile zipFile = null;
		try {
			File file = new File(ZIPFILE);
			if (file.exists() && file.canRead()) {
				zipFile = new ZipFile(ZIPFILE);
				instr = zipFile.getInputStream(zipFile.getEntry(path));
			} else {
				instr = new FileInputStream(path);
			}
			byte[] bytes = null;
			try {
				bytes = new byte[instr.available()];
				instr.read(bytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
			_dat_bytes.put(name, ByteBuffer.wrap(bytes));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (instr != null) {
				try {
					instr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (zipFile != null) {
				try {
					zipFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//------------------------------------------
	
	public static final int read = 0;
	public static final int write = 1;
	
	public BufferedImage bitmap;
	public ByteBuffer _bytes;
	public String _filename;
	private boolean isOK = false;
	
	public CFile(String file) {
		Open(file, CFile.read);
	}
	
	public CFile(String file, int mode) {
		Open(file, mode);
	}
	
	public boolean Open(String file) {
		return Open(file, CFile.read);
	}
	
	public boolean Open(String file, int mode) {
		//FIXME:ADD
		//添加，方便调试
		this._filename = file;
		bitmap = CFile._bitmaps.get(file);
		if (bitmap != null) {
			isOK = true;
			return true;
		} else {
			//return false;
			_bytes = CFile._dat_bytes.get(file);
			trace("open hex file:" + file);
			if (_bytes != null) {
				isOK = true;
				return true;
			} else {
				return false;
			}
		}
	}
	
	public boolean Close() {
		return true;
	}
	
	public boolean IsOk() { 
		return this.isOK; 
	}
	
	//FIXME:
	public int Read(ByteBuffer bytes, int length) {
		int pos = bytes.position();
		_bytes.position(0);
		byte[] b = new byte[length];
		_bytes.get(b, 0, length); //FIXME:
		bytes.put(b);
		return bytes.position() - pos;
	}
	
	//FIXME:
	public int GetFileSize() {
		if (_bytes != null) {
			return _bytes.array().length; //FIXME:
		}
		return 0;
	}
}
