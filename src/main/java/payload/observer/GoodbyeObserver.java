package payload.observer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;


import event.EventType;
import event.EventValue;
import interceptors.Loggable;
import payload.EventPayload;

@Named
@ApplicationScoped
public class GoodbyeObserver {
     // only called for EventValue.SayGoodBye
	
	 @Loggable
	 public void notifyGoodbye(@Observes @EventType(value = EventValue.SayGoodBye) EventPayload payload) {
		    payload.addResponse("goodbye from observer " + this.toString());
     }

	
}
