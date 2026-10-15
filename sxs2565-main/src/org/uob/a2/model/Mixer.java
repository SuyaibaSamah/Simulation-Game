package org.uob.a2.model;

import org.uob.a2.engine.*;
import org.uob.a2.model.ResourceType;

public class Mixer extends Converter implements Tickable {

    private final int sugarAmount = 10;
    private final int cocoaAmount = 10;
    private final int milkAmount = 10;
    private final int outputAmount = 10;
    boolean enoughResources;

    public Mixer(String name) {
        super(name, ResourceType.SUGAR, 10, ResourceType.WONKA_BAR, 10); 
        addCost(ResourceType.CREDITS, 200);
    }

    @Override
    public void convert(Context ctx) {
        int sugar = ctx.state().getResourceAmount(ResourceType.SUGAR);
        int cocoa = ctx.state().getResourceAmount(ResourceType.COCOA);
        int milk = ctx.state().getResourceAmount(ResourceType.MILK);

         if (sugar < sugarAmount || cocoa < cocoaAmount || milk < milkAmount) {
        ctx.setMessage("You do not have enough resources to mix a Wonka Bar.");
        return;
         }
        
        if (sugar >= sugarAmount && cocoa >= cocoaAmount && milk >= milkAmount) {
            /*boolean removedSugar = ctx.state().removeResource(ResourceType.SUGAR, sugarAmount);
            boolean removedCocoa = ctx.state().removeResource(ResourceType.COCOA, cocoaAmount);
            boolean removedMilk = ctx.state().removeResource(ResourceType.MILK, milkAmount);
            boolean enoughResources = true;*/
            ctx.state().removeResource(ResourceType.SUGAR, sugarAmount);
            ctx.state().removeResource(ResourceType.COCOA, cocoaAmount);
            ctx.state().removeResource(ResourceType.MILK, milkAmount);
            ctx.state().addResource(output, outputAmount);
        }
            
            //only produce if ALL removal done
           /* if (removedSugar == true && removedCocoa==true && removedMilk==true) {
                ctx.state().addResource(output, outputAmount);*/
            } 
            


    @Override
    public void tick(Context ctx) {
        convert(ctx);
    }

    @Override
    public String toCSV() {
        return "Mixer," + name +
               ",Sugar:" + sugarAmount +
               ",Cocoa:" + cocoaAmount +
               ",Milk:" + milkAmount +
               ",Output:" + outputAmount;
    }
}
