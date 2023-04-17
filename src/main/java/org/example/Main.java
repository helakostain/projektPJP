package org.example;

import org.Implement.EvalVisitor;
import org.Implement.VerboseListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.gen.MyGrammarLexer;
import org.gen.MyGrammarParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FileInputStream inputFile = null;
        String filename = "PLC_t1.in";
        String path = "src/main/resources/"+filename;
        try {
            inputFile = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Parsing: " + filename);
        assert inputFile != null;
        ANTLRInputStream input = null;
        try {
            input = new ANTLRInputStream(inputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MyGrammarLexer lexer = new MyGrammarLexer((org.antlr.v4.runtime.CharStream) input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MyGrammarParser parser = new MyGrammarParser(tokens);

        parser.addErrorListener(new VerboseListener());

        ParseTree tree = parser.program();

        if(parser.getNumberOfSyntaxErrors() == 0)
        {
            new EvalVisitor().visit(tree);
        }
        else
        {
            System.out.println("ERROR");
        }
    }
}