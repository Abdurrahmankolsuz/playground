// Naksh
// Copyright (C) 2011 Mahmut Bulut
// 
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License as
// published by the Free Software Foundation; either version 2 of
// the License, or (at your option) any later version.
// 
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
// 02111-1307, USA.
// 
// Mahmut Bulut  mahmutbulut0@gmail.com

package command;

/**
 *
 * @author mahmutbulut
 */
import java.io.*;
import naksh.*;

//import weka.associations.*;
//import weka.attributeSelection.*;
//import weka.classifiers.*;
//import weka.clusterers.*;
//import weka.core.*;
//import weka.datagenerators.*;
//import weka.estimators.*;
//import weka.experiment.*;
//import weka.filters.*;
//import weka.gui.*;

public class weka extends Command{
    
    public void execute(String[] args)
            throws IOException, InterruptedException
    {
        process_args(args);
        launch();
    }
    
    public void usage()
    {
        out().println("weka [java-heap-size]\n" +
                      "     - Heap size must be integer and can't be\n"+
                      "     - greater than your physical memory\n" +
                      "     Launch weka app from the inside\n" +
                      "     of the class.");
    }
    
    public void process_args(String[] args)
    {
        if(args.length == 1){
         _heap_size = args[0].toString();
        }
        else{
            out().println("Too many arguments detected.");
        }
    }
        
    public void launch(){
        try
        {
        String cmd = "java -Xmx"+_heap_size+"m -jar weka.jar";
        out().println(cmd);
	Runtime run = Runtime.getRuntime();
	Process proc = run.exec(cmd);
        }
       catch(IOException e)
       {
        out().println("Launch failed");
       }
    }
    
    private String _heap_size;
}
