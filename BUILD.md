# Build and Run Instructions

This project uses Maven as its build tool. Follow these instructions to build and run the Quiz Application.

## Prerequisites

- **Java Development Kit (JDK)**: Version 11 or higher
  - Download from: https://www.oracle.com/java/technologies/downloads/
  - Verify installation: `java -version`

- **Maven**: Version 3.6.0 or higher
  - Download from: https://maven.apache.org/download.cgi
  - Verify installation: `mvn -version`

## Project Structure

```
Quiz/
├── pom.xml                              # Maven project configuration
├── README.md                            # Project overview
├── BUILD.md                             # This file
├── src/
│   ├── main/
│   │   ├── java/com/quiz/              # Main source code
│   │   │   ├── model/                  # Data models
│   │   │   ├── view/                   # Swing UI components
│   │   │   ├── controller/             # Business logic controllers
│   │   │   └── util/                   # Utility classes
│   │   └── resources/                  # Configuration and data files
│   │       ├── application.properties  # App configuration
│   │       ├── config/log4j2.xml      # Logging configuration
│   │       └── questions/              # Quiz questions in JSON
│   └── test/
│       ├── java/com/quiz/             # Test code
│       │   ├── model/
│       │   ├── view/
│       │   └── controller/
│       └── resources/                  # Test resources
├── docs/                               # Documentation files
└── .gitignore                          # Git ignore patterns
```

## Building the Project

### 1. Clean Build
Remove all previously built artifacts and rebuild from scratch:

```bash
mvn clean
```

### 2. Compile Source Code
Compile Java source files:

```bash
mvn compile
```

### 3. Run Tests
Execute all unit tests:

```bash
mvn test
```

### 4. Build JAR Package
Create a JAR file with your application:

```bash
mvn package
```

This will create two JAR files in the `target/` directory:
- `quiz-app-1.0.0.jar` - Standard JAR
- `quiz-app-fat-1.0.0.jar` - Uber JAR (includes all dependencies)

### 5. Full Build (Clean + Compile + Test + Package)
```bash
mvn clean package
```

## Running the Application

### From Maven
Run the application directly using Maven (requires source code implementation):

```bash
mvn exec:java -Dexec.mainClass="com.quiz.QuizApplication"
```

### From JAR File
After building, run the generated JAR:

```bash
java -jar target/quiz-app-fat-1.0.0.jar
```

## Code Coverage Report

Generate a code coverage report using JaCoCo:

```bash
mvn clean test jacoco:report
```

The report will be available at: `target/site/jacoco/index.html`

## Common Maven Commands

| Command | Description |
|---------|-------------|
| `mvn clean` | Remove build artifacts |
| `mvn compile` | Compile source code |
| `mvn test` | Run unit tests |
| `mvn package` | Package application |
| `mvn install` | Install JAR to local Maven repository |
| `mvn site` | Generate project documentation |
| `mvn help:describe -Dplugin=groupId:artifactId` | Get plugin help |

## Troubleshooting

### Build fails with "No compiler is provided"
- **Cause**: JDK not installed or JAVA_HOME not set
- **Solution**: Install JDK 11+ and set `JAVA_HOME` environment variable to JDK installation path

### "Failed to resolve dependencies"
- **Cause**: Maven can't access repositories
- **Solution**: Check internet connection and Maven settings in `~/.m2/settings.xml`

### Port already in use error
- **Cause**: Another application is using the same port
- **Solution**: Stop other applications or change port in `application.properties`

## IDE Integration

### IntelliJ IDEA
1. Open project root directory
2. IDE will recognize Maven project automatically
3. Build: Ctrl+F9 (Windows/Linux) or Cmd+F9 (Mac)
4. Run: Right-click main class → Run

### Eclipse
1. File → Import → Maven → Existing Maven Projects
2. Select project root directory
3. Click Finish

### Visual Studio Code
1. Install "Extension Pack for Java" from Microsoft
2. Open project root directory
3. Maven commands available in Command Palette (Ctrl+Shift+P)

## Next Steps

1. **Implement Model Classes**: Create `Question.java`, `Player.java`, `QuizModel.java` in `src/main/java/com/quiz/model/`
2. **Implement Controllers**: Create game logic controllers in `src/main/java/com/quiz/controller/`
3. **Create UI**: Build Swing components in `src/main/java/com/quiz/view/`
4. **Write Tests**: Add unit tests in `src/test/java/com/quiz/`
5. **Add Question Data**: Create JSON question files in `src/main/resources/questions/`

## Additional Resources

- [Maven Documentation](https://maven.apache.org/guides/)
- [Java 11 Documentation](https://docs.oracle.com/en/java/javase/11/)
- [Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)
- [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/)
- [SLF4J & Log4j Documentation](https://logging.apache.org/log4j/2.x/)

## Support

For issues or questions, please refer to the [contribution guidelines](contribute.md).
