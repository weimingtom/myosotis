@set GENHOME=%~dp0

@set OUT_DIR=%GENHOME%src\com\iteye\weimingtom\myosotis\memorybasic
@del %OUT_DIR%\*.tokens
@del %OUT_DIR%\*Lexer.java
@del %OUT_DIR%\*Parser.java
@java -cp ".;%GENHOME%lib\antlr-3.3-complete.jar" org.antlr.Tool -o "%OUT_DIR%" "%GENHOME%grammar\MemoryBasic.g"

@set OUT_DIR=%GENHOME%src\com\iteye\weimingtom\myosotis\calc
@del %OUT_DIR%\*.tokens
@del %OUT_DIR%\*Lexer.java
@del %OUT_DIR%\*Parser.java
@java -cp ".;%GENHOME%lib\antlr-3.3-complete.jar" org.antlr.Tool -o "%OUT_DIR%" "%GENHOME%grammar\Calc.g"

pause
