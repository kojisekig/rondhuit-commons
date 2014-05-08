/*
 *  Copyright (c) 2014 RONDHUIT Co.,Ltd.
 *
 */

package com.rondhuit.commons;

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

  public static void closeQuietly(InputStream is){
    try{
      is.close();
    }
    catch(IOException ignored){}
  }

  public static void closeQuietly(Reader r){
    try{
      r.close();
    }
    catch(IOException ignored){}
  }

  public static void closeQuietly(OutputStream os){
    try{
      os.close();
    }
    catch(IOException ignored){}
  }

  public static void closeQuietly(Writer w){
    try{
      w.close();
    }
    catch(IOException ignored){}
  }
}
