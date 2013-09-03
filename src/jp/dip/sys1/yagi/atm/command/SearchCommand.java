/**
 * 
 */
package jp.dip.sys1.yagi.atm.command;

import java.util.List;
import java.util.regex.Pattern;

import jp.dip.sys1.yagi.atm.repository.Loader;
import jp.dip.sys1.yagi.atm.util.Logger;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * @author yagitoshihiro
 *
 */
public class SearchCommand extends Command {
    private final static String TAG = SearchCommand.class.getSimpleName();
    //TODO refactoring...
    
    private JSONArray getArray(JSONObject json, String key) {
        if (json.has(key)) {
            return json.getJSONArray(key);
        }
        return null;
    }

    private JSONObject getObject(JSONObject json, String key) {
        if (json.has(key)) {
            return json.getJSONObject(key);
        }
        return null;
    }

    private String getString(JSONObject json, String key) {
        if (json.has(key)) {
            return json.getString(key);
        }
        return null;
    }
    
    public SearchCommand() {

    }
    
    @Override
    public void perform() {
        List<Option> options = getOptions();
        if(options == null || options.isEmpty()){
            Logger.d(TAG, "Usage: atm search keyword");
            return;
        }
        
        String keyword = options.get(0).getName();
        Pattern pattern = Pattern.compile(keyword);
        Loader loader = new Loader();
        JSONObject json = loader.loadRepositoriesJson();
        JSONArray repositories = getArray(json, "repositories");
        JSONObject nameMap = getObject(json, "package_name_map");
        if (repositories == null) {
            System.out.println("load error: repositories is null.");
            return;
        }
        if (nameMap == null) {
            System.out.println("load error: nameMap is null.");
            return;
        }

        for (int i = 0; i < repositories.length(); i++) {
            JSONObject repository = repositories.getJSONObject(i);
            String name = getString(repository, "name");
            String url = getString(repository, "url");
            String version = getString(repository, "version");
            if (nameMap.has(name)) {
                name = getString(nameMap, name);
            }
            if(pattern.matcher(name).find()){
                System.out.println(name + "(" + version + ") : " + url );
            }
        }   
    }
}
