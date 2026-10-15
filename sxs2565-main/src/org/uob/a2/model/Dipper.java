package org.uob.a2.model;

import org.uob.a2.engine.Converter;
import org.uob.a2.engine.Context;
import org.uob.a2.engine.Tickable;
import org.uob.a2.engine.*;

import java.util.EnumMap;
import java.util.Map;

public class Dipper extends Converter implements Tickable {

    public Dipper(String name) {
        super(name, ResourceType.APPLE, 10, ResourceType.CANDY_APPLE, 10);
        addCost(ResourceType.CREDITS, 100);
    }

    @Override
    public void convert(Context ctx) {
        // check if enough input
        if (ctx.state().getResourceAmount(input) >= inputAmount) {
            ctx.state().removeResource(input, inputAmount);
            ctx.state().addResource(output, outputAmount);
        }
    }

    @Override
    public void tick(Context ctx) {
        convert(ctx);
    }

    @Override
    public String toCSV() {
        return "Dipper," + name + ", " + inputAmount + ", " + outputAmount;
    }
}
