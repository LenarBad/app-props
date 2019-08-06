[![Maven Central](https://img.shields.io/maven-central/v/io.lenar/app-props.svg)](https://maven-badges.herokuapp.com/maven-central/io.lenar/app-props)
![Build Status](https://travis-ci.com/LenarBad/app-props.svg?branch=master)
[![Hits-of-Code](https://hitsofcode.com/github/lenarbad/app-props)](https://hitsofcode.com/view/github/lenarbad/app-props)

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
   - ```addProperties(Resource file)```
   - ```addJsonProperty(Resource file, String name, Class clazz)```
   - ```addJsonPropertyAsList(Resource file, String name,  Class<T[]> clazz)```

**Examples**   
   
```java
AppProps appProps = new AppProps()
                        .addProperties(new UserHomeFile("my-test.properties"))
                        .addProperties(new ResourceFile("my-app.properties"))
                        .addJsonProperty(new ResourceFile("test-users.json"), "testUser", User.class)
                        .addJsonPropertyAsList(new ResourceFile("test-cards.json"), "testCards", Card[].class);
```   

All environmental variables will be added automatically.   
All configs will be read in the order that they were added to ```AppProps```. 

**How to access to property values**

```java
String dbPassword = appProps.value("password");
User testUser = appProp.valueAs("testUser", User.class);
List<Card> testCards = appProps.valueAsListOf("testCards", Card[].class);
```




