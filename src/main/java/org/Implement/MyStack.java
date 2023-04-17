package org.Implement;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Pair;

import java.util.Hashtable;
import java.util.Map;

public class MyStack {
    Map<String, Pair<MyType, Object>> map = new Hashtable<>();

    public void Add(Token variable, MyType type)
    {
        var name = variable.getText().trim();
        if(map.containsKey(name))
        {
            ErrorTracker.NewError(variable, variable + "is already in stack!");
        }
        else
        {
            if(type == MyType.Int) {
                map.put(name, new Pair<>(type, 0));
            } else if (type == MyType.Float) {
                map.put(name, new Pair<>(type, 0.f));
            } else if (type == MyType.String) {
                map.put(name, new Pair<>(type, ""));
            } else {
                map.put(name, new Pair<>(type, false));
            }
        }
    }

    public void setVal(Token val, Pair<MyType, Object> vari)
    {
        var name = val.getText().trim();
        map.put(name, vari);
    }

    public Pair<MyType, Object> getVal(Token val)
    {
        var name = val.getText().trim();
        if(map.containsKey(name))
        {
            return map.get(name);
        }
        else
        {
            ErrorTracker.NewError(val, val + " was NOT added to stack!");
            return new Pair<>(MyType.Error, 0);
        }
    }
}
