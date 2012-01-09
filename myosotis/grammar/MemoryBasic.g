grammar MemoryBasic;

@lexer::header {
package com.iteye.weimingtom.myosotis.memorybasic;
}

@parser::header {
package com.iteye.weimingtom.myosotis.memorybasic;
}

@members {
public Compiler driver;
}

compilationUnit : statement+;

statement 
: 'end' NEWLINE { driver.EndStatement(); }
| assign NEWLINE { driver.AssignStatement($assign.value); }
| 'if' comp_expr 'then' NEWLINE { driver.IfStatement($comp_expr.value); }
| 'else' NEWLINE { driver.ElseStatement(); }
| 'endif' NEWLINE  { driver.EndifStatement(); }
| 'for' assign 'to' e1=expr ('step' e2=expr)? NEWLINE { driver.ForStatement($assign.value, $e1.value, $e2.value); }
| 'next' NEWLINE { driver.NextStatement(); } 
| 'while' comp_expr NEWLINE { driver.WhileStatement($comp_expr.value); }
| 'wend' NEWLINE { driver.WendStatement(); }
| 'print' args NEWLINE { driver.PrintStatement($args.value); } 
| NEWLINE
;

assign returns [Assign value]
: value_expr '=' expr { $value = new Assign((int)'=', $value_expr.value, $expr.value); }
;

// NOTE: AVOID null on branch e1 !!!

comp_expr returns [Node value]
: e1=expr { $value = $e1.value; }
('==' e2=expr { $value = Node.MakeNode(driver, Node.OP_EQ, $e1.value, $e2.value); }
| '!=' e3=expr { $value = Node.MakeNode(driver, Node.OP_NE, $e1.value, $e3.value); } 
| '>' e4=expr { $value = Node.MakeNode(driver, Node.OP_GT, $e1.value, $e4.value); }
| '>=' e5=expr { $value = Node.MakeNode(driver, Node.OP_GE, $e1.value, $e5.value); }
| '<' e6=expr { $value = Node.MakeNode(driver, Node.OP_LT, $e1.value, $e6.value); }
| '<=' e7=expr { $value = Node.MakeNode(driver, Node.OP_LE, $e1.value, $e7.value); }
)
;

expr returns [Node value]
: e1=mulexpr {$value = $e1.value;}
( '+' e2=mulexpr { $value = Node.MakeNode(driver, Node.OP_PLUS, $e1.value, $e2.value); }
| '-' e3=mulexpr { $value = Node.MakeNode(driver, Node.OP_MINUS, $e1.value, $e3.value); }
)*
;

mulexpr returns [Node value]
: e1=unaexpr {$value = $e1.value;}
( '*' e2=unaexpr { $value = Node.MakeNode(driver, Node.OP_TIMES, $e1.value, $e2.value); }
| '/' e3=unaexpr { $value = Node.MakeNode(driver, Node.OP_DIVIDE, $e1.value, $e2.value); }
| '%' e4=unaexpr { $value = Node.MakeNode(driver, Node.OP_MOD, $e1.value, $e2.value); }
)*
;

unaexpr returns [Node value]
: value_expr { $value = $value_expr.value; }
| INT { $value = new Node(Node.OP_CONST, $INT.text); }
| '-' e1=unaexpr { $value = Node.MakeNode(driver, Node.OP_NEG, $e1.value); }
| '(' e2=expr ')' { $value = $e2.value;}
| 'rand' '(' e3=expr ')' { $value = Node.MakeNode(driver, Node.OP_RANDFUNC, $e3.value); }
;

value_expr returns [ValueNode value]				
: ID { $value = new ValueNode($ID.text); }
;

args returns [Args value]
: e1=expr { $value = new Args($e1.value); }
(
',' e2=expr { $value = $value.Add($e2.value); } 
)*
;

// Lexer

ID 
: ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')* 
;

INT 
: '0'..'9'+ 
;

LINE_COMMENT
: ('#' | '\'' | 'rem') ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
;

NEWLINE
: '\r'? '\n' 
;

//NOTE: Don't include '\r' and '\n' in WS
WS 
: (' ' | '\t')+ {$channel=HIDDEN;} 
;
