package org.Implement;

import org.antlr.v4.runtime.misc.Pair;
import org.gen.MyGrammarBaseVisitor;
import org.gen.MyGrammarParser;

public class EvalVisitor extends MyGrammarBaseVisitor<Pair<MyType, Object>> {
    private MyStack stack = new MyStack();

    private Float ToFloat(Object val)
    {
        if(val instanceof Integer || val instanceof Float){
            return (float)val;
        }
        else {
            return Float.NaN;
        }
    }


    @Override
    public Pair<MyType, Object> visitProgram(MyGrammarParser.ProgramContext ctx) {
        boolean correct = true;
        for (var stmnt: ctx.statement()) {
            var v = visit(stmnt);
            if(v.a.equals(MyType.Error)) {
                correct = false;
                break;
            }
        }
        if(correct) {
            return new Pair<>(MyType.Empty, 0);
        }
        else {
            return new Pair<>(MyType.Error, 0);
        }
    }

    @Override
    public Pair<MyType, Object> visitBlockOfStatements(MyGrammarParser.BlockOfStatementsContext ctx) {
        Pair<MyType, Object> last = new Pair<>(MyType.Empty, 0);
        boolean correct = true;
        for (var stmnt: ctx.statement()) {
            last = visit(stmnt);
            if(last.a.equals(MyType.Error)) {
                correct = false;
                last = new Pair<>(MyType.Error, 0);
                break;
            }
        }
        return last;
    }

    @Override
    public Pair<MyType, Object> visitDeclaration(MyGrammarParser.DeclarationContext ctx) {
        var type = visit(ctx.primitiveType());
        if(type.a.equals(MyType.Error)) {
            return new Pair<>(MyType.Error, 0);
        }
        for (var id: ctx.IDENTIFIER()) {
            stack.Add(id.getSymbol(), type.a);
        }
        return new Pair<>(MyType.Empty, 0);
    }

    @Override
    public Pair<MyType, Object> visitIfElse(MyGrammarParser.IfElseContext ctx) {
        return super.visitIfElse(ctx);
    }

    @Override
    public Pair<MyType, Object> visitWhile(MyGrammarParser.WhileContext ctx) {
        return super.visitWhile(ctx);
    }

    @Override
    public Pair<MyType, Object> visitReadStatement(MyGrammarParser.ReadStatementContext ctx) {
        return super.visitReadStatement(ctx);
    }

    @Override
    public Pair<MyType, Object> visitWriteStatement(MyGrammarParser.WriteStatementContext ctx) {
        return super.visitWriteStatement(ctx);
    }

    @Override
    public Pair<MyType, Object> visitPrintExpr(MyGrammarParser.PrintExprContext ctx) {
        var val = visit(ctx.expr());
        if(!val.a.equals(MyType.Error)) {
            System.out.println(val.b);
            return new Pair<>(MyType.Empty, 0);
        }
        else {
            ErrorTracker.PrintEraseErr();
            return new Pair<>(MyType.Error, 0);
        }
    }

    @Override
    public Pair<MyType, Object> visitEmptyStatement(MyGrammarParser.EmptyStatementContext ctx) {
        return super.visitEmptyStatement(ctx);
    }

    @Override
    public Pair<MyType, Object> visitMulDivMod(MyGrammarParser.MulDivModContext ctx) {
        return super.visitMulDivMod(ctx);
    }

    @Override
    public Pair<MyType, Object> visitParens(MyGrammarParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Pair<MyType, Object> visitNegation(MyGrammarParser.NegationContext ctx) {
        return super.visitNegation(ctx);
    }

    @Override
    public Pair<MyType, Object> visitComparison(MyGrammarParser.ComparisonContext ctx) {
        return super.visitComparison(ctx);
    }

    @Override
    public Pair<MyType, Object> visitBool(MyGrammarParser.BoolContext ctx) {
        var val = ctx.getText();
        if(val.equals("true")){
            return new Pair<>(MyType.Boolean, true);
        } else if (val.equals("false")){
            return new Pair<>(MyType.Boolean, false);
        } else {
            return new Pair<>(MyType.Error, 0);
        }
    }

    @Override
    public Pair<MyType, Object> visitString(MyGrammarParser.StringContext ctx) {
        return new Pair<>(MyType.String, ctx.STRING().getText().replace("\"", ""));
    }

    @Override
    public Pair<MyType, Object> visitAssignment(MyGrammarParser.AssignmentContext ctx) {
        return super.visitAssignment(ctx);
    }

    @Override
    public Pair<MyType, Object> visitLogicalAnd(MyGrammarParser.LogicalAndContext ctx) {
        return super.visitLogicalAnd(ctx);
    }

    @Override
    public Pair<MyType, Object> visitFloat(MyGrammarParser.FloatContext ctx) {
        return new Pair<>(MyType.Float, Float.parseFloat(ctx.FLOAT().getText()));
    }

    @Override
    public Pair<MyType, Object> visitInt(MyGrammarParser.IntContext ctx) {
        return new Pair<>(MyType.Int, Integer.parseInt(ctx.INT().getText()));
    }

    @Override
    public Pair<MyType, Object> visitRelation(MyGrammarParser.RelationContext ctx) {
        return super.visitRelation(ctx);
    }

    @Override
    public Pair<MyType, Object> visitAddSubCon(MyGrammarParser.AddSubConContext ctx) {
        return super.visitAddSubCon(ctx);
    }

    @Override
    public Pair<MyType, Object> visitUnaryMinus(MyGrammarParser.UnaryMinusContext ctx) {
        var op = visit(ctx.expr());
        if(op.a.equals(MyType.Error)) {
            return new Pair<>(MyType.Error, 0);
        }
        switch (op.a)
        {
            case Int -> {return new Pair<>(MyType.Int, -(int)op.b);}
            case Float -> {return new Pair<>(MyType.Float, -(float)op.b);}
            default -> {
                ErrorTracker.NewError(ctx.SUB().getSymbol(), "Expression " + ctx.SUB().getText() + " " + op.b + " has wrong operands!");
                ErrorTracker.PrintEraseErr();
                return new Pair<>(MyType.Error, 0);
            }
        }
    }

    @Override
    public Pair<MyType, Object> visitId(MyGrammarParser.IdContext ctx) {
        return stack.getVal(ctx.IDENTIFIER().getSymbol());
    }

    @Override
    public Pair<MyType, Object> visitLogicalOr(MyGrammarParser.LogicalOrContext ctx) {
        return super.visitLogicalOr(ctx);
    }

    @Override
    public Pair<MyType, Object> visitPrimitiveType(MyGrammarParser.PrimitiveTypeContext ctx) {
        if(ctx.type.getText().equals("int")){
            return new Pair<>(MyType.Int,0);
        } else if (ctx.type.getText().equals("float")) {
            return new Pair<>(MyType.Float,0.f);
        } else if (ctx.type.getText().equals("string")) {
            return new Pair<>(MyType.String,"");
        } else if (ctx.type.getText().equals("bool")) {
            return new Pair<>(MyType.Boolean,false);
        } else {
          return new Pair<>(MyType.Error,0);
        }
    }
}
