package org.uob.a2.model;

import org.uob.a2.engine.Converter;
import org.uob.a2.engine.Context;
import org.uob.a2.engine.Tickable;
import org.uob.a2.engine.*;

import java.util.EnumMap;
import java.util.Map;

public class Heater extends Converter implements Tickable {

    public Heater(String name) {
        super(name, ResourceType.SUGAR, 10, ResourceType.CANDY, 10);
        addCost(ResourceType.CREDITS, 100);
    }

    /* private static Map<ResourceType, Integer> createInputs() {
        Map<ResourceType, Integer> inputs = new EnumMap<>(ResourceType.class);
        inputs.put(ResourceType.SUGAR, 10);
        return inputs;
    }

    private static Map<ResourceType, Integer> createOutputs() {
        Map<ResourceType, Integer> outputs = new EnumMap<>(ResourceType.class);
        outputs.put(ResourceType.CANDY, 10);
        return outputs;
    } */

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
        return "Heater," + name + ", " + inputAmount + ", " + outputAmount;
    }
}
