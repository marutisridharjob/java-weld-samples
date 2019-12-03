package mikra.evttests.observer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;

import org.apache.log4j.Logger;

import mikra.evttests.EventPayload;
import mikra.evttests.interceptors.Loggable;

@Named
@ApplicationScoped
public class EventObserver {
	// this observer is always called regardless of the EventType
	private static Logger Log = Logger.getLogger(EventObserver.class);

	@Loggable
	public void notifyGoodbye(@Observes EventPayload payload) {
		Log.info("called from " + payload.getMsg());
		payload.addResponse(" event from observer " + this.toString());
	}

}
