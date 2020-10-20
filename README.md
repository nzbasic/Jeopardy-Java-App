# SOFTENG 206 Assignment 2

This is a simple java app to play a game of Jeopardy.

Menu:\
![alt text](https://cdn.discordapp.com/attachments/627267590862929961/768260893414785064/unknown.png "Jeopardy Menu")

Question Selection:\
![alt text](https://cdn.discordapp.com/attachments/627267590862929961/768261129658171412/unknown.png "Question Selection")

To compile and run, you must use these java VM args: "vmArgs": "--module-path path-to-javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.media,javafx.base,javafx.fxml"
Must have javafx-sdk-11.0.2. 

To run on the supplied VM:
1. Extract the zip file (found in releases)
2. Grant yourself permission to run run.sh (e.g. chmod 700 run.sh)
3. Make sure a categories folder is present in the same directory as the jar file, one is supplied in the source code, instructions to create categories below.
4. In a terminal, open the directory where the script and jar is stored and execute "./run.sh" 

To create a new category, create a file with the name of the category and write questions inside with the below format
PrizeMoney, Question, Answer
example: 100, Which assignment is this, Assignment 2

A maximum of 6 categories will be shown at once in the game with a maximum of 5 questions per category. 





