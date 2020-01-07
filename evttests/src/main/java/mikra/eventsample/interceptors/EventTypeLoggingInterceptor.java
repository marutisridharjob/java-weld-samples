package mikra.eventsample.interceptors;

import java.lang.annotation.Annotation;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mikra.eventsample.EventPayload;
import mikra.eventsample.event.EventType;

@Loggable
@Interceptor
public class EventTypeLoggingInterceptor {
	// for business related stuff better use Decorators
	private static Logger Log =  LogManager.getLogger(EventTypeLoggingInterceptor.class);

	@AroundInvoke
	public Object doLog(InvocationContext ctx) throws Exception {
		Annotation[][] at = ctx.getMethod().getParameterAnnotations();

		for (Annotation[] annotationRow : at) {
			for (Annotation annotation : annotationRow) {
				if (annotation instanceof EventType) {
					EventType et = (EventType) annotation;
					Log.info("intercept: observer about to call with EventType value: " + et.value());
				}
			}
		}

		EventPayload pl = (EventPayload) ctx.getParameters()[0];
		pl.setIntercepted(true);

		return ctx.proceed();
	}

}