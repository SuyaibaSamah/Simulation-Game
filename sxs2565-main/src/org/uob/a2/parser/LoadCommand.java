package org.uob.a2.parser;

import org.uob.a2.engine.*;
import java.util.List;

public class LoadCommand extends Command {

    public LoadCommand(List<String> words) {
        super(words);
    }

    @Override
    public String execute(Context ctx) {
        if (words.size() < 2) {
        return "Usage: load <filename>";
        }
        String filename = words.get(1);
        return ctx.engine().load(filename);
    }

}