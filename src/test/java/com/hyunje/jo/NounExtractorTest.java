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
        System.out.println(extractor.extract("트위터 형태소 분석기를 이용한 명사 추출 예시입니다."));
    }
}
