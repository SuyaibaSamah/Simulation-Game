package org.uob.a2.parser;

import org.uob.a2.engine.*;
import java.util.List;

public class InvalidCommand extends Command {

    public InvalidCommand(List<String> words) {
        super(words);
    }

    @Override
    public String execute(Context ctx) {
        return "Invalid command. Type 'help' for available commands.";
    }
}