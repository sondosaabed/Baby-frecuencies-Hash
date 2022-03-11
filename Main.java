import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner; 
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application{
	public static ArrayList<FileObj> listOfFiles = new ArrayList<>();
	public static Hash hashTable = new Hash(); 
	public static FileChooser fileChooser;  
	public static File file;
	
	public static void main(String[] args) {
		launch(args);
	}
 
	@SuppressWarnings("unused")
	@Override
	public void start(Stage stage) throws Exception { 
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(130,130,130,130));
		pane.setHgap(10.5);
		pane.setVgap(10.5); 
		
		BackgroundFill fill = new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY); 
		Background background = new Background(fill);  
		
		BackgroundFill fill1 = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY); 
		Background background1 = new Background(fill1);  
		pane.setBackground(background1);
		
		Label hello = new Label("Hello user:");
		hello.setFont(Font.font(16));  
		hello.setTextFill(Color.BROWN );
		pane.add(hello , 0, 0);
		
		Label process = new Label("Your process...");
		process.setFont(Font.font(12));  
		process.setTextFill(Color.RED );
		pane.add(process , 0, 1);
		
		Button add = new Button("Add file"); 
		add.setFont(Font.font(14)); 
		add.setBackground(background);
		add.setTextFill(Color.DARKGREEN);
		add.setPrefSize(100, 50);
		pane.add(add , 0, 2);
		
		add.setOnAction(e -> {
			Stage stage2 = new Stage();
			GridPane pane2 = new GridPane();
			pane2.setPadding( new Insets( 100,130, 100,130 ));
			pane2.setBackground(background1); 
            pane2.setAlignment(Pos.CENTER); 
    		pane2.setHgap(15.5);
    		pane2.setVgap(15.5); 
    		
    		Label yearl = new Label("Chose a year: ");
    		yearl.setFont(Font.font(16));  
    		yearl.setTextFill(Color.BROWN );
    		pane2.add(yearl , 0, 0);
    		
    		Label filel = new Label("Chose a file: ");
    		filel.setFont(Font.font(16));  
    		filel.setTextFill(Color.BROWN );
    		pane2.add(filel , 0, 1);
    		
    		TextField yearT = new TextField(); 
    		yearT.setFont(Font.font(14));
    		yearT.setPrefWidth(50);
    		pane2.add(yearT, 1, 0);
    		
    		Button btBr = new Button("Browse");
    		btBr.setTextFill(Color.BLACK);
    		btBr.setFont(Font.font(16));
    		GridPane.setHalignment(btBr, HPos.CENTER);
    		btBr.setBackground(background);
	    	pane2.add(btBr, 1, 1); 
	    	 
	    	btBr.setOnAction(d-> { 
	    		Stage stage3 = new Stage();
	    		fileChooser = new FileChooser();
	    		file=fileChooser.showOpenDialog(stage3);  
	    	});
	    	 
    		Button btAd = new Button("Add");
    		btAd.setTextFill(Color.DARKGREEN);
    		btAd.setFont(Font.font(16));
    		GridPane.setHalignment(btAd, HPos.CENTER);
	    	btAd.setBackground(background);
	    	pane2.add(btAd, 0, 3); 
	    	
	    	Label answer= new Label();
			answer.setFont(Font.font(14));
			answer.setTextFill(Color.RED);
		   	pane2.add(answer,0,2);
	    	
		   	btAd.setOnAction(d-> {  
	    		if(yearT.getText().isBlank() ||fileChooser==(null) ) { 
				   	answer.setText("Enter value");
	    		} else{
	    			try { 
	    				int c =Integer.parseInt( yearT.getText().trim());
			    		FileObj fi= new FileObj(file,yearT.getText().trim());
			    		listOfFiles.add(fi);
			    		process.setText("File added");
			    		stage2.close();
	    			}catch(NumberFormatException t) {
	    				answer.setText("Enter numbers");
	    			}
	    		}
	    	});
	    	
    		Button btCancel = new Button("Cancel");
	    	btCancel.setTextFill(Color.DARKGREEN);
	    	btCancel.setFont(Font.font(16));
	    	btCancel.setBackground(background);
	    	pane2.add(btCancel, 1, 3);  
	    	
	    	btCancel.setOnAction(d-> {
	    		stage2.close();
	    	});
	    	
	    	Scene scene = new Scene(pane2); 
  		
	    	stage2.setTitle("Add File");
	    	stage2.setScene(scene);  
	    	stage2.show(); 
		});
		
		Button run = new Button("Run");
		run.setFont(Font.font(14)); 
		run.setTextFill(Color.DARKGREEN);
		run.setPrefSize(100, 50);
		run.setBackground(background );
		pane.add(run, 1, 2);
		
		run.setOnAction(a-> {
			if(listOfFiles.isEmpty() ) {
				process.setText("Add at least 1 file");
			}else {  
				readFiles(listOfFiles);
				System.out.println(listOfFiles.toString());
				Stage stage1 = new Stage();
				GridPane pane1 = new GridPane();
				pane1.setBackground(background1); 
	            pane1.setAlignment(Pos.CENTER); 
	    		pane1.setHgap(10);
	    		pane1.setVgap(10); 
    		
	    		Button addd = new Button();
	    		addd.setTextFill(Color.BROWN); 
	    		addd.setPrefSize(200, 200); 
	    		addd.setGraphic(new ImageView(new Image("Add.png")));
	    		addd.setBackground(background );
	    		addd.setOnAction(k-> {
	    			Stage stage2 = new Stage();
	    			GridPane pane2 = new GridPane();
	    			pane2.setPadding( new Insets( 100,130, 100,130 ));
	    			pane2.setBackground(background1); 
	                pane2.setAlignment(Pos.CENTER); 
	        		pane2.setHgap(15.5);
	        		pane2.setVgap(15.5);  
        		
	        		Button name = new Button();
	        		name.setTextFill(Color.BROWN); 
	        		name.setPrefSize(200, 200); 
	        		name.setGraphic(new ImageView(new Image("Name.png")));
	        		name.setBackground(background );
	        		name.setOnAction(x->{
	        			Stage stage3 = new Stage();
		    			GridPane pane3 = new GridPane();
		    			pane3.setPadding( new Insets( 100,130, 100,130 ));
		    			pane3.setBackground(background1); 
		                pane3.setAlignment(Pos.CENTER); 
		        		pane3.setHgap(15.5);
		        		pane3.setVgap(15.5); 
	        		
		        		Label namel= new Label(("Name of the baby:"));
				    	namel.setFont(Font.font(20));
				    	pane3.add(namel,0,0);  
				    	
				    	Label genderl= new Label(("Gender of the baby:"));
				    	genderl.setFont(Font.font(20));
				    	pane3.add(genderl, 0, 1); 
		        	  
				    	Label frecuencyl= new Label(("Frecuency of the baby:"));
				    	frecuencyl.setFont(Font.font(20));
				    	pane3.add(frecuencyl, 0, 2); 
				    	
				    	Label yearl= new Label(("Year of the frecuency:"));
				    	yearl.setFont(Font.font(20));
				    	pane3.add(yearl, 0, 3); 
			    	 
				    	TextField name1 = new TextField();
				    	name1.setFont(Font.font(16));
				    	name1.setPrefSize(150, 30);
				    	pane3.add(name1, 1, 0);  
				    	
				    	ComboBox<String> comboBox = new ComboBox<String>();   
				    	comboBox.getItems().add("Female") ;
				    	comboBox.getItems().add("Male"); 
				    	comboBox.setBackground(background);
				    	comboBox.setPrefSize(150, 30);
				    	pane3.add(comboBox, 1, 1);  
				    	 
				    	TextField frec1 = new TextField();
				    	frec1.setFont(Font.font(16));
				    	frec1.setPrefSize(150, 30);
				    	pane3.add(frec1, 1, 2);   
				    	
				    	ComboBox<String> comboBox1 = new ComboBox<String>();  
				    	for(int i=0; i<listOfFiles.size(); i++)
				    		comboBox1.getItems().add(listOfFiles.get(i).f.format(listOfFiles.get(i).getYear())) ; 
				    	comboBox1.setBackground(background);
				    	comboBox1.setPrefSize(150, 30);
				    	pane3.add(comboBox1, 1, 3);    
			    	
				    	Button cancel = new Button("Cancel");
		        		cancel.setTextFill(Color.BROWN);  
		        		cancel.setFont(Font.font(16));
				    	GridPane.setHalignment(cancel, HPos.CENTER);
		        		cancel.setBackground(background );
		        		pane3.add(cancel, 1,5);
		        		cancel.setOnAction(y->{
		        			stage3.close();
		        		});
			    	 
			    	  Button btAdd = new Button("Add");
			    	  btAdd.setTextFill(Color.BROWN);
			    	  btAdd.setFont(Font.font(16));
			    	  GridPane.setHalignment(  btAdd, HPos.CENTER);
			    	  btAdd.setBackground(background);
			    	  pane3.add(btAdd, 0, 5);
			    	  btAdd.setOnAction(o-> {
			    		  
			    		  if(comboBox.getSelectionModel().getSelectedItem()== null ||name1.getText().isEmpty()|| comboBox1.getSelectionModel().getSelectedItem()== null|| frec1.getText().isEmpty()) {
			    			  Label answer= new Label();
			    			  answer.setTextFill(Color.RED);
						      answer.setFont(Font.font(16));
						      pane3.add(answer,0,4);  
			    			  answer.setText("You must enter all values");
			    		  } else {
			    			  Stage stage4 = new Stage();
			    			  GridPane pane4 = new GridPane();
			    			  pane4.setPadding( new Insets( 100,130, 100,130 ));
			    			  pane4.setBackground(background1); 
			    			  pane4.setAlignment(Pos.CENTER); 
			    			  pane4.setHgap(15.5);
			    			  pane4.setVgap(15.5); 
			        		
			    			  Label answer= new Label();
			    			  answer.setFont(Font.font(20));
			    			  pane4.add(answer,0,0); 
				    	
			    			  char g;
				    		  if(comboBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Female")) {
				    			  g ='F';
				    		  }
				    		  else {
				    			  g='M';
				    		  }
			    		 
				    		  Name namey = new Name(name1.getText().trim()+","+ g);
				    		  try {
				    		  Frecuency frec = new Frecuency(Integer.parseInt(frec1.getText().trim()),comboBox1.getSelectionModel().getSelectedItem().trim());
				    		 
				    		  int p = addName(namey, frec);
				    		  if(p== 0) {
				    			  answer.setText(name1.getText().trim()+" was added!"); 
				    		  }else if(p== 1) {
				    			  answer.setText(name1.getText().trim()+" existed, new frecuency added!");
				    		  }else {
				    			  answer.setText(name1.getText().trim()+" existed, frecuency updated for "+comboBox1.getSelectionModel().getSelectedItem().trim());
				    		  }	
				    		  
				    		  reWriteFile(frec);
				    		  }catch(NumberFormatException u) {
				    			  answer.setText("You must enter matches values");
				    		  }
					    	Button ok = new Button("Ok");
					    	ok.setTextFill(Color.BROWN);  
			        		ok.setFont(Font.font(16));
					    	GridPane.setHalignment(ok, HPos.CENTER);
					    	ok.setBackground(background );
			        		pane4.add(ok, 0,1);
			        		ok.setOnAction(y->{
			        			stage4.close();
			        		});
		        		
			        		Scene scene3 = new Scene(pane4); 
		        		
			                stage4.setTitle("Answer");
			                stage4.setScene(scene3);  
			                stage4.show();
			    		  }
			    	  });
		    	  
			    	  Scene scene3 = new Scene(pane3); 

			    	  stage3.setTitle("Add new name");
			    	  stage3.setScene(scene3);  
			    	  stage3.show();
	        		});
        		
        		Button frecuency = new Button();
        		frecuency.setTextFill(Color.BROWN); 
        		frecuency.setPrefSize(200, 200); 
        		frecuency.setGraphic(new ImageView(new Image("Frecuency.png")));
        		frecuency.setBackground(background );
        		frecuency.setOnAction(x->{
        			Stage stage3 = new Stage();
        			GridPane pane3 = new GridPane();
        			pane3.setPadding( new Insets( 100,130, 100,130 ));
        			pane3.setBackground(background1); 
                    pane3.setAlignment(Pos.CENTER); 
            		pane3.setHgap(15.5);
            		pane3.setVgap(15.5);  
    		    	   
    		    	Label naame= new Label(("Name of the baby:"));
    		    	naame.setFont(Font.font(20));
    		    	pane3.add(naame,0,0); 
    		    	   
    		    	Label gender= new Label(("Gender of the baby:"));
    		    	gender.setFont(Font.font(20));
    		    	pane3.add(gender, 0, 1); 
    		    	   
    		    	TextField name1 = new TextField();
    		    	name1.setFont(Font.font(16));
    		    	name1.setPrefSize(150, 30);
    		    	pane3.add(name1, 1, 0); 
    		    	   
    		    	ComboBox<String> comboBox = new ComboBox<String>();   
    		    	comboBox.getItems().add("Female") ;
    		    	comboBox.getItems().add("Male"); 
    		    	comboBox.setBackground(background);
    		    	comboBox.setPrefSize(150, 30);
    		    	pane3.add(comboBox, 1, 1);  
    		    	 
    		    	  Button btCh = new Button("Choose");
    		    	  btCh.setTextFill(Color.BROWN);
    		    	  btCh.setFont(Font.font(16));
    		    	  GridPane.setHalignment( btCh, HPos.CENTER);
    		    	  btCh.setBackground(background);
    		    	  pane3.add(btCh, 0, 3); 
    		    	   
    		    	  btCh.setOnAction(d-> {
    		    		  if(comboBox.getSelectionModel().getSelectedItem()== null ||name1.getText().isEmpty()) {
    		    			  Label answer= new Label("You must enter values");
    		    			  answer.setTextFill(Color.RED);
    					      answer.setFont(Font.font(16));
    					      pane3.add(answer,0,2);   
    		    		  } else {
    		    		    Stage stage4 = new Stage();
    		    			GridPane pane4 = new GridPane();
    		    			pane4.setPadding( new Insets( 100,130, 100,130 ));
    		    			pane4.setBackground(background1); 
    		                pane4.setAlignment(Pos.CENTER); 
    		        		pane4.setHgap(15.5);
    		        		pane4.setVgap(15.5);  
    		        		
    		        		Label answer= new Label();
    				    	answer.setFont(Font.font(20));
    				    	pane4.add(answer,0,0);  
    				    	
    		    		  char g;
    		    		  if(comboBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Female")) {
    		    			  g ='F';
    		    		  }else {
    		    			  g='M';
    		    		  }
    		    		  String key =name1.getText()+g;
    		    		  
    		    		  Button btCancel = new Button("Ok");
    			    	  btCancel.setTextFill(Color.BROWN);
    			    	  btCancel.setFont(Font.font(16));
    			    	  GridPane.setHalignment(btCancel,HPos.CENTER);
    			    	  btCancel.setBackground(background);
    			    	  
    			    	  btCancel.setOnAction(i-> {
    		        			stage4.close();
    			    	  }); 
    		    		if(hashTable.find(key)==null) {
    		    			answer.setText("Sorry, name was not found"); 
    				    	pane4.add(btCancel, 1, 0); 
    		    		}else {
    		    			answer.setText("Name was found");
    		    			Label year= new Label(("Year of the frecuency:"));
    			    		year.setFont(Font.font(20));
    				    	pane4.add(year,0,1); 
    				    	   
    				    	Label frec= new Label(("frecuency of the baby:"));
    				    	frec.setFont(Font.font(20));
    				    	pane4.add(frec, 0, 2); 
    				    	
    			    		TextField year1 = new TextField();
    			    		year1.setFont(Font.font(16));
    			    		year1.setPrefSize(150, 30);
    				    	pane4.add(year1, 1, 1);
    				    	
    				    	TextField frec1 = new TextField();
    				    	frec1.setFont(Font.font(16));
    				    	frec1.setPrefSize(150, 30);
    				    	pane4.add(frec1, 1, 2);
    				    	
    				    	Button btUp = new Button("Add");
    				    	btUp.setTextFill(Color.BROWN);
    				    	btUp.setFont(Font.font(16));
    				    	btUp.setBackground(background);
    				    	GridPane.setHalignment( btUp, HPos.CENTER);
    				    	pane4.add(btUp, 0, 4); 
    		    		
    				    	  btCancel.setText("Cancel");
    				    	  pane4.add(btCancel, 1, 4); 
    			    	  btUp.setOnAction(l-> { 
    			    		  if(year1.getText().isEmpty() ||frec1.getText().isEmpty()) {
    			    			  Label answe = new Label("Enter values");
    			    			  answe.setTextFill(Color.RED);
    						      answe.setFont(Font.font(16));
    						      pane4.add(answe ,0,3); 
    			    		  }else {
    			    		  Stage stage5 = new Stage();
    			    		  GridPane pane5 = new GridPane();
    			    		  pane5.setPadding( new Insets( 100,130, 100,130 ));
    			    		  pane5.setBackground(background1); 
    			    		  pane5.setAlignment(Pos.CENTER); 
    			    		  pane5.setHgap(15.5);
    			    		  pane5.setVgap(15.5); 
    			    		  
    			    		String  nameS= name1.getText()+","+ g;
    			    		Name namen = new Name(nameS); 
    			    		Label answe = new Label();
	  		    			  answe.setTextFill(Color.RED);
	  					      answe.setFont(Font.font(16));
	  					      pane5.add(answe ,0,0);
    			    		try {
    			    			int c= Integer.parseInt(year1.getText().trim());
    			    			int s= Integer.parseInt(frec1.getText().trim());
    			    		Frecuency frecuenciy = new Frecuency(Integer.parseInt(frec1.getText().trim()),year1.getText().trim());
    			    		
    			    		
    					      
    					      Button btCancel1 = new Button("Ok");
    				    	  btCancel1.setTextFill(Color.BROWN);
    				    	  btCancel1.setFont(Font.font(16));
    				    	  GridPane.setHalignment(btCancel1,HPos.CENTER);
    				    	  btCancel1.setBackground(background);
    				    	  pane5.add(btCancel1, 1, 0); 
    				    	  
    				    	  btCancel1.setOnAction(i-> {
    			        			stage5.close();
    				    	  }); 
    					      if(addName( namen,frecuenciy)==1) {
    					    	  answe.setText("Frecuency added!");
    							} else if(addName(namen,frecuenciy)==0) {
    								answe.setText("Frecuency of "+year1.getText().trim()+" for "+name1.getText()+ " was updated!");
    							} else {
    								answe.setText(name1.getText()+" was added!"); 
    							}
    					      reWriteFile(frecuenciy); 
    			    		}catch (NumberFormatException ew) {
      			    			 answe.setText("Numbers for year");
      			    		}
    			    		  Scene scene5 = new Scene(pane5); 
    			    		  
    			    		  stage5.setTitle("Answer");
    			    		  stage5.setScene(scene5);  
    			    		  stage5.show();
    			    		  } 
    		        		}); 
    			    	   
    		    		}
    		        	  Scene scene4 = new Scene(pane4); 
    		                stage4.setTitle("Update Frecuency");
    		                stage4.setScene(scene4);  
    		                stage4.show();
    		    		  }
    	    		  });
    	    	   
    	    	  Button btCancel = new Button("Cancel");
    	    	  btCancel.setTextFill(Color.BROWN);
    	    	  btCancel.setFont(Font.font(16));
    	    	  btCancel.setBackground(background);
    	    	  pane3.add(btCancel, 1, 3);  
    	    	  btCancel.setOnAction(d-> {
        			stage3.close();
        		});
        			  
    	    	  Scene scene = new Scene(pane3); 
          		
                  stage3.setTitle("Add");
                  stage3.setScene(scene );  
                  stage3.show(); 
        		});
        		
        		Button cancel = new Button();
        		cancel.setTextFill(Color.BROWN); 
        		cancel.setPrefSize(200, 200); 
        		cancel.setGraphic(new ImageView(new Image("Cancel.png")));
        		cancel.setBackground(background );
        		cancel.setOnAction(x->{
        			stage2.close();
        		});
        		
        		pane2.add(name, 0, 0);
        		pane2.add(frecuency, 1, 0);
        		pane2.add(cancel, 2, 0);
        		
        		Scene scene = new Scene(pane2); 
        		
                stage2.setTitle("Add");
                stage2.setScene(scene);  
                stage2.show();
    		});
    		
    		Button delete = new Button( );
    		delete.setTextFill(Color.BROWN); 
    		delete.setPrefSize(200, 200);  
    		delete.setGraphic(new ImageView(new Image("Delete.png" )));
    		delete.setBackground(background );
    		delete.setOnAction(k-> {
    			Stage stage2 = new Stage();
    			GridPane pane2 = new GridPane();
    			pane2.setPadding( new Insets( 100,130, 100,130 ));
    			pane2.setBackground(background1); 
                pane2.setAlignment(Pos.CENTER); 
        		pane2.setHgap(15.5);
        		pane2.setVgap(15.5);  
		    	   
		    	Label name= new Label(("Name of the baby:"));
		    	name.setFont(Font.font(20));
		    	pane2.add(name,0,0); 
		    	   
		    	Label gender= new Label(("Gender of the baby:"));
		    	gender.setFont(Font.font(20));
		    	pane2.add(gender, 0, 1); 
		    	   
		    	TextField name1 = new TextField();
		    	name1.setFont(Font.font(16));
		    	name1.setPrefSize(150, 30);
		    	pane2.add(name1, 1, 0); 
		    	   
		    	ComboBox<String> comboBox = new ComboBox<String>();   
		    	comboBox.getItems().add("Female") ;
		    	comboBox.getItems().add("Male"); 
		    	comboBox.setBackground(background);
		    	comboBox.setPrefSize(150, 30);
		    	pane2.add(comboBox, 1, 1);  
		    	 
		    	  Button btDEl = new Button("Delete");
		    	  btDEl.setTextFill(Color.BROWN);
		    	  btDEl.setFont(Font.font(16));
		    	  GridPane.setHalignment(  btDEl, HPos.CENTER);
		    	  btDEl.setBackground(background);
		    	  pane2.add( btDEl, 0, 3); 
		    	   
		    	  btDEl.setOnAction(d-> {
		    		  if(comboBox.getSelectionModel().getSelectedItem()== null ||name1.getText().isEmpty()) {
		    			  Label answer= new Label("You must enter values");
		    			  answer.setTextFill(Color.RED);
					      answer.setFont(Font.font(16)); 
					    	pane2.add(answer,0,2);   
		    		  } else {
		    			  Stage stage3 = new Stage();
		    			  
		    			GridPane pane3 = new GridPane();
		    			pane3.setPadding( new Insets( 100,130, 100,130 ));
		    			pane3.setBackground(background1); 
		                pane3.setAlignment(Pos.CENTER); 
		        		pane3.setHgap(15.5);
		        		pane3.setVgap(15.5);  
				    	   
				    	Label answer= new Label( );
				    	answer.setFont(Font.font(20));
				    	pane3.add(answer,0,0);  
				    	
		    		  char g;
		    		  if(comboBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Female")) {
		    			  g ='F';
		    		  }
		    		  else {
		    			  g='M';
		    		  }
		    		 Name key = new Name(name1.getText().trim()+","+ g); 
		    		 String key1= name1.getText().trim()+  g;
			    	  
		    		 Button btCancel = new Button("Ok");
		    		 btCancel.setTextFill(Color.BROWN);
		    		 btCancel.setFont(Font.font(16));
		    		 btCancel.setBackground(background);
		    		 pane3.add(btCancel, 2, 0); 
			    	  
		    		 btCancel.setOnAction(l-> {
		        			stage3.close();
		        	});
		    		 HashNode found =hashTable.find(key1);
			  		int y1 = delete(key);
					if(y1== -1) {
						 answer.setText("Sorry, "+name1.getText().trim()+" was not found to be deleted!"); 
					}else if(y1== 0){    
						for(int i=1;i<found.getMaxHeap().getSize();i++) 
								reWriteFile(found.getMaxHeap().getHeapTable()[i]);  
						answer.setText(name1.getText().trim()+" was deleted! Files upated");
					}   
					Scene scene3 = new Scene(pane3); 
	                stage3.setTitle("Answer");
	                stage3.setScene(scene3);  
	                stage3.show();
		    		}
		    	  });
		    	   
		    	  Button btCancel = new Button("Cancel");
		    	  btCancel.setTextFill(Color.BROWN);
		    	  btCancel.setFont(Font.font(16));
		    	  btCancel.setBackground(background);
		    	  pane2.add(btCancel, 1, 3);  
		    	  btCancel.setOnAction(d-> {
        			stage2.close();
		    	  });
		    	Scene scene = new Scene(pane2); 
        		
                stage2.setTitle("Delete");
                stage2.setScene(scene);  
                stage2.show();  
    		});
    		
    		Button search = new Button( ); 
    		search.setTextFill(Color.BROWN); 
    		search.setPrefSize(200, 200);
    		search.setGraphic(new ImageView(new Image("Search.png")));
    		search.setBackground(background );
    		search.setOnAction(k-> {
    			Stage stage2 = new Stage();
    			GridPane pane2 = new GridPane();
    			pane2.setPadding( new Insets( 100,130, 100,130 ));
    			pane2.setBackground(background1); 
                pane2.setAlignment(Pos.CENTER); 
        		pane2.setHgap(15.5);
        		pane2.setVgap(15.5);  
		    	   
		    	Label name= new Label(("Name of the baby:"));
		    	name.setFont(Font.font(20));
		    	pane2.add(name,0,0); 
		    	   
		    	Label gender= new Label(("Gender of the baby:"));
		    	gender.setFont(Font.font(20));
		    	pane2.add(gender, 0, 1); 
		    	   
		    	TextField name1 = new TextField();
		    	name1.setFont(Font.font(16));
		    	name1.setPrefSize(150, 30);
		    	pane2.add(name1, 1, 0); 
		    	   
		    	ComboBox<String> comboBox = new ComboBox<String>();   
		    	comboBox.getItems().add("Female") ;
		    	comboBox.getItems().add("Male"); 
		    	comboBox.setBackground(background);
		    	comboBox.setPrefSize(150, 30);
		    	pane2.add(comboBox, 1, 1);  
		    	 
		    	  Button btSearch = new Button("Search");
		    	  btSearch.setTextFill(Color.BROWN);
		    	  btSearch.setFont(Font.font(16));
		    	  GridPane.setHalignment( btSearch, HPos.CENTER);
		    	  btSearch.setBackground(background);
		    	  pane2.add(btSearch, 0, 3); 
		    	   
		    	  btSearch.setOnAction(d-> {
		    		  if(comboBox.getSelectionModel().getSelectedItem()== null ||name1.getText().isEmpty()) {
		    			  Label answer= new Label("You must enter values");
		    			  answer.setTextFill(Color.RED);
					      answer.setFont(Font.font(16));
					    	pane2.add(answer,0,2);   
		    		  } else {
		    		  Stage stage3 = new Stage();
		    			GridPane pane3 = new GridPane();
		    			pane3.setPadding( new Insets( 100,130, 100,130 ));
		    			pane3.setBackground(background1); 
		                pane3.setAlignment(Pos.CENTER); 
		        		pane3.setHgap(15.5);
		        		pane3.setVgap(15.5);  
				    	   
				    	Label answer= new Label( );
				    	answer.setFont(Font.font(20));
				    	pane3.add(answer,0,0);  
				    	
		    		  char g;
		    		  if(comboBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Female")) {
		    			  g ='F';
		    		  }
		    		  else {
		    			  g='M';
		    		  } 
		    		  
			    	  Button btCancel = new Button("Ok");
			    	  btCancel.setTextFill(Color.BROWN);
			    	  btCancel.setFont(Font.font(16));
			    	  btCancel.setBackground(background);
			    	  pane3.add(btCancel, 2, 0); 
			    	  
			    	  btCancel.setOnAction(l-> {
		        			stage3.close();
		        		}); 
			    	  String  key= name1.getText()+g; 
			    	  HashNode found = hashTable.find(key); 
		        	  if(found== null)    
			    	    answer.setText("Sorry, "+name1.getText()+" was not found"); 
		        	  else {
		        		  answer.setText(name1.getText() +" was found, frecuencies over the years:"); 
		        	      TextArea area = new TextArea();  
		        	      area.setPrefColumnCount(15);
		        	      area.setPrefHeight(300);
		        	      area.setPrefWidth(300);  
		        	      
		    		   String text="";
		    		   for (int i = 1; i <= found.getMaxHeap().getSize() ; i++) {
		    			   text = text + found.getMaxHeap().getHeapTable()[i].f.format(found.getMaxHeap().getHeapTable()[i].getYear())+" --> " + found.getMaxHeap().getHeapTable()[i].getFrecuency() +"\n"; 
		    			}  
		    		   area.setText(text );
		    		   pane3.add(area, 0, 1); 
		        	  }   
		        	  Scene scene3 = new Scene(pane3); 
		                stage3.setTitle("Answer");
		                stage3.setScene(scene3);  
		                stage3.show();
		    		  }
		    		  }); 
		    	  
		    	  Button btCancel = new Button("Cancel");
		    	  btCancel.setTextFill(Color.BROWN);
		    	  btCancel.setFont(Font.font(16));
		    	  btCancel.setBackground(background);
		    	  pane2.add(btCancel, 1, 3);  
        		 btCancel.setOnAction(d-> {
        			stage2.close();
        		});
		    	Scene scene = new Scene(pane2); 
        		
                stage2.setTitle("Search");
                stage2.setScene(scene);  
                stage2.show(); 
    		});
    		
    		Button update = new Button( );
    		update.setTextFill(Color.BROWN); 
    		update.setFont(Font.font(14));  
    		update.setPrefSize(200, 200);
    		update.setGraphic(new ImageView(new Image("Update.png" )));
    		update.setBackground(background );
    		update.setOnAction(k-> {
    			Stage stage2 = new Stage();
    			GridPane pane2 = new GridPane();
    			pane2.setPadding( new Insets( 100,130, 100,130 ));
    			pane2.setBackground(background1); 
                pane2.setAlignment(Pos.CENTER); 
        		pane2.setHgap(15.5);
        		pane2.setVgap(15.5);  
		    	   
		    	Label name= new Label(("Name of the baby:"));
		    	name.setFont(Font.font(20));
		    	pane2.add(name,0,0); 
		    	   
		    	Label gender= new Label(("Gender of the baby:"));
		    	gender.setFont(Font.font(20));
		    	pane2.add(gender, 0, 1); 
		    	   
		    	TextField name1 = new TextField();
		    	name1.setFont(Font.font(16));
		    	name1.setPrefSize(150, 30);
		    	pane2.add(name1, 1, 0); 
		    	   
		    	ComboBox<String> comboBox = new ComboBox<String>();   
		    	comboBox.getItems().add("Female") ;
		    	comboBox.getItems().add("Male"); 
		    	comboBox.setBackground(background);
		    	comboBox.setPrefSize(150, 30);
		    	pane2.add(comboBox, 1, 1);  
		    	 
		    	  Button btCh = new Button("Choose");
		    	  btCh.setTextFill(Color.BROWN);
		    	  btCh.setFont(Font.font(16));
		    	  GridPane.setHalignment( btCh, HPos.CENTER);
		    	  btCh.setBackground(background);
		    	  pane2.add(btCh, 0, 3); 
		    	   
		    	  btCh.setOnAction(d-> {
		    		  if(comboBox.getSelectionModel().getSelectedItem()== null ||name1.getText().isEmpty()) {
		    			  Label answer= new Label("You must enter values");
		    			  answer.setTextFill(Color.RED);
					      answer.setFont(Font.font(16));
					    	pane2.add(answer,0,2);   
		    		  } else {
		    		  Stage stage3 = new Stage();
		    			GridPane pane3 = new GridPane();
		    			pane3.setPadding( new Insets( 100,130, 100,130 ));
		    			pane3.setBackground(background1); 
		                pane3.setAlignment(Pos.CENTER); 
		        		pane3.setHgap(15.5);
		        		pane3.setVgap(15.5);  
		        		
		        		Label answer= new Label();
				    	answer.setFont(Font.font(20));
				    	pane3.add(answer,0,0);  
				    	
		    		  char g;
		    		  if(comboBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Female")) {
		    			  g ='F';
		    		  }
		    		  else {
		    			  g='M';
		    		  }
		    		  String key =name1.getText()+g;
		    		  Button btCancel = new Button("Ok");
			    	  btCancel.setTextFill(Color.BROWN);
			    	  btCancel.setFont(Font.font(16));
			    	  GridPane.setHalignment(btCancel,HPos.CENTER);
			    	  btCancel.setBackground(background);
			    	  
			    	  btCancel.setOnAction(i-> {
		        			stage3.close();
			    	  }); 
		    		if(hashTable.find(key)==null) {
		    			answer.setText("Sorry, name was not found"); 
				    	pane3.add(btCancel, 1, 0); 
		    		}else {
		    			answer.setText("Name was found");
		    			Label year= new Label(("Year to update:"));
			    		year.setFont(Font.font(20));
				    	pane3.add(year,0,1); 
				    	   
				    	Label frec= new Label(("New frecuency:"));
				    	frec.setFont(Font.font(20));
				    	pane3.add(frec, 0, 2); 
				    	
			    		TextField year1 = new TextField();
			    		year1.setFont(Font.font(16));
			    		year1.setPrefSize(150, 30);
				    	pane3.add(year1, 1, 1);
				    	
				    	TextField frec1 = new TextField();
				    	frec1.setFont(Font.font(16));
				    	frec1.setPrefSize(150, 30);
				    	pane3.add(frec1, 1, 2);
				    	
				    	Button btUp = new Button("Update");
				    	btUp.setTextFill(Color.BROWN);
				    	btUp.setFont(Font.font(16));
				    	btUp.setBackground(background);
				    	GridPane.setHalignment( btUp, HPos.CENTER);
				    	pane3.add(btUp, 0, 4); 
		    		
				    	  btCancel.setText("Cancel");
				    	  pane3.add(btCancel, 1, 4); 
			    	  btUp.setOnAction(l-> { 
			    		  if(year1.getText().isEmpty() ||frec1.getText().isEmpty()) {
			    			  Label answe = new Label("Enter values");
			    			  answe.setTextFill(Color.RED);
						      answe.setFont(Font.font(16));
						      pane3.add(answe ,0,3); 
			    		  }else {
			    		  Stage stage4 = new Stage();
			    		  GridPane pane4 = new GridPane();
			    		  pane4.setPadding( new Insets( 100,130, 100,130 ));
			    		  pane4.setBackground(background1); 
			    		  pane4.setAlignment(Pos.CENTER); 
			    		  pane4.setHgap(15.5);
			    		  pane4.setVgap(15.5); 
			    		  
			    		  Label answe = new Label();
		    			  answe.setTextFill(Color.RED);
					      answe.setFont(Font.font(16));
					      pane4.add(answe ,0,0);
					      
			    		String  nameS= name1.getText()+","+ g;
			    		Name namen = new Name(nameS);
			    		try {
			    			int c= Integer.parseInt(year1.getText().trim());
			    			int s= Integer.parseInt(frec1.getText().trim());
			    		Frecuency frecuency = new Frecuency(Integer.parseInt(frec1.getText().trim()),year1.getText().trim());
			    		
			    		  
					      Button btCancel1 = new Button("Ok");
				    	  btCancel1.setTextFill(Color.BROWN);
				    	  btCancel1.setFont(Font.font(16));
				    	  GridPane.setHalignment(btCancel1,HPos.CENTER);
				    	  btCancel1.setBackground(background);
				    	  pane4.add(btCancel1, 1, 0); 
				    	  
				    	  btCancel1.setOnAction(i-> {
			        			stage4.close();
				    	  }); 
					      if(update(namen,frecuency)==1) {
					    	  answe.setText("There are no prevoius recodrs in "+year1.getText().trim()+" for "+name1.getText() +"!");
							} else if(update(namen,frecuency)==0) {
								answe.setText("Frecuency of "+year1.getText().trim()+" for "+name1.getText()+ " was updated!");
							} else {
								answe.setText(name1.getText()+" was not found!"); 
							}
					      reWriteFile(frecuency);
			    		} catch(NumberFormatException e) {
			    			answe.setText("Numbers for year and frecuency");
			    		}
			    		  Scene scene4 = new Scene(pane4); 
			    		  
			    		  stage4.setTitle("Answer");
			    		  stage4.setScene(scene4);  
			    		  stage4.show();
			    		  } 
		        		}); 
			    	   
		    		}
		        	  Scene scene3 = new Scene(pane3); 
		                stage3.setTitle("Update Frecuency");
		                stage3.setScene(scene3);  
		                stage3.show();
		    		  }
	    		  });
	    	   
	    	  Button btCancel = new Button("Cancel");
	    	  btCancel.setTextFill(Color.BROWN);
	    	  btCancel.setFont(Font.font(16));
	    	  btCancel.setBackground(background);
	    	  pane2.add(btCancel, 1, 3);  
    		 btCancel.setOnAction(d-> {
    			stage2.close();
    		});
	    	Scene scene = new Scene(pane2); 
    		
            stage2.setTitle("Update");
            stage2.setScene(scene);  
            stage2.show(); 
    		});
    		
    		Button max = new Button( );
    		max.setTextFill(Color.BROWN);
    		max.setFont(Font.font(14));  
    		max.setPrefSize(200, 200);
    		max.setGraphic(new ImageView(new Image("Maximum.png" )));
    		max.setBackground(background );
    		max.setOnAction(k-> {
    			Stage stage2 = new Stage();
    			GridPane pane2 = new GridPane();
    			pane2.setPadding( new Insets( 100,130, 100,130 ));
    			pane2.setBackground(background1); 
                pane2.setAlignment(Pos.CENTER); 
        		pane2.setHgap(15.5);
        		pane2.setVgap(15.5);  
		    	   
		    	Label name= new Label(("The baby with the maximum frecuency is:"));
		    	name.setFont(Font.font(20));
		    	pane2.add(name,0,0); 
		    	   
		    	Label answer= new Label( "100 in 1976");
		    	answer.setFont(Font.font(20));  
		    	GridPane.setHalignment( answer, HPos.CENTER);
		    	pane2.add(answer, 0, 1);  
		    	String gendes = "";
		    	
		    	if (max().getName().getGender()== 'F')
		    		gendes = "Female";
		    	else
		    		gendes = "Male";
		    	answer.setText(max().getName().getName()+" --> "+gendes+" has a maximum of "+ max().getMaxHeap().getHeapTable()[1].getFrecuency()+" in "+max().getMaxHeap().getHeapTable()[1].f.format(max().getMaxHeap().getHeapTable()[1].getYear()) );
		    	  
		    	Button ok = new Button("Ok");
		    	ok.setTextFill(Color.RED);
		    	ok.setFont(Font.font(20));
		    	ok.setBackground(background);
		    	GridPane.setHalignment( ok, HPos.RIGHT);
		    	pane2.add(ok, 0, 2);  
        		ok.setOnAction(d-> {
        			stage2.close();
        		});
		    	  
        		Scene scene = new Scene(pane2); 
        		
                stage2.setTitle("Find Most Popular");
                stage2.setScene(scene);  
                stage2.show();
    		});
    		
    		Button show = new Button( );
    		show.setTextFill(Color.BROWN);
    		show.setFont(Font.font(14));
    		show.setPrefSize(200, 200);
    		show.setGraphic(new ImageView(new Image("Show.png" )));
    		show.setBackground(background );
    		show.setOnAction(x -> {
    			Stage stage2 = new Stage();
    			GridPane pane2 = new GridPane();
    			pane2.setPadding( new Insets( 100,130, 100,130 ));
    			pane2.setBackground(background1); 
                pane2.setAlignment(Pos.CENTER); 
        		pane2.setHgap(15.5);
        		pane2.setVgap(15.5);  
		    	   
		    	Label name= new Label(("Enter year to show names data:"));
		    	name.setFont(Font.font(20));
		    	pane2.add(name,0,0); 
		    	   
		    	TextField year1 = new TextField();
		    	year1.setFont(Font.font(16));
		    	year1.setPrefSize(88, 30);
		    	pane2.add(year1, 1, 0); 
		    	
		    	Button Btsh = new Button("Show");
		    	Btsh.setTextFill(Color.RED);
		    	Btsh.setFont(Font.font(20));
		    	Btsh.setBackground(background);
		    	GridPane.setHalignment(Btsh, HPos.RIGHT);
		    	pane2.add(Btsh, 0, 2);  
		    	
		    	Btsh.setOnAction(d-> {  
		    		  if(year1.getText().isEmpty() ) {
		    			  Label answe = new Label("Enter a year");
		    			  answe.setTextFill(Color.RED);
					      answe.setFont(Font.font(16));
					      pane2.add(answe ,0,1); 
		    		  }else {  
		    		    Stage stage3 = new Stage();
		    			GridPane pane3 = new GridPane();
		    			pane3.setPadding( new Insets( 100,130, 100,130 ));
		    			pane3.setBackground(background1); 
		                pane3.setAlignment(Pos.CENTER); 
		        		pane3.setHgap(15.5);
		        		pane3.setVgap(15.5);  
		        		
		        		Label answer= new Label( );
				    	answer.setFont(Font.font(20));
				    	pane3.add(answer,0,0);  
				    	
				    	Button btCancel = new Button("Ok");
				    	  btCancel.setTextFill(Color.RED);
				    	  btCancel.setFont(Font.font(20));
				    	  btCancel.setBackground(background);
				    	  pane3.add(btCancel, 2, 0); 
				    	  
				    	  btCancel.setOnAction(l-> {
			        			stage3.close();
			        	 }); 
				    	  try { 
			    				int c =Integer.parseInt( year1.getText().trim());
				    	  if(show(year1.getText().trim()) != null ) { 
				        	 Scanner sc;
				        	 String text="";
				        	 int cuma1;
				        	 int cuma2;
				        	 String line;
				        	 String gender;
				        	 Name name1;
								try {
									sc = new Scanner(show(year1.getText().trim()).getFile());
									while(sc.hasNextLine()) {
										line =sc.nextLine();
										name1=new Name(line); 
										 cuma1=line.indexOf(',');
										 cuma2=line.lastIndexOf(',');
										 if(name1.getGender()=='F') {
											 gender="Female";
										 }else {
											 gender="Male";
										 }
										text = text + line.substring(0,cuma1) +" --> "+ gender+" --> "+line.substring(cuma2+1)+" \n"; 
						     	      }  
						        	 sc.close();
								} catch (FileNotFoundException e1) { 
									e1.printStackTrace();
								}
					        	 
				    		  answer.setText("Data of "+year1.getText().trim()+" :");
					    	  TextArea area = new TextArea();  
			        	      area.setPrefColumnCount(40);
			        	      area.setPrefHeight(300);
			        	      area.setPrefWidth(300);
			        	      area.setText(text );
		        	      pane3.add(area, 0, 1);
				    	  }else {
				    		  answer.setText("Sorry, year was not found");
				    	  }
				    	  }catch(NumberFormatException fd) {
				    		  answer.setText("enter nymbers for year");
				    	  }
				    	Scene scene3 = new Scene(pane3); 
				    	
		                stage3.setTitle("Show data");
		                stage3.setScene(scene3);  
		                stage3.show();
		    		  }
        		}); 
		    	
		    	Button cancel = new Button("Cancel");
		    	cancel.setTextFill(Color.RED);
		    	cancel.setFont(Font.font(20));
		    	cancel.setBackground(background);
		    	GridPane.setHalignment( cancel, HPos.LEFT);
		    	pane2.add(cancel, 1, 2);  
		    	cancel.setOnAction(d-> {
        			stage2.close();
        		});
		    	
		    	Scene scene = new Scene(pane2); 
        		
                stage2.setTitle("Show");
                stage2.setScene(scene);  
                stage2.show();
    		}); 
    		
    		Button exit1 = new Button("Exit" );
    		exit1.setTextFill(Color.RED);
    		exit1.setFont(Font.font(20));
    		GridPane.setHalignment(exit1, HPos.RIGHT); 
    		exit1.setBackground(background );
    		exit1.setOnAction(x -> Platform.exit());  
    		DecimalFormat df = new DecimalFormat("####0.00");
    		Label avg = new Label("Estimation of data size: "+df.format(avg(listOfFiles)));
    		avg.setFont(Font.font(18));
    		avg.setTextFill(Color.RED);
    		
    		pane1.add(avg, 0, 0);
    		pane1.add(addd, 0, 1); 
    		pane1.add(delete, 1, 1);
    		pane1.add(search, 2, 1);
    		pane1.add(update, 0, 2);
    		pane1.add(max, 1, 2);
    		pane1.add(show, 2, 2);
    		pane1.add(exit1, 2, 3);
    		
    		Scene secondScene = new Scene(pane1, 900,600); 
    		
            stage1.setTitle("Home");
            stage1.setScene(secondScene);  
            stage1.show();
			}
			});
		
		Button cancel = new Button("Cancel");
		cancel.setFont(Font.font(14)); 
		cancel.setTextFill(Color.DARKGREEN);
		cancel.setBackground(background );
		cancel.setPrefSize(100, 50);
		pane.add(cancel,2,2); 
		cancel.setOnAction(e -> Platform.exit()); 
		 
		Scene scene  = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("Babies");
		stage.show();
	} 
	
	public static int setSize(ArrayList<FileObj> listOfFiles) { 
		int size = 0; 
		size= (int) (listOfFiles.size()* avg(listOfFiles)); 
		return size;
	}
	 
	public static void readFiles(ArrayList<FileObj> listOfFiles) { 
		Scanner scan; 
		String year;
		int cuma2;   
		String line;
		Frecuency frec;
		Name name ; 

		hashTable = new Hash(setSize(listOfFiles) );
		if (listOfFiles.size() > 0) {
			for (int i = 0; i < listOfFiles.size(); i++ ) {
				if (listOfFiles.get(i) != null) { 
				try {
					scan = new Scanner( new FileReader(listOfFiles.get(i).getFile() ));  
					//S,F,27
					while(scan.hasNextLine() ) {
						line = scan.nextLine();
						cuma2 = line.lastIndexOf(',');
					 	name = new Name(line) ;  
						year = listOfFiles.get(i).f.format(listOfFiles.get(i).getYear() ); 
						frec =  new Frecuency(Integer.parseInt(line.substring(cuma2+1)),year) ;   
						hashTable.insert(name,frec,listOfFiles.size()*4); 
					} 
					scan.close();
				} catch (FileNotFoundException e) { 
					e.printStackTrace();
				} catch (InputMismatchException e) {
					e.printStackTrace();
				}
				}
			} 
	  } 
	}  
	
	public static double avg(ArrayList<FileObj> listOfFiles) {
		double total = 0;
		if (listOfFiles.size() > 0) {
			for(int i=0;i<listOfFiles.size();i++) {
				total = total +lines(listOfFiles.get(i).getFile()); 
			}
		}
		return total/listOfFiles.size();
	}
	
	public static int lines(File file ) {
		int count = 0; 
	    try {   
	      Scanner sc = new Scanner(file); 
	      while(sc.hasNextLine()) {
	        sc.nextLine();
	        count++;
	      } 
	      sc.close();
	    } catch (Exception e) {
	      e.getStackTrace();
	    }
	    return count;
	}  
	
	public static FileObj show(String year) {
		try{ 
		for (FileObj element : listOfFiles){
	         if (element.contains(year)){
	        	 return element;
	         }
	      }
		 } catch (Exception e) {
		      e.getStackTrace();
		 }  
        return null;
	}

	public static int addName(Name name, Frecuency frecuency) {
		return hashTable.insert(name, frecuency, listOfFiles.size()*4);  
	} 

	public static int delete(Name name) {
		String key = name.getName()+name.getGender(); 
		return hashTable.remove(key);
	}

	public static int search(Name name) {
		String key = name.getName()+name.getGender();
		if(hashTable.find(key)==(null)) {
			return -1;
		} else {
			return 1;
		}   
	}

	public static HashNode max() { 
		return hashTable.getMax(); 
	}

	public static int update(Name name, Frecuency frecuency) {
		return hashTable.update(name, frecuency, listOfFiles.size()); 
	}
	
	public static BufferedWriter findFile(ArrayList<FileObj> listOfFiles,String year2 ) {
		BufferedWriter out = null;
		for (int i = 0; i < listOfFiles.size(); i++ ) {
			if(listOfFiles.get(i).contains(year2)) {
				try {
					out = new BufferedWriter(new FileWriter(listOfFiles.get(i).getFile().getPath()));
				} catch (IOException e) { 
					e.printStackTrace();
				}
			}	
		}
		return out; 
	}
	
	public static void reWriteFile(Frecuency frec) { 
		hashTable.write(findFile(listOfFiles, frec.f.format(frec.getYear())), frec); 
	}
} 

