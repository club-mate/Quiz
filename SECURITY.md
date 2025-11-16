# Security Policy

## Reporting a Vulnerability

The Quiz project takes security seriously. If you discover a security vulnerability in our codebase, please **do not** open a public GitHub issue. Instead, please report it responsibly to our security team.

### Reporting Process

1. **Email**: Send details to the project maintainers with subject line: "SECURITY: Quiz Vulnerability Report"
2. **Include**:
   - Description of the vulnerability
   - Steps to reproduce (if applicable)
   - Affected version(s)
   - Potential impact
   - Suggested remediation (if available)

3. **Timeline**:
   - We acknowledge receipt of your report within 48 hours
   - We will investigate and provide updates every 7 days
   - We will release patches as quickly as possible after confirming the vulnerability

### Disclosure Policy

Once we've patched the vulnerability:
- We'll contact you for permission before public disclosure
- We'll credit you in the release notes (unless you prefer anonymity)
- We'll follow a 90-day window from patch release for public disclosure

## Security Best Practices

### For Users

1. **Keep Dependencies Updated**: Regularly update Java and Maven
   ```bash
   java -version  # Ensure Java 11 or higher
   ```

2. **Secure File Storage**: Quiz saves game state locally
   - Ensure proper file permissions on quiz data files
   - Do not share game state files containing sensitive information

3. **Source Code**: Always download from official GitHub repository
   ```bash
   git clone https://github.com/club-mate/Quiz.git
   ```

### For Developers

1. **Dependency Management**
   - Review `pom.xml` for security vulnerabilities
   - Keep dependencies up to date
   - Run `mvn dependency:check` regularly

2. **Code Review**
   - All code changes require review before merging
   - Security concerns should be addressed in PRs

3. **Testing**
   - Write unit tests for security-sensitive code
   - Use `mvn clean verify` before submitting PRs

4. **Input Validation**
   - Validate all user input
   - Sanitize file paths to prevent directory traversal
   - Validate JSON data when loading questions

5. **Logging**
   - Do not log sensitive information
   - Use proper logging levels (Log4j2)
   - Keep logs accessible only to authorized personnel

6. **Serialization**
   - Be cautious with Java object serialization
   - Validate serialized data before deserialization
   - Consider using JSON with Gson for safer serialization

## Known Security Considerations

### 1. File-Based Storage
The application currently stores quiz data in text/JSON files:
- **Consideration**: File permissions determine access control
- **Mitigation**: Set proper file permissions on game state and question files
- **Future**: Consider database encryption for sensitive data

### 2. Local Data Persistence
Player scores and game state are saved locally:
- **Consideration**: Local file system access could expose game data
- **Mitigation**: Warn users about file permissions and local storage security
- **Future**: Add optional encryption for saved game data

### 3. Swing UI Security
The Swing application runs on the local machine:
- **Consideration**: Local clipboard and system access
- **Mitigation**: No sensitive data should be passed via clipboard
- **Future**: Consider adding data sanitization for clipboard operations

### 4. JSON Parsing
Questions are loaded from JSON files:
- **Consideration**: Malformed or malicious JSON could cause issues
- **Mitigation**: Proper error handling and JSON validation
- **Future**: Schema validation for question files

## Supported Versions

We provide security updates for the following versions:

| Version | Supported          | Support Ends |
|---------|------------------|--------------|
| 1.x     | :white_check_mark: | Current      |
| < 1.0   | :x:               | No support   |

## Security Update Process

1. **Identification**: Vulnerability is identified and confirmed
2. **Patching**: Fix is developed and tested
3. **Release**: New version is released with security patch
4. **Notification**: Users are notified via GitHub releases
5. **Documentation**: Security advisory is published

## Recommendations

### For Running the Application

1. Run only on trusted machines
2. Use appropriate file permissions for game data
3. Keep Java runtime updated
4. Review question files before importing (if accepting external files)

### For Contributing

1. Follow the [CONTRIBUTING.md](CONTRIBUTING.md) guidelines
2. Use secure coding practices
3. Report security concerns responsibly
4. Test your changes thoroughly

## Additional Resources

- [OWASP Top 10](https://owasp.org/www-project-top-ten/)
- [Java Security Documentation](https://docs.oracle.com/en/java/javase/11/security/)
- [Gson Security Considerations](https://github.com/google/gson)
- [Maven Security](https://maven.apache.org/security.html)

## Security Contact

For security-related inquiries, contact the project maintainers through GitHub.

---

Thank you for helping keep the Quiz project secure!
