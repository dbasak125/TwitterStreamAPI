/** Sample Twitter data-scraper
 *  Stream API Documentation: from https://dev.twitter.com/rest/public/search
 *  Reference: twitter4j code examples from http://twitter4j.org/en/code-examples.html
 *
 *
 *  @author dbasak
 *  @date 09/15/2016
 *  
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Stream {
    public static void main(String[] args) throws TwitterException {
    
    ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setDebugEnabled(true);
    cb.setOAuthConsumerKey("9999999"); //sample key values. substitute your own here.
    cb.setOAuthConsumerSecret("99999999999");
    cb.setOAuthAccessToken("78999999");
    cb.setOAuthAccessTokenSecret("Ahjgjhgjhghjgjg");

    TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
    StatusListener listener = new StatusListener() {

        public void onStatus(Status status) {
            System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            try(PrintStream out = new PrintStream(new FileOutputStream("./json.json", true))){
            	try {
    				long id;
    				id = status.getRetweetedStatus().getId();
    			} catch (NullPointerException ex) {
    				out.print("," + TwitterObjectFactory.getRawJSON(status) + "\n"); //check if retweet, and log status json in output file
    			}
            	
            } catch (FileNotFoundException ex) {
            	System.exit(-1);
            }
        }

        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
        }

        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
        }

        public void onScrubGeo(long userId, long upToStatusId) {
            System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
        }

        public void onException(Exception ex) {
            ex.printStackTrace();
        }

		@Override
		public void onStallWarning(StallWarning arg0) {
			// TODO Auto-generated method stub
			
		}
    };

    FilterQuery fq = new FilterQuery();
    String keywords[] = {"usopen", "Apple event", "iphone7", "game of thrones", "wawrinka"}; //keywords for Public stream API filter

    fq.track(keywords);
    fq.language("ko","tr","en","es"); //language filter. ISO 639-1 language codes.

    twitterStream.addListener(listener);
    twitterStream.filter(fq);      
    

}}