package com.fxgraph.graph;

import org.w3c.dom.Document;
//import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Info {
	public NodeList nListCell;
	public NodeList nListEdge;
	public Document doc;

	public Info(){
//		nListCell = new NodeList() {
//
//			@Override
//			public Node item(int index) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public int getLength() {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//		};
//		nListEdge = new NodeList() {
//
//			@Override
//			public Node item(int index) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public int getLength() {
//				// TODO Auto-generated method stub
//				return 0;
//			}
//		};

	}

	public Info(NodeList nListCell,NodeList nListEdge,Document doc){
		this.nListCell = nListCell;
		this.nListEdge = nListEdge;
		this.doc = doc;
	}

}
