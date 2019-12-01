package weldSe.observer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;

import org.apache.log4j.Logger;

import weldSe.EventPayload;
import weldSe.event.EventType;
import weldSe.event.EventValue;
import weldSe.interceptors.Loggable;

@Named
@ApplicationScoped
public class HelloObserver {
	 // only called for EventValue.SayHello
	 private static Logger Log = Logger.getLogger(HelloObserver.class);

	 @Loggable
	 public void notifyHello(@Observes @EventType(value = EventValue.SayHello) EventPayload payload) {
		    Log.info(" called from " + payload.getMsg());  
		    payload.addResponse("hello from observer "+this.toString());
		  }
		    
	
}
