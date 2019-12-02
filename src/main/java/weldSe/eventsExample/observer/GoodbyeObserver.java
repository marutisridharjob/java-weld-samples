package weldSe.eventsExample.observer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;

import weldSe.eventsExample.EventPayload;
import weldSe.eventsExample.event.EventType;
import weldSe.eventsExample.event.EventValue;
import weldSe.eventsExample.interceptors.Loggable;

@Named
@ApplicationScoped
public class GoodbyeObserver {
     // only called for EventValue.SayGoodBye
	
	 @Loggable
	 public void notifyGoodbye(@Observes @EventType(value = EventValue.SayGoodBye) EventPayload payload) {
		    payload.addResponse("goodbye from observer " + this.toString());
     }

	
}
