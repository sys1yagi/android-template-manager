package jp.dip.sys1.yagi.atm.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.Test;

public class LoaderTest {

    @AfterClass
    public static void cleanup() {
        Loader loader = new Loader();
        loader.setCacheStorePath("test_cache");
        loader.clearCache();
    }

    @Test
    public void testLoadRepositoriesJson() {
        Loader loader = new Loader();
        loader.setCacheStorePath("test_cache");
        assertThat(loader.clearCache(), is(true));

        JSONObject object = loader.loadRepositoriesJson();

        assertThat(object, notNullValue());
    }

    @Test
    public void testSaveCache_param_null() {
        Loader loader = new Loader();
        loader.setCacheStorePath("test_cache");

        assertThat(loader.clearCache(), is(true));

        String dummySHA = "test_sha";
        assertThat(loader.saveCache(dummySHA, null), is(false));
    }

    @Test
    public void testSaveCache() {
        Loader loader = new Loader();
        loader.setCacheStorePath("test_cache");

        assertThat(loader.clearCache(), is(true));

        String dummySHA = "test_sha";
        JSONObject object = new JSONObject("{\"repositories\":[\"https://github.com/sys1yagi/atm_sample\"],\"package_name_map\":{\"atm_sample\":\"ATM Sample Templates\"},\"schema_version\":\"1.0\"}");
        assertThat(loader.saveCache(dummySHA, object), is(true));

        File cache = new File(loader.getCacheStorePath(), dummySHA);
        // System.out.println(cache.getAbsolutePath());
        assertThat(cache.exists(), is(true));
    }

    // hash check
    @Test
    public void testGetSHA_not_null() {
        testSaveCache();
        Loader loader = new Loader();
        loader.setCacheStorePath("test_cache");
        String head = loader.getHeadRepositoriesJsonSHA();
        //System.out.println(head);
        assertThat(head, notNullValue());
    }

}