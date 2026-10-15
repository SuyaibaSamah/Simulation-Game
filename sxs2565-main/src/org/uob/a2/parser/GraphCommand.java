package org.uob.a2.parser;

import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;

import java.util.List;
import java.util.Map;

public class GraphCommand extends Command {

    public GraphCommand(List<String> words) {
        super(words);
    }

    @Override
    public String execute(Context ctx) {
        if (words.size() < 2) {
            return "Usage: graph <resource>";
        }

        String resName = words.get(1).toUpperCase();
        ResourceType resource = null;
        boolean inHistory = false;

        for (ResourceType r : ResourceType.values()) {
            if (r.name().equals(resName)) {
                resource = r;
                break;
            }
        }

        List<Integer> history = ctx.state().getResourceHistory(resource);

        StringBuilder sb = new StringBuilder();

        int tick = 0;
        for (int value : history) {
            for (int i = 0; i < value; i += 10) { // 1 '*' per 10 units
        sb.append("*");
            }
            sb.append(" (").append(value).append(")\n");
        }
        return sb.toString();
        }
}
