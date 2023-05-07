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
    boolean isFirst = true;
    String strFirst = "";
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
            if(s == null) {
                s = "";
            }
            str += s;
            System.out.print(s);
        }
        File file = new File("PLC_errors.out.txt");
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
        int jmp = genNum();
        int posEnd = genNum();
        neg += (ctx.neg == null) ? "" : property.get(ctx.neg);
        pos += property.get(ctx.pos);
        property.put(ctx, cond + "fjmp " + jmp + "\n" + pos + "jmp " + posEnd + "\n" + "label " + jmp + "\n" + neg + "label " + posEnd + "\n");
    }

    @Override
    public void exitWhile(MyGrammarParser.WhileContext ctx) {
        var cond = property.get(ctx.expr());
        String body = "";
        int start = genNum();
        int end = genNum();
        body += property.get(ctx.statement());
        body += "jmp " + start + "\n";
        property.put(ctx, "label " + start + "\n" + cond + "fjmp " + end + "\n" + body + "label " + end + "\n");
    }

    @Override
    public void exitReadStatement(MyGrammarParser.ReadStatementContext ctx) {
        String str = "";
        boolean flag = false;
        for (var id: ctx.IDENTIFIER()) {
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
            if(exprProp == null){
                exprProp = "";
            }
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
                    if(leftVal.a.equals(MyType.Float) && rightVal.a.equals(MyType.Float)){
                        property.put(ctx, leftProp + rightProp + "mul\n");
                    } else if(leftVal.a.equals(MyType.Float)){
                        property.put(ctx, leftProp + rightProp + "itof\n" + "mul\n");
                    } else {
                        property.put(ctx, leftProp + "itof\n" + rightProp + "mul\n");
                    }
                } else {
                    values.put(ctx, new Pair<>(MyType.Int, (int)leftVal.b * (int)rightVal.b));
                    property.put(ctx, leftProp + rightProp + "mul\n");
                }
            }
            case MyGrammarParser.DIV -> {
                if(leftVal.a.equals(MyType.Float) || rightVal.a.equals(MyType.Float)){
                    values.put(ctx, new Pair<>(MyType.Float, ToFloat(leftVal.b) / ToFloat(rightVal.b)));
                    if(leftVal.a.equals(MyType.Float) && rightVal.a.equals(MyType.Float)){
                        property.put(ctx, leftProp + rightProp + "div\n");
                    } else if(leftVal.a.equals(MyType.Float)){
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
    public void exitParens(MyGrammarParser.ParensContext ctx) {
        property.put(ctx, property.get(ctx.expr()));
        values.put(ctx, values.get(ctx.expr()));
    }

    @Override
    public void exitNegation(MyGrammarParser.NegationContext ctx) {
        var op = property.get(ctx.expr());
        var val = values.get(ctx.expr());
        values.put(ctx, new Pair<>(MyType.Boolean, !(boolean)val.b));
        property.put(ctx, op + "not\n");
    }

    @Override
    public void exitComparison(MyGrammarParser.ComparisonContext ctx) {
        var leftProp = property.get(ctx.expr().get(0));
        var rightProp = property.get(ctx.expr().get(1));
        var leftVal = values.get(ctx.expr().get(0));
        var rightVal = values.get(ctx.expr().get(1));
        switch (ctx.op.getType()){
            case MyGrammarParser.EQ -> {
                if(leftVal.a.equals(MyType.Float) || rightVal.a.equals(MyType.Float)){
                    values.put(ctx, new Pair<>(MyType.Boolean, ToFloat(leftVal.b) == ToFloat(rightVal.b)));
                    if(leftVal.a.equals(MyType.Float)){
                        property.put(ctx, leftProp + rightProp + "itof\n" + "eq\n");
                    } else {
                        property.put(ctx, leftProp + "itof\n" + rightProp + "eq\n");
                    }
                }else if (leftVal.a.equals(MyType.String)){
                    values.put(ctx, new Pair<>(MyType.Boolean, leftVal.b.toString() == rightVal.b.toString()));
                    property.put(ctx, leftProp + rightProp + "eq\n");
                } else {
                    values.put(ctx, new Pair<>(MyType.Boolean, (int)leftVal.b == (int)rightVal.b));
                    property.put(ctx, leftProp + rightProp + "eq\n");
                }
            }
            case MyGrammarParser.NEQ -> {
                if(leftVal.a.equals(MyType.Float) || rightVal.a.equals(MyType.Float)){
                    values.put(ctx, new Pair<>(MyType.Boolean, ToFloat(leftVal.b) != ToFloat(rightVal.b)));
                    if(leftVal.a.equals(MyType.Float)){
                        property.put(ctx, leftProp + rightProp + "itof\n" + "eq\n" + "not\n");
                    } else {
                        property.put(ctx, leftProp + "itof\n" + rightProp + "eq\n" + "not\n");
                    }
                } else if(leftVal.a.equals(MyType.String)){
                    values.put(ctx, new Pair<>(MyType.Boolean, leftVal.b.toString() != rightVal.b.toString()));
                    property.put(ctx, leftProp + rightProp + "eq\n" + "not\n");
                } else {
                    values.put(ctx, new Pair<>(MyType.Boolean, (int)leftVal.b != (int)rightVal.b));
                    property.put(ctx, leftProp + rightProp + "eq\n" + "not\n");
                }
            }
        }
    }

    @Override
    public void exitBool(MyGrammarParser.BoolContext ctx) {
        var val = ctx.getText();
        property.put(ctx, "push B " + val + "\n");
        if(val.equals("true")){
            values.put(ctx, new Pair<>(MyType.Boolean, true));
        } else {
            values.put(ctx, new Pair<>(MyType.Boolean, false));
        }
    }

    @Override
    public void exitString(MyGrammarParser.StringContext ctx) {
        var val = ctx.STRING().getText();
        property.put(ctx, "push S " + val + "\n");
        values.put(ctx, new Pair<>(MyType.String, ctx.STRING().getText().replace('\\', ' ')));
    }

    @Override
    public void enterAssignment(MyGrammarParser.AssignmentContext ctx) {
        if(isFirst){
            isFirst=false;
            strFirst=ctx.expr().getText();
        }
    }

    @Override
    public void exitAssignment(MyGrammarParser.AssignmentContext ctx) {
        var right = property.get(ctx.expr());
        var rightVal = values.get(ctx.expr());
        var val = stack.getVal(ctx.IDENTIFIER().getSymbol());
        String res = ctx.IDENTIFIER().getText();
        if(val.a.equals(rightVal.a)){
            stack.setVal(ctx.IDENTIFIER().getSymbol(), rightVal);
            values.put(ctx, rightVal);
            property.put(ctx, right + "save " + res + "\n" + "load " + res + "\n");
        } else {
            var tmp = new Pair<>(MyType.Float, rightVal.b);
            stack.setVal(ctx.IDENTIFIER().getSymbol(), tmp);
            values.put(ctx, tmp);
            property.put(ctx, right + "itof\n" + "save " + res + "\n" + "load " + res + "\n");
        }
        if (ctx.expr().getText().equals(strFirst) && !isFirst) {
            isFirst = true;
            right = property.get(ctx);
            property.put(ctx, right + "pop\n");
        }
    }

    @Override
    public void exitLogicalAnd(MyGrammarParser.LogicalAndContext ctx) {
        var leftProp = property.get(ctx.expr().get(0));
        var rightProp = property.get(ctx.expr().get(1));
        var leftVal = values.get(ctx.expr().get(0));
        var rightVal = values.get(ctx.expr().get(1));
        values.put(ctx, new Pair<>(MyType.Boolean, (boolean)leftVal.b && (boolean)rightVal.b));
        property.put(ctx, leftProp + rightProp + "and\n");
    }

    @Override
    public void exitFloat(MyGrammarParser.FloatContext ctx) {
        var val = Float.parseFloat(ctx.FLOAT().getText());
        property.put(ctx, "push F " + val + "\n");
        values.put(ctx, new Pair<>(MyType.Float, val));
    }

    @Override
    public void exitInt(MyGrammarParser.IntContext ctx) {
        var val = Integer.parseInt(ctx.INT().getText(), 10);
        property.put(ctx, "push I " + val + "\n");
        values.put(ctx, new Pair<>(MyType.Int, val));
    }

    @Override
    public void exitRelation(MyGrammarParser.RelationContext ctx) {
        var leftProp = property.get(ctx.expr().get(0));
        var rightProp = property.get(ctx.expr().get(1));
        var leftVal = values.get(ctx.expr().get(0));
        var rightVal = values.get(ctx.expr().get(1));
        switch (ctx.op.getType()){
            case MyGrammarParser.LES -> {
                if(leftVal.a.equals(MyType.Float) || rightVal.a.equals(MyType.Float)){
                    values.put(ctx, new Pair<>(MyType.Boolean, ToFloat(leftVal.b) < ToFloat(rightVal.b)));
                    if(leftVal.a.equals(MyType.Float)){
                        property.put(ctx, leftProp + rightProp + "itof\n" + "lt\n");
                    } else {
                        property.put(ctx, leftProp + "itof\n" + rightProp + "lt\n");
                    }
                } else {
                    values.put(ctx, new Pair<>(MyType.Boolean, (int)leftVal.b < (int)rightVal.b));
                    property.put(ctx, leftProp + rightProp + "lt\n");
                }
            }
            case MyGrammarParser.GRE -> {
                if(leftVal.a.equals(MyType.Float) || rightVal.a.equals(MyType.Float)){
                    values.put(ctx, new Pair<>(MyType.Boolean, ToFloat(leftVal.b) > ToFloat(rightVal.b)));
                    if(leftVal.a.equals(MyType.Float)){
                        property.put(ctx, leftProp + rightProp + "itof\n" + "gt\n");
                    } else {
                        property.put(ctx, leftProp + "itof\n" + rightProp + "gt\n");
                    }
                } else {
                    values.put(ctx, new Pair<>(MyType.Boolean, (int)leftVal.b > (int)rightVal.b));
                    property.put(ctx, leftProp + rightProp + "gt\n");
                }
            }
        }
    }

    @Override
    public void exitAddSubCon(MyGrammarParser.AddSubConContext ctx) {
        var leftProp = property.get(ctx.expr().get(0));
        var rightProp = property.get(ctx.expr().get(1));
        var leftVal = values.get(ctx.expr().get(0));
        var rightVal = values.get(ctx.expr().get(1));
        switch (ctx.op.getType()){
            case MyGrammarParser.ADD -> {
                if(leftVal.a.equals(MyType.Float) || rightVal.a.equals(MyType.Float)){
                    values.put(ctx, new Pair<>(MyType.Float, ToFloat(leftVal.b) + ToFloat(rightVal.b)));
                    if(leftVal.a.equals(MyType.Float)){
                        property.put(ctx, leftProp + rightProp + "itof\n" + "add\n");
                    } else {
                        property.put(ctx, leftProp + "itof\n" + rightProp + "add\n");
                    }
                } else {
                    values.put(ctx, new Pair<>(MyType.Int, (int)leftVal.b + (int)rightVal.b));
                    property.put(ctx, leftProp + rightProp + "add\n");
                }
            }
            case MyGrammarParser.SUB -> {
                if(leftVal.a.equals(MyType.Float) || rightVal.a.equals(MyType.Float)){
                    values.put(ctx, new Pair<>(MyType.Float, ToFloat(leftVal.b) - ToFloat(rightVal.b)));
                    if(leftVal.a.equals(MyType.Float)){
                        property.put(ctx, leftProp + rightProp + "itof\n" + "sub\n");
                    } else {
                        property.put(ctx, leftProp + "itof\n" + rightProp + "sub\n");
                    }
                } else {
                    values.put(ctx, new Pair<>(MyType.Int, (int)leftVal.b - (int)rightVal.b));
                    property.put(ctx, leftProp + rightProp + "sub\n");
                }
            }
            case MyGrammarParser.CON -> {
                values.put(ctx, new Pair<>(MyType.String, leftVal.b.toString() + rightVal.b.toString()));
                property.put(ctx, leftProp + rightProp + "concat\n");
            }
        }
    }

    @Override
    public void exitUnaryMinus(MyGrammarParser.UnaryMinusContext ctx) {
        var val = values.get(ctx.expr());
        var prop = property.get(ctx.expr());
        switch (val.a){
            case Int -> {
                values.put(ctx, new Pair<>(MyType.Int, -(int)val.b));
                property.put(ctx, prop + "uminus\n");
            }
            case Float -> {
                values.put(ctx, new Pair<>(MyType.Float, -ToFloat(val.b)));
                property.put(ctx, prop + "uminus\n");
            }
        }
    }

    @Override
    public void exitId(MyGrammarParser.IdContext ctx) {
        values.put(ctx, stack.getVal(ctx.IDENTIFIER().getSymbol()));
        property.put(ctx, "load " + ctx.IDENTIFIER().getText() + "\n");
    }

    @Override
    public void exitLogicalOr(MyGrammarParser.LogicalOrContext ctx) {
        var leftProp = property.get(ctx.expr().get(0));
        var rightProp = property.get(ctx.expr().get(1));
        var leftVal = values.get(ctx.expr().get(0));
        var rightVal = values.get(ctx.expr().get(1));
        values.put(ctx, new Pair<>(MyType.Boolean, (boolean)leftVal.b || (boolean)rightVal.b));
        property.put(ctx, leftProp + rightProp + "or\n");
    }

    @Override
    public void exitPrimitiveType(MyGrammarParser.PrimitiveTypeContext ctx) {
        if(ctx.type.getText().equals("int")){
            values.put(ctx, new Pair<>(MyType.Int, 0));
            property.put(ctx, "push I 0\n");
        } else if(ctx.type.getText().equals("float")){
            values.put(ctx, new Pair<>(MyType.Float, 0.0));
            property.put(ctx, "push F 0.0\n");
        } else if(ctx.type.getText().equals("string")){
            values.put(ctx, new Pair<>(MyType.String, ""));
            property.put(ctx, "push S \"\"\n");
        } else {
            values.put(ctx, new Pair<>(MyType.Boolean, true));
            property.put(ctx, "push B true\n");
        }
    }
}
