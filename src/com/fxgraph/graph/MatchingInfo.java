package com.fxgraph.graph;
import java.io.BufferedReader;
import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class MatchingInfo {
	TextArea textarea;

    public void display(String title , BufferedReader reader) throws IOException{
    	Stage window = new Stage();
    	textarea = new TextArea();
    	window.setTitle(title);
    	//modality要使用Modality.APPLICATION_MODEL
    	window.initModality(Modality.APPLICATION_MODAL);
    	window.setMinWidth(300);
    	window.setMinHeight(150);

    	Button button = new Button("Close the Window");
    	button.setOnAction(e -> window.close());

    	//    Label label = new Label(message);

    	VBox layout = new VBox(10);
    	String line;
    	textarea.appendText("Relations:");
    	textarea.appendText("\n");
    	while ((line = reader.readLine()) != null)
    	{
    		System.out.println(line);
    		textarea.appendText(line);
    		textarea.appendText("\n");
    	}
    	reader.close();
    	layout.getChildren().addAll(textarea, button);
    	layout.setAlignment(Pos.CENTER);
    	Scene scene = new Scene(layout);
    	window.setScene(scene);
    	//使用showAndWait()先处理这个窗口，而如果不处理，main中的那个窗口不能响应
    	window.showAndWait();
    	}
}