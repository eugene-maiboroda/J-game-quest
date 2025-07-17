### J-Game Quest

**J-Game Quest** is a simple text-based adventure game built using Java Servlets and JSP. The project simulates a basic game engine where users can make choices and progress through a story. It demonstrates how to use Java EE components like `HttpServlet`, session handling, and JSP templating for interactive user experience.


### Description

Players are presented with a series of game scenes rendered through JSP pages. Each action sends a `GET` or `POST` request, which is handled by the game controller servlet. The servlet determines the next scene based on player input and updates session attributes accordingly.

The game logic includes:

* branching story choices,
* user state tracking via `HttpSession`,
* dynamic rendering of game text and options.


### Technologies Used

* Java 11
* Java Servlet API (`javax.servlet.http.*`)
* JSP (Java Server Pages)
* Apache Tomcat 9
* HTML
* JUnit 5
* Mockito


### How to Run

1. Make sure you have Apache Tomcat 9.x installed
2. Clone the repository:

```bash
git clone https://github.com/eugene-maiboroda/J-game-quest.git
```

3. Open the project in IntelliJ IDEA or another IDE.
4. Build the project with Maven:

   ```bash
   mvn clean install
   ```
4. Deploy J-game-quest.war to Tomcat webapps/ folder or run via your IDE
   <img width="858" height="838" alt="SCR-20250717-omld" src="https://github.com/user-attachments/assets/f061d177-9412-4e88-b5c7-b47e49877a18" />


### Example Flow

* User opens the game
* JSP renders the intro scene with multiple-choice options
* On selection, the servlet processes input and returns the next scene
* The session maintains game state between requests


### Compatibility Note

The project uses javax.servlet, and therefore is not compatible with Tomcat 10+, which requires migration to jakarta.servlet.
To ensure stable operation, use Tomcat version 9.0.71 or any other version from the 9.x series.


### What I Learned From This Project

* Building web applications using Servlets and JSP
* Managing HTTP requests/responses with `doGet` and `doPost`
* Using session state to persist user progress
* Structuring simple MVC-like flow using `web.xml` for routing
* Writing unit tests for servlet behavior using Mockito
