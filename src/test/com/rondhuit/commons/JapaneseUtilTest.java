/*
 *  Copyright (c) 2012-2014 RONDHUIT Co.,Ltd.
 *
 */

package com.rondhuit.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JapaneseUtilTest {

  @Test
  public void testIsAlphabetAlphabet() throws Exception {
    String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz" +
      "ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ" +
      "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ";
    bulkTestForIsAlphabet( str, true );
  }
  
  @Test
  public void testIsAlphabetHiragana() throws Exception {
    String str = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん" +
      "がぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ";
    bulkTestForIsAlphabet( str, false );
  }

  @Test
  public void testIsAlphabetKatakana() throws Exception {
    String str = "アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン" +
      "ガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポ";
    bulkTestForIsAlphabet( str, false );
  }

  @Test
  public void testIsAlphabetKanjiSymbols() throws Exception {
    String str = "々㈠㈡㈢㈣㈤㈥㈦㈧㈨㈩㈪㈫㈬㈭㈮㈯㈰㈱㈲㈳㈴㈵㈶㈷㈸㈹㈺㈻㈼㈽㈾㈿";
    bulkTestForIsAlphabet( str, false );
  }

  @Test
  public void testIsAlphabetKanaSymbols() throws Exception {
    String str = "ゝゞヽヾ";
    bulkTestForIsAlphabet( str, false );
  }
  
  private void bulkTestForIsAlphabet( String str, boolean expected ){
    for( int i = 0; i < str.length(); i++ ){
      assertEquals( "error at " + str.charAt( i ), expected, JapaneseUtil.isAlphabet( str.charAt( i ) ) );
    }
  }
  
  @Test
  public void testIsNumberNumber() throws Exception {
    String str = "0123456789０１２３４５６７８９";
    bulkTestForIsNumber( str, true );
  }
  
  @Test
  public void testIsNumberAlphabet() throws Exception {
    String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz" +
      "ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ" +
      "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ";
    bulkTestForIsNumber( str, false );
  }
  
  @Test
  public void testIsNumberHiragana() throws Exception {
    String str = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん" +
      "がぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ";
    bulkTestForIsNumber( str, false );
  }

  @Test
  public void testIsNumberKatakana() throws Exception {
    String str = "アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン" +
      "ガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポ";
    bulkTestForIsNumber( str, false );
  }

  @Test
  public void testIsNumberKanjiSymbols() throws Exception {
    String str = "々㈠㈡㈢㈣㈤㈥㈦㈧㈨㈩㈪㈫㈬㈭㈮㈯㈰㈱㈲㈳㈴㈵㈶㈷㈸㈹㈺㈻㈼㈽㈾㈿";
    bulkTestForIsNumber( str, false );
  }

  @Test
  public void testIsNumberKanaSymbols() throws Exception {
    String str = "ゝゞヽヾ";
    bulkTestForIsNumber( str, false );
  }
  
  private void bulkTestForIsNumber( String str, boolean expected ){
    for( int i = 0; i < str.length(); i++ ){
      assertEquals( "error at " + str.charAt( i ), expected, JapaneseUtil.isNumber( str.charAt( i ) ) );
    }
  }

  @Test
  public void testIsHiraganaHiragana() throws Exception {
    String str = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん" +
      "がぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ" +
      "ぁぃぅぇぉゃゅょ";
    bulkTestForIsHiragana( str, true );
  }

  @Test
  public void testIsHiraganaKatakana() throws Exception {
    String str = "アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン" +
      "ガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポ" +
      "ァィゥェォャュョ";
    bulkTestForIsHiragana( str, false );
  }
  
  private void bulkTestForIsHiragana( String str, boolean expected ){
    for( int i = 0; i < str.length(); i++ ){
      assertEquals( "error at " + str.charAt( i ), expected, JapaneseUtil.isHiragana( str.charAt( i ) ) );
    }
  }
  
  @Test
  public void testIsHiraganaStr() throws Exception {
    assertFalse( JapaneseUtil.isHiragana( "" ) );
    assertTrue( JapaneseUtil.isHiragana( "ひらがな" ) );
    assertFalse( JapaneseUtil.isHiragana( "ひらがな漢字です" ) );
    assertTrue( JapaneseUtil.isHiragana( "ひらがな漢字です".toCharArray(), 0, 4 ) );
  }

  @Test
  public void testIsDoubleWidthKatakanaHiragana() throws Exception {
    String str = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん" +
      "がぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ" +
      "ぁぃぅぇぉゃゅょ";
    bulkTestForIsDoubleWidthKatakana( str, false );
  }

  @Test
  public void testIsDoubleWidthKatakanaDoubleWidthKatakana() throws Exception {
    String str = "アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン" +
      "ガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポ" +
      "ァィゥェォャュョ";
    bulkTestForIsDoubleWidthKatakana( str, true );
  }

  @Test
  public void testIsDoubleWidthKatakanaHalfWidthKatakana() throws Exception {
    String str = "ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝ" +
      "ｶﾞｷﾞｸﾞｹﾞｺﾞｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟ" +
      "ｧｨｩｪｫｬｭｮ";
    bulkTestForIsDoubleWidthKatakana( str, false );
  }
  
  private void bulkTestForIsDoubleWidthKatakana( String str, boolean expected ){
    for( int i = 0; i < str.length(); i++ ){
      assertEquals( "error at " + str.charAt( i ), expected, JapaneseUtil.isDoubleWidthKatakana( str.charAt( i ) ) );
    }
  }

  @Test
  public void testIsHalfWidthKatakanaHiragana() throws Exception {
    String str = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん" +
      "がぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ" +
      "ぁぃぅぇぉゃゅょ";
    bulkTestForIsHalfWidthKatakana( str, false );
  }

  @Test
  public void testIsHalfWidthKatakanaDoubleWidthKatakana() throws Exception {
    String str = "アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン" +
      "ガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポ" +
      "ァィゥェォャュョ";
    bulkTestForIsHalfWidthKatakana( str, false );
  }

  @Test
  public void testIsHalfWidthKatakanaHalfWidthKatakana() throws Exception {
    String str = "ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝ" +
      "ｶﾞｷﾞｸﾞｹﾞｺﾞｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟ" +
      "ｧｨｩｪｫｬｭｮ";
    bulkTestForIsHalfWidthKatakana( str, true );
  }
  
  private void bulkTestForIsHalfWidthKatakana( String str, boolean expected ){
    for( int i = 0; i < str.length(); i++ ){
      assertEquals( "error at " + str.charAt( i ), expected, JapaneseUtil.isHalfWidthKatakana( str.charAt( i ) ) );
    }
  }

  @Test
  public void testIsKatakanaHiragana() throws Exception {
    String str = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん" +
      "がぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ" +
      "ぁぃぅぇぉゃゅょ";
    bulkTestForIsKatakana( str, false );
  }

  @Test
  public void testIsKatakanaDoubleWidthKatakana() throws Exception {
    String str = "アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン" +
      "ガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポ" +
      "ァィゥェォャュョ";
    bulkTestForIsKatakana( str, true );
  }

  @Test
  public void testIsKatakanaHalfWidthKatakana() throws Exception {
    String str = "ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝ" +
      "ｶﾞｷﾞｸﾞｹﾞｺﾞｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟ" +
      "ｧｨｩｪｫｬｭｮ";
    bulkTestForIsKatakana( str, true );
  }
  
  private void bulkTestForIsKatakana( String str, boolean expected ){
    for( int i = 0; i < str.length(); i++ ){
      assertEquals( "error at " + str.charAt( i ), expected, JapaneseUtil.isKatakana( str.charAt( i ) ) );
    }
  }
  
  @Test
  public void testIsKatakanaStr() throws Exception {
    assertFalse( JapaneseUtil.isKatakana( "" ) );
    assertTrue( JapaneseUtil.isKatakana( "カタカナ" ) );
    assertFalse( JapaneseUtil.isKatakana( "カタカナです" ) );
    assertTrue( JapaneseUtil.isKatakana( "カタカナです".toCharArray(), 0, 4 ) );
  }

  @Test
  public void testProlonged() throws Exception {
    assertTrue( JapaneseUtil.isHiragana( JapaneseUtil.KATAKANA_HIRAGANA_PROLONGED_SOUND_MARK ) );
    assertFalse( JapaneseUtil.isHiragana( JapaneseUtil.KATAKANA_HIRAGANA_PROLONGED_SOUND_MARK, false ) );
    assertTrue( JapaneseUtil.isKatakana( JapaneseUtil.KATAKANA_HIRAGANA_PROLONGED_SOUND_MARK ) );
    assertFalse( JapaneseUtil.isKatakana( JapaneseUtil.KATAKANA_HIRAGANA_PROLONGED_SOUND_MARK, false ) );
    assertTrue( JapaneseUtil.isKatakana( JapaneseUtil.HALF_KATAKANA_PROLONGED_SOUND_MARK ) );
    assertFalse( JapaneseUtil.isKatakana( JapaneseUtil.HALF_KATAKANA_PROLONGED_SOUND_MARK, false ) );
  }
}
