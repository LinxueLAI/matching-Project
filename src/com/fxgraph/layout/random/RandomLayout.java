package com.fxgraph.layout.random;

import java.util.List;
import java.util.Random;

import com.fxgraph.graph.Cell;
import com.fxgraph.graph.Graph;
import com.fxgraph.layout.base.Layout;

//import javafx.scene.control.Label;

public class RandomLayout extends Layout {

    Graph graph;

    Random rnd = new Random();

    public RandomLayout(Graph graph) {

        this.graph = graph;

    }

    public void execute(int n) {
    	int i = 0;
    	n = 0;

        List<Cell> cells = graph.getModel().getAllCells();

        for (Cell cell : cells) {
//        	Label l = new Label(cell.getId());
//
            double x = rnd.nextDouble() * 400;
            double y = rnd.nextDouble() * 500;
//            System.out.println(x/500);
//            System.out.println(y/500);
//        	double x = 50 +i*50;
//        	double y = 50 +i*50;
//            System.out.println();
//            l.setLayoutX(x);
//            l.setLayoutY(y);
        	i = i+1;

            cell.relocate(x, y);//可以获知生成图的位置，以左上角为准
            System.out.println(cell.cellId);
            System.out.println("x = ");
            System.out.println(x);
            System.out.println("y = ");
            System.out.println(y);
            System.out.println("--------------------");

        }

    }

}