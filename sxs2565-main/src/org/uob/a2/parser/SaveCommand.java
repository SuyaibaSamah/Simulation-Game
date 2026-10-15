package org.uob.a2.parser;

import org.uob.a2.engine.*;
import java.util.List;

public class SaveCommand extends Command {

    public SaveCommand(List<String> words) {
        super(words);
    }

    @Override
    public String execute(Context ctx) {
        if (words.size() < 2) {
            return "You must specify a filename.";
        }

        String filename = words.get(1);
        return ctx.engine().save(filename);
    }
}
