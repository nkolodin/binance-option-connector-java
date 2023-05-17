This is a lightweight library that works as a connector to the [Binance Options API](https://binance-docs.github.io/apidocs/voptions/en/#change-log)

The library is based on this library [Binance Futures Library](https://github.com/binance/binance-futures-connector-java)

### Installation

``` 
<dependency>
    <groupId>io.github.binance</groupId>
    <artifactId>binance-option-connector-java</artifactId>
    <version>0.0.1</version>
</dependency>
```
Run mvn install where pom.xml is located to install the dependency.

### Install 2/2: Run via command line
$ mvn install

## Supported APIs:

/eapi/*

Options Websocket Market Stream

### Run Example
The examples are located under src/test/java/examples. Before running the examples, set up your API_KEY and SECRET_KEY in PrivateConfig.java. This configuration file is only used for examples, you should reconfigure the API_KEY and SECRET_KEY when using the library.

### Contributing
Contributions are welcome.
If you've found a bug within this project, please open an issue to discuss what you would like to change.