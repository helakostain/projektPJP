package org.Implement;

import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class ErrorTracker {
    private static List<String> errorData = new ArrayList<>();

    static public void NewError(Token token, String mess)
    {
        errorData.add(token.getLine() + ":" + token.getChannel() + " - " + mess);
    }

    public static int NumErrors()
    {
        return errorData.size();
    }

    public static void PrintEraseErr()
    {
        for (var err: errorData){
            System.err.println(err);
        }
        errorData.clear();
    }
}
