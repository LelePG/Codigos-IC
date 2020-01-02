//implementação do BFN do MINIJAVA
grammar MiniJava;
goal: mainClass (classDeclaration)* EOF;
mainClass: 'class' identifier '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' identifier ')'
 statement  '}'; //O BNF representa este statement com chaves, mas como a primeira regra dos statements já tem as chaves, deixá-las aqui também ia fazer com que eu precisasse abrir e fechar as chaves duas vezes
classDeclaration: 'class' identifier ('extends' identifier)? '{' (varDeclaration)* (methodDeclaration)* '}';
varDeclaration: type identifier ';';
methodDeclaration: 'public' type identifier '(' (type identifier(',' type identifier)*)? ')' '{'
(varDeclaration)* (statement)* 'return' expression ';''}';
type: 'int' '[' ']'
    |'boolean'
    |'int'
    |identifier;
statement: '{' (statement)*'}'
    |'if' '(' expression ')' statement 'else' statement
    |'while' '(' expression ')' statement
    |'System.out.println' '(' expression ')' ';'
    |identifier ('=' expression | '[' expression ']' '=' expression )';';
expression: INTEGER_LITERAL
    |'true'
    |'false'
    |identifier
    |'this'
    |'new' ('int' '[' expression ']' | identifier '(' ')' )
    |'!' expression
    |'(' expression ')'
    |expression (('&&'|'<'|'+'|'-'|'*') expression|'[' expression ']'|'.' 'length' |'.' identifier '(' (expression (',' expression)*)? ')');
identifier: IDENTIFIER;
IDENTIFIER: [a-zA-Z] [a-zA-Z0-9]*;
INTEGER_LITERAL: [0-9] [a-zA-Z0-9]*;//não entendi direito a diferença do IDENTIFIER pro INTEGER_LITERAL
WS: [ \t\r\n]+ -> skip ;