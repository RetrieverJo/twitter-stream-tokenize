package com.hyunje.jo;

import org.fluentd.logger.FluentLogger;
import twitter4j.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * 한국지역내에서 발생하는 트위터의 스트림을 받아 형태소 분석을 수행하여, Hadoop 으로 전송합니다.
 *
 * @author hyunje
 * @since 15. 1. 8.
 */
public class TweetSampleStream {
    private static FluentLogger Logger;
    static{
        Properties prop = new Properties();
        InputStream input;
        try {
            input = new FileInputStream("fluentd.properties");
            prop.load(input);

            String host = prop.getProperty("host");
            String port = prop.getProperty("port");

            Logger = FluentLogger.getLogger("twitter",host,Integer.parseInt(port));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static FilterQuery getBoundingBoxFilter() {
        //Korea
        double lat = 36.3;
        double lon = 127.9;
        double d = 2;
        double lon1 = lon - d;
        double lon2 = lon + d;
        double lat1 = lat - d;
        double lat2 = lat + d;

        double bbox[][] = {{lon1, lat1}, {lon2, lat2}};
        FilterQuery filterQuery = new FilterQuery();
        return filterQuery.locations(bbox);
    }

    /**
     * Main entry of this application.
     *
     * @param args arguments doesn't take effect with this example
     */
    public static void main(String[] args) throws TwitterException, IOException {
        TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
        FilterQuery filter = getBoundingBoxFilter();

        final NounExtractor extractor = new NounExtractor();
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                List<String> extracted = extractor.extractFromSentence(status.getText());
                if(extracted.size() != 0) {
                    System.out.println("Nouns : " + extracted);
                    Logger.log("text", "noun", extracted);
                }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
            }

            @Override
            public void onStallWarning(StallWarning warning) {
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };

        twitterStream.addListener(listener);
        twitterStream.filter(filter);
    }
}
