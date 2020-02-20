package com.fxgraph.layout.random;

import java.util.List;
import java.util.Random;

import com.fxgraph.graph.Cell;
import com.fxgraph.graph.Graph;
//import com.fxgraph.graph.NodeNumber;
import com.fxgraph.layout.base.Layout;

public class RightLayout extends Layout {
    Graph graph;

    Random rnd = new Random();

//    NodeNumber n;

    public RightLayout(Graph graph) {

        this.graph = graph;

    }

    public void execute(int n) {
    	int i = 1;
//    	n.n = n.getNB(4);
    	System.out.println("n = "+n);

        List<Cell> cells = graph.getModel().getAllCells();


        for (Cell cell : cells) {
        	if(i>n){

            double x = 500 + rnd.nextDouble() * 400;
            double y = rnd.nextDouble() * 500;
//            	double x = 250 +i*50;
//            	double y = 50 +i*50;
//                System.out.println();
//                l.setLayoutX(x);
//                l.setLayoutY(y);
//            	i = i+1;
//            System.out.println();
//            l.setLayoutX(x);
//            l.setLayoutY(y);
            cell.relocate(x, y);//可以获知生成图的位置，以左上角为准
            System.out.println(cell.cellId);
            System.out.println("x = ");
            System.out.println(x);
            System.out.println("y = ");
            System.out.println(y);
//            System.out.println("--------------------");
        	}
        	i=i+1;
        }

    }
}
