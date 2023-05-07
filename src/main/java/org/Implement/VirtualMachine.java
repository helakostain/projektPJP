package org.Implement;

import org.antlr.v4.runtime.misc.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class VirtualMachine {
    private Map<Integer, Integer> labels = new Hashtable<>();
    private List<String> code = new ArrayList<>();
    private Map<String, Pair<MyType, Object>> memory = new Hashtable<>();
    private Stack<Pair<MyType, Object>> stack = new Stack<>();

    public VirtualMachine(String filename){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            int count = 0;
            code.add(line);
            count++;
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
                code.add(line);
                if(line == null)
                {
                    break;
                }
                if(line.startsWith("label")){
                    String[] split = line.split(" ");
                    int index = Integer.parseInt(split[1]);
                    labels.put(index, count);
                }
                count++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load(String str){
        if(memory.containsKey(str)){
            stack.push(memory.get(str));
        }else{
            throw new RuntimeException("Variable " + str + " was not initialized!!");
        }
    }

    private void save(String str){
        var val = stack.pop();
        memory.put(str, val);
    }

    private void print(int numObjects){
        List<Object> objects = new ArrayList<>();
        for(int i = 0; i < numObjects; i++){
            objects.add(stack.pop().b);
        }
        Collections.reverse(objects);
        for (var obj: objects) {
            System.out.print(obj);
        }
        System.out.print("\n");
    }

    private void read(String str){
        Scanner scanner = new Scanner(System. in);
        String inputString = scanner. nextLine();
        switch (str){
            case "I" -> {
                try{
                    int out = Integer.parseInt(inputString);
                    stack.push(new Pair<>(MyType.Int, out));
                } catch (NumberFormatException e){
                    throw new RuntimeException("Read value was expected to be Int but it is not: " + e.getMessage());
                }
            }
            case "F" -> {
                try{
                    float out = Float.parseFloat(inputString);
                    stack.push(new Pair<>(MyType.Float, out));
                } catch (NumberFormatException e){
                    throw new RuntimeException("Read value was expected to be Float but it is not: " + e.getMessage());
                }
            }
            case "B" -> {
                if(inputString.equals("true")){
                    stack.push(new Pair<>(MyType.Boolean, true));
                } else if (inputString.equals("false")) {
                    stack.push(new Pair<>(MyType.Boolean, false));
                } else {
                    throw new RuntimeException("Read value was expected to be Boolean but it is not, the value is: " + inputString);
                }
            }
            case "S" -> {
                stack.push(new Pair<>(MyType.String, inputString.replace("\"", "")));
            }
        }
    }

    private void push(String type, String val){
        switch (type){
            case "I" -> {
                try{
                    int out = Integer.parseInt(val);
                    stack.push(new Pair<>(MyType.Int, out));
                } catch (NumberFormatException e){
                    throw new RuntimeException("Read value was expected to be Int but it is not: " + e.getMessage());
                }
            }
            case "F" -> {
                try{
                    float out = Float.parseFloat(val);
                    stack.push(new Pair<>(MyType.Float, out));
                } catch (NumberFormatException e){
                    throw new RuntimeException("Read value was expected to be Float but it is not: " + e.getMessage());
                }
            }
            case "B" -> {
                if(val.equals("true")){
                    stack.push(new Pair<>(MyType.Boolean, true));
                } else if (val.equals("false")) {
                    stack.push(new Pair<>(MyType.Boolean, false));
                } else {
                    throw new RuntimeException("Read value was expected to be Boolean but it is not, the value is: " + val);
                }
            }
            case "S" -> {
                stack.push(new Pair<>(MyType.String, val.replace("\"", "")));
            }
        }
    }

    private void itof(){
        var val = stack.pop();
        if(val.a.equals(MyType.Int)){
            stack.push(new Pair<>(MyType.Float, ((Number) val.b).floatValue()));
        } else if(val.a.equals(MyType.Float)){
            stack.push(new Pair<>(MyType.Float, val.b));
        } else {
            throw new RuntimeException("Failed to perform itof as value " + val.toString() + " is not int or float");
        }
    }

    private void not(){
        stack.push(new Pair<>(MyType.Boolean, !(boolean) stack.pop().b));
    }

    private void eq(){
        Pair<MyType, Object> right = stack.pop();
        Pair<MyType, Object> left = stack.pop();
        switch (left.a){
            case Int -> {
                stack.push(new Pair<>(MyType.Boolean, (int)left.b == (int)right.b));
            }
            case Float -> {
                stack.push(new Pair<>(MyType.Boolean, (float)left.b == (float)right.b));
            }
            case Boolean -> {
                stack.push(new Pair<>(MyType.Boolean, (boolean)left.b == (boolean)right.b));
            }
            case String -> {
                stack.push(new Pair<>(MyType.Boolean, left.b.toString().equals(right.b.toString())));
            }
        }
    }

    private void lt(){
        Pair<MyType, Object> right = stack.pop();
        Pair<MyType, Object> left = stack.pop();
        switch (left.a){
            case Int -> {
                stack.push(new Pair<>(MyType.Boolean, (int)left.b < (int)right.b));
            }
            case Float -> {
                stack.push(new Pair<>(MyType.Boolean, (float)left.b < (float)right.b));
            }
        }
    }

    private void gt(){
        Pair<MyType, Object> right = stack.pop();
        Pair<MyType, Object> left = stack.pop();
        switch (left.a){
            case Int -> {
                stack.push(new Pair<>(MyType.Boolean, (int)left.b > (int)right.b));
            }
            case Float -> {
                stack.push(new Pair<>(MyType.Boolean, (float)left.b > (float)right.b));
            }
        }
    }

    private void or(){
        Pair<MyType, Object> right = stack.pop();
        Pair<MyType, Object> left = stack.pop();
        if(left.a.equals(MyType.Boolean)){
            stack.push(new Pair<>(MyType.Boolean, (boolean)left.b || (boolean)right.b));
        }
    }

    private void and(){
        Pair<MyType, Object> right = stack.pop();
        Pair<MyType, Object> left = stack.pop();
        if(left.a.equals(MyType.Boolean)){
            stack.push(new Pair<>(MyType.Boolean, (boolean)left.b && (boolean)right.b));
        }
    }

    private void concat(){
        Pair<MyType, Object> right = stack.pop();
        Pair<MyType, Object> left = stack.pop();
        if(left.a.equals(MyType.String)){
            stack.push(new Pair<>(MyType.String, left.b.toString() + right.b.toString()));
        }
    }

    private void uminus(){
        Pair<MyType, Object> pair = stack.pop();
        switch (pair.a){
            case Int -> {
                stack.push(new Pair<>(MyType.Int, -(int)pair.b));
            }
            case Float -> {
                stack.push(new Pair<>(MyType.Float, -(float)pair.b));
            }
        }
    }

    private void mod(){
        Pair<MyType, Object> right = stack.pop();
        Pair<MyType, Object> left = stack.pop();
        stack.push(new Pair<>(MyType.Int, (int)left.b % (int)right.b));
    }

    private void div(){
        Pair<MyType, Object> right = stack.pop();
        Pair<MyType, Object> left = stack.pop();
        switch (left.a){
            case Int -> {
                if((int)right.b == 0)
                {
                    throw new RuntimeException("Trying to divide with " + right.b + "!!!!!");
                }
                stack.push(new Pair<>(MyType.Int, (int)left.b / (int)right.b));
            }
            case Float -> {
                if((float)right.b == 0.0)
                {
                    throw new RuntimeException("Trying to divide with " + right.b + "!!!!!");
                }
                stack.push(new Pair<>(MyType.Float, (float)left.b / (float)right.b));
            }
        }
    }

    private void mul(){
        Pair<MyType, Object> right = stack.pop();
        Pair<MyType, Object> left = stack.pop();
        switch (left.a){
            case Int -> {
                stack.push(new Pair<>(MyType.Int, (int)left.b * (int)right.b));
            }
            case Float -> {
                stack.push(new Pair<>(MyType.Float, (float)left.b * (float)right.b));
            }
        }
    }

    private void sub(){
        Pair<MyType, Object> right = stack.pop();
        Pair<MyType, Object> left = stack.pop();
        switch (left.a){
            case Int -> {
                stack.push(new Pair<>(MyType.Int, (int)left.b - (int)right.b));
            }
            case Float -> {
                stack.push(new Pair<>(MyType.Float, (float)left.b - (float)right.b));
            }
        }
    }

    private void add(){
        Pair<MyType, Object> right = stack.pop();
        Pair<MyType, Object> left = stack.pop();
        switch (left.a){
            case Int -> {
                stack.push(new Pair<>(MyType.Int, (int)left.b + (int)right.b));
            }
            case Float -> {
                stack.push(new Pair<>(MyType.Float, (float)left.b + (float)right.b));
            }
        }
    }

    public void run(){
        int current = 0;
        while(current < code.size()){
            var test = code.get(current);
            if(test == null) {
                break;
            }
            var cmd = test.split(" ");
            if(cmd.length > 3){
                cmd = code.get(current).split(" ",3);
            }
            //System.out.println(Arrays.toString(cmd));
            //TODO: to musi jit predelat do switche ne?
            switch (cmd[0]) {
                case "jmp" -> {
                    int to = Integer.parseInt(cmd[1]);
                    current = labels.get(to);
                    System.out.println("");
                }
                case "fjmp" -> {
                    var val = stack.pop();
                    if ((boolean) val.b) {
                        current++;
                    } else {
                        current = labels.get(Integer.parseInt(cmd[1]));
                    }
                }
                case "label" -> current++;
                case "load" -> {
                    load(cmd[1]);
                    current++;
                }
                case "save" -> {
                    save(cmd[1]);
                    current++;
                }
                case "print" -> {
                    int tmp = Integer.parseInt(cmd[1]);
                    print(tmp);
                    current++;
                }
                case "read" -> {
                    var type = cmd[1];
                    read(type);
                    current++;
                }
                case "pop" -> {
                    stack.pop();
                    current++;
                }
                case "push" -> {
                    var type = cmd[1];
                    var obj = cmd[2];
                    if (type == "S") {
                        for (int i = 3; i < cmd.length; i++) {
                            obj += " " + cmd[i];
                        }
                    }
                    push(type, obj);
                    current++;
                }
                case "itof" -> {
                    itof();
                    current++;
                }
                case "not" -> {
                    not();
                    current++;
                }
                case "eq" -> {
                    eq();
                    current++;
                }
                case "lt" -> {
                    lt();
                    current++;
                }
                case "gt" -> {
                    gt();
                    current++;
                }
                case "or" -> {
                    or();
                    current++;
                }
                case "and" -> {
                    and();
                    current++;
                }
                case "concat" -> {
                    concat();
                    current++;
                }
                case "uminus" -> {
                    uminus();
                    current++;
                }
                case "mod" -> {
                    mod();
                    current++;
                }
                case "div" -> {
                    div();
                    current++;
                }
                case "mul" -> {
                    mul();
                    current++;
                }
                case "sub" -> {
                    sub();
                    current++;
                }
                case "add" -> {
                    add();
                    current++;
                }
            }
        }
    }
}
