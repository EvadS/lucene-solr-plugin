package com.se.sample;

import com.se.sample.filter.MindBlowingTokenFilter;
import org.apache.lucene.analysis.*;

import java.io.IOException;

public class TestMindBlowingTokenFilter extends BaseTokenStreamTestCase {

    Analyzer analyzer = new Analyzer() {
        @Override
        protected TokenStreamComponents createComponents(String fieldName) {
           // Tokenizer source = new FooTokenizer(reader);
            Tokenizer source = new MockTokenizer( MockTokenizer.WHITESPACE, true);
            TokenStream filter = new MindBlowingTokenFilter(source);

            return new TokenStreamComponents(source, filter);
        }
    };

    public void testPositionIncrementsSingleTerm() throws IOException {

        String output[] = {"queries" + MindBlowingTokenFilter.MIND_BLOWING_SUFFIX, "queries"};
        // the position increment for the first term must be one in this case and of the second must be 0,
        // because the second term is stored in the same position in the token filter stream
        int posIncrements[] = {1, 0};
        // this is dummy stuff, but the test does not run without it
        int posLengths[] = {1, 1};

        assertAnalyzesToPositions(analyzer, "queries", output, posIncrements, posLengths);
    }

    public void testPositionIncrementsTwoTerm() throws IOException {

        String output[] = {"your" + MindBlowingTokenFilter.MIND_BLOWING_SUFFIX, "your", "queries" + MindBlowingTokenFilter.MIND_BLOWING_SUFFIX, "queries"};
        // the position increment for the first term must be one in this case and of the second must be 0,
        // because the second term is stored in the same position in the token filter stream
        int posIncrements[] = {1, 0, 1, 0};
        // this is dummy stuff, but the test does not run without it
        int posLengths[] = {1, 1, 1, 1};

        assertAnalyzesToPositions(analyzer, "your queries", output, posIncrements, posLengths);
    }

    public void testPositionIncrementsFourTerms() throws IOException {

        String output[] = {
                "your" + MindBlowingTokenFilter.MIND_BLOWING_SUFFIX, "your",
                "queries" + MindBlowingTokenFilter.MIND_BLOWING_SUFFIX, "queries",
                "are" + MindBlowingTokenFilter.MIND_BLOWING_SUFFIX, "are",
                "fast" + MindBlowingTokenFilter.MIND_BLOWING_SUFFIX, "fast"};
        // the position increment for the first term must be one in this case and of the second must be 0,
        // because the second term is stored in the same position in the token filter stream
        int posIncrements[] = {
                1, 0,
                1, 0,
                1, 0,
                1, 0};
        // this is dummy stuff, but the test does not run without it
        int posLengths[] = {
                1, 1,
                1, 1,
                1, 1,
                1, 1};

        // position increments are following the 1-0 pattern, because for each next term we insert a new term into
        // the same position (i.e. position increment is 0)
        assertAnalyzesToPositions(analyzer, "your queries are fast", output, posIncrements, posLengths);
    }

    public void testPositionOffsetsFourTerms() throws IOException {

        String output[] = {
                "your" + MindBlowingTokenFilter.MIND_BLOWING_SUFFIX, "your",
                "queries" + MindBlowingTokenFilter.MIND_BLOWING_SUFFIX, "queries",
                "are" + MindBlowingTokenFilter.MIND_BLOWING_SUFFIX, "are",
                "fast" + MindBlowingTokenFilter.MIND_BLOWING_SUFFIX, "fast"};
        // the position increment for the first term must be one in this case and of the second must be 0,
        // because the second term is stored in the same position in the token filter stream
        int startOffsets[] = {
                0, 0,
                5, 5,
                13, 13,
                17, 17};
        // this is dummy stuff, but the test does not run without it
        int endOffsets[] = {
                4, 4,
                12, 12,
                16, 16,
                21, 21};

        assertAnalyzesTo(analyzer, "your queries are fast", output, startOffsets, endOffsets);
    }

}
