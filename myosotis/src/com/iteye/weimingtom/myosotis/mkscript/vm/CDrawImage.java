package com.iteye.weimingtom.myosotis.mkscript.vm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * NOTE 4 byte == int
 * @author Administrator
 *
 */
public class CDrawImage extends CImage {
	
	public CDrawImage() {
		super(0, 0);
	}
	
	private void Draw(Graphics dc, int x, int y, int w, int h, int ox, int oy) {
		if (x != ox || y != oy) {
			throw new RuntimeException("Draw x != ox || y != oy");
		}
		dc.drawImage(_bmd, 
			ox, oy, ox + w, oy + h, 
			 x,  y,  x + w,  y + h, 
			null);
	}
	
	private void Draw2(Graphics dc, Rectangle rect, Point point) {
		Draw(dc, rect.x, rect.y, rect.width, rect.height, point.x, point.y);
	}
	
	public void Draw3(Graphics dc, Rectangle rect) {
		Draw(dc, rect.x, rect.y, rect.width, rect.height, rect.x, rect.y);
	}
	
	public boolean Create3(Graphics dc, int width, int height) {
		super.Create(width, height, 24);
		return true;
	}
	
	public void DrawText(CFont hFont, int x1, int y1, String str) {
		DrawText(hFont, x1, y1, str, CMisc.WhitePixel);
	}
	
	public void DrawText(CFont hFont, int x1, int y1, String str, int color) {
		Graphics g = _bmd.getGraphics();
		//FIXME:NO font
		g.setColor(new Color(color));
		int fontSize = 0;
		while (true) {
			fontSize++;
			g.setFont(new Font(null, 0, fontSize));
			if (g.getFontMetrics().getHeight() > CConfig.MessageFont) {
				break;
			}
		}
		g.setFont(new Font(null, 0, fontSize - 1));
		// FIMXE:(x1, y1)坐标为文字的左上角
		FontMetrics m = g.getFontMetrics();
		int h = m.getAscent();// + m.getDescent() + m.getLeading();
		//int h = m.getHeight();
		g.drawString(str, x1, y1 + h);
		g.dispose();
	}
	
	public void WipeIn_(CImage image, Rectangle rect, int count) {
		count = count % 8;
		if (count < 0) {
			count = count + 8;
		}
		Rectangle rect2 = (Rectangle)rect.clone();
		if (rect2.x < 0)
			rect2.x = 0;
		if (rect2.x + rect2.width > image.Width())
			rect2.width = image.Width() - rect2.x;
		if (rect2.x + rect2.width > this.Width())
			rect2.width = this.Width() - rect2.x;
		int[] pixels = new int[rect2.width * rect2.height];
		_bmd.getRGB(rect2.x, rect2.y, rect2.width, rect2.height, pixels, 0, rect2.width);
		int[] img_pixels = new int[rect2.width * rect2.height];
		image._bmd.getRGB(rect2.x, rect2.y, rect2.width, rect2.height, img_pixels, 0, rect2.width);
		int[] pixels2 = new int[rect2.width * rect2.height];
		int w = rect2.width;
		int y = 0;
		int pixels_position = 0;
		int pixels2_position = 0;
		int img_pixels_position = 0;
		while (pixels_position < pixels.length) {
			if (y % 8 < count) {
				System.arraycopy(img_pixels, img_pixels_position, pixels2, pixels2_position, w);
				pixels2_position += w;
				img_pixels_position += w;
				pixels_position += w;
			} else {
				System.arraycopy(pixels, pixels_position, pixels2, pixels2_position, w);
				pixels2_position += w;
				img_pixels_position += w;
				pixels_position += w;
			}
			y++;
		}
		_bmd.setRGB(rect2.x, rect2.y, rect2.width, rect2.height, pixels2, 0, rect2.width);
	}
	
	public void WipeIn(CImage image, Rectangle rect, int count) {
		Graphics g = _bmd.getGraphics();		
		count = count % 8;
		if (count < 0) {
			count = count + 8;
		}
		Rectangle rect2 = (Rectangle)rect.clone();
		if (rect2.x < 0)
			rect2.x = 0;
		if (rect2.x + rect2.width > image.Width())
			rect2.width = image.Width() - rect2.x;
		if (rect2.x + rect2.width > this.Width())
			rect2.width = this.Width() - rect2.x;
		int[] pixels = new int[rect2.width * rect2.height];
		_bmd.getRGB(rect2.x, rect2.y, rect2.width, rect2.height, pixels, 0, rect2.width);
		int[] img_pixels = new int[rect2.width * rect2.height];
		image._bmd.getRGB(rect2.x, rect2.y, rect2.width, rect2.height, img_pixels, 0, rect2.width);
		int[] pixels2 = new int[rect2.width * rect2.height];
		int w = rect2.width;
		int y = 0;
		int pixels_position = 0;
		int pixels2_position = 0;
		int img_pixels_position = 0;
		while (pixels_position < pixels.length) {
			if (y % 8 <= count) {
				//System.out.println("draw " + y + "/" + count + "," + (rect.x) + "," + (rect.y + y) + "," + (rect.x + rect.width) + "," + (rect.y + y + 1));
				g.drawImage(image._bmd,
						rect.x, rect.y + y, rect.x + rect.width, rect.y + y + 1, 
						rect.x, rect.y + y, rect.x + rect.width, rect.y + y + 1, 
						null);
				//System.arraycopy(img_pixels, img_pixels_position, pixels2, pixels2_position, w);
				pixels2_position += w;
				img_pixels_position += w;
				pixels_position += w;
			} else {
				//System.arraycopy(pixels, pixels_position, pixels2, pixels2_position, w);
				pixels2_position += w;
				img_pixels_position += w;
				pixels_position += w;
			}
			y++;
		}
		//_bmd.setRGB(rect2.x, rect2.y, rect2.width, rect2.height, pixels2, 0, rect2.width);
		g.dispose();
	}
	
	public void WipeOut(Rectangle rect, int count) {
		Rectangle rect2 = (Rectangle)rect.clone();
		if (rect2.x < 0)
			rect2.y = 0;
		if (rect2.x + rect2.width > this.Width())
			rect2.width = this.Width() - rect2.x;
		int[] pixels = new int[rect2.width * rect2.height];
		_bmd.getRGB(rect2.x, rect2.y, rect2.width, rect2.height, pixels, 0, rect2.width);
		int[] pixels2 = new int[rect2.width * rect2.height];
		int w = rect2.width;
		int y = 0;
		count = count % 8;
		int[] temp2 = new int[w];
		for (int i = 0; i < w; i++) {
			temp2[i] = 0xFF000000;
		}
		int pixels_position = 0;
		int pixels2_position = 0;
		while (pixels_position < pixels.length) {
			if (y % 8 == count) {
				System.arraycopy(temp2, 0, pixels2, pixels2_position, w);
				pixels2_position += w;
				pixels_position += w;
			} else {
				System.arraycopy(pixels, pixels_position, pixels2, pixels2_position, w);
				pixels2_position += w;
				pixels_position += w;
			}
			y++;
		}
		_bmd.setRGB(rect2.x, rect2.y, rect2.width, rect2.height, pixels2, 0, rect2.width);		
	}
	
	public boolean WipeIn2(CImage image, Rectangle rect, int count) {
		int width = rect.width;
		int height = rect.height;
		boolean update = false;
		int npos = count * 4;
		for (int y = 0; y < height; y += 32) {
			if (npos >= 0 && npos < 32) {
				int ypos = y + npos;
				Copy(image, new Rectangle(0, ypos, width, 4));
				update = true;
			}
			npos -= 4;
		}
		return update;
	}
	
	public boolean WipeOut2(Rectangle rect, int count) {
		int width = rect.width;
		int height = rect.height;
		boolean update = false;
		int npos = count * 4;
		for (int y = 0; y < height; y += 32) {
			if (npos >= 0 && npos < 32) {
				int ypos = y + npos;
				FillRect(new Rectangle(0, ypos, width, 4), 0);
				update = true;
			}
			npos -= 4;
		}
		return update;
	}
	
	public void FadeCvt(CImage image, Rectangle rect, int[] cvt) {
		int[] pixels = new int[rect.width * rect.height];
		image._bmd.getRGB(rect.x, rect.y, rect.width, rect.height, pixels, 0, rect.width);
		int[] pixels_bg = new int[rect.width * rect.height];
		_bmd.getRGB(rect.x, rect.y, rect.width, rect.height, pixels_bg, 0, rect.width);
		int[] pixels2 = new int[rect.width * rect.height];
		int pixels_position = 0; 
		int pixels_bg_position = 0;
		int pixels2_postion = 0;
		while (pixels_position < pixels.length && 
			pixels_bg_position < pixels_bg.length) {
			int p = pixels[pixels_position++];
			int p2 = pixels_bg[pixels_bg_position++];
			int a = (p & 0xFF000000) >>> 24;
			if (a == 0) {
				pixels2[pixels2_postion++] = p2;
			} else {
				int b = (p & 0x000000FF);
				int g = (p & 0x0000FF00) >>> 8;
				int r = (p & 0x00FF0000) >>> 16;
				b = cvt[b];
				g = cvt[g];
				r = cvt[r];
				pixels2[pixels2_postion++] = ((a << 24) | (r << 16) | (g << 8) | b);
			}
		}
		_bmd.setRGB(rect.x, rect.y, rect.width, rect.height, pixels2, 0, rect.width);
	}
	
	public void FadeFromBlack(CImage image, Rectangle rect, int count) {
		int[] cvt = new int[256];
		count++;
		for (int i = 0; i < 256; i++) {
			cvt[i] = ((i * count) / 16) & 0xFF;
		}
		FadeCvt(image, rect, cvt);
	}
	
	public void FadeToBlack(CImage image, Rectangle rect, int count) {
		int[] cvt = new int[256];
		count = 15 - count;
		for (int i = 0; i < 256; i++) {
			cvt[i] = ((i * count) / 16) & 0xFF;
		}
		FadeCvt(image, rect, cvt);
	}
	
	public void FadeFromWhite(CImage image, Rectangle rect, int count) {
		int[] cvt = new int[256];
		count++;
		int	level = 255 * (16 - count);
		for (int i = 0; i < 256; i++) {
			cvt[i] = ((i * count + level) / 16) & 0xFF;
		}
		FadeCvt(image, rect, cvt);
	}

	public void FadeToWhite(CImage image, Rectangle rect, int count) {
		int[] cvt = new int[256];
		count = 15 - count;
		int level = 255 * (16 - count);
		for (int i = 0; i < 256; i++) {
			cvt[i] = ((i * count + level) / 16) & 0xFF;
		}
		FadeCvt(image, rect, cvt);
	}
	
	private static int[] BitMask = {
		0x2080,	// 0010 0000 1000 0000
		0xa0a0,	// 1010 0000 1010 0000
		0xa1a4,	// 1010 0001 1010 0100
		0xa5a5,	// 1010 0101 1010 0101
		0xada7,	// 1010 1101 1010 0111
		0xafaf,	// 1010 1111 1010 1111
		0xefbf,	// 1110 1111 1011 1111
		0xffff,	// 1111 1111 1111 1111
	};
	private static int[] XMask = {
		0xf000, 0x0f00, 0x00f0, 0x000f,
	};
	private static int[] YMask = {
		0x8888, 0x4444, 0x2222, 0x1111,
	};
	
	public void Mix(CImage image, Rectangle rect, int count) {
		count = count % 8;
		if (count < 0) {
			count = count + 8;
		}
		Rectangle rect2 = (Rectangle)rect.clone();
		if (rect2.x < 0)
			rect2.x = 0;
		if (rect2.x + rect2.width > image.Width())
			rect2.width = image.Width() - rect2.x;
		if (rect2.x + rect2.width > this.Width())
			rect2.width = this.Width() - rect2.x;
		int[] pixels = new int[rect2.width * rect2.height];
		_bmd.getRGB(rect2.x, rect2.y, rect2.width, rect2.height, pixels, 0, rect2.width);
		int[] img_pixels = new int[rect2.width * rect2.height];
		image._bmd.getRGB(rect2.x, rect2.y, rect2.width, rect2.height, img_pixels, 0, rect2.width);
		int[] pixels2 = new int[rect2.width * rect2.height];
		int w = rect2.width;
		int pixels_position = 0;
		int img_pixels_position = 0;
		int pixels2_position = 0;
		for (int y = 0; pixels_position < pixels.length; y++) {
			for (int x = 0; x < w; x++) {
				int mask = (int)(BitMask[count]) & (int)(YMask[y & 3]);
				int p = pixels[pixels_position++];
				int p2 = img_pixels[img_pixels_position++];	
				if ((p2 & 0xFF000000) == 0) {
					pixels2[pixels2_position++] = p;
				} else if ((mask & XMask[x & 3]) != 0) {
					pixels2[pixels2_position++] = p2;
				} else {
					pixels2[pixels2_position++] = p;
				}
			}
		}
		_bmd.setRGB(rect.x, rect.y, rect.width, rect.height, pixels2, 0, rect.width);
	}
}
