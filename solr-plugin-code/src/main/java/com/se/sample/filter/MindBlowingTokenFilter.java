package com.se.sample.filter;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionLengthAttribute;

import java.io.IOException;


public final class MindBlowingTokenFilter extends TokenFilter {

    private final CharTermAttribute termAtt;
    private final PositionIncrementAttribute posAtt;

    // dummy thing, is needed for complying with BaseTokenStreamTestCase assertions
    private PositionLengthAttribute posLenAtt; // don't remove this, otherwise the low-level test will fail

    private State save;

    public static final String MIND_BLOWING_SUFFIX = "mindblowing";

    /**
     * Construct a token stream filtering the given input.
     *
     * @param input
     */
     public MindBlowingTokenFilter(TokenStream input) {
        super(input);
        this.termAtt = addAttribute(CharTermAttribute.class);
        this.posAtt = addAttribute(PositionIncrementAttribute.class);
        this.posLenAtt = addAttribute(PositionLengthAttribute.class);
    }



    @Override
    public boolean incrementToken() throws IOException {
        if( save != null ) {
            restoreState(save);
            save = null;
            return true;
        }

        if (input.incrementToken()) {
            // pass through zero-length terms
            int oldLen = termAtt.length();
            if (oldLen == 0) return true;
            int origOffset = posAtt.getPositionIncrement();

            // save original state
            posAtt.setPositionIncrement(0);
            save = captureState();

            //char[] origBuffer = termAtt.buffer();

            char [] buffer = termAtt.resizeBuffer(oldLen + MIND_BLOWING_SUFFIX.length());

            for (int i = 0; i < MIND_BLOWING_SUFFIX.length(); i++) {
                buffer[oldLen + i] = MIND_BLOWING_SUFFIX.charAt(i);
            }

            posAtt.setPositionIncrement(origOffset);
            termAtt.copyBuffer(buffer, 0, oldLen + MIND_BLOWING_SUFFIX.length());

            return true;
        }
        return false;
    }
}