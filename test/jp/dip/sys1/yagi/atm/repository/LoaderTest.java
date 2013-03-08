package jp.dip.sys1.yagi.atm.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.json.JSONObject;
import org.junit.Test;


public class LoaderTest{
    
    @Test
    public void testLoadRepositoriesJson(){
        Loader loader = new Loader();
        loader.setCacheStorePath("test_cache");
        
        JSONObject object = loader.loadRepositoriesJson();
        
        assertThat(true, is(object != null));       
        System.out.println(object);
    }
    
}