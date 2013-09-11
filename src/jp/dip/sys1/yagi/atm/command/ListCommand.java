/**
 * 
 */
package jp.dip.sys1.yagi.atm.command;

import java.util.List;

import jp.dip.sys1.yagi.atm.repository.Loader;
import jp.dip.sys1.yagi.atm.repository.Repository;

/**
 * @author yagitoshihiro
 * 
 */
public class ListCommand extends Command {
    //TODO implement options.
    //clear
    //cache
    public void perform() {
        System.out.println("load template list...");
        Loader loader = new Loader();
        List<Repository> repositories = loader.loadRepositoriesJson();

        for (Repository repo : repositories){
            System.out.println(repo);
        }
    }
}
