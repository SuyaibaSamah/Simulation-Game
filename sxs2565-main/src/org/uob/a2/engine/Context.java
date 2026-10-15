package org.uob.a2.engine;

import org.uob.a2.engine.Engine;
import org.uob.a2.engine.SimulationState;

public class Context {
    private final Engine engine;
    private final SimulationState state;
    private String enoughResourceMessage = "";

    public Context(Engine engine, SimulationState state) {
        this.engine = engine;
        this.state = state;
    }

    public Engine engine() { return engine; }
    public SimulationState state() { return state; }

    public void setMessage(String msg) { 
        this.enoughResourceMessage = msg; 
    }
}
