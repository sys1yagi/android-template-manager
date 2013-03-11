/**
 * 
 */
package jp.dip.sys1.yagi.atm.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import jp.dip.sys1.yagi.atm.util.Logger;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 
 * @author yagitoshihiro
 */
public class Loader {
    private final static String TAG = Loader.class.getSimpleName();
    private final static String DEFAULT_REPOSITORY_COMMITS_API_URL = "https://api.github.com/repos/sys1yagi/atm-repository/commits";
    private final static String DEFAULT_REPOSITORIES_JSON_URL = "https://raw.github.com/sys1yagi/atm-repository/master/repositories.json";
    private final static String DEFAULT_CACHE_STORE_PATH = "cache";

    private String mCacheStorePath;

    /**
     * 
     */
    public Loader() {
        mCacheStorePath = DEFAULT_CACHE_STORE_PATH;
    }

    /**
     * get head repository sha.
     * 
     * @return
     */
    /* package */String getHeadRepositoriesJsonSHA() {
        String jsonString = loadNetwork(DEFAULT_REPOSITORY_COMMITS_API_URL);
        if(jsonString != null){
            JSONArray jsonArray = new JSONArray(jsonString);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject json = jsonArray.getJSONObject(i);
                System.out.println(json);
            }
            
        }
        return null;
    }

    /**
     * 
     * @param headSHA
     * @return
     */
    /* package */boolean isModifiedRepositoriesJson(String headSHA) {
        if (headSHA == null) {
            return true;
        }
        File dir = getCacheDirs();
        File cacheFile = new File(dir, headSHA);
        return !cacheFile.exists();
    }

    private File getCacheDirs() {
        File dir = new File(mCacheStorePath + "/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    /* package */boolean clearCache() {
        File dir = getCacheDirs();
        if (dir != null && dir.exists()) {
            try {
                FileUtils.deleteDirectory(dir);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /* package */boolean saveCache(String name, JSONObject cacheData) {
        if (name == null || cacheData == null) {
            Logger.d(TAG, "name or cache is null.");
            return false;
        }
        this.clearCache();
        File dir = getCacheDirs();
        File cacheFile = new File(dir, name);

        try {
            FileUtils.write(cacheFile, cacheData.toString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* package */JSONObject loadRepositoriesJsonCache() {

        return null;
    }

    /* package */JSONObject loadRepositoriesJsonNetwork() {
        String jsonString = loadNetwork(DEFAULT_REPOSITORIES_JSON_URL);
        if(jsonString != null){
            return new JSONObject(jsonString);
        }
        return null;
    }

    /* package */String loadNetwork(String url) {
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            HttpsURLConnection con = (HttpsURLConnection) new URL(url).openConnection();
            isr = new InputStreamReader(con.getInputStream());
            br = new BufferedReader(isr);
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 
     * @return
     */
    /* package */JSONObject loadRepositoriesJson() {
        String headSHA = getHeadRepositoriesJsonSHA();
        if (isModifiedRepositoriesJson(headSHA)) {
            return loadRepositoriesJsonNetwork();
        } else {
            return loadRepositoriesJsonCache();
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
