/*
 *  Copyright (c) 2014 RONDHUIT Co.,Ltd.
 *
 */

package com.rondhuit.commons;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * 
 * @since 0.1
 */
public final class IOUtils {

  /**
   *
   * @param c
   * @since 0.3
   */
  public static void closeQuietly(Closeable c){
    try{
      if(c != null) c.close();
    }
    catch(IOException ignored){}
  }

  public static void closeQuietly(InputStream is){
    try{
      if(is != null) is.close();
    }
    catch(IOException ignored){}
  }

  public static void closeQuietly(Reader r){
    try{
      if(r != null) r.close();
    }
    catch(IOException ignored){}
  }

  public static void closeQuietly(OutputStream os){
    try{
      if(os != null) os.close();
    }
    catch(IOException ignored){}
  }

  public static void closeQuietly(Writer w){
    try{
      if(w != null) w.close();
    }
    catch(IOException ignored){}
  }
}
