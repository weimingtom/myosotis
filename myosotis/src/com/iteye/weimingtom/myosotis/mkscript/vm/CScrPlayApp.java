package com.iteye.weimingtom.myosotis.mkscript.vm;

public class CScrPlayApp {
	public CMainWin MainWin;
	
	public CScrPlayApp() {
		CFile.loadData("data/main.scr", "data/sample3.dat");
		CFile.loadBitmap("cgdata/bg001", "cgdata/bg001.JPG");
		CFile.loadBitmap("cgdata/bg002", "cgdata/bg002.JPG");
		CFile.loadBitmap("cgdata/bg003", "cgdata/bg003.JPG");
		CFile.loadBitmap("cgdata/event1", "cgdata/event1.JPG");
		CFile.loadBitmap("cgdata/megu111", "cgdata/MEGU111.png");
		CFile.loadBitmap("cgdata/megu112", "cgdata/MEGU112.png");
		CFile.loadBitmap("cgdata/megu113", "cgdata/MEGU113.png");
		CFile.loadBitmap("cgdata/megu121", "cgdata/MEGU121.png");
		CFile.loadBitmap("cgdata/megu122", "cgdata/MEGU122.png");
		CFile.loadBitmap("cgdata/megu123", "cgdata/MEGU123.png");
		CFile.loadBitmap("cgdata/megu211", "cgdata/MEGU211.png");
		CFile.loadBitmap("cgdata/megu212", "cgdata/MEGU212.png");
		CFile.loadBitmap("cgdata/megu213", "cgdata/MEGU213.png");
		CFile.loadBitmap("cgdata/megu221", "cgdata/MEGU221.png");
		CFile.loadBitmap("cgdata/megu222", "cgdata/MEGU222.png");
		CFile.loadBitmap("cgdata/megu223", "cgdata/MEGU223.png");
		CFile.loadBitmap("cgdata/megu311", "cgdata/MEGU311.png");
		CFile.loadBitmap("cgdata/megu312", "cgdata/MEGU312.png");
		CFile.loadBitmap("cgdata/megu313", "cgdata/MEGU313.png");
		CFile.loadBitmap("cgdata/megu321", "cgdata/MEGU321.png");
		CFile.loadBitmap("cgdata/megu322", "cgdata/MEGU322.png");
		CFile.loadBitmap("cgdata/megu323", "cgdata/MEGU323.png");
		CFile.loadBitmap("cgdata/mesi111", "cgdata/mesi111.png");
		CFile.loadBitmap("cgdata/sino111", "cgdata/SINO111.png");
		CFile.loadBitmap("cgdata/sino112", "cgdata/SINO112.png");
		CFile.loadBitmap("cgdata/sino113", "cgdata/SINO113.png");
		CFile.loadBitmap("cgdata/sino121", "cgdata/SINO121.png");
		CFile.loadBitmap("cgdata/sino122", "cgdata/SINO122.png");
		CFile.loadBitmap("cgdata/sino123", "cgdata/SINO123.png");
		CFile.loadBitmap("cgdata/sino211", "cgdata/SINO211.png");
		CFile.loadBitmap("cgdata/sino212", "cgdata/SINO212.png");
		CFile.loadBitmap("cgdata/sino213", "cgdata/SINO213.png");
		CFile.loadBitmap("cgdata/sino221", "cgdata/SINO221.png");
		CFile.loadBitmap("cgdata/sino222", "cgdata/SINO222.png");
		CFile.loadBitmap("cgdata/sino223", "cgdata/SINO223.png");
		CFile.loadBitmap("cgdata/sino224", "cgdata/SINO224.png");
		CFile.loadBitmap("cgdata/sino311", "cgdata/SINO311.png");
		CFile.loadBitmap("cgdata/sino312", "cgdata/SINO312.png");
		CFile.loadBitmap("cgdata/sino313", "cgdata/SINO313.png");
		CFile.loadBitmap("cgdata/sino321", "cgdata/SINO321.png");
		CFile.loadBitmap("cgdata/sino322", "cgdata/SINO322.png");
		CFile.loadBitmap("cgdata/sino323", "cgdata/SINO323.png");
		CFile.loadBitmap("cgdata/sino411", "cgdata/SINO411.png");
		CFile.loadBitmap("cgdata/sino412", "cgdata/SINO412.png");
		CFile.loadBitmap("cgdata/sino413", "cgdata/SINO413.png");
		CFile.loadBitmap("cgdata/sino421", "cgdata/SINO421.png");
		CFile.loadBitmap("cgdata/sino422", "cgdata/SINO422.png");
		CFile.loadBitmap("cgdata/sino423", "cgdata/SINO423.png");
		CFile.loadBitmap("cgdata/title001", "cgdata/title001.JPG");
		
		MainWin = new CMainWin();
		MainWin.start();
	}
	
	public static void main(String[] args) {
		new CScrPlayApp();
	}
}
