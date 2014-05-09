/*
 *  Copyright (c) 2012-2014 RONDHUIT Co.,Ltd.
 *
 */

package com.rondhuit.commons;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * リソースローダーユーティリティクラス。
 * 
 * @since 0.1
 */
public final class ResourceLoader {

  /**
   * クラスパスより指定されたファイル名のプロパティファイルをロードし、{@link Properties}オブジェクトにして返す。
   * 
   * @param confFile ロードするプロパティファイル名。
   * @return ロードしたプロパティ。
   */
  public static Properties loadProperties(String confFile){
    InputStream is = null;
    try{
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      is = classLoader.getResourceAsStream(confFile);
      if(is == null) throw new IllegalArgumentException(confFile + " cannot be found in your classpath!");
      Properties props = new Properties();
      props.load(is);
      return props;
    }
    catch(IOException e){
      throw new RuntimeException(e);
    }
    finally{
      IOUtils.closeQuietly(is);
    }
  }
  
  /**
   * クラスパスより指定されたファイル名のテキストファイルをロードし、{@link String}のリストを返す。エンコーディングはUTF-8のみサポート。
   * "#"で始まる行はコメントとして無視する（コメントとみなすのは"#"が最初にある場合のみ）。
   * 
   * @param file ロードするテキストファイル名。
   * @return 読み込んだテキストファイルの行データ。
   */
  public static List<String> loadStrList(String file){
    InputStream is = null;
    InputStreamReader isr = null;
    BufferedReader br = null;
    try{
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      is = classLoader.getResourceAsStream(file);
      if(is == null) throw new IllegalArgumentException(file + " cannot be found in your classpath!");
      List<String> strList = new ArrayList<String>();
      isr = new InputStreamReader(is, "UTF-8");
      br = new BufferedReader(isr);
      String line = null;
      while((line = br.readLine()) != null){
        String tl = line.trim();
        if(!tl.startsWith("#"))
          strList.add(line.trim());
      }
      return strList;
    }
    catch(IOException e){
      throw new RuntimeException(e);
    }
    finally{
      IOUtils.closeQuietly(br);
      IOUtils.closeQuietly(isr);
      IOUtils.closeQuietly(is);
    }
  }

  /**
   * クラスパスより指定されたファイル名のファイルを{@link InputStream}オブジェクトで返す。
   * 
   * @param file {@link InputStream}でオープンするファイル名。
   * @return オープンしたファイル。
   */
  public static InputStream getFileAsStream(String file){
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream is = classLoader.getResourceAsStream(file);
    if(is == null) throw new IllegalArgumentException(file + " cannot be found in your classpath!");
    return is;
  }

  /**
   * クラスローダーを使用せず、指定されたファイル名（ディレクトリ名を含んでよい）のファイルを読み込み、レコード一覧を返す。
   * @since 0.2
   */
  public static List<String> getStringList(String file, String encoding) throws IOException {
    InputStream is = null;
    InputStreamReader isr = null;
    BufferedReader br = null;
    
    try{
      is = new FileInputStream(file);
      isr = new InputStreamReader(is, encoding);
      br = new BufferedReader(isr);
      
      List<String> resultSet = new ArrayList<String>();
      String line = null;
      while((line = br.readLine()) != null){
        resultSet.add(line.trim());
      }
      return resultSet;
    }
    finally{
      IOUtils.closeQuietly(br);
      IOUtils.closeQuietly(isr);
      IOUtils.closeQuietly(is);
    }
  }
}
