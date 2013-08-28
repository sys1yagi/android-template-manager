/**
 * 
 */
package jp.dip.sys1.yagi.atm.repository;

/**
 * 
 * @author yagitoshihiro
 *
 */
public class Repository {

    private String mRepositoryURL = null;
    private String mRepositoryName = null;
    
    public Repository(String repositoryURL, String repositoryName) {
        this.mRepositoryURL = repositoryURL;
        this.mRepositoryName = repositoryName;
    }

    public String getRepositoryURL() {
        return mRepositoryURL;
    }

    public String getRepositoryName() {
        return mRepositoryName;
    }

}
