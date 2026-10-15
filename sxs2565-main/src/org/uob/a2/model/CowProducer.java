package org.uob.a2.model;

import org.uob.a2.engine.*;
import org.uob.a2.engine.Producer;
import org.uob.a2.engine.Context;
import org.uob.a2.model.ResourceType;
import java.util.*;

public class CowProducer extends Producer implements Tickable {

    public CowProducer(String name) {
        super(name, ResourceType.MILK, 10);
        addCost(ResourceType.CREDITS, 50);
    }

    @Override
    public void tick(Context ctx) {
        produce(ctx);
    }

    @Override
    public void produce(Context ctx) {
        ctx.state().addResource(product, amount);
    }

    @Override
    public String toCSV() {
        return "Cow Farm," + name + "," + amount;
    }
}
