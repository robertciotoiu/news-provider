#News feed project

##It contains two applications:
- ###News ingest application
<pre>
&nbsp;- Scheduled task.
&nbsp;- Polling news feed from: <a>http://feeds.nos.nl/nosjournaal?format=xml</a>
&nbsp;- Frequency: 5 minutes
&nbsp;- Save and update news
&nbsp;- PostgreSql database
</pre>  

&nbsp;Doc reference: https://spring.io/guides/gs/scheduling-tasks/

- ###News feed GraphQL API
<pre>
&nbsp;Endpoint:
&nbsp;- /v1/news 
</pre>

Doc reference: https://spring.io/projects/spring-graphql#learn