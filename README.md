![GitHub](https://img.shields.io/github/license/lenarbad/app-props)
[![Maven Central](https://img.shields.io/maven-central/v/io.lenar/app-props.svg)](https://maven-badges.herokuapp.com/maven-central/io.lenar/app-props)

![Build Status](https://travis-ci.com/LenarBad/app-props.svg?branch=master)
[![Hits of Code](https://hitsofcode.com/github/lenarbad/app-props)](https://hitsofcode.com/view/github/lenarbad/app-props)
[![Lines of Code](https://tokei.rs/b1/github/lenarbad/app-props)](https://github.com/lenarbad/app-props)

# AppProps
The library for handling the application properties that are stored externally or/and internally in the project or/and set as environment variables


**You can use different sources to config your application**
    
   - *Environmental variables*
   - *Properties files (usually .properties)*
   - *Files in JSON format*
   
    
**Files can be stored anywhere**

   - *Resource folder of your project*
   - *User Home folder*
   - *Any custom location on your computer*
   - *Somewhere in the network*
   
**How to add configs**

To add a config file to your ```appProp``` you need pass a ```Resource file;``` with one of methods
   - ```addProperties(Source file)```
   - ```addJsonProperty(Source file, String name, Class clazz)```
   - ```addJsonPropertyAsList(Source file, String name,  Class<T[]> clazz)```

**Examples**   
   
```java
AppProps appProps = new AppProps()
                        .addProperties(new UserHomeFile("my-test.properties"))
                        .addProperties(new ResourceFile("my-app.properties"))
                        .addJsonProperty(new ResourceFile("test-users.json"), "testUser", User.class)
                        .addJsonPropertyAsList(new ResourceFile("test-cards.json"), "testCards", Card[].class)
                        .addYamlProperty(new ResourceFile("test-address.yml"), "shippingAddress", ShippingAddress.class)
                        .addYamlPropertyAsList(new ResourceFile("test-order-lines.yml"), "orderLines", OrderLine.class);
```   

All environmental variables will be added automatically.   
All configs will be read in the order that they were added to ```AppProps```. 

**How to access property values**

```java
String dbPassword = appProps.value("password");
User testUser = appProp.valueAs("testUser", User.class);
ShippingAddress shippingAddress = (ShippingAddress) appProp.valueObject("shippingAddress", ShippingAddress.class);
List<Card> testCards = appProps.valueAsListOf("testCards", Card[].class);
List<OrderLine> orderLines = appProps.valueAsListOf("orderLines", OrderLine[].class);
```

**List of objects YAML example**

```java
public class TestObject {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
```

```yaml
"value": "value 1"
---
"value": "value 2"
```

