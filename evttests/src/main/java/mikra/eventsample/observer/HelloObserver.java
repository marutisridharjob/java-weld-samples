package mikra.eventsample.observer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;

import org.apache.log4j.Logger;

import mikra.eventsample.EventPayload;
import mikra.eventsample.event.EventType;
import mikra.eventsample.event.EventValue;
import mikra.eventsample.interceptors.Loggable;

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
