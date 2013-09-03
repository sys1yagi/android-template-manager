/**
 * 
 */
package jp.dip.sys1.yagi.atm.repository;

/**
 * Templates info.
 * 
 * @author yagitoshihiro
 * 
 */
public class Repository {
    private String mRepositoryName = null;
    private String mRepositoryURL = null;
    private String mVersion = null;
    // TODO description?

    public Repository(String repositoryName, String repositoryURL, String version) {
        this.mRepositoryName = repositoryName;
        this.mRepositoryURL = repositoryURL;
        this.mVersion = version;
    }

    public String getRepositoryURL() {
        return mRepositoryURL;
    }

    public String getRepositoryName() {
        return mRepositoryName;
    }

    public String getVersion() {
        return mVersion;
    }

    @Override
    public String toString() {
        return mRepositoryName + "(" + mVersion + ") : " + mRepositoryURL;
    }
}
