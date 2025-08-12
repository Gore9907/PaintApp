🎨 JavaFX Drawing App

A simple JavaFX-based drawing application using design patterns. Built for learning and exploration, this app allows users to draw shapes like circles, rectangles, squiggles, and more using an intuitive icon-based interface.

---

✨ Features

- Draw various shapes: Circle, Rectangle, Oval, Triangle, Polyline, Squiggle, etc.
- Eraser tool
- Image-based toolbar with clickable icons
- Uses the Strategy design pattern for tool switching
- Cleanly structured JavaFX + Maven project

🧰 Tech Stack

- Java – Core programming language for application logic and structure

- JavaFX – UI framework for rendering shapes, handling user interactions, and building the toolbar interface

- Maven – Project management and build tool for dependency handling and running the app

- Design Patterns – Applied for clean, maintainable, and extensible architecture:

   Strategy Pattern – For switching between drawing tools dynamically

   Model–View–Controller (MVC) – To separate data, user interface, and control logic

   Observer/Observable Pattern – For updating views when the model changes

🚀 Getting Started

Follow these steps to clone, build, and run the application.

🧾 Requirements

- Java 17 or higher (tested with Java 22)
- Git
- Maven (or use included Maven wrapper)

💻 Clone the Repository

git clone https://github.com/Gore9907/PaintApp.git
cd PaintApp

▶️ Run with Maven Wrapper (recommended)

./mvnw clean javafx:run

On Windows:
mvnw.cmd clean javafx:run

⚠️ macOS Users: JavaFX Runtime Path

If you see: "JavaFX runtime components are missing"

1. Install JavaFX via Homebrew:
   brew install --cask javafx

2. Add VM options in IntelliJ (Run > Edit Configurations...):

   --module-path /opt/homebrew/opt/openjfx/lib --add-modules javafx.controls,javafx.fxml

🧪 Run from IntelliJ IDEA

1. Open the project (pom.xml) in IntelliJ.
2. IntelliJ will auto-detect it as a Maven project.
3. Run the paint.Paint main class.
4. (macOS only) Add the VM options above if needed.

---

👨‍💻 Authors

Gore Ho 
Computer Science + Economics @ University of Toronto Mississauga
Steric Tsui @ University of Toronto Mississauga
Xuanyuan Wang
Computer Science + Applied Statistics @ University of Toronto Mississauga

---
