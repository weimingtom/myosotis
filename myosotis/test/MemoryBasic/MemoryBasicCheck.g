grammar MemoryBasicCheck;

compilationUnit : statement+;

statement 
: 'end' NEWLINE
| assign NEWLINE
| 'if' comp_expr 'then' NEWLINE 
| 'else' NEWLINE 
| 'endif' NEWLINE 
| 'for' assign 'to' e1=expr ('step' e2=expr)? NEWLINE 
| 'next' NEWLINE  
| 'while' comp_expr NEWLINE 
| 'wend' NEWLINE 
| 'print' args NEWLINE  
| NEWLINE
;

assign
: value_expr '=' expr
;

comp_expr
: e1=expr 
('==' e2=expr 
| '!=' e3=expr  
| '>' e4=expr 
| '>=' e5=expr 
| '<' e6=expr 
| '<=' e7=expr
)
;

expr
: e1=mulexpr
( '+' e2=mulexpr 
| '-' e3=mulexpr 
)*
;

mulexpr
: e1=unaexpr
( '*' e2=unaexpr 
| '/' e3=unaexpr 
| '%' e4=unaexpr
)*
;

unaexpr 
: value_expr 
| INT 
| '-' e1=unaexpr 
| '(' e2=expr ')' 
| 'rand' '(' e3=expr ')' 
;

value_expr				
: ID 
;

args
: e1=expr 
(
',' e2=expr  
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

WS 
: (' ' | '\t')+ {$channel=HIDDEN;} 
;
