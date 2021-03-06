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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class FileUtilTest {

  @Test
  public void testDeleteRecursively() throws Exception {
    File dirs = new File("a/b/c");
    assertFalse(dirs.exists());
    dirs.mkdirs();
    assertTrue(dirs.exists());
    FileUtil.deleteRecursively("a");
    assertFalse(dirs.exists());
  }
}
