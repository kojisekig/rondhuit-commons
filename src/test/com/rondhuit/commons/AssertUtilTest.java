/*
 *  Copyright (c) 2012-2014 RONDHUIT Co.,Ltd.
 *
 */

package com.rondhuit.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Set;

import org.junit.Test;

public class AssertUtilTest {

  @Test
  public void testCsvNull() throws Exception {
    assertNull(AssertUtil.csv(AssertUtil.SEP_COMMA, new String[]{}));
  }

  @Test
  public void testCsv() throws Exception {
    assertEquals("abc,123,4,def", AssertUtil.csv(AssertUtil.SEP_COMMA, "abc", "123", "4", "def"));
  }

  @Test
  public void testSetNull() throws Exception {
    assertNull(AssertUtil.set(new String[]{}));
  }

  @Test
  public void testSet() throws Exception {
    Set<Integer> set1 = AssertUtil.set(1, 2, 3, 10, 100, 1000);
    Set<Integer> set2 = AssertUtil.set(1000, 100, 10, 3, 2, 1);
    AssertUtil.assertCollectionEquals(set1, set2);
  }

  @Test
  public void testAssertCollectionCsvStringEquals() throws Exception {
    AssertUtil.assertCollectionCsvStringEquals(
        AssertUtil.set("RONDHUIT", "ロンウイット", "丸八"), "RONDHUIT/ロンウイット/丸八", AssertUtil.SEP_SLASH);
  }
  
  @Test
  public void testAssertCsvStringCollectionEquals() throws Exception {
    AssertUtil.assertCsvStringCollectionEquals(
        "RONDHUIT/ロンウイット/丸八", AssertUtil.set("RONDHUIT", "ロンウイット", "丸八"), AssertUtil.SEP_SLASH);
  }
  
  @Test
  public void testAssertCsvStringEquals() throws Exception {
    AssertUtil.assertCsvStringEquals(
        "RONDHUIT ロンウイット 丸八", "丸八 RONDHUIT ロンウイット", AssertUtil.SEP_SPACE);
  }
  
  @Test
  public void testAssertCollectionEqualsNull1() throws Exception {
    AssertUtil.assertCollectionEquals(null, null);
  }
  
  @Test(expected=AssertionError.class)
  public void testAssertCollectionEqualsNull2() throws Exception {
    AssertUtil.assertCollectionEquals(null, AssertUtil.set("1"));
  }
  
  @Test(expected=AssertionError.class)
  public void testAssertCollectionEqualsNull3() throws Exception {
    AssertUtil.assertCollectionEquals(AssertUtil.set("1"), null);
  }

  @Test
  public void testAssertCollectionCsvStringPartOf() throws Exception {
    AssertUtil.assertCollectionCsvStringPartOf(
        AssertUtil.set("RONDHUIT", "ロンウイット", "丸八"), "RONDHUIT/ロンウイット/丸八", AssertUtil.SEP_SLASH);
    AssertUtil.assertCollectionCsvStringPartOf(
        AssertUtil.set("RONDHUIT", "ロンウイット", "丸八"), "RONDHUIT/ロンウイット", AssertUtil.SEP_SLASH);
    AssertUtil.assertCollectionCsvStringPartOf(
        AssertUtil.set("RONDHUIT", "ロンウイット", "丸八"), "ロンウイット", AssertUtil.SEP_SLASH);
  }
  
  @Test
  public void testAssertCsvStringCollectionPartOf() throws Exception {
    AssertUtil.assertCsvStringCollectionPartOf(
        "RONDHUIT/ロンウイット/丸八", AssertUtil.set("RONDHUIT", "ロンウイット", "丸八"), AssertUtil.SEP_SLASH);
    AssertUtil.assertCsvStringCollectionPartOf(
        "RONDHUIT/ロンウイット/丸八", AssertUtil.set("RONDHUIT", "ロンウイット"), AssertUtil.SEP_SLASH);
    AssertUtil.assertCsvStringCollectionPartOf(
        "RONDHUIT/ロンウイット/丸八", AssertUtil.set("ロンウイット"), AssertUtil.SEP_SLASH);
  }
  
  @Test
  public void testAssertCsvStringPartOf() throws Exception {
    AssertUtil.assertCsvStringPartOf(
        "RONDHUIT ロンウイット 丸八", "丸八 RONDHUIT ロンウイット", AssertUtil.SEP_SPACE);
    AssertUtil.assertCsvStringPartOf(
        "RONDHUIT ロンウイット 丸八", "丸八 RONDHUIT", AssertUtil.SEP_SPACE);
    AssertUtil.assertCsvStringPartOf(
        "RONDHUIT ロンウイット 丸八", "丸八", AssertUtil.SEP_SPACE);
  }
  
  @Test
  public void testAssertCollectionPartOfNull1() throws Exception {
    AssertUtil.assertCollectionPartOf(null, null);
  }
  
  @Test(expected=AssertionError.class)
  public void testAssertCollectionPartOfNull2() throws Exception {
    AssertUtil.assertCollectionPartOf(null, AssertUtil.set("1"));
  }
  
  @Test
  public void testAssertCollectionPartOfNull3() throws Exception {
    AssertUtil.assertCollectionPartOf(AssertUtil.set("1"), null);
  }

  @Test
  public void testAssertCollectionCsvStringContains() throws Exception {
    AssertUtil.assertCollectionCsvStringContains(
        AssertUtil.set("RONDHUIT", "ロンウイット", "丸八"), "RONDHUIT/ロンウイット/丸八", AssertUtil.SEP_SLASH);
    AssertUtil.assertCollectionCsvStringContains(
        AssertUtil.set("RONDHUIT", "ロンウイット"), "RONDHUIT/ロンウイット/丸八", AssertUtil.SEP_SLASH);
    AssertUtil.assertCollectionCsvStringContains(
        AssertUtil.set("ロンウイット"), "RONDHUIT/ロンウイット/丸八", AssertUtil.SEP_SLASH);
  }
  
  @Test
  public void testAssertCsvStringCollectionContains() throws Exception {
    AssertUtil.assertCsvStringCollectionContains(
        "RONDHUIT/ロンウイット/丸八", AssertUtil.set("RONDHUIT", "ロンウイット", "丸八"), AssertUtil.SEP_SLASH);
    AssertUtil.assertCsvStringCollectionContains(
        "RONDHUIT/ロンウイット", AssertUtil.set("RONDHUIT", "ロンウイット", "丸八"), AssertUtil.SEP_SLASH);
    AssertUtil.assertCsvStringCollectionContains(
        "ロンウイット", AssertUtil.set("RONDHUIT", "ロンウイット", "丸八"), AssertUtil.SEP_SLASH);
  }
  
  @Test
  public void testAssertCsvStringContains() throws Exception {
    AssertUtil.assertCsvStringContains(
        "RONDHUIT ロンウイット 丸八", "丸八 RONDHUIT ロンウイット", AssertUtil.SEP_SPACE);
    AssertUtil.assertCsvStringContains(
        "ロンウイット 丸八", "丸八 RONDHUIT ロンウイット", AssertUtil.SEP_SPACE);
    AssertUtil.assertCsvStringContains(
        "丸八", "丸八 RONDHUIT ロンウイット", AssertUtil.SEP_SPACE);
  }
  
  @Test
  public void testAssertCollectionContainsNull1() throws Exception {
    AssertUtil.assertCollectionContains(null, null);
  }
  
  @Test
  public void testAssertCollectionContainsNull2() throws Exception {
    AssertUtil.assertCollectionContains(null, AssertUtil.set("1"));
  }
  
  @Test(expected=AssertionError.class)
  public void testAssertCollectionContainsNull3() throws Exception {
    AssertUtil.assertCollectionContains(AssertUtil.set("1"), null);
  }
}
