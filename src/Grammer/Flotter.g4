parser grammar Flotter;

options {tokenVocab = LexerGrammar;}

program: importStatement* expression* EOF;
expression: widgetExpression | function | function_call | decl | class_declaration | block | returnStatement | assign;

widgetExpression: widget SC+;

widget : (container | row | column | form | paddingWidget | text | elevatedButton | materialApp | scaffold | textField | navigator);

row : Row OPENP rowColumnProperties? CLOSEP;
column : Column OPENP rowColumnProperties? CLOSEP;

rowColumnProperties : rowColumnProperty (COMMA rowColumnProperty)* COMMA?;
rowColumnProperty: ( children | mainAxisAlignment | crossAxisAlignment );

mainAxisAlignment: MainAxisAlignment1 COLON MainAxisAlignment2 DOT mainCrossAxisAlignmentProperty;
crossAxisAlignment: CrossAxisAlignment1 COLON CrossAxisAlignment2 DOT mainCrossAxisAlignmentProperty;
mainCrossAxisAlignmentProperty: START | END | CENTER | SPACE_BETWEEN | SPACE_AROUND | SPACE_EVENLY;

text : Text OPENP textProperties? CLOSEP ;
textProperties: textProperty (COMMA textProperty)* COMMA?;
textProperty: (SingleLineString | textStyle);

textStyle: Style COLON TextStyle OPENP textStyleProperties CLOSEP;
textStyleProperties: textStyleProperty (COMMA textStyleProperty)* COMMA?;
textStyleProperty: (fontSize | color | fontWeight);

fontSize: FontSize COLON NUMBER;
fontWeight: FontWeight COLON NUMBER;
color: Color COLON ColorsDot colorName=IDENTIFIER;

container : Container OPENP containerProperties? CLOSEP ;
containerProperties : containerProperty (COMMA containerProperty)* COMMA?;
containerProperty : ( width | height | child | margin | padding | color);

paddingWidget : PaddingWidget OPENP paddingProperties CLOSEP ;
paddingProperties : paddingProperty (COMMA paddingProperty)* COMMA?;
paddingProperty: ( child | padding);

form : Form OPENP formProperty CLOSEP ;
formProperty : child COMMA?;

elevatedButton: ElevatedButton OPENP elevatedButtonProperties CLOSEP;
elevatedButtonProperties : elevatedButtonProperty (COMMA elevatedButtonProperty)* COMMA?;
elevatedButtonProperty : ( child | onPressed );

onPressed: OnPressed COLON OPENP CLOSEP block;

materialApp: MaterialApp OPENP materialAppProperties CLOSEP;
materialAppProperties: materialAppProperty (COMMA materialAppProperty)* COMMA?;
materialAppProperty: (home | title);

home: Home COLON function_call;
title: Title COLON SingleLineString;

scaffold: Scaffold OPENP scaffoldProperties CLOSEP;
scaffoldProperties: scaffoldProperty (COMMA scaffoldProperty)* COMMA?;
scaffoldProperty: body;
body: Body COLON widget;

textField: TextField OPENP textFieldProperties CLOSEP;
textFieldProperties: textFieldProperty (COMMA textFieldProperty)* COMMA?;
textFieldProperty: controller | textFieldDecoration;

textFieldDecoration: DECORATION COLON inputDecoration;
controller: Controller COLON IDENTIFIER;

inputDecoration: INPUT_DECORATION OPENP LABEL_TEXT COLON IDENTIFIER CLOSEP;

navigator: NavigatorPush OPENP (navContext=CONTEXT COMMA)+ materialpageroute CLOSEP;
materialpageroute: MaterialPageRoute OPENP Builder COLON OPENP contextRefrence=CONTEXT  CLOSEP EQ GREATER function_call COMMA? CLOSEP COMMA?;

// Properties for all widgets.
width : Width COLON (NUMBER | IDENTIFIER);
height: Height COLON (NUMBER | IDENTIFIER);
child: Child COLON widget;
children: Children COLON OPENSB (widget COMMA)* CLOSESB;
margin: Margin COLON edgeInsetsOnly;
padding: Padding COLON edgeInsetsOnly;

edgeInsetsOnly: EdgeInsetsOnly OPENP edgeInsetsParams CLOSEP ;
edgeInsetsParams: LEFT COLON NUMBER COMMA TOP COLON NUMBER COMMA RIGHT COLON NUMBER COMMA BOTTOM COLON NUMBER ;

// Dart specific grammer
decl:  variable_type IDENTIFIER (EQ variable_value)? SC+;

assign: IDENTIFIER EQ variable_value SC+;

variable_type: DOUBLE
             | INT
             | BOOL
             | STRING
             | IDENTIFIER;
             //Deleted this for simplicity reasons: INT | IDENTIFIER (LESS variable_type GREATER)? ;

variable_value: literal | abstract;

literal: SingleLineString
       | TRUE_FALSE
       | NULL
       | NUMBER
       ;

 abstract:IDENTIFIER (DOT (IDENTIFIER | function_call))*
         | widget
         | function_call
         ;

// Right now widget is not considired a function
function: override? function_return_type IDENTIFIER OPENP parameters? CLOSEP block;

function_return_type : variable_type | VOID;

parameters: parameter (COMMA parameter)*;
parameter: variable_type? IDENTIFIER;

function_call: IDENTIFIER OPENP (function_call_arguments | function_call_named_arguments)? CLOSEP SC*;
function_call_named_arguments: function_call_named_argument (',' function_call_named_argument)*;
function_call_named_argument: IDENTIFIER COLON variable_value;
function_call_arguments: variable_value (COMMA variable_value)*;

block: OPENCB expression* CLOSECB;

class_declaration : CLASS IDENTIFIER (EXTENDS IDENTIFIER)? OPENCB (class_member_declaration)* CLOSECB;
class_member_declaration : class_build_method | decl | function | function_call;
class_build_method: override WIDGET BUILD OPENP BUILD_CONTEXT CONTEXT CLOSEP OPENCB RETURN widget SC+ CLOSECB;

returnStatement: RETURN variable_value? SC+;

override: AT OVERRIDE;
importStatement: IMPORT SingleLineString SC+;
