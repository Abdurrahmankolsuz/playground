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

package os;

import java.io.*;
import naksh.*;
import util.*;

// Break ties
import naksh.File;

public class DOS extends OS
{
    public boolean caseSensitive()
    {
        return false;
    }

    public String nakshToOsPath(String naksh_path)
    {
        String os_path = null;
        switch (naksh_path.length())
        {
        case 0:
            Assertion.check(false);
            break;

        case 1:
            os_path = "\\";
            break;

        case 2:
            if (naksh_path.charAt(0) == '/')
                os_path = naksh_path.charAt(1) + ":\\";
            else
                throw new InvalidNakshPathException
                    (naksh_path+" is not a valid naksh path.");
            break;
            
        default:
            if (naksh_path.charAt(0) == '/' && 
                naksh_path.charAt(2) == '/')
                os_path = 
                    naksh_path.charAt(1) + 
                    ":" + 
                    naksh_path.substring(2);
            else
                throw new InvalidNakshPathException
                    (naksh_path+" is not a valid "+
                     "naksh path.");
        }
        os_path = os_path.replace('/', File.separatorChar);
        return os_path;
    }

    public String osToJshellPath(String os_path)
    {
        String naksh_path = null;
        if (os_path.charAt(1) == ':' &&
            os_path.charAt(2) == File.separatorChar)
            naksh_path =
                "/" +
                os_path.charAt(0) +
                os_path.substring(2);
        else
            throw new NakshException(os_path+" is not a valid OS path.");
        naksh_path = naksh_path.replace(File.separatorChar, '/');
        if (naksh_path.endsWith("/") && naksh_path.length() > 1)
            naksh_path = 
                naksh_path.substring(0, naksh_path.length() - 1);
        return naksh_path;
    }

    public String canonicalizePath(String path)
    {
        return Path.simplify(path);
    }

    public naksh.File createFile(String path)
    {
        return new DOSFile(path);
    }

    public naksh.File createFile(String directory, String file_name)
    {
        return new DOSFile(directory, file_name);
    }
}
