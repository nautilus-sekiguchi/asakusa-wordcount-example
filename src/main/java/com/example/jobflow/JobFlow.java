package com.example.jobflow;

import com.asakusafw.vocabulary.flow.*;
import com.example.io.TextInput;
import com.example.io.WordCountOutput;
import com.example.modelgen.dmdl.model.Text;
import com.example.modelgen.dmdl.model.WordCount;
import com.example.operator.WordOperatorFactory;

/**
 * ジョブフロークラス
 *
 * 一つのまとまった出力（業務）のフローを記述する
 *
 * @author bohnen
 */
@com.asakusafw.vocabulary.flow.JobFlow(name = "wordCountFlow")
public class JobFlow extends FlowDescription{

    private final In<Text> in;
    private final Out<WordCount> out;

    /**
     * ジョブフローのコンストラクタでは、入出力の定義を行う
     * @param in
     * @param out
     */
    public JobFlow(
            @Import(name="input", description = TextInput.class)
            final In<Text> in,
            @Export(name="output",description = WordCountOutput.class)
            final Out<WordCount> out) {
        this.in = in;
        this.out = out;
    }

    /**
     * ジョブフローを記述する
     */
    @Override protected void describe() {
        WordOperatorFactory op = new WordOperatorFactory();

        final WordOperatorFactory.Tokenize tokenized = op.tokenize(in);
        final WordOperatorFactory.Count counted = op.count(tokenized.out);
        out.add(counted.out);

    }
}
