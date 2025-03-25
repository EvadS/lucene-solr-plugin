
# LuceneQParserPlugin

## working with solr 

### start 

```cmd
  solr start
```

### create collection 
```cmd
	solr create -c books
```

### to check on local directoris 
```
	${SOLR_HOME}\server\solr\books
```

### upload data 
```cmd
	java -jar -Dc=books -Dauto post.jar book.csv
```

### check collecion by web 
```
	http://localhost:8983/solr/#/books/query
```

### stop 
```cmd
	solr stop -all
```

```cmd
	solr stop -p 8983
```

## custom plugin 

### put 