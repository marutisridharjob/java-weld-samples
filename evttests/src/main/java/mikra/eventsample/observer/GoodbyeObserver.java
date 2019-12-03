package mikra.eventsample.observer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;

import mikra.eventsample.EventPayload;
import mikra.eventsample.event.EventType;
import mikra.eventsample.event.EventValue;
import mikra.eventsample.interceptors.Loggable;

@Named
@ApplicationScoped
public class GoodbyeObserver {
     // only called for EventValue.SayGoodBye
	
	 @Loggable
	 public void notifyGoodbye(@Observes @EventType(value = EventValue.SayGoodBye) EventPayload payload) {
		    payload.addResponse("goodbye from observer " + this.toString());
     }

	
}
