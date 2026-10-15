package org.uob.a2.parser;

import org.uob.a2.*;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;

import java.util.List;

public class BuildCommand extends Command {

    public BuildCommand(List<String> words) {
        super(words);
    }

    @Override
    public String execute(Context ctx) {
        if (words.size() < 2) {
            return "Please specify an entity to build.";
        }

        String entityType = words.get(1);

        // Define cost for each entity type
        int cost;
        switch (entityType.toLowerCase()) {
            case "sugarcanefarm": cost = 50; break;
            case "cocoafarm": cost = 50; break;
            case "cowfarm": cost = 50; break;
            case "garden": cost = 50; break;
            case "mixer": cost = 100; break;
            case "heater": cost = 100; break;
            case "dipper": cost = 100; break;
            case "deliverytruck": cost = 0; break;
            default:
                return "Unknown Entity: " + entityType;
        }

        // Check if player has enough credits
        int credits = ctx.state().getResourceAmount(ResourceType.CREDITS);
        if (credits < cost) {
            return "Not enough credits to build " + entityType + ". Needed: " + cost + ", Available: " + credits;
        }

        ctx.state().removeResource(ResourceType.CREDITS, cost);

        String baseName = entityType.substring(0, 1).toUpperCase() + entityType.substring(1).toLowerCase();
        int count = 1;
        String uniqueName = baseName + "-" + count;
        boolean exists = true;

        while (exists) {
            exists = false;
            for (Producer p : ctx.state().getProducers()) {
                if (p.getName().equalsIgnoreCase(uniqueName)) {
                    exists = true; break;
                }
            }
            for (Converter cv : ctx.state().getConverters()) {
                if (cv.getName().equalsIgnoreCase(uniqueName)) {
                    exists = true; break;
                }
            }
            for (Consumer cs : ctx.state().getConsumers()) {
                if (cs.getName().equalsIgnoreCase(uniqueName)) {
                    exists = true; break;
                }
            }
            if (exists) {
                count++;
                uniqueName = baseName + "-" + count;
            }
        }
        switch (entityType.toLowerCase()) {
            case "sugarcanefarm":
                ctx.state().getProducers().add(new SugarCaneProducer(uniqueName));
                break;
            case "cocoafarm":
                ctx.state().getProducers().add(new CocoaFarmProducer(uniqueName));
                break;
            case "cowfarm":
                ctx.state().getProducers().add(new CowProducer(uniqueName));
                break;
            case "garden":
                ctx.state().getProducers().add(new Garden(uniqueName));
                break;
            case "mixer":
                ctx.state().getConverters().add(new Mixer(uniqueName));
                break;
            case "heater":
                ctx.state().getConverters().add(new Heater(uniqueName));
                break;
            case "dipper":
                ctx.state().getConverters().add(new Dipper(uniqueName));
                break;
            case "deliverytruck":
                ctx.state().getConsumers().add(new DeliveryTruck(uniqueName));
                break;
        }

        return "Built " + uniqueName + ". Remaining credits: " + ctx.state().getResourceAmount(ResourceType.CREDITS);
    }
}
