# carsproc
Simple Neo4j stored procedure - traverse and remove

1. clone the repo
2. `mvn clean package`
3. `cp ./target/carsproc-1.0-SNAPSHOT.jar YOUR_PLUGIN_DIRECTORY` (see note below for embedded neo4j plugin direcotry)
4. [re]start neo4j
5. populate some data: 
```create (a:Car{data:"I am a Car"})
create (b:Driver{data:"I am a Driver"})
create (c:Wheel{data:"I am a Wheel"})
create (d:Wheel{data:"I am a Wheel"})

with a,b,c,d
create (a)-[:RELATED_TO]->(b)
create (a)-[:RELATED_TO]->(c)
create (a)-[:RELATED_TO]->(d)
```

5. call the procedure: `MATCH (a:Car) CALL com.rgabay.carsproc(a) yield path RETURN path`

## Note on running this in the embedded mode:
1. deploy the procedure jar somewhere on your file system
2. tell neo4j to use that directory to look for the plugin (this config also exposes bolt on port 7688 so you can use the UI) :
```
graphDb = new GraphDatabaseFactory()
                .newEmbeddedDatabaseBuilder( DB_PATH )
                .setConfig( bolt.type, "BOLT" )
                .setConfig( bolt.enabled, "true" )
                .setConfig( bolt.address, "localhost:7688" )
                .setConfig(GraphDatabaseSettings.plugin_dir, PLUGIN_PATH)
                .newGraphDatabase();
```                
