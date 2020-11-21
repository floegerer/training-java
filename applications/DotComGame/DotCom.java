import java.util.ArrayList;

class DotCom {

    private ArrayList<String> locationCells;
    private String name;

    public void setLocationCells(ArrayList<String> cellLocations) {

        locationCells = cellLocations;

    }

    public void setName(String newName) {
        name = newName;
    }

    public String checkYourself( String userGuess ) {

        String result = "miss";

        int index = locationCells.indexOf(userGuess);

        if (index >= 0) {

            locationCells.remove(index);

            if(locationCells.isEmpty()) {

                result = "kill";
                System.out.println("You killed: " + name);

            } else {

                result = "hit";
            
            }

        }

        return result;

    }

}
