package com.hyunje.jo;

import com.twitter.penguin.korean.TwitterKoreanProcessorJava;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 문장에서 명사만 추출하여 반환하는 클래스.
 * 혹은, JSON 형태로 되어 있는 명사의 리스트에서 JSON을 파싱하여 명사의 리스트를 반환한다.
 *
 * @author hyunje
 * @since 15. 1. 8.
 */
public final class NounExtractor {
    private final TwitterKoreanProcessorJava processor;
    private final String jsonObjectName = "noun";

    public NounExtractor() {
        processor = new TwitterKoreanProcessorJava.Builder().build();
    }

    /**
     * 문장에서 명사를 추출하여 반환한다.
     *
     * @param sentence 형태소 분석을 수행할 문장.
     * @return 명사의 리스트.
     */
    public List<String> extractFromSentence(String sentence) {
        List<KoreanTokenizer.KoreanToken> parsed = processor
                .tokenize(sentence);
        List<String> nouns = new ArrayList<String>();
        for (KoreanTokenizer.KoreanToken token : parsed) {
            if (token.pos().toString().equals("Noun") && token.text().length() > 1) {
                nouns.add(token.text());
            }
        }
        return nouns;
    }

    /**
     * JSON 형태의 문자열 명사만 가져와 반환한다.
     * {"noun":["나라","걱정","얘기"]} 형태의 JSON을 파싱
     *
     * @param jsonString Json 형태의 문자열.
     * @return 파싱된 명사의 리스트.
     */
    public List<String> extractFromJSON(String jsonString){
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray nouns = jsonObject.getJSONArray(jsonObjectName);

        List<String> nounArray = new ArrayList<String>();
        for (int i = 0; i < nouns.length(); i++) {
            nounArray.add(nouns.getString(i));
        }
        return nounArray;
    }
}
