package transactionaltests;

import java.security.Principal;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestPrincipal implements Principal {


	@Override
	public String getName() {
		return "testname";
	}

}
