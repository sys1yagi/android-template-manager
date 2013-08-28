/**
 * 
 */
package jp.dip.sys1.yagi.atm.repository;

/**
 * not use yet.
 * @author yagitoshihiro
 * 
 */
public class Repository {
    private String mRepositoryName = null;
    private String mRepositoryURL = null;
    private String mRepositoryCommitsAPI = null;

    public Repository(String repositoryName, String repositoryURL, String repositoryCommitsAPI) {
        this.mRepositoryName = repositoryName;
        this.mRepositoryURL = repositoryURL;
        this.mRepositoryCommitsAPI = repositoryCommitsAPI;
    }

    public String getRepositoryURL() {
        return mRepositoryURL;
    }

    public String getRepositoryName() {
        return mRepositoryName;
    }

    public String getRepositoryCommitsAPI() {
        return mRepositoryCommitsAPI;
    }
}
