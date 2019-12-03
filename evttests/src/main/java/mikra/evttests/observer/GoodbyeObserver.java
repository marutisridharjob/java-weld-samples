package mikra.evttests.observer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;

import mikra.evttests.EventPayload;
import mikra.evttests.event.EventType;
import mikra.evttests.event.EventValue;
import mikra.evttests.interceptors.Loggable;

@Named
@ApplicationScoped
public class GoodbyeObserver {
     // only called for EventValue.SayGoodBye
	
	 @Loggable
	 public void notifyGoodbye(@Observes @EventType(value = EventValue.SayGoodBye) EventPayload payload) {
		    payload.addResponse("goodbye from observer " + this.toString());
     }

	
}
