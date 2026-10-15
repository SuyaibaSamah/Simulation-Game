package org.uob.a2.model;

import org.uob.a2.engine.*;
import org.uob.a2.engine.Producer;
import org.uob.a2.engine.Context;
import org.uob.a2.model.ResourceType;
import java.util.*;

public class CocoaFarmProducer extends Producer implements Tickable {

    public CocoaFarmProducer(String name) {
    super(name, ResourceType.COCOA, 10);
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
        return "Cocoa Farm," + name + "," + amount;
    }
}