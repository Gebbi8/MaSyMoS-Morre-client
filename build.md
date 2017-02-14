Build MaSyMoS-morre client
==========================

When you've cloned the source code:

```sh
git clone git@github.com:SemsProject/MaSyMoS-Morre-client.git
```

There are two supported options to build this project:

* [Build with Maven](#build-with-maven)

Build with Maven 
-----------------

[Maven](https://maven.apache.org/) is a build automation tool. We ship a `pom.xml` together with the sources which tells maven about versions and dependencies. Thus, maven is able to resolve everything on its own and, in order to create the library, all you need to call is `mvn package`:

```sh
usr@srv $ cd morre.client
usr@srv $ mvn -DskipTests package

[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building morre.client 0.0.9-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ morre.client ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /users/stud00/mp487/mig-git/morre-client/morre.client/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ morre.client ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ morre.client ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /users/stud00/mp487/mig-git/morre-client/morre.client/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ morre.client ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-surefire-plugin:2.17:test (default-test) @ morre.client ---
[INFO] Tests are skipped.
[INFO] 
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ morre.client ---
[INFO] Building jar: /users/stud00/mp487/mig-git/morre-client/morre.client/target/MorreClient.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.671 s
[INFO] Finished at: 2017-02-14T15:33:25+01:00
[INFO] Final Memory: 12M/304M
[INFO] ------------------------------------------------------------------------
mvn -DskipTests package  9.58s user 0.25s system 274% cpu 3.576 total
```

That done, you'll find the binaries in the `target` directory.
