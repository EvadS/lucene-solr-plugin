package com.se.sample.filter;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;

import java.util.Map;

public class MindBlowingTokenFilterFactory extends TokenFilterFactory {
    public MindBlowingTokenFilterFactory(Map<String, String> args) {
        super(args);
    }

    public MindBlowingTokenFilter create(TokenStream input) {
        return new MindBlowingTokenFilter(input);
    }
}