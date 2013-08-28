/**
 * 
 */
package jp.dip.sys1.yagi.atm;

import jp.dip.sys1.yagi.atm.command.Command;
import jp.dip.sys1.yagi.atm.command.CommandParser;

/**
 * @author yagitoshihiro
 *
 */
public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {

        //atm update


            
        //atm list
          
        CommandParser parser = CommandParser.getInstance();
        Command command = parser.parse(args);
        if(command == null){
            //usage
            System.out.println("Usage:");
            System.out.println("\tatm <command> <options>");
            System.out.println();
            System.out.println("Commands:");
            //TODO command Usage
        }
        else{
            command.perform();
        }
    }    
}
