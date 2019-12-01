package weldSe.observer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;

import weldSe.EventPayload;
import weldSe.event.EventType;
import weldSe.event.EventValue;
import weldSe.interceptors.Loggable;

@Named
@ApplicationScoped
public class GoodbyeObserver {
     // only called for EventValue.SayGoodBye
	
	 @Loggable
	 public void notifyGoodbye(@Observes @EventType(value = EventValue.SayGoodBye) EventPayload payload) {
		    payload.addResponse("goodbye from observer " + this.toString());
     }

	
}
