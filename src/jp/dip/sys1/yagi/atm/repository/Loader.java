/**
 * 
 */
package jp.dip.sys1.yagi.atm.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

/**
 * @author yagitoshihiro
 * 
 */
public class Loader {

    private final static String DEFAULT_REPOSITORY_COMMITS_API_URL = "https://api.github.com/repos/sys1yagi/atm-repository/commits";
    private final static String DEFAULT_REPOSITORIES_JSON_URL = "https://raw.github.com/sys1yagi/atm-repository/master/repositories.json";
    private final static String DEFAULT_CACHE_STORE_PATH = "cache";

    private String mCacheStorePath;

    public Loader() {
        mCacheStorePath = DEFAULT_CACHE_STORE_PATH;
    }

    /**
     * check sha.
     * @return
     */
    /* package */boolean isModifiedRepositoriesJson(){
        
        
        return true;
    }
    
    /* package */boolean saveCache(JSONObject cache){
        
        return false;
    }
    /* package */JSONObject loadRepositoriesJsonCache(){
        
        return null;
    }
    /* package */JSONObject loadRepositoriesJsonNetwork(){
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            HttpsURLConnection con = (HttpsURLConnection) new URL(DEFAULT_REPOSITORIES_JSON_URL).openConnection();
            isr = new InputStreamReader(con.getInputStream());
            br = new BufferedReader(isr);
            String line = null;
            StringBuilder sb = new StringBuilder();
            while((line = br.readLine()) != null){
                sb.append(line);
            }
            return new JSONObject(sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(isr != null){ try{isr.close();}catch(Exception e){e.printStackTrace();}}
            if(br != null){ try{br.close();}catch(Exception e){e.printStackTrace();}}
        }
        return null;
    }
    
    /**
     * 
     * @return
     */
    /* package */JSONObject loadRepositoriesJson() {
        if(isModifiedRepositoriesJson()){
            return loadRepositoriesJsonNetwork();
        }
        else{
            return loadRepositoriesJson();
        }
    }

    public List<Repository> getRepositories() {

        // JSONObject object = new JSONObject();

        return null;
    }

    public String getCacheStorePath() {
        return mCacheStorePath;
    }

    public void setCacheStorePath(String cacheStorePath) {
        mCacheStorePath = cacheStorePath;
    }

}
