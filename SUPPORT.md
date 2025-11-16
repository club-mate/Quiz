# Support

Thank you for using the Quiz application! This document provides guidance on getting help and support.

## Getting Help

### Documentation

Start with these resources:

- **[README.md](README.md)** - Project overview and quick start guide
- **[BUILD.md](BUILD.md)** - Build and installation instructions
- **[ARCHITECTURE.md](ARCHITECTURE.md)** - Application architecture and design
- **[CONTRIBUTING.md](CONTRIBUTING.md)** - Contribution guidelines

### Frequently Asked Questions

#### Q: How do I build the project?
**A:** See [BUILD.md](BUILD.md) for detailed instructions:
```bash
mvn clean package
java -jar target/quiz-app-fat-1.0.0.jar
```

#### Q: What are the system requirements?
**A:**
- Java 11 or higher
- Maven 3.6.0 or higher
- Any operating system (Windows, macOS, Linux)

#### Q: How do I add new questions?
**A:** Questions are stored in JSON format. See [ARCHITECTURE.md](ARCHITECTURE.md#question-format-json) for the question format specification.

#### Q: Where are saved games stored?
**A:** Game state is saved to local files. The exact location depends on your configuration (see application.properties).

#### Q: How do I report a bug?
**A:** Please see [Reporting Issues](#reporting-issues) below.

#### Q: How do I contribute?
**A:** See [CONTRIBUTING.md](CONTRIBUTING.md) for detailed contribution guidelines.

### Reporting Issues

We use GitHub Issues to track bugs and feature requests.

#### Before Opening an Issue

1. Check existing [Issues](https://github.com/club-mate/Quiz/issues) to avoid duplicates
2. Check [Discussions](https://github.com/club-mate/Quiz/discussions) for similar topics
3. Ensure you're using the latest version

#### How to Report a Bug

[Open a Bug Report](https://github.com/club-mate/Quiz/issues/new?template=bug_report.md) and include:

- **Title**: Clear, concise description of the bug
- **Environment**: Java version, OS, Maven version
- **Steps to Reproduce**: Exact steps to reproduce the issue
- **Expected Behavior**: What should happen
- **Actual Behavior**: What actually happens
- **Screenshots/Logs**: If applicable, include error messages
- **Reproducibility**: How often does this occur?

**Example:**
```
Title: Application crashes when loading corrupted question file

Environment:
- Java 11.0.13
- Ubuntu 20.04
- Maven 3.6.3

Steps to Reproduce:
1. Create a malformed JSON question file
2. Place it in the questions directory
3. Launch the application
4. Try to load questions

Expected Behavior:
Application should show an error message

Actual Behavior:
Application crashes with NullPointerException
```

#### How to Request a Feature

[Open a Feature Request](https://github.com/club-mate/Quiz/issues/new?template=feature_request.md) and include:

- **Title**: Clear description of the feature
- **Use Case**: Why is this feature needed?
- **Proposed Solution**: How should it work?
- **Alternative Solutions**: Other approaches you've considered
- **Additional Context**: Any relevant information

**Example:**
```
Title: Add difficulty-based scoring system

Use Case:
Currently all correct answers give the same points, regardless of difficulty.
Players should earn more points for harder questions.

Proposed Solution:
1. Add difficulty level to Question model
2. Implement scoring multiplier based on difficulty
3. Display difficulty indicator in UI

Alternative Solutions:
- Time-based scoring (faster = more points)
- Category-based scoring

Additional Context:
This would make the game more challenging and rewarding.
```

#### How to Report Security Issues

**Do NOT** open a public issue for security vulnerabilities. See [SECURITY.md](SECURITY.md) for responsible disclosure process.

### Discussion and Questions

Use [GitHub Discussions](https://github.com/club-mate/Quiz/discussions) for:

- General questions about how to use the application
- Ideas for improvements (before opening an issue)
- Sharing tips and tricks
- Getting feedback on your ideas
- General conversation about the project

## Community Support

### Asking Effective Questions

When asking for help, provide:

1. **Context**: What are you trying to do?
2. **Details**: Environment, versions, configuration
3. **Error Messages**: Full error stacktraces if applicable
4. **What You've Tried**: Steps you've already taken
5. **Expected vs Actual**: What should happen vs what happens

### Response Time

- **Bug Reports**: We aim to respond within 1 week
- **Feature Requests**: We review regularly; response time varies
- **Discussions**: Community members may respond at any time

## Getting Involved

### Ways to Contribute

1. **Report Bugs**: Help us find and fix issues
2. **Suggest Features**: Propose enhancements
3. **Write Code**: Submit pull requests
4. **Improve Documentation**: Help make docs clearer
5. **Answer Questions**: Help other community members
6. **Test**: Try new features and report issues

### Code Contributions

See [CONTRIBUTING.md](CONTRIBUTING.md) for:

- Development setup
- Coding guidelines
- Testing requirements
- Pull request process
- Commit message conventions

## Project Information

- **Repository**: [https://github.com/club-mate/Quiz](https://github.com/club-mate/Quiz)
- **Project Board**: [https://github.com/users/club-mate/projects/1](https://github.com/users/club-mate/projects/1)
- **License**: [MIT License](LICENSE)

## Escalation Path

If you have an issue that isn't resolved:

1. **Comment on the Issue**: Provide additional information
2. **Open a Discussion**: Ask for community advice
3. **Contact Maintainers**: For sensitive matters, reach out privately through GitHub

## Related Resources

- [Java Documentation](https://docs.oracle.com/en/java/javase/11/)
- [Maven Documentation](https://maven.apache.org/)
- [GitHub Help](https://docs.github.com/)
- [Git Documentation](https://git-scm.com/doc)
- [Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)

## Thank You

Thank you for being part of the Quiz project community! Your support, feedback, and contributions help make this project better.

---

**Last Updated**: November 16, 2025
