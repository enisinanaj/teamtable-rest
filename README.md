# rest-api
Sagittarius REST server API. Example built in Groovy/Java Compliant with JAX-RS specifications using Jersey 2.24

# Incompatibility between Jersey and Groovy

Jersey components cannot be written in Groovy. Groovy adds throu Javassist among others, the methods `getProperties` and `setProperties`. Jersey has these methods implemented in it's components as *final* therefore cannot be overriden by those of Groovy.

An JIRA Issue has been opened to Groovy to fix this problem.
