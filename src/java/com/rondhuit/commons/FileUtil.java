/*
 *  Copyright (c) 2012-2014 RONDHUIT Co.,Ltd.
 *
 */

package com.rondhuit.commons;

import java.io.File;

/**
 * ファイルを扱う共通ユーティリティクラス。
 * @since 0.1
 */
public class FileUtil {

  /**
   * 引数で指定されたディレクトリやファイルを再帰的に削除する。
   * @param parent
   */
  public static void deleteRecursively(String parent){
    executeRecursively(new DeleteExecutor(), parent);
  }

  /**
   * 引数で指定されたディレクトリやファイルに対して{@link Executor}を再帰的に実行する。
   * @param executor
   * @param parent
   */
  public static void executeRecursively(Executor executor, String parent){
    executeRecursively(executor, parent, null);
  }

  /**
   * 引数で指定されたディレクトリやファイルに対して{@link Executor}を再帰的に実行する。
   * @param executor
   * @param parent
   * @param file
   */
  public static void executeRecursively(Executor executor, String parent, String file){
    File theFile = file == null ? new File(parent) : new File(parent, file);
    if(!theFile.exists()) return;
    if(theFile.isFile()){
      if(executor.isExecutable(theFile))
        executor.execute(theFile);
      return;
    }
    else{
      if(!theFile.isAbsolute()){
        theFile = theFile.getAbsoluteFile();
      }
      String[] fileList = theFile.list();
      for(String aFile : fileList){
        executeRecursively(executor, theFile.getAbsolutePath(), aFile);
      }
      if(executor.isExecutable(theFile))
        executor.execute(theFile);
    }
  }

  /**
   * {@link FileUtil#executeRecursively(Executor, String, String)}によって再帰的に呼び出される{@link Executor}の
   * 抽象クラス。
   */
  public static abstract class Executor {

    /**
     * サブクラスはこのメソッドを実装して、引数で指定されたファイルを処理する。
     * 
     * @param theFile
     */
    public abstract void execute(File theFile);

    /**
     * サブクラスはこのメソッドをオーバーライドして引数で指定されたファイルを処理するかどうかを判定して返す。
     * デフォルトの実装では常にtrueを返す。
     * 
     * @param theFile
     * @return このファイルを処理する場合はtrue、処理しない場合はfalseを返す。
     */
    public boolean isExecutable(File theFile){
      return true;
    }
  }
  
  static class DeleteExecutor extends Executor {
    public void execute(File theFile) {
      theFile.delete();
    }
  }
}
