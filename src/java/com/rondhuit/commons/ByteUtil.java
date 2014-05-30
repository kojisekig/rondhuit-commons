/*
 *  Copyright (c) 2012-2014 RONDHUIT Co.,Ltd.
 *
 */

package com.rondhuit.commons;

import java.io.UnsupportedEncodingException;

/**
 * 主にバイナリ辞書読み書き時にintやStringとbyteとの相互変換を行うユーティリティクラス。
 * 
 * @since 0.5
 */
public final class ByteUtil {
  
  /**
   * 正のint整数値を4バイトのbyte配列に変換する。
   * @param src 変換元。
   * @param dest 変換先。
   */
  public static void int2bytes4(int src, byte[] dest){
    int2bytes4(src, dest, 0);
  }

  /**
   * 正のint整数値をoffsetで指定したオフセットからの4バイトのbyte配列に変換する。
   * @param src 変換元。
   * @param dest 変換先。
   * @param offset オフセット。
   * @since 0.6
   */
  public static void int2bytes4(int src, byte[] dest, int offset){
    assert (dest.length - offset) > 3;
    dest[offset + 0] = (byte)((src & 0xFF000000) >>> 24);
    dest[offset + 1] = (byte)((src & 0x00FF0000) >>> 16);
    dest[offset + 2] = (byte)((src & 0x0000FF00) >>> 8);
    dest[offset + 3] = (byte)((src & 0x000000FF));
  }

  /**
   * 正のint整数値を2バイトのbyte配列に変換する。
   * @param src 変換元。
   * @param dest 変換先。
   */
  public static void int2bytes2(int src, byte[] dest){
    int2bytes2(src, dest, 0);
  }

  /**
   * 正のint整数値をoffsetで指定したオフセットからの2バイトのbyte配列に変換する。
   * @param src 変換元。
   * @param dest 変換先。
   * @param offset オフセット。
   * @since 0.6
   */
  public static void int2bytes2(int src, byte[] dest, int offset){
    assert (dest.length - offset) > 1;
    dest[offset + 0] = (byte)((src & 0xFF00) >>> 8);
    dest[offset + 1] = (byte)((src & 0x00FF));
  }

  /**
   * 正の2バイトまたは4バイトのbyte配列をintに変換して返す。
   * @param src 変換元（大きさが2または4の配列）。
   * @return 変換先。
   */
  public static int bytes2int(byte[] src){
    return bytes2int(src, 0, src.length);
  }

  /**
   * 正の2バイトまたは4バイトのbyte配列をintに変換して返す。
   * @param src 変換元。
   * @param offset 変換元のsrcの参照開始オフセット。
   * @param length 有効長（2または4）。
   * @return 変換先。
   */
  public static int bytes2int(byte[] src, int offset, int length){
    if(length == 2){
      int val = src[offset + 0] & 0xFF;
      return (val << 8) + (src[offset + 1] & 0xFF);
    }
    else if(length == 4){
      int val = src[offset + 0] & 0xFF;
      val = (val << 8) + (src[offset + 1] & 0xFF);
      val = (val << 8) + (src[offset + 2] & 0xFF);
      return (val << 8) + (src[offset + 3] & 0xFF);
    }
    else{
      throw new IllegalArgumentException("invalid length of src byte array");
    }
  }

  /**
   * 正負のshort整数値を2バイトのbyte配列に変換する。
   * @param src 変換元。
   * @param dest 変換先。
   */
  public static void short2bytes2(short src, byte[] dest){
    short2bytes2(src, dest, 0);
  }

  /**
   * 正負のshort整数値をoffsetで指定したオフセットからの2バイトのbyte配列に変換する。
   * @param src 変換元。
   * @param dest 変換先。
   * @param offset オフセット。
   * @since 0.6
   */
  public static void short2bytes2(short src, byte[] dest, int offset){
    assert (dest.length - offset) > 1;
    dest[offset + 0] = (byte)((src & 0xFF00) >>> 8);
    dest[offset + 1] = (byte)((src & 0x00FF));
  }

  /**
   * 正負の2バイトのbyte配列をshortに変換して返す。
   * @param src 変換元（大きさが2の配列）。
   * @return 変換先。
   */
  public static short bytes2short(byte[] src){
    assert src.length >= 2;
    short val = (short)(src[0] & 0xFF);
    return (short)((val << 8) | (src[1] & 0xFF));
  }

  /**
   * encoding で指定したエンコーディングイメージの文字列コードが長さ255以内で収まる場合のバイト配列変換。
   * 収まらない場合は{@link IllegalArgumentException}がスローされる。
   * その場合は{@link #shortStr2bytes(String, String)}を代わりに用いる。
   * @param src 変換元文字列。
   * @param encoding 変換文字コード。
   * @return
   */
  public static byte[] byteStr2bytes(String src, String encoding){
    try {
      byte[] image = src.getBytes(encoding);
      if(image.length > 255)
        throw new IllegalArgumentException(String.format("%s byte image of \"%s\" is too long. %d is longer than 255!", encoding, src, image.length));
      byte[] result = new byte[image.length + 1];
      result[0] = ByteUtil.toByte(image.length);
      System.arraycopy(image, 0, result, 1, image.length);
      return result;
    } catch (UnsupportedEncodingException e) {
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * encoding で指定したエンコーディングイメージの文字列コードが長さ65535以内で収まる場合のバイト配列変換。
   * 収まらない場合は{@link IllegalArgumentException}がスローされる。
   * @param src 変換元文字列。
   * @param encoding 変換文字コード。
   * @return
   */
  public static byte[] shortStr2bytes(String src, String encoding){
    try {
      byte[] image = src.getBytes(encoding);
      if(image.length > 65535)
        throw new IllegalArgumentException(String.format("%s byte image of \"%s\" is too long. %d is longer than 65535!", encoding, src, image.length));
      byte[] result = new byte[image.length + 2];
      ByteUtil.short2bytes2(ByteUtil.toShort(image.length), result);
      System.arraycopy(image, 0, result, 2, image.length);
      return result;
    } catch (UnsupportedEncodingException e) {
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * int数値をbyte値に変換して返す。
   * @param src 変換元。
   * @return 変換先。
   */
  public static byte toByte(int src){
    return (byte)(src & 0xFF);
  }

  /**
   * int数値をshort値に変換して返す。
   * @param src 変換元。
   * @return 変換先。
   */
  public static short toShort(int src){
    return (short)(src & 0xFFFF);
  }

  /**
   * int数値をchar値に変換して返す。
   * @param src 変換元。
   * @return 変換先。
   */
  public static char toChar(int src){
    return (char)(src & 0xFFFF);
  }

  /**
   * byte数値をint値に変換して返す。
   * @param src 変換元。
   * @return 変換先。
   */
  public static int toInt(byte src){
    return (int)(src & 0xFF);
  }

  /**
   * 正のshort数値をint値に変換して返す。
   * @param src 変換元。
   * @return 変換先。
   */
  public static int toInt(short src){
    return (int)(src & 0xFFFF);
  }

  /**
   * char数値をint値に変換して返す。
   * @param src 変換元。
   * @return 変換先。
   */
  public static int toInt(char src){
    return (int)(src & 0xFFFF);
  }

  /**
   * 先頭バイトが長さを表すバイト配列から文字列への変換を行う。{@link #byteStr2bytes(String, String)}の逆変換。
   * @param src 先頭バイトが長さを表すバイト配列。
   * @param encoding 文字コード。
   * @return 変換後文字列。
   */
  public static String toStr1(byte[] src, String encoding){
    return toStr1(src, 0, encoding);
  }

  /**
   * offsetからの先頭バイトが長さを表すバイト配列から文字列への変換を行う。{@link #byteStr2bytes(String, String)}の逆変換。
   * @param src offsetからの先頭バイトが長さを表すバイト配列。
   * @param offset
   * @param encoding 文字コード。
   * @return 変換後文字列。
   * @since 0.7
   */
  public static String toStr1(byte[] src, int offset, String encoding){
    try {
      return new String(src, offset + 1, toInt(src[offset]), encoding);
    } catch (UnsupportedEncodingException e) {
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * 先頭2バイトが長さを表すバイト配列から文字列への変換を行う。{@link #shortStr2bytes(String, String)}の逆変換。
   * @param src 先頭2バイトが長さを表すバイト配列。
   * @param encoding 文字コード。
   * @return 変換後文字列。
   */
  public static String toStr2(byte[] src, String encoding){
    return toStr2(src, 0, encoding);
  }

  /**
   * offsetからの先頭2バイトが長さを表すバイト配列から文字列への変換を行う。{@link #shortStr2bytes(String, String)}の逆変換。
   * @param src offsetからの先頭2バイトが長さを表すバイト配列。
   * @param offset
   * @param encoding 文字コード。
   * @return 変換後文字列。
   * @since 0.7
   */
  public static String toStr2(byte[] src, int offset, String encoding){
    try {
      return new String(src, offset + 2, bytes2int(src, offset, 2), encoding);
    } catch (UnsupportedEncodingException e) {
      throw new IllegalArgumentException(e);
    }
  }
}
