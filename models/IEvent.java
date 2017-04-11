package models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import tools.Tuple;

public class IEvent {
	private LocalDateTime begin;
	private LocalDateTime end;
	private String name;
	private String location;
	private String[] infos;

	public IEvent(String s) {
		begin = null;
		end = null;
		name = null;
		location = null;
		infos = null;

		String[] sequences = s.split("\n");
		for (int i = 0; i < sequences.length; i++)
		if(!(sequences[i].charAt(0) == ' ')) initProprieties(sequences[i]);

	}

	private void initProprieties(String sequence) {
		Tuple<String, String> tupleSequence = getTupleFromSequence(sequence);
		String value = tupleSequence.getArg2();
		switch (tupleSequence.getArg1()) {
		case "DTSTART":
			this.begin = dateParser(value);
			break;
		case "DTEND":
			this.end = dateParser(value);
			break;
		case "SUMMARY":
			this.name = value;
			break;
		case "LOCATION":
			this.location = value;
			break;
		case "DESCRIPTION":
			this.infos = value.split("\n");
			break;
		}
	}

	private Tuple<String, String> getTupleFromSequence(String sequence) {
		return new Tuple<String, String>(sequence.split(":")[0], sequence.split(":")[1]);
	}

	@SuppressWarnings("deprecation")
	private LocalDateTime dateParser(String strDate) {
		int year = Integer.parseInt(strDate.substring(0, 4));
		int month = Integer.parseInt(strDate.substring(4, 6));
		int day = Integer.parseInt(strDate.substring(6, 8));
		int hour = Integer.parseInt(strDate.substring(9, 11));
		int min = Integer.parseInt(strDate.substring(11, 13));

		return LocalDateTime.of(year, month, day, hour, min, 0);
	}

	public boolean isReady() {
		return ((begin != null) && (end != null) && (location != null) && (name != null) && (infos != null));
	}

	@Override
	public String toString() {
		return this.name + " " + this.location + " " + this.begin + " " + this.end;
	}
	
	public static ArrayList<IEvent> IcsToIEvent(String stringUrl){
		ArrayList<IEvent> events = new ArrayList<IEvent>();
		
		try{
			URL url = new URL(stringUrl);
			URLConnection uc = url.openConnection();
	
			uc.setRequestProperty("X-Requested-With", "Curl");
	
			String userpass = "osef" + ":" + "";
			String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
			uc.setRequestProperty("Authorization", basicAuth);
	
			BufferedReader input = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String inputLine;
			StringBuilder iEventBuilder = new StringBuilder();
			while ((inputLine = input.readLine()) != null) {
				System.out.println(inputLine);
				
	
				iEventBuilder.append(inputLine);
				iEventBuilder.append("\n");
	
				if (inputLine.equals("END:VEVENT")) {
					events.add(new IEvent(iEventBuilder.toString()));
					iEventBuilder = new StringBuilder();
				}
				
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return events;
	}

	public LocalDateTime getBegin() {
		return begin;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public String[] getInfos() {
		return infos;
	}
	
	public String getBeautifulInfos(){
		StringBuilder str = new StringBuilder();
		for(int i=0;i<infos.length;i++){
			str.append(infos[i]);
			str.append("\n");
		}
		return str.toString();
	}
	
	
	
}
