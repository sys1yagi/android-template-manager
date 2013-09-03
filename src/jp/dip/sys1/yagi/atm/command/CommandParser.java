/**
 * 
 */
package jp.dip.sys1.yagi.atm.command;

import java.util.ArrayList;
import java.util.List;

import jp.dip.sys1.yagi.atm.util.Logger;

/**
 * @author yagitoshihiro
 * 
 */
public class CommandParser {
    private final static String TAG = CommandParser.class.getSimpleName();
    private static CommandParser INTANCE = null;

    public CommandParser() {
    }

    public static CommandParser getInstance() {
        if (INTANCE == null) {
            INTANCE = new CommandParser();
        }
        return INTANCE;
    }

    private Command createCommand(String name) {
        if(name == null){
            return null;
        }
        switch(name){
        case "list":
            return new ListCommand();
        case "search":
            return new SearchCommand();
        case "install":
            return new InstallCommand();
        case "uninstall":
            return new UnInstallCommand();
        
        }
        return null;
    }

    private Option createOption(String[] args, int pos) {
        if(args != null && args.length > pos){
            Option option = new Option();
            if(args[pos].startsWith("-")){
                option.setName(args[pos].replace("-", ""));
                if(args.length > pos + 1){
                    if(!args[pos+1].startsWith("-")){
                        option.setParam(args[pos + 1]);
                    }
                }
            }
            else{
                option.setName(args[pos]);
            }
            return option;
        }
        return null;
    }

    public Command parse(String[] args) {
        if (args == null) {
            Logger.e(TAG, "parse error. args is null.");
            return null;
        } else if (args.length < 1) {
            Logger.e(TAG, "parse error. too few args.");
            return null;
        }
        Command command = createCommand(args[0]);
        if(command == null){
            return null;
        }
        // options
        List<Option> options = new ArrayList<Option>();
        Option option = null;
        int pos = 1;
        while ((option = createOption(args, pos)) != null) {
            options.add(option);
            pos += option.count();
        }
        command.setOptions(options);

        return command;
    }

}
