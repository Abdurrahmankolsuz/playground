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

package naksh;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import commandline.*;
import util.*;
import java_cup.runtime.*;

public class Naksh
{
    public static final String NAKSH_BUFFER         = "naksh.buffer";
    public static final String NAKSH_PAGE           = "naksh.page";
    public static final String NAKSH_PROMPT         = "naksh.prompt";
    public static final String NAKSH_COLUMNS        = "naksh.columns";
    public static final String NAKSH_LINES          = "naksh.lines";
    public static final String NAKSH_TIME_COMMANDS  = "naksh.time_commands";
    public static final String NAKSH_DIR            = "naksh.dir";
    public static final String NAKSH_HISTORY_SIZE   = "naksh.history_size";
    public static final String NAKSH_FLAG           = "naksh.flag";

    
    // Interface for interactive usage

    public static void main(String[] args)
        throws Exception
    {
        new Naksh(true /* interactive */);
    }


    // Interface for programmatic usage

    public static Naksh create()
        throws Exception
    {
        return new Naksh(false /* not interactive */);
    }

    public void runCommand(String command)
        throws Exception
    {
        process_command_line(command);
    }


    // For use by Naksh

    public static OS os()
    {
        return _os;
    }

    public static void okToExit()
    {
        _security_manager.okToExit();
    }


    // For use by this class

    private Naksh(boolean interactive)
        throws Exception
    {
        initialize(interactive);
        // The command command.exit calls System.exit.
        if (_interactive)
            while (true)
                process_command_line();
    }

    private void initialize(boolean interactive)
        throws Exception
    {
        _security_manager = new NakshSecurityManager();
        System.setSecurityManager(_security_manager);

        _interactive = interactive;

        // Set up object for dealing with platform dependencies.
        _os = OS.create();

        // Set up shell input
        _shell_input = new BufferedReader
            (new InputStreamReader(System.in));

        // Set up environment
        initialize_environment();
    }

    private void initialize_environment()
    {
        Util.systemProperty(NAKSH_PROMPT, ">+>");
        Util.systemProperty(NAKSH_PAGE, "true");
        Util.systemProperty(NAKSH_COLUMNS, "60");
        Util.systemProperty(NAKSH_LINES, "19");
        Util.systemProperty(NAKSH_BUFFER, "false");
        Util.systemProperty(NAKSH_FLAG, ":");
        Util.systemProperty
            (NAKSH_DIR,
             Path.osToJshell(System.getProperty("user.dir")));
        Util.systemProperty(NAKSH_HISTORY_SIZE, "100");
    }
        
    private void process_command_line()
        throws Exception
    {
        String text = read_command_line();
        try
        { process_command_line(text); }
        catch (Exception e)
        { System.err.println("Illegal syntax."); }
    }

    private void process_command_line(String command_line)
        throws Exception
    {
        JobThread job_thread = null;
        job_thread = JobThread.create(command_line);
        job_thread.startWork();
        job_thread.waitIfForegroundJob();
    }

    private String read_command_line()
        throws IOException
    {
        String line = null;
        do
        {
            System.out.print
                ((History.only().lastCommandId() + 1)+
                 System.getProperty("naksh.prompt")+" ");
            System.out.flush();
            line = readLine();
        }
        while (line == null || line.trim().length() == 0);
        // Trim to get rid of cr and or lf at end of line.
        return line.trim();
    }

    // Needed because BufferedReader.readLine doesn't work
    // on symbian os...
    public String readLine() 
        throws java.io.IOException
    {
        StringBuffer s = new StringBuffer();
        char c;
        while ((c = (char)System.in.read())!= '\n')
        {
            if (c == '\b' && s.length() > 0)
                s.setLength(s.length() - 1);
            else
                s.append(c);
        }
        return s.toString();
    }

    private static OS _os;
    private static NakshSecurityManager _security_manager;

    private BufferedReader _shell_input;
    private boolean _interactive;
}
