// take user input --> sends it to parser --> parser turns it to executable command --> context executes it by sending the command over to engine or simustate --> loop until game ends ??

package org.uob.a2;
import org.uob.a2.engine.*;
import org.uob.a2.parser.*;
import org.uob.a2.model.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        
        SimulationState state = new SimulationState();
        Engine engine = new Engine(state);
        Context ctx = new Context(engine, state);
       // engine.initialiseDefaults();
        Parser parser = new Parser();
        
        //scanner
        Scanner scanner = new Scanner(System.in);
        boolean playing = true;
        while(playing){
            System.out.print("[" + engine.getCurrentTick() + "] Enter Command >> ");
            String input = scanner.nextLine().toLowerCase().trim(); 
            System.out.print("\n");
            
            if (input.isEmpty()){
                System.out.println(engine.nextTick());
                continue;
            }

            //execute the command after parsing
            Command cmd = parser.parse(input); //loops thorugh everything in the parse method in parser

            String output = cmd.execute(ctx);
            if (output != null && !output.isEmpty()){
                System.out.println(output);
            }

            if(input.equals("quit")){
                playing = false;
                continue;
            }
        }
        scanner.close();
    }
}
