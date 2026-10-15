//  cheat code option that creates a large amount of each resource for testing.
package org.uob.a2.parser;

import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import java.util.List;
import org.uob.a2.*;

public class CheatCommand extends Command {

    public CheatCommand(List<String> words) {
        super(words);
    }

    @Override
    public String execute(Context ctx) {
        // add 10000 of each resource
        for (ResourceType resource : ResourceType.values()) {
            ctx.state().addResource(resource, 10000);
        }
        ctx.state().updateHistory();
        return "CHEAT MODE ACTIVATED! SUGAR RUSH!";
    }
}