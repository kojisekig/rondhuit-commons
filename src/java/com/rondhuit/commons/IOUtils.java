/*
 *  Copyright (c) 2014 RONDHUIT Co.,Ltd.
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
