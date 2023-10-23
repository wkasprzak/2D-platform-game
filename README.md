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

-----------
Have you ever wondered how map overlays for Google Maps are created?  In the project you will build your own interactive visualization of a large dataset tagged by geospatial information.  Both powerful and challenging, data visualization is one of the hot topics of modern computer science - as well as something that influences our world every day!

To create this project, you’ll be working with a package called Unfolding Maps, which is a library for interactive maps and geovisualizations.  It was developed by Till Nagel and the team at the University of Applied Sciences Potsdam along with other contributors and we are grateful that they developed such a cool package that we (and you) can build.

The project is separated into several parts, and you will be able to achieve project milestones. At the end of the course, you’ll have the opportunity to showcase your creation and see what your peers have built too.

</header>

<!--
  <<< Author notes: Step 1 >>>
  Choose 3-5 steps for your course.
  The first step is always the hardest, so pick something easy!
  Link to docs.github.com for further explanations.
  Encourage users to open new tabs for steps!
  TBD-step-1-notes.
-->

## Learning outcomes:
<!--
_Welcome to "TBD-course-name"! :wave:_

TBD-step-1-information

**What is _TBD-term-1_**: TBD-definition-1

### :keyboard: Activity: TBD-step-1-name
-->
1. Build an interactive graphical program in Java.
2. Design classes to make use of object-oriented programming paradigms.
3. Explain inheritance and polymorphism and how each concept is applied in the creation of a Java GUI.
4. Explain how event handling works in Java, and write event handlers to create an interactive program.
5. Write and apply searching and sorting algorithms to manage large data sets.
6. Find and fix errors (bugs) in code.
7. Develop and use test cases to ensure correctness of a program.

## Acknowledgements:
1. We  are extremely grateful to Till Nagel and the team at the University of  Applied Sciences Potsdam and all other contributors to Unfolding Maps, a  library for interactive maps and geovisualizations:
   http://unfoldingmaps.org/
2. As part of this package, the SQLite  library (version 3.7.2) is included.  We gratefully acknowledge  xerial.org for creating this Java library:
   https://bitbucket.org/xerial/sqlite-jdbc/

## Images:
<p align="center"><img align="center" src="https://github.com/akakiev/long-term-project-map-java/blob/main/pr1.jpg" height="300" width="400" /></p>
<p align="center"><img align="center" src="https://github.com/akakiev/long-term-project-map-java/blob/main/pr2.jpg" height="300" width="400" /></p>
<p align="center"><img align="center" src="https://github.com/akakiev/long-term-project-map-java/blob/main/pr3.jpg" height="300" width="400" /></p>
<footer>

<!--
  <<< Author notes: Footer >>>
  Add a link to get support, GitHub status page, code of conduct, license link.
-->

---

Get help: [TBD-support](TBD-support-link) &bull; [Review the GitHub status page](https://www.githubstatus.com/)

&copy; 2023 TBD-copyright-holder &bull; [Code of Conduct](https://www.contributor-covenant.org/version/2/1/code_of_conduct/code_of_conduct.md) &bull; [MIT License](https://gh.io/mit)

</footer>
