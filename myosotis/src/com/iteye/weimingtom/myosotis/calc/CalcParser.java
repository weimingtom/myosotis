// $ANTLR 3.3 Nov 30, 2010 12:45:30 C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g 2012-01-08 21:46:09

package com.iteye.weimingtom.myosotis.calc;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CalcParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ID", "NEWLINE", "INT", "FLOAT", "ESC", "CHAR", "STRING", "COMMENT", "LINE_COMMENT", "WS", "'='", "'print'", "'list'", "'+'", "'-'", "'*'", "'/'", "'('", "')'"
    };
    public static final int EOF=-1;
    public static final int T__14=14;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int ID=4;
    public static final int NEWLINE=5;
    public static final int INT=6;
    public static final int FLOAT=7;
    public static final int ESC=8;
    public static final int CHAR=9;
    public static final int STRING=10;
    public static final int COMMENT=11;
    public static final int LINE_COMMENT=12;
    public static final int WS=13;

    // delegates
    // delegators


        public CalcParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public CalcParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return CalcParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g"; }


    public CalcDriver driver = new CalcDriver();



    // $ANTLR start "compilationUnit"
    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:15:1: compilationUnit : ( statement )+ ;
    public final void compilationUnit() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:15:17: ( ( statement )+ )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:15:19: ( statement )+
            {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:15:19: ( statement )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=ID && LA1_0<=NEWLINE)||(LA1_0>=15 && LA1_0<=16)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:15:19: statement
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
    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:17:1: statement : ( ID '=' expr NEWLINE | 'print' expr NEWLINE | 'list' NEWLINE | NEWLINE );
    public final void statement() throws RecognitionException {
        Token ID1=null;
        Node expr2 = null;

        Node expr3 = null;


        try {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:18:1: ( ID '=' expr NEWLINE | 'print' expr NEWLINE | 'list' NEWLINE | NEWLINE )
            int alt2=4;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt2=1;
                }
                break;
            case 15:
                {
                alt2=2;
                }
                break;
            case 16:
                {
                alt2=3;
                }
                break;
            case NEWLINE:
                {
                alt2=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:18:3: ID '=' expr NEWLINE
                    {
                    ID1=(Token)match(input,ID,FOLLOW_ID_in_statement44); 
                    match(input,14,FOLLOW_14_in_statement46); 
                    pushFollow(FOLLOW_expr_in_statement48);
                    expr2=expr();

                    state._fsp--;

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_statement50); 
                     driver.assign((ID1!=null?ID1.getText():null), expr2); 

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:19:3: 'print' expr NEWLINE
                    {
                    match(input,15,FOLLOW_15_in_statement56); 
                    pushFollow(FOLLOW_expr_in_statement58);
                    expr3=expr();

                    state._fsp--;

                    match(input,NEWLINE,FOLLOW_NEWLINE_in_statement60); 
                     driver.print(expr3); 

                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:20:3: 'list' NEWLINE
                    {
                    match(input,16,FOLLOW_16_in_statement66); 
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_statement68); 
                     driver.list(); 

                    }
                    break;
                case 4 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:21:3: NEWLINE
                    {
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_statement74); 

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


    // $ANTLR start "expr"
    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:24:1: expr returns [Node value] : e= mulexpr ( '+' e= mulexpr | '-' e= mulexpr )* ;
    public final Node expr() throws RecognitionException {
        Node value = null;

        Node e = null;


        try {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:25:1: (e= mulexpr ( '+' e= mulexpr | '-' e= mulexpr )* )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:25:3: e= mulexpr ( '+' e= mulexpr | '-' e= mulexpr )*
            {
            pushFollow(FOLLOW_mulexpr_in_expr89);
            e=mulexpr();

            state._fsp--;

            value = e;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:26:1: ( '+' e= mulexpr | '-' e= mulexpr )*
            loop3:
            do {
                int alt3=3;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==17) ) {
                    alt3=1;
                }
                else if ( (LA3_0==18) ) {
                    alt3=2;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:26:3: '+' e= mulexpr
            	    {
            	    match(input,17,FOLLOW_17_in_expr95); 
            	    pushFollow(FOLLOW_mulexpr_in_expr99);
            	    e=mulexpr();

            	    state._fsp--;


            	    	Node temp = new Node(); 
            	    	temp.op = Node.OP_PLUS;
            	    	temp.left = value;
            	    	temp.right = e;
            	    	value = temp;


            	    }
            	    break;
            	case 2 :
            	    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:34:3: '-' e= mulexpr
            	    {
            	    match(input,18,FOLLOW_18_in_expr106); 
            	    pushFollow(FOLLOW_mulexpr_in_expr110);
            	    e=mulexpr();

            	    state._fsp--;


            	    	Node temp = new Node(); 
            	    	temp.op = Node.OP_MINUS;
            	    	temp.left = value;
            	    	temp.right = e;
            	    	value = temp;


            	    }
            	    break;

            	default :
            	    break loop3;
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
    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:45:1: mulexpr returns [Node value] : e= unaexpr ( '*' e= unaexpr | '/' e= unaexpr )* ;
    public final Node mulexpr() throws RecognitionException {
        Node value = null;

        Node e = null;


        try {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:46:1: (e= unaexpr ( '*' e= unaexpr | '/' e= unaexpr )* )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:46:3: e= unaexpr ( '*' e= unaexpr | '/' e= unaexpr )*
            {
            pushFollow(FOLLOW_unaexpr_in_mulexpr131);
            e=unaexpr();

            state._fsp--;

            value = e;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:47:1: ( '*' e= unaexpr | '/' e= unaexpr )*
            loop4:
            do {
                int alt4=3;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==19) ) {
                    alt4=1;
                }
                else if ( (LA4_0==20) ) {
                    alt4=2;
                }


                switch (alt4) {
            	case 1 :
            	    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:47:3: '*' e= unaexpr
            	    {
            	    match(input,19,FOLLOW_19_in_mulexpr137); 
            	    pushFollow(FOLLOW_unaexpr_in_mulexpr141);
            	    e=unaexpr();

            	    state._fsp--;


            	    	Node temp = new Node(); 
            	    	temp.op = Node.OP_TIMES;
            	    	temp.left = value;
            	    	temp.right = e;
            	    	value = temp;


            	    }
            	    break;
            	case 2 :
            	    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:55:3: '/' e= unaexpr
            	    {
            	    match(input,20,FOLLOW_20_in_mulexpr148); 
            	    pushFollow(FOLLOW_unaexpr_in_mulexpr152);
            	    e=unaexpr();

            	    state._fsp--;


            	    	Node temp = new Node(); 
            	    	temp.op = Node.OP_DIVIDE;
            	    	temp.left = value;
            	    	temp.right = e;
            	    	value = temp;


            	    }
            	    break;

            	default :
            	    break loop4;
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
    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:66:1: unaexpr returns [Node value] : ( ID | INT | e= parexpr | '-' e= unaexpr );
    public final Node unaexpr() throws RecognitionException {
        Node value = null;

        Token ID4=null;
        Token INT5=null;
        Node e = null;


        try {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:67:1: ( ID | INT | e= parexpr | '-' e= unaexpr )
            int alt5=4;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt5=1;
                }
                break;
            case INT:
                {
                alt5=2;
                }
                break;
            case 21:
                {
                alt5=3;
                }
                break;
            case 18:
                {
                alt5=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:67:3: ID
                    {
                    ID4=(Token)match(input,ID,FOLLOW_ID_in_unaexpr173); 

                    	Node temp = new Node(); 
                    	temp.op = Node.OP_VALUE;
                    	temp.string = (ID4!=null?ID4.getText():null);
                    	value = temp;


                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:74:3: INT
                    {
                    INT5=(Token)match(input,INT,FOLLOW_INT_in_unaexpr180); 

                    	Node temp = new Node(); 
                    	temp.op = Node.OP_CONST;
                    	temp.value = Integer.parseInt((INT5!=null?INT5.getText():null));
                    	value = temp;


                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:81:3: e= parexpr
                    {
                    pushFollow(FOLLOW_parexpr_in_unaexpr189);
                    e=parexpr();

                    state._fsp--;

                    value = e;

                    }
                    break;
                case 4 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:82:3: '-' e= unaexpr
                    {
                    match(input,18,FOLLOW_18_in_unaexpr195); 
                    pushFollow(FOLLOW_unaexpr_in_unaexpr199);
                    e=unaexpr();

                    state._fsp--;


                    	Node temp = new Node(); 
                    	temp.op = Node.OP_NEG;
                    	temp.left = e;
                    	temp.right = null;
                    	value = temp;


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


    // $ANTLR start "parexpr"
    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:92:1: parexpr returns [Node value] : '(' e= expr ')' ;
    public final Node parexpr() throws RecognitionException {
        Node value = null;

        Node e = null;


        try {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:93:1: ( '(' e= expr ')' )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:93:3: '(' e= expr ')'
            {
            match(input,21,FOLLOW_21_in_parexpr215); 
            pushFollow(FOLLOW_expr_in_parexpr219);
            e=expr();

            state._fsp--;

            match(input,22,FOLLOW_22_in_parexpr221); 
            value = e;

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
    // $ANTLR end "parexpr"

    // Delegated rules


 

    public static final BitSet FOLLOW_statement_in_compilationUnit34 = new BitSet(new long[]{0x0000000000018032L});
    public static final BitSet FOLLOW_ID_in_statement44 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_statement46 = new BitSet(new long[]{0x0000000000240050L});
    public static final BitSet FOLLOW_expr_in_statement48 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_statement50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_statement56 = new BitSet(new long[]{0x0000000000240050L});
    public static final BitSet FOLLOW_expr_in_statement58 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_statement60 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_statement66 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_NEWLINE_in_statement68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_statement74 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mulexpr_in_expr89 = new BitSet(new long[]{0x0000000000060002L});
    public static final BitSet FOLLOW_17_in_expr95 = new BitSet(new long[]{0x0000000000240050L});
    public static final BitSet FOLLOW_mulexpr_in_expr99 = new BitSet(new long[]{0x0000000000060002L});
    public static final BitSet FOLLOW_18_in_expr106 = new BitSet(new long[]{0x0000000000240050L});
    public static final BitSet FOLLOW_mulexpr_in_expr110 = new BitSet(new long[]{0x0000000000060002L});
    public static final BitSet FOLLOW_unaexpr_in_mulexpr131 = new BitSet(new long[]{0x0000000000180002L});
    public static final BitSet FOLLOW_19_in_mulexpr137 = new BitSet(new long[]{0x0000000000240050L});
    public static final BitSet FOLLOW_unaexpr_in_mulexpr141 = new BitSet(new long[]{0x0000000000180002L});
    public static final BitSet FOLLOW_20_in_mulexpr148 = new BitSet(new long[]{0x0000000000240050L});
    public static final BitSet FOLLOW_unaexpr_in_mulexpr152 = new BitSet(new long[]{0x0000000000180002L});
    public static final BitSet FOLLOW_ID_in_unaexpr173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_unaexpr180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_parexpr_in_unaexpr189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_unaexpr195 = new BitSet(new long[]{0x0000000000240050L});
    public static final BitSet FOLLOW_unaexpr_in_unaexpr199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_parexpr215 = new BitSet(new long[]{0x0000000000240050L});
    public static final BitSet FOLLOW_expr_in_parexpr219 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_parexpr221 = new BitSet(new long[]{0x0000000000000002L});

}