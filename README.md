[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/OKtrtRRZ)
[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-718a45dd9cf7e7f842a935f5ebbe5719a5e09af4491e668f4dbf3b35d5cca122.svg)](https://classroom.github.com/online_ide?assignment_repo_id=12306780&assignment_repo_type=AssignmentRepo)
<header>

<!--
  <<< Author notes: Course header >>>
  Read <https://skills.github.com/quickstart> for more information about how to build courses using this template.
  Include a 1280×640 image, course name in sentence case, and a concise description in emphasis.
  In your repository settings: enable template repository, add your 1280×640 social image, auto delete head branches.
  Next to "About", add description & tags; disable releases, packages, & environments.
  Add your open source license, GitHub uses the MIT license.
-->

# Object-oriented programming

</header>

Long term project in Java - Wojciech Kasprzak <br>
The project will involve creating a platform game in the Java language. Throughout the semester, additional classes and packages will be added to expand the application's functionality. Below is a brief description of the most important classes. <br>

The main package will contain the basic framework for the graphical application, including a class responsible for building the window and the main game class. <br>
The entities package will contain information related to the in-game characters, including the player and monsters. Individual classes will include information about the behavior of the creature, its state, as well as attributes such as health, strength, and so on. Sample part of the Player class: <br>

Player --- extends ---> Entity <br>
	+Player(float x, float y, int width, int height) <br>
	#BufferedImage[][] charactersAppearance; <br>
	#moving: boolean = false <br>
	#left: boolean <br>
 	#right: boolean <br>
  	#jump: boolean <br>
  	#facedLeft: int <br>
  	#flipX: int <br>
	#void setAction() <br>
	+void draw(Graphics g) <br>
	+void update() <br>
	#void updateAnimationCounter() <br>
	#void changePosition() <br>
	#void loadGraphics() <br>

The levels package will contain information regarding the generated levels, including their structure and textures. <br>
Another package that will be part of the application is the one related to collectible objects, such as tokens. <br>
The last main package (for now) is the game state package, which will include classes such as Menu and Playing. These classes will be responsible for managing specific parts of the application. <br>
In addition to the aforementioned packages, the game will also include several utility classes such as MouseInput, KeyboardInput, DataImport, and so on. <br>
By next week, the README file will contain more detailed information about the classes. <br>

Aims of the project: <br>
-> understanding the concepts of object-oriented programming <br>
-> understanding the concept of Inheritance <br>
-> using Interfaces <br>
-> Practicing Class Desing <br>
-> Developing Programming Skills <br>

</footer>
