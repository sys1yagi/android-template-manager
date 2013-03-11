package jp.dip.sys1.yagi.atm.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.hamcrest.core.IsNull;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Test;


public class LoaderTest{

    @After
    public void cleanup(){
        //Loader loader = new Loader();
        //loader.clearCache();
    }
    
    @Test
    public void testLoadRepositoriesJson(){
        Loader loader = new Loader();
        loader.setCacheStorePath("test_cache");
        assertThat(true, is(loader.clearCache()));
        
        JSONObject object = loader.loadRepositoriesJson();
        
        assertThat(true, is(object != null));       
        System.out.println(object);
    }
    
    @Test
    public void testSaveCache_param_null(){
        Loader loader = new Loader();
        loader.setCacheStorePath("test_cache");
        
        assertThat(true, is(loader.clearCache()));
        
        String dummySHA = "test_sha";
        assertThat(false, is(loader.saveCache(dummySHA, null)));
    }
    @Test
    public void testSaveCache(){
        Loader loader = new Loader();
        loader.setCacheStorePath("test_cache");
        
        assertThat(true, is(loader.clearCache()));
        
        String dummySHA = "test_sha";
        JSONObject object = new JSONObject("{\"repositories\":[\"https://github.com/sys1yagi/atm_sample\"],\"package_name_map\":{\"atm_sample\":\"ATM Sample Templates\"},\"schema_version\":\"1.0\"}");
        assertThat(true, is(loader.saveCache(dummySHA, object)));
        
        File cache = new File(loader.getCacheStorePath(), dummySHA);
        System.out.println(cache.getAbsolutePath());
        assertThat(true,is(cache.exists()));
    }
    
    //hash check
    @Test
    public void testGetSHA_null(){
        Loader loader = new Loader();
        loader.setCacheStorePath("test_cache");
        assertThat(true, is(loader.clearCache()));
        String head = loader.getHeadRepositoriesJsonSHA();
        assertThat(true, is(head == null));
    }
    @Test
    public void testGetSHA_not_null(){
        testSaveCache();
        Loader loader = new Loader();
        loader.setCacheStorePath("test_cache");
        String head = loader.getHeadRepositoriesJsonSHA();
        System.out.println(head);
        assertThat(true, is(head != null));
    }
    //getRepositories();
    
    
}