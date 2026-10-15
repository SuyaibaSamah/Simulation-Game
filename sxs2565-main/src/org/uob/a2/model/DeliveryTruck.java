package org.uob.a2.model;
import org.uob.a2.engine.Context;
import org.uob.a2.engine.Consumer;
import org.uob.a2.engine.Tickable;
import org.uob.a2.model.ResourceType;

public class DeliveryTruck extends Consumer implements Tickable {
    private int level;
    
    public DeliveryTruck(String name) {
        super(name, ResourceType.WONKA_BAR, 10);
        this.level = 0;
        addCost(ResourceType.CREDITS, 200);
    }
    
    public int getLevel() {
        return level;
    }
    
    @Override
    public void consume(Context ctx) {
        // Check if we have enough resources 
        if (ctx.state().getResourceAmount(ResourceType.WONKA_BAR) >= 10 &&
            ctx.state().getResourceAmount(ResourceType.CANDY) >= 10 &&
            ctx.state().getResourceAmount(ResourceType.CANDY_APPLE) >= 10) {
            
            //remove the resources then level up
            if (ctx.state().removeResource(ResourceType.WONKA_BAR, 10) &&
                ctx.state().removeResource(ResourceType.CANDY, 10) &&
                ctx.state().removeResource(ResourceType.CANDY_APPLE, 10)) {
                
                level++;
                System.out.println("DeliveryTruck level is now: " + level);
                
            }
        }

        if (ctx.state().getResourceAmount(consumedResource) >= amount) {
            if (ctx.state().removeResource(consumedResource, amount)) {
                System.out.println("DeliveryTruck level is now: " + level);
            }
        }
    }
    
    @Override
    public void tick(Context ctx) {
        consume(ctx);
    }
    
    public String deliver(Context ctx) {
        if (level == 0) {
            return "DeliveryTruck is at level 0. Nothing to deliver!";
        }
        
        int creditsEarned = level * 100;
        ctx.state().addResource(ResourceType.CREDITS, creditsEarned);
        
        int deliveredLevel = level;
        level = 0; // Reset level after delivery
        
        return "Delivery successful! DeliveryTruck leve is now 0. CREDITS Earned: " + creditsEarned;
    }
    
    @Override
    public String toCSV() {
        return "DeliveryTruck," + getName() + "," + level;
    }
}