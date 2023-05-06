package org.gen;// Generated from C:/Users/adamh/OneDrive/Dokumenty/V�B/6. semestr/Programovac� jazyky a p�eklada�e/projekt/projektPJP/src/main/resources\MyGrammar.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MyGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, INT_KEYWORD=8, 
		FLOAT_KEYWORD=9, STRING_KEYWORD=10, BOOL_KEYWORD=11, SEMI=12, COMMA=13, 
		CON=14, MUL=15, DIV=16, MOD=17, ADD=18, SUB=19, LES=20, GRE=21, NEG=22, 
		EQ=23, NEQ=24, AND=25, OR=26, READ=27, WRITE=28, IF=29, ELSE=30, WHILE=31, 
		IDENTIFIER=32, FLOAT=33, INT=34, BOOL=35, STRING=36, WS=37, COMMENT=38, 
		LINE_COMMENT=39;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_expr = 2, RULE_primitiveType = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "expr", "primitiveType"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'}'", "'('", "')'", "'true'", "'false'", "'='", "'int'", 
			"'float'", "'string'", "'bool'", "';'", "','", "'.'", "'*'", "'/'", "'%'", 
			"'+'", "'-'", "'<'", "'>'", "'!'", "'=='", "'!='", "'&&'", "'||'", "'read'", 
			"'write'", "'if'", "'else'", "'while'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "INT_KEYWORD", "FLOAT_KEYWORD", 
			"STRING_KEYWORD", "BOOL_KEYWORD", "SEMI", "COMMA", "CON", "MUL", "DIV", 
			"MOD", "ADD", "SUB", "LES", "GRE", "NEG", "EQ", "NEQ", "AND", "OR", "READ", 
			"WRITE", "IF", "ELSE", "WHILE", "IDENTIFIER", "FLOAT", "INT", "BOOL", 
			"STRING", "WS", "COMMENT", "LINE_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MyGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MyGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MyGrammarParser.EOF, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(9); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(8);
				statement();
				}
				}
				setState(11); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 101875982186L) != 0) );
			setState(13);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EmptyStatementContext extends StatementContext {
		public TerminalNode SEMI() { return getToken(MyGrammarParser.SEMI, 0); }
		public EmptyStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterEmptyStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitEmptyStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitEmptyStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WriteStatementContext extends StatementContext {
		public TerminalNode WRITE() { return getToken(MyGrammarParser.WRITE, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode SEMI() { return getToken(MyGrammarParser.SEMI, 0); }
		public List<TerminalNode> COMMA() { return getTokens(MyGrammarParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MyGrammarParser.COMMA, i);
		}
		public WriteStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterWriteStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitWriteStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitWriteStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BlockOfStatementsContext extends StatementContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockOfStatementsContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterBlockOfStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitBlockOfStatements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitBlockOfStatements(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ReadStatementContext extends StatementContext {
		public TerminalNode READ() { return getToken(MyGrammarParser.READ, 0); }
		public List<TerminalNode> IDENTIFIER() { return getTokens(MyGrammarParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(MyGrammarParser.IDENTIFIER, i);
		}
		public TerminalNode SEMI() { return getToken(MyGrammarParser.SEMI, 0); }
		public List<TerminalNode> COMMA() { return getTokens(MyGrammarParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MyGrammarParser.COMMA, i);
		}
		public ReadStatementContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterReadStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitReadStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitReadStatement(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WhileContext extends StatementContext {
		public TerminalNode WHILE() { return getToken(MyGrammarParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationContext extends StatementContext {
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(MyGrammarParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(MyGrammarParser.IDENTIFIER, i);
		}
		public TerminalNode SEMI() { return getToken(MyGrammarParser.SEMI, 0); }
		public List<TerminalNode> COMMA() { return getTokens(MyGrammarParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MyGrammarParser.COMMA, i);
		}
		public DeclarationContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfElseContext extends StatementContext {
		public StatementContext pos;
		public StatementContext neg;
		public TerminalNode IF() { return getToken(MyGrammarParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MyGrammarParser.ELSE, 0); }
		public IfElseContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterIfElse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitIfElse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitIfElse(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrintExprContext extends StatementContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(MyGrammarParser.SEMI, 0); }
		public PrintExprContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterPrintExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitPrintExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitPrintExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		int _la;
		try {
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				_localctx = new BlockOfStatementsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(15);
				match(T__0);
				setState(16);
				statement();
				setState(20);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 101875982186L) != 0)) {
					{
					{
					setState(17);
					statement();
					}
					}
					setState(22);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(23);
				match(T__1);
				}
				break;
			case INT_KEYWORD:
			case FLOAT_KEYWORD:
			case STRING_KEYWORD:
			case BOOL_KEYWORD:
				_localctx = new DeclarationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(25);
				primitiveType();
				setState(26);
				match(IDENTIFIER);
				setState(31);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(27);
					match(COMMA);
					setState(28);
					match(IDENTIFIER);
					}
					}
					setState(33);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(34);
				match(SEMI);
				}
				break;
			case IF:
				_localctx = new IfElseContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(36);
				match(IF);
				setState(37);
				match(T__2);
				setState(38);
				expr(0);
				setState(39);
				match(T__3);
				setState(40);
				((IfElseContext)_localctx).pos = statement();
				setState(43);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(41);
					match(ELSE);
					setState(42);
					((IfElseContext)_localctx).neg = statement();
					}
					break;
				}
				}
				break;
			case WHILE:
				_localctx = new WhileContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(45);
				match(WHILE);
				setState(46);
				match(T__2);
				setState(47);
				expr(0);
				setState(48);
				match(T__3);
				setState(49);
				statement();
				}
				break;
			case READ:
				_localctx = new ReadStatementContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(51);
				match(READ);
				setState(52);
				match(IDENTIFIER);
				setState(57);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(53);
					match(COMMA);
					setState(54);
					match(IDENTIFIER);
					}
					}
					setState(59);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(60);
				match(SEMI);
				}
				break;
			case WRITE:
				_localctx = new WriteStatementContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(61);
				match(WRITE);
				setState(62);
				expr(0);
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(63);
					match(COMMA);
					setState(64);
					expr(0);
					}
					}
					setState(69);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(70);
				match(SEMI);
				}
				break;
			case T__2:
			case T__4:
			case T__5:
			case SUB:
			case NEG:
			case IDENTIFIER:
			case FLOAT:
			case INT:
			case STRING:
				_localctx = new PrintExprContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(72);
				expr(0);
				setState(73);
				match(SEMI);
				}
				break;
			case SEMI:
				_localctx = new EmptyStatementContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(75);
				match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MulDivModContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MUL() { return getToken(MyGrammarParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(MyGrammarParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(MyGrammarParser.MOD, 0); }
		public MulDivModContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterMulDivMod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitMulDivMod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitMulDivMod(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParensContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParensContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterParens(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitParens(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitParens(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NegationContext extends ExprContext {
		public Token prefix;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode NEG() { return getToken(MyGrammarParser.NEG, 0); }
		public NegationContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitNegation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitNegation(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode EQ() { return getToken(MyGrammarParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(MyGrammarParser.NEQ, 0); }
		public ComparisonContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitComparison(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitComparison(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoolContext extends ExprContext {
		public BoolContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitBool(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitBool(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringContext extends ExprContext {
		public TerminalNode STRING() { return getToken(MyGrammarParser.STRING, 0); }
		public StringContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentContext extends ExprContext {
		public TerminalNode IDENTIFIER() { return getToken(MyGrammarParser.IDENTIFIER, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignmentContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LogicalAndContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode AND() { return getToken(MyGrammarParser.AND, 0); }
		public LogicalAndContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterLogicalAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitLogicalAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitLogicalAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FloatContext extends ExprContext {
		public TerminalNode FLOAT() { return getToken(MyGrammarParser.FLOAT, 0); }
		public FloatContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterFloat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitFloat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitFloat(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntContext extends ExprContext {
		public TerminalNode INT() { return getToken(MyGrammarParser.INT, 0); }
		public IntContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitInt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RelationContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LES() { return getToken(MyGrammarParser.LES, 0); }
		public TerminalNode GRE() { return getToken(MyGrammarParser.GRE, 0); }
		public RelationContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterRelation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitRelation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitRelation(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddSubConContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ADD() { return getToken(MyGrammarParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(MyGrammarParser.SUB, 0); }
		public TerminalNode CON() { return getToken(MyGrammarParser.CON, 0); }
		public AddSubConContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterAddSubCon(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitAddSubCon(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitAddSubCon(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryMinusContext extends ExprContext {
		public Token prefix;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SUB() { return getToken(MyGrammarParser.SUB, 0); }
		public UnaryMinusContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterUnaryMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitUnaryMinus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitUnaryMinus(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdContext extends ExprContext {
		public TerminalNode IDENTIFIER() { return getToken(MyGrammarParser.IDENTIFIER, 0); }
		public IdContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LogicalOrContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OR() { return getToken(MyGrammarParser.OR, 0); }
		public LogicalOrContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterLogicalOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitLogicalOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitLogicalOr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				_localctx = new IdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(79);
				match(IDENTIFIER);
				}
				break;
			case 2:
				{
				_localctx = new BoolContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(80);
				_la = _input.LA(1);
				if ( !(_la==T__4 || _la==T__5) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 3:
				{
				_localctx = new ParensContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(81);
				match(T__2);
				setState(82);
				expr(0);
				setState(83);
				match(T__3);
				}
				break;
			case 4:
				{
				_localctx = new IntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(85);
				match(INT);
				}
				break;
			case 5:
				{
				_localctx = new FloatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(86);
				match(FLOAT);
				}
				break;
			case 6:
				{
				_localctx = new StringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(87);
				match(STRING);
				}
				break;
			case 7:
				{
				_localctx = new UnaryMinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(88);
				((UnaryMinusContext)_localctx).prefix = match(SUB);
				setState(89);
				expr(9);
				}
				break;
			case 8:
				{
				_localctx = new NegationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(90);
				((NegationContext)_localctx).prefix = match(NEG);
				setState(91);
				expr(8);
				}
				break;
			case 9:
				{
				_localctx = new AssignmentContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(92);
				match(IDENTIFIER);
				setState(93);
				match(T__6);
				setState(94);
				expr(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(117);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(115);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivModContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(97);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(98);
						((MulDivModContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 229376L) != 0)) ) {
							((MulDivModContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(99);
						expr(8);
						}
						break;
					case 2:
						{
						_localctx = new AddSubConContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(100);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(101);
						((AddSubConContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 802816L) != 0)) ) {
							((AddSubConContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(102);
						expr(7);
						}
						break;
					case 3:
						{
						_localctx = new RelationContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(103);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(104);
						((RelationContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==LES || _la==GRE) ) {
							((RelationContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(105);
						expr(6);
						}
						break;
					case 4:
						{
						_localctx = new ComparisonContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(106);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(107);
						((ComparisonContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQ || _la==NEQ) ) {
							((ComparisonContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(108);
						expr(5);
						}
						break;
					case 5:
						{
						_localctx = new LogicalAndContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(109);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(110);
						match(AND);
						setState(111);
						expr(4);
						}
						break;
					case 6:
						{
						_localctx = new LogicalOrContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(112);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(113);
						match(OR);
						setState(114);
						expr(3);
						}
						break;
					}
					} 
				}
				setState(119);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimitiveTypeContext extends ParserRuleContext {
		public Token type;
		public TerminalNode INT_KEYWORD() { return getToken(MyGrammarParser.INT_KEYWORD, 0); }
		public TerminalNode FLOAT_KEYWORD() { return getToken(MyGrammarParser.FLOAT_KEYWORD, 0); }
		public TerminalNode STRING_KEYWORD() { return getToken(MyGrammarParser.STRING_KEYWORD, 0); }
		public TerminalNode BOOL_KEYWORD() { return getToken(MyGrammarParser.BOOL_KEYWORD, 0); }
		public PrimitiveTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).enterPrimitiveType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MyGrammarListener ) ((MyGrammarListener)listener).exitPrimitiveType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MyGrammarVisitor ) return ((MyGrammarVisitor<? extends T>)visitor).visitPrimitiveType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveTypeContext primitiveType() throws RecognitionException {
		PrimitiveTypeContext _localctx = new PrimitiveTypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_primitiveType);
		try {
			setState(124);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_KEYWORD:
				enterOuterAlt(_localctx, 1);
				{
				setState(120);
				((PrimitiveTypeContext)_localctx).type = match(INT_KEYWORD);
				}
				break;
			case FLOAT_KEYWORD:
				enterOuterAlt(_localctx, 2);
				{
				setState(121);
				((PrimitiveTypeContext)_localctx).type = match(FLOAT_KEYWORD);
				}
				break;
			case STRING_KEYWORD:
				enterOuterAlt(_localctx, 3);
				{
				setState(122);
				((PrimitiveTypeContext)_localctx).type = match(STRING_KEYWORD);
				}
				break;
			case BOOL_KEYWORD:
				enterOuterAlt(_localctx, 4);
				{
				setState(123);
				((PrimitiveTypeContext)_localctx).type = match(BOOL_KEYWORD);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\'\u007f\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0001\u0000\u0004\u0000\n\b"+
		"\u0000\u000b\u0000\f\u0000\u000b\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0005\u0001\u0013\b\u0001\n\u0001\f\u0001\u0016\t\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0005\u0001\u001e\b\u0001\n\u0001\f\u0001!\t\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001,\b\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0005\u00018\b\u0001\n\u0001\f\u0001;\t\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001B\b"+
		"\u0001\n\u0001\f\u0001E\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0003\u0001M\b\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002`\b\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005"+
		"\u0002t\b\u0002\n\u0002\f\u0002w\t\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0003\u0003}\b\u0003\u0001\u0003\u0000\u0001\u0004"+
		"\u0004\u0000\u0002\u0004\u0006\u0000\u0005\u0001\u0000\u0005\u0006\u0001"+
		"\u0000\u000f\u0011\u0002\u0000\u000e\u000e\u0012\u0013\u0001\u0000\u0014"+
		"\u0015\u0001\u0000\u0017\u0018\u0098\u0000\t\u0001\u0000\u0000\u0000\u0002"+
		"L\u0001\u0000\u0000\u0000\u0004_\u0001\u0000\u0000\u0000\u0006|\u0001"+
		"\u0000\u0000\u0000\b\n\u0003\u0002\u0001\u0000\t\b\u0001\u0000\u0000\u0000"+
		"\n\u000b\u0001\u0000\u0000\u0000\u000b\t\u0001\u0000\u0000\u0000\u000b"+
		"\f\u0001\u0000\u0000\u0000\f\r\u0001\u0000\u0000\u0000\r\u000e\u0005\u0000"+
		"\u0000\u0001\u000e\u0001\u0001\u0000\u0000\u0000\u000f\u0010\u0005\u0001"+
		"\u0000\u0000\u0010\u0014\u0003\u0002\u0001\u0000\u0011\u0013\u0003\u0002"+
		"\u0001\u0000\u0012\u0011\u0001\u0000\u0000\u0000\u0013\u0016\u0001\u0000"+
		"\u0000\u0000\u0014\u0012\u0001\u0000\u0000\u0000\u0014\u0015\u0001\u0000"+
		"\u0000\u0000\u0015\u0017\u0001\u0000\u0000\u0000\u0016\u0014\u0001\u0000"+
		"\u0000\u0000\u0017\u0018\u0005\u0002\u0000\u0000\u0018M\u0001\u0000\u0000"+
		"\u0000\u0019\u001a\u0003\u0006\u0003\u0000\u001a\u001f\u0005 \u0000\u0000"+
		"\u001b\u001c\u0005\r\u0000\u0000\u001c\u001e\u0005 \u0000\u0000\u001d"+
		"\u001b\u0001\u0000\u0000\u0000\u001e!\u0001\u0000\u0000\u0000\u001f\u001d"+
		"\u0001\u0000\u0000\u0000\u001f \u0001\u0000\u0000\u0000 \"\u0001\u0000"+
		"\u0000\u0000!\u001f\u0001\u0000\u0000\u0000\"#\u0005\f\u0000\u0000#M\u0001"+
		"\u0000\u0000\u0000$%\u0005\u001d\u0000\u0000%&\u0005\u0003\u0000\u0000"+
		"&\'\u0003\u0004\u0002\u0000\'(\u0005\u0004\u0000\u0000(+\u0003\u0002\u0001"+
		"\u0000)*\u0005\u001e\u0000\u0000*,\u0003\u0002\u0001\u0000+)\u0001\u0000"+
		"\u0000\u0000+,\u0001\u0000\u0000\u0000,M\u0001\u0000\u0000\u0000-.\u0005"+
		"\u001f\u0000\u0000./\u0005\u0003\u0000\u0000/0\u0003\u0004\u0002\u0000"+
		"01\u0005\u0004\u0000\u000012\u0003\u0002\u0001\u00002M\u0001\u0000\u0000"+
		"\u000034\u0005\u001b\u0000\u000049\u0005 \u0000\u000056\u0005\r\u0000"+
		"\u000068\u0005 \u0000\u000075\u0001\u0000\u0000\u00008;\u0001\u0000\u0000"+
		"\u000097\u0001\u0000\u0000\u00009:\u0001\u0000\u0000\u0000:<\u0001\u0000"+
		"\u0000\u0000;9\u0001\u0000\u0000\u0000<M\u0005\f\u0000\u0000=>\u0005\u001c"+
		"\u0000\u0000>C\u0003\u0004\u0002\u0000?@\u0005\r\u0000\u0000@B\u0003\u0004"+
		"\u0002\u0000A?\u0001\u0000\u0000\u0000BE\u0001\u0000\u0000\u0000CA\u0001"+
		"\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000DF\u0001\u0000\u0000\u0000"+
		"EC\u0001\u0000\u0000\u0000FG\u0005\f\u0000\u0000GM\u0001\u0000\u0000\u0000"+
		"HI\u0003\u0004\u0002\u0000IJ\u0005\f\u0000\u0000JM\u0001\u0000\u0000\u0000"+
		"KM\u0005\f\u0000\u0000L\u000f\u0001\u0000\u0000\u0000L\u0019\u0001\u0000"+
		"\u0000\u0000L$\u0001\u0000\u0000\u0000L-\u0001\u0000\u0000\u0000L3\u0001"+
		"\u0000\u0000\u0000L=\u0001\u0000\u0000\u0000LH\u0001\u0000\u0000\u0000"+
		"LK\u0001\u0000\u0000\u0000M\u0003\u0001\u0000\u0000\u0000NO\u0006\u0002"+
		"\uffff\uffff\u0000O`\u0005 \u0000\u0000P`\u0007\u0000\u0000\u0000QR\u0005"+
		"\u0003\u0000\u0000RS\u0003\u0004\u0002\u0000ST\u0005\u0004\u0000\u0000"+
		"T`\u0001\u0000\u0000\u0000U`\u0005\"\u0000\u0000V`\u0005!\u0000\u0000"+
		"W`\u0005$\u0000\u0000XY\u0005\u0013\u0000\u0000Y`\u0003\u0004\u0002\t"+
		"Z[\u0005\u0016\u0000\u0000[`\u0003\u0004\u0002\b\\]\u0005 \u0000\u0000"+
		"]^\u0005\u0007\u0000\u0000^`\u0003\u0004\u0002\u0001_N\u0001\u0000\u0000"+
		"\u0000_P\u0001\u0000\u0000\u0000_Q\u0001\u0000\u0000\u0000_U\u0001\u0000"+
		"\u0000\u0000_V\u0001\u0000\u0000\u0000_W\u0001\u0000\u0000\u0000_X\u0001"+
		"\u0000\u0000\u0000_Z\u0001\u0000\u0000\u0000_\\\u0001\u0000\u0000\u0000"+
		"`u\u0001\u0000\u0000\u0000ab\n\u0007\u0000\u0000bc\u0007\u0001\u0000\u0000"+
		"ct\u0003\u0004\u0002\bde\n\u0006\u0000\u0000ef\u0007\u0002\u0000\u0000"+
		"ft\u0003\u0004\u0002\u0007gh\n\u0005\u0000\u0000hi\u0007\u0003\u0000\u0000"+
		"it\u0003\u0004\u0002\u0006jk\n\u0004\u0000\u0000kl\u0007\u0004\u0000\u0000"+
		"lt\u0003\u0004\u0002\u0005mn\n\u0003\u0000\u0000no\u0005\u0019\u0000\u0000"+
		"ot\u0003\u0004\u0002\u0004pq\n\u0002\u0000\u0000qr\u0005\u001a\u0000\u0000"+
		"rt\u0003\u0004\u0002\u0003sa\u0001\u0000\u0000\u0000sd\u0001\u0000\u0000"+
		"\u0000sg\u0001\u0000\u0000\u0000sj\u0001\u0000\u0000\u0000sm\u0001\u0000"+
		"\u0000\u0000sp\u0001\u0000\u0000\u0000tw\u0001\u0000\u0000\u0000us\u0001"+
		"\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000v\u0005\u0001\u0000\u0000"+
		"\u0000wu\u0001\u0000\u0000\u0000x}\u0005\b\u0000\u0000y}\u0005\t\u0000"+
		"\u0000z}\u0005\n\u0000\u0000{}\u0005\u000b\u0000\u0000|x\u0001\u0000\u0000"+
		"\u0000|y\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000|{\u0001\u0000"+
		"\u0000\u0000}\u0007\u0001\u0000\u0000\u0000\u000b\u000b\u0014\u001f+9"+
		"CL_su|";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}