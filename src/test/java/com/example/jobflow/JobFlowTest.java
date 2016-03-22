package com.example.jobflow;

import com.asakusafw.testdriver.JobFlowDriverOutput;
import com.asakusafw.testdriver.JobFlowTester;
import com.example.modelgen.dmdl.model.Text;
import com.example.modelgen.dmdl.model.WordCount;
import org.junit.Test;

/**
 * ジョブフローのテスト
 *
 * @author Tadatoshi
 */
public class JobFlowTest {

    @Test
    public void jobflowTest(){
        JobFlowTester tester = new JobFlowTester(getClass());
        tester.input("input", Text.class).prepare("text.xls#input");
        tester.output("output", WordCount.class).verify("word_count.xls#output","word_count.xls#rule");
        tester.runTest(JobFlow.class);
    }

}
