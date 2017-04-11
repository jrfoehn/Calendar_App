package controllers;

import javafx.fxml.FXML;
import views.code.Main;

public class AskController {
	private Main main;

	
	public AskController(){}
	
	public void setMain(Main main){
		this.main=main;
	}
	
	@FXML
	public void choix1(){
		next(1);
	}
	
	@FXML
	public void choix2(){
		next(2);
	}
	
	@FXML
	public void choix3(){
		next(3);
	}
	
	private void next(int classe){
		main.setClasse(classe);
		main.getCalendarPane().initialise();
		main.showAgenda();
	}
	
	

}
