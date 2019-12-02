package weldSe.eventsExample;

import java.util.ArrayList;
import java.util.stream.Stream;

public class EventPayload {
	
	private boolean intercepted;
	
	private String msg;
	
	private ArrayList<String> response;
	
	public EventPayload(String message) {
	    this.msg = message;
	    this.intercepted = false;
		this.response = new ArrayList<String>();
	}
	
	public void setIntercepted(boolean b) {
		intercepted = true;
	}
	
	public boolean isIntercepted() {
		return this.intercepted;
	}
	
	public String getMsg() {
		return this.msg;
	}
	
	public void addResponse(String resp) {
		this.response.add(resp);
	}
	
	public Stream<String> getResponseValues(){
		return this.response.stream();
	}

}
