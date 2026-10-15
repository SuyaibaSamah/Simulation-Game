package org.uob.a2.engine;

import org.uob.a2.model.*;
import org.uob.a2.model.ResourceType;

import java.util.List;
import java.util.*;
import java.util.ArrayList;

import java.util.EnumMap;
import java.util.Map;

public class SimulationState {

    private List<Producer> producers;
    private List<Converter> converters;
    private List<Consumer> consumers;
    private Map<ResourceType, Integer> inventory = new EnumMap<>(ResourceType.class);
    private List<Map<ResourceType, Integer>> resourceHistory = new ArrayList<>();

    public SimulationState() {
        this.producers = new ArrayList<>();
        this.converters = new ArrayList<>();
        this.consumers = new ArrayList<>();
        
        // initialize all resources to 0:
        for (ResourceType type : ResourceType.values()) {
            inventory.put(type, 0);
        }
    }
    public List<Producer> getProducers() {
        return producers;
    }

    public List<Converter> getConverters() {
        return converters;
    }

    public List<Consumer> getConsumers() {
        return consumers;
    }

    // INVENTORY:

    public void updateResource(ResourceType resource, int amount){
        inventory.put(resource, amount);
    }

    public void addResource(ResourceType resource, int amount){
        int current = inventory.getOrDefault(resource, 0);
        inventory.put(resource, current + amount);
    }

    public int getResourceAmount(ResourceType resource){
        return inventory.getOrDefault(resource, 0);
    }

    public boolean removeResource(ResourceType resource, int amount){
        int current = inventory.getOrDefault(resource, 0); // check if we have enough
        if (current < amount){ // if amount we want to subtract is more than what we have
            return false;
        } else {
            inventory.put(resource, current - amount);
            return true;
        }
    }

    // RESOURCE HISTORY:

    // update inventory everytime theres a change:

    public void updateHistory(){
        Map<ResourceType, Integer> updated = new EnumMap<>(ResourceType.class);
        for (Map.Entry<ResourceType, Integer> entry : inventory.entrySet()){
            updated.put(entry.getKey(), entry.getValue());
        }
        resourceHistory.add(updated);

    }
    
    public Map<ResourceType, Integer> getInventory() {
    return inventory;
    }

    public List<Map<ResourceType, Integer>> getHistory(){
        return resourceHistory;
    }

    public List<Integer> getResourceHistory(ResourceType resource) {
        List<Integer> history = new ArrayList<>();
        for (Map<ResourceType, Integer> tick : resourceHistory) {
            history.add(tick.getOrDefault(resource, 0));
        }
    return history;
    }

}