/**
 * 
 */
package jp.dip.sys1.yagi.atm.util;

/**
 * @author yagitoshihiro
 *
 */
public final class Environment {
    private final static String ANDROID_HOME = "ANDROID_HOME";
    
    private static Environment INSTANCE = null;
    
    private String mInstallPath;
    
    
    public Environment() {
        mInstallPath = System.getenv(ANDROID_HOME);
    }

    public static synchronized Environment getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Environment();
        }
        return INSTANCE;
    }

    public String getInstallPath() {
        return mInstallPath;
    }

    public void setInstallPath(String installPath) {
        mInstallPath = installPath;
    }
}
