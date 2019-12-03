package mikra.evttests;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Stream;

public class EventPayload implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 601499924737711109L;

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
