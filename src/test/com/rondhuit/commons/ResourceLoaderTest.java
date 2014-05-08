/*
 *  Copyright (c) 2012-2014 RONDHUIT Co.,Ltd.
 *
 */

package com.rondhuit.commons;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Properties;

import org.junit.Test;

public class ResourceLoaderTest {

  @Test(expected=IllegalArgumentException.class)
  public void testLoadPropertiesNotFound() throws Exception {
    ResourceLoader.loadProperties("not-exist-conf-props");
  }

  @Test
  public void testLoadProperties() throws Exception {
    Properties props = ResourceLoader.loadProperties("ResourceLoaderTest-conf.properties");
    assertEquals(3, props.size());
    assertEquals("v.a", props.getProperty("p.a"));
    assertEquals("v.b", props.getProperty("p.b"));
    assertEquals("v.c", props.getProperty("p.c"));
  }

  @Test(expected=IllegalArgumentException.class)
  public void testLoadStrListNotFound() throws Exception {
    ResourceLoader.loadProperties("not-exist-txt-file");
  }

  @Test
  public void testLoadStrList() throws Exception {
    // reuse the test conf file as a text file
    List<String> strList = ResourceLoader.loadStrList("ResourceLoaderTest-conf.properties");
    assertEquals(3, strList.size());
    assertEquals("p.a=v.a", strList.get(0));
    assertEquals("p.b=v.b", strList.get(1));
    assertEquals("p.c=v.c", strList.get(2));
  }
}
