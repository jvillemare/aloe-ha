# How to ACTUALLY use JavaFX 11 with Eclipse

JavaFX 11 is a major pain to setup correctly. Here's the "easy" way to do it right:

 1. [Install JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
 2. [Find where JDK 11 is installed for your Operating System](https://www.google.com/search?q=where+is+jdk+11+installed)
 	- For Windows, this will be `C:\Program Files\Java\jdk-11.0.7`
 3. [Add JDK 11 to your PATH variable, because for some reason the installer doesn't do it](https://www.java.com/en/download/help/path.xml)
	- Make sure any path containing the words `javapath` is not there.
	- If your JDK 11 is installed at `C:\Program Files\Java\jdk-11.0.7`, you
	want to add `C:\Program Files\Java\jdk-11.0.7\lib` to the PATH
    - That `\lib` at the end is essential.
 4. [Download JavaFX 11](https://gluonhq.com/products/javafx/)
 5. Unzip the `javafx-sdk-11.0.2` zip into the `\lib` directory of JDK 11.
 	- Not that it *has* to be here, it helps to keep things organized and simple.
 6. Open Eclipse
 7. Right click on your project folder (highest folder in the Package Explorer on
	 the left) >>> Properties >>> Java Compiler
	- Uncheck `Use compliance from execution environment on the 'Java Build Path'`.
	- Click `Apply and Close`.
 8. Top toolbar >>> Window >>> Preferences >>> Java >>> Build Path >>> User Libraries
	- Click `New`
	- Name the user library as `JavaFX11`
	- Click `JavaFX11`
	- Click `Add External JARs...`
	- Navigate to where you unzipped JavaFX11
		- Following the example above, it should be in `C:\Program Files\Java\jdk-11.0.2\lib\javafx-sdk-11.0.2\`
	- In the `javafx-sdk-11.0.2\` directory, navigate to `lib\`, and select all
	the JARs. **DO NOT** include the `src.zip`.
	- Click `Apply and Close`.
 8. Right click on your project folder >>> Build Path >>> Configure Build Path...
	- Click on `Classpath`
	- Click `Add Library...`
	- Click `User Library` then `Next >`
	- Select `JavaFX11`
	- Click `Finish`
	- Click `Apply and Close`
 9. Right click on your project folder >>> Run as >>> Maven build...
	- In the `Goals:` field, type in `clean compile assembly:single`
	- Click `Run`
	- This will now build a single, standalone "fat" JAR with the entire project
	in a runnable format.
 10. If you want to run your project normally again:
	- Right click on your project folder >>> Run as >> Java Application
	- Ensure that you run `Main`, **NOT** `Controller`, as this *will* create
	problems.

Congratulations, you survived the gauntlet. It took me 20 hours to figure this
out. I hope it helps.

If someone could explain why pointing Eclipse to the Maven dependencies, but it
why it doesn't use the maven dependencies all the time, I would be happy to
update this.

---

For future reference, this is *all* you need to add to your `pom.xml` to make
this work:

```
<build>
    <plugins>
        <plugin>
	    	<artifactId>maven-assembly-plugin</artifactId>
		    	<configuration>
		        <archive>
			        <manifest>
		            	<mainClass>udel.GardenProject.garden.Main</mainClass>
			        </manifest>
		        </archive>
		        <descriptorRefs>
			        <descriptorRef>jar-with-dependencies</descriptorRef>
		        </descriptorRefs>
	    	</configuration>
	    </plugin>
    </plugins>
</build>
```

If you want to have this Maven project build for continuous integration (CI),
you need to add:

```
<dependencies>
    <dependency>
	    <groupId>junit</groupId>
	    <artifactId>junit</artifactId>
	    <version>4.11</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>11</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-media</artifactId>
        <version>11</version>
        <classifier>win</classifier>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-media</artifactId>
        <version>11</version>
        <classifier>mac</classifier>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-media</artifactId>
        <version>11</version>
        <classifier>linux</classifier>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-graphics</artifactId>
        <version>11</version>
        <classifier>win</classifier>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-graphics</artifactId>
        <version>11</version>
        <classifier>mac</classifier>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-graphics</artifactId>
        <version>11</version>
        <classifier>linux</classifier>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>11</version>
    </dependency>
	<dependency>
		<groupId>org.testfx</groupId>
		<artifactId>testfx-junit</artifactId>
		<version>4.0.15-alpha</version>
		<scope>test</scope>
	</dependency>
</dependencies>
```

to the `pom.xml` so that you can run JavaFX, unit tests, and unit tests on
JavaFX.
