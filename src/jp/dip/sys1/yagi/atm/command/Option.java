/**
 * 
 */
package jp.dip.sys1.yagi.atm.command;

/**
 * @author yagitoshihiro
 *
 */
public class Option {

    private String mName = null;
    private String mParam = null;
    /**
     * 
     */
    public Option(String name, String param) {
        this.mName = name;
        this.mParam = param;
    }
    public String getName() {
        return mName;
    }
    public String getParam() {
        return mParam;
    }

    

}
