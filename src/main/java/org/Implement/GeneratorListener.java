package org.Implement;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.gen.MyGrammarBaseListener;
import org.gen.MyGrammarParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GeneratorListener extends MyGrammarBaseListener {
    ParseTreeProperty<String> property = new ParseTreeProperty<>();
    ParseTreeProperty<Pair<MyType, Object>> values = new ParseTreeProperty<>();
    MyStack stack = new MyStack();
    int num = 0;
    private int genNum()
    {
        return num++;
    }

    private Float ToFloat(Object val)
    {
        if(val instanceof Integer || val instanceof Float){
            return ((Number) val).floatValue();
        }
        else {
            return Float.NaN;
        }
    }

    @Override
    public void exitProgram(MyGrammarParser.ProgramContext ctx) {
        String str = "";
        for (var stmnt: ctx.statement()) {
            var s = property.get(stmnt);
            str += s;
            System.out.print(s);
        }
        File file = new File("PLC_t1.out.txt");
        try {
            if (!file.exists()) {
                    file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(str);
            bw.close();
        } catch (IOException e) {
                throw new RuntimeException(e);
        }
    }

    @Override
    public void exitBlockOfStatements(MyGrammarParser.BlockOfStatementsContext ctx) {
        String str = "";
        for (var stmnt: ctx.statement()) {
            str += property.get(stmnt);
        }
        property.put(ctx, str);
    }

    @Override
    public void exitDeclaration(MyGrammarParser.DeclarationContext ctx) {
        var type = property.get(ctx.primitiveType());
        var val = values.get(ctx.primitiveType());
        String str = "";
        for (var id: ctx.IDENTIFIER()) {
            str += type;
            str += "save " + id.getText() + "\n";
            stack.Add(id.getSymbol(), val.a);
        }
        property.put(ctx, str);
    }

    @Override
    public void exitIfElse(MyGrammarParser.IfElseContext ctx) {
        var cond = property.get(ctx.expr());
        String pos = "";
        String neg = "";
        int jump = genNum();
        int posEnd = genNum();
        neg += (ctx.neg.equals(null)) ? "" : property.get(ctx.neg);
        pos += property.get(ctx.pos);
        property.put(ctx, cond + "fjump " + jump + "\n" + pos + "jump " + posEnd + "\n" + "label " + jump + "\n" + neg + "label " + posEnd + "\n");
    }

    @Override
    public void exitWhile(MyGrammarParser.WhileContext ctx) {
        var cond = property.get(ctx.expr());
        String body = "";
        int start = genNum();
        int end = genNum();
        body += property.get(ctx.statement());
        body += "jump " + start + "\n";
        property.put(ctx, "label " + start + "\n" + cond + "fjump " + end + "\n" + body + "label " + end + "\n");
    }

    @Override
    public void exitReadStatement(MyGrammarParser.ReadStatementContext ctx) {
        String str = "";
        boolean flag = false;
        for (var id: ctx.IDENTIFIER()) {
            if(flag){break;} //TODO: is this needed????
            var variable = stack.getVal(id.getSymbol());
            switch (variable.a){
                case Boolean -> {
                    str += "read B\n";
                    flag = true;
                }
                case Int -> {
                    str += "read I\n";
                    flag = true;
                }
                case Float -> {
                    str += "read F\n";
                    flag = true;
                }
                case String -> {
                    str += "read S\n";
                    flag = true;
                }
                default -> {
                }
            }
            str += "save " + id.getSymbol().getText() + "\n";
        }
        property.put(ctx, str);
    }

    @Override
    public void exitWriteStatement(MyGrammarParser.WriteStatementContext ctx) {
        String str = "";
        int count = 0;
        for (var expr: ctx.expr()){
            var val = values.get(expr);
            var exprProp = property.get(expr);
            str += exprProp;
            count++;
        }
        property.put(ctx, str + "print " + count + "\n");
    }

    @Override
    public void exitPrintExpr(MyGrammarParser.PrintExprContext ctx) {
        property.put(ctx, property.get(ctx.expr()));
    }

    @Override
    public void exitMulDivMod(MyGrammarParser.MulDivModContext ctx) {
        var leftProp = property.get(ctx.expr().get(0));
        var rightProp = property.get(ctx.expr().get(1));
        var leftVal = values.get(ctx.expr().get(0));
        var rightVal = values.get(ctx.expr().get(1));
        switch (ctx.op.getType()){
            case MyGrammarParser.MUL -> {
                if(leftVal.a.equals(MyType.Float) || rightVal.a.equals(MyType.Float)){
                    values.put(ctx, new Pair<>(MyType.Float, ToFloat(leftVal.b) * ToFloat(rightVal.b)));
                    if(leftVal.a.equals(MyType.Float)){
                        property.put(ctx, leftProp + rightProp + "itof\n" + "mul\n");
                    } else {
                        property.put(ctx, leftProp + "itof\n" + rightProp + "mul\n");
                    }
                } else {
                    values.put(ctx, new Pair<>(MyType.Int, (int)leftVal.b * (int)rightVal.b));
                    property.put(ctx, leftProp + rightProp + "mul\n");
                }
            } //TODO: should break; here?
            case MyGrammarParser.DIV -> {
                if(leftVal.a.equals(MyType.Float) || rightVal.a.equals(MyType.Float)){
                    values.put(ctx, new Pair<>(MyType.Float, ToFloat(leftVal.b) / ToFloat(rightVal.b)));
                    if(leftVal.a.equals(MyType.Float)){
                        property.put(ctx, leftProp + rightProp + "itof\n" + "div\n");
                    } else {
                        property.put(ctx, leftProp + "itof\n" + rightProp + "div\n");
                    }
                } else {
                    values.put(ctx, new Pair<>(MyType.Int, (int)leftVal.b / (int)rightVal.b));
                    property.put(ctx, leftProp + rightProp + "div\n");
                }
            }
            case MyGrammarParser.MOD -> {
                values.put(ctx, new Pair<>(MyType.Int, (int)leftVal.b % (int)rightVal.b));
                property.put(ctx, leftProp + rightProp + "mod\n");
            }
        }
    }

    @Override
    public void enterParens(MyGrammarParser.ParensContext ctx) {
        super.enterParens(ctx); //TODO: CONTINUE HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

    @Override
    public void exitParens(MyGrammarParser.ParensContext ctx) {
        super.exitParens(ctx);
    }

    @Override
    public void enterNegation(MyGrammarParser.NegationContext ctx) {
        super.enterNegation(ctx);
    }

    @Override
    public void exitNegation(MyGrammarParser.NegationContext ctx) {
        super.exitNegation(ctx);
    }

    @Override
    public void enterComparison(MyGrammarParser.ComparisonContext ctx) {
        super.enterComparison(ctx);
    }

    @Override
    public void exitComparison(MyGrammarParser.ComparisonContext ctx) {
        super.exitComparison(ctx);
    }

    @Override
    public void enterBool(MyGrammarParser.BoolContext ctx) {
        super.enterBool(ctx);
    }

    @Override
    public void exitBool(MyGrammarParser.BoolContext ctx) {
        super.exitBool(ctx);
    }

    @Override
    public void enterString(MyGrammarParser.StringContext ctx) {
        super.enterString(ctx);
    }

    @Override
    public void exitString(MyGrammarParser.StringContext ctx) {
        super.exitString(ctx);
    }

    @Override
    public void enterAssignment(MyGrammarParser.AssignmentContext ctx) {
        super.enterAssignment(ctx);
    }

    @Override
    public void exitAssignment(MyGrammarParser.AssignmentContext ctx) {
        super.exitAssignment(ctx);
    }

    @Override
    public void enterLogicalAnd(MyGrammarParser.LogicalAndContext ctx) {
        super.enterLogicalAnd(ctx);
    }

    @Override
    public void exitLogicalAnd(MyGrammarParser.LogicalAndContext ctx) {
        super.exitLogicalAnd(ctx);
    }

    @Override
    public void enterFloat(MyGrammarParser.FloatContext ctx) {
        super.enterFloat(ctx);
    }

    @Override
    public void exitFloat(MyGrammarParser.FloatContext ctx) {
        super.exitFloat(ctx);
    }

    @Override
    public void enterInt(MyGrammarParser.IntContext ctx) {
        super.enterInt(ctx);
    }

    @Override
    public void exitInt(MyGrammarParser.IntContext ctx) {
        super.exitInt(ctx);
    }

    @Override
    public void enterRelation(MyGrammarParser.RelationContext ctx) {
        super.enterRelation(ctx);
    }

    @Override
    public void exitRelation(MyGrammarParser.RelationContext ctx) {
        super.exitRelation(ctx);
    }

    @Override
    public void enterAddSubCon(MyGrammarParser.AddSubConContext ctx) {
        super.enterAddSubCon(ctx);
    }

    @Override
    public void exitAddSubCon(MyGrammarParser.AddSubConContext ctx) {
        super.exitAddSubCon(ctx);
    }

    @Override
    public void enterUnaryMinus(MyGrammarParser.UnaryMinusContext ctx) {
        super.enterUnaryMinus(ctx);
    }

    @Override
    public void exitUnaryMinus(MyGrammarParser.UnaryMinusContext ctx) {
        super.exitUnaryMinus(ctx);
    }

    @Override
    public void enterId(MyGrammarParser.IdContext ctx) {
        super.enterId(ctx);
    }

    @Override
    public void exitId(MyGrammarParser.IdContext ctx) {
        super.exitId(ctx);
    }

    @Override
    public void enterLogicalOr(MyGrammarParser.LogicalOrContext ctx) {
        super.enterLogicalOr(ctx);
    }

    @Override
    public void exitLogicalOr(MyGrammarParser.LogicalOrContext ctx) {
        super.exitLogicalOr(ctx);
    }

    @Override
    public void enterPrimitiveType(MyGrammarParser.PrimitiveTypeContext ctx) {
        super.enterPrimitiveType(ctx);
    }

    @Override
    public void exitPrimitiveType(MyGrammarParser.PrimitiveTypeContext ctx) {
        super.exitPrimitiveType(ctx);
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        super.enterEveryRule(ctx);
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        super.exitEveryRule(ctx);
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        super.visitTerminal(node);
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        super.visitErrorNode(node);
    }
}
