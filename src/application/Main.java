package application;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.fxgraph.graph.CellType;
import com.fxgraph.graph.Graph;
import com.fxgraph.graph.Info;
import com.fxgraph.graph.MatchingInfo;
import com.fxgraph.graph.Model;
import com.fxgraph.graph.NodeNumber;
import com.fxgraph.graph.ReadXML;
import com.fxgraph.layout.base.Layout;
import com.fxgraph.layout.random.RandomLayout;
import com.fxgraph.layout.random.RightLayout;

public class Main extends Application {
//	private static Stage primaryStage;
	private Stage primaryStage;
	private Text actionStatus;

    Graph graph1 = new Graph();
    NodeNumber nb = new NodeNumber();

    @Override
    public void start(Stage primaryStage) {
    	this.primaryStage = primaryStage;
        this.primaryStage.setTitle("GraphMatchingApp");
     // Set the application icon.
        this.primaryStage.getIcons().add(new Image("file:resource/image/address_book32.png"));

        BorderPane root = new BorderPane();

        graph1 = new Graph();
        nb = new NodeNumber();
        actionStatus = new Text();
        root.setCenter(graph1.getScrollPane());
        MenuBar menubar = new MenuBar();

        root.setTop(menubar);

        Menu menu1 = new Menu("File");
        MenuItem item1 = new MenuItem("Load Graph1");
        MenuItem item2 = new MenuItem("Load Graph2");
        MenuItem item7 = new MenuItem("Clear All");
        MenuItem item8 = new MenuItem("Exit");

        menu1.getItems().addAll(item1,item2,item7,item8);

        Menu menu2 = new Menu("Matching");
        MenuItem item3 = new MenuItem("Show Matching Relation...");
        MenuItem item4 = new MenuItem("Save As JPG");
        MenuItem item5 = new MenuItem("Save As PNG");
        menu2.getItems().addAll(item3,item4,item5);

        Menu menu3 = new Menu("Help");
        MenuItem item6 = new MenuItem("More Information...");

        menu3.getItems().add(item6);

        menubar.getMenus().addAll(menu1,menu2,menu3);
        menubar.setPrefWidth(root.getWidth());

        //root.getChildren().add(menubar);

        //单击事件：Load Graph1
        item1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
//				System.out.println();
				 FileChooser fileChooser = new FileChooser();
			        // Set extension filter
			        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
			                "XML files (xml)", "*.xml");//这里可以改格式的
			        fileChooser.getExtensionFilters().add(extFilter);
			        // Show save file dialog
//			        File file = fileChooser.showOpenDialog(Main.getPrimaryStage());
			        File file = fileChooser.showOpenDialog(getPrimaryStage());
			        if (file != null) {
			        	loadPersonData1FromFile(file);
//			        	Main.addGraphComponents(file);
			            Layout layout1 = new RandomLayout(graph1);
			            layout1.execute(0);
			            System.out.println("--------------------");
			        }
			}
		});
        //单击事件：Load Graph2
        item2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
//				System.out.println();
				 FileChooser fileChooser = new FileChooser();
			        // Set extension filter
			        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
			                "XML files (xml)", "*.xml");//这里可以改格式的
			        fileChooser.getExtensionFilters().add(extFilter);
			        // Show save file dialog
//			        File file = fileChooser.showOpenDialog(Main.getPrimaryStage());
			        File file = fileChooser.showOpenDialog(getPrimaryStage());
			        if (file != null) {
			        	int n;
			            n = loadPersonData2FromFile(file);
//			        	Main.addGraphComponents(file);
			            Layout layout2 = new RightLayout(graph1);
			            layout2.execute(n);
			            System.out.println("--------------------");
			        }

			}
		});
        //单击事件：Show Matching result = add the lines
        item3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					showSingleFileChooser();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
        });
        //单击事件：Save the whole Graph As JPG image
        item4.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent arg0) {
        		 FileChooser fileChooser = new FileChooser();

        		    //Set extension filter
        		    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg"));

        		    //Prompt user to select a file
        		    File file = fileChooser.showSaveDialog(null);
        		    if(file != null){
        		        try {
        		            //Pad the capture area
        		            WritableImage writableImage = new WritableImage((int)graph1.getScrollPane().getWidth(),
        		                    (int)graph1.getScrollPane().getHeight());
        		            graph1.getScrollPane().snapshot(null, writableImage);
        		            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
        		            //Write the snapshot to the chosen file
        		            ImageIO.write(renderedImage, "jpg", file);
        		        } catch (IOException ex) { ex.printStackTrace(); }
        		    }
        	}
        });

        //单击事件：Save the whole Graph As PNG image
        item5.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent arg0) {
        		 FileChooser fileChooser = new FileChooser();

        		    //Set extension filter
        		    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));

        		    //Prompt user to select a file
        		    File file = fileChooser.showSaveDialog(null);

        		    if(file != null){
        		        try {
        		            //Pad the capture area
        		            WritableImage writableImage = new WritableImage((int)graph1.getScrollPane().getWidth(),
        		                    (int)graph1.getScrollPane().getHeight());
        		            graph1.getScrollPane().snapshot(null, writableImage);
        		            RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
        		            //Write the snapshot to the chosen file
        		            ImageIO.write(renderedImage, "png", file);
        		        } catch (IOException ex) { ex.printStackTrace(); }
        		    }
        	}
        });

        //单击事件：Search more information On the Internet
        item6.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				HostServices host = getHostServices();
				host.showDocument("www.baidu.com");
			}

		});

        //单击事件：Clear All
        item7.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				graph1 = new Graph();
				root.setCenter(graph1.getScrollPane());
				nb.n = 0;
			}
		});

        //单击事件：Close and Exit
        item8.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				primaryStage.close();
			}

		});

        Scene scene = new Scene(root, 1000, 700);
//        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
//        File file = new File("C:/Users/test1.xml");
//        addGraphComponents(file);
//        Layout layout = new RandomLayout(graph);
//        layout.execute();

    }

	private void showSingleFileChooser() throws IOException {
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(null);

		if (selectedFile != null) {

			actionStatus.setText("File selected: " + selectedFile.getName());
			BufferedReader reader1 = new BufferedReader(new FileReader(selectedFile));
			BufferedReader reader2 = new BufferedReader(new FileReader(selectedFile));
			String line,nom1,nom2;
		    //AddEdge
		    Model model = graph1.getModel();
	        graph1.beginUpdate();

//	        nb.n = nb.getNB(nlist1.getLength())-1;
//	        System.out.println("nb.n = "+nb.n);
//	        for (int temp = 0; temp < nlist1.getLength(); temp++) {
	        while ((line = reader2.readLine()) != null){
	        	nom1 = line.substring(0, 6);
	        	nom2 = line.substring(13);

	        	System.out.println("nom1 = "+nom1);
	        	System.out.println("nom2 = "+nom2);
	        	System.out.println(nom1.length());
	        	System.out.println(nom2.length());
	        	model.addEdge(nom1,nom2,1);
	        	}
	        graph1.endUpdate();
	        new MatchingInfo().display("Matching informations", reader1);
	        reader2.close();

		}

	}

	public int loadPersonData2FromFile(File file) {
		System.out.println("----------------------------");//for test the result
		Model model = graph1.getModel();
        graph1.beginUpdate();
        ReadXML reader = new ReadXML();
//        File file = new File("C:/Users/test1.xml");
        Info info =reader.getList(file);
        NodeList nlist1 = info.nListCell;
        //NodeNumber nb = new NodeNumber();
        nb.n = nb.getNB(nlist1.getLength())-1;
        System.out.println("nb.n = "+nb.n);
        for (int temp = 0; temp < nlist1.getLength(); temp++) {

        		Node nNode1 = nlist1.item(temp);
        		System.out.println("\nCurrent Element :" + nNode1.getNodeName());
        		if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
        			Element eElement1 = (Element) nNode1;
        			String cellname = eElement1.getElementsByTagName("name").item(0).getTextContent();
        			String celltype = eElement1.getElementsByTagName("type").item(0).getTextContent();
        			String type1 =new String("rectangle");
        			String type2 =new String("triangle");
        			String type3 = new String("Label");
        			if(celltype.equals(type1))
        			{
        				model.addCell(cellname,CellType.RECTANGLE);
            			System.out.println("cellid : " + eElement1.getElementsByTagName("name").item(0).getTextContent());
//            			System.out.println("cellnamelength = "+cellname.length());
            			System.out.println("type : " + eElement1.getElementsByTagName("type").item(0).getTextContent());
        			}
        			else if(celltype.equals(type2)){
        				model.addCell(eElement1.getElementsByTagName("name").item(0).getTextContent(), CellType.TRIANGLE);
        				System.out.println("cellid : " + eElement1.getElementsByTagName("name").item(0).getTextContent());
        				System.out.println("type : " + eElement1.getElementsByTagName("type").item(0).getTextContent());
        			}
        			else if(celltype.equals(type3)){
        				model.addCell(eElement1.getElementsByTagName("name").item(0).getTextContent(), CellType.LABEL);
        				System.out.println("cellid : " + eElement1.getElementsByTagName("name").item(0).getTextContent());
        				System.out.println("type : " + eElement1.getElementsByTagName("type").item(0).getTextContent());
        			}
        		}
        	}
        	NodeList nList2 = info.nListEdge;
			for (int temp = 0; temp < nList2.getLength(); temp++) {
        		Node nNode2 = nList2.item(temp);

        		System.out.println("\nCurrent Element :" + nNode2.getNodeName());

        		if (nNode2.getNodeType() == Node.ELEMENT_NODE) {

        			Element eElement2 = (Element) nNode2;
        			String sourceid = eElement2.getElementsByTagName("sourceid").item(0).getTextContent();
        			String targetid = eElement2.getElementsByTagName("targetid").item(0).getTextContent();
        			model.addEdge(sourceid,targetid,0);
        			System.out.println("sourceid : " + eElement2.getElementsByTagName("sourceid").item(0).getTextContent());
        			System.out.println("targetid : " + eElement2.getElementsByTagName("targetid").item(0).getTextContent());
        		}
        	}
        graph1.endUpdate();

		return nb.n;
	}

	public void loadPersonData1FromFile(File file) {
//        addGraph1Components(file);
		System.out.println("----------------------------");//for test the result
		Model model = graph1.getModel();
        graph1.beginUpdate();
        ReadXML reader = new ReadXML();
//        File file = new File("C:/Users/test1.xml");
        Info info =reader.getList(file);
        NodeList nlist1 = info.nListCell;
        //NodeNumber nb = new NodeNumber();
//        nb.n = nb.getNB(nlist1.getLength());
        for (int temp = 0; temp < nlist1.getLength(); temp++) {

        		Node nNode1 = nlist1.item(temp);
        		System.out.println("\nCurrent Element :" + nNode1.getNodeName());
        		if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
        			Element eElement1 = (Element) nNode1;
        			String cellname = eElement1.getElementsByTagName("name").item(0).getTextContent();
        			String celltype = eElement1.getElementsByTagName("type").item(0).getTextContent();
        			String type1 =new String("rectangle");
        			String type2 =new String("triangle");
        			String type3 =new String("Label");
        			if(celltype.equals(type1))
        			{
        				model.addCell(cellname,CellType.RECTANGLE);
        				System.out.println("cellnamelength = "+cellname.length());
        				System.out.println("cellid : " + eElement1.getElementsByTagName("name").item(0).getTextContent());

            			System.out.println("type : " + eElement1.getElementsByTagName("type").item(0).getTextContent());
        			}
        			else if(celltype.equals(type2)){
        				model.addCell(eElement1.getElementsByTagName("name").item(0).getTextContent(), CellType.TRIANGLE);
        				System.out.println("cellid : " + eElement1.getElementsByTagName("name").item(0).getTextContent());
        				System.out.println("type : " + eElement1.getElementsByTagName("type").item(0).getTextContent());
        			}
        			else if(celltype.equals(type3)){
        				model.addCell(eElement1.getElementsByTagName("name").item(0).getTextContent(), CellType.LABEL);
        				System.out.println("cellid : " + eElement1.getElementsByTagName("name").item(0).getTextContent());
        				System.out.println("type : " + eElement1.getElementsByTagName("type").item(0).getTextContent());
        			}
        		}
        	}
        	NodeList nList2 = info.nListEdge;
			for (int temp = 0; temp < nList2.getLength(); temp++) {
        		Node nNode2 = nList2.item(temp);

        		System.out.println("\nCurrent Element :" + nNode2.getNodeName());

        		if (nNode2.getNodeType() == Node.ELEMENT_NODE) {

        			Element eElement2 = (Element) nNode2;
        			String sourceid = eElement2.getElementsByTagName("sourceid").item(0).getTextContent();
        			String targetid = eElement2.getElementsByTagName("targetid").item(0).getTextContent();
        			model.addEdge(sourceid,targetid,0);
        			System.out.println("sourceid : " + eElement2.getElementsByTagName("sourceid").item(0).getTextContent());
        			System.out.println("targetid : " + eElement2.getElementsByTagName("targetid").item(0).getTextContent());
        		}
        	}
        graph1.endUpdate();
//        return nb.n;
	}
	/**
     * Returns the main stage.
     * @return
     */
//    public static Stage getPrimaryStage() {
//        return primaryStage;
//    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}