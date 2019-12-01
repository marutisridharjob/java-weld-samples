package eventtests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;

import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import weldSe.EventPayload;
import weldSe.interceptors.EventTypeLoggingInterceptor;
import weldSe.observer.EventObserver;
import weldSe.observer.GoodbyeObserver;
import weldSe.observer.HelloObserver;
import weldSe.sender.EventSender;

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
