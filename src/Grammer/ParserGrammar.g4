parser grammar ParserGrammar;

options {tokenVocab = LexerGrammar;}

program: importStatement* expression* EOF;

expression: function | function_call SC+ | decl | arith SC+ | assign | conditional_if | while_loop
    | for_loop | block | class_declaration | flowControlStatement;

variable_values: literal
        | abstract_variable_value
        ;

literal: SingleLineString
        | TRUE_FALSE
        | NULL
        | NUMBER
        ;

propertyAccessDot: DOT abstract_variable_value;
abstract_variable_value: (THIS DOT)? IDENTIFIER propertyAccessDot?
            | arith
            | function_call propertyAccessDot?
            | array propertyAccessDot?
            | function
            | IDENTIFIER
            ;

variable_type: INT | DOUBLE | BOOL | STRING | IDENTIFIER (LESS variable_type GREATER)? ;

function_return_type : variable_type | VOID?;

parameter: variable_type? IDENTIFIER;
parameters: parameter (COMMA parameter)*;

optional_positional_parameters: OPENSB parameters CLOSESB;
named_optional_parameters: OPENCB parameters CLOSECB;

function_parameters_declaration: (parameters | optional_positional_parameters (COMMA parameters)* | named_optional_parameters (COMMA parameters)*) COMMA?;

function:
         override? function_return_type IDENTIFIER OPENP function_parameters_declaration? CLOSEP block
         | OPENP function_parameters_declaration? CLOSEP block
          ;

function_call: IDENTIFIER OPENP function_call_arguments? CLOSEP;
function_call_argument: (IDENTIFIER COLON)? variable_values;
function_call_arguments: function_call_argument (COMMA function_call_argument)* COMMA?;

decl:  variable_type IDENTIFIER (EQ variable_values)? (COMMA IDENTIFIER (EQ variable_values)?)* SC+;

assign: abstract_variable_value EQ variable_values SC+;

arith: arith OP arith
     | arith OPette arith
     | OPENP arith CLOSEP
     | NUMBER
     | IDENTIFIER
     | function_call
     | incrementOrDecrement
;

array: OPENSB variable_values? (COMMA variable_values)* COMMA? CLOSESB
     | IDENTIFIER OPENSB abstract_variable_value CLOSESB
     ;

conditional_if: IF OPENP boolean_exp CLOSEP expression conditional_else_if* conditional_else?;
conditional_else_if: ELSEIF OPENP boolean_exp CLOSEP expression;
conditional_else: ELSE expression;

block: OPENCB expression* CLOSECB;

comparator: EQ EQ | NOT EQ | GREATER EQ? | LESS EQ?;

boolean_exp: boolean_exp AA boolean_exp
         | boolean_exp OR boolean_exp
         | OPENP boolean_exp CLOSEP
         | variable_values comparator variable_values
         | abstract_variable_value
         | TRUE_FALSE
         ;

while_loop: WHILE OPENP boolean_exp CLOSEP expression;
for_loop: FOR OPENP (decl | assign | SC+) boolean_exp? SC+ arith? CLOSEP expression;


incrementOrDecrement: IDENTIFIER INCREMENT | IDENTIFIER DECREMENT | INCREMENT IDENTIFIER | DECREMENT IDENTIFIER;

class_declaration : CLASS IDENTIFIER (EXTENDS variable_type)? OPENCB (class_member_declaration)* CLOSECB;
class_member_declaration : decl | function;

override: AT OVERRIDE;

// Statements
flowControlStatement: breakStatement
    |   continueStatement
    |   returnStatement
    ;

breakStatement: BREAK SC+;
continueStatement: CONTINUE SC+;
returnStatement: RETURN variable_values? SC+;

importStatement: IMPORT SingleLineString SC+;
