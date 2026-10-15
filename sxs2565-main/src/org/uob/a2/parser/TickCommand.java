package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import org.uob.a2.*;
import java.util.List;

public class TickCommand extends Command { 

    public TickCommand(List<String> words){
        super(words);
    } 

    @Override
    public String execute(Context ctx) {
        int n = 1; // default number of ticks

        if (words.size() > 1) {
            if (isInteger(words.get(1))) {
                n = Integer.parseInt(words.get(1));
            } else {
                return "Invalid tick command.";
            }
        }

        for (int i = 0; i < n; i++) {
            ctx.engine().nextTick();
        }

        return "Tick advanced by " + n;
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
