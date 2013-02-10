package com.iteye.weimingtom.myosotis.mkscript.vm;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 * FIXME: testCDrawImage中WipeOut有问题（AS3版也有问题）
 * @author Administrator
 *
 */
public class CTest {
	private static class CTestWindow extends CWindow {
		private static final long serialVersionUID = 1L;
		
		private CImage img1 = new CImage(640, 480);
		private CImage img2 = new CImage(640, 480);
		private CDrawImage img3 = new CDrawImage();
		
		private int wheel_degree = 0;
		
		@Override
		public boolean OnCreate() {
			CFile.loadBitmap("cgdata/bg001", "cgdata/bg001.JPG");
			CFile.loadBitmap("cgdata/ch001", "cgdata/MEGU111.png");
			
			testCImage();
			//testCDrawImage();
			//testFormatMessage();
			return super.OnCreate();
		}
		
		private void testCImage() {
			img1.LoadImage("bg001", 0, 0);
			img2.LoadImage("ch001", 0, 0);
			
			//img1.Copy2(img2);
			//img1.MixImage(img2, new Rectangle(0, 0, 320, 480), 0x00FF00);
			//img1.FillRect2(100, 100, 100, 100, 0xFF0000);
			//img1.DrawRect2(100, 100, 100, 100, 0xFF0000);
			//img1.FillHalfToneRect2(100, 100, 100, 100);
			//img1.DrawFrameRect(100, 100, 100, 100, 0xFF0000);
			img1.DrawFrameRect2(new Rectangle(100, 100, 100, 100));
			
			this.getBufGraph().setColor(Color.BLUE);
			this.getBufGraph().fillRect(0, 0, 640, 480);
			this.getBufGraph().drawImage(img1._bmd, 0, 0, null);
		}
		
		/**
		 * 效果测试
		 * 注意检查异常（尤其是涉及ByteArray的复杂运算）
		 */
		private void testCDrawImage() {
			img3.LoadImage("bg001", 0, 0);
			img2.LoadImage("ch001", 160, 0);
			//addChild(layer1);
			//layer1.graphics.clear();
			//img3.Copy2(img2);
			
			//img3.MixImage(img2, new Rectangle(160, 0, 320, 480));
			
			img3.DrawText(null, 0, 100, "hello, world", 0x0000FF);
			img3.WipeIn(img2, new Rectangle(0, 0, 640, 480), 0);
			//img3.WipeOut(new Rectangle(50, 0, 640, 480), 0);
			//img3.FadeToWhite(img2, new Rectangle(0, 0, 640, 480), 0);
			//img3.Mix(img2, new Rectangle(0, 0, 640, 480), 0);
			this.getBufGraph().setColor(Color.BLUE);
			this.getBufGraph().fillRect(0, 0, 640, 480);
			img3.Draw3(this.getBufGraph(), new Rectangle(0, 0, 640, 480));
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("wheel_degree : " + wheel_degree);
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				//System.out.println("vk_up");
				wheel_degree++;
				break;
				
			case KeyEvent.VK_DOWN:
				//System.out.println("vk_down");
				wheel_degree--;
				break;
			}
			this.getBufGraph().setColor(Color.BLUE);
			this.getBufGraph().fillRect(0, 0, 640, 480);
			img3.LoadImage("bg001", 0, 0);
			img3.DrawText(null, 0, 100, "hello, world", 0x0000FF);
			img3.WipeIn(img2, new Rectangle(0, 0, 640, 480), wheel_degree);
			//img3.WipeOut(img2, new Rectangle(0, 0, 640, 480), wheel_degree);
			//img3.FadeToWhite(img2, new Rectangle(0, 0, 640, 480), wheel_degree);
			//img3.Mix(img2, new Rectangle(0, 0, 640, 480), wheel_degree);
			img3.Draw3(this.getBufGraph(), new Rectangle(0, 0, 640, 480));
			
			super.keyPressed(e);
		}

		
		private void testFormatMessage() {
			String testStr = "“２楼已经去过了啦！\n去别的地方吧”";
			System.out.println("original:" + testStr);
			int i = FormatMessage(testStr);
			//trace("FormatMessage :", );
		}
		
		private String[] MsgBuffer = new String[4];
		private int CurY = 0;
		private int CurX = 0;
		
		public int FormatMessage(String msg) {
			CurX = CurY = 0;
			String[] lines = msg.split("\n", 4);
			for (int i = 0; i < 4; i++) {
				if (i < lines.length && lines[i] != null) {
					MsgBuffer[i] = lines[i];
					System.out.println("FormatMessage," + i + "," + MsgBuffer[i]);
					CurY++;
				} else {
					System.out.println("FormatMessage," + i);
					MsgBuffer[i] = "";
				}
			}
			System.out.println("FormatMessage CurY == " + CurY);
			return CurY;
		}
		
		@Override
		public boolean OnIdle(int count) {
			return super.OnIdle(count);
		}
	}
	
	public static void main(String[] args) {
		//System.out.println("hello");
		new CTestWindow().start();
	}
}
