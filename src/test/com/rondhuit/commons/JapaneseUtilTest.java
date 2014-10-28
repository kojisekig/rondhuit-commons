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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JapaneseUtilTest {

  @Test
  public void testH2KUTF16() throws Exception {
    assertEquals( "ホンジツハセイテンナリ。", JapaneseUtil.transHiraganaDoubleWidthKatakanaUTF16( "ほんじつはせいてんなり。" ) );
    assertEquals( "バイナリファイル", JapaneseUtil.transHiraganaDoubleWidthKatakanaUTF16( "ばいなりファイル" ) );
    assertEquals( "本日ハ晴天ナリ。", JapaneseUtil.transHiraganaDoubleWidthKatakanaUTF16( "本日は晴天なり。" ) );
    assertEquals( "オープン・ソース", JapaneseUtil.transHiraganaDoubleWidthKatakanaUTF16( "おーぷん・そーす" ) );
    assertEquals( "オオプン・ソオス", JapaneseUtil.transHiraganaDoubleWidthKatakanaUTF16( "おおぷん・そおす" ) );
    assertEquals( "全漢字", JapaneseUtil.transHiraganaDoubleWidthKatakanaUTF16( "全漢字" ) );
    assertEquals( "ヴヵヶ", JapaneseUtil.transHiraganaDoubleWidthKatakanaUTF16( "ヴヵヶ" ) );
  }

  @Test
  public void testK2HUTF16() throws Exception {
    assertEquals( "ほんじつはせいてんなり。", JapaneseUtil.transDoubleWidthKatakanaHiraganaUTF16( "ホンジツハセイテンナリ。" ) );
    assertEquals( "ばいなりふぁいる", JapaneseUtil.transDoubleWidthKatakanaHiraganaUTF16( "ばいなりファイル" ) );
    assertEquals( "本日は晴天なり。", JapaneseUtil.transDoubleWidthKatakanaHiraganaUTF16( "本日ハ晴天ナリ。" ) );
    assertEquals( "おーぷん・そーす", JapaneseUtil.transDoubleWidthKatakanaHiraganaUTF16( "オープン・ソース" ) );
    assertEquals( "全漢字", JapaneseUtil.transDoubleWidthKatakanaHiraganaUTF16( "全漢字" ) );
    assertEquals( "ヴヵヶ", JapaneseUtil.transDoubleWidthKatakanaHiraganaUTF16( "ヴヵヶ" ) );
  }

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
  public void testIsHiraganaUTF16Hiragana() throws Exception {
    String str = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん" +
      "がぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ" +
      "ぁぃぅぇぉゃゅょ";
    bulkTestForIsHiraganaUTF16( str, true );
  }

  @Test
  public void testIsHiraganaUTF16Katakana() throws Exception {
    String str = "アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン" +
      "ガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポ" +
      "ァィゥェォャュョ";
    bulkTestForIsHiraganaUTF16( str, false );
  }
  
  private void bulkTestForIsHiraganaUTF16( String str, boolean expected ){
    for( int i = 0; i < str.length(); i++ ){
      assertEquals( "error at " + str.charAt( i ), expected, JapaneseUtil.isHiraganaUTF16( str.charAt( i ) ) );
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
  public void testIsHiraganaIncluded() throws Exception {
    assertFalse( JapaneseUtil.isHiraganaIncluded( "" ) );
    assertTrue( JapaneseUtil.isHiraganaIncluded( "ひらがな" ) );
    final String s = "ひらがな漢字です";
    assertTrue( JapaneseUtil.isHiraganaIncluded( s ) );
    assertTrue( JapaneseUtil.isHiraganaIncluded( s.toCharArray(), 0, s.length() ) );
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
  public void testIsDoubleWidthKatakanaUTF16Hiragana() throws Exception {
    String str = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん" +
      "がぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ" +
      "ぁぃぅぇぉゃゅょ";
    bulkTestForIsDoubleWidthKatakanaUTF16( str, false );
  }

  @Test
  public void testIsDoubleWidthKatakanaUTF16DoubleWidthKatakana() throws Exception {
    String str = "アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン" +
      "ガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポ" +
      "ァィゥェォャュョ" +
      "ヴヵヶ";
    bulkTestForIsDoubleWidthKatakanaUTF16( str, true );
  }

  @Test
  public void testIsDoubleWidthKatakanaUTF16HalfWidthKatakana() throws Exception {
    String str = "ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝ" +
      "ｶﾞｷﾞｸﾞｹﾞｺﾞｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟ" +
      "ｧｨｩｪｫｬｭｮ";
    bulkTestForIsDoubleWidthKatakanaUTF16( str, false );
  }
  
  private void bulkTestForIsDoubleWidthKatakanaUTF16( String str, boolean expected ){
    for( int i = 0; i < str.length(); i++ ){
      assertEquals( "error at " + str.charAt( i ), expected, JapaneseUtil.isDoubleWidthKatakanaUTF16( str.charAt( i ) ) );
    }
  }

  @Test
  public void testIsDoubleWidthKatakanaMappableHiraganaUTF16Hiragana() throws Exception {
    String str = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん" +
      "がぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ" +
      "ぁぃぅぇぉゃゅょ";
    bulkTestForIsDoubleWidthKatakanaMappableHiraganaUTF16( str, false );
  }

  @Test
  public void testIsDoubleWidthKatakanaMappableHiraganaUTF16DoubleWidthKatakanaMappableHiragana() throws Exception {
    String str = "アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン" +
      "ガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポ" +
      "ァィゥェォャュョ";
    bulkTestForIsDoubleWidthKatakanaMappableHiraganaUTF16( str, true );
  }

  @Test
  public void testIsDoubleWidthKatakanaMappableHiraganaUTF16DoubleWidthKatakanaUnMappableHiragana() throws Exception {
    String str = "ヴヵヶ";
    bulkTestForIsDoubleWidthKatakanaMappableHiraganaUTF16( str, false );
  }

  @Test
  public void testIsDoubleWidthKatakanaMappableHiraganaUTF16HalfWidthKatakana() throws Exception {
    String str = "ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝ" +
      "ｶﾞｷﾞｸﾞｹﾞｺﾞｻﾞｼﾞｽﾞｾﾞｿﾞﾀﾞﾁﾞﾂﾞﾃﾞﾄﾞﾊﾞﾋﾞﾌﾞﾍﾞﾎﾞﾊﾟﾋﾟﾌﾟﾍﾟﾎﾟ" +
      "ｧｨｩｪｫｬｭｮ";
    bulkTestForIsDoubleWidthKatakanaMappableHiraganaUTF16( str, false );
  }
  
  private void bulkTestForIsDoubleWidthKatakanaMappableHiraganaUTF16( String str, boolean expected ){
    for( int i = 0; i < str.length(); i++ ){
      assertEquals( "error at " + str.charAt( i ), expected, JapaneseUtil.isDoubleWidthKatakanaMappableHiraganaUTF16( str.charAt( i ) ) );
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
  public void testIsKatakanaIncluded() throws Exception {
    assertFalse( JapaneseUtil.isKatakanaIncluded( "" ) );
    assertTrue( JapaneseUtil.isKatakanaIncluded( "カタカナ" ) );
    final String s = "カタカナです";
    assertTrue( JapaneseUtil.isKatakanaIncluded( s ) );
    assertTrue( JapaneseUtil.isKatakanaIncluded( s.toCharArray(), 0, s.length() ) );
  }
  
  @Test
  public void testIsKanjiHiragana() throws Exception {
    String str = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん" +
      "がぎぐげござじずぜぞだぢづでどばびぶべぼぱぴぷぺぽ";
    bulkTestForIsKanji( str, false );
  }

  @Test
  public void testIsKanjiKatakana() throws Exception {
    String str = "アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン" +
      "ガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポ";
    bulkTestForIsKanji( str, false );
  }

  @Test
  public void testIsKanjiKanjiSymbols() throws Exception {
    String str = "々㈠㈡㈢㈣㈤㈥㈦㈧㈨㈩㈪㈫㈬㈭㈮㈯㈰㈱㈲㈳㈴㈵㈶㈷㈸㈹㈺㈻㈼㈽㈾㈿";
    bulkTestForIsKanji( str, false );
  }

  @Test
  public void testIsKanjiKanaSymbols() throws Exception {
    String str = "ゝゞヽヾ";
    bulkTestForIsKanji( str, false );
  }

  @Test
  public void testIsKanjiKanjiRange() throws Exception {
    for( char c = 0x3400; c < 0x4dc0; c++ )
      assertTrue( "error at " + Integer.toHexString( c ), JapaneseUtil.isKanji( c ) );
    for( char c = 0x4e00; c < 0xa000; c++ )
      assertTrue( "error at " + Integer.toHexString( c ), JapaneseUtil.isKanji( c ) );
  }
  
  private void bulkTestForIsKanji( String str, boolean expected ){
    for( int i = 0; i < str.length(); i++ ){
      assertEquals( "error at " + str.charAt( i ), expected, JapaneseUtil.isKanji( str.charAt( i ) ) );
    }
  }
  
  @Test
  public void testIsKanjiStr() throws Exception {
    assertFalse( JapaneseUtil.isKanji( "" ) );
    assertTrue( JapaneseUtil.isKanji( "漢字" ) );
    assertFalse( JapaneseUtil.isKanji( "漢字です" ) );
  }
  
  @Test
  public void testIsKanjiNumber() throws Exception {
    for( char c : JapaneseUtil.KANJI_NUMBERS ){
      assertTrue( JapaneseUtil.isKanjiNumber( c ) );
    }
    assertFalse( JapaneseUtil.isKanjiNumber( '市' ) );
    assertFalse( JapaneseUtil.isKanjiNumber( '荷' ) );
    assertFalse( JapaneseUtil.isKanjiNumber( '賛' ) );
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
  
  @Test
  public void testTurbidHiragana() throws Exception {
    String source = "あいうえおかきくけこ";
    bulkTestTurbidHiragana( source, "あいうえおがぎぐげご" );
  }
  
  private void bulkTestTurbidHiragana( String source, String expected ){
    for( int i = 0; i < source.length(); i++ ){
      assertEquals( expected.charAt( i ), JapaneseUtil.turbidHiragana( source.charAt( i ) ) );
    }
  }
  
  @Test
  public void testTurbidKatakana() throws Exception {
    String source = "カキクケコサシスセソナニヌネノ";
    bulkTestTurbidKatakana( source, "ガギグゲゴザジズゼゾナニヌネノ" );
  }
  
  private void bulkTestTurbidKatakana( String source, String expected ){
    for( int i = 0; i < source.length(); i++ ){
      assertEquals( expected.charAt( i ), JapaneseUtil.turbidKatakana( source.charAt( i ) ) );
    }
  }
  
  @Test
  public void testTurbidKana() throws Exception {
    String source = "あいうえおカキクケコサシスセソナニヌネノはひふへほ";
    bulkTestTurbidKana( source, "あいうえおガギグゲゴザジズゼゾナニヌネノばびぶべぼ" );
  }
  
  private void bulkTestTurbidKana( String source, String expected ){
    for( int i = 0; i < source.length(); i++ ){
      assertEquals( expected.charAt( i ), JapaneseUtil.turbidKana( source.charAt( i ) ) );
    }
  }
}
