lexer grammar LexerGrammar;

// Symbols
AA: '&&';
AT: '@';
NOT: '!';
OR: '||';
DOT: '.';
COMMA: ',';
COLON: ':';
OP: '/' | '*';
OPette: '+' | '-';
INCREMENT: '++';
DECREMENT: '--';
GREATER: '>';
LESS: '<';
EQ: '=';
SC: ';';
OPENP: '(';
CLOSEP: ')';
OPENCB: '{';
CLOSECB: '}';
OPENSB: '[';
CLOSESB: ']';

// Keywords
VOID: 'void';
INT: 'int';
DOUBLE: 'double';
STRING: 'String';
BOOL: 'bool';
TRUE_FALSE: 'true' | 'false';
NULL: 'null';
IF: 'if';
ELSE: 'else';
ELSEIF: 'else if';
WHILE: 'while';
CLASS: 'class';
RETURN: 'return';
NEW: 'new';
CONTINUE: 'continue';
BREAK: 'break';
//THIS: 'this';
OVERRIDE: 'override';
EXTENDS: 'extends';
IMPORT: 'import';
FOR: 'for';

Child: 'child';
Children: 'children';
Row: 'Row';
Column: 'Column';
Container : 'Container';
Text: 'Text';
Width : 'width';
Height: 'height';
Margin: 'margin';
EdgeInsetsOnly: 'EdgeInsets.only';
Style: 'style';
TextStyle: 'TextStyle';
FontSize: 'fontSize';
FontWeight: 'fontWeight';
Color: 'color';
ColorsDot: 'Colors.';
ElevatedButton: 'ElevatedButton';
OnPressed: 'onPressed';
MaterialApp: 'MaterialApp';
Form: 'Form';
Home: 'home';
Title: 'title';
Scaffold: 'Scaffold';
Body: 'body';
TextField: 'TextField';
Controller: 'controller';
NavigatorPush: 'Navigator.push';
MaterialPageRoute: 'MaterialPageRoute';
Builder: 'builder';
SizedBox: 'SizedBox';

LEFT: 'left';
TOP: 'top';
BOTTOM: 'bottom';
RIGHT: 'right';

// Row and Column
START: 'start';
END: 'end';
CENTER: 'center';
SPACE_BETWEEN: 'space-between';
SPACE_AROUND: 'space-around';
SPACE_EVENLY: 'space-evenly';

PaddingWidget: 'Padding';
Padding: 'padding';
MainAxisAlignment1: 'mainAxisAlignment';
MainAxisAlignment2: 'MainAxisAlignment';
CrossAxisAlignment1: 'crossAxisAlignment';
CrossAxisAlignment2: 'CrossAxisAlignment';

DECORATION: 'decoration';
INPUT_DECORATION: 'InputDecoration';
LABEL_TEXT: 'labelText';

WIDGET: 'Widget';
BUILD: 'build';
BUILD_CONTEXT: 'BuildContext';
CONTEXT: 'context';


NUMBER : DIGIT+ ( '.' DIGIT+ )? ;

SingleLineString : StringDQ | 'r\'' (~('\'' | '\n' | '\r'))* '\'' | 'r"' (~('"' | '\n' | '\r'))* '"' ;
fragment StringDQ : ('"' | '\'') StringContentDQ*? ('"' | '\'') ;
fragment StringContentDQ : ~('\\' | '"' | '\n' | '\r') | '\\' ~('\n' | '\r') | StringDQ | '${' StringContentDQ*? '}';

IDENTIFIER : IDENTIFIER_START IDENTIFIER_PART* ;
fragment IDENTIFIER_START : LETTER | '_' ;
fragment IDENTIFIER_PART : IDENTIFIER_START | DIGIT ;

fragment LETTER : [a-zA-Z] ;
fragment DIGIT : [0-9] ;

WHITESPACE : ('\t' | ' ')+ -> skip;
NEWLINE : ('\n' | '\r' | '\r\n') -> skip ;
COMMENT : '//'.*? '\n' -> skip;
MULTI_LINE_COMMENT: '/*' (MULTI_LINE_COMMENT | .)*? '*/' -> skip;