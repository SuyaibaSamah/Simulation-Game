package org.uob.a2.parser;

import org.uob.a2.*;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class Parser {
    
    public Command parse(String command) {
        // sanitize input:
        command = command.trim().toLowerCase();
        if (command.isEmpty()) {
            return new TickCommand(new ArrayList<>());
        }
        List<String> words = new ArrayList<>(Arrays.asList(command.split(" ")));
        // splits both words in the input and puts it into an array list of fixed size(i think?): index 0 is command
        // [  ,  ]
        
        
        String firstWord = words.get(0);
        
        switch (firstWord) {
            case "tick":
            case "t":
                return new TickCommand(words);
            
            case "build":
            case "b":
                return new BuildCommand(words);
            
            case "info":
            case "i":
                return new InfoCommand(words);
            
            case "help":
                return new HelpCommand(words);
            
            case "quit":
                return new QuitCommand(words);
            
            case "cheat":
                return new CheatCommand(words);
            
            case "graph":
            case "g":
                return new GraphCommand(words);
            
            case "save":
            case "s":
                return new SaveCommand(words);
            
            case "load":
            case "l":
                return new LoadCommand(words);
            
            case "deliver":
                return new DeliverCommand(words);
            
            default:
                return new InvalidCommand(words);
        }
    }
}