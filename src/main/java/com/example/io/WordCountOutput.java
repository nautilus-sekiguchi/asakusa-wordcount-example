package com.example.io;

import com.example.modelgen.dmdl.csv.AbstractWordCountCsvOutputDescription;

import java.util.Arrays;
import java.util.List;

/**
 * 単語集計のCSV出力例
 *
 * @author Tadatoshi
 */
public class WordCountOutput extends AbstractWordCountCsvOutputDescription {

    /**
     * ファイル出力ディレクトリを指定する
     * @return ファイル出力ディレクトリ
     */
    @Override public String getBasePath() {
        return "wordcount";
    }

    /**
     * 出力ファイルのソート順を指定する
     * @return ソート順。リストで指定し、逆順は "-" を付与する。
     */
    @Override public List<String> getOrder() {
        return Arrays.asList("word");
    }

    /**
     * 出力ファイルの出力パターンを指定する。
     *
     * 例では単一ファイルに出力しているが、単一ファイル出力では性能がでない(Reducerが一つになる）ことに注意。
     * {property}.csv とすることで、プロパティの値毎にファイルを分割できる。
     * また、*.csv とすることで、mapperでファイル出力を行う。（ただし、ソート指定はできない）
     *
     * @return
     */
    @Override public String getResourcePattern() {
        return "wordcount.csv";
    }
}
