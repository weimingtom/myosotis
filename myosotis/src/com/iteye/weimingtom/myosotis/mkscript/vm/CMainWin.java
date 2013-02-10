package com.iteye.weimingtom.myosotis.mkscript.vm;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import com.iteye.weimingtom.myosotis.mkscript.script.ScriptType;

/**
 * Rectangle is OK ?
 * @author Administrator
 *
 */
public class CMainWin extends CWindow {
	private static final long serialVersionUID = 1L;

	private static void trace(String str) {
		System.out.println(str);
	}
	
	private static final boolean NO_FIX_UPDATE_BUG = true;
	
	public static final int ActionNop = 0;
	public static final int ActionScriptDone = 1;
	public static final int ActionScriptSetup = 2;
	public static final int ActionScript = 3;
	public static final int ActionGameLoad = 4;
	public static final int ActionGameSave = 5;
	
	public static final int MusicCD = 0;
	public static final int MusicOff = 1;

	public static final int TimerSleep = 0;

	public static final int IS_TIMEDOUT = (1 << 0);

	public static final int MAX_MENU_ITEM = 8;
	public static final int MAX_MENU_TEXT = 60;
	public static final int MAX_SAVE_TEXT = 62;
	
	protected static final int None = 0;
	protected static final int Left = 1;
	protected static final int Right = 2;
	protected static final int Both = 3;
	protected static final int Center = 4;
	
	protected static final int MSG_W = CConfig.MessageFont * CConfig.MessageWidth / 2 + 20;
	protected static final int MSG_H = (CConfig.MessageFont + 2) * CConfig.MessageLine + 14;
	protected static final int MSG_X = (CConfig.WindowWidth - MSG_W) / 2;
	protected static final int MSG_Y = CConfig.WindowHeight - MSG_H - 8;
	protected static final int MSG_TEXTOFFSET_X = MSG_X + 10;
	protected static final int MSG_TEXTOFFSET_Y = MSG_Y + 7;
	protected static final int MSG_TEXTSPACE_Y = 2;
	protected static final int WAITMARK_X = CConfig.MessageWidth - 2;
	protected static final int WAITMARK_Y = CConfig.MessageLine - 1;
	
	protected static final int MENU_X = 20;
	protected static final int MENU_Y = MSG_Y - 2;
	protected static final int MENU_WIDTH = (MAX_MENU_TEXT + 2) * CConfig.MessageFont / 2;
	protected static final int MENU_HEIGHT = (MAX_MENU_ITEM + 1) * CConfig.MessageFont;
	protected static final int MENU_MIN_WIDTH = 80;
	protected static final int MENU_FRAME_WIDTH = 10;
	protected static final int MENU_FRAME_HEIGHT = 10;
	protected static final int MENU_ITEM_SPACE = 2;
	protected static final int MENU_ITEM_HEIGHT = CConfig.MessageFont + MENU_ITEM_SPACE;

	protected static final int SAVE_ITEM_HEIGHT = 32;
	protected static final int SAVE_ITEM_SPACE = 4;
	protected static final int SAVE_ITEM_INTERVAL = SAVE_ITEM_HEIGHT + SAVE_ITEM_SPACE;
	protected static final int SAVE_W = 400;
	protected static final int SAVE_H = SAVE_ITEM_INTERVAL * CParams.PARAMS_MAX_SAVE + SAVE_ITEM_HEIGHT;
	protected static final int SAVE_X = (CConfig.WindowWidth - SAVE_W) / 2;
	protected static final int SAVE_Y = (CConfig.WindowHeight - SAVE_H) / 2;
	protected static final int SAVE_TEXT_OFFSET_X = SAVE_X + 10;
	protected static final int SAVE_TEXT_OFFSET_Y = SAVE_Y + 8;
	protected static final int SAVE_TITLE_WIDTH = 72;

	protected static final int TextMessage = 1 << 0;
	protected static final int TextWaitMark = 1 << 1;
	protected static final int MenuFrame = 1 << 2;
	protected static final int SaveTitle = 1 << 3;
	
	protected static final int MenuItemFirst = 4;
	protected static final int SaveItemFirst = 12;
	
	protected static int MenuItem(int n) { 
		return 1 << (MenuItemFirst + n); 
	}
	
	protected static int SaveItem(int n) { 
		return 1 << (SaveItemFirst + n); 
	}
	
	protected CFont hFont = new CFont();
	protected int MusicMode;
	
	protected CMci music;
	protected CDAudio cdaudio = new CDAudio();
	protected int MusicNo;
	protected WaveOut wave = new WaveOut();
	
	protected CParams LoadParam	= new CParams();

	protected CAction Action;
	protected CAction NopAction = new CAction();
	protected CScriptAction ScriptAction = new CScriptAction();
	protected CGameLoadAction GameLoadAction = new CGameLoadAction(); 
	protected CGameSaveAction GameSaveAction = new CGameSaveAction();
	
	protected CDrawImage ViewImage = new CDrawImage();
	protected CDrawImage MixedImage = new CDrawImage();
	protected CImage BackLayer = new CImage();
	protected CImage OverlapLayer = new CImage();
	protected int OverlapFlags;
	protected boolean TextDisplay;
	protected boolean WaitMarkShowing;
	
	protected Rectangle InvalidRect = new Rectangle(0, 0, 0, 0); // 无效区域
	protected Rectangle TextRect = new Rectangle(MSG_X, MSG_Y, MSG_W, MSG_H);
	protected Rectangle WaitMarkRect = new Rectangle(MsgX(WAITMARK_X), MsgY(WAITMARK_Y),
			CConfig.MessageFont, CConfig.MessageFont);
	protected Rectangle MenuRect = new Rectangle();
	protected Rectangle OverlapBounds = new Rectangle();
	protected boolean BackShow;
	protected boolean OverlapShow;
	protected boolean TextShow;
	protected boolean MenuShow;
	protected boolean SaveShow;
	protected Rectangle SaveRect = new Rectangle(SAVE_X, SAVE_Y, SAVE_W, SAVE_H);
	protected int BgColor;
	
	protected CViewEffect ViewEffect;
	protected int TimePeriod;
	
	protected String[] MsgBuffer = new String[CConfig.MessageLine];
	
	protected int CurX;
	protected int CurY;
	
	protected CMenuItem[] MenuBuffer = new CMenuItem[CMainWin.MAX_MENU_ITEM];
	protected int MenuCount;
	
	protected boolean IsSaveMenu;
	protected CDataTitle[] DataTitle = new CDataTitle[CParams.PARAMS_MAX_SAVE];

	protected static final Rectangle[] Position = {
		new Rectangle(0, 0, 0, 0),
	    new Rectangle(0, 0, CConfig.WindowWidth / 2, CConfig.WindowHeight),
	    new Rectangle(CConfig.WindowWidth / 2, 0, CConfig.WindowWidth / 2, CConfig.WindowHeight),
	    new Rectangle(0, 0, CConfig.WindowWidth, CConfig.WindowHeight),
	    new Rectangle(CConfig.WindowWidth / 4, 0, CConfig.WindowWidth / 2, CConfig.WindowHeight),
	};
	
	public Graphics getClientDC() {
		trace("getClientDC()" + this.getBufGraph());
		return this.getBufGraph();
	}
	
	public void Repaint(Rectangle rect) {
		ViewImage.Draw3(getClientDC(), rect);
	}
	
	public void CopyAndRepaint(Rectangle rect) {
		//FIXME:
		//Rectangle allRactangle = new Rectangle(320, 0, 320, 480);
		trace("CMainWin::CopyAndRepaint rect == " + rect.x + " " + rect.y + " " + rect.width + " " + rect.height);
		ViewImage.Copy(MixedImage, rect);
		if (NO_FIX_UPDATE_BUG) {
			Repaint(/*allRactangle*/rect);
		}
	}
	
	public int GetMenuSelect(Point point) {
		if (point.x < MenuRect.x + MENU_FRAME_WIDTH || 
			point.y < MenuRect.y + MENU_FRAME_HEIGHT || 
			point.x >= MenuRect.x + MenuRect.width - MENU_FRAME_WIDTH || 
			point.y >= MenuRect.y + MenuRect.height - MENU_FRAME_HEIGHT)
			return -1;
		return (point.y - MenuRect.y - MENU_FRAME_WIDTH) / MENU_ITEM_HEIGHT;
	}
	
	public void WipeIn(Rectangle rect) {
		WipeIn(rect, -1);
	}
	
	public void WipeIn(Rectangle rect, int pattern) {
		Rectangle rect2 = (Rectangle)rect.clone();
		Update(false);
		switch (pattern) {
		case 1:
			ViewEffect = new EffectWipeIn(this, ViewImage, MixedImage, rect2);
			break;
		
		default:
			ViewEffect = new EffectWipeIn2(this, ViewImage, MixedImage, rect2);
			break;
		}
	}
	
	public void WipeIn2() {
		WipeIn2(1);
	}
	
	public void WipeIn2(int pattern) {
		WipeIn(new Rectangle(0, 0, CConfig.WindowWidth, CConfig.WindowHeight), pattern);
	}
	
	public void WipeOut(int pattern) {
		HideMessageWindow();
		switch (pattern) {
		case 1:
			ViewEffect = new EffectWipeOut(this, ViewImage, MixedImage);
			break;
		
		default:
			ViewEffect = new EffectWipeOut2(this, ViewImage, MixedImage);
			break;
		}
		HideAllLayer(CMisc.BlackPixel);
	}
	
	public void FadeIn() {
		Update(false);
		ViewEffect = new EffectFadeIn(this, ViewImage, MixedImage);
	}
	
	public void FadeOut() {
		HideMessageWindow();
		ViewEffect = new EffectFadeOut(this, ViewImage, MixedImage);
		HideAllLayer(CMisc.BlackPixel);
	}
	
	public void CutIn(Rectangle rect) {
		Rectangle rect2 = (Rectangle)rect.clone();
		Update(false);
		CopyAndRepaint(rect2);
	}
	
	public void CutIn2() {
		CutIn(new Rectangle(0, 0, CConfig.WindowWidth, CConfig.WindowHeight));
	}
	
	public void CutOut(boolean white) {
		HideMessageWindow();
		HideAllLayer(white? CMisc.WhitePixel: CMisc.BlackPixel);
		Invalidate(Position[Both]);
		Update();
	}
	
	public void WhiteIn() {
		Update(false);
		ViewEffect = new EffectWhiteIn(this, ViewImage, MixedImage);
	}
	
	public void WhiteOut() {
		HideMessageWindow();
		ViewEffect = new EffectWhiteOut(this, ViewImage, MixedImage);
		HideAllLayer(CMisc.WhitePixel);
	}
	
	public void MixFade(Rectangle rect) {
		Rectangle rect2 = (Rectangle) rect.clone();
		Update(false);
		ViewEffect = new EffectMixFade(this, ViewImage, MixedImage, rect2);
	}
	
	public void Shake() {
		ViewEffect = new EffectShake(this, ViewImage);
	}
	
	public void Flash() {
		ViewEffect = new EffectFlash(this, ViewImage);
	}
	
	public void StopWipe() {
		ViewEffect = null;
	}
	
	public boolean IsLoadOK() {
		return Action.IsScriptRunning() && ScriptAction.IsSaveLoadOK();
	}
	
	public boolean IsSaveOK() {
		return Action.IsScriptRunning() && ScriptAction.IsSaveLoadOK();
	}
	
	public CMainWin() {
		CurX = CurY = 0;
		OverlapFlags = 0;
		BgColor = CMisc.BlackPixel;
		TextDisplay = false;
		WaitMarkShowing = false;
		
		OverlapBounds.setRect(0, 0, 0, 0);
		BackShow = false;
		OverlapShow = false;
		TextShow = false;
		MenuShow = false;
		SaveShow = false;
		
		Action = NopAction;
		hFont = null;

		MusicMode = MusicCD;
		music = cdaudio;
		MusicNo = 0;
		
		ViewEffect = null;
		
		for (int i = 0; i < MenuBuffer.length; i++) {
			MenuBuffer[i] = new CMenuItem();
		}
	}
	
	@Override 
	public void OnLButtonUp(int modKeys, Point point) {
		Action.LButtonUp(modKeys, point);
	}

	@Override 
	public void OnLButtonDown(int modKeys, Point point) {
		Action.LButtonDown(modKeys, point);
	}

	@Override 
	public void OnRButtonUp(int modKeys, Point point) {
		Action.RButtonUp(modKeys, point);
	}

	@Override 
	public void OnMouseMove(int modKeys, Point point) {
		Action.MouseMove(modKeys, point);
	}
	
	@Override 
	public boolean OnIdle(int count) {
		if (ViewEffect != null) {
			if (ViewEffect.Step2(System.currentTimeMillis())) {
				return true;
			}
			StopWipe();
			Action.WipeDone();
		}
		return Action.IdleAction();
	}
	
	public void Sleep(int i) {
		//FIXME:
	}
	
	@Override 
	public boolean OnCreate() {
		trace("CMainWin::OnCreate");
		Graphics dc = getClientDC();
		if (!ViewImage.Create3(dc, CConfig.WindowWidth, CConfig.WindowHeight) || 
			!MixedImage.Create3(dc, CConfig.WindowWidth, CConfig.WindowHeight) || 
			!BackLayer.Create2(CConfig.WindowWidth, CConfig.WindowHeight) || 
			!OverlapLayer.Create2(CConfig.WindowWidth, CConfig.WindowHeight)) {
			MessageBox("内存无法配置。\n" +
				"请先关闭其他应用程序，在重新执行这个程序。");
			return false;
		}
		ViewImage.Clear();
		if ((hFont = new CFont()) == null) {
			MessageBox("找不到宋体。");
			return false;				
		}
		SetAction(ActionNop);
		OnFirstAction();
		return true;
	}
	
	public void OnFirstAction() {
		if (music != null) {
			if (!music.Open(this) && MusicMode == MusicCD) {
				MusicMode = MusicOff;
				music = null;
			}
		}
		wave.Open(this);
		StartMainMenu();
	}
	
	public void DestroyWindow() {
		//FIXME:
	}
	
	public void OnClose() {
		if (MessageBox2("确定要结束了吗?", CConfig.ApplicationTitle,
			CWindow.MB_ICONQUESTION | CWindow.MB_OKCANCEL) == CWindow.IDOK) {
			DestroyWindow();
		}
	}
	
	public Graphics getPaintDC() {
		return null;
	}
	
	public Rectangle getRcPaint() {
		return null;
	}
	
	@Override 
	public void OnPaint() {
		Graphics dc = getPaintDC();
		ViewImage.Draw3(dc, getRcPaint());
	}
	
	@Override 
	public void OnDestroy() {
		if (music != null) {
			music.Stop();
			music.Close();
			music = null;
		}
		super.OnDestroy();
	}
	
	public void OnTimer(int id) {
		KillTimer(id);
		Action.TimedOut(id);
	}
	
	public void OnCommand(int notifyCode, int id, CWindow ctrl) {
		
	}
	
	public void OnInitSubMenu(Object hMenu, int id) {
		
	}
	
	public void OnMciNotify(int flag, int id) {
		
	}
	
	public void ChangeMusicMode(int mode) {
		if (MusicMode != mode) {		
			MusicMode = mode;
			if (music != null) {
				music.Stop();
				music.Close();
				music = null;
			}
			switch (MusicMode) {
			case MusicCD:
				music = cdaudio;
				if (!music.Open(this)) {
					MusicMode = MusicOff;
					music = null;
				}
				break;
			}
			if (music != null && MusicNo > 0) {
				music.Play(MusicNo);
			}
		}
	}
	
	public boolean SetAction(int action) {
		trace("CMainWin::SetAction() " + action);
		return SetAction(action, 0);
	}
	
	public boolean SetAction(int action, int param) {
		StopWipe();
		switch (action) {
		case ActionScriptDone:
		case ActionScript:
			StopMusic();
			break;
		}
		switch (action) {
		case ActionNop:
			Action = NopAction;
			NopAction.Initialize(this);
			break;

		case ActionScriptDone:
			StartMainMenu();
			break;
		
		case ActionScriptSetup:
			ScriptAction.Setup(LoadParam);
			// no break
		
		case ActionScript:
			Action = ScriptAction;
			break;
		
		case ActionGameLoad:
			ShowLoadSaveMenu(false);
			GameLoadAction.Initialize(this);
			Action.Pause();
			Action = GameLoadAction;
			break;
		
		case ActionGameSave:
			ShowLoadSaveMenu(true);
			GameSaveAction.Initialize(this);
			Action.Pause();
			Action = GameSaveAction;
			break;
		}
		//FIXME:
		PostMessage(CConfig.WM_KICKIDLE);
		return true;
	}
	
	public boolean StartScript(String name, int mode) {
		ScriptAction.Initialize(this, mode);
		if (!ScriptAction.Load(name))
			return false;
		SetAction(ActionScript);
		return true;
	}
	
	public void StartMainMenu() {
		if (!StartScript("main", ScriptType.MODE_SYSTEM))
			DestroyWindow();
	}
	
	public void WriteMessage(String msg) {
		FormatMessage(msg);
		WaitMarkShowing = true;
		ShowMessageWindow();
	}
	
	public void HideWaitMark() {
		if (WaitMarkShowing) {
			WaitMarkShowing = false;
			if (TextShow) {
				Mixing(WaitMarkRect, TextWaitMark);
				CopyAndRepaint(WaitMarkRect);
			}
		}
	}
	
	public void OpenMenu() {
		//FIXME:
		int maxlen = MENU_MIN_WIDTH;
		MenuRect.y = MENU_Y - ((MENU_FRAME_HEIGHT * 2) + MenuCount * MENU_ITEM_HEIGHT
			- MENU_ITEM_SPACE);
		MenuRect.x = MENU_X;
		MenuRect.height = MENU_Y - MenuRect.y;
		MenuRect.width = MENU_X + (MENU_FRAME_WIDTH * 2) + maxlen - MenuRect.x;
		MenuShow = true;
		trace("CMainWin::OpenMenu == " + MenuRect.x + " " + MenuRect.y + " " + MenuRect.width + " " + MenuRect.height);
		Mixing(MenuRect);
		if (NO_FIX_UPDATE_BUG) {
			CopyAndRepaint(MenuRect);
		}
	}
	
	public void SelectMenu(int index, boolean select) {
		if (index >= 0) {
			MenuBuffer[index].color = select? CMisc.RedPixel: CMisc.WhitePixel;
			Rectangle r = new Rectangle(
				MenuRect.x + MENU_FRAME_WIDTH,
				MenuRect.y + MENU_FRAME_HEIGHT + MENU_ITEM_HEIGHT * index,
				MenuRect.width - MENU_FRAME_WIDTH * 2,
				CConfig.MessageFont
			);
			Mixing(r, MenuItem(index));
			CopyAndRepaint(r);
		}
	}
	
	public void ShowMessageWindow() {
		TextDisplay = true;
		TextShow = true;
		Invalidate(TextRect);
		Update();
	}
	
	public void HideMessageWindow() {
		HideMessageWindow(true);
	}
	
	public void HideMessageWindow(boolean update) {
		TextDisplay = false;
		if (TextShow) {
			TextShow = false;
			Invalidate(TextRect);
			if (update)
				Update();
		}
	}
	
	public void FlipMessageWindow() {
		if (TextDisplay) {
			TextShow = TextShow? false: true;
			Invalidate(TextRect);
			Update();
		}
	}
	
	public void ShowOverlapLayer(int pos) {
		if (OverlapShow) {
			if ((OverlapFlags == Center && pos != Center) ||
			    (OverlapFlags != Center && pos == Center)) {	
				trace("CMainWin::ShowOverlapLayer 显示在中间，删除所有之前显示的图形");
				Invalidate(Position[OverlapFlags]);
				OverlapFlags = None;
				OverlapBounds.setBounds(0, 0, 0, 0);
			}
		}
		OverlapFlags |= pos;
		OverlapBounds = Position[OverlapFlags];
		OverlapShow = true;
		trace("CMainWin::ShowOverlapLayer Invalidate == " + Position[pos]);
		Invalidate(Position[pos]);
	}
	
	public void HideOverlapLayer(int pos) {
		if (OverlapShow) {	
			if ((OverlapFlags == Center && pos != Center) ||
			    (OverlapFlags != Center && pos == Center)) {	
				Invalidate(Position[OverlapFlags]);
				OverlapFlags = None;
				OverlapBounds.setRect(0, 0, 0, 0);
			}
		}
		OverlapFlags &= ~pos;
		OverlapBounds = Position[OverlapFlags];
		if (OverlapFlags == None)
			OverlapShow = false;
		Invalidate(Position[pos]);
	}
	
	public void HideMenuWindow() {
		HideMenuWindow(true);
	}
	
	public void HideMenuWindow(boolean update) {
		if (MenuShow) {
			MenuShow = false;
			Invalidate(MenuRect);
			if (update)
				Update();
		}
	}
	
	public int GetMenuItemCount() { 
		return MenuCount; 
	}
	
	public int GetMenuAnser(int index) { 
		return MenuBuffer[index].anser; 
	}
	
	public void HideAllLayer(int pix) {
		BgColor = pix;
		BackShow = false;
		OverlapShow = false;
		OverlapFlags = None;
		OverlapBounds.setRect(0, 0, 0, 0);
	}
	
	
	public void Mixing(Rectangle rect) {
		Mixing(rect, 0xFFFFFFFF);
	}
	
	public void Mixing(Rectangle rect, int flags) {
		if (BackShow)
			MixedImage.Copy(BackLayer, rect);
		else
			MixedImage.FillRect(rect, BgColor);
		if (OverlapShow) {
			trace("CMainWin::Mixing, OverlapShow" + OverlapBounds.intersection(rect));
			MixedImage.MixImage(OverlapLayer, OverlapBounds.intersection(rect));
		}
		if (SaveShow) {
			if ((flags & SaveTitle) != 0) {
				MixedImage.DrawFrameRect(SAVE_X, SAVE_Y, SAVE_TITLE_WIDTH, SAVE_ITEM_HEIGHT);
				MixedImage.DrawText(hFont, SAVE_TEXT_OFFSET_X, SAVE_TEXT_OFFSET_Y,
					IsSaveMenu? "存档": "装入");
			}
			for (int i = 0; i < CParams.PARAMS_MAX_SAVE; i++) {
				if ((flags & SaveItem(i)) != 0) {
					int	y = (i + 1) * SAVE_ITEM_INTERVAL;
					MixedImage.DrawFrameRect(SAVE_X, SAVE_Y + y, SAVE_W, SAVE_ITEM_HEIGHT,
						DataTitle[i].color);
					MixedImage.DrawText(hFont, SAVE_TEXT_OFFSET_X, SAVE_TEXT_OFFSET_Y + y,
						DataTitle[i].title, DataTitle[i].color);
				}
			}
		} else {
			if (TextShow) {
				if ((flags & TextMessage) != 0) {
					//FIXME:
					trace("CMainWin::Mixing: TextRect == " + TextRect.x + " " + TextRect.y + " " + TextRect.width + " " + TextRect.height);
					MixedImage.DrawFrameRect2(TextRect);
					for (int i = 0; i < CConfig.MessageLine; i++) {
						trace("CMainWin::Mixing DrawText " + MsgX(0) + "," + MsgY(i) + "," + MsgBuffer[i]);
						MixedImage.DrawText(hFont, MsgX(0), MsgY(i), MsgBuffer[i]);
					}
				} else {
					Rectangle temp = TextRect.intersection(rect);
					if (temp.isEmpty()) {
						temp.setBounds(0, 0, 0, 0); //FIXME:Java可能会产生负数的宽高值
					}
					trace("CMainWin::Mixing: FillHalfToneRect " + temp.x + " " + temp.y + " " + temp.width + " " + temp.height);
					MixedImage.FillHalfToneRect(temp);
				}
				if (WaitMarkShowing && (flags & TextWaitMark) != 0)
					MixedImage.DrawText(hFont, MsgX(WAITMARK_X), MsgY(WAITMARK_Y), "▼");
			}
			if (MenuShow) {
				if ((flags & MenuFrame) != 0)
					MixedImage.DrawFrameRect2(MenuRect);
				else
					MixedImage.FillHalfToneRect(MenuRect.intersection(rect));
				for (int i = 0; i < MenuCount; i++) {
					if ((flags & MenuItem(i)) != 0) {
						MixedImage.DrawText(hFont,
							MenuRect.x + MENU_FRAME_WIDTH,
							MenuRect.y + MENU_FRAME_HEIGHT + MENU_ITEM_HEIGHT * i,
							MenuBuffer[i].text, MenuBuffer[i].color);
					}
				}
			}
		}
	}
	
	public boolean Update() {
		return Update(true);
	}
	
	public boolean Update(boolean repaint) {
		if (!InvalidRect.isEmpty()) {	
			if (NO_FIX_UPDATE_BUG) {
				Mixing(InvalidRect);
				if (repaint) {
					CopyAndRepaint(InvalidRect);
				}
			}
			InvalidRect.setRect(0, 0, 0, 0);
			return true;
		}
		return false;
	}
	
	public boolean LoadImageBack(String name) {
		BackShow = true;
		Invalidate(Position[Both]);
		return BackLayer.LoadImage(name);
	}
	
	public boolean LoadImageOverlap(String name) {
		return LoadImageOverlap(name, CMainWin.Both);
	}
	
	public boolean LoadImageOverlap(String name, int pos) {
		ShowOverlapLayer(pos);
		return OverlapLayer.LoadImage(name, Position[pos].x, Position[pos].y);
	}
	
	public boolean ClearImageBack() {
		BackShow = false;
		Invalidate(Position[Both]);
		return true;
	}
	
	public boolean Kinsoku(String p) {
		//FIXME:
		return false;
	}
	
	public static final int STR_LIMIT =	(CConfig.MessageWidth - 2);
	public static final int STR_WIDTH =	(STR_LIMIT - 2);
	
	public void ClearMessage() {
		HideMessageWindow();
		CurX = CurY = 0;
		for (int i = 0; i < CConfig.MessageLine; i++) {
			MsgBuffer[i] = "";
		}
	}
	
	public boolean _ismbblead(int c) {
		//FIXME:
		return false;
	}
	
	public int ____FormatMessage(String msg) {
		//FIXME:
		return 0;
	}
	
	public int FormatMessage(String msg) {
		//FIXME:
		CurX = CurY = 0;
		String[] lines = msg.split("\n", CConfig.MessageLine);
		for (int i = 0; i < CConfig.MessageLine; i++) {
			if (i < lines.length && lines[i] != null) {
				MsgBuffer[i] = lines[i];
				trace("FormatMessage" + " " + i + " " + MsgBuffer[i]);
				CurY++;
			} else {
				trace("FormatMessage" + " " + i);
				MsgBuffer[i] = "";
			}
		}
		trace("FormatMessage CurY == " + CurY);
		return CurY;
	}
	
	public void SetMenuItem(String str, int anser) {
		//FIXME:
		int n = str.length();
		MenuBuffer[MenuCount].text = str;
		MenuBuffer[MenuCount].anser = anser;
		MenuBuffer[MenuCount].length = n;
		MenuBuffer[MenuCount].color = CMisc.WhitePixel;
		MenuCount++;
	}
	
	public void ClearMenuItemCount() { 
		MenuCount = 0; 
	}
	
	public void LoadGame(int no) {
		if (!LoadParam.Load(no)) {
			MessageBox("无法读取。");
			return;
		}
		ScriptAction.Initialize(this, ScriptType.MODE_SCENARIO);
		if (ScriptAction.Load(LoadParam.last_script)) {
			HideMessageWindow(false);
			HideMenuWindow(false);
			if (SaveShow) {
				SaveShow = false;
				Invalidate(SaveRect);
			}
			Update();
			SetAction(ActionScriptSetup);
		}
	}
	
	public void SaveGame(int no, int flags) {
		if (!ScriptAction.Params.Save(no)) {
			MessageBox("无法存储。");
			return;
		}
		CancelLoadSaveMenu(flags);
	}
	
	public void ShowLoadSaveMenu(boolean isSave) {
		IsSaveMenu = isSave;
		SaveShow = true;
		for (int i = 0; i < CParams.PARAMS_MAX_SAVE; i++) {
			CParams param = new CParams();
			if (param.Load(i)) {
				DataTitle[i].activate = true;
				DataTitle[i].title = (i + 1) + ": " + 
					param.save_month + "/" + param.save_date + " " +
					param.save_hour + ":" + param.save_minute;
			} else {
				DataTitle[i].activate = IsSaveMenu? true: false;
				DataTitle[i].title = (i + 1) + ": -- no data --";
			}
			DataTitle[i].color = DataTitle[i].activate? CMisc.WhitePixel: CMisc.GrayPixel;
		}
		Invalidate(SaveRect);
		if (TextShow)
			Invalidate(TextRect);
		if (MenuShow)
			Invalidate(MenuRect);
		Update();
	}
	
	public void HideLoadSaveMenu() {
		SaveShow = false;
		Invalidate(SaveRect);
		if (TextShow)
			Invalidate(TextRect);
		if (MenuShow)
			Invalidate(MenuRect);
		Update();
	}
	
	public void CancelLoadSaveMenu(int flags) {
		HideLoadSaveMenu();
		Action = ScriptAction;
		Action.Resume();
		if ((flags & IS_TIMEDOUT) != 0)
			Action.TimedOut(TimerSleep);
		PostMessage(CConfig.WM_KICKIDLE);
	}
	
	public void SelectLoadSaveMenu(int index, boolean select) {
		if (index >= 0) {
			DataTitle[index].color = select? CMisc.RedPixel: CMisc.WhitePixel;
			int y = index * SAVE_ITEM_INTERVAL + SAVE_ITEM_INTERVAL;
			Rectangle rect = new Rectangle(
				SAVE_X, SAVE_Y + y, SAVE_W, SAVE_ITEM_HEIGHT);
			Mixing(rect, SaveItem(index));
			CopyAndRepaint(rect);
		}
	}
	
	public int GetLoadSaveSelect(Point point) {
		if (point.x >= SAVE_X && point.x < SAVE_X + SAVE_W && 
			point.y >= SAVE_Y + SAVE_ITEM_INTERVAL) {
			int index = point.y - SAVE_Y - SAVE_ITEM_INTERVAL;
			if (index % SAVE_ITEM_INTERVAL < SAVE_ITEM_HEIGHT) {
				index /= SAVE_ITEM_INTERVAL;
				if (index < CParams.PARAMS_MAX_SAVE && DataTitle[index].activate)
					return index;
			}
		}
		return -1;
	}
	
	public int NextLoadSaveSelect(int index) {
		for (int i = 1; i <= CParams.PARAMS_MAX_SAVE; i++) {
			int next = (index + i) % CParams.PARAMS_MAX_SAVE;
			if (DataTitle[next].activate)
				return next;
		}
		return -1;
	}
	
	public int PrevLoadSaveSelect(int index) {
		for (int i = CParams.PARAMS_MAX_SAVE - 1; i > 0; i--) {
			int prev = (index + i) % CParams.PARAMS_MAX_SAVE;
			if (DataTitle[prev].activate)
				return prev;
		}
		return -1;
	}
	
	public boolean StartMusic(int no) {
		if (MusicNo != no) {
			MusicNo = no;
			if (music != null) {
				music.Stop();
				return music.Play(no);
			}
		}
		return true;
	}
	
	public boolean RestartMusic() {
		if (music != null)
			return music.Replay();
		return true;
	}
	
	public boolean StopMusic() {
		MusicNo = 0;
		if (music != null)
			return music.Stop();
		return true;
	}
	
	public boolean StartWave(String name) {
		String path;
		path = CConfig.WAVEPATH + name + ".wav";
		return wave.Play2(path);
	}
	
	public void Invalidate(Rectangle rect) { 
		trace("CMainWin::Invalidate() InvalidRect == " + InvalidRect.x + "," + InvalidRect.y + "," + InvalidRect.width + "," + InvalidRect.height);
		trace("CMainWin::Invalidate() rect " + rect.x + "," + rect.y + "," + rect.width + "," + rect.height);
		if (InvalidRect.isEmpty()) {
			InvalidRect = new Rectangle(rect);
		} else if (rect.isEmpty()) {
			//InvalidRect = InvalidRect;
		} else {
			InvalidRect = InvalidRect.union(rect); 
		}
		trace("CMainWin::Invalidate() InvalidRect(2) == " + InvalidRect.x + "," + InvalidRect.y + "," + InvalidRect.width + "," + InvalidRect.height);
	}
	
	public int MsgX(int x) { 
		return x * CConfig.MessageFont / 2 + CMainWin.MSG_TEXTOFFSET_X; 
	}
	
	public int MsgY(int y) { 
		return y * (CConfig.MessageFont + CMainWin.MSG_TEXTSPACE_Y) + CMainWin.MSG_TEXTOFFSET_Y; 
	}
	
	public void ClearBack() { 
		ClearImageBack(); 
	}
	
	public void ClearCenter() { 
		HideOverlapLayer(CMainWin.Center); 
	}
	
	public void ClearLeft() { 
		HideOverlapLayer(CMainWin.Left); 
	}
	
	public void ClearRight() { 
		HideOverlapLayer(CMainWin.Right); 
	}
	
	public void ClearOverlap() { 
		HideOverlapLayer(CMainWin.Both); 
	}
	
	public boolean LoadImageLeft(String name) { 
		return LoadImageOverlap(name, CMainWin.Left); 
	}
	
	public boolean LoadImageRight(String name) { 
		return LoadImageOverlap(name, CMainWin.Right); 
	}
	
	public boolean LoadImageCenter(String name) { 
		trace("CMainWin::LoadImageCenter");
		return LoadImageOverlap(name, CMainWin.Center); 
	}
	
	public Rectangle GetInvalidRect() { 
		return InvalidRect; 
	}
}
