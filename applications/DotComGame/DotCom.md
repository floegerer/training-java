

**DECLARE** an *int array* to hold the location cells. Call it *locationCells*.

**DECLARE** an *int* to hold the number of hits. Call it *numOfHits* and **SET** it to 0.

---

**DECLARE** a *checkYourself()* method that takes a *String* for the users guess ("I","3"),
checks it and returns a result representing a "hit", "miss" or "kill".

**DECLARE** a *setLocationCells()* setter method that takes an *int array*. It contains the 3 cell locations as *ints* (2,3,4-).

---

**METHOD** *String checkYourself( String userGuess )*

- **GET** the user guess as *String* parameter
- **CONVERT** the user guess to an *int*
- **REPEAT** with each of the location cells in the *int* array

- - **COMPARE** the user guess to the location cell
- - **IF** the user guess matches

- - - **INCREMENT** the number of hits
- - - **CHECK** if it was the last location cell

- - - - **IF** number of hits is 3 **RETURN** "kill" as result
- - - - **ELSE** it was not a kill **RETURN** "hit"

- - - **ELSE** the user guess did not match so **RETURN** "miss"
- - **END IF**

- **END REPEAT**

**END METHOD**

---

**METHOD** *void setLocationCells(int[] cellLocations)

- **GET** the cell locations as an *int array* parameter
- **ASSIGN** the cell locations parameter to the cell locations instance variable

**END METHOD**

<style>

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

}

</style>
