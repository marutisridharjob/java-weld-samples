package mikra.evttests.interceptors;

import java.lang.annotation.Annotation;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

import mikra.evttests.EventPayload;
import mikra.evttests.event.EventType;

@Loggable
@Interceptor
public class EventTypeLoggingInterceptor {
	// for business related stuff better use Decorators
	private static Logger Log = Logger.getLogger(EventTypeLoggingInterceptor.class);

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