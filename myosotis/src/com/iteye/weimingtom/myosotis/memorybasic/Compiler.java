package com.iteye.weimingtom.myosotis.memorybasic;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

/**
 * @author Administrator
 * 
 */
public class Compiler {
	private ValueTable variables = new ValueTable();
	//VMCode 
	private List<VMCode> statement = new ArrayList<VMCode>();
	//Label
	private List<Label> labels = new ArrayList<Label>();
	//State
	private Stack<State> state_stack = new Stack<State>();
	//TempValue
	private List<TempValue> temp_value = new ArrayList<TempValue>();
	private int error_count = 0;
	private String file;
	
	//--------------------------------------------
	
	public void PushConst(int arg1) {
		statement.add(new VMCode(VMCode.VM_PUSHCONST, arg1));
	}
	
	public void PushValue(int arg1) {
		statement.add(new VMCode(VMCode.VM_PUSHVALUE, arg1));
	}
	
	public void PopValue(int arg1) {
		statement.add(new VMCode(VMCode.VM_POPVALUE, arg1));
	}
	
	public void OpPop() {
		statement.add(new VMCode(VMCode.VM_POP));
	}
	
	public void OpMovC(int arg1, int arg2) {
		statement.add(new VMCode(VMCode.VM_MOV_C, arg1, arg2));
	}

	public void OpMovV(int arg1, int arg2) {
		statement.add(new VMCode(VMCode.VM_MOV_V, arg1, arg2));
	}
	
	public void OpNegC(int arg1, int arg2) {
		statement.add(new VMCode(VMCode.VM_NEG_C, arg1, arg2));
	}
	
	public void OpNegV(int arg1, int arg2) {
		statement.add(new VMCode(VMCode.VM_NEG_V, arg1, arg2));
	}
	
	public void OpEqCC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_EQ_CC, arg1, arg2, arg3));
	}

	public void OpEqVC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_EQ_VC, arg1, arg2, arg3));
	}	
	
	public void OpEqCV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_EQ_CV, arg1, arg2, arg3));
	}
	
	public void OpEqVV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_EQ_VV, arg1, arg2, arg3));
	}
	
	public void OpNeCC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_NE_CC, arg1, arg2, arg3));
	}
	
	public void OpNeVC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_NE_VC, arg1, arg2, arg3));
	}
	
	public void OpNeCV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_NE_CV, arg1, arg2, arg3));
	}
	
	public void OpNeVV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_NE_VV, arg1, arg2, arg3));
	}
	
	public void OpGtCC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_GT_CC, arg1, arg2, arg3));
	}
	
	public void OpGtVC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_GT_VC, arg1, arg2, arg3));
	}
	
	public void OpGtCV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_GT_CV, arg1, arg2, arg3));
	}
	
	public void OpGtVV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_GT_VV, arg1, arg2, arg3));
	}
	
	public void OpGeCC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_GE_CC, arg1, arg2, arg3));
	}
	
	public void OpGeVC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_GE_VC, arg1, arg2, arg3));
	}
	
	public void OpGeCV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_GE_CV, arg1, arg2, arg3));
	}
	
	public void OpGeVV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_GE_VV, arg1, arg2, arg3));
	}
	
	public void OpLtCC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_LT_CC, arg1, arg2, arg3));
	}
	
	public void OpLtVC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_LT_VC, arg1, arg2, arg3));
	}
	
	public void OpLtCV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_LT_CV, arg1, arg2, arg3));
	}
	
	public void OpLtVV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_LT_VV, arg1, arg2, arg3));
	}
	
	public void OpLeCC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_LE_CC, arg1, arg2, arg3));
	}
	
	public void OpLeVC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_LE_VC, arg1, arg2, arg3));
	}

	public void OpLeCV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_LE_CV, arg1, arg2, arg3));
	}
	
	public void OpLeVV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_LE_VV, arg1, arg2, arg3));
	}
	
	public void OpAddCC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_ADD_CC, arg1, arg2, arg3));
	}
	
	public void OpAddVC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_ADD_VC, arg1, arg2, arg3));
	}
	
	public void OpAddCV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_ADD_CV, arg1, arg2, arg3));
	}
	
	public void OpAddVV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_ADD_VV, arg1, arg2, arg3));
	}
	
	public void OpSubCC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_SUB_CC, arg1, arg2, arg3));
	}
	
	public void OpSubVC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_SUB_VC, arg1, arg2, arg3));
	}
	
	public void OpSubCV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_SUB_CV, arg1, arg2, arg3));
	}
	
	public void OpSubVV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_SUB_VV, arg1, arg2, arg3));
	}

	public void OpMulCC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_MUL_CC, arg1, arg2, arg3));
	}
	
	public void OpMulVC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_MUL_VC, arg1, arg2, arg3));
	}
	
	public void OpMulCV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_MUL_CV, arg1, arg2, arg3));
	}
	
	public void OpMulVV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_MUL_VV, arg1, arg2, arg3));
	}
	
	public void OpDivCC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_DIV_CC, arg1, arg2, arg3));
	}
	
	public void OpDivVC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_DIV_VC, arg1, arg2, arg3));
	}
	
	public void OpDivCV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_DIV_CV, arg1, arg2, arg3));
	}
	
	public void OpDivVV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_DIV_VV, arg1, arg2, arg3));
	}

	public void OpModCC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_MOD_CC, arg1, arg2, arg3));
	}
	
	public void OpModVC(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_MOD_VC, arg1, arg2, arg3));
	}
	
	public void OpModCV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_MOD_CV, arg1, arg2, arg3));
	}
	
	public void OpModVV(int arg1, int arg2, int arg3) {
		statement.add(new VMCode(VMCode.VM_MOD_VV, arg1, arg2, arg3));
	}
	
	public void OpJmp(int arg1) {
		statement.add(new VMCode(VMCode.VM_JMP, arg1));
	}	
	
	public void OpJmpC(int arg1, int arg2) {
		statement.add(new VMCode(VMCode.VM_JMPC, arg1, arg2));
	}
	
	public void OpJmpNC(int arg1, int arg2) {
		statement.add(new VMCode(VMCode.VM_JMPNC, arg1, arg2));
	}
	
	public void OpPrint(int arg1) {
		statement.add(new VMCode(VMCode.VM_PRINT, arg1));
	}
	
	public void OpRandC(int arg1, int arg2) {
		statement.add(new VMCode(VMCode.VM_RAND_C, arg1, arg2));
	}
	
	public void OpRandV(int arg1, int arg2) {
		statement.add(new VMCode(VMCode.VM_RAND_V, arg1, arg2));
	}

	public void OpHalt() {
		statement.add(new VMCode(VMCode.VM_HALT));
	}
	
	//--------------------------------------------
	
	public Compiler() {
		
	}
	
	public boolean compile(String str, VMData data) {
		file = str; 
		//FIXME:
		int result = 0;
		//
		ANTLRStringStream input = new ANTLRStringStream(str);
		MemoryBasicLexer lexer = new MemoryBasicLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		MemoryBasicParser parser = new MemoryBasicParser(tokens);
		// Share data here 
		parser.driver = this;
		try {
			parser.compilationUnit();
		} catch (RecognitionException e) {
			e.printStackTrace();
			result = -1;
		} finally {
			parser.driver = null;
		}
		if (result != 0) {
			return false;
		}
		while (!state_stack.empty()) {
			State state = state_stack.peek();
			switch (state.getState()) {
			case State.STATE_IF:
				error("if does not match endif");
				break;

			case State.STATE_FOR:
				error("for does not match next");
				state.setEnd(null);
				state.setStep(null);
				break;

			case State.STATE_WHILE:
				error("while does not match wend");
				break;
			}
			state_stack.pop();
		}
		//FIXME:
		VMCode code = statement.get(statement.size() - 1);
		if (code.getOp() != VMCode.VM_HALT) {
			OpHalt();
		}
		int code_size = LabelSetting();
		//FIXME:CraeteData
		//from statements to data (dump to byte array) 
		CreateData(data, code_size);
		return error_count == 0;
	}
	
	public void error(String m) {
		System.err.println(m);
		error_count++;
	}
	
	public void debug_dump() {
		System.out.println("---variables---");
		variables.dump();
		String[] op_name = VMCode.VM_OP_NAMES;
		System.out.println("---code---");
		int	pos = 0;
		int size = statement.size();
		for (int i = 0; i < size; i++) {
			VMCode code = (VMCode)statement.get(i);
			System.out.print(pos + ": " + op_name[code.getOp()]);
			if (code.getSize() > 1) {
				System.out.print(", " + code.getArg1());
				if (code.getSize() > 5) {
					System.out.print(", " + code.getArg2());
					if (code.getSize() > 9) {
						System.out.print(", " + code.getArg3());
					}
				}
			}
			System.out.println();
			if (code.getOp() != VMCode.VM_MAXCOMMAND) {
				pos += code.getSize();
			}
		}
		System.out.println("---");
	}
	
	public int GetTempAddr(int value) {
		return temp_value.get(value).getAddr();
	}
	
	public ValueTag GetValueTag(String name) {
		return variables.find(name);
	}
	
	public ValueTag AddValue(String name) {
		if (variables.add(name, 1)) {
			return variables.find(name);
		}
		return null;
	}
	
	//--------------------------------------------
	
	public NodeValue MakeNeg(int ret, NodeValue value) {
		NodeValue v = NodeValue.MakeTempValue(this, ret);
		if (value.getType() == NodeValue.CONST) {
			OpNegC(v.value(), value.value());
		} else {
			OpNegV(v.value(), value.value());
		}
		return v;
	}
	
	public NodeValue MakeRand(int ret, NodeValue value) {
		NodeValue v = NodeValue.MakeTempValue(this, ret);
		if (value.getType() == NodeValue.CONST) {
			OpRandC(v.value(), value.value());
		} else {
			OpRandV(v.value(), value.value());
		}
		return v;
	}
	
	public NodeValue MakeMoveC(int ret, int value) {
		NodeValue v = NodeValue.MakeTempValue(this, ret);
		OpMovC(v.value(), value);
		return v;
	}
	
	public NodeValue MakeMoveV(int ret, NodeValue value) {
		NodeValue v = NodeValue.MakeTempValue(this, ret);
		OpMovV(v.value(), value.value());
		return v;
	}
	
	public NodeValue MakeOp(int op, int ret, NodeValue left, NodeValue right) {
		NodeValue v = NodeValue.MakeTempValue(this, ret);
		int code = 0;
		switch (op) {
		case Node.OP_EQ:		
			code = VMCode.VM_EQ_CC;	
			break;
		
		case Node.OP_NE:		
			code = VMCode.VM_NE_CC;	
			break;
			
		case Node.OP_GT:		
			code = VMCode.VM_GT_CC;	
			break;
		  
		case Node.OP_GE:		
			code = VMCode.VM_GE_CC;	
			break;
		  
		case Node.OP_LT:		
			code = VMCode.VM_LT_CC;	
			break;
		  
		case Node.OP_LE:		
			code = VMCode.VM_LE_CC;	
			break;
		  
		case Node.OP_MINUS:	
			code = VMCode.VM_SUB_CC;	
			break;
		  
		case Node.OP_PLUS:		
			code = VMCode.VM_ADD_CC;	
			break;
		  
		case Node.OP_TIMES:	
			code = VMCode.VM_MUL_CC;	
			break;
		  
		case Node.OP_DIVIDE:	
			code = VMCode.VM_DIV_CC;	
			break;
		  
		case Node.OP_MOD:		
			code = VMCode.VM_MOD_CC;	
			break;
		}
		if (left.getType() != NodeValue.CONST) {
			code++;
		}
		if (right.getType() != NodeValue.CONST) {
			code += 2;
		}
		statement.add(new VMCode(code, v.value(), left.value(), right.value()));
		return v;
	}
	
	public void MakeJmpC(int label, NodeValue value) {
		OpJmpC(label, value.value());
	}
	
	public void MakeJmpNC(int label, NodeValue value) {
		OpJmpNC(label, value.value());
	}
	
	public void MakePush(NodeValue value) {
		if (value.getType() == NodeValue.CONST) {
			PushConst(value.value());
		} else {
			PushValue(value.value());
		}
	}
	
	public int LabelSetting() {
		int pos = 0;
		Iterator<VMCode> it = statement.iterator();
		while(it.hasNext()) {
			VMCode code = it.next();
			if (code.getOp() == VMCode.VM_MAXCOMMAND) {
				labels.get(code.getArg1()).setPos(pos);
			} else {
				pos += code.getSize();
			}
		}
		Iterator<VMCode> it2 = statement.iterator();
		while(it2.hasNext()) {
			VMCode code = it2.next();
			switch (code.getOp()) {
			case VMCode.VM_JMP:
			case VMCode.VM_JMPC:
			case VMCode.VM_JMPNC:
				code.setArg1(labels.get(code.getArg1()).getPos());
				break;
			}
		}
		return pos;
	}
	
	public int MakeLabel() {
		int index = labels.size();
		labels.add(new Label(index));
		return index;
	}
	
	public void SetLabel(int label) {
		statement.add(new VMCode(VMCode.VM_MAXCOMMAND, label));
	}
	
	//FIXME:CraeteData
	public boolean CreateData(VMData data, int code_size) {
		data.command = new byte[code_size];
		data.command_size = code_size;
		data.value_size = variables.size();
		Iterator<VMCode> it = statement.iterator();
		int p = 0;
		// NOTE: I forget "while" isn't 'if' !!!
		while (it.hasNext()) {
			VMCode code = it.next();
			p = code.Get(data.command, p);
		}
		return true;
	}
	
	public int AllocTempValue() {
		int size = temp_value.size();
		for (int i = 0; i<size; i++) {
			if (temp_value.get(i).getRef() == 0) {
				temp_value.get(i).setRef(1);
				return i;
			}
		}
		String str = "#" + size;
		ValueTag tag = AddValue(str);
		temp_value.add(new TempValue(1, tag.getAddr()));
		return size;
	}

	public void UseTempValue(int value) {
		TempValue v = temp_value.get(value);
		v.setRef(v.getRef() + 1);
	}

	public void ReleaseTempValue(int value) {
		TempValue v = temp_value.get(value);
		v.setRef(v.getRef() - 1);
	}
	
	//-------------------------------------
	
	public void AssignStatement(Assign assign) {
		assign.analyze(this);
	}
	
	public void IfStatement(Node expr) {
		int label = MakeLabel();
		MakeJmpNC(label, NodeValue.MakeNodeValue(this, expr));
		state_stack.push(new State(State.STATE_IF, label));
	}
	
	public void ElseStatement() {
		if (state_stack.empty() || 
			state_stack.peek().getState() != State.STATE_IF) {
			error("if doesn't have else");
		} else {
			State state = state_stack.peek();
			int label = MakeLabel();
			OpJmp(label);
			SetLabel(state.getLabel1());
			state.setLabel1(label);
		}
	}
	
	public void EndifStatement() {
		if (state_stack.empty() || 
			state_stack.peek().getState() != State.STATE_IF) {
			error("if doesn't have endif");
		} else {
			State state = state_stack.peek();
			SetLabel(state.getLabel1());
			state_stack.pop();
		}
	}
	
	public void ForStatement(Assign start, Node end, Node step) {
		int label = MakeLabel();
		NodeValue counter = start.analyze(this);
		SetLabel(label);
		state_stack.push(new State(State.STATE_FOR, label, counter, end, step));
	}
	
	public void NextStatement() {
		if (state_stack.empty() || 
			state_stack.peek().getState() != State.STATE_FOR) {
			error("for doesn't have next");
		} else {
			State state = state_stack.peek();
			int label = MakeLabel();
			MakeJmpC(label, MakeOp(Node.OP_EQ, -1, state.getCounter(), 
					NodeValue.MakeNodeValue(this, state.getEnd())));
			{
				NodeValue step = new NodeValue(NodeValue.CONST, 1);
				if (state.getStep() != null) {
					step = NodeValue.MakeNodeValue(this, state.getStep());
				}
				//FIXME:
				MakeOp(Node.OP_PLUS, state.getCounter().getValue(), state.getCounter(), step);
			}
			OpJmp(state.getLabel1());
			SetLabel(label);
			state.setEnd(null);
			state.setStep(null);
			state_stack.pop();
		}
	}
	
	public void WhileStatement(Node expr) {
		int label1 = MakeLabel();
		int label2 = MakeLabel();
		SetLabel(label1);
		MakeJmpNC(label2, NodeValue.MakeNodeValue(this, expr));
		state_stack.push(new State(State.STATE_WHILE, label1, label2));
		expr = null;
	}
	
	public void WendStatement() {
		if (state_stack.empty() || state_stack.peek().getState() != State.STATE_WHILE) {
			error("while does not match wend");
		} else {
			State state = state_stack.peek();
			OpJmp(state.getLabel1());
			SetLabel(state.getLabel2());
			state_stack.pop();
		}
	}
	
	public void EndStatement() {
		OpHalt();
	}
	
	public void PrintStatement(Args args) {
		int arg_count = 0;
		if (args != null) {
			Iterator<Node> it = args.getIterator();
			while (it.hasNext()) {
				Node node = it.next();
				NodeValue value = NodeValue.MakeNodeValue(this, node);
				this.MakePush(value);
			}
			arg_count = args.getSize();
		}
		OpPrint(arg_count);
		args = null;
	}
}
