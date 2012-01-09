package com.iteye.weimingtom.myosotis.memorybasic;

/**
 * op   : 1 byte
 * arg1 : 4 bytes
 * arg2 : 4 bytes
 * arg3 : 4 bytes
 */
public class VMCode {
	public static final int VM_PUSHCONST = 0;
	public static final int VM_PUSHVALUE = 1;
	public static final int VM_POPVALUE = 2;
	public static final int VM_POP = 3;
	public static final int VM_MOV_C = 4;
	public static final int VM_MOV_V = 5;
	public static final int VM_NEG_C = 6;
	public static final int VM_NEG_V = 7;
	public static final int VM_EQ_CC = 8;
	public static final int VM_EQ_VC = 9;
	public static final int VM_EQ_CV = 10;
	public static final int VM_EQ_VV = 11;
	public static final int VM_NE_CC = 12;
	public static final int VM_NE_VC = 13;
	public static final int VM_NE_CV = 14;
	public static final int VM_NE_VV = 15;
	public static final int VM_GT_CC = 16;
	public static final int VM_GT_VC = 17;
	public static final int VM_GT_CV = 18;
	public static final int VM_GT_VV = 19;
	public static final int VM_GE_CC = 20;
	public static final int VM_GE_VC = 21;
	public static final int VM_GE_CV = 22;
	public static final int VM_GE_VV = 23;
	public static final int VM_LT_CC = 24;
	public static final int VM_LT_VC = 25;
	public static final int VM_LT_CV = 26;
	public static final int VM_LT_VV = 27;
	public static final int VM_LE_CC = 28;
	public static final int VM_LE_VC = 29;
	public static final int VM_LE_CV = 30;
	public static final int VM_LE_VV = 31;
	public static final int VM_ADD_CC = 32;
	public static final int VM_ADD_VC = 33;
	public static final int VM_ADD_CV = 34;
	public static final int VM_ADD_VV = 35;
	public static final int VM_SUB_CC = 36;
	public static final int VM_SUB_VC = 37;
	public static final int VM_SUB_CV = 38;
	public static final int VM_SUB_VV = 39;
	public static final int VM_MUL_CC = 40;
	public static final int VM_MUL_VC = 41;
	public static final int VM_MUL_CV = 42;
	public static final int VM_MUL_VV = 43;
	public static final int VM_DIV_CC = 44;
	public static final int VM_DIV_VC = 45;
	public static final int VM_DIV_CV = 46;
	public static final int VM_DIV_VV = 47;
	public static final int VM_MOD_CC = 48;
	public static final int VM_MOD_VC = 49;
	public static final int VM_MOD_CV = 50;
	public static final int VM_MOD_VV = 51;
	public static final int VM_JMP = 52;
	public static final int VM_JMPC = 53;
	public static final int VM_JMPNC = 54;
	public static final int VM_PRINT = 55;
	public static final int VM_RAND_C = 56;
	public static final int VM_RAND_V = 57;
	public static final int VM_HALT = 58;
	public static final int VM_MAXCOMMAND = 59; //TODO:
	
	public static final String[] VM_OP_NAMES = {
		"PushConst",
		"PushValue",
		"PopValue",
		"OpPop",
		"OpMovC",
		"OpMovV",
		"OpNegC",
		"OpNegV",
		"OpEqCC",
		"OpEqVC",
		"OpEqCV",
		"OpEqVV",
		"OpNeCC",
		"OpNeVC",
		"OpNeCV",
		"OpNeVV",
		"OpGtCC",
		"OpGtVC",
		"OpGtCV",
		"OpGtVV",
		"OpGeCC",
		"OpGeVC",
		"OpGeCV",
		"OpGeVV",
		"OpLtCC",
		"OpLtVC",
		"OpLtCV",
		"OpLtVV",
		"OpLeCC",
		"OpLeVC",
		"OpLeCV",
		"OpLeVV",
		"OpAddCC",
		"OpAddVC",
		"OpAddCV",
		"OpAddVV",
		"OpSubCC",
		"OpSubVC",
		"OpSubCV",
		"OpSubVV",
		"OpMulCC",
		"OpMulVC",
		"OpMulCV",
		"OpMulVV",
		"OpDivCC",
		"OpDivVC",
		"OpDivCV",
		"OpDivVV",
		"OpModCC",
		"OpModVC",
		"OpModCV",
		"OpModVV",
		"OpJmp",
		"OpJmpC",
		"OpJmpNC",
		"OpPrint",
		"OpRandC",
		"OpRandV",
		"OpHalt",	
		"LABEL",
	};
	
	private int size;
	private int op;
	private int arg1;
	private int arg2;
	private int arg3;
	
	public VMCode(int op) {
		this.size = 1;
		this.op = op;
		this.arg1 = 0;
	}
	
	public VMCode(int op, int arg1) {
		this.size = 5;
		this.op = op;
		this.arg1 = arg1;
	}
	
	public VMCode(int op, int arg1, int arg2) {
		this.size = 9;
		this.op = op;
		this.arg1 = arg1;
		this.arg2 = arg2;
	}
	
	public VMCode(int op, int arg1, int arg2, int arg3){
		this.size = 13;
		this.op = op;
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.arg3 = arg3;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getOp() {
		return op;
	}

	public void setOp(int op) {
		this.op = op;
	}

	public int getArg1() {
		return arg1;
	}

	public void setArg1(int arg1) {
		this.arg1 = arg1;
	}

	public int getArg2() {
		return arg2;
	}

	public void setArg2(int arg2) {
		this.arg2 = arg2;
	}

	public int getArg3() {
		return arg3;
	}

	public void setArg3(int arg3) {
		this.arg3 = arg3;
	}
	
	public int Get(byte[] data, int p) {
		//FIXME:
		if (this.op != VMCode.VM_MAXCOMMAND) {
			data[p++] = (byte)this.op;
			if (this.size > 1) {
				data[p++] = (byte)(this.arg1 >>> 0);
				data[p++] = (byte)(this.arg1 >>> 8);
				data[p++] = (byte)(this.arg1 >>> 16);
				data[p++] = (byte)(this.arg1 >>> 24);
			}
			if (this.size > 5) {
				data[p++] = (byte)(this.arg2 >>> 0);
				data[p++] = (byte)(this.arg2 >>> 8);
				data[p++] = (byte)(this.arg2 >>> 16);
				data[p++] = (byte)(this.arg2 >>> 24);
			}
			if (this.size > 9) {
				data[p++] = (byte)(this.arg3 >>> 0);
				data[p++] = (byte)(this.arg3 >>> 8);
				data[p++] = (byte)(this.arg3 >>> 16);
				data[p++] = (byte)(this.arg3 >>> 24);
			}
		}
		return p;
	}
	
	
}
