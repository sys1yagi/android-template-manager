/**
 * 
 */
package jp.dip.sys1.yagi.atm.command;

import java.util.List;

/**
 * @author yagitoshihiro
 * 
 */
public abstract class Command {
    private List<Option> mOptions;
    public void setOptions(List<Option> options) {
        mOptions = options;
    }
    public List<Option> getOptions(){
        return mOptions;
    }
    public abstract void perform();
}
