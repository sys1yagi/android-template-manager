/**
 * 
 */
package jp.dip.sys1.yagi.atm.command;

import jp.dip.sys1.yagi.atm.repository.Loader;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author yagitoshihiro
 * 
 */
public class ListCommand extends Command {
    //TODO
    //clear
    //cache
    
    
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

    /*
     * (non-Javadoc)
     * 
     * @see jp.dip.sys1.yagi.atm.command.ICommand#perform(java.util.List)
     */
    public void perform() {
        // repositories.jsonを持ってくる
        System.out.println("load template list...");
        Loader loader = new Loader();
        JSONObject json = loader.loadRepositoriesJson();
        //TODO json to class object.
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
            System.out.println(name + "(" + version + ") : " + url );
        }
    }
}
