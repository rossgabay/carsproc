package com.rgabay.carsproc;

import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.PathExpander;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.traversal.BranchState;



public class CarExpander implements PathExpander{

    @Override
    public Iterable<Relationship> expand(Path path, BranchState branchState) {
        return path.endNode().getRelationships();
    }

    @Override
    public PathExpander reverse() {
        return null;
    }
}
