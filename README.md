# Challenge 2

Need to create web service for getting tags statistics from stackoverflow.


## Development

### For development You can run it as:

```
lein do clean, figwheel
```

and open at http://0.0.0.0:3449

### For production

1. Build it as
```
lein do clean, cljsbuild once prod
```

2. Check it as
```
lein ring server
```
at http://0.0.0.0:3000

3. You can build it

For servlet container like Jetty or Tomcat... like:
```
lein ring uberwar
```
Or as standard java application like:
```
lein ring uberjar
```
and run it as
```
java -jar target/challenge-0.1.0-standalone.jar
```
and open at http://0.0.0.0:3000

## Good luck!
