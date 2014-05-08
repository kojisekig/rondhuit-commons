/*
 *  Copyright (c) 2012-2014 RONDHUIT Co.,Ltd.
 *
 */

package com.rondhuit.commons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is not thread safe. Don't use the same instance of this class from multiple thread!
 * 
 * @since 0.1
 */
public class TemplateEngine {

  private final String mark;
  private Map<String, List<String>> templateMap;
  private int offset;
  
  public TemplateEngine(String mark){
    this.mark = mark;
    templateMap = new HashMap<String, List<String>>();
  }
  
  public void addLine(String name, String line){
    List<String> lines = templateMap.get(name);
    if(lines == null){
      lines = new ArrayList<String>();
      templateMap.put(name, lines);
    }
    lines.add(line);
  }
  
  public void addFile(String name, String file) throws IOException {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream is = null;
    InputStreamReader isr = null;
    BufferedReader br = null;
    try{
      is = classLoader.getResourceAsStream(file);
      isr = new InputStreamReader(is, "UTF-8");
      br = new BufferedReader(isr);
      while(true){
        String line = br.readLine();
        if(line == null) break;
        addLine(name, line);
      }
    }
    catch(IOException e){
      throw new RuntimeException(e);
    }
    catch(NullPointerException e){
      throw new RuntimeException("template file \"" + file + "\" is not found in your classpath", e);
    }
    finally{
      IOUtils.closeQuietly(br);
      IOUtils.closeQuietly(isr);
      IOUtils.closeQuietly(is);
    }
  }
  
  private String getLine(String source, String[] repls){
    StringBuilder sb = new StringBuilder();
    int start = 0;
    while(start < source.length()){
      int index = source.indexOf(mark, start);
      if(index < 0){
        sb.append(source.substring(start));
        break;
      }
      sb.append(source.subSequence(start, index));
      if(offset < repls.length)
        sb.append(repls[offset++]);
      else
        sb.append(mark);
      start = index + mark.length();
    }
    
    return sb.toString();
  }
  
  public String getReplacedLines(String name, String... repls){
    List<String> lines = templateMap.get(name);
    if(lines == null) return null;
    offset = 0;
    StringBuilder sb = new StringBuilder();
    int i = 0;
    for(String source : lines){
      if(i++ > 0)
        sb.append('\n');
      sb.append(getLine(source, repls));
    }
    
    return sb.toString();
  }
  
  public Map<String, List<String>> getTemplateMap(){
    return templateMap;
  }
}
