package com.example.jobflow;

import com.asakusafw.vocabulary.flow.*;
import com.example.io.TextInput;
import com.example.io.WordCountHiveOutput;
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
    private final Out<WordCount> pqt;

    /**
     * ジョブフローのコンストラクタでは、入出力の定義を行う
     * @param in
     * @param out
     * @param pqt
     */
    public JobFlow(
            @Import(name = "input", description = TextInput.class)
            final In<Text> in,
            @Export(name = "output", description = WordCountOutput.class)
            final Out<WordCount> out,
            @Export(name = "parquet", description = WordCountHiveOutput.class)
            final Out<WordCount> pqt) {
        this.in = in;
        this.out = out;
        this.pqt = pqt;
    }

    /**
     * ジョブフローを記述する
     */
    @Override protected void describe() {
        WordOperatorFactory op = new WordOperatorFactory();

        final WordOperatorFactory.Tokenize tokenized = op.tokenize(in);
        final WordOperatorFactory.Count counted = op.count(tokenized.out);
        out.add(counted.out);
        pqt.add(counted.out);
    }
}
