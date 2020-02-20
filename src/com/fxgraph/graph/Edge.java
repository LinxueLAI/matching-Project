package com.fxgraph.graph;

import javafx.scene.Group;
import javafx.scene.shape.Line;

public class Edge extends Group {

    protected Cell source;
    protected Cell target;

    Line line;

    public Edge(Cell source, Cell target,int n) {


        this.source = source;
        this.target = target;

        source.addCellChild(target);
        target.addCellParent(source);

        line = new Line();
        switch (n) {

        case 1:
        	line.getStrokeDashArray().addAll(2d, 16d);
            break;

        case 2:
        	line.getStrokeDashArray().addAll(25d, 10d);
            break;

        default:

        }
//        line.getStrokeDashArray().addAll(2d, 21d);
//        Line line1 = new Line(20, 40, 270, 40);
//        line.getStrokeDashArray().addAll(25d, 20d, 5d, 20d);
//        line.getStrokeDashArray().addAll(50d, 40d);
//        line.getStrokeDashArray().addAll(25d, 10d);
//        line.getStrokeDashArray().addAll(2d);
//        line.getStrokeDashArray().addAll(2d, 21d);

        line.startXProperty().bind( source.layoutXProperty().add(source.getBoundsInParent().getWidth() / 2.0));
        line.startYProperty().bind( source.layoutYProperty().add(source.getBoundsInParent().getHeight() / 2.0));

        line.endXProperty().bind( target.layoutXProperty().add( target.getBoundsInParent().getWidth() / 2.0));
        line.endYProperty().bind( target.layoutYProperty().add( target.getBoundsInParent().getHeight() / 2.0));

        getChildren().add(line);

    }
    public Cell getSource() {
        return source;
    }

    public Cell getTarget() {
        return target;
    }

}