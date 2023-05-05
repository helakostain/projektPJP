package org.Implement;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Pair;
import org.gen.MyGrammarBaseVisitor;
import org.gen.MyGrammarParser;

import java.util.Scanner;

public class EvalVisitor extends MyGrammarBaseVisitor<Pair<MyType, Object>> {
    private MyStack stack = new MyStack();

    private Float ToFloat(Object val)
    {
        if(val instanceof Integer || val instanceof Float){
            //return (float)val;
            return ((Number) val).floatValue();
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
                //break;
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
        var cond = visit(ctx.expr());
        if(!cond.a.equals(MyType.Boolean)){
            ErrorTracker.NewError(ctx.IF().getSymbol(), "Condition for IF statement is not BOOLEAN!!!L");
            ErrorTracker.PrintEraseErr();
            return new Pair<>(MyType.Error, 0);
        }
        if((boolean)cond.b) {
            return visit(ctx.pos);
        }else {
            if(ctx.neg != null) {
                return visit(ctx.neg);
            }
        }
        return new Pair<>(MyType.Empty, 0);
    }

    @Override
    public Pair<MyType, Object> visitWhile(MyGrammarParser.WhileContext ctx) {
        var cond = visit(ctx.expr());
        if(!cond.a.equals(MyType.Boolean)) {
            ErrorTracker.NewError(ctx.WHILE().getSymbol(), "Condition for WHILE statement is not BOOLEAN!!");
            ErrorTracker.PrintEraseErr();
            return new Pair<>(MyType.Error,0);
        }
        var cond2 = cond;
        while ((boolean)cond2.b) //while cond 2 is true
        {
            var val = visit(ctx.statement());
            cond2 = visit(ctx.expr());
        }
        return new Pair<>(MyType.Empty,0);
    }

    @Override
    public Pair<MyType, Object> visitReadStatement(MyGrammarParser.ReadStatementContext ctx) {
        for (var id : ctx.IDENTIFIER()) {
            var variable = stack.getVal(id.getSymbol());
            System.out.print("Enter a " + variable.a + " : ");
            Scanner scanner = new Scanner(System. in);
            String inputString = scanner. nextLine();
            switch (variable.a)
            {
                case Int -> {
                    int out;
                    try {
                        out = Integer.parseInt(inputString);
                        stack.setVal(id.getSymbol(), new Pair<>(MyType.Int, out));
                    }
                    catch (NumberFormatException numberFormatException){
                        ErrorTracker.NewError(ctx.READ().getSymbol(), "This variable was supposed to be Integer!");
                        ErrorTracker.PrintEraseErr();
                        return new Pair<>(MyType.Error,0);
                    }
                }
                case Boolean -> {
                    if (inputString.equals("true")) {
                        stack.setVal(id.getSymbol(), new Pair<>(MyType.Boolean, true));
                    } else if (inputString.equals("false")) {
                        stack.setVal(id.getSymbol(), new Pair<>(MyType.Boolean, false));
                    } else {
                        ErrorTracker.NewError(ctx.READ().getSymbol(), "This variable is not boolean! (true, false)");
                        ErrorTracker.PrintEraseErr();
                        return new Pair<>(MyType.Error,0);
                    }
                }
                case Float -> {
                    float out;
                    try {
                        out = Float.parseFloat(inputString);
                        stack.setVal(id.getSymbol(), new Pair<>(MyType.Float, out));
                    }
                    catch (NumberFormatException numberFormatException) {
                        ErrorTracker.NewError(ctx.READ().getSymbol(), "This variable was supposed to be Float!");
                        ErrorTracker.PrintEraseErr();
                        return new Pair<>(MyType.Error,0);
                    }
                }
                case String -> {
                    stack.setVal(id.getSymbol(), new Pair<>(MyType.String, inputString.replace('\\', '\0')));
                }
                default -> {
                    return new Pair<>(MyType.Error,0);
                }
            }
        }
        return new Pair<>(MyType.Empty,0);
    }

    @Override
    public Pair<MyType, Object> visitWriteStatement(MyGrammarParser.WriteStatementContext ctx) {
        for (var expr : ctx.expr()) {
            var val = visit(expr);
            if(!val.a.equals(MyType.Error)){
                System.out.print(val.b);
            }
        }
        System.out.print("\n");
        return new Pair<>(MyType.Empty,0);
    }

    @Override
    public Pair<MyType, Object> visitPrintExpr(MyGrammarParser.PrintExprContext ctx) {
        var val = visit(ctx.expr());
        if(!val.a.equals(MyType.Error)) {
            System.out.print(val.b);
            return new Pair<>(MyType.Empty, 0);
        }
        else {
            ErrorTracker.PrintEraseErr();
            return new Pair<>(MyType.Error, 0);
        }
    }

    @Override
    public Pair<MyType, Object> visitEmptyStatement(MyGrammarParser.EmptyStatementContext ctx) {
        return new Pair<>(MyType.Empty,0);
    }

    @Override
    public Pair<MyType, Object> visitMulDivMod(MyGrammarParser.MulDivModContext ctx) {
        var left = visit(ctx.expr().get(0));
        var right = visit(ctx.expr().get(1));
        if(left.a.equals(MyType.Error) || right.a.equals(MyType.Error)) {
            return new Pair<>(MyType.Error, 0);
        }
        switch (ctx.op.getType())
        {
            case MyGrammarParser.MUL -> {
                if(left.a.equals(MyType.String) || right.a.equals(MyType.String)) {
                    ErrorTracker.NewError(ctx.MUL().getSymbol(), "Expression " + left.b + " " + ctx.MUL().getText() + " " + right.b + " has wrong operands");
                    ErrorTracker.PrintEraseErr();
                    return new Pair<>(MyType.Error, 0);
                }
                if(left.a.equals(MyType.Float) || right.a.equals(MyType.Float)) {
                    return new Pair<>(MyType.Float, (ToFloat(left.b) * ToFloat(right.b)));
                } else {
                    return new Pair<>(MyType.Int, ((int)left.b * (int)right.b));
                }
            }
            case MyGrammarParser.DIV -> {
                if(left.a.equals(MyType.String) || right.a.equals(MyType.String)) {
                    ErrorTracker.NewError(ctx.DIV().getSymbol(), "Expression " + left.b + " " + ctx.DIV().getText() + " " + right.b + " has wrong operands");
                    ErrorTracker.PrintEraseErr();
                    return new Pair<>(MyType.Error, 0);
                }
                if(left.a.equals(MyType.Float) || right.a.equals(MyType.Float)) {
                    if(((float)right.b) == 0.0) {
                        ErrorTracker.NewError(ctx.DIV().getSymbol(), "Expression " + left.b + " " + ctx.DIV().getText() + " " + right.b + " cant be divided by zero!");
                        ErrorTracker.PrintEraseErr();
                        return new Pair<>(MyType.Error, 0);
                    }
                    else {
                        return new Pair<>(MyType.Float, (ToFloat(left.b) / ToFloat(right.b)));
                    }

                } else {
                    if(((int)right.b) == 0) {
                        ErrorTracker.NewError(ctx.DIV().getSymbol(), "Expression " + left.b + " " + ctx.DIV().getText() + " " + right.b + " cant be divided by zero!");
                        ErrorTracker.PrintEraseErr();
                        return new Pair<>(MyType.Error, 0);
                    }
                    else {
                        return new Pair<>(MyType.Int, ((int)left.b / (int)right.b));
                    }
                }
            }
            case MyGrammarParser.MOD -> {
                if(left.a.equals(MyType.Int) || right.a.equals(MyType.Int)) {
                    return new Pair<>(MyType.Int, ((int)left.b % (int)right.b));
                } else {
                    ErrorTracker.NewError(ctx.MOD().getSymbol(), "Expression " + left.b + " " + ctx.MOD().getText() + " " + right.b + " has wrong operands!");
                    ErrorTracker.PrintEraseErr();
                    return new Pair<>(MyType.Error, 0);
                }
            }
            default -> {
                return new Pair<>(MyType.Error, 0);
            }
        }
    }

    @Override
    public Pair<MyType, Object> visitParens(MyGrammarParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Pair<MyType, Object> visitNegation(MyGrammarParser.NegationContext ctx) {
        var op = visit(ctx.expr());
        if(op.a.equals(MyType.Error)){ return new Pair<>(MyType.Error, 0); }
        switch (op.a){
            case Boolean -> { return new Pair<>(MyType.Boolean, !((boolean)op.b));}
            default -> {
                ErrorTracker.NewError(ctx.NEG().getSymbol(), "Expression " + ctx.NEG().getText() + " with value " + op.b + " have wrong operands!");
                ErrorTracker.PrintEraseErr();
                return new Pair<>(MyType.Error, 0);
            }
        }
    }

    @Override
    public Pair<MyType, Object> visitComparison(MyGrammarParser.ComparisonContext ctx) {
        var left = visit(ctx.expr().get(0));
        var right = visit(ctx.expr().get(1));
        if(left.a.equals(MyType.Error) || right.a.equals(MyType.Error)) {
            return new Pair<>(MyType.Error,0);
        }
        switch (ctx.op.getType()){
            case MyGrammarParser.EQ -> {
                if ((left.a.equals(MyType.Boolean) || right.a.equals(MyType.Boolean))) {
                    ErrorTracker.NewError(ctx.EQ().getSymbol(), "Expression " + left.b + " " + ctx.EQ().getText() + " " + right.b + " has wrong operands!");
                    ErrorTracker.PrintEraseErr();
                    return new Pair<>(MyType.Error, 0);
                }
                if (left.a.equals(MyType.Float) || right.a.equals(MyType.Float)) { //TODO: mozna blbost?
                    return new Pair<>(MyType.Boolean, ToFloat(left.b) == ToFloat(right.b));
                } else if (left.a.equals(MyType.Int) && right.a.equals(MyType.Int)) {
                    return new Pair<>(MyType.Boolean, (int)left.b == (int)right.b);
                }else {
                    return new Pair<>(MyType.Boolean, left.b.toString() == right.b.toString());
                }
            }
            case MyGrammarParser.NEQ -> {
                if(left.a.equals(MyType.Boolean) || right.a.equals(MyType.Boolean)){
                    ErrorTracker.NewError(ctx.NEQ().getSymbol(), "Expression " + left.b + " " + ctx.NEQ().getText() + " " + right.b + " has wrong operands!");
                    ErrorTracker.PrintEraseErr();
                    return new Pair<>(MyType.Error, 0);
                }
                if (left.a.equals(MyType.Float) || right.a.equals(MyType.Float)) { //TODO: mozna blbost?
                    return new Pair<>(MyType.Boolean, ToFloat(left.b) != ToFloat(right.b));
                } else if (left.a.equals(MyType.Int) && right.a.equals(MyType.Int)) {
                    return new Pair<>(MyType.Boolean, (int)left.b != (int)right.b);
                }else {
                    return new Pair<>(MyType.Boolean, left.b.toString() != right.b.toString());
                }
            }
            default -> {
                return new Pair<>(MyType.Error,0);
            }
        }
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
        var right = visit(ctx.expr());
        var val = stack.getVal(ctx.IDENTIFIER().getSymbol());
        if(val.a.equals(MyType.Error) || right.a.equals(MyType.Error)) { return new Pair<>(MyType.Error,0);}
        if(val.a.equals(right.a)) {
            stack.setVal(ctx.IDENTIFIER().getSymbol(), right);
        }
        if(val.a.equals(MyType.Float) && right.a.equals(MyType.Int)) {
            var val2 = new Pair<>(MyType.Float, right.b);
            stack.setVal(ctx.IDENTIFIER().getSymbol(), val2);
            return val2;
        }
        if(!val.a.equals(right.a)){
            ErrorTracker.NewError(ctx.IDENTIFIER().getSymbol(), "Variable " + ctx.IDENTIFIER().getText() + " type is " + val.a + " but assigned type is " + right.a);
            ErrorTracker.PrintEraseErr();
            return new Pair<>(MyType.Error, 0);
        }
        return new Pair<>(MyType.Error, 0);
    }

    @Override
    public Pair<MyType, Object> visitLogicalAnd(MyGrammarParser.LogicalAndContext ctx) {
        var left = visit(ctx.expr().get(0));
        var right = visit(ctx.expr().get(1));
        if(left.a.equals(MyType.Error) || right.a == MyType.Error) {
            return new Pair<>(MyType.Error,0);
        }
        if(!left.a.equals(MyType.Boolean) || !right.a.equals(MyType.Boolean)){
            ErrorTracker.NewError(ctx.AND().getSymbol(), "Expression " + left.b + " " + ctx.AND().getText() + " " + right.b + " have wrong operands!");
            ErrorTracker.PrintEraseErr();
            return new Pair<>(MyType.Error,0);
        }
        return new Pair<>(MyType.Boolean, ((boolean)left.b && (boolean)right.b));
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
        var left = visit(ctx.expr().get(0));
        var right = visit(ctx.expr().get(1));
        if(left.a.equals(MyType.Error) || right.a.equals(MyType.Error)) {
            return new Pair<>(MyType.Error,0);
        }
        switch (ctx.op.getType()){
            case MyGrammarParser.LES -> {
                if((left.a.equals(MyType.String) || right.a.equals(MyType.String)) || (left.a.equals(MyType.Boolean) || right.a.equals(MyType.Boolean))){
                    ErrorTracker.NewError(ctx.LES().getSymbol(), "Expression " + left.b + " " + ctx.LES().getText() + " " + right.b + " has wrong operands");
                    ErrorTracker.PrintEraseErr();
                    return new Pair<>(MyType.Error, 0);
                }
                if(left.a.equals(MyType.Float) || right.a.equals(MyType.Float)){
                    return new Pair<>(MyType.Boolean, ToFloat(left.b) < ToFloat(right.b));
                }
                else {
                    return new Pair<>(MyType.Boolean, (int)left.b < (int)right.b);
                }
            }
            case MyGrammarParser.GRE -> {
                if((left.a.equals(MyType.String) || right.a.equals(MyType.String)) || (left.a.equals(MyType.Boolean) || right.a.equals(MyType.Boolean))) {
                    ErrorTracker.NewError(ctx.GRE().getSymbol(), "Expression " + left.b + " " + ctx.GRE().getText() + " " + right.b + " has wrong operands");
                    ErrorTracker.PrintEraseErr();
                    return new Pair<>(MyType.Error, 0);
                }
                if(left.a.equals(MyType.Float) || right.a.equals(MyType.Float)){
                    return new Pair<>(MyType.Boolean, ToFloat(left.b) > ToFloat(right.b));
                }
                else {
                    return new Pair<>(MyType.Boolean, (int)left.b > (int)right.b);
                }
            }
            default -> { return new Pair<>(MyType.Error,0);}
        }
    }

    @Override
    public Pair<MyType, Object> visitAddSubCon(MyGrammarParser.AddSubConContext ctx) {
        var left = visit(ctx.expr().get(0));
        var right = visit(ctx.expr().get(1));
        if(left.a.equals(MyType.Error) || right.a.equals(MyType.Error)) {
            return new Pair<>(MyType.Error, 0);
        }
        switch (ctx.op.getType())
        {
            case MyGrammarParser.ADD -> {
                if((left.a.equals(MyType.String) || right.a.equals(MyType.String)) || (left.a.equals(MyType.Boolean) || right.a.equals(MyType.Boolean))) {
                    ErrorTracker.NewError(ctx.ADD().getSymbol(), "Expression " + left.b + " " + ctx.ADD().getText() + " " + right.b + " has wrong operands");
                    ErrorTracker.PrintEraseErr();
                    return new Pair<>(MyType.Error, 0);
                }
                if(left.a.equals(MyType.Float) || right.a.equals(MyType.Float)) {
                    return new Pair<>(MyType.Float, (ToFloat(left.b) + ToFloat(right.b)));
                } else {
                    //System.out.println(left.a + left.b.toString() + " " + right.a + right.b.toString());
                    return new Pair<>(MyType.Int, ((int)left.b + (int)right.b));
                }
            }
            case MyGrammarParser.SUB -> {
                if((left.a.equals(MyType.String) || right.a.equals(MyType.String)) || (left.a.equals(MyType.Boolean) || right.a.equals(MyType.Boolean))) {
                    ErrorTracker.NewError(ctx.SUB().getSymbol(), "Expression " + left.b + " " + ctx.SUB().getText() + " " + right.b + " has wrong operands");
                    ErrorTracker.PrintEraseErr();
                    return new Pair<>(MyType.Error, 0);
                }
                if(left.a.equals(MyType.Float) || right.a.equals(MyType.Float)) {
                    return new Pair<>(MyType.Float, (ToFloat(left.b) - ToFloat(right.b)));
                } else {
                    return new Pair<>(MyType.Int, ((int)left.b - (int)right.b));
                }
            }
            case MyGrammarParser.CON -> {
                if(left.a.equals(MyType.String) && right.a.equals(MyType.String)) {
                    return new Pair<>(MyType.String, (left.b.toString() + right.b.toString()));
                } else {
                    ErrorTracker.NewError(ctx.CON().getSymbol(), "Expression " + left.b + " " + ctx.CON().getText() + " " + right.b + " has wrong operands");
                    ErrorTracker.PrintEraseErr();
                    return new Pair<>(MyType.Error, 0);
                }
            }
            default -> { return new Pair<>(MyType.Error,0); }
        }
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
            case Float -> {return new Pair<>(MyType.Float, -ToFloat(op.b));}
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
        var left = visit(ctx.expr().get(0));
        var right = visit(ctx.expr().get(1));
        if(left.a.equals(MyType.Error) || right.a.equals(MyType.Error)) { return new Pair<>(MyType.Error,0);} //TODO: zautomatizovat tohle pres funkci?
        if((!left.a.equals(MyType.Boolean)) || (!right.a.equals(MyType.Boolean))){
            ErrorTracker.NewError(ctx.OR().getSymbol(), "Expression " + left.b + " " + ctx.OR().getText() + " " + right.b + " has wrong operands!");
            ErrorTracker.PrintEraseErr();
            return new Pair<>(MyType.Error, 0);
        }
        return new Pair<>(MyType.Boolean, ((boolean)left.b || (boolean)right.b));
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
