**METHOD** *public static void main(String [] args)*

## // SETUP

**DECLARE** an int variable to hold the number of user guesses named *numOfGuesses*

**MAKE** a new *DotCom* object, name it *theDotCom* 

**COMPUTE** a random number between 0 & 4 that will be the starting location cell position

**MAKE** an int array object & call it *locations* to hold the starting position + 2 cells [1,2,3]

**SET** *locations* array, fill with 3 ints by adding two more numbers (+1)   

**INVOKE** the *setLocationCells* method on object dotcom to set starting position

**DECLARE** a boolean variable representing the state of the game name *isAlive*

## // GAMEPLAY

**WHILE** the object dotcom is still alive:

- **GET** the user input from the command line

- **COMPARE** the input to the dotcom position by invoking the *checkYourSelf() method*

- **INCREMENT** numOfGuesses variable

- **REPEAT** until the result returned is a "kill" 

**IF** the result is a "kill" 

**DISPLAY** the user score by printing out *numOfGuesses" 

**SET** *isAlive* to false

## // END

<style>

h2 {
  color: #888;
  margin: 1em 0;
}

body {
  font-size: 12pt;
}

ul, li, ul *, li * { 
  margin:0; 
  padding: 0; 
}

ul li {
  list-style-type:""; 
  list-style-position: inside; 
}

ul {

  border-left: 3px solid #555;
  padding-left: 2em;
  //padding-top: 16px;
  line-height: 2em;
  margin-bottom: 0.7em;

}

</style>
