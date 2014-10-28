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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * JUnitの各種assertメソッドの拡張。
 * 
 * @since 0.9
 */
public class AssertUtil {
  
  public static final String SEP_COMMA = ",";
  public static final String SEP_SLASH = "/";
  public static final String SEP_SPACE = " ";
  
  public static String csv(String separator, String...elements){
    if(elements.length == 0) return null;

    StringBuilder sb = new StringBuilder(elements[0]);
    for(int i = 1; i < elements.length; i++){
      sb.append(separator).append(elements[i]);
    }
    return sb.toString();
  }
  
  public static <T> Set<T> set(@SuppressWarnings("unchecked") T...elements){
    if(elements.length == 0) return null;
    
    Set<T> set = new HashSet<T>();
    for(T element : elements){
      set.add(element);
    }
    
    return set;
  }
  
  public static void assertCollectionCsvStringEquals(Collection<String> expected, String actualCsv, String separator){
    String[] acts = actualCsv.split(separator);
    assertCollectionEquals(expected, Arrays.asList(acts));
  }
  
  public static void assertCsvStringCollectionEquals(String expectedCsv, Collection<String> actual, String separator){
    String[] exps = expectedCsv.split(separator);
    assertCollectionEquals(Arrays.asList(exps), actual);
  }
  
  public static void assertCsvStringEquals(String expectedCsv, String actualCsv, String separator){
    String[] exps = expectedCsv.split(separator);
    String[] acts = actualCsv.split(separator);
    assertCollectionEquals(Arrays.asList(exps), Arrays.asList(acts));
  }

  public static <T> void assertCollectionEquals(Collection<T> expected, Collection<T> actual){
    if(expected == null){
      if(actual == null) return;
      else{
        throw new AssertionError(String.format("null is expected but actual is not null. actual.size() = %d", actual.size()));
      }
    }
    else{
      if(actual == null)
        throw new AssertionError("There must be some elements in the Collection but actual is null");
    }
    
    if(expected.size() != actual.size())
      throw new AssertionError(String.format("expected.size(%d) doesn't equal actual.size(%d)",
          expected.size(), actual.size()));
    
    for(T exp : expected){
      if(!actual.contains(exp))
        throw new AssertionError(String.format("actual set doesn't contain expected element \"%s\"", exp.toString()));
    }
  }
  
  public static void assertCollectionCsvStringPartOf(Collection<String> expectedSet, String actualPartCsv, String separator){
    String[] acts = actualPartCsv.split(separator);
    assertCollectionPartOf(expectedSet, Arrays.asList(acts));
  }
  
  public static void assertCsvStringCollectionPartOf(String expectedSetCsv, Collection<String> actualPart, String separator){
    String[] exps = expectedSetCsv.split(separator);
    assertCollectionPartOf(Arrays.asList(exps), actualPart);
  }
  
  public static void assertCsvStringPartOf(String expectedSetCsv, String actualPartCsv, String separator){
    String[] exps = expectedSetCsv.split(separator);
    String[] acts = actualPartCsv.split(separator);
    assertCollectionPartOf(Arrays.asList(exps), Arrays.asList(acts));
  }

  public static <T> void assertCollectionPartOf(Collection<T> expectedSet, Collection<T> actualPart){
    if(actualPart == null) return;
    if(expectedSet == null){
      throw new AssertionError(String.format("null is expected but actual is not null. actualPart.size() = %d", actualPart.size()));
    }
    
    for(T actual : actualPart){
      if(!expectedSet.contains(actual))
        throw new AssertionError(String.format("expectedSet doesn't contain actual element \"%s\"", actual.toString()));
    }
  }
  
  public static void assertCollectionCsvStringContains(Collection<String> expectedPart, String actualSetCsv, String separator){
    String[] acts = actualSetCsv.split(separator);
    assertCollectionContains(expectedPart, Arrays.asList(acts));
  }
  
  public static void assertCsvStringCollectionContains(String expectedPartCsv, Collection<String> actualSet, String separator){
    String[] exps = expectedPartCsv.split(separator);
    assertCollectionContains(Arrays.asList(exps), actualSet);
  }
  
  public static void assertCsvStringContains(String expectedPartCsv, String actualSetCsv, String separator){
    String[] exps = expectedPartCsv.split(separator);
    String[] acts = actualSetCsv.split(separator);
    assertCollectionContains(Arrays.asList(exps), Arrays.asList(acts));
  }

  public static <T> void assertCollectionContains(Collection<T> expectedPart, Collection<T> actualSet){
    if(expectedPart == null) return;
    if(actualSet == null){
      throw new AssertionError("actualSet is null and it can't contain expectedPart");
    }
    
    for(T exp : expectedPart){
      if(!actualSet.contains(exp))
        throw new AssertionError(String.format("actualSet doesn't contain expected element \"%s\"", exp.toString()));
    }
  }
}
