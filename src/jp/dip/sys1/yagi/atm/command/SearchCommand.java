/**
 * 
 */
package jp.dip.sys1.yagi.atm.command;

import java.util.List;
import java.util.regex.Pattern;

import jp.dip.sys1.yagi.atm.repository.Loader;
import jp.dip.sys1.yagi.atm.repository.Repository;
import jp.dip.sys1.yagi.atm.util.Logger;

/**
 * @author yagitoshihiro
 * 
 */
public class SearchCommand extends Command {
    private final static String TAG = SearchCommand.class.getSimpleName();

    public SearchCommand() {

    }

    @Override
    public void perform() {
        List<Option> options = getOptions();
        if (options == null || options.isEmpty()) {
            Logger.d(TAG, "Usage: atm search keyword");
            return;
        }

        String keyword = options.get(0).getName();
        Pattern pattern = Pattern.compile(keyword);
        Loader loader = new Loader();

        List<Repository> repositories = loader.loadRepositoriesJson();

        for (Repository repo : repositories) {
            if (pattern.matcher(repo.getRepositoryName()).find()) {
                System.out.println(repo);
            }
            System.out.println(repo);
        }
    }
}
