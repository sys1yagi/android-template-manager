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
import java.util.ArrayList;
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
        this.mCacheStorePath = DEFAULT_CACHE_STORE_PATH;
    }

    /**
     * get head repository sha.
     * 
     * @return
     */
    /* package */String getHeadRepositoriesJsonSHA() {
        String jsonString = loadNetwork(DEFAULT_REPOSITORY_COMMITS_API_URL);
        if (jsonString != null) {
            JSONArray jsonArray = new JSONArray(jsonString);
            if (jsonArray.length() >= 1) {
                return jsonArray.getJSONObject(0).getString("sha");
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

    /* package */JSONObject loadRepositoriesJsonCache(String sha) {
        File dir = getCacheDirs();
        File cacheFile = new File(dir, sha);
        try {
            String jsonString = FileUtils.readFileToString(cacheFile);
            return new JSONObject(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* package */JSONObject loadRepositoriesJsonNetwork(String sha) {
        String jsonString = loadNetwork(DEFAULT_REPOSITORIES_JSON_URL);
        if (jsonString != null) {
            JSONObject json = new JSONObject(jsonString);
            if (!saveCache(sha, json)) {
                Logger.e(TAG, "cache save error...");
            }
            return json;
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
    public JSONObject loadRepositoriesJson() {
        return this.loadRepositoriesJson(false);
    }
    public JSONObject loadRepositoriesJson(boolean isCacheOnly) {
        
        //TODO cache only = true
        
        String headSHA = getHeadRepositoriesJsonSHA();
        if (isModifiedRepositoriesJson(headSHA)) {
            Logger.d(TAG, "load network...");
            return loadRepositoriesJsonNetwork(headSHA);
        } else {
            Logger.d(TAG, "load cache...");
            return loadRepositoriesJsonCache(headSHA);
        }
    }

    public String getCacheStorePath() {
        return this.mCacheStorePath;
    }

    public void setCacheStorePath(String cacheStorePath) {
        this.mCacheStorePath = cacheStorePath;
    }

}
