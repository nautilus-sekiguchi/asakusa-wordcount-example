package com.example.io;

import com.example.modelgen.dmdl.hive.parquet.AbstractWordCountParquetFileOutputDescription;

/**
 * Created by bohnen on 2016/03/28
 *
 * @author Tadatoshi
 */
public class WordCountHiveOutput extends AbstractWordCountParquetFileOutputDescription{
    @Override public String getBasePath() {
        return "tables/word_count";
    }

    @Override public String getResourcePattern() {
        return "*";
    }
}
