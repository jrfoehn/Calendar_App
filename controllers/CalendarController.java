package controllers;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import views.code.Main;

public class CalendarController implements EventHandler<ActionEvent> {
	private Main main;
	
	public CalendarController(){}
	
	public void setMain(Main main){
		this.main=main;
	}
	
	@FXML
	public void print(){
		PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            main.getCalendarPane().getAgenda().print(job);
            job.endJob();
        }
	}

	@Override
	public void handle(ActionEvent event) {
			System.out.println("hey");
	}
	
}
