package org.uob.a2.parser;
import org.uob.a2.engine.*;
import org.uob.a2.model.*;
import java.util.List;

public class InfoCommand extends Command {
    public InfoCommand(List<String> words) {
        super(words);
    }

    @Override
    public String execute(Context ctx) {
        if (words.size() < 2) {
            return "Info for what? Use: info resources OR info entities";
        }
        
        String type = words.get(1).toLowerCase();
        StringBuilder output = new StringBuilder();
        SimulationState state = ctx.state();
        
        switch (type) {
            case "resources":
            case "resource":
                for (ResourceType resource : ResourceType.values()) {
                    int amount = state.getResourceAmount(resource);
                    output.append(resource.name())
                          .append(": ")
                          .append(amount)
                          .append("\n");
                }
                break;
                
            case "entities":
            case "entity":
                for (Producer p : state.getProducers()) {
                    output.append(p.getName())
                          .append("[")
                          .append(p.getProduct().name())
                          .append(" x")
                          .append(p.getAmount())
                          .append("]\n");
                }
                
                for (Converter c : state.getConverters()) {
                    String name = c.getName();
                    
                    if (name.toLowerCase().contains("mixer")) {
                        output.append(name)
                              .append("[SUGAR x10 + COCOA x10 + MILK x10 → WONKA_BAR x1]\n");
                    } else if (name.toLowerCase().contains("heater")) {
                        output.append(name)
                              .append("[SUGAR x10 → CANDY x10]\n");
                    } else if (name.toLowerCase().contains("dipper")) {
                        output.append(name)
                              .append("[CANDY x10 + APPLE x10 → CANDY_APPLE x10]\n");
                    }
                }
                
                for (Consumer cs : state.getConsumers()) {
                    if (cs instanceof DeliveryTruck) {
                        DeliveryTruck truck = (DeliveryTruck) cs;
                        output.append(truck.getName())
                              .append("[WONKA_BAR x10 + CANDY x10 + CANDY_APPLE x10 to level up] ")
                              .append("--Current level: ")
                              .append(truck.getLevel())
                              .append("--\n");
                    }
                }
                break;
                
            default:
                return "Invalid info type: " + type;
        }
        
        return output.toString();
    }
}