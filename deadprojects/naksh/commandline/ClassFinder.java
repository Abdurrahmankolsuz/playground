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

package commandline;

import java.util.*;
import util.*;

class ClassFinder
{
    ClassFinder()
    {
        _stable_classes = new Hashtable();
        int i = 0;
        while (i < _stable_class_names.length)
        {
            String command = _stable_class_names[i++];
            String class_name = _stable_class_names[i++];
            try
            {
                Class klass = Class.forName(class_name);
                _stable_classes.put(command, klass);
            }
            catch (ClassNotFoundException e)
            {
                System.err.println("Couldn't find "+class_name);
                Assertion.check(false);
            }
        }
    }

    Class find(String class_name)
        throws ClassNotFoundException
    {
        Class klass = (Class) _stable_classes.get(class_name);
        if (klass == null)
        {
            NakshClassLoader loader = new NakshClassLoader();
            klass = loader.loadClass(class_name);
        }
        return klass;
    }

    private static String[] _stable_class_names =
    {
        // Real names
        "command.cat",         "command.cat",
        "command.cd",          "command.cd",
        "command.cp",          "command.cp",
        "command.dirs",        "command.dirs",
        "command.echo",        "command.echo",
        "command.env",         "command.env",
        "command.exit",        "command.exit",
        "command.gc",          "command.gc",
        "command.help",        "command.help",
        "command.history",     "command.history",
        "command.javac",       "command.javac",
        "command.jobs",        "command.jobs",
        "command.kill",        "command.kill",
        "command.ls",          "command.ls",
        "command.mkdir",       "command.mkdir",
        "command.popd",        "command.popd",
        "command.pushd",       "command.pushd",
        "command.pwd",         "command.pwd",
        "command.rm",          "command.rm",
        "command.save",        "command.save",
        "command.set",         "command.set",
        "command.ircbot",      "command.ircbot",
        "command.weka",        "command.weka",
        
        // Aliases
        "cat",                        "command.cat",
        "cd",                         "command.cd",
        "cp",                         "command.cp",
        "dirs",                       "command.dirs",
        "echo",                       "command.echo",
        "env",                        "command.env",
        "exit",                       "command.exit",
        "gc",                         "command.gc",
        "help",                       "command.help",
        "history",                    "command.history",
        "javac",                      "command.javac",
        "jobs",                       "command.jobs",
        "kill",                       "command.kill",
        "ls",                         "command.ls",
        "mkdir",                      "command.mkdir",
        "popd",                       "command.popd",
        "pushd",                      "command.pushd",
        "pwd",                        "command.pwd",
        "quit",                       "command.exit",
        "rm",                         "command.rm",
        "save",                       "command.save",
        "set",                        "command.set",
        "ircbot",                     "command.ircbot",
        "weka",                       "command.weka",
    };

    private Hashtable _stable_classes;
}
