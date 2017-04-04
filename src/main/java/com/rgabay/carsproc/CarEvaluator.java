package com.rgabay.carsproc;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.traversal.BranchState;
import org.neo4j.graphdb.traversal.Evaluation;
import org.neo4j.graphdb.traversal.PathEvaluator;


public class CarEvaluator implements PathEvaluator{

    private Label deleteLabel = Label.label("Wheel");

    @Override
    public Evaluation evaluate(Path path, BranchState branchState) {

        if (path.endNode().hasLabel(deleteLabel)) {

            // delete rels
            for(Relationship rel :path.endNode().getRelationships()) rel.delete();

            // delete the node
            path.endNode().delete();

            return Evaluation.EXCLUDE_AND_PRUNE;
        }
            return Evaluation.INCLUDE_AND_CONTINUE;
    }

    @Override
    public Evaluation evaluate(Path path) {
        return null;
    }
}
