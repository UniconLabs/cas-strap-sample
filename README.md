# A great place to start your CAS Adventure!

!Documentation coming!

## Getting started

* Download and install an appropriate JDK 1.7 (http://www.oracle.com/technetwork/java/javase/downloads/index.html). Make sure you download the JDK and not the JRE.
* Download http://dl.bintray.com/apereo/Releases/#cas-strap-sample.2014.03.31.1.zip
* unzip cas-strap.zip
* modify etc/cas/cas.properties
  * change first line to point to installation directory
  * add Duo Security configuration (if needed)
  * add Yubikey configuration (if needed)

## Running the server

On Linux/Mac:

```shell
./cas-strap run
```

On Windows:

```shell
cas-strap.bat run
```

## Building a war

On Linux/Mac:

```shell
./cas-strap war
```

On Windows:

```shell
cas-strap.bat war
```

## The Person Service
Included in the distribution is a person service to manage people and groups for discovery (see `src/main/groovy/org/jasig/cas/service`).

### SuperSimplePersonServiceImpl

The `SuperSimplePersonServiceImpl` is the simplest implementation that will work with discovery, assuming that anyone
can log in and that a group called `idp` should use an external IdP for authentication. To use it:

1. edit `src/main/webapp/WEB-INF/deployerConfigContext.xml`, modifying the `personService` bean by setting the class to
`org.jasig.cas.service.SuperSimplePersonServiceImpl`.
2. By default, the scaldingspoon.org IdP is used (which uses a simple `username=password` authenticator. If another is
needed, add the metadata file to `etc/cas/metadata` and modify `src/main/webapp/WEB-INF/spring-configuration/samlSecurityContext.xml`.
Change the bean with id `metadata`, modifying the list argument as needed, e.g.:
```xml
<constructor-arg>
    <list>
        <ref bean="spMetadataProvider" />
        <bean class="org.opensaml.saml2.metadata.provider.FilesystemMetadataProvider">
            <constructor-arg type="java.io.File" value="${ETC_ROOT}/etc/cas/metadata/idp.xml" />
            <property name="parserPool" ref="parserPool"/>
        </bean>
    </list>
</constructor-arg>
```
3. restart the server
4. For IdP authentication, enter `idp` into the `Organization Alias` field and hit `SELECT`; For internal authentication,
enter anything into either field and hit `SELECT`.

### PersonServiceImpl

The `PersonServiceImpl` is a more fleshed out test implementation. Instructions for usage are basically the same as the
`SuperSimplePersonServiceImpl` with a few differences:
* use `org.jasig.cas.service.SuperSimplePersonServiceImpl` for the `personService` bean in the `deployerConfigContext.xml`
* logins are limited to users and groups defined in the service:

|Organization Alias|Info|
|-----------------|----|
|idp|external IdP authentication; must use the `jj` user|
|auth|internal authentication|

|Username|Info|
|----|----|
|jj|external IdP authentication|
|auth|internal authentication|
