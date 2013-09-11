/**
 * 
 */
package jp.dip.sys1.yagi.atm.command;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import jp.dip.sys1.yagi.atm.util.Environment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author yagitoshihiro
 * 
 */
public class InstallCommandTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        // TODO テストに関わる部分をアンインストールしておく
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testEnvironmentError() {
        Environment env = Environment.getInstance();
        env.setInstallPath(null);
        Command command = new InstallCommand();
        try {
            command.perform();
        } catch (CommandException e) {
            // ok
            return;
        }
        fail("did not throw CommandException.");
    }

    @Test
    public void testTooFewParameterError() {
        Environment env = Environment.getInstance();
        env.setInstallPath("aaa");
        Command command = new InstallCommand();
        try {
            command.perform();
        } catch (CommandException e) {
            // ok
            return;
        }
        fail("did not throw CommandException.");
    }

    @Test
    public void testSearchError() {
        Environment env = Environment.getInstance();
        env.setInstallPath("aaa");
        Command command = new InstallCommand();
        List<Option> options = new ArrayList<Option>();
        Option option = new Option();
        option.setName("not found test");
        options.add(option);
        command.setOptions(options);
        try {
            command.perform();
        } catch (CommandException e) {
            // ok
            return;
        }
        fail("did not throw CommandException.");
    }
    // TODO install success.

}
