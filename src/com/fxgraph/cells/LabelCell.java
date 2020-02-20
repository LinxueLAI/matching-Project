
package com.fxgraph.cells;

import javafx.scene.control.Label;
//import javafx.scene.shape.Circle;

import com.fxgraph.graph.Cell;

public class LabelCell extends Cell {

    public LabelCell(String id) {
        super(id);

        //Circle circle = new Circle(10);

        Label view = new Label(id);
//        circle.set
        setView(view);

    }

}