package eventtests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import javax.inject.Inject;

import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import interceptors.EventTypeLoggingInterceptor;
import payload.EventPayload;
import payload.observer.EventObserver;
import payload.observer.GoodbyeObserver;
import payload.observer.HelloObserver;
import payload.sender.EventSender;

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

		pl.getResponseValues().forEach(val -> System.out.println(val));
		
		EventPayload pl2 = new EventPayload("CDITest Goodbye");
		sender.doSayHello(pl2);

		assertTrue(pl2.getResponseValues().iterator().hasNext());
		assertTrue(pl2.isIntercepted());

		pl2.getResponseValues().forEach(val -> System.out.println(val));

		
	}

}
