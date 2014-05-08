/*
 *  Copyright (c) 2012-2014 RONDHUIT Co.,Ltd.
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
