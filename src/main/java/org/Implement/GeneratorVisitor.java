package org.Implement;

import org.gen.MyGrammarBaseVisitor;
import org.gen.MyGrammarParser;

public class GeneratorVisitor extends MyGrammarBaseVisitor<String> {
    private MyStack stack = new MyStack();
    @Override
    public String visitProgram(MyGrammarParser.ProgramContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (var stmnt: ctx.statement()) {
            var code = visit(stmnt);
            sb.append(code);
        }
        return sb.toString();
    }

    @Override
    public String visitBlockOfStatements(MyGrammarParser.BlockOfStatementsContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (var stmnt: ctx.statement()) {
            var code = visit(stmnt);
            sb.append(code);
        }
        return sb.toString();
    }

    @Override
    public String visitDeclaration(MyGrammarParser.DeclarationContext ctx) {
        var type = visit(ctx.primitiveType());
        StringBuilder sb = new StringBuilder();
        for (var id: ctx.IDENTIFIER()) {
            sb.append(visit(ctx.primitiveType()) + "save " + id.getText() + "\n");
        }
        return sb.toString();
    }

    @Override
    public String visitPrintExpr(MyGrammarParser.PrintExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public String visitMulDivMod(MyGrammarParser.MulDivModContext ctx) {
        var left = visit(ctx.expr().get(0));
        var right = visit(ctx.expr().get(1));
        if(ctx.op.getType() == MyGrammarParser.MUL){
            return left + right + "mul\n";
        } else if(ctx.op.getType() == MyGrammarParser.DIV){
            return left + right + "div\n";
        } else {
            return left + right + "mod\n";
        }
    }

    @Override
    public String visitParens(MyGrammarParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public String visitNegation(MyGrammarParser.NegationContext ctx) {
        var op = visit(ctx.expr());
        return op + "not\n";
    }

    @Override
    public String visitComparison(MyGrammarParser.ComparisonContext ctx) {
        var left = visit(ctx.expr().get(0));
        var right = visit(ctx.expr().get(1));
        if(ctx.op.getType() == MyGrammarParser.EQ) {
            return left + right + "eq\n";
        } else {
            return left + right + "eq\n" + "not\n";
        }
    }

    @Override
    public String visitBool(MyGrammarParser.BoolContext ctx) {
        var val = ctx.getText();
        return "Push B " + val + "\n";
    }

    @Override
    public String visitString(MyGrammarParser.StringContext ctx) {
        var val = ctx.STRING().getText();
        return "Push S " + val + "\n";
    }

    @Override
    public String visitAssignment(MyGrammarParser.AssignmentContext ctx) {
        var right = visit(ctx.expr());
        String str = ctx.IDENTIFIER().getText();
        return right + "save " + str + "\n" + "load " + str + "\n";
    }

    @Override
    public String visitLogicalAnd(MyGrammarParser.LogicalAndContext ctx) {
        var left = visit(ctx.expr().get(0));
        var right = visit(ctx.expr().get(1));
        return left + right + "and\n";
    }

    @Override
    public String visitFloat(MyGrammarParser.FloatContext ctx) { //TODO: tahle metoda se mi nezda, float to double?????
        var val = Double.parseDouble(ctx.FLOAT().getText());
        return "Push F " + val + "\n";
    }

    @Override
    public String visitInt(MyGrammarParser.IntContext ctx) {
        var val = Integer.parseInt(ctx.INT().getText(),10);
        return "Push I " + val + "\n";
    }

    @Override
    public String visitRelation(MyGrammarParser.RelationContext ctx) {
        var left = visit(ctx.expr().get(0));
        var right = visit(ctx.expr().get(1));
        if(ctx.op.getType() == MyGrammarParser.GRE){
            return left + right + "gt\n";
        } else {
            return left + right + "lt\n";
        }
    }

    @Override
    public String visitAddSubCon(MyGrammarParser.AddSubConContext ctx) {
        var left = visit(ctx.expr().get(0));
        var right = visit(ctx.expr().get(1));
        if(ctx.op.getType() == MyGrammarParser.ADD){
            return left + right + "add\n";
        }else if(ctx.op.getType() == MyGrammarParser.SUB) {
            return left + right + "sub\n";
        } else {
            return left + right + "concat\n";
        }
    }

    @Override
    public String visitUnaryMinus(MyGrammarParser.UnaryMinusContext ctx) {
        var op = visit(ctx.expr());
        return op + "uminus\n";
    }

    @Override
    public String visitId(MyGrammarParser.IdContext ctx) {
        return "load" + ctx.IDENTIFIER().getText() + "\n";
    }

    @Override
    public String visitLogicalOr(MyGrammarParser.LogicalOrContext ctx) {
        var left = visit(ctx.expr().get(0));
        var right = visit(ctx.expr().get(1));
        return left + right + "or\n";
    }

    @Override
    public String visitPrimitiveType(MyGrammarParser.PrimitiveTypeContext ctx) {
        if(ctx.type.getText().equals("int")){
            return "Push I 0\n";
        } else if (ctx.type.getText().equals("float")) {
            return "Push F 0.0\n";
        } else if (ctx.type.getText().equals("string")) {
            return "Push S \"\"\n";
        } else {
            return "Push B true\n";
        }
    }
}
