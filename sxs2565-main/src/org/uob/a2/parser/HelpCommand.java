package org.uob.a2.parser;

import org.uob.a2.engine.*;
import java.util.List;

public class HelpCommand extends Command {

    public HelpCommand(List<String> words) {
        super(words);
    }
    
    // General help text
    String helpText = "Available commands:\n" +
                  "tick|t: advance simulation one tick\n" +
                  "build|b <entity>: create a new entity\n" +
                  "info|i <resources|entities>: show info\n" +
                  "help <topic>: show help\n" +
                  "cheat: add resources for testing\n" +
                  "graph|g <resource>: show resource over time\n" +
                  "save|s <filename>: save simulation\n" +
                  "load|l <filename>: load simulation\n" +
                    "quit: exit game" +
                  "deliver: perform final action\n" + "\n" +
                    "Producers: sugarcanefarm, cocoafarm, cowfarm, garden.\n" +
                    "Converters: mixer, heater, dipper.\n"+
                    "Consumer: deliverytruck.";
    @Override
    public String execute(Context ctx) {
        // general help
        if (words.size() < 2) {
            return helpText;
        } else {
            // specific help 
            String topic = words.get(1).toLowerCase();
            switch (topic) {
                case "tick":
                case "t":
                    return "tick|t: advance simulation one tick";
                case "build":
                case "b":
                    return "build|b <entity>: create a new entity";
                case "info":
                case "i":
                    return "info|i <resources|entities>: show info";
                case "help":
                    return "help <topic>: show help";
                case "cheat":
                    return "cheat: add resources for testing";
                case "graph":
                case "g":
                    return "graph|g <resource>: show resource over time";
                case "save":
                case "s":
                    return "save|s <filename>: save simulation";
                case "load":
                case "l":
                    return "load|l <filename>: load simulation";
                case "deliver":
                    return "deliver <entity>: perform final action";
                case "quit":
                    return "quit: exit game";
                case "entities":
                case "entity" :
                    return "Producers: sugarcanefarm, cocoafarm, cowfarm, garden.\n" +
                    "Converters: mixer, heater, dipper.\n"+
                    "Consumer: deliverytruck.";
                default:
                    return "No help available for: " + topic;
            }
        }
    }
}
