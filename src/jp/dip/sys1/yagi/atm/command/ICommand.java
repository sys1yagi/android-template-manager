/**
 * 
 */
package jp.dip.sys1.yagi.atm.command;

import java.util.List;


/**
 * @author yagitoshihiro
 *
 */
public interface ICommand {
    public void perform(List<Option> options);
}
