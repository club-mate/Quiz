# Architecture Documentation

## Overview

The Quiz Application is built using the **Model-View-Controller (MVC)** design pattern. This architecture separates concerns into three main layers:

- **Model**: Data and business logic
- **View**: User interface components
- **Controller**: Logic that connects Model and View

## Directory Structure

```
src/main/java/com/quiz/
├── model/              # Data models and business logic
│   ├── QuizModel.java         # Main game state
│   ├── Question.java          # Question entity
│   ├── Player.java            # Player entity
│   └── GameState.java         # Game state management
├── view/               # Swing UI components
│   ├── QuizView.java          # Main window
│   ├── QuestionPanel.java     # Question display panel
│   ├── PlayerPanel.java       # Player info panel
│   └── MenuBar.java           # Application menu
├── controller/         # Business logic and event handlers
│   ├── QuizController.java    # Main game controller
│   ├── GameController.java    # Game flow control
│   └── QuestionController.java # Question handling
└── util/              # Utility classes
    ├── QuestionLoader.java    # Load questions from JSON
    ├── GameSerializer.java    # Save/load game state
    └── ConfigManager.java     # Configuration management
```

## Model Layer

### Responsibilities
- Store application state
- Implement game rules and logic
- Manage data persistence

### Key Classes

#### QuizModel
- Maintains list of questions
- Tracks current question index
- Manages list of players
- Tracks game score and state

```java
public class QuizModel {
    private List<Question> questions;
    private List<Player> players;
    private int currentQuestionIndex;
    private int currentPlayerIndex;
    // ... business logic methods
}
```

#### Question
- Represents a single quiz question
- Stores question text, options, correct answer

```java
public class Question {
    private int id;
    private String text;
    private List<String> options;
    private int correctAnswerIndex;
    private String difficulty;
    private String category;
}
```

#### Player
- Represents a quiz participant
- Tracks player score and progress

```java
public class Player {
    private String name;
    private int score;
    private int correctAnswers;
    // ... scoring methods
}
```

## View Layer

### Responsibilities
- Display user interface
- Capture user input
- Render game state

### Key Components

#### QuizView (Main Window)
- JFrame container for the application
- Manages application windows and dialogs
- Coordinates display of different panels

#### QuestionPanel
- Displays current question
- Shows answer options as buttons/radio buttons
- Provides visual feedback

#### PlayerPanel
- Shows current player name
- Displays score
- Shows remaining lives/time

## Controller Layer

### Responsibilities
- Handle user interactions
- Update Model based on user actions
- Update View when Model changes

### Key Classes

#### QuizController
- Main controller for game flow
- Implements event listeners
- Coordinates Model and View updates

```java
public class QuizController implements ActionListener {
    private QuizModel model;
    private QuizView view;

    public void answerQuestion(int selectedOption) {
        // Validate answer
        // Update model
        // Update view
        // Progress to next question
    }
}
```

#### GameController
- Manages game state transitions
- Handles game start/pause/end
- Manages turn switching between players

## Data Flow

```
User Action
    ↓
View (captures input)
    ↓
Controller (processes input)
    ↓
Model (updates state/logic)
    ↓
Controller (notifies view)
    ↓
View (renders new state)
```

## Question Format (JSON)

Questions are stored in JSON format for easy serialization:

```json
{
  "id": 1,
  "question": "What is the capital of France?",
  "options": ["London", "Berlin", "Paris", "Madrid"],
  "correctAnswerIndex": 2,
  "difficulty": "easy",
  "category": "Geography"
}
```

## Design Patterns Used

### 1. Model-View-Controller (MVC)
Separates application into three interconnected components for better organization and reusability.

### 2. Observer Pattern
Controller observes user actions from View and updates Model accordingly.

### 3. Singleton Pattern (Recommended)
Use for ConfigManager and GameState to ensure single instance throughout application:

```java
public class ConfigManager {
    private static ConfigManager instance;

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }
}
```

### 4. Factory Pattern (Recommended)
For creating Question objects from JSON data:

```java
public class QuestionFactory {
    public static Question createFromJson(JsonObject json) {
        // Create and return Question instance
    }
}
```

### 5. Strategy Pattern (Recommended)
For different difficulty levels and scoring strategies:

```java
public interface ScoringStrategy {
    int calculateScore(Question question, long timeSpent);
}
```

## Dependencies

### External Libraries
- **Gson**: JSON serialization/deserialization
- **Log4j2**: Logging framework
- **JUnit 5**: Unit testing
- **Mockito**: Test mocking

### Java Built-in
- **javax.swing**: UI components
- **java.io**: File operations
- **java.util**: Collections and utilities

## Configuration

Application configuration is managed through:

1. **application.properties**: Game settings, file paths
2. **log4j2.xml**: Logging configuration
3. **Environment variables**: Optional overrides

## Testing Strategy

### Unit Tests
- Test individual Model classes
- Verify business logic
- Test utility classes

### Integration Tests
- Test Controller and Model interaction
- Verify data persistence

### UI Tests (Optional)
- Test View components
- Verify user interactions

## Future Enhancements

1. **Database Integration**: Replace file-based storage with database
2. **Network Multiplayer**: Add network support for remote players
3. **Advanced Scoring**: Implement difficulty-based scoring
4. **Question Categories**: Filter questions by category
5. **User Authentication**: Add login system
6. **Leaderboard**: Track player statistics over time
7. **Analytics**: Track game metrics and player behavior

## Best Practices

1. **Separation of Concerns**: Each class has a single responsibility
2. **Dependency Injection**: Pass dependencies to constructors
3. **Error Handling**: Implement proper exception handling
4. **Logging**: Use logging instead of System.out.println
5. **Configuration**: Externalize configuration from code
6. **Testing**: Write unit tests for business logic
7. **Documentation**: Keep JavaDoc comments up to date

## Getting Started with Implementation

1. Start with Model classes (Question, Player, QuizModel)
2. Implement data loading (QuestionLoader)
3. Create Controller with game logic
4. Build View components
5. Connect Controller to View
6. Add event handlers
7. Write tests
8. Add error handling and logging

See [BUILD.md](BUILD.md) for build instructions and [README.md](README.md) for project overview.
