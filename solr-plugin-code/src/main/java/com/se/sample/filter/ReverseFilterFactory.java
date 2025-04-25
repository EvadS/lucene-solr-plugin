package com.se.sample.filter;

import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;


public class ReverseFilterFactory extends TokenFilterFactory {
    public ReverseFilterFactory(Map<String, String> args) {
        super(args);
        // TODO Auto-generated constructor stub
    }
    @Override
    public TokenStream create(TokenStream input) {
        return new ReverseCustomFilter(input);
    }
}