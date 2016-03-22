package com.example.batch;

import com.asakusafw.vocabulary.batch.Batch;
import com.asakusafw.vocabulary.batch.BatchDescription;
import com.example.jobflow.JobFlow;

/**
 * バッチクラス
 *
 * YAESSで起動するバッチの定義を記述する。
 *
 * @author bohnen
 */
// nameがYAESS起動時のバッチ名になる。sparkの場合は、spark.[バッチ名]となる
@com.asakusafw.vocabulary.batch.Batch(name = "wordcount")
public class AsakusaBatch extends BatchDescription {

    /**
     * バッチ定義を記述する
     */
    @Override protected void describe() {
        run(JobFlow.class).soon();
    }
}
