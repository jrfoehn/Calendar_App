package views.code;

import java.io.IOException;
import java.util.ArrayList;

import controllers.CalendarController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import jfxtras.scene.control.LocalDateTimeTextField;
import jfxtras.scene.control.agenda.Agenda;
import models.IEvent;

public class CalendarPane {
	private final static String ISBS1 = "https://planif.esiee.fr/jsp/custom/modules/plannings/anonymous_cal.jsp?resources=953,1977,5281,5282&projectId=0&calType=ical&nbWeeks=4";
	private final static String ISBS2 = "https://planif.esiee.fr/jsp/custom/modules/plannings/anonymous_cal.jsp?resources=954,2184&projectId=0&calType=ical&nbWeeks=4";
	private final static String ISBS3 = "https://planif.esiee.fr/jsp/custom/modules/plannings/anonymous_cal.jsp?resources=1087,1112,1113&projectId=0&calType=ical&nbWeeks=4";
	private final static String ISBS = "https://ade.u-pec.fr/jsp/custom/modules/plannings/anonymous_cal.jsp?data=8241fc3873200214cf4f762fbdbc0b60bd72d825015315fe5d6496b849150f14ec7f554d6ed7ba1bbad7b9bdf5b7bdb2e5f080c018e322f8e0a6e61ee79f603c8af069ff1fd12df9792f3a0ef34f7c25157990d0456e1f4f";

	
	private Main main;
	private Parent parent;
	private CalendarController controller;
	private Agenda agenda;
	public CalendarPane(Main main){
		super();
		this.main = main;
		
		initView();
	}
	
	private void initView(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(WelcomePane.class.getResource("../fxml/CalendarPane.fxml"));
		
		try {
			parent = (Parent) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		controller = loader.getController();
		controller.setMain(main);
	}
	
	public void initialise(){
		//init events
				ArrayList<IEvent> events = null;

				switch (main.getClasse()) {
					case 1:
						events = IEvent.IcsToIEvent(ISBS1);
						break;
					case 2:
						events = IEvent.IcsToIEvent(ISBS2);
						break;
					case 3:
						events = IEvent.IcsToIEvent(ISBS3);
						break;
				}
				events.addAll(IEvent.IcsToIEvent(ISBS));
				
				//events = IEvent.IcsToIEvent(ISBS); 
						
						
						
				//build agenda
				agenda = new Agenda();
				
				for(IEvent event : events){
					try{
					agenda.appointments()
					.add(new Agenda.AppointmentImplLocal()
							.withStartLocalDateTime(event.getBegin())
							.withEndLocalDateTime(event.getEnd())
							.withSummary(event.getName())
							.withDescription(event.getBeautifulInfos()));
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				LocalDateTimeTextField datePicker = new LocalDateTimeTextField();
				datePicker.localDateTimeProperty().bindBidirectional(agenda.displayedLocalDateTime());
				
				((BorderPane) parent).setTop(datePicker);
				((BorderPane) parent).setCenter(agenda);
				
				

	}
	
	public void update(){};
	
	public Parent getPane(){
		return parent;
	}
	
	public Agenda getAgenda(){ 
		return agenda;
	}
}
