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

import java.util.Properties;

/**
 * プロパティ{@link Properties}から各型のオブジェクト値を取得する等のユーティリティクラス。
 * 
 * @since 0.1
 */
public final class PropertiesUtil {
  
  public static int getIntProperty(Properties props, String key, int defVal){
    String val = props.getProperty(key);
    return val == null ? defVal : Integer.parseInt(val);
  }
  
  public static int getReqIntProperty(Properties props, String key){
    String val = props.getProperty(key);
    if(val == null) throw new IllegalArgumentException(String.format("property \"%s\" must be set", key));
    return Integer.parseInt(val);
  }

  /**
   * 
   * @param props
   * @param required
   * @param key
   * @return integer value of the property
   */
  public static int getIntProperty(Properties props, boolean required, String key){
    String val = props.getProperty(key);
    if(required && val == null) throw new IllegalArgumentException(String.format("property \"%s\" must be set", key));
    return val == null ? 0 : Integer.parseInt(val);
  }

  /**
   * 
   * @param props
   * @param primary
   * @param secondary
   * @return integer value of the property
   */
  public static int getReqIntProperty(Properties props, String primary, String secondary){
    String val = props.getProperty(primary);
    if(val == null){
      val = props.getProperty(secondary);
    }
    if(val == null) throw new IllegalArgumentException(
        String.format("property \"%s\" or \"%s\" must be set", primary, secondary));
    return Integer.parseInt(val);
  }
  
  /**
   * 
   * @param props
   * @param required
   * @param primary
   * @param secondary
   * @return integer value of the property
   */
  public static int getIntProperty(Properties props, boolean required, String primary, String secondary){
    String val = props.getProperty(primary);
    if(val == null){
      val = props.getProperty(secondary);
    }
    if(required && val == null) throw new IllegalArgumentException(
        String.format("property \"%s\" or \"%s\" must be set", primary, secondary));
    return val == null ? 0 : Integer.parseInt(val);
  }
  
  public static float getFloatProperty(Properties props, String key, float defVal){
    String val = props.getProperty(key);
    return val == null ? defVal : Float.parseFloat(val);
  }
  
  public static boolean getBoolProperty(Properties props, String key, boolean defVal){
    String val = props.getProperty(key);
    return val == null ? defVal : Boolean.parseBoolean(val);
  }
  
  public static String getProperty(Properties props, String key, String defVal){
    String val = props.getProperty(key);
    return val == null ? defVal : val;
  }
  
  public static String getReqProperty(Properties props, String key){
    String val = props.getProperty(key);
    if(val == null) throw new IllegalArgumentException(String.format("property \"%s\" must be set", key));
    return val;
  }

  /**
   * 
   * @param props
   * @param required
   * @param key
   * @return String value of the property
   */
  public static String getProperty(Properties props, boolean required, String key){
    String val = props.getProperty(key);
    if(required && val == null) throw new IllegalArgumentException(String.format("property \"%s\" must be set", key));
    return val;
  }
  
  public static String getReqProperty(Properties props, String primary, String secondary){
    String val = props.getProperty(primary);
    if(val == null){
      val = props.getProperty(secondary);
    }
    if(val == null) throw new IllegalArgumentException(
        String.format("property \"%s\" or \"%s\" must be set", primary, secondary));
    return val;
  }

  /**
   * 
   * @param props
   * @param required
   * @param primary
   * @param secondary
   * @return String value of the property
   */
  public static String getProperty(Properties props, boolean required, String primary, String secondary){
    String val = props.getProperty(primary);
    if(val == null){
      val = props.getProperty(secondary);
    }
    if(required && val == null) throw new IllegalArgumentException(
        String.format("property \"%s\" or \"%s\" must be set", primary, secondary));
    return val;
  }
  
  public static String getProperty(Properties props, String key, String defVal, String[] valueSet){
    String val = props.getProperty(key, defVal);
    validateValueForValueSet(key, val, valueSet);
    return val;
  }
  
  public static String getReqProperty(Properties props, String key, String[] valueSet){
    String val = props.getProperty(key);
    if(val == null) throw new IllegalArgumentException(String.format("property \"%s\" must be set", key));
    validateValueForValueSet(key, val, valueSet);
    return val;
  }

  public static void validateValueForValueSet(String key, String value, String[] valueSet){
    for(String validValue : valueSet){
      if(value.equals(validValue)) return;
    }

    // throw an exception
    StringBuilder sb = new StringBuilder();
    for(String validValue : valueSet){
      if(sb.length() > 0) sb.append(',');
      sb.append(validValue);
    }
    throw new IllegalArgumentException(String.format("the property \"%s\" must be one of %s, but you set \"%s\"", key, sb.toString(), value));
  }
}
