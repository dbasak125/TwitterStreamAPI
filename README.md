# TwitterStreamAPI
Twitter Public Stream API data pull

A quick and dirty way to use the twitter4j library (wrapper for Twitter's Public Stream API) to fetch tweets in streaming mode and log them progressively in a json file.

Application uses OAuth for authentication while making the persistent HTTP connection with Twitter data stream.

Output .json file is further used by Append class to format json data for indexing in Apache Lucene/Solr.

*Please refer  http://twitter4j.org/en/code-examples.html to further experiment with available classes. Refer twitter4j JavaDocs at http://twitter4j.org/javadoc/index.html
