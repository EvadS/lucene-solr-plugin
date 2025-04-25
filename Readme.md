
# LuceneQParserPlugin

## custom plugin
to make plugin jar
move to solr plugin code directory and build with maven
```cmd
    mvn clean install
```
then move  **se-plugin.jar* from target directory to ${SOLR_HOME}\custom_lib folder

№№ check lib is working
${DOLR_HOME}\server\logs\solr.log


### Solr base command
#### run solr instance
```cmd
  .\solr.cmd start
```

#### create collection
```cmd
	.\solr.cmd create -c books
```

#### to check on local directories
```
	${SOLR_HOME}\server\solr\books
```

#### upload data
```cmd
	java -jar -Dc=books -Dauto post.jar books.csv
```

####check collection on web
```
	http://localhost:8983/solr/#/books/query
```

#### stop
```cmd
	solr stop -all
```
