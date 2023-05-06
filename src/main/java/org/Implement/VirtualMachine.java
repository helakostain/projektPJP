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
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
                code.add(line);
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

    public void run(){
        int current = 0;
        while(current < code.size()){
            var cmd = code.get(current).split(" ");
            //TODO: to musi jit predelat do switche ne?
            if(cmd[0].equals("jmp")){
                int to = Integer.parseInt(cmd[1]);
                current = labels.get(to);
            } else if(cmd[0].equals("fjmp")){
                var val = stack.pop();
                if((boolean) val.b){
                    current++;
                } else {
                    current = labels.get(Integer.parseInt(cmd[1]));
                }
            } else if(cmd[0].equals("label")){
                current++;
            } else if(cmd[0].equals("load")){
                //Load(cmd[1]); //TODO: doimplementovat metody!
                current++;
            } else if(cmd[0].equals("save")){
                //Save(cmd[1]); //TODO: doimplementovat metody!
                current++;
            } else if(cmd[0].equals("print")){
                int tmp = Integer.parseInt(cmd[1]);
                //Print(tmp); //TODO: doimplementovat metody!
                current++;
            } else if(cmd[0].equals("read")){
                var type = cmd[1];
                //Read(type); //TODO: doimplementovat metody!
                current++;
            } else if(cmd[0].equals("pop")){
                stack.pop();
                current++;
            } else if(cmd[0].equals("push")){
                var type = cmd[1];
                var obj = cmd[2];
                if(type == "S"){
                    for(int i = 3; i < cmd.length; i++){
                        obj += " " + cmd[i];
                    }
                }
                //Push(type, obj); //TODO: doimplementovat metody!
                current++;
            } else if(cmd[0].equals("itof")){
                //Itof(); //TODO: doimplementovat metody!
                current++;
            } else if(cmd[0].equals("not")){
                //Not(); //TODO: doimplementovat metody!
                current++;
            } else if(cmd[0].equals("eq")){
                //Eq(); //TODO: doimplementovat metody!
                current++;
            } else if(cmd[0].equals("lt")){
                //Lt(); //TODO: doimplementovat metody!
                current++;
            } else if(cmd[0].equals("gt")){
                //Gt(); //TODO: doimplementovat metody!
                current++;
            } else if(cmd[0].equals("or")){
                //Or(); //TODO: doimplementovat metody!
                current++;
            } else if(cmd[0].equals("and")){
                //And(); //TODO: doimplementovat metody!
                current++;
            } else if(cmd[0].equals("concat")){
                //Concat(); //TODO: doimplementovat metody!
                current++;
            } else if(cmd[0].equals("uminus")){
                //Uminus(); //TODO: doimplementovat metody!
                current++;
            } else if(cmd[0].equals("mod")){
                //Mod(); //TODO: doimplementovat metody!
                current++;
            } else if(cmd[0].equals("div")){
                //Div(); //TODO: doimplementovat metody!
                current++;
            } else if(cmd[0].equals("mul")){
                //Mul(); //TODO: doimplementovat metody!
                current++;
            } else if(cmd[0].equals("sub")){
                //Sub(); //TODO: doimplementovat metody!
                current++;
            } else if(cmd[0].equals("add")){
                //Add(); //TODO: doimplementovat metody!
                current++;
            }
        }
    }
}
