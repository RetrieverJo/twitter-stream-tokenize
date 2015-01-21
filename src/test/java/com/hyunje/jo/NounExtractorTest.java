package com.hyunje.jo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NounExtractorTest {
    NounExtractor extractor;

    @Before
    public void setUp() throws Exception {
        extractor = new NounExtractor();
    }

    @Test
    public void testExtractFromSentence() throws Exception {
        List<String> nouns = extractor.extractFromSentence("트위터에서공개한형태소분석기테스트입니다.띄어쓰기가안되어있을때에도잘분석해줍니다.이렇게형태소분석을사용하면됩니다.");
        Assert.assertNotNull(nouns);

    }

    //{"noun":["나라","걱정","얘기"]} 형태의 JSON을 파싱
    @Test
    public void testExtractFromJSON() throws Exception {
        final String testJSON = "{\"noun\":[\"나라\",\"걱정\",\"얘기\"]}";
        final List<String> answer = new ArrayList<String>();
        answer.add("나라");answer.add("걱정");answer.add("얘기");

        List<String> extractedResult = extractor.extractFromJSON(testJSON);

        Assert.assertEquals(answer.size(),extractedResult.size());
        for(int i=0;i<answer.size();i++){
            Assert.assertTrue(answer.get(i).equals(extractedResult.get(i)));
        }
    }
}