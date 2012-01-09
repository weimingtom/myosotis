package com.iteye.weimingtom.myosotis.memorybasic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VCPU {
	public static final int STACK_SIZE = 1000;
	private Random rand = new Random();
	private VMData data;
	private byte[] command;
	private int command_ptr;
	private int command_size;

	private VMStack stack = new VMStack();
	private List<Integer> global_value = new ArrayList<Integer>();
	
	public VCPU(VMData data) {
		this.data = data;
	}
	
	/**
	 * Get arguments from bytes.
	 * @see VMCode
	 */
	public int[] value(int n) { 
		int[] ret = new int[n];
		for(int i = 0; i < n; i++) {
			ret[i] = this.command[this.command_ptr++] << 0;
			ret[i] |= this.command[this.command_ptr++] << 8;
			ret[i] |= this.command[this.command_ptr++] << 16;
			ret[i] |= this.command[this.command_ptr++] << 24;
		}
		return ret;
	}
	
	public int addr() { 
		return this.command_ptr; 
	}
	
	public void jmp(int addr) { 
		this.command_ptr = addr; 
	}
	
	public void push(int v) throws StackOverflowException { 
		stack.push(v); 
	}
	
	public void pop() { 
		stack.pop(); 
	}
	
	public int top() { 
		return stack.top(); 
	}
	
	//--------------------------------------
	
	private void PushConst(int value) throws StackOverflowException {
		push(value);
	}

	private void PushValue(int value) throws StackOverflowException {
		push((Integer)global_value.get(value));
	}

	private void PopValue(int value)
	{
		global_value.set(value, (Integer)top()); 
		pop();
	}

	public void OpPop() {
		pop();
	}

	private void OpMovC(int result, int arg) {
		global_value.set(result, new Integer(arg));
	}

	private void OpMovV(int result, int arg) {
		global_value.set(result, (Integer)global_value.get(arg));
	}

	private void OpNegC(int result, int arg) {
		global_value.set(result, new Integer(-arg));
	}

	private void OpNegV(int result, int arg) {
		global_value.set(result, new Integer(-(Integer)global_value.get(arg)));
	}

	// ==
	private void OpEqCC(int result, int arg1, int arg2) {
		global_value.set(result, new Integer((arg1 == arg2) ? 1 : 0));
	}

	private void OpEqVC(int result, int arg1, int arg2) {
		global_value.set(result, new Integer((Integer)global_value.get(arg1) == arg2 ? 1 : 0));
	}

	private void OpEqCV(int result, int arg1, int arg2) {
		global_value.set(result, new Integer(arg1 == (Integer)global_value.get(arg2) ? 1 : 0));
	}

	private void OpEqVV(int result, int arg1, int arg2) {
		global_value.set(result, new Integer((Integer)global_value.get(arg1) == (Integer)global_value.get(arg2) ? 1 : 0));
	}

	// !=
	private void OpNeCC(int result, int arg1, int arg2) {
		global_value.set(result, new Integer(arg1 != arg2 ? 1 : 0));
	}

	private void OpNeVC(int result, int arg1, int arg2) {
		global_value.set(result, new Integer((Integer)global_value.get(arg1) != arg2 ? 1 : 0));
	}

	private void OpNeCV(int result, int arg1, int arg2) {
		global_value.set(result, new Integer(arg1 != (Integer)global_value.get(arg2) ? 1 : 0));
	}

	private void OpNeVV(int result, int arg1, int arg2) {
		global_value.set(result, new Integer((Integer)global_value.get(arg1) != (Integer)global_value.get(arg2) ? 1 : 0));
	}

	// >
	private void OpGtCC(int result, int arg1, int arg2) {
		global_value.set(result, new Integer(arg1 > arg2 ? 1 : 0));
	}

	private void OpGtVC(int result, int arg1, int arg2) {
		global_value.set(result, new Integer((Integer)global_value.get(arg1) > arg2 ? 1 : 0));
	}

	private void OpGtCV(int result, int arg1, int arg2) {
		global_value.set(result, new Integer(arg1 > (Integer)global_value.get(arg2) ? 1 : 0));
	}

	private void OpGtVV(int result, int arg1, int arg2) {
		global_value.set(result, new Integer((Integer)global_value.get(arg1) > (Integer)global_value.get(arg2) ? 1 : 0));
	}

	// >=
	private void OpGeCC(int result, int arg1, int arg2) {
		global_value.set(result, new Integer(arg1 >= arg2 ? 1 : 0));
	}

	private void OpGeVC(int result, int arg1, int arg2) {
		global_value.set(result, new Integer((Integer)global_value.get(arg1) >= arg2 ? 1 : 0));
	}

	private void OpGeCV(int result, int arg1, int arg2) {
		global_value.set(result, new Integer(arg1 >= (Integer)global_value.get(arg2) ? 1 : 0));
	}

	private void OpGeVV(int result, int arg1, int arg2) {
		global_value.set(result, new Integer((Integer)global_value.get(arg1) >= (Integer)global_value.get(arg2) ? 1 : 0));
	}

	// <
	private void OpLtCC(int result, int arg1, int arg2) {
		global_value.set(result, new Integer(arg1 < arg2 ? 1 : 0));
	}

	private void OpLtVC(int result, int arg1, int arg2) {
		global_value.set(result, new Integer((Integer)global_value.get(arg1) < arg2 ? 1 : 0));
	}

	private void OpLtCV(int result, int arg1, int arg2) {
		global_value.set(result, new Integer(arg1 < (Integer)global_value.get(arg2) ? 1 : 0));
	}

	private void OpLtVV(int result, int arg1, int arg2) {
		global_value.set(result, new Integer((Integer)global_value.get(arg1) < (Integer)global_value.get(arg2) ? 1 : 0));
	}

	// <=
	private void OpLeCC(int result, int arg1, int arg2) {
		global_value.set(result, new Integer(arg1 <= arg2 ? 1 : 0));
	}

	private void OpLeVC(int result, int arg1, int arg2) {
		global_value.set(result, new Integer((Integer)global_value.get(arg1) <= arg2 ? 1 : 0));
	}

	private void OpLeCV(int result, int arg1, int arg2) {
		global_value.set(result, new Integer(arg1 <= (Integer)global_value.get(arg2) ? 1 : 0));
	}

	private void OpLeVV(int result, int arg1, int arg2) {
		global_value.set(result, new Integer((Integer)global_value.get(arg1) <= (Integer)global_value.get(arg2) ? 1 : 0));
	}

	// +
	private void OpAddCC(int result, int arg1, int arg2) {
		global_value.set(result, new Integer(arg1 + arg2));
	}

	private void OpAddVC(int result, int arg1, int arg2) {
		global_value.set(result, new Integer((Integer)global_value.get(arg1) + arg2));
	}

	private void OpAddCV(int result, int arg1, int arg2) {
		global_value.set(result, new Integer(arg1 + (Integer)global_value.get(arg2)));
	}

	private void OpAddVV(int result, int arg1, int arg2) {
		global_value.set(result, new Integer((Integer)global_value.get(arg1) + (Integer)global_value.get(arg2)));
	}

	// -
	private void OpSubCC(int result, int arg1, int arg2) {
		global_value.set(result, new Integer(arg1 - arg2));
	}

	private void OpSubVC(int result, int arg1, int arg2) {
		global_value.set(result, new Integer((Integer)global_value.get(arg1) - arg2));
	}

	private void OpSubCV(int result, int arg1, int arg2) {
		global_value.set(result, new Integer(arg1 - (Integer)global_value.get(arg2)));
	}

	private void OpSubVV(int result, int arg1, int arg2) {
		global_value.set(result, new Integer((Integer)global_value.get(arg1) - (Integer)global_value.get(arg2)));
	}

	// *
	private void OpMulCC(int result, int arg1, int arg2) {
		global_value.set(result, new Integer(arg1 * arg2));
	}

	private void OpMulVC(int result, int arg1, int arg2) {
		global_value.set(result, new Integer((Integer)global_value.get(arg1) * arg2));
	}

	private void OpMulCV(int result, int arg1, int arg2) {
		global_value.set(result, new Integer(arg1 * (Integer)global_value.get(arg2)));
	}

	private void OpMulVV(int result, int arg1, int arg2) {
		global_value.set(result, new Integer((Integer)global_value.get(arg1) * (Integer)global_value.get(arg2)));
	}

	// /
	private void OpDivCC(int result, int arg1, int arg2) throws DevideByZeroException {
		if (arg2 == 0) {
			throw new DevideByZeroException();
		}
		global_value.set(result, new Integer(arg1 / arg2));
	}

	private void OpDivVC(int result, int arg1, int arg2) throws DevideByZeroException {
		if (arg2 == 0) {
			throw new DevideByZeroException();
		}
		global_value.set(result, new Integer((Integer)global_value.get(arg1) / arg2));
	}

	private void OpDivCV(int result, int arg1, int arg2) throws DevideByZeroException {
		arg2 = (Integer)global_value.get(arg2);
		if (arg2 == 0) {
			throw new DevideByZeroException();
		}
		global_value.set(result, new Integer(arg1 / arg2));
	}

	private void OpDivVV(int result, int arg1, int arg2) throws DevideByZeroException {
		arg2 = (Integer)global_value.get(arg2);
		if (arg2 == 0) {
			throw new DevideByZeroException();
		}
		global_value.set(result, new Integer((Integer)global_value.get(arg1) / arg2));
	}

	// %
	private void OpModCC(int result, int arg1, int arg2) throws DevideByZeroException {
		if (arg2 == 0) {
			throw new DevideByZeroException();
		}
		global_value.set(result, new Integer(arg1 % arg2));
	}

	private void OpModVC(int result, int arg1, int arg2) throws DevideByZeroException {
		if (arg2 == 0) {
			throw new DevideByZeroException();
		}
		global_value.set(result, new Integer((Integer)global_value.get(arg1) % arg2));
	}

	private void OpModCV(int result, int arg1, int arg2) throws DevideByZeroException {
		arg2 = (Integer)global_value.get(arg2);
		if (arg2 == 0) {
			throw new DevideByZeroException();
		}
		global_value.set(result, new Integer(arg1 % arg2));
	}

	private void OpModVV(int result, int arg1, int arg2) throws DevideByZeroException {
		arg2 = (Integer)global_value.get(arg2);
		if (arg2 == 0) {
			throw new DevideByZeroException();
		}
		global_value.set(result, new Integer((Integer)global_value.get(arg1) % arg2));
	}

	private void OpJmp(int addr) {
		jmp(addr);
	}

	private void OpJmpC(int addr, int arg) {
		if (global_value.get(arg) == null || 
			(Integer)global_value.get(arg) == 0) {
			jmp(addr);
		}
	}

	private void OpJmpNC(int addr, int arg) {
		if (global_value.get(arg) == null || 
			(Integer)global_value.get(arg) == 0) {
			jmp(addr);
		}
	}

	private void OpHalt() {
		
	}

	private int rand() {
		return rand.nextInt();
	}
	
	private void OpRandC(int result, int arg) {
		global_value.set(result, new Integer((arg <= 0) ? 0 : (rand() % arg)));
	}

	private void OpRandV(int result, int arg) {
		arg = (Integer)global_value.get(arg);
		global_value.set(result, new Integer((arg <= 0) ? 0 : (rand() % arg)));
	}

	private void OpPrint(int count) {
		while (count > 0) {
			count--;
			System.out.print(top());
			pop();
			if (count != 0) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}
	
	//-----------------------------------
	/**
	 * @see VMCode
	 * @return
	 */
	public int run() {
		this.command = this.data.command;
		this.command_size = this.data.command_size;
		
		//FIXME:???
		//global_value.resize(this.data.value_size);
		global_value.clear();
		for (int i = 0; i < this.data.value_size; i++) {
			global_value.add(0);
		}
		//
		
		this.command_ptr = 0;
		try {
			while (this.command[command_ptr] != VMCode.VM_HALT) {
				int op = this.command[this.command_ptr++];
				int v[];
				switch (op) {
				case VMCode.VM_PUSHCONST:
					v = value(1);
					PushConst(v[0]);
					break;
					
				case VMCode.VM_PUSHVALUE:
					v = value(1);
					PushValue(v[0]);
					break;

				case VMCode.VM_POPVALUE:
					v = value(1);
					PopValue(v[0]);
					break;
	
				case VMCode.VM_POP:
					OpPop();
					break;
					
				case VMCode.VM_MOV_C:
					v = value(2);
					OpMovC(v[0], v[1]);
					break;
					
				case VMCode.VM_MOV_V:
					v = value(2);
					OpMovV(v[0], v[1]);
					break;
					
				case VMCode.VM_NEG_C:
					v = value(2);
					OpNegC(v[0], v[1]);
					break;
					
				case VMCode.VM_NEG_V:
					v = value(2);
					OpNegV(v[0], v[1]);
					break;
					
				case VMCode.VM_EQ_CC:
					v = value(3);
					OpEqCC(v[0], v[1], v[2]);
					break;

				case VMCode.VM_EQ_VC:
					v = value(3);
					OpEqVC(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_EQ_CV:
					v = value(3);
					OpEqCV(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_EQ_VV:
					v = value(3);
					OpEqVV(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_NE_CC:
					v = value(3);
					OpNeCC(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_NE_VC:
					v = value(3);
					OpNeVC(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_NE_CV:
					v = value(3);
					OpNeCV(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_NE_VV:
					v = value(3);
					OpNeVV(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_GT_CC:
					v = value(3);
					OpGtCC(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_GT_VC:
					v = value(3);
					OpGtVC(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_GT_CV:
					v = value(3);
					OpGtCV(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_GT_VV:
					v = value(3);
					OpGtVV(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_GE_CC:
					v = value(3);
					OpGeCC(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_GE_VC:
					v = value(3);
					OpGeVC(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_GE_CV:
					v = value(3);
					OpGeCV(v[0], v[1], v[2]);
					break;

				case VMCode.VM_GE_VV:
					v = value(3);
					OpGeVV(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_LT_CC:
					v = value(3);
					OpLtCC(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_LT_VC:
					v = value(3);
					OpLtVC(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_LT_CV:
					v = value(3);
					OpLtCV(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_LT_VV:
					v = value(3);
					OpLtVV(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_LE_CC:
					v = value(3);
					OpLeCC(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_LE_VC:
					v = value(3);
					OpLeVC(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_LE_CV:
					v = value(3);
					OpLeCV(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_LE_VV:
					v = value(3);
					OpLeVV(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_ADD_CC:
					v = value(3);
					OpAddCC(v[0], v[1], v[2]);
					break;
	
				case VMCode.VM_ADD_VC:
					v = value(3);
					OpAddVC(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_ADD_CV:
					v = value(3);
					OpAddCV(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_ADD_VV:
					v = value(3);
					OpAddVV(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_SUB_CC:
					v = value(3);
					OpSubCC(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_SUB_VC:
					v = value(3);
					OpSubVC(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_SUB_CV:
					v = value(3);
					OpSubCV(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_SUB_VV:
					v = value(3);
					OpSubVV(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_MUL_CC:
					v = value(3);
					OpMulCC(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_MUL_VC:
					v = value(3);
					OpMulVC(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_MUL_CV:
					v = value(3);
					OpMulCV(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_MUL_VV:
					v = value(3);
					OpMulVV(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_DIV_CC:
					v = value(3);
					OpDivCC(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_DIV_VC:
					v = value(3);
					OpDivVC(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_DIV_CV:
					v = value(3);
					OpDivCV(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_DIV_VV:
					v = value(3);
					OpDivVV(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_MOD_CC:
					v = value(3);
					OpModCC(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_MOD_VC:
					v = value(3);
					OpModVC(v[0], v[1], v[2]);
					break;
					
				case VMCode.VM_MOD_CV:
					v = value(3);
					OpModCV(v[0], v[1], v[2]);
					break;

				case VMCode.VM_MOD_VV:
					v = value(3);
					OpModVV(v[0], v[1], v[2]);
					break;
	
				case VMCode.VM_JMP:
					v = value(1);
					OpJmp(v[0]);
					break;			

				case VMCode.VM_JMPC:
					v = value(2);
					OpJmpC(v[0], v[1]);
					break;
					
				case VMCode.VM_JMPNC:
					v = value(2);
					OpJmpNC(v[0], v[1]);
					break;
					
				case VMCode.VM_PRINT:
					v = value(1);
					OpPrint(v[0]);
					break;
					
				case VMCode.VM_RAND_C:
					v = value(2);
					OpRandC(v[0], v[1]);
					break;
					
				case VMCode.VM_RAND_V:
					v = value(2);
					OpRandV(v[0], v[1]);
					break;
					
				case VMCode.VM_HALT:
					OpHalt();
					break;
					
				default:
					break;
				}
			}
		} catch (StackOverflowException e) {
			e.printStackTrace();
			return -1;
		} catch (DevideByZeroException e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
}
