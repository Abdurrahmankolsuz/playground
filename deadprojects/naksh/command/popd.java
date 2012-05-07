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

import java.io.*;
import naksh.*;

public class popd extends Command
{
    public void execute(String[] args)
        throws IOException
    {
        String new_dir = pop_dir_stack();
        if (new_dir != null)
            go_to_new_directory(new_dir);
        else
            out().println(property("naksh.dir"));
    }

    public void usage()
    {
        out().println("popd");
        out().println("    Pops the directory stack. The current directory\n"+
                      "    is changed to the directory that is removed from\n"+
                      "    the stack, and the directory stack, stored in\n"+
                      "    naksh.dir, is modified.");
    }

    private String pop_dir_stack()
    {
        String dir_stack = property("naksh.dir_stack");
        String dir = null;
        if (dir_stack != null)
        {
            int colon = dir_stack.indexOf(" ");
            if (colon > 0)
            {
                dir = dir_stack.substring(0, colon);
                dir_stack = dir_stack.substring(colon + 1);
                property("naksh.dir_stack", dir_stack);
            }
            else
            {
                dir = dir_stack;
                removeProperty("naksh.dir_stack");
            }
        }
        return dir;
    }

    private void go_to_new_directory(String new_dir)
        throws IOException
    {
        cd cd_command = new cd();
        cd_command.execute(new String[] { new_dir });
    }
}
