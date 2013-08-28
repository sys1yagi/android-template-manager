/**
 * 
 */
package jp.dip.sys1.yagi.atm.command;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @author yagitoshihiro
 *
 */
public class CommandParserTest {

    @Test
    public void testGetInstance(){
        assertThat(CommandParser.getInstance(), notNullValue());
    }
    
    @Test
    public void testGetListCommand(){
        CommandParser parser = CommandParser.getInstance();
        assertThat(parser, notNullValue());
        Command command = parser.parse(new String[]{"list"});
        assertThat(command, notNullValue());
    }
    @Test
    public void testGetListCommandAndOptionsInvalid1(){
        CommandParser parser = CommandParser.getInstance();
        assertThat(parser, notNullValue());
        Command command = parser.parse(new String[]{"list", "all"});
        assertThat(command, notNullValue());
        assertThat(command.getOptions(), notNullValue());
        assertThat(command.getOptions().size(), is(1));
    }
    
    //install
    //search
    //uninstall
    //update

}
