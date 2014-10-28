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

import java.lang.Character.UnicodeBlock;

/**
 * 日本語処理共通ユーティリティクラス。
 * @since 0.1
 */
public class JapaneseUtil {

  /**
   * 濁点
   */
  public static final char COMBINING_KATAKANA_HIRAGANA_VOICED_SOUND_MARK = '\u3099';
  
  /**
   * 半濁点
   */
  public static final char COMBINING_KATAKANA_HIRAGANA_SEMI_VOICED_SOUND_MARK = '\u309A';
  
  /**
   * 濁点
   */
  public static final char KATAKANA_HIRAGANA_VOICED_SOUND_MARK = '\u309B';
  
  /**
   * 半濁点
   */
  public static final char KATAKANA_HIRAGANA_SEMI_VOICED_SOUND_MARK = '\u309C';
  
  /**
   * 全角長音記号
   */
  public static final char KATAKANA_HIRAGANA_PROLONGED_SOUND_MARK = '\u30FC';
  
  /**
   * 半角長音記号
   */
  public static final char HALF_KATAKANA_PROLONGED_SOUND_MARK = '\uFF70';

  /**
   * 全角中黒
   */
  public static final char NAKAGURO_MARK = '\u30FB';

  /**
   * 半角中黒
   */
  public static final char HALF_NAKAGURO_MARK = '\uFF65';

  /**
   * 文字が中黒か否かを判定する。
   * @param c 判定対象の文字
   * @return 判定結果。文字が中黒のときはtrue
   */
  public static boolean isNakaguro(char c){
    return c == NAKAGURO_MARK || c == HALF_NAKAGURO_MARK;
  }
  
  /**
   * 文字列がすべて算用数字か算用数字でないかを判定する。算用数字は半角の'0'-'9'と 全角の'０'-'９'を含む。
   * @param str 判定対象の文字列
   * @return 判定結果。すべて算用数字の時はtrue
   */
  public static boolean isNumber( String str ){
    return isNumber( str.toCharArray() );
  }

  /**
   * 文字列がすべて算用数字か算用数字でないかを判定する。算用数字は半角の'0'-'9'と 全角の'０'-'９'を含む。
   * @param str 判定対象の文字列
   * @return 判定結果。すべて算用数字の時はtrue
   */
  public static boolean isNumber( char[] str ){
    if( str.length == 0 ) return false;
    for( char c : str ){
      if( !isNumber( c ) ) return false;
    }
    return true;
  }

  /**
   * 文字列がすべて算用数字か算用数字でないかを判定する。算用数字は半角の'0'-'9'と 全角の'０'-'９'を含む。
   * @param str 判定対象の文字列
   * @param start 判定対象の文字列の開始位置
   * @param len 判定対象の文字列の長さ
   * @return 判定結果。すべて算用数字の時はtrue
   */
  public static boolean isNumber( char[] str, int start, int len ){
    if( str.length < start + len ) return false;
    for( int i = 0; i < len; i++ ){
      char c = str[start + i];
      if( !isNumber( c ) ) return false;
    }
    return true;
  }
  
  /**
   * 文字が算用数字か算用数字でないかを判定する。算用数字は半角の'0'-'9'と 全角の'０'-'９'を含む。
   * @param c 判定対象の文字
   * @return 判定結果。算用数字の時はtrue
   */
  public static boolean isNumber( char c ){
    return
    ( '\u0030' <= c && c <= '\u0039' ) || // 0 - 9
    ( '\uff10' <= c && c <= '\uff19' ); // ａ - ｚ
  }

  /**
   * 文字が算用数字か算用数字でないかを判定する。算用数字は半角の'0'-'9'と 全角の'０'-'９'を含む。
   * @param c 判定対象の文字
   * @return 判定結果。算用数字の時はtrue
   */
  public static boolean isNumber( int c ){
    return isNumber( (char)c );
  }

  public static final char[] KANJI_NUMBERS = {
    '〇', '一', '二', '三', '四', '五', '六', '七', '八', '九', '十', '百', '千', '万', '億', '兆', '京',
    '零', '壱', '弐', '参', '拾', '萬'
  };
  
  /**
   * 文字が漢数字か否かを判定する。
   * @param c 判定対象の文字
   * @return 判定結果。漢数字の時はtrue
   */
  public static boolean isKanjiNumber( char c ){
    for( char k : KANJI_NUMBERS ){
      if( k == c ) return true;
    }
    return false;
  }
  
  /**
   * 文字が漢数字か否かを判定する。
   * @param c 判定対象の文字
   * @return 判定結果。漢数字の時はtrue
   */
  public static boolean isKanjiNumber( int c ){
    return isKanjiNumber( (char)c );
  }
  
  /**
   * 文字列がすべてアルファベットか否かを判定する。
   * @param str 判定対象の文字列
   * @return 判定結果。すべてアルファベットの時はtrue
   */
  public static boolean isAlphabet( String str ){
    return isAlphabet( str.toCharArray() );
  }

  /**
   * 文字列がすべてアルファベットか否かを判定する。
   * @param str 判定対象の文字列
   * @return 判定結果。すべてアルファベットの時はtrue
   */
  public static boolean isAlphabet( char[] str ){
    if( str.length == 0 ) return false;
    for( char c : str ){
      if( !isAlphabet( c ) ) return false;
    }
    return true;
  }

  /**
   * 文字列がすべてアルファベットか否かを判定する。
   * @param str 判定対象の文字列
   * @param start 判定対象の文字列の開始位置
   * @param len 判定対象の文字列の長さ
   * @return 判定結果。すべてアルファベットの時はtrue
   */
  public static boolean isAlphabet( char[] str, int start, int len ){
    if( str.length < start + len ) return false;
    for( int i = 0; i < len; i++ ){
      char c = str[start + i];
      if( !isAlphabet( c ) ) return false;
    }
    return true;
  }
  
  /**
   * 文字がアルファベットかアルファベットでないかを判定する。
   * なお、英語文字のアルファベット以外の文字については判定はあまり正確ではないため、
   * 今後アルファベットと認定される文字コード範囲の再考が起こりうることに注意。
   * @param c 判定対象の文字
   * @return 判定結果。アルファベットの時はtrue
   */
  public static boolean isAlphabet( char c ){
    return
    ( '\u0041' <= c && c <= '\u005a' ) || // A - Z
    ( '\u0061' <= c && c <= '\u007a' ) || // a - z
    ( '\u00c0' <= c && c <= '\u00d6' ) || // À - Ö
    ( '\u00d8' <= c && c <= '\u00df' ) || // Ø - ß
    ( '\u00e0' <= c && c <= '\u00f6' ) || // à - ö
    ( '\u00f8' <= c && c <= '\u00ff' ) || // ø - ÿ
    ( '\u0100' <= c && c <= '\u02b8' ) || // Ā - ʸ
    ( '\u0372' <= c && c <= '\u051f' ) || // Ͳ - ԟ
    ( '\u1e00' <= c && c <= '\u1ffc' ) || // Ḁ - ῼ
    ( '\uff21' <= c && c <= '\uff3a' ) || // Ａ - Ｚ
    ( '\uff41' <= c && c <= '\uff5a' ); // ａ - ｚ
  }

  /**
   * 文字がアルファベットかアルファベットでないかを判定する。
   * @param c 判定対象の文字
   * @return 判定結果。アルファベットの時はtrue
   */
  public static boolean isAlphabet( int c ){
    return isAlphabet( (char)c );
  }
  
  /**
   * 文字列がすべてひらがなか否かを判定する。
   * @param str 判定対象の文字列
   * @return 判定結果。すべてひらがなのときはtrue
   */
  public static boolean isHiragana( String str ){
    return isHiragana( str.toCharArray() );
  }

  /**
   * 文字列がすべてひらがなか否かを判定する。
   * @param str 判定対象の文字列
   * @return 判定結果。すべてひらがなのときはtrue
   */
  public static boolean isHiragana( char[] str ){
    if( str.length == 0 ) return false;
    for( char c : str ){
      if( !isHiragana( c ) ) return false;
    }
    return true;
  }
  
  /**
   * 文字列がすべてひらがなか否かを判定する。
   * @param str 判定対象の文字列
   * @param start チェックする開始位置
   * @param len チェックする長さ
   * @return 判定結果。すべてひらがなのときはtrue
   */
  public static boolean isHiragana( char[] str, int start, int len ){
    if( str.length < start + len ) return false;
    for( int i = 0; i < len; i++ ){
      char c = str[start + i];
      if( !isHiragana( c ) ) return false;
    }
    return true;
  }
  
  /**
   * 文字がひらがなかひらがなでないかを判定する。
   * @param c 判定対象の文字
   * @return 判定結果。ひらがなのときはtrue
   */
  public static boolean isHiragana( char c ){
    return isHiragana( c, true );
  }
  
  /**
   * 文字がひらがなかひらがなでないかを判定する。
   * @param c 判定対象の文字
   * @param includeProlonged 長音記号をひらがなに含める場合はtrue。含めない場合はfalse
   * @return 判定結果。ひらがなのときはtrue
   */
  public static boolean isHiragana( char c, boolean includeProlonged ){
    if( !includeProlonged || c != KATAKANA_HIRAGANA_PROLONGED_SOUND_MARK ){
      UnicodeBlock block = UnicodeBlock.of( c );
      if( block == null ) return false;
      return block.equals( UnicodeBlock.HIRAGANA );
    }
    return true;
  }
  
  /**
   * 文字がひらがなかひらがなでないかを判定する。
   * @param c 判定対象の文字
   * @return 判定結果。ひらがなのときはtrue
   */
  public static boolean isHiragana( int c ){
    return isHiragana( (char)c );
  }
  
  /**
   * 文字列がすべてひらがなか否かを判定する。
   * 判定にはUTF-16の「ぁ」から「ん」の間の文字か否かで判断する。
   * @param str 判定対象の文字列
   * @return 判定結果。すべてひらがなのときはtrue
   */
  public static boolean isHiraganaUTF16( String str ){
    return isHiraganaUTF16( str.toCharArray() );
  }

  /**
   * 文字列がすべてひらがなか否かを判定する。
   * 判定にはUTF-16の「ぁ」から「ん」の間の文字か否かで判断する。
   * @param str 判定対象の文字列
   * @return 判定結果。すべてひらがなのときはtrue
   */
  public static boolean isHiraganaUTF16( char[] str ){
    if( str.length == 0 ) return false;
    for( char c : str ){
      if( !isHiraganaUTF16( c ) ) return false;
    }
    return true;
  }
  
  /**
   * 文字列がすべてひらがなか否かを判定する。
   * 判定にはUTF-16の「ぁ」から「ん」の間の文字か否かで判断する。
   * @param str 判定対象の文字列
   * @param start チェックする開始位置
   * @param len チェックする長さ
   * @return 判定結果。すべてひらがなのときはtrue
   */
  public static boolean isHiraganaUTF16( char[] str, int start, int len ){
    if( str.length < start + len ) return false;
    for( int i = 0; i < len; i++ ){
      char c = str[start + i];
      if( !isHiraganaUTF16( c ) ) return false;
    }
    return true;
  }
  
  /**
   * 文字がひらがなかひらがなでないかを判定する。
   * 判定にはUTF-16の「ぁ」から「ん」の間の文字か否かで判断する。
   * @param c 判定対象の文字
   * @return 判定結果。ひらがなのときはtrue
   */
  public static boolean isHiraganaUTF16( char c ){
    return c >= 'ぁ' && c <= 'ん';
  }
  
  /**
   * 文字がひらがなかひらがなでないかを判定する。
   * 判定にはUTF-16の「ぁ」から「ん」の間の文字か否かで判断する。
   * @param c 判定対象の文字
   * @return 判定結果。ひらがなのときはtrue
   */
  public static boolean isHiraganaUTF16( int c ){
    return isHiraganaUTF16( (char)c );
  }
  
  /**
   * 文字列にひらがなが含まれているか否かを判定する。
   * @param str 判定対象の文字列
   * @return 判定結果。ひらがなが含まれている時はtrue
   */
  public static boolean isHiraganaIncluded( String str ){
    return isHiraganaIncluded( str.toCharArray() );
  }

  /**
   * 文字列にひらがなが含まれているか否かを判定する。
   * @param str 判定対象の文字列
   * @return 判定結果。ひらがなが含まれている時はtrue
   */
  public static boolean isHiraganaIncluded( char[] str ){
    if( str.length == 0 ) return false;
    for( char c : str ){
      if( isHiragana( c ) ) return true;
    }
    return false;
  }
  
  /**
   * 文字列にひらがなが含まれているか否かを判定する。
   * @param str 判定対象の文字列
   * @param start チェックする開始位置
   * @param len チェックする長さ
   * @return 判定結果。ひらがなが含まれている時はtrue
   */
  public static boolean isHiraganaIncluded( char[] str, int start, int len ){
    if( str.length < start + len ) return false;
    for( int i = 0; i < len; i++ ){
      char c = str[start + i];
      if( isHiragana( c ) ) return true;
    }
    return false;
  }
  
  /**
   * 文字列がすべてカタカナか否かを判定する。なお、カタカナは全角であっても半角であっても良い。
   * @param str 判定対象の文字列
   * @return 判定結果。すべてカタカナの時はtrue
   */
  public static boolean isKatakana( String str ){
    return isKatakana( str.toCharArray() );
  }

  /**
   * 文字列がすべてカタカナか否かを判定する。なお、カタカナは全角であっても半角であっても良い。
   * @param str 判定対象の文字列
   * @return 判定結果。すべてカタカナの時はtrue
   */
  public static boolean isKatakana( char[] str ){
    if( str.length == 0 ) return false;
    for( char c : str ){
      if( !isKatakana( c ) ) return false;
    }
    return true;
  }
  
  /**
   * 文字列がすべてカタカナか否かを判定する。なお、カタカナは全角であっても半角であっても良い。
   * @param str 判定対象の文字列
   * @param start チェックする開始位置
   * @param len チェックする長さ
   * @return 判定結果。すべてカタカナの時はtrue
   */
  public static boolean isKatakana( char[] str, int start, int len ){
    if( str.length < start + len ) return false;
    for( int i = 0; i < len; i++ ){
      char c = str[start + i];
      if( !isKatakana( c ) ) return false;
    }
    return true;
  }
  
  /**
   * 文字がカタカナかカタカナでないかを判定する。なお、カタカナは全角であっても半角であっても良い。
   * @param c 判定対象の文字
   * @return 判定結果。カタカナのときはtrue
   */
  public static boolean isKatakana( char c ){
    return isKatakana( c, true );
  }
  
  /**
   * 文字がカタカナかカタカナでないかを判定する。なお、カタカナは全角であっても半角であっても良い。
   * @param c 判定対象の文字
   * @param includeProlonged 長音記号をカタカナに含める場合はtrue。含めない場合はfalse
   * @return 判定結果。カタカナのときはtrue
   */
  public static boolean isKatakana( char c, boolean includeProlonged ){
    return isDoubleWidthKatakana( c, includeProlonged ) || isHalfWidthKatakana( c, includeProlonged );
  }
  
  /**
   * 文字がカタカナかカタカナでないかを判定する。なお、カタカナは全角であっても半角であっても良い。
   * @param c 判定対象の文字
   * @return 判定結果。カタカナのときはtrue
   */
  public static boolean isKatakana( int c ){
    return isKatakana( (char)c );
  }
  
  /**
   * 文字が全角カタカナか全角カタカナでないかを判定する。
   * @param c 判定対象の文字
   * @return 判定結果。全角カタカナのときはtrue
   */
  public static boolean isDoubleWidthKatakana( char c ){
    return isDoubleWidthKatakana( c, true );
  }
  
  /**
   * 文字が全角カタカナか全角カタカナでないかを判定する。
   * @param c 判定対象の文字
   * @param includeProlonged 長音記号をカタカナに含める場合はtrue。含めない場合はfalse
   * @return 判定結果。全角カタカナのときはtrue
   */
  public static boolean isDoubleWidthKatakana( char c, boolean includeProlonged ){
    if( !includeProlonged && c == KATAKANA_HIRAGANA_PROLONGED_SOUND_MARK )
      return false;
    UnicodeBlock block = UnicodeBlock.of( c );
    if( block == null ) return false;
    return block.equals( UnicodeBlock.KATAKANA );
  }
  
  /**
   * 文字が半角カタカナか半角カタカナでないかを判定する。半角カタカナには濁点、半濁点記号の含む。
   * @param c 判定対象の文字
   * @return 判定結果。半角カタカナのときはtrue
   */
  public static boolean isHalfWidthKatakana( char c ){
    return isHalfWidthKatakana( c, true );
  }
  
  /**
   * 文字が半角カタカナか半角カタカナでないかを判定する。半角カタカナには濁点、半濁点記号の含む。
   * @param c 判定対象の文字
   * @param includeProlonged 長音記号をカタカナに含める場合はtrue。含めない場合はfalse
   * @return 判定結果。半角カタカナのときはtrue
   */
  public static boolean isHalfWidthKatakana( char c, boolean includeProlonged ){
    if( !includeProlonged && c == HALF_KATAKANA_PROLONGED_SOUND_MARK )
      return false;
    return 'ｦ' <= c && c <= 'ﾟ';
  }
  
  /**
   * 文字列がすべて全角カタカナか否かを判定する。
   * 判定にはUTF-16の「ァ」から「ヶ」の間の文字か否かで判断する。
   * ひらがなに変換できない「ヴ」「ヵ」「ヶ」を含む。
   * @param str 判定対象の文字列
   * @return 判定結果。すべてカタカナの時はtrue
   */
  public static boolean isDoubleWidthKatakanaUTF16( String str ){
    return isDoubleWidthKatakanaUTF16( str.toCharArray() );
  }

  /**
   * 文字列がすべて全角カタカナか否かを判定する。
   * 判定にはUTF-16の「ァ」から「ヶ」の間の文字か否かで判断する。
   * ひらがなに変換できない「ヴ」「ヵ」「ヶ」を含む。
   * @param str 判定対象の文字列
   * @return 判定結果。すべてカタカナの時はtrue
   */
  public static boolean isDoubleWidthKatakanaUTF16( char[] str ){
    if( str.length == 0 ) return false;
    for( char c : str ){
      if( !isDoubleWidthKatakanaUTF16( c ) ) return false;
    }
    return true;
  }
  
  /**
   * 文字列がすべて全角カタカナか否かを判定する。
   * 判定にはUTF-16の「ァ」から「ヶ」の間の文字か否かで判断する。
   * ひらがなに変換できない「ヴ」「ヵ」「ヶ」を含む。
   * @param str 判定対象の文字列
   * @param start チェックする開始位置
   * @param len チェックする長さ
   * @return 判定結果。すべてカタカナの時はtrue
   */
  public static boolean isDoubleWidthKatakanaUTF16( char[] str, int start, int len ){
    if( str.length < start + len ) return false;
    for( int i = 0; i < len; i++ ){
      char c = str[start + i];
      if( !isDoubleWidthKatakanaUTF16( c ) ) return false;
    }
    return true;
  }
  
  /**
   * 文字が全角カタカナか全角カタカナでないかを判定する。
   * 判定にはUTF-16の「ァ」から「ヶ」の間の文字か否かで判断する。
   * ひらがなに変換できない「ヴ」「ヵ」「ヶ」を含む。
   * @param c 判定対象の文字
   * @return 判定結果。カタカナのときはtrue
   */
  public static boolean isDoubleWidthKatakanaUTF16( char c ){
    return c >= 'ァ' && c <= 'ヶ';
  }
  
  /**
   * 文字が全角カタカナか全角カタカナでないかを判定する。
   * 判定にはUTF-16の「ァ」から「ヶ」の間の文字か否かで判断する。
   * ひらがなに変換できない「ヴ」「ヵ」「ヶ」を含む。
   * @param c 判定対象の文字
   * @return 判定結果。カタカナのときはtrue
   */
  public static boolean isDoubleWidthKatakanaUTF16( int c ){
    return isDoubleWidthKatakanaUTF16( (char)c );
  }
  
  /**
   * 文字列がすべて全角カタカナか否かを判定する。
   * 判定にはUTF-16の「ァ」から「ン」の間の文字か否かで判断する。
   * ひらがなに変換できない「ヴ」「ヵ」「ヶ」は含まない。
   * @param str 判定対象の文字列
   * @return 判定結果。すべてカタカナの時はtrue
   */
  public static boolean isDoubleWidthKatakanaMappableHiraganaUTF16( String str ){
    return isDoubleWidthKatakanaMappableHiraganaUTF16( str.toCharArray() );
  }

  /**
   * 文字列がすべて全角カタカナか否かを判定する。
   * 判定にはUTF-16の「ァ」から「ン」の間の文字か否かで判断する。
   * ひらがなに変換できない「ヴ」「ヵ」「ヶ」は含まない。
   * @param str 判定対象の文字列
   * @return 判定結果。すべてカタカナの時はtrue
   */
  public static boolean isDoubleWidthKatakanaMappableHiraganaUTF16( char[] str ){
    if( str.length == 0 ) return false;
    for( char c : str ){
      if( !isDoubleWidthKatakanaMappableHiraganaUTF16( c ) ) return false;
    }
    return true;
  }
  
  /**
   * 文字列がすべて全角カタカナか否かを判定する。
   * 判定にはUTF-16の「ァ」から「ン」の間の文字か否かで判断する。
   * ひらがなに変換できない「ヴ」「ヵ」「ヶ」は含まない。
   * @param str 判定対象の文字列
   * @param start チェックする開始位置
   * @param len チェックする長さ
   * @return 判定結果。すべてカタカナの時はtrue
   */
  public static boolean isDoubleWidthKatakanaMappableHiraganaUTF16( char[] str, int start, int len ){
    if( str.length < start + len ) return false;
    for( int i = 0; i < len; i++ ){
      char c = str[start + i];
      if( !isDoubleWidthKatakanaMappableHiraganaUTF16( c ) ) return false;
    }
    return true;
  }
  
  /**
   * 文字が全角カタカナか全角カタカナでないかを判定する。
   * 判定にはUTF-16の「ァ」から「ン」の間の文字か否かで判断する。
   * ひらがなに変換できない「ヴ」「ヵ」「ヶ」は含まない。
   * @param c 判定対象の文字
   * @return 判定結果。カタカナのときはtrue
   */
  public static boolean isDoubleWidthKatakanaMappableHiraganaUTF16( char c ){
    return c >= 'ァ' && c <= 'ン';
  }
  
  /**
   * 文字が全角カタカナか全角カタカナでないかを判定する。
   * 判定にはUTF-16の「ァ」から「ン」の間の文字か否かで判断する。
   * ひらがなに変換できない「ヴ」「ヵ」「ヶ」は含まない。
   * @param c 判定対象の文字
   * @return 判定結果。カタカナのときはtrue
   */
  public static boolean isDoubleWidthKatakanaMappableHiraganaUTF16( int c ){
    return isDoubleWidthKatakanaMappableHiraganaUTF16( (char)c );
  }
  
  /**
   * 文字列にカタカナが含まれているか否かを判定する。
   * @param str 判定対象の文字列
   * @return 判定結果。カタカナが含まれている時はtrue
   */
  public static boolean isKatakanaIncluded( String str ){
    return isKatakanaIncluded( str.toCharArray() );
  }

  /**
   * 文字列にカタカナが含まれているか否かを判定する。
   * @param str 判定対象の文字列
   * @return 判定結果。カタカナが含まれている時はtrue
   */
  public static boolean isKatakanaIncluded( char[] str ){
    if( str.length == 0 ) return false;
    for( char c : str ){
      if( isKatakana( c ) ) return true;
    }
    return false;
  }
  
  /**
   * 文字列にカタカナが含まれているか否かを判定する。
   * @param str 判定対象の文字列
   * @param start チェックする開始位置
   * @param len チェックする長さ
   * @return 判定結果。カタカナが含まれている時はtrue
   */
  public static boolean isKatakanaIncluded( char[] str, int start, int len ){
    if( str.length < start + len ) return false;
    for( int i = 0; i < len; i++ ){
      char c = str[start + i];
      if( isKatakana( c ) ) return true;
    }
    return false;
  }
  
  /**
   * 文字列がすべて漢字か否かを判定する。
   * @param str 判定対象の文字列
   * @return 判定結果。すべて漢字の時はtrue
   */
  public static boolean isKanji( String str ){
    return isKanji( str.toCharArray() );
  }

  /**
   * 文字列がすべて漢字か否かを判定する。
   * @param str 判定対象の文字列
   * @return 判定結果。すべて漢字の時はtrue
   */
  public static boolean isKanji( char[] str ){
    if( str.length == 0 ) return false;
    for( char c : str ){
      if( !isKanji( c ) ) return false;
    }
    return true;
  }
  
  /**
   * 文字列がすべて漢字か否かを判定する。
   * @param str 判定対象の文字列
   * @param start チェックする開始位置
   * @param len チェックする長さ
   * @return 判定結果。すべて漢字のときはtrue
   */
  public static boolean isKanji( char[] str, int start, int len ){
    if( str.length < start + len ) return false;
    for( int i = 0; i < len; i++ ){
      char c = str[start + i];
      if( !isKanji( c ) ) return false;
    }
    return true;
  }
  
  /**
   * 文字が漢字か漢字でないかを判定する。
   * @param c 判定対象の文字
   * @return 判定結果。漢字の時はtrue
   */
  public static boolean isKanji( char c ){
    UnicodeBlock block = UnicodeBlock.of( c );
    if( block == null ) return false;
    String blk = block.toString();
    return blk.startsWith( "CJK_UNIFIED_IDEOGRAPHS" ) ||
           blk.startsWith( "CJK_COMPATIBILITY" );
    // これではだめ
    //return block.equals( UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS ) ||
    //  block.equals( UnicodeBlock.CJK_COMPATIBILITY );
  }

  /**
   * 文字が漢字か漢字でないかを判定する。
   * @param c 判定対象の文字
   * @return 判定結果。漢字の時はtrue
   */
  public static boolean isKanji( int c ){
    return isKanji( (char)c );
  }

  private static final int deltaH2K = 'ァ' - 'ぁ';
  private static final int deltaK2H = 'ぁ' - 'ァ';
  
  /**
   * ひらがな（UTF-16の「ぁ」から「ん」）を全角カタカナ（UTF-16の「ァ」から「ン」）に変換する。
   * @param hiragana 変換対象のひらがな文字列
   * @return 変換後のカタカナ文字列
   */
  public static String transHiraganaDoubleWidthKatakanaUTF16( String hiragana ){
    char[] str = new char[hiragana.length()];
    for( int i = 0; i < str.length; i++ ){
      str[i] = hiragana.charAt( i );
      if( isHiraganaUTF16( str[i] ) )
        str[i] = (char)( str[i] + deltaH2K );
    }
    return new String( str );
  }
  
  /**
   * 全角カタカナ（UTF-16の「ァ」から「ン」）をひらがな（UTF-16の「ぁ」から「ん」）に変換する。
   * @param katakana 変換対象のカタカナ文字列
   * @return 変換後のひらがな文字列
   */
  public static String transDoubleWidthKatakanaHiraganaUTF16( String katakana ){
    char[] str = new char[katakana.length()];
    for( int i = 0; i < str.length; i++ ){
      str[i] = katakana.charAt( i );
      if( isDoubleWidthKatakanaMappableHiraganaUTF16( str[i] ) )
        str[i] = (char)( str[i] + deltaK2H );
    }
    return new String( str );
  }
  
  static final char[] TURBIDABLE_HIRAGANA = { 'か','き','く','け','こ','さ','し','す','せ','そ','た','ち','つ','て','と','は','ひ','ふ','へ','ほ' };
  static final char[] TURBID_HIRAGANA = { 'が','ぎ','ぐ','げ','ご','ざ','じ','ず','ぜ','ぞ','だ','ぢ','づ','で','ど','ば','び','ぶ','べ','ぼ' };

  /**
   * ひらがなに濁点を付ける。
   * @param c 濁点を付ける対象のひらがな
   * @return 濁点が付けられたひらがな
   */
  public static char turbidHiragana( char c ){
    for( int i = 0; i < TURBIDABLE_HIRAGANA.length; i++ ){
      if( c == TURBIDABLE_HIRAGANA[i] )
        return TURBID_HIRAGANA[i];
    }
    return c;
  }

  /**
   * ひらがなから濁点を取り除く。
   * @param c 濁点が付いたひらがな
   * @return 濁点がとれたひらがな
   */
  public static char unturbidHiragana( char c ){
    for( int i = 0; i < TURBID_HIRAGANA.length; i++ ){
      if( c == TURBID_HIRAGANA[i] )
        return TURBIDABLE_HIRAGANA[i];
    }
    return c;
  }
  
  static final char[] TURBIDABLE_KATAKANA = { 'カ','キ','ク','ケ','コ','サ','シ','ス','セ','ソ','タ','チ','ツ','テ','ト','ハ','ヒ','フ','ヘ','ホ' };
  static final char[] TURBID_KATAKANA = { 'ガ','ギ','グ','ゲ','ゴ','ザ','ジ','ズ','ゼ','ゾ','ダ','ヂ','ヅ','デ','ド','バ','ビ','ブ','ベ','ボ' };
  
  /**
   * カタカナに濁点を付ける。
   * @param c 濁点を付ける対象のカタカナ
   * @return 濁点が付けられたカタカナ
   */
  public static char turbidKatakana( char c ){
    for( int i = 0; i < TURBIDABLE_KATAKANA.length; i++ ){
      if( c == TURBIDABLE_KATAKANA[i] )
        return TURBID_KATAKANA[i];
    }
    return c;
  }

  /**
   * カタカナから濁点を取り除く。
   * @param c 濁点が付いたカタカナ
   * @return 濁点がとれたカタカナ
   */
  public static char unturbidKatakana( char c ){
    for( int i = 0; i < TURBID_KATAKANA.length; i++ ){
      if( c == TURBID_KATAKANA[i] )
        return TURBIDABLE_KATAKANA[i];
    }
    return c;
  }

  /**
   * ひらがなまたはカタカナに濁点を付ける。
   * @param c 濁点を付ける対象のひらがなまたはカタカナ
   * @return 濁点が付けられたひらがなまたはカタカナ
   */
  public static char turbidKana( char c ){
    if( isHiragana( c ) ) return turbidHiragana( c );
    else if( isKatakana( c ) ) return turbidKatakana( c );
    return c;
  }

  /**
   * ひらがなまたはカタカナから濁点を取り除く。
   * @param c 濁点が付いたひらがなまたはカタカナ
   * @return 濁点がとれたひらがなまたはカタカナ
   */
  public static char unturbidKana( char c ){
    if( isHiragana( c ) ) return unturbidHiragana( c );
    else if( isKatakana( c ) ) return unturbidKatakana( c );
    return c;
  }
  
  /**
   * 文字が長音記号か否かを判定する。
   * @param c 判定する文字
   * @return 長音記号の場合はtrue。長音記号でないときはfalse
   */
  public static boolean isProlongedSoundMark( char c ){
    return c == KATAKANA_HIRAGANA_PROLONGED_SOUND_MARK ||
      c == HALF_KATAKANA_PROLONGED_SOUND_MARK;
  }
  
  /**
   * 文字が長音記号か否かを判定する。
   * @param c 判定する文字
   * @return 長音記号の場合はtrue。長音記号でないときはfalse
   */
  public static boolean isProlongedSoundMark( int c ){
    return isProlongedSoundMark( (char)c );
  }
}
