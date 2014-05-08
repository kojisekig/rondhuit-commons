/*
 *  Copyright (c) 2012-2014 RONDHUIT Co.,Ltd.
 *
 */

package com.rondhuit.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Properties;

import org.junit.Test;

public class PropertiesUtilTest {
  
  @Test(expected=IllegalArgumentException.class)
  public void testGetReqPropertyNull() throws Exception {
    Properties props = new Properties();
    PropertiesUtil.getReqProperty(props, "aKey");
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testGetReqIntPropertyNull() throws Exception {
    Properties props = new Properties();
    PropertiesUtil.getReqIntProperty(props, "aKey");
  }
  
  @Test
  public void testGetRequiredProperty() throws Exception {
    Properties props = new Properties();
    assertNull(PropertiesUtil.getProperty(props, false, "key1"));
    assertNull(PropertiesUtil.getProperty(props, false, "key1", "key2"));

    try{
      PropertiesUtil.getProperty(props, true, "key1");
      fail("IllegalArgumentException must be thrown!");
    }
    catch(IllegalArgumentException expected){}

    try{
      PropertiesUtil.getProperty(props, true, "key1", "key2");
      fail("IllegalArgumentException must be thrown!");
    }
    catch(IllegalArgumentException expected){}
  }
  
  @Test
  public void testGetRequiredIntProperty() throws Exception {
    Properties props = new Properties();
    assertEquals(0, PropertiesUtil.getIntProperty(props, false, "key1"));
    assertEquals(0, PropertiesUtil.getIntProperty(props, false, "key1", "key2"));

    try{
      PropertiesUtil.getIntProperty(props, true, "key1");
      fail("IllegalArgumentException must be thrown!");
    }
    catch(IllegalArgumentException expected){}

    try{
      PropertiesUtil.getIntProperty(props, true, "key1", "key2");
      fail("IllegalArgumentException must be thrown!");
    }
    catch(IllegalArgumentException expected){}
  }
  
  @Test(expected=IllegalArgumentException.class)
  public void testValidateValueForValueSetError() throws Exception {
    PropertiesUtil.validateValueForValueSet("menu", "special", new String[]{"sho", "chiku", "bai"});
  }
  
  @Test
  public void testValidateValueForValueSet() throws Exception {
    PropertiesUtil.validateValueForValueSet("menu", "chiku", new String[]{"sho", "chiku", "bai"});
  }
  
  @Test
  public void testGetPropertyWithValueSet() throws Exception {
    String[] valueSet = new String[]{"sho", "chiku", "bai"};
    Properties props = new Properties();
    assertEquals("bai", PropertiesUtil.getProperty(props, "key1", "bai", valueSet));

    props.setProperty("key2", "special");
    try{
      PropertiesUtil.getProperty(props, "key2", "bai", valueSet);
      fail("IllegalArgumentException must be thrown!");
    }
    catch(IllegalArgumentException expected){}

    props.setProperty("key3", "sho");
    assertEquals("sho", PropertiesUtil.getProperty(props, "key3", "chiku", valueSet));
  }
  
  @Test
  public void testGetRequiredPropertyWithValueSet() throws Exception {
    String[] valueSet = new String[]{"sho", "chiku", "bai"};
    Properties props = new Properties();
    try{
      PropertiesUtil.getReqProperty(props, "key1", valueSet);
      fail("IllegalArgumentException must be thrown!");
    }
    catch(IllegalArgumentException expected){}

    props.setProperty("key2", "special");
    try{
      PropertiesUtil.getReqProperty(props, "key2", valueSet);
      fail("IllegalArgumentException must be thrown!");
    }
    catch(IllegalArgumentException expected){}

    props.setProperty("key3", "sho");
    assertEquals("sho", PropertiesUtil.getReqProperty(props, "key3", valueSet));
  }
}
