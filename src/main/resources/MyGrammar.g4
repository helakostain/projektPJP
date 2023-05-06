grammar MyGrammar;

/** The start rule; begin parsing here. */
program: statement+ EOF ;

statement
    : '{' statement (statement)* '}'                                    # blockOfStatements     // YY
    | primitiveType IDENTIFIER ( COMMA IDENTIFIER)* SEMI    # declaration           // YY
    | IF '(' expr ')' pos=statement (ELSE neg=statement)?   # ifElse                // YY
    | WHILE '(' expr ')' statement                          # while                 // Y
    | READ IDENTIFIER ( COMMA IDENTIFIER)* SEMI             # readStatement         // YY
    | WRITE expr ( COMMA expr)* SEMI                        # writeStatement        // YY
    | expr SEMI                                             # printExpr             // YY
    | SEMI                                                  # emptyStatement        // Y
    ;

    

expr: IDENTIFIER                            # id            // YY
    | ('true'|'false')                      # bool          // YY
    | '(' expr ')'                          # parens        // YY
    | INT                                   # int           // YY
    | FLOAT                                 # float         // YY
    | STRING                                # string        // YY
    | prefix=SUB expr                       # unaryMinus    // YY
    | prefix=NEG expr                       # negation      // YY
    | expr op=(MUL|DIV|MOD) expr            # mulDivMod     // YY
    | expr op=(ADD|SUB|CON) expr            # addSubCon     // YY
    | expr op=(LES|GRE) expr                # relation      // YY
    | expr op=(EQ|NEQ) expr                 # comparison    // YY
    | expr AND expr                         # logicalAnd    // YY
    | expr OR expr                          # logicalOr     // YY
    | <assoc=right> IDENTIFIER '=' expr     # assignment    // YY
    ;

primitiveType
    : type=INT_KEYWORD
    | type=FLOAT_KEYWORD
    | type=STRING_KEYWORD
    | type=BOOL_KEYWORD
    ;


INT_KEYWORD : 'int';
FLOAT_KEYWORD : 'float';
STRING_KEYWORD : 'string';
BOOL_KEYWORD : 'bool';

SEMI:               ';';
COMMA:              ',';

CON : '.' ;
MUL : '*' ; 
DIV : '/' ;
MOD : '%' ;
ADD : '+' ;
SUB : '-' ;
LES : '<' ;
GRE : '>' ;
NEG : '!' ;
EQ  : '==' ;
NEQ : '!=' ;
AND : '&&' ;
OR : '||' ;

READ : 'read' ;
WRITE : 'write' ;
IF : 'if' ;
ELSE : 'else' ;
WHILE : 'while' ;

IDENTIFIER : [a-zA-Z] ([a-zA-Z0-9]*)? ; 

// DATA TYPES

FLOAT : [0-9]+'.'[0-9]+ ;
INT : [0-9]+ ; 
BOOL : ('true'|'false') ;
STRING : '"' (~["\\\r\n] | EscapeSequence)* '"';

fragment EscapeSequence
    : '\\' [btnfr"'\\]
    | '\\' ([0-3]? [0-7])? [0-7]
    ;

// SKIP

WS : [ \t\r\n]+ -> skip ;
COMMENT: '/*' .*? '*/' -> skip ;
LINE_COMMENT: '//' ~[\r\n]* -> skip ;