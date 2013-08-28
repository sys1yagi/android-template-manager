/**
 * 
 */
package jp.dip.sys1.yagi.atm.util;

/**
 * @author yagitoshihiro
 * 
 */
public class Logger {
    public static void d(String tag, String msg) {
        System.out.println("d:" + tag + ":" + msg);
    }

    public static void e(String tag, String msg) {
        System.out.println("e:" + tag + ":" + msg);
    }
}
