/*
 *  Copyright (c) 2012-2014 RONDHUIT Co.,Ltd.
 *
 */

package com.rondhuit.commons;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TemplateEngineTest{

  @Test
  public void testBasic() throws Exception {
    TemplateEngine te = new TemplateEngine("###");
    te.addLine("0", "Hello, world!");
    te.addLine("start", "###, world!");
    te.addLine("end", "Hello, ###");
    te.addLine("2", "a ### bb ### c");
    te.addLine("connected", "########");
    
    assertEquals("Hello, world!", te.getReplacedLines("0"));
    assertEquals("Hello, world!", te.getReplacedLines("0", "Lucene"));
    assertEquals("Hello, world!", te.getReplacedLines("0", "Lucene", "Solr"));
    
    assertEquals("###, world!", te.getReplacedLines("start"));
    // explicit null replacement
    assertEquals("null, world!", te.getReplacedLines("start", new String[]{null}));
    assertEquals("Lucene, world!", te.getReplacedLines("start", "Lucene"));
    assertEquals("Lucene, world!", te.getReplacedLines("start", "Lucene", "Solr"));
    
    assertEquals("Hello, ###", te.getReplacedLines("end"));
    assertEquals("Hello, Lucene", te.getReplacedLines("end", "Lucene"));
    assertEquals("Hello, Lucene", te.getReplacedLines("end", "Lucene", "Solr"));
    
    assertEquals("a ### bb ### c", te.getReplacedLines("2"));
    assertEquals("a Lucene bb ### c", te.getReplacedLines("2", "Lucene"));
    assertEquals("a Lucene bb Solr c", te.getReplacedLines("2", "Lucene", "Solr"));
    assertEquals("a Lucene bb Solr c", te.getReplacedLines("2", "Lucene", "Solr", "Apache"));
    
    assertEquals("########", te.getReplacedLines("connected"));
    assertEquals("Lucene#####", te.getReplacedLines("connected", "Lucene"));
    assertEquals("LuceneSolr##", te.getReplacedLines("connected", "Lucene", "Solr"));
    assertEquals("LuceneSolr##", te.getReplacedLines("connected", "Lucene", "Solr", "Apache"));

    // replace with short string
    assertEquals("12##", te.getReplacedLines("connected", "1", "2", "3", "4"));
  }

  @Test
  public void testJapanese() throws Exception {
    TemplateEngine te = new TemplateEngine("%%%%%");
    te.addLine("ja", "こんにちは、%%%%%さん。");
    
    assertEquals("こんにちは、後藤田さん。", te.getReplacedLines("ja", "後藤田"));
  }

  @Test
  public void testMultipleLines() throws Exception {
    TemplateEngine te = new TemplateEngine("??");
    te.addLine("ja", "こんにちは、??さん。");
    te.addLine("ja", "あなたは見事第123回迷惑メールでだまされやすい人ナンバーワンに選ばれました！");
    te.addLine("ja", "賞金??円をただちにお受け取りください。");
    te.addLine("ja", "受け取り方法は??をご覧ください。");
    
    assertEquals(
        "こんにちは、田中さん。\n" +
        "あなたは見事第123回迷惑メールでだまされやすい人ナンバーワンに選ばれました！\n" +
        "賞金一億円をただちにお受け取りください。\n" +
        "受け取り方法はhttp://shoukinget.jp/をご覧ください。", te.getReplacedLines("ja", "田中", "一億", "http://shoukinget.jp/"));
  }
}
