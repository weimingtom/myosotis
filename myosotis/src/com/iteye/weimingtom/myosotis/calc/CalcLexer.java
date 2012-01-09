// $ANTLR 3.3 Nov 30, 2010 12:45:30 C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g 2012-01-08 21:46:09

package com.iteye.weimingtom.myosotis.calc;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CalcLexer extends Lexer {
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

    public CalcLexer() {;} 
    public CalcLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public CalcLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g"; }

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:7:7: ( '=' )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:7:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:8:7: ( 'print' )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:8:9: 'print'
            {
            match("print"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:9:7: ( 'list' )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:9:9: 'list'
            {
            match("list"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:10:7: ( '+' )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:10:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:11:7: ( '-' )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:11:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:12:7: ( '*' )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:12:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:13:7: ( '/' )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:13:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:14:7: ( '(' )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:14:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:15:7: ( ')' )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:15:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:104:4: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:104:6: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:104:30: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')||(LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:106:5: ( ( '0' .. '9' )+ )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:106:7: ( '0' .. '9' )+
            {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:106:7: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:106:7: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "FLOAT"
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:108:7: ( INT '.' ( INT )? | '.' INT )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                alt4=1;
            }
            else if ( (LA4_0=='.') ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:108:9: INT '.' ( INT )?
                    {
                    mINT(); 
                    match('.'); 
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:108:17: ( INT )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:108:17: INT
                            {
                            mINT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:109:6: '.' INT
                    {
                    match('.'); 
                    mINT(); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FLOAT"

    // $ANTLR start "CHAR"
    public final void mCHAR() throws RecognitionException {
        try {
            int _type = CHAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:112:6: ( '\\'' ( ESC | ~ ( '\\'' | '\\\\' ) ) '\\'' )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:112:8: '\\'' ( ESC | ~ ( '\\'' | '\\\\' ) ) '\\''
            {
            match('\''); 
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:112:13: ( ESC | ~ ( '\\'' | '\\\\' ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='\\') ) {
                alt5=1;
            }
            else if ( ((LA5_0>='\u0000' && LA5_0<='&')||(LA5_0>='(' && LA5_0<='[')||(LA5_0>=']' && LA5_0<='\uFFFF')) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:112:15: ESC
                    {
                    mESC(); 

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:112:21: ~ ( '\\'' | '\\\\' )
                    {
                    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CHAR"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:115:8: ( '\"' ( ESC | ~ ( '\\\\' | '\"' ) )* '\"' )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:115:11: '\"' ( ESC | ~ ( '\\\\' | '\"' ) )* '\"'
            {
            match('\"'); 
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:115:15: ( ESC | ~ ( '\\\\' | '\"' ) )*
            loop6:
            do {
                int alt6=3;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='\\') ) {
                    alt6=1;
                }
                else if ( ((LA6_0>='\u0000' && LA6_0<='!')||(LA6_0>='#' && LA6_0<='[')||(LA6_0>=']' && LA6_0<='\uFFFF')) ) {
                    alt6=2;
                }


                switch (alt6) {
            	case 1 :
            	    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:115:17: ESC
            	    {
            	    mESC(); 

            	    }
            	    break;
            	case 2 :
            	    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:115:23: ~ ( '\\\\' | '\"' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "ESC"
    public final void mESC() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:119:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:119:7: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
            {
            match('\\'); 
            if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "ESC"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:123:5: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:123:7: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:123:12: ( options {greedy=false; } : . )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='*') ) {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1=='/') ) {
                        alt7=2;
                    }
                    else if ( ((LA7_1>='\u0000' && LA7_1<='.')||(LA7_1>='0' && LA7_1<='\uFFFF')) ) {
                        alt7=1;
                    }


                }
                else if ( ((LA7_0>='\u0000' && LA7_0<=')')||(LA7_0>='+' && LA7_0<='\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:123:40: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            match("*/"); 

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMENT"

    // $ANTLR start "LINE_COMMENT"
    public final void mLINE_COMMENT() throws RecognitionException {
        try {
            int _type = LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:127:5: ( '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:127:7: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
            {
            match("//"); 

            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:127:12: (~ ( '\\n' | '\\r' ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\u0000' && LA8_0<='\t')||(LA8_0>='\u000B' && LA8_0<='\f')||(LA8_0>='\u000E' && LA8_0<='\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:127:12: ~ ( '\\n' | '\\r' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:127:26: ( '\\r' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0=='\r') ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:127:26: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 
            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LINE_COMMENT"

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:130:8: ( ( '\\r' )? '\\n' )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:130:9: ( '\\r' )? '\\n'
            {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:130:9: ( '\\r' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='\r') ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:130:9: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:132:4: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:132:6: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:132:6: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>='\t' && LA11_0<='\n')||LA11_0=='\r'||LA11_0==' ') ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:1:8: ( T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | ID | INT | FLOAT | CHAR | STRING | COMMENT | LINE_COMMENT | NEWLINE | WS )
        int alt12=18;
        alt12 = dfa12.predict(input);
        switch (alt12) {
            case 1 :
                // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:1:10: T__14
                {
                mT__14(); 

                }
                break;
            case 2 :
                // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:1:16: T__15
                {
                mT__15(); 

                }
                break;
            case 3 :
                // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:1:22: T__16
                {
                mT__16(); 

                }
                break;
            case 4 :
                // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:1:28: T__17
                {
                mT__17(); 

                }
                break;
            case 5 :
                // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:1:34: T__18
                {
                mT__18(); 

                }
                break;
            case 6 :
                // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:1:40: T__19
                {
                mT__19(); 

                }
                break;
            case 7 :
                // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:1:46: T__20
                {
                mT__20(); 

                }
                break;
            case 8 :
                // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:1:52: T__21
                {
                mT__21(); 

                }
                break;
            case 9 :
                // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:1:58: T__22
                {
                mT__22(); 

                }
                break;
            case 10 :
                // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:1:64: ID
                {
                mID(); 

                }
                break;
            case 11 :
                // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:1:67: INT
                {
                mINT(); 

                }
                break;
            case 12 :
                // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:1:71: FLOAT
                {
                mFLOAT(); 

                }
                break;
            case 13 :
                // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:1:77: CHAR
                {
                mCHAR(); 

                }
                break;
            case 14 :
                // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:1:82: STRING
                {
                mSTRING(); 

                }
                break;
            case 15 :
                // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:1:89: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 16 :
                // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:1:97: LINE_COMMENT
                {
                mLINE_COMMENT(); 

                }
                break;
            case 17 :
                // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:1:110: NEWLINE
                {
                mNEWLINE(); 

                }
                break;
            case 18 :
                // C:\\Documents and Settings\\Administrator\\workspace_android4\\myosotis\\grammar\\Calc.g:1:118: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA12 dfa12 = new DFA12(this);
    static final String DFA12_eotS =
        "\2\uffff\2\12\3\uffff\1\26\3\uffff\1\27\3\uffff\1\21\1\30\1\uffff"+
        "\2\12\5\uffff\3\12\1\36\1\37\2\uffff";
    static final String DFA12_eofS =
        "\40\uffff";
    static final String DFA12_minS =
        "\1\11\1\uffff\1\162\1\151\3\uffff\1\52\3\uffff\1\56\3\uffff\1\12"+
        "\1\11\1\uffff\1\151\1\163\5\uffff\1\156\2\164\2\60\2\uffff";
    static final String DFA12_maxS =
        "\1\172\1\uffff\1\162\1\151\3\uffff\1\57\3\uffff\1\71\3\uffff\1"+
        "\12\1\40\1\uffff\1\151\1\163\5\uffff\1\156\2\164\2\172\2\uffff";
    static final String DFA12_acceptS =
        "\1\uffff\1\1\2\uffff\1\4\1\5\1\6\1\uffff\1\10\1\11\1\12\1\uffff"+
        "\1\14\1\15\1\16\2\uffff\1\22\2\uffff\1\17\1\20\1\7\1\13\1\21\5\uffff"+
        "\1\3\1\2";
    static final String DFA12_specialS =
        "\40\uffff}>";
    static final String[] DFA12_transitionS = {
            "\1\21\1\20\2\uffff\1\17\22\uffff\1\21\1\uffff\1\16\4\uffff"+
            "\1\15\1\10\1\11\1\6\1\4\1\uffff\1\5\1\14\1\7\12\13\3\uffff\1"+
            "\1\3\uffff\32\12\4\uffff\1\12\1\uffff\13\12\1\3\3\12\1\2\12"+
            "\12",
            "",
            "\1\22",
            "\1\23",
            "",
            "",
            "",
            "\1\24\4\uffff\1\25",
            "",
            "",
            "",
            "\1\14\1\uffff\12\13",
            "",
            "",
            "",
            "\1\20",
            "\2\21\2\uffff\1\21\22\uffff\1\21",
            "",
            "\1\31",
            "\1\32",
            "",
            "",
            "",
            "",
            "",
            "\1\33",
            "\1\34",
            "\1\35",
            "\12\12\7\uffff\32\12\4\uffff\1\12\1\uffff\32\12",
            "\12\12\7\uffff\32\12\4\uffff\1\12\1\uffff\32\12",
            "",
            ""
    };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | ID | INT | FLOAT | CHAR | STRING | COMMENT | LINE_COMMENT | NEWLINE | WS );";
        }
    }
 

}