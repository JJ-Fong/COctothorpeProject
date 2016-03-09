/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

grammar expDecaf2;

//********* LEXER ******************
fragment LETTER: ('a'..'z'|'A'..'Z') ;
fragment DIGIT : '0'..'9' ;
ID : LETTER( LETTER | DIGIT)* ;
NUM: DIGIT(DIGIT)* ;
COMMENTS: '//' ~('\r' | '\n' )*  -> channel(HIDDEN);
WS : [ \t\r\n\f | ' '| '\r' | '\n' | '\t']+  ->channel(HIDDEN); 

CHAR: (LETTER|DIGIT|' '| '!' | '"' | '#' | '$' | '%' | '&' | '\'' | '(' | ')' | '*' | '+' 
| ',' | '-' | '.' | '/' | ':' | ';' | '<' | '=' | '>' | '?' | '@' | '[' | '\\' | ']' | '^' | '_' | '`'| '{' | '|' | '}' | '~' 
'\t'| '\n' | '\"' | '\'');


// ********** PARSER *****************

program : 'class' 'Program' '{' multiple_declaration '}'  ;

multiple_declaration : (declaration)*;

declaration: structDeclaration| varDeclaration | methodDeclaration  ;

varDeclaration: varType ID ';' | varType ID '[' NUM ']' ';'  ;

structDeclaration : 'struct' ID '{' (varDeclaration)* '}'  ;

varType: structDeclaration | 'int' | 'char' | 'boolean' | 'struct' ID | 'void' ;

methodDeclaration : methodType ID '(' opt_parameter ')' block  ;

opt_parameter : parameter_list | ;

parameter_list : parameter | parameter ',' (parameter_list)*;

methodType : 'int' | 'char' | 'boolean' | 'void' ;

parameter : parameterType ID | parameterType ID '[' ']' ;

parameterType: 'int' | 'char' | 'boolean'  ;

block : '{' (varDeclaration)* (statement)* '}' ;

statement: statement_cerrado | statement_abierto ; 

statement_cerrado: 'if' '(' expression ')' statement_cerrado 'else' statement_cerrado
                    | 'while' '(' expression ')' block
                    |'return' expressionA ';' 
                    | methodCall ';' 
                    | block  
                    | location '=' expression
                    | (expression)? ';'  ;
 
statement_abierto: 'if' '(' expression ')' statement 
                    | 'if' '(' expression ')' statement_cerrado 'else' statement_abierto ;
       

expressionA: expression | ;


location : (ID|ID '[' expression ']') ('.' location)?  ;

expression :  rel_Exp | rel_Exp conditionalop expression ;

rel_Exp :  eq_Exp | eq_Exp relop rel_Exp ; 

eq_Exp : add_Exp | add_Exp eqop eq_Exp; 
z
add_Exp :  mult_Exp| mult_Exp addop add_Exp   ; 

mult_Exp : negate_Exp | negate_Exp mulop mult_Exp  ; 

negate_Exp : value | '!'  value  | '-' value   ;

conditionalop : '&&' | '||';

relop : '<=' | '<' | '>' | '>=' ;

eqop : '==' | '!=';

addop : '+' | '-';

mulop : '*' | '/' | '%';

value :  literal | '(' expression ')' | methodCall | location;

methodCall :    ID '(' arg1 ')' ;

arg1    :   arg2 | ;

arg2    :   (arg) (',' arg)* ;

arg :   expression;

literal : int_literal | char_literal | bool_literal ;

int_literal : NUM ;

char_literal : '\'' CHAR '\'' ;

bool_literal : 'true' | 'false' ;