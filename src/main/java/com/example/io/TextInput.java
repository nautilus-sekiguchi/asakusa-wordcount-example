package com.example.io;

import com.example.modelgen.dmdl.line.AbstractTextLineInputDescription;

/**
 * Direct IO Lineを利用したテキストファイル入力クラス
 *
 * @author Tadatoshi
 */
public class TextInput extends AbstractTextLineInputDescription {

    /**
     * ファイルを配置するディレクトリ
     *
     * Asakusa設定ファイルに記載されている <b>com.asakusafw.directio.root.fs.path</b>からの
     * 相対位置を指定する。
     * @return ディレクトリパス
     */
    @Override public String getBasePath() {
        return "inputtext";
    }

    /**
     * ファイル名のパターン
     * @return ファイル名パターン
     */
    @Override public String getResourcePattern() {
        return "*.txt";
    }

    /**
     * ファイルがなくても動作するかどうか。サンプルとして記載しているが、通常は指定しなくてもよい。
     * @return trueならファイルが存在しなくてもエラーにならない
     */
    @Override public boolean isOptional() {
        return true;
    }
}
