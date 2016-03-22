package com.example.operator;

import com.asakusafw.runtime.testing.MockResult;
import com.example.modelgen.dmdl.model.Text;
import com.example.modelgen.dmdl.model.Word;
import org.junit.Test;

import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * 演算子単体テスト
 *
 * @author Tadatoshi
 */
public class WordOperatorTest {

    @Test
    public void tokenizeTest(){
        // Result型のテスト用Mock。addするときのオブジェクトを使いまわしているときは、
        // ちょっと面倒。 see. http://docs.asakusafw.com/latest/release/ja/html/testing/start-guide.html
        MockResult<Word> words = new MockResult<Word>() {
            @Override protected Word bless(final Word result) {
                Word ret = new Word();
                ret.copyFrom(result);
                return ret;
            }
        };

        WordOperatorImpl op = new WordOperatorImpl();

        Text in = new Text();
        in.setLineAsString("This is 'a' test.");
        op.tokenize(in,words);

        // 英単語とアンダースコア以外は無視して、すべて小文字になっていることを確認
        assertThat(words.getResults().size(),is(4));

        StringBuffer wb = new StringBuffer();
        for(Word w : words.getResults()){
            wb.append(w.getWordAsString());
        }
        assertThat(wb.toString(),is("thisisatest"));
    }
}
