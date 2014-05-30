/*
 *  Copyright (c) 2012-2014 RONDHUIT Co.,Ltd.
 *
 */

package com.rondhuit.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class ByteUtilTest {

  @Test
  public void testInt2bytes4() throws Exception {
    byte[] data = new byte[4];
    for(int i = 0; i <= Integer.MAX_VALUE && i >= 0; i += 100){
      ByteUtil.int2bytes4(i, data);
      assertEquals(i, ByteUtil.bytes2int(data));
    }
  }

  @Test
  public void testInt2bytes2() throws Exception {
    byte[] data = new byte[2];
    for(int i = 0; i <= Short.MAX_VALUE; i++){
      ByteUtil.int2bytes2(i, data);
      assertEquals(i, ByteUtil.bytes2int(data));
    }
  }

  @Test
  public void testShort2bytes2() throws Exception {
    byte[] data = new byte[2];
    for(short i = Short.MIN_VALUE; i < Short.MAX_VALUE; i++){
      ByteUtil.short2bytes2(i, data);
      assertEquals(i, ByteUtil.bytes2short(data));
    }
  }

  @Test
  public void testToByte() throws Exception {
    for(int i = 0; i < 128; i++){
      assertEquals((byte)i, ByteUtil.toByte(i));
      assertEquals(i, ByteUtil.toInt(ByteUtil.toByte(i)));
    }
    
    assertFalse(128 == ByteUtil.toByte(128));
  }

  @Test
  public void testToByte2() throws Exception {
    for(int i = 0; i < 256; i++){
      assertEquals(i, ByteUtil.toInt(ByteUtil.toByte(i)));
    }
  }

  @Test
  public void testToShort() throws Exception {
    for(int i = 0; i < 32767; i++){
      assertEquals((short)i, ByteUtil.toShort(i));
      assertEquals(i, ByteUtil.toInt(ByteUtil.toShort(i)));
    }
    
    assertFalse(32768 == ByteUtil.toShort(32768));
  }

  @Test
  public void testToShort2() throws Exception {
    for(int i = 0; i < 65536; i++){
      assertEquals(i, ByteUtil.toInt(ByteUtil.toShort(i)));
    }
  }
  
  static final String STR = "abcdefghijklmnopqrstuvwxyz0123456789あいうえおかきくけこさしすせそたちつてと";
  
  @Test
  public void testToChar() throws Exception {
    byte[] b2 = new byte[2];
    for(int i = 0; i < STR.length(); i++){
      ByteUtil.int2bytes2(ByteUtil.toInt(STR.charAt(i)), b2);
      char c = (char)(ByteUtil.bytes2int(b2) & 0xFFFF);
      assertEquals(c, STR.charAt(i));
    }
  }

  @Test
  public void testByteStr2bytes() throws Exception {
    final String STR = "株式会社ロンウイット";
    final String ENCODING = "UTF-8";
    assertEquals(STR, ByteUtil.toStr1(ByteUtil.byteStr2bytes(STR, ENCODING), ENCODING));
  }

  @Test(expected=IllegalArgumentException.class)
  public void testByteStr2bytesTooLong() throws Exception {
    final String STR = "あいうえおかきくけこさしすせそたちつてと" +
        "あいうえおかきくけこさしすせそたちつてと" +
        "あいうえおかきくけこさしすせそたちつてと" +
        "あいうえおかきくけこさしすせそたちつてと" +
        "あいうえおか";
    final String ENCODING = "UTF-8";
    assertEquals(STR, ByteUtil.toStr1(ByteUtil.byteStr2bytes(STR, ENCODING), ENCODING));
  }

  @Test
  public void testShortStr2bytes() throws Exception {
    final String STR = "株式会社ロンウイット";
    final String ENCODING = "UTF-8";
    assertEquals(STR, ByteUtil.toStr2(ByteUtil.shortStr2bytes(STR, ENCODING), ENCODING));

    final String STR2 = "あいうえおかきくけこさしすせそたちつてと" +
        "あいうえおかきくけこさしすせそたちつてと" +
        "あいうえおかきくけこさしすせそたちつてと" +
        "あいうえおかきくけこさしすせそたちつてと" +
        "あいうえおか";
    assertEquals(STR2, ByteUtil.toStr2(ByteUtil.shortStr2bytes(STR2, ENCODING), ENCODING));
  }

  @Test(expected=IllegalArgumentException.class)
  public void testShortStr2bytesTooLong() throws Exception {
    final String STR = strGenerator("株式会社ロンウイット", 2185);
    final String ENCODING = "UTF-8";
    assertEquals(STR, ByteUtil.toStr2(ByteUtil.shortStr2bytes(STR, ENCODING), ENCODING));
  }
  
  private String strGenerator(String src, int loop){
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < loop; i++){
      sb.append(src);
    }
    return sb.toString();
  }
}
