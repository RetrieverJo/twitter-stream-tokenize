package com.hyunje.jo;

/**
 * 명사 추출을 위한 클래스를 테스트.
 *
 * @author hyunje
 * @since 15. 1. 8.
 */
public class NounExtractorTest {
    public static void main(String[] args){
        NounExtractor extractor = new NounExtractor();
        System.out.println(extractor.extract("형태소분석기테스트클래스입니다.띄어쓰기가안되어있는경우에는어떤결과가나올까요?"));
    }
}
