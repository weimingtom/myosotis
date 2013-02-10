package com.iteye.weimingtom.myosotis.mkscript.vm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * TODO: DONNOT call getGraphics many times!
 * TODO: new Color(color)
 * @author Administrator
 *
 */
public class CImage extends CDib {
	public CImage() {
		
	}
	
	public CImage(int width, int height) {
		if (width == 0 && height == 0) {
			return;
		}
		Create2(width, height);
	}
	
	public boolean Create2(int width, int height) {
		return super.Create(width, height, 24);
	}
	
	public boolean LoadImage(String name) {
		return LoadImage(name, 0, 0);
	}
	
	public boolean LoadImage(String name, int ox, int oy) {
		String path = CConfig.CGPATH + name;
		CFile file = new CFile(path);
		if (!file.IsOk())
			return false;
		return LoadBMP(file, ox, oy);
	}
	
	public void FillRect(Rectangle rect, int color) {
		Graphics g = _bmd.getGraphics();
		g.setColor(new Color(color));
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
		g.dispose();
	}
	
	public void Copy(CImage image, Rectangle rect) {
		Graphics g = _bmd.getGraphics();
		g.drawImage(image._bmd, 
			rect.x, rect.y, rect.x + rect.width, rect.y + rect.height, 
			rect.x, rect.y, rect.x + rect.width, rect.y + rect.height, 
			null);
		g.dispose();
	}
	
	public void MixImage(CImage image, Rectangle rect) {
		this.MixImage(image, rect, 0x00FF00);
	}
	
	public void MixImage(CImage image, Rectangle rect, int trans_color) {
		Graphics g = _bmd.getGraphics();
		g.drawImage(image._bmd, 
				rect.x, rect.y, rect.x + rect.width, rect.y + rect.height, 
				rect.x, rect.y, rect.x + rect.width, rect.y + rect.height, 
				null);
		g.dispose();
	}
	
	public void DrawRect(Rectangle rect, int color) {
		int width = rect.width;
		int height = rect.height;
		FillRect2(rect.x, rect.y, width, 1, color);
		FillRect2(rect.x, rect.y, 1, height, color);
		FillRect2(rect.x + rect.width - 1, rect.y, 1, height, color);
		FillRect2(rect.x, rect.y + rect.height - 1, width, 1, color);
	}
	
	public void FillHalfToneRect(Rectangle rect) {
		int[] pixcels = new int[rect.width * rect.height];
		_bmd.getRGB(rect.x, rect.y, rect.width, rect.height, 
				pixcels, 0, rect.width);
		for (int i = 0; i < pixcels.length; i++) {
			int p = pixcels[i];
			int b = (p & 0x000000FF);
			int g = (p & 0x0000FF00) >>> 8;
			int r = (p & 0x00FF0000) >>> 16;
			int a = (p & 0xFF000000) >>> 24;
			b /= 2;
			g /= 2;
			r /= 2;
			pixcels[i] = (a << 24) | (r << 16) | (g << 8) | b;
		}
		_bmd.setRGB(rect.x, rect.y, rect.width, rect.height, 
				pixcels, 0, rect.width);
	}
	
	public void DrawFrameRect(int x, int y, int w, int h) {
		DrawFrameRect(x, y, w, h, 0xFFFFFF);
	}
	
	public void DrawFrameRect(int x, int y, int w, int h, int color) {
		DrawRect2(x, y + 1, w, h - 2, color);
		DrawRect2(x + 1, y, w - 2, h, color);
		FillHalfToneRect2(x + 2, y + 2, w - 4, h - 4);
	}
	
	public void Copy2(CImage image) {
		Copy(image, new Rectangle(0, 0, image.Width(), image.Height()));
	}

	public void FillRect2(int x, int y, int w, int h, int color) {
		FillRect(new Rectangle(x, y, w, h), color);
	}

	public void DrawRect2(int x, int y, int w, int h, int color) {
		DrawRect(new Rectangle(x, y, w, h), color);
	}

	public void FillHalfToneRect2(int x, int y, int w, int h) {
		FillHalfToneRect(new Rectangle(x, y, w, h));
	}
	
	public void DrawFrameRect2(Rectangle rect) {
		DrawFrameRect2(rect, 0xFFFFFF);
	}
	
	public void DrawFrameRect2(Rectangle rect, int color) {
		DrawFrameRect(rect.x, rect.y, rect.width, rect.height, color);
	}
}
