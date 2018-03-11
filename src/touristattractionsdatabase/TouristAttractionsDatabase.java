package touristattractionsdatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TouristAttractionsDatabase {

    private static final String TYPE_ENTERTAINMENT = "Entertainment";
    private static final String TYPE_MUSEUM = "Museum";
    private static final String TYPE_CULTURE = "Culture";
    private static final String TYPE_SPORT = "Sport";
    private static final String SEX_FEMALE = "Female";
    private static final String SEX_MALE = "Male";

    public static void main(String[] args) {
        // reading database attractions
        ArrayList<TouristAttraction> attractions = readDbAttractions("C:/Users/konrad/Desktop/turist_db.txt");
        System.out.println("--------------attractions dataBase----------------");
        for (TouristAttraction attraction : attractions) {
            attraction.print();  
        }

        // reading input from user
        TouristAttraction input = readUserInput();
        if (input == null) {
            return; 
        }
        System.out.println("--------------user input----------------");
        input.print();

        // list of the most fitting attractions
        ArrayList<TouristAttraction> selectedAttractions = mostFittingAttractions(input, attractions);
        System.out.println("--------------selected attractions----------------");
        for (TouristAttraction attraction : selectedAttractions) {
            attraction.print();
        }

        // save results into the file
        int result = JOptionPane.showConfirmDialog(null, "Do you want to save your results into a file?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            writeSelectedAttractions("C:/Users/konrad/Desktop/SelectedAttractions.txt", selectedAttractions);
        }
    } // end main method

    public static ArrayList<TouristAttraction> readDbAttractions(String fileName) {
        ArrayList<TouristAttraction> attractions = new ArrayList<TouristAttraction>();

        String line;

        try {

            BufferedReader bufferReader = new BufferedReader(new FileReader(fileName));

            line = bufferReader.readLine();
            while (line != null) {
                if (line.startsWith("#") == false) {
                    String[] values = line.split(";");
                    TouristAttraction attraction = new TouristAttraction(values);
                    attractions.add(attraction);
                }

                line = bufferReader.readLine();
            }

            bufferReader.close(); 

        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("Unexpected error");
        }

        return attractions;
    } //end readDbAttractions

    public static void writeSelectedAttractions(String fileName, ArrayList<TouristAttraction> selectedAttractions) {

        if (selectedAttractions.size() == 0) {
            return;
        }
        try {

            BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(new File(fileName)));

            for (TouristAttraction attraction : selectedAttractions) {
                bufferWriter.write(attraction.toString()); // i ve used toString() because "write" need String, print() does not return String.
                bufferWriter.newLine();
            }

            bufferWriter.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("Unexpected error");
        } 

    } //end readDbAttractions

    public static TouristAttraction readUserInput() {
        String userName = JOptionPane.showInputDialog("Please enter your name");

        String type;
        do {
            type = JOptionPane.showInputDialog("Please enter the type (Entertainment, Museum, Culture, Sport) or empty for skip: ");
            if (type == null) { // when cancelled
                return null;
            }
            if (type.equalsIgnoreCase(TYPE_ENTERTAINMENT) || type.equalsIgnoreCase(TYPE_MUSEUM) || type.equalsIgnoreCase(TYPE_CULTURE) || type.equalsIgnoreCase(TYPE_SPORT)) {
                break; 
            } else if (type.length() != 0) {
                System.out.println("Please enter one of the following options: Entertainment, Museum, Culture, Sport");
            }
        } while (type.length() != 0);

        int durationTime = -1;
        do {
            String duration = JOptionPane.showInputDialog("Please enter duration time or empty for skip: ");
            if (duration == null) {
                return null;
            }
            if (duration.length() == 0) {
                break;
            }
            try {
                durationTime = Integer.parseInt(duration);
                break;
            } catch (NumberFormatException ex) {
                System.out.println("You must enter the number");
            }
        } while (true);

        int minAge = -1;
        do {
            String age = JOptionPane.showInputDialog("Please enter your age or empty for skip: ");
            if (age == null) {
                return null;
            }
            if (age.length() == 0) {
                break;
            }
            try {
                minAge = Integer.parseInt(age);
                break;
            } catch (NumberFormatException ex) {
                System.out.println("You must enter the number");
            }
        } while (true);

        double cost = -1;
        do {
            String co = JOptionPane.showInputDialog("Please enter how much money you can spend or empty for skip: ");
            if (co == null) {
                return null;
            }
            if (co.length() == 0) {
                break;
            }
            try {
                cost = Double.parseDouble(co);
                break;
            } catch (NumberFormatException ex) {
                System.out.println("You must enter the number");
            }
        } while (true);

        String sex;
        do {
            sex = JOptionPane.showInputDialog("Please enter your gender (Female, Male) or empty for skip: ");
            if (sex == null) {
                return null;
            }
            if (sex.equalsIgnoreCase(SEX_FEMALE) || sex.equalsIgnoreCase(SEX_MALE)) {
                break;
            } else if (sex.length() != 0) {
                System.out.println("Please enter one of the following options: Female, Male");
            }
        } while (sex.length() != 0);

        TouristAttraction user = new TouristAttraction(userName, type, durationTime, minAge, cost, sex);

        return user;
    }

    public static ArrayList<TouristAttraction> mostFittingAttractions(TouristAttraction input, ArrayList<TouristAttraction> attractions) {

        ArrayList<TouristAttraction> selectedAttractions = new ArrayList<TouristAttraction>();

        for (TouristAttraction attraction : attractions) {
            if (input.getType().isEmpty() == false) {
                if (input.getType().equals(attraction.getType()) == false) {
                    continue;
                }
            }
            if (input.getCost() > 0) {
                if (input.getCost() < attraction.getCost()) {
                    continue;
                }
            }
            if (input.getDurationTime() > 0) {
                if (input.getDurationTime() < attraction.getDurationTime()) {
                    continue;
                }
            }
            if (input.getMinAge() > 0) {
                if (input.getMinAge() < attraction.getMinAge()) {
                    continue;
                }
            }
            if (input.getSex().isEmpty() == false && attraction.getSex().equals("Both") == false) {
                if (input.getSex().equals(attraction.getSex()) == false) {
                    continue;
                }
            }

            selectedAttractions.add(attraction);
        } // end for

        return selectedAttractions;
    } // end mostFittingAttractions
}//end class

