package org.uob.a2.engine;

import org.uob.a2.model.*;
import java.nio.file.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import org.uob.a2.engine.Context;
import org.uob.a2.model.ResourceType;
import static java.nio.file.StandardOpenOption.*;
import java.util.Map;
import java.util.EnumMap;
import org.uob.a2.engine.SimulationState;



public class Engine {

    private SimulationState state;
    private int currentTick;

    //constructor
    public Engine(SimulationState state){
        this.state = state;
        this.currentTick = 0;
        state.addResource(ResourceType.CREDITS, 1000);
    }

    public int getCurrentTick(){
        return currentTick;
    }

    public String nextTick(){

        for (Producer p : state.getProducers()) {
            p.tick(new Context(this, state));
          //  state.addResource(ResourceType.CREDITS, 50);
          //  return "Credits gained: 50";
        }

        // Tick all converters
        for (Converter cv : state.getConverters()) {
            cv.tick(new Context(this, state));
          //  state.addResource(ResourceType.CREDITS, 100);
           // return "Credits gained: 100";
        }

        // Tick all consumers
        for (Consumer cs : state.getConsumers()) {
            cs.tick(new Context(this, state));
            //state.addResource(ResourceType.CREDITS, 400);
            //return "Credits gained: 400";
            
        }

        // Update resource history for graphing
        state.updateHistory();

        currentTick++;
        return " ";
    }

    //--SAVE--

    //--SAVE-
    public String save(String filename){
    
    Path file = Paths.get("data", filename); 

    try {
        OutputStream output = new BufferedOutputStream(Files.newOutputStream(file, CREATE, TRUNCATE_EXISTING));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
        // Save current tick
        writer.write("CurrentTick," + currentTick);
        writer.newLine();

        // Save all resources in inventory
        for (Map.Entry<ResourceType, Integer> entry : state.getInventory().entrySet()) {
            writer.write(entry.getKey() + "," + entry.getValue());
            writer.newLine();
        }

        writer.flush();
        return "Saving as... " + filename;

    } catch (IOException e) {
        return "Error saving game";
    }
}

//--LOAD--
    public String load(String filename){
    Path file = Paths.get("data", filename);

    if (!Files.exists(file)) {
        return "File not found: " + filename;
    }

    try  {
        InputStream input = new BufferedInputStream(Files.newInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        // clear current state before loading
        for (ResourceType r : ResourceType.values()) {
            state.updateResource(r, 0);
        }

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            if (parts[0].equals("CurrentTick")) {
                currentTick = Integer.parseInt(parts[1]);
            } else {
                ResourceType res = ResourceType.valueOf(parts[0]);
                int amount = Integer.parseInt(parts[1]);
                state.updateResource(res, amount);
            }
        }

        return "Loading... " + filename;

    } catch (IOException | IllegalArgumentException e) {
        return "Error loading game";
    }
}

    //----INITIAZLIZE DEFAUALTS---
    public void initialiseDefaults() {

     /*   state.addResource(ResourceType.SUGAR, 100);
        state.addResource(ResourceType.COCOA, 50);
        state.addResource(ResourceType.MILK, 50);
        state.addResource(ResourceType.OOMPA_LOOMPA, 1); */

        state.getProducers().add(new SugarCaneProducer("SugarcaneFarm"));
        state.getProducers().add(new CocoaFarmProducer("CocoaFarm"));
        state.getProducers().add(new CowProducer("CowFarm"));
        state.getProducers().add(new Garden("Garden"));
        
        //  converters
        state.getConverters().add(new Dipper("Dipper"));
        state.getConverters().add(new Mixer("Mixer"));
        state.getConverters().add(new Heater("Heater"));
        
        //  consumer
        state.getConsumers().add(new DeliveryTruck("DeliveryTruck"));
    }
}
