package org.uob.a2.parser;

import org.uob.a2.engine.Context;
import org.uob.a2.engine.Consumer;
import org.uob.a2.model.DeliveryTruck;

import java.util.List;

public class DeliverCommand extends Command {

    public DeliverCommand(List<String> words) {
        super(words);
    }

    @Override
    public String execute(Context ctx) {
        List<Consumer> consumers = ctx.state().getConsumers();

        for (Consumer c : consumers) {
            if (c instanceof DeliveryTruck) {
                DeliveryTruck truck = (DeliveryTruck) c;
                return truck.deliver(ctx);
            }
        }

        return "No delivery truck available..";
    }
}
