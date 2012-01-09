grammar Calc;

@lexer::header {
package com.iteye.weimingtom.myosotis.calc;
}

@parser::header {
package com.iteye.weimingtom.myosotis.calc;
}

@members {
public CalcDriver driver = new CalcDriver();
}

compilationUnit : statement+;

statement 
: ID '=' expr NEWLINE { driver.assign($ID.text, $expr.value); }
| 'print' expr NEWLINE { driver.print($expr.value); }
| 'list' NEWLINE { driver.list(); }
| NEWLINE
;

expr returns [Node value]
: e=mulexpr {$value = $e.value;}
( '+' e=mulexpr 
{
	Node temp = new Node(); 
	temp.op = Node.OP_PLUS;
	temp.left = $value;
	temp.right = $e.value;
	$value = temp;
}
| '-' e=mulexpr 
{
	Node temp = new Node(); 
	temp.op = Node.OP_MINUS;
	temp.left = $value;
	temp.right = $e.value;
	$value = temp;
}
)*
;

mulexpr returns [Node value]
: e=unaexpr {$value = $e.value;}
( '*' e=unaexpr 
{
	Node temp = new Node(); 
	temp.op = Node.OP_TIMES;
	temp.left = $value;
	temp.right = $e.value;
	$value = temp;
}
| '/' e=unaexpr 
{
	Node temp = new Node(); 
	temp.op = Node.OP_DIVIDE;
	temp.left = $value;
	temp.right = $e.value;
	$value = temp;
}
)*
;
		
unaexpr returns [Node value]
: ID 
{
	Node temp = new Node(); 
	temp.op = Node.OP_VALUE;
	temp.string = $ID.text;
	$value = temp;
}
| INT 
{
	Node temp = new Node(); 
	temp.op = Node.OP_CONST;
	temp.value = Integer.parseInt($INT.text);
	$value = temp;
}
| e=parexpr {$value = $e.value;}
| '-' e=unaexpr 
{
	Node temp = new Node(); 
	temp.op = Node.OP_NEG;
	temp.left = $e.value;
	temp.right = null;
	$value = temp;
}
;

parexpr returns [Node value]
: '(' e=expr ')' {$value = $e.value;}
;









ID : ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')* ;

INT : '0'..'9'+ ;

FLOAT : INT '.' INT?
	  | '.' INT
	  ;

CHAR : '\'' ( ESC | ~('\''|'\\') ) '\''
     ;

STRING :  '"' ( ESC | ~('\\'|'"') )* '"'
       ;

fragment
ESC : '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    ;

COMMENT
    : '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

LINE_COMMENT
    : '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;

NEWLINE:'\r'? '\n' ;

WS : (' '|'\t'|'\r'|'\n')+ {$channel=HIDDEN;} ;
