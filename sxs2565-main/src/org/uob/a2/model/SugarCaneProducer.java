package org.uob.a2.engine;
import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;

public class SugarCaneProducer extends Producer implements Tickable {

    public SugarCaneProducer(String name) {
        super(name, ResourceType.SUGAR, 20);
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
        return "SugarCaneFarm, " + name + "," + amount;
    }
}