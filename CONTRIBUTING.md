# Contributing to Quiz

First off, thank you for considering contributing to the Quiz project! It's people like you that make Quiz such a great tool.

## Code of Conduct

This project and everyone participating in it is governed by our [Code of Conduct](CODE_OF_CONDUCT.md). By participating, you are expected to uphold this code.

## How Can I Contribute?

### Reporting Bugs

Before creating bug reports, please check the issue list as you might find out that you don't need to create one. When you are creating a bug report, please include as many details as possible:

* **Use a clear and descriptive title**
* **Describe the exact steps which reproduce the problem**
* **Provide specific examples to demonstrate the steps**
* **Describe the behavior you observed after following the steps**
* **Explain which behavior you expected to see instead and why**
* **Include screenshots and animated GIFs if possible**
* **Include your environment details** (Java version, OS, Maven version)

### Suggesting Enhancements

Enhancement suggestions are tracked as GitHub issues. When creating an enhancement suggestion, please include:

* **Use a clear and descriptive title**
* **Provide a step-by-step description of the suggested enhancement**
* **Provide specific examples to demonstrate the steps**
* **Describe the current behavior and the new expected behavior**
* **Explain why this enhancement would be useful**

### Pull Requests

* Fill in the required template
* Follow the Java/Maven styleguides
* Include appropriate test cases
* Update documentation as needed
* End all files with a newline

## Development Setup

### Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- Git

### Getting Started

1. **Fork the repository**
   ```bash
   # Visit https://github.com/club-mate/Quiz and click "Fork"
   ```

2. **Clone your fork**
   ```bash
   git clone https://github.com/YOUR-USERNAME/Quiz.git
   cd Quiz
   ```

3. **Add upstream remote**
   ```bash
   git remote add upstream https://github.com/club-mate/Quiz.git
   ```

4. **Create a feature branch**
   ```bash
   git checkout -b feature/my-feature
   # or
   git checkout -b fix/my-bug-fix
   ```

5. **Build the project**
   ```bash
   mvn clean install
   ```

6. **Make your changes**
   - Keep commits atomic and well-documented
   - Write tests for new features
   - Follow the code style (see below)

7. **Run tests**
   ```bash
   mvn clean test
   mvn clean verify
   ```

8. **Push to your fork**
   ```bash
   git push origin feature/my-feature
   ```

9. **Submit a Pull Request**
   - Provide a clear description of your changes
   - Link to any relevant issues
   - Ensure all tests pass

## Development Guidelines

### Code Style

We follow Google's Java Style Guide with these specifics:

#### Naming Conventions

- **Classes**: PascalCase (e.g., `QuizModel`, `GameController`)
- **Methods**: camelCase (e.g., `getQuestion()`, `handleAnswer()`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `MAX_PLAYERS`, `DEFAULT_TIMEOUT`)
- **Variables**: camelCase (e.g., `currentQuestion`, `playerScore`)

#### Formatting

- **Indentation**: 4 spaces (no tabs)
- **Line Length**: 100 characters max
- **Braces**: Opening braces on same line (1TBS)
- **Import Organization**: Standard Java order (java > javax > third-party > project)

```java
// Good
public class QuizModel {
    private List<Question> questions;
    private int currentIndex;

    public void loadQuestions(String filePath) {
        // Implementation
    }
}

// Bad
public class QuizModel{
    private List<Question> questions;
    private int currentIndex;
    public void loadQuestions(String filePath){
        // Implementation
    }
}
```

#### JavaDoc Comments

Add JavaDoc for public classes, methods, and fields:

```java
/**
 * Loads quiz questions from a JSON file.
 *
 * @param filePath the path to the question file
 * @throws IOException if the file cannot be read
 * @throws JsonSyntaxException if the file is not valid JSON
 */
public void loadQuestions(String filePath) throws IOException {
    // Implementation
}
```

### Testing

All code contributions should include tests.

#### Unit Tests

- Test file naming: `{Class}Test.java`
- Location: `src/test/java/{same-package-as-source}`
- Framework: JUnit 5

```java
class QuestionTest {

    @Test
    void testQuestionCreation() {
        // Arrange
        String text = "What is 2+2?";
        List<String> options = Arrays.asList("3", "4", "5");
        int correctIndex = 1;

        // Act
        Question question = new Question(text, options, correctIndex);

        // Assert
        assertEquals(text, question.getText());
        assertEquals(correctIndex, question.getCorrectAnswerIndex());
    }
}
```

#### Test Coverage

- Aim for at least 70% code coverage
- Test both happy paths and edge cases
- Test error conditions and exception handling

Run coverage reports:
```bash
mvn clean test jacoco:report
# Report location: target/site/jacoco/index.html
```

#### Integration Tests

- Test file naming: `{Component}IntegrationTest.java`
- Use `@Integration` annotation if available
- Test interaction between multiple classes

### Git Workflow

#### Commit Messages

Write clear, descriptive commit messages:

```
Fix scoring bug in GameController

- Fix incorrect score calculation for correct answers
- Add unit tests for scoring logic
- Update documentation

Fixes #123
```

Guidelines:
- Use imperative mood ("add" not "added" or "adds")
- Start with 50 characters or less
- Reference issues and pull requests when relevant
- Explain *what* and *why*, not *how*

#### Branch Naming

Use descriptive branch names:
- `feature/add-leaderboard`
- `fix/crash-on-load`
- `docs/update-readme`
- `refactor/simplify-controller`

#### Keep Your Branch Updated

Before submitting a PR, sync with upstream:

```bash
git fetch upstream
git rebase upstream/master
# or
git merge upstream/master
```

### Documentation

#### Code Comments

- Write comments for *why*, not *what*
- Keep comments concise and clear
- Update comments when changing code

```java
// Good - Explains the why
// We use a LinkedHashMap to preserve insertion order for deterministic behavior
private Map<Integer, Player> players = new LinkedHashMap<>();

// Bad - Explains the what
// This is a map to store players
private Map<Integer, Player> players;
```

#### Documentation Files

- Update [README.md](README.md) for user-facing changes
- Update [ARCHITECTURE.md](ARCHITECTURE.md) for architectural changes
- Update [BUILD.md](BUILD.md) for build/setup changes

#### JavaDoc

- Document all public classes and methods
- Include `@param`, `@return`, `@throws` tags
- Use proper grammar and punctuation

### Performance Considerations

- Avoid unnecessary object creation in loops
- Use appropriate data structures (Arrays vs Lists vs Sets)
- Profile before and after performance changes
- Document performance-critical code

### Security

- Validate all input
- Avoid hardcoded secrets or credentials
- Be cautious with file operations (path traversal)
- Use proper exception handling
- See [SECURITY.md](SECURITY.md) for security guidelines

## Styleguide

### Language and Tone

- Use clear, concise English
- Be welcoming and inclusive
- Assume good intentions

## Additional Notes

### Issue and Pull Request Labels

- `bug` - Something isn't working
- `enhancement` - New feature or request
- `documentation` - Improvements or additions to documentation
- `good first issue` - Good for newcomers
- `help wanted` - Extra attention is needed
- `question` - Further information is requested

### Recognition

Contributors are recognized in:
- Release notes
- GitHub contributors page
- Project documentation (if appropriate)

## Getting Help

- Read the [README.md](README.md)
- Check [ARCHITECTURE.md](ARCHITECTURE.md)
- Review existing issues and PRs
- Ask in GitHub Discussions
- Read [SUPPORT.md](SUPPORT.md)

## Merging Policy

Pull requests are merged when:

1. All tests pass
2. Code review is approved
3. No merge conflicts exist
4. Documentation is updated

We use squash merging for feature branches to keep the history clean.

---

Thank you for contributing! 🎉
