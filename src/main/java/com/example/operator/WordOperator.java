package com.example.operator;

import com.asakusafw.runtime.core.Result;
import com.asakusafw.vocabulary.operator.Extract;
import com.asakusafw.vocabulary.operator.Summarize;
import com.example.modelgen.dmdl.model.Text;
import com.example.modelgen.dmdl.model.Word;
import com.example.modelgen.dmdl.model.WordCount;

import java.util.regex.Pattern;

/**
 * 演算子定義クラス
 * abstractクラスとして定義しなければならないことに注意する。
 *
 * @author bohnen
 */
public abstract class WordOperator {

    // 単語分割パターン
    private final Pattern splitPattern = Pattern.compile("\\W+");

    // Extractの出力に使うオブジェクト。outにaddする場合は、使い回して問題ない。
    private Word word = new Word();

    /**
     * 抽出演算子の例。
     *
     * ここでは、行を単語に分解して、一行から複数の単語モデルを出力している
     * @param in 行モデル
     * @param out 単語モデル
     */
    @Extract
    public void tokenize(Text in,Result<Word> out){
        String[] words = splitPattern.split(in.getLineAsString());
        for(String s : words){
            word.reset();
            word.setWordAsString(s.toLowerCase());
            out.add(word);
        }
    }

    /**
     * 集計演算子の例
     *
     * 集計演算を書く必要はないが、定義だけ必要。
     * @param in 単語モデル
     * @return 単語集計モデル
     */
    @Summarize
    public abstract WordCount count(Word in);
}
