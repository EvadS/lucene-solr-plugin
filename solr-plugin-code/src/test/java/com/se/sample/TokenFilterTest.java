package com.se.sample;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.BaseTokenStreamTestCase;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

public class TokenFilterTest extends BaseTokenStreamTestCase {

    @Test
    public void shouldNotAlterKeywordAnalyzed() throws IOException {
        Analyzer myKeywordAnalyzer = new KeywordAnalyzer();

        assertTokenStreamContents(
                myKeywordAnalyzer.tokenStream("my_keyword_field", new StringReader("ISO8859-1 and all that jazz")), new String[] {
                        "ISO8859-1 and all that jazz"
                });

        assertAnalyzesTo(myKeywordAnalyzer, "ISO8859-1 and all that jazz", new String[] {
                "ISO8859-1 and all that jazz" // a single token output as expected from the KeywordAnalyzer
        });
    }
}