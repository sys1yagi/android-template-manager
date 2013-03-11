/**
 * 
 */
package jp.dip.sys1.yagi.atm;

import jp.dip.sys1.yagi.atm.command.ListCommand;

/**
 * @author yagitoshihiro
 *
 */
public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {
        //atm install
            //-f
            //-sdk_path
        //atm update
        //atm uninstall
        //atm search
            
        //atm list
          
        ListCommand command = new ListCommand();
        command.perform(null);
        
    }    
}
