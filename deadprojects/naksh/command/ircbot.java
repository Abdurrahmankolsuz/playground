/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

/**
 *
 * @author mahmutbulut
 */

import java.io.*;
import java.util.Vector;
import util.*;
import naksh.*;
import numeric.*;
import numeric.uk.ac.port.dsg.numeric.ParsBot;
// import numeric.uk.ac.port.dsg.numeric.ParsBot;

public class ircbot extends Command {
    public void execute(String[] args)
            throws IOException, InterruptedException{
        direct_connect(args);
    }
    
    public void direct_connect(String[] args){
        ParsBot bot = new ParsBot();
    }
    
    
    public void usage()
    {
        out().println("ircbot");
        out().println("    Places numeric bot to the irc channels.\n"+
                      "    - Channels are placed in ParsBot.java file\n" +
                      "    - To connect to custom channels edit ParsBot.java file\n" +
                      "    - addBrain() section.\n" +
                      "    This source code editing is for security reasons.\n");
    }

}
