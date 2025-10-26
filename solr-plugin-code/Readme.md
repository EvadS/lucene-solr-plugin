# Solr plugins  learning

 [Apache Lucene](https://lucene.apache.org/core/) is a Java library used for full-text searching of documents, and is at the core of search servers like [Solr](http://lucene.apache.org/solr/) and [Elasticsearch](https://www.elastic.co/products/elasticsearch). It can also be embedded into Java applications, such as Android apps or web back-ends.

## Dependencies
 * java 11

##  source article
 [Full Text Search of Dialogues with Apache Lucene: A Tutorial] https://www.toptal.com/database/full-text-search-of-dialogues-with-apache-lucene

для того чтобы фильтры токенизаторы работали необходимо указать их в папке /WEB_INF

тут написано о custom_lib
https://medium.com/@akshayt030/developing-custom-filter-plugin-in-lucene-solr-8-63cb099036cc

поле
text_general
...
<filter class="com.se.sample.filter.ReverseFilterFactory"/>
...
```