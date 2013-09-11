/**
 * 
 */
package jp.dip.sys1.yagi.atm.command;

import java.util.List;
import java.util.regex.Pattern;

import jp.dip.sys1.yagi.atm.repository.Loader;
import jp.dip.sys1.yagi.atm.repository.Repository;
import jp.dip.sys1.yagi.atm.util.Environment;
import jp.dip.sys1.yagi.atm.util.Logger;


/**
 * @author yagitoshihiro
 *
 */
public class InstallCommand extends Command{
    //atm install
    //-f
    //-sdk_path
    
    /**
     * 
     */
    public InstallCommand() {
        
    }

    /* (non-Javadoc)
     * @see jp.dip.sys1.yagi.atm.command.ICommand#perform(java.util.List)
     */
    @Override
    public void perform() throws CommandException{
        Environment env = Environment.getInstance();
        String installPath = env.getInstallPath();
        if(installPath == null){
            throw new CommandException("ANDROID_HOME not found. ex: export ANDROID_HOME=android/home/path");
        }
        //search
        List<Option> options = getOptions();
        if (options == null || options.isEmpty()) {
            throw new CommandException("too few arguments. atm install 'template_id'.");
        }
        String templateId = options.get(0).getName();
        Loader loader = new Loader();
        List<Repository> repositories = loader.loadRepositoriesJson();
        Repository install = null;
        for (Repository repo : repositories) {
            System.out.println(repo.getRepositoryId());
            if (repo.getRepositoryId().matches(templateId)) {
                install = repo;
                break;
            }
        }
        if(install == null){
            throw new CommandException("template not found : " + templateId);
        }
        
        //TODO
    }
}
