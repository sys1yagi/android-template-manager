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

    public Option() {
    }

    public void setName(String name) {
        mName = name;
    }

    public void setParam(String param) {
        mParam = param;
    }

    public String getName() {
        return mName;
    }

    public String getParam() {
        return mParam;
    }

    public int count() {
        int count = 0;
        if (mName != null) {
            count++;
        }
        if (mParam != null) {
            count++;
        }
        return count;
    }

}
