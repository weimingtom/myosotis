// $ANTLR 3.3 Nov 30, 2010 12:45:30 C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g 2012-01-08 21:46:09

package com.iteye.weimingtom.myosotis.memorybasic;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class MemoryBasicParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "NEWLINE", "INT", "ID", "LINE_COMMENT", "WS", "'end'", "'if'", "'then'", "'else'", "'endif'", "'for'", "'to'", "'step'", "'next'", "'while'", "'wend'", "'print'", "'='", "'=='", "'!='", "'>'", "'>='", "'<'", "'<='", "'+'", "'-'", "'*'", "'/'", "'%'", "'('", "')'", "'rand'", "','"
    };
    public static final int EOF=-1;
    public static final int T__9=9;
    public static final int T__10=10;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int NEWLINE=4;
    public static final int INT=5;
    public static final int ID=6;
    public static final int LINE_COMMENT=7;
    public static final int WS=8;

    // delegates
    // delegators


        public MemoryBasicParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public MemoryBasicParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return MemoryBasicParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g"; }


    public Compiler driver;



    // $ANTLR start "compilationUnit"
    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:15:1: compilationUnit : ( statement )+ ;
    public final void compilationUnit() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:15:17: ( ( statement )+ )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:15:19: ( statement )+
            {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:15:19: ( statement )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==NEWLINE||LA1_0==ID||(LA1_0>=9 && LA1_0<=10)||(LA1_0>=12 && LA1_0<=14)||(LA1_0>=17 && LA1_0<=20)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:15:19: statement
            	    {
            	    pushFollow(FOLLOW_statement_in_compilationUnit34);
            	    statement();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "compilationUnit"


    // $ANTLR start "statement"
    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:17:1: statement : ( 'end' NEWLINE | assign NEWLINE | 'if' comp_expr 'then' NEWLINE | 'else' NEWLINE | 'endif' NEWLINE | 'for' assign 'to' e1= expr ( 'step' e2= expr )? NEWLINE | 'next' NEWLINE | 'while' comp_expr NEWLINE | 'wend' NEWLINE | 'print' args NEWLINE | NEWLINE );
    public final void statement() throws RecognitionException {
        Node e1 = null;

        Node e2 = null;

        Assign assign1 = null;

        Node comp_expr2 = null;

        Assign assign3 = null;

        Node comp_expr4 = null;

        Args args5 = null;


        try {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:18:1: ( 'end' NEWLINE | assign NEWLINE | 'if' comp_expr 'then' NEWLINE | 'else' NEWLINE | 'endif' NEWLINE | 'for' assign 'to' e1= expr ( 'step' e2= expr )? NEWLINE | 'next' NEWLINE | 'while' comp_expr NEWLINE | 'wend' NEWLINE | 'print' args NEWLINE | NEWLINE )
            int alt3=11;
            switch ( input.LA(1) ) {
            case 9:
                {
                alt3=1;
                }
                break;
            case ID:
                {
                alt3=2;
                }
                break;
            case 10:
                {
                alt3=3;
                }
                break;
            case 12:
                {
                alt3=4;
                }
                break;
            case 13:
                {
                alt3=5;
                }
                break;
            case 14:
                {
                alt3=6;
                }
                break;
            case 17:
                {
                alt3=7;
                }
                break;
            case 18:
                {
                alt3=8;
                }
                break;
            case 19:
                {
                alt3=9;
                }
                break;
            case 20:
                {
                alt3=10;
                }
                break;
            case NEWLINE:
                {
                alt3=11;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:18:3: 'end' NEWLINE
                    {
                    match(input,9,FOLLOW_9_in_statement44); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_statement46); 
                     driver.EndStatement(); 

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:19:3: assign NEWLINE
                    {
                    pushFollow(FOLLOW_assign_in_statement52);
                    assign1=assign();

                    state._fsp--;

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_statement54); 
                     driver.AssignStatement(assign1); 

                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:20:3: 'if' comp_expr 'then' NEWLINE
                    {
                    match(input,10,FOLLOW_10_in_statement60); 
                    pushFollow(FOLLOW_comp_expr_in_statement62);
                    comp_expr2=comp_expr();

                    state._fsp--;

                    match(input,11,FOLLOW_11_in_statement64); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_statement66); 
                     driver.IfStatement(comp_expr2); 

                    }
                    break;
                case 4 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:21:3: 'else' NEWLINE
                    {
                    match(input,12,FOLLOW_12_in_statement72); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_statement74); 
                     driver.ElseStatement(); 

                    }
                    break;
                case 5 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:22:3: 'endif' NEWLINE
                    {
                    match(input,13,FOLLOW_13_in_statement80); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_statement82); 
                     driver.EndifStatement(); 

                    }
                    break;
                case 6 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:23:3: 'for' assign 'to' e1= expr ( 'step' e2= expr )? NEWLINE
                    {
                    match(input,14,FOLLOW_14_in_statement89); 
                    pushFollow(FOLLOW_assign_in_statement91);
                    assign3=assign();

                    state._fsp--;

                    match(input,15,FOLLOW_15_in_statement93); 
                    pushFollow(FOLLOW_expr_in_statement97);
                    e1=expr();

                    state._fsp--;

                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:23:29: ( 'step' e2= expr )?
                    int alt2=2;
                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==16) ) {
                        alt2=1;
                    }
                    switch (alt2) {
                        case 1 :
                            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:23:30: 'step' e2= expr
                            {
                            match(input,16,FOLLOW_16_in_statement100); 
                            pushFollow(FOLLOW_expr_in_statement104);
                            e2=expr();

                            state._fsp--;


                            }
                            break;

                    }

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_statement108); 
                     driver.ForStatement(assign3, e1, e2); 

                    }
                    break;
                case 7 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:24:3: 'next' NEWLINE
                    {
                    match(input,17,FOLLOW_17_in_statement114); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_statement116); 
                     driver.NextStatement(); 

                    }
                    break;
                case 8 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:25:3: 'while' comp_expr NEWLINE
                    {
                    match(input,18,FOLLOW_18_in_statement123); 
                    pushFollow(FOLLOW_comp_expr_in_statement125);
                    comp_expr4=comp_expr();

                    state._fsp--;

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_statement127); 
                     driver.WhileStatement(comp_expr4); 

                    }
                    break;
                case 9 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:26:3: 'wend' NEWLINE
                    {
                    match(input,19,FOLLOW_19_in_statement133); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_statement135); 
                     driver.WendStatement(); 

                    }
                    break;
                case 10 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:27:3: 'print' args NEWLINE
                    {
                    match(input,20,FOLLOW_20_in_statement141); 
                    pushFollow(FOLLOW_args_in_statement143);
                    args5=args();

                    state._fsp--;

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_statement145); 
                     driver.PrintStatement(args5); 

                    }
                    break;
                case 11 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:28:3: NEWLINE
                    {
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_statement152); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "statement"


    // $ANTLR start "assign"
    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:31:1: assign returns [Assign value] : value_expr '=' expr ;
    public final Assign assign() throws RecognitionException {
        Assign value = null;

        ValueNode value_expr6 = null;

        Node expr7 = null;


        try {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:32:1: ( value_expr '=' expr )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:32:3: value_expr '=' expr
            {
            pushFollow(FOLLOW_value_expr_in_assign165);
            value_expr6=value_expr();

            state._fsp--;

            match(input,21,FOLLOW_21_in_assign167); 
            pushFollow(FOLLOW_expr_in_assign169);
            expr7=expr();

            state._fsp--;

             value = new Assign((int)'=', value_expr6, expr7); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "assign"


    // $ANTLR start "comp_expr"
    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:37:1: comp_expr returns [Node value] : e1= expr ( '==' e2= expr | '!=' e3= expr | '>' e4= expr | '>=' e5= expr | '<' e6= expr | '<=' e7= expr ) ;
    public final Node comp_expr() throws RecognitionException {
        Node value = null;

        Node e1 = null;

        Node e2 = null;

        Node e3 = null;

        Node e4 = null;

        Node e5 = null;

        Node e6 = null;

        Node e7 = null;


        try {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:38:1: (e1= expr ( '==' e2= expr | '!=' e3= expr | '>' e4= expr | '>=' e5= expr | '<' e6= expr | '<=' e7= expr ) )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:38:3: e1= expr ( '==' e2= expr | '!=' e3= expr | '>' e4= expr | '>=' e5= expr | '<' e6= expr | '<=' e7= expr )
            {
            pushFollow(FOLLOW_expr_in_comp_expr188);
            e1=expr();

            state._fsp--;

             value = e1; 
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:39:1: ( '==' e2= expr | '!=' e3= expr | '>' e4= expr | '>=' e5= expr | '<' e6= expr | '<=' e7= expr )
            int alt4=6;
            switch ( input.LA(1) ) {
            case 22:
                {
                alt4=1;
                }
                break;
            case 23:
                {
                alt4=2;
                }
                break;
            case 24:
                {
                alt4=3;
                }
                break;
            case 25:
                {
                alt4=4;
                }
                break;
            case 26:
                {
                alt4=5;
                }
                break;
            case 27:
                {
                alt4=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:39:2: '==' e2= expr
                    {
                    match(input,22,FOLLOW_22_in_comp_expr193); 
                    pushFollow(FOLLOW_expr_in_comp_expr197);
                    e2=expr();

                    state._fsp--;

                     value = Node.MakeNode(driver, Node.OP_EQ, e1, e2); 

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:40:3: '!=' e3= expr
                    {
                    match(input,23,FOLLOW_23_in_comp_expr203); 
                    pushFollow(FOLLOW_expr_in_comp_expr207);
                    e3=expr();

                    state._fsp--;

                     value = Node.MakeNode(driver, Node.OP_NE, e1, e3); 

                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:41:3: '>' e4= expr
                    {
                    match(input,24,FOLLOW_24_in_comp_expr214); 
                    pushFollow(FOLLOW_expr_in_comp_expr218);
                    e4=expr();

                    state._fsp--;

                     value = Node.MakeNode(driver, Node.OP_GT, e1, e4); 

                    }
                    break;
                case 4 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:42:3: '>=' e5= expr
                    {
                    match(input,25,FOLLOW_25_in_comp_expr224); 
                    pushFollow(FOLLOW_expr_in_comp_expr228);
                    e5=expr();

                    state._fsp--;

                     value = Node.MakeNode(driver, Node.OP_GE, e1, e5); 

                    }
                    break;
                case 5 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:43:3: '<' e6= expr
                    {
                    match(input,26,FOLLOW_26_in_comp_expr234); 
                    pushFollow(FOLLOW_expr_in_comp_expr238);
                    e6=expr();

                    state._fsp--;

                     value = Node.MakeNode(driver, Node.OP_LT, e1, e6); 

                    }
                    break;
                case 6 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:44:3: '<=' e7= expr
                    {
                    match(input,27,FOLLOW_27_in_comp_expr244); 
                    pushFollow(FOLLOW_expr_in_comp_expr248);
                    e7=expr();

                    state._fsp--;

                     value = Node.MakeNode(driver, Node.OP_LE, e1, e7); 

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "comp_expr"


    // $ANTLR start "expr"
    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:48:1: expr returns [Node value] : e1= mulexpr ( '+' e2= mulexpr | '-' e3= mulexpr )* ;
    public final Node expr() throws RecognitionException {
        Node value = null;

        Node e1 = null;

        Node e2 = null;

        Node e3 = null;


        try {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:49:1: (e1= mulexpr ( '+' e2= mulexpr | '-' e3= mulexpr )* )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:49:3: e1= mulexpr ( '+' e2= mulexpr | '-' e3= mulexpr )*
            {
            pushFollow(FOLLOW_mulexpr_in_expr267);
            e1=mulexpr();

            state._fsp--;

            value = e1;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:50:1: ( '+' e2= mulexpr | '-' e3= mulexpr )*
            loop5:
            do {
                int alt5=3;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==28) ) {
                    alt5=1;
                }
                else if ( (LA5_0==29) ) {
                    alt5=2;
                }


                switch (alt5) {
            	case 1 :
            	    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:50:3: '+' e2= mulexpr
            	    {
            	    match(input,28,FOLLOW_28_in_expr273); 
            	    pushFollow(FOLLOW_mulexpr_in_expr277);
            	    e2=mulexpr();

            	    state._fsp--;

            	     value = Node.MakeNode(driver, Node.OP_PLUS, e1, e2); 

            	    }
            	    break;
            	case 2 :
            	    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:51:3: '-' e3= mulexpr
            	    {
            	    match(input,29,FOLLOW_29_in_expr283); 
            	    pushFollow(FOLLOW_mulexpr_in_expr287);
            	    e3=mulexpr();

            	    state._fsp--;

            	     value = Node.MakeNode(driver, Node.OP_MINUS, e1, e3); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "expr"


    // $ANTLR start "mulexpr"
    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:55:1: mulexpr returns [Node value] : e1= unaexpr ( '*' e2= unaexpr | '/' e3= unaexpr | '%' e4= unaexpr )* ;
    public final Node mulexpr() throws RecognitionException {
        Node value = null;

        Node e1 = null;

        Node e2 = null;

        Node e3 = null;

        Node e4 = null;


        try {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:56:1: (e1= unaexpr ( '*' e2= unaexpr | '/' e3= unaexpr | '%' e4= unaexpr )* )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:56:3: e1= unaexpr ( '*' e2= unaexpr | '/' e3= unaexpr | '%' e4= unaexpr )*
            {
            pushFollow(FOLLOW_unaexpr_in_mulexpr307);
            e1=unaexpr();

            state._fsp--;

            value = e1;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:57:1: ( '*' e2= unaexpr | '/' e3= unaexpr | '%' e4= unaexpr )*
            loop6:
            do {
                int alt6=4;
                switch ( input.LA(1) ) {
                case 30:
                    {
                    alt6=1;
                    }
                    break;
                case 31:
                    {
                    alt6=2;
                    }
                    break;
                case 32:
                    {
                    alt6=3;
                    }
                    break;

                }

                switch (alt6) {
            	case 1 :
            	    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:57:3: '*' e2= unaexpr
            	    {
            	    match(input,30,FOLLOW_30_in_mulexpr313); 
            	    pushFollow(FOLLOW_unaexpr_in_mulexpr317);
            	    e2=unaexpr();

            	    state._fsp--;

            	     value = Node.MakeNode(driver, Node.OP_TIMES, e1, e2); 

            	    }
            	    break;
            	case 2 :
            	    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:58:3: '/' e3= unaexpr
            	    {
            	    match(input,31,FOLLOW_31_in_mulexpr323); 
            	    pushFollow(FOLLOW_unaexpr_in_mulexpr327);
            	    e3=unaexpr();

            	    state._fsp--;

            	     value = Node.MakeNode(driver, Node.OP_DIVIDE, e1, e2); 

            	    }
            	    break;
            	case 3 :
            	    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:59:3: '%' e4= unaexpr
            	    {
            	    match(input,32,FOLLOW_32_in_mulexpr333); 
            	    pushFollow(FOLLOW_unaexpr_in_mulexpr337);
            	    e4=unaexpr();

            	    state._fsp--;

            	     value = Node.MakeNode(driver, Node.OP_MOD, e1, e2); 

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "mulexpr"


    // $ANTLR start "unaexpr"
    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:63:1: unaexpr returns [Node value] : ( value_expr | INT | '-' e1= unaexpr | '(' e2= expr ')' | 'rand' '(' e3= expr ')' );
    public final Node unaexpr() throws RecognitionException {
        Node value = null;

        Token INT9=null;
        Node e1 = null;

        Node e2 = null;

        Node e3 = null;

        ValueNode value_expr8 = null;


        try {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:64:1: ( value_expr | INT | '-' e1= unaexpr | '(' e2= expr ')' | 'rand' '(' e3= expr ')' )
            int alt7=5;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt7=1;
                }
                break;
            case INT:
                {
                alt7=2;
                }
                break;
            case 29:
                {
                alt7=3;
                }
                break;
            case 33:
                {
                alt7=4;
                }
                break;
            case 35:
                {
                alt7=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:64:3: value_expr
                    {
                    pushFollow(FOLLOW_value_expr_in_unaexpr355);
                    value_expr8=value_expr();

                    state._fsp--;

                     value = value_expr8; 

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:65:3: INT
                    {
                    INT9=(Token)match(input,INT,FOLLOW_INT_in_unaexpr361); 
                     value = new Node(Node.OP_CONST, (INT9!=null?INT9.getText():null)); 

                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:66:3: '-' e1= unaexpr
                    {
                    match(input,29,FOLLOW_29_in_unaexpr367); 
                    pushFollow(FOLLOW_unaexpr_in_unaexpr371);
                    e1=unaexpr();

                    state._fsp--;

                     value = Node.MakeNode(driver, Node.OP_NEG, e1); 

                    }
                    break;
                case 4 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:67:3: '(' e2= expr ')'
                    {
                    match(input,33,FOLLOW_33_in_unaexpr377); 
                    pushFollow(FOLLOW_expr_in_unaexpr381);
                    e2=expr();

                    state._fsp--;

                    match(input,34,FOLLOW_34_in_unaexpr383); 
                     value = e2;

                    }
                    break;
                case 5 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:68:3: 'rand' '(' e3= expr ')'
                    {
                    match(input,35,FOLLOW_35_in_unaexpr389); 
                    match(input,33,FOLLOW_33_in_unaexpr391); 
                    pushFollow(FOLLOW_expr_in_unaexpr395);
                    e3=expr();

                    state._fsp--;

                    match(input,34,FOLLOW_34_in_unaexpr397); 
                     value = Node.MakeNode(driver, Node.OP_RANDFUNC, e3); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "unaexpr"


    // $ANTLR start "value_expr"
    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:71:1: value_expr returns [ValueNode value] : ID ;
    public final ValueNode value_expr() throws RecognitionException {
        ValueNode value = null;

        Token ID10=null;

        try {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:72:1: ( ID )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:72:3: ID
            {
            ID10=(Token)match(input,ID,FOLLOW_ID_in_value_expr416); 
             value = new ValueNode((ID10!=null?ID10.getText():null)); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "value_expr"


    // $ANTLR start "args"
    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:75:1: args returns [Args value] : e1= expr ( ',' e2= expr )* ;
    public final Args args() throws RecognitionException {
        Args value = null;

        Node e1 = null;

        Node e2 = null;


        try {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:76:1: (e1= expr ( ',' e2= expr )* )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:76:3: e1= expr ( ',' e2= expr )*
            {
            pushFollow(FOLLOW_expr_in_args433);
            e1=expr();

            state._fsp--;

             value = new Args(e1); 
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:77:1: ( ',' e2= expr )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==36) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\MemoryBasic.g:78:1: ',' e2= expr
            	    {
            	    match(input,36,FOLLOW_36_in_args439); 
            	    pushFollow(FOLLOW_expr_in_args443);
            	    e2=expr();

            	    state._fsp--;

            	     value = value.Add(e2); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "args"

    // Delegated rules


 

    public static final BitSet FOLLOW_statement_in_compilationUnit34 = new BitSet(new long[]{0x00000000001E7652L});
    public static final BitSet FOLLOW_9_in_statement44 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_statement46 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_statement52 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_statement54 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_statement60 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_comp_expr_in_statement62 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_11_in_statement64 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_statement66 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_statement72 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_statement74 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_statement80 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_statement82 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_14_in_statement89 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_assign_in_statement91 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_statement93 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_expr_in_statement97 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_16_in_statement100 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_expr_in_statement104 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_statement108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_statement114 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_statement116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_statement123 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_comp_expr_in_statement125 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_statement127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_statement133 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_statement135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_statement141 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_args_in_statement143 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_statement145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_statement152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_expr_in_assign165 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_assign167 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_expr_in_assign169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_comp_expr188 = new BitSet(new long[]{0x000000000FC00000L});
    public static final BitSet FOLLOW_22_in_comp_expr193 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_expr_in_comp_expr197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_comp_expr203 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_expr_in_comp_expr207 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_comp_expr214 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_expr_in_comp_expr218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_comp_expr224 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_expr_in_comp_expr228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_comp_expr234 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_expr_in_comp_expr238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_comp_expr244 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_expr_in_comp_expr248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mulexpr_in_expr267 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_28_in_expr273 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_mulexpr_in_expr277 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_29_in_expr283 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_mulexpr_in_expr287 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_unaexpr_in_mulexpr307 = new BitSet(new long[]{0x00000001C0000002L});
    public static final BitSet FOLLOW_30_in_mulexpr313 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_unaexpr_in_mulexpr317 = new BitSet(new long[]{0x00000001C0000002L});
    public static final BitSet FOLLOW_31_in_mulexpr323 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_unaexpr_in_mulexpr327 = new BitSet(new long[]{0x00000001C0000002L});
    public static final BitSet FOLLOW_32_in_mulexpr333 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_unaexpr_in_mulexpr337 = new BitSet(new long[]{0x00000001C0000002L});
    public static final BitSet FOLLOW_value_expr_in_unaexpr355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_unaexpr361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_unaexpr367 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_unaexpr_in_unaexpr371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_unaexpr377 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_expr_in_unaexpr381 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_unaexpr383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_unaexpr389 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_33_in_unaexpr391 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_expr_in_unaexpr395 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_unaexpr397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_value_expr416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_args433 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_36_in_args439 = new BitSet(new long[]{0x0000000A20000060L});
    public static final BitSet FOLLOW_expr_in_args443 = new BitSet(new long[]{0x0000001000000002L});

}