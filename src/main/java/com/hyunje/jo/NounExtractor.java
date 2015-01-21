package com.hyunje.jo;

import com.twitter.penguin.korean.TwitterKoreanProcessorJava;
import com.twitter.penguin.korean.tokenizer.KoreanTokenizer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 문장에서 명사만 추출하여 반환하는 클래스.
 *
 * @author hyunje
 * @since 15. 1. 8.
 */
public final class NounExtractor {
    private TwitterKoreanProcessorJava processor;

    public NounExtractor() {
        processor = new TwitterKoreanProcessorJava.Builder().build();
    }

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

    public List<String> extractFromJSON(String jsonString){
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray nouns = jsonObject.getJSONArray("noun");

        List<String> nounArray = new ArrayList<String>();
        for (int i = 0; i < nouns.length(); i++) {
            nounArray.add(nouns.getString(i));
        }
        return nounArray;
    }
}
