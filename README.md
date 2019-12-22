## java_examples

# CDI 2.0 / weld SE example with parameterised events. 
Also shows up how the annotation value is consumed by an interceptor with reflection.
Simply run EventSenderTest.java as an unit-test.

# SE Container JTA Example (uses SimpleJndi,Narayana,Eclipselink,H2)
Inspired by [CDI_JTA_TESTING]( https://in.relation.to/2019/01/23/testing-cdi-beans-and-persistence-layer-under-java-se/ )
But there was no comparable JTA example with Eclipselink and SimpleJndi out there so I fiddled
a little bit to get it working :-). 

An example can be found here: 
Setup: just replace your productive EntityManagerProducer with the H2EntityManagerProducer.
It's responsible for overriding some of the persistence.xml properties for
setting up the H2 database - so you can test directly with the persistence.xml
which is shipped in production. The junit5 weld extension is used to setup the container
and the SEMemoryContext is responsible for the TransactionManager`s JNDI-Lookup.
The h2 datasource needs configuration under the JNDI-Name "java:/dsName" and the name of the initial h2
setup-script is configured with the JNDI-Name "java:/h2SetupScript".


## installation
I tested it within Eclipse (4.13.0) - just import it as a maven project.

## dependencies
- java 8 SE
- weld SE (3.1.2)
- maven 3.6.3
- junit 5
