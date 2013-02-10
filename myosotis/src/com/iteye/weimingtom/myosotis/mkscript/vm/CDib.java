package com.iteye.weimingtom.myosotis.mkscript.vm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class CDib {
	private static void trace(String str) {
		System.out.println(str);
	}
	
	public BufferedImage _bmd;

	public CDib() {
		
	}	
	
	public boolean Create(int width, int height, int depth) {
		_bmd = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		return true;
	}
	
	public boolean LoadBMP(CFile file) {
		return LoadBMP(file, 0, 0);
	}
	
	public boolean LoadBMP(CFile file, int ox, int oy) {
		if (file.bitmap == null) {
			throw new RuntimeException("CDib::LoadBMP 失败!" + file._filename);
		}
		//FIMXE:
		if (_bmd == null) {
			_bmd = new BufferedImage(file.bitmap.getWidth(), file.bitmap.getHeight(), BufferedImage.TYPE_INT_ARGB);
		} else {
			_bmd = new BufferedImage(_bmd.getWidth(), _bmd.getHeight(), BufferedImage.TYPE_INT_ARGB);
		}
		trace("CDib::LoadBMP -> file.bitmap == " + file.bitmap);
		Graphics g = _bmd.getGraphics();
		g.drawImage(file.bitmap, ox, oy, null);
		g.dispose();
		return true;
	}
	
	public int Width() { 
		return _bmd.getWidth(); 
	}
	
	public int Height() { 
		return _bmd.getHeight();
	}
	
	public void Clear() {
		Graphics g = _bmd.getGraphics();
		g.setColor(new Color(0, 0, 0, 0));
		g.fillRect(0, 0, _bmd.getWidth(), _bmd.getHeight());
		g.dispose();
	}
}
