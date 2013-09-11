/**
 * 
 */
package jp.dip.sys1.yagi.atm.command;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

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
    public void testGetListCommandAndOptionsValid1(){
        CommandParser parser = CommandParser.getInstance();
        assertThat(parser, notNullValue());
        Command command = parser.parse(new String[]{"list", "all"});
        assertThat(command, notNullValue());
        assertThat(command instanceof ListCommand, is(true));
        assertThat(command.getOptions(), notNullValue());
        assertThat(command.getOptions().size(), is(1));
        
        assertThat(command.getOptions().get(0).getName(), is("all"));
    }
    @Test
    public void testGetListCommandAndOptionsValid2(){
        CommandParser parser = CommandParser.getInstance();
        assertThat(parser, notNullValue());
        Command command = parser.parse(new String[]{"list", "-a", "test"});
        assertThat(command, notNullValue());
        assertThat(command instanceof ListCommand, is(true));
        assertThat(command.getOptions(), notNullValue());
        assertThat(command.getOptions().size(), is(1));
        
        assertThat(command.getOptions().get(0).getName(), is("a"));
        assertThat(command.getOptions().get(0).getParam(), is("test"));
    }
    @Test
    public void testGetListCommandAndOptionsValid3(){
        CommandParser parser = CommandParser.getInstance();
        assertThat(parser, notNullValue());
        Command command = parser.parse(new String[]{"list", "--a", "test2"});
        assertThat(command, notNullValue());
        assertThat(command instanceof ListCommand, is(true));
        assertThat(command.getOptions(), notNullValue());
        assertThat(command.getOptions().size(), is(1));
        
        assertThat(command.getOptions().get(0).getName(), is("a"));
        assertThat(command.getOptions().get(0).getParam(), is("test2"));
    }
    @Test
    public void testSearch(){
        CommandParser parser = CommandParser.getInstance();
        assertThat(parser, notNullValue());
        Command command = parser.parse(new String[]{"search", "sample"});
        assertThat(command, notNullValue());
        assertThat(command instanceof SearchCommand, is(true));
        assertThat(command.getOptions(), notNullValue());
        assertThat(command.getOptions().size(), is(1));
        
        assertThat(command.getOptions().get(0).getName(), is("sample"));
        try{
            command.perform();
        }catch(CommandException e){
            fail(e.getMessage());
        }
    }
    
    //install
    //search
    //uninstall
    //update

}
