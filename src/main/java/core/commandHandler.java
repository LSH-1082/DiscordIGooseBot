package core;

import command.Command;
import java.util.HashMap;

public class commandHandler {

    public static final commandParser parse = new commandParser();
    public static HashMap<String, Command> command = new HashMap<>();

    public static void handlerCommand(commandParser.commandContainer cmd){
        if(command.containsKey(cmd.invoke)){
            boolean safe = command.get(cmd.invoke).called(cmd.args, cmd.event);

            if(!safe){
                command.get(cmd.invoke).action(cmd.args, cmd.event);
                command.get(cmd.invoke).executed(safe, cmd.event);
            }
            else{
                command.get(cmd.invoke).executed(safe, cmd.event);
            }
        }
        else{
            System.out.println();
        }
    }
}
