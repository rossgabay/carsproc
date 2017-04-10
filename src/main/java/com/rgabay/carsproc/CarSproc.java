package com.rgabay.carsproc;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.traversal.TraversalDescription;

import org.neo4j.logging.Log;
import org.neo4j.procedure.*;

import java.util.stream.Stream;

public class CarSproc {

    @Context
    public GraphDatabaseService db;

    @Context
    public Log log;


    private static final CarExpander carExpander = new CarExpander();
    private static final CarEvaluator carEvaluator = new CarEvaluator();

    @Description("com.rgabay.carsproc(node) | deletes wheels")
    @Procedure(name = "com.rgabay.carsproc", mode = Mode.WRITE)
    public Stream<PathResult> carsproc( @Name("startNode") Node startNode) {

        TraversalDescription myTraversal = db.traversalDescription()
                .depthFirst()
                .expand(carExpander)
                .evaluator(carEvaluator);

        return  myTraversal.traverse(startNode).stream().map(PathResult::new);
    }

    public static class PathResult {
        public Path path;

        PathResult(Path path) {
            this.path = path;
        }
    }
}
