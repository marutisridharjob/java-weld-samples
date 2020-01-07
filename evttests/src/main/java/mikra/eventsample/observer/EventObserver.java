package mikra.eventsample.observer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mikra.eventsample.EventPayload;
import mikra.eventsample.interceptors.Loggable;

@Named
@ApplicationScoped
public class EventObserver {
	// this observer is always called regardless of the EventType
	private static Logger Log = LogManager.getLogger(EventObserver.class);

	@Loggable
	public void notifyGoodbye(@Observes EventPayload payload) {
		Log.info("called from " + payload.getMsg());
		payload.addResponse(" event from observer " + this.toString());
	}

}
