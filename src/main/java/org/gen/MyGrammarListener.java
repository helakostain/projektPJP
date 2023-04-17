package org.gen;// Generated from C:/Users/adamh/OneDrive/Dokumenty/V�B/6. semestr/Programovac� jazyky a p�eklada�e/projekt/projektPJP/src/main/resources\MyGrammar.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MyGrammarParser}.
 */
public interface MyGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MyGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MyGrammarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MyGrammarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockOfStatements}
	 * labeled alternative in {@link MyGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlockOfStatements(MyGrammarParser.BlockOfStatementsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockOfStatements}
	 * labeled alternative in {@link MyGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlockOfStatements(MyGrammarParser.BlockOfStatementsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declaration}
	 * labeled alternative in {@link MyGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(MyGrammarParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declaration}
	 * labeled alternative in {@link MyGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(MyGrammarParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifElse}
	 * labeled alternative in {@link MyGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfElse(MyGrammarParser.IfElseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifElse}
	 * labeled alternative in {@link MyGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfElse(MyGrammarParser.IfElseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code while}
	 * labeled alternative in {@link MyGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhile(MyGrammarParser.WhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code while}
	 * labeled alternative in {@link MyGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhile(MyGrammarParser.WhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code readStatement}
	 * labeled alternative in {@link MyGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReadStatement(MyGrammarParser.ReadStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code readStatement}
	 * labeled alternative in {@link MyGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReadStatement(MyGrammarParser.ReadStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code writeStatement}
	 * labeled alternative in {@link MyGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWriteStatement(MyGrammarParser.WriteStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code writeStatement}
	 * labeled alternative in {@link MyGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWriteStatement(MyGrammarParser.WriteStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link MyGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterPrintExpr(MyGrammarParser.PrintExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link MyGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitPrintExpr(MyGrammarParser.PrintExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyStatement}
	 * labeled alternative in {@link MyGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement(MyGrammarParser.EmptyStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyStatement}
	 * labeled alternative in {@link MyGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement(MyGrammarParser.EmptyStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulDivMod}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDivMod(MyGrammarParser.MulDivModContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulDivMod}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDivMod(MyGrammarParser.MulDivModContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parens}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParens(MyGrammarParser.ParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParens(MyGrammarParser.ParensContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negation}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNegation(MyGrammarParser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negation}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNegation(MyGrammarParser.NegationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparison}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterComparison(MyGrammarParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparison}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitComparison(MyGrammarParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bool}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBool(MyGrammarParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bool}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBool(MyGrammarParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code string}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterString(MyGrammarParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code string}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitString(MyGrammarParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignment}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(MyGrammarParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignment}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(MyGrammarParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalAnd}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAnd(MyGrammarParser.LogicalAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalAnd}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAnd(MyGrammarParser.LogicalAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code float}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFloat(MyGrammarParser.FloatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code float}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFloat(MyGrammarParser.FloatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInt(MyGrammarParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInt(MyGrammarParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relation}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRelation(MyGrammarParser.RelationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relation}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRelation(MyGrammarParser.RelationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addSubCon}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSubCon(MyGrammarParser.AddSubConContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addSubCon}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSubCon(MyGrammarParser.AddSubConContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryMinus}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryMinus(MyGrammarParser.UnaryMinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryMinus}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryMinus(MyGrammarParser.UnaryMinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterId(MyGrammarParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitId(MyGrammarParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalOr}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOr(MyGrammarParser.LogicalOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalOr}
	 * labeled alternative in {@link MyGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOr(MyGrammarParser.LogicalOrContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyGrammarParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveType(MyGrammarParser.PrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyGrammarParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveType(MyGrammarParser.PrimitiveTypeContext ctx);
}