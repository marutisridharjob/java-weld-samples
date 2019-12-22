# java_examples

## CDI 2.0 / weld SE example with parameterised events. 
Also shows up how the annotation value is consumed by an interceptor with reflection.
Simply run EventSenderTest.java as an unit-test.

## SE Container JTA Example (uses SimpleJndi,Narayana,Eclipselink,H2)
Inspired by [CDI_JTA_TESTING]( https://in.relation.to/2019/01/23/testing-cdi-beans-and-persistence-layer-under-java-se/ )
But there was no comparable JTA example with Eclipselink and SimpleJndi out there..

The entire glue logic needed is within the maven-module jta-cdi-helper.

A test-setup example can be found in the eclipselink-jta module under /transactionaltests.
[example_test](https://github.com/mikra01/java_examples/blob/master/eclipselink-jta/src/test/java/transactionaltests/ExampleTest.java)

Setup:  just write an junit5 extension by using one of the junit5 extensions in the module jta-cdi-helper.
Just check the ExampleTestExtension.class for an example. It's responsible for setup the
synthetic container.

### implementation details
The H2EntityManagerFactoryProducer is responsible for overriding of some of the persistence.xml properties for setting up the H2 database - so you can test directly with the persistence.xml
which is shipped in production. 
The SEMemoryContext is responsible for the TransactionManager`s JNDI-Lookup.
The H2 datasource needs configuration under the JNDI-Name "java:/dsName" and the name of the initial H2
setup-script is configured with the JNDI-Name "java:/h2SetupScript" (see /test/resources/dbtest/dbinit.sql)

After the test you could dump the contents of the in-memory-database into a file for
validation against a template.

Happy testing!

## installation
I tested everything with Eclipse (4.13.0) - just import it as a maven project.

## dependencies
- java 8 SE
- weld SE (3.1.2)
- maven 3.6.3
- junit 5
