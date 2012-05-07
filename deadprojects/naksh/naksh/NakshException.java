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
import java.util.*;

public class NakshException extends RuntimeException
{
    public NakshException(String message)
    {
        super(message);
    }

    public NakshException(Vector exceptions)
    {
        super("Command execution failed.");
        _exceptions = exceptions;
    }

    public void describe(PrintStream out)
    {
        if (_exceptions == null)
            printStackTrace(out);
        else
        {
            for (Enumeration exception_scan = _exceptions.elements();
                 exception_scan.hasMoreElements();)
            {
                Throwable exception = (Throwable) exception_scan.nextElement();
                if (exception instanceof command.exit.ReallyExit)
                {
                    Naksh.okToExit();
                    System.exit(0);
                }
                else
                    exception.printStackTrace(out);
            }
        }
    }

    private Vector _exceptions;
}
