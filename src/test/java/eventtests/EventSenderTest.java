package eventtests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;

import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import weldSe.eventsExample.EventPayload;
import weldSe.eventsExample.interceptors.EventTypeLoggingInterceptor;
import weldSe.eventsExample.observer.EventObserver;
import weldSe.eventsExample.observer.GoodbyeObserver;
import weldSe.eventsExample.observer.HelloObserver;
import weldSe.eventsExample.sender.EventSender;

@ExtendWith(WeldJunit5Extension.class)
class EventSenderTest {

	@WeldSetup
	public WeldInitiator weld = WeldInitiator.of(WeldInitiator
			.createWeld().beanClasses(EventObserver.class, GoodbyeObserver.class, HelloObserver.class,
					EventSender.class, EventTypeLoggingInterceptor.class)
			.enableInterceptors(EventTypeLoggingInterceptor.class));

	@Inject
	EventSender sender;

	@Test
	void testHelloGoodbye() {
		EventPayload pl = new EventPayload("CDITest Hello");
		sender.doSayGoodBye(pl);

		assertTrue(pl.getResponseValues().iterator().hasNext());
		assertTrue(pl.isIntercepted());
		assertTrue(pl.getResponseValues().count() == 2);

		pl.getResponseValues().forEach(val -> System.out.println(val));

		EventPayload pl2 = new EventPayload("CDITest Goodbye");
		sender.doSayHello(pl2);

		assertTrue(pl2.getResponseValues().iterator().hasNext());
		assertTrue(pl2.isIntercepted());
		assertTrue(pl.getResponseValues().count() == 2);

		pl2.getResponseValues().forEach(val -> System.out.println(val));

	}

}
