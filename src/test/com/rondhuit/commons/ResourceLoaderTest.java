/*
 *  Copyright (c) 2012-2014 RONDHUIT Co.,Ltd.
 *  
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
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
