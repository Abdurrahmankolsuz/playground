/* 
Copyright Paul James Mutton, 2001-2004, http://www.jibble.org/

This file is part of PircBot.

This software is dual-licensed, allowing you to choose between the GNU
General Public License (GPL) and the www.jibble.org Commercial License.
Since the GPL may be too restrictive for use in a proprietary application,
a commercial license is also provided. Full license information can be
found at http://www.jibble.org/licenses/

$Author: pjm2 $
$Id: IrcException.java,v 1.20 2004/02/29 01:20:37 pjm2 Exp $

*/


package numeric.org.jibble.pircbot;

/**
 * An IrcException class.
 *
 * @since   0.9
 * @author  Paul James Mutton,
 *          <a href="http://www.jibble.org/">http://www.jibble.org/</a>
 * @version    1.4.0 (Build time: Thu Mar  4 23:06:30 2004)
 */
public class IrcException extends Exception {
    
    /**
     * Constructs a new IrcException.
     *
     * @param e The error message to report.
     */
    public IrcException(String e) {
        super(e);
    }
    
}