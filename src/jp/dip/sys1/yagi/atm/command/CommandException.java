/**
 * 
 */
package jp.dip.sys1.yagi.atm.command;

/**
 * @author yagitoshihiro
 * 
 */
public class CommandException extends Exception {

    /**
     * 
     */
    public CommandException() {

    }

    /**
     * @param arg0
     */
    public CommandException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     */
    public CommandException(Throwable arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public CommandException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

}
