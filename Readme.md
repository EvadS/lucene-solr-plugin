
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


### Удаленная отладка 
на примере solr в данном проекте windos систем 

расположение 
lucene-solr-plugin/solr-8.11.4

заходим в bin 
находим файл solr.in.cmd и в конце дописываем 
```
set SOLR_OPTS=%SOLR_OPTS% -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005
```
Параметр -Xdebug в контексте Solr используется для включения удалённой отладки. Она приостановится в ожидании подключения отладчика ( suspend=y).

After saving the changes, start Solr using the solr control script:
```
	.\solr.cmd start
```

Configure your IDE (e.g., IntelliJ IDEA, Eclipse) to connect to the remote Java application on the specified port (e.g., 5005). You can then set breakpoints in your Solr code and debug it remotely.


Следующий скрипт нужно проверить 
```
$~/workspace/lucene-solr/solr>./bin/solr -f -a "-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=4044" -s /home/doug/workspace/solr_home/
```
