package org.uob.a2.engine;

public interface Tickable {
     void tick(Context ctx);

}

// any class that implements Tickable must have a tick method.