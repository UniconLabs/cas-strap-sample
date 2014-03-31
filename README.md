# A great place to start your CAS Adventure!

!Documentation coming!

## Getting started

* Download and install an appropriate JDK 1.7 (http://www.oracle.com/technetwork/java/javase/downloads/index.html). Make sure you download the JDK and not the JRE.
* Download http://dl.bintray.com/apereo/Releases/#cas-strap-sample.zip
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
