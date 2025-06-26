import java.io.*;
import java.util.LinkedList;

/**
 * Manager writing and showing score data
 *
 * @author Atkin Rong
 */

public class ScoreManager {
    private static ScoreManager instance;
    private String currFileName;
    private String user;

    public static ScoreManager getInstance() {
        if (instance == null) {
            instance = new ScoreManager();
        }
        return instance;
    }

    public void setCurrFileName(String fileName) {
        this.currFileName = fileName;
    }

    public void writeData(int correct, int incorrect, int level, double timeTaken) {

        String textToWrite;

        if(user != null) {
             textToWrite = user + "  ";
        } else {
            textToWrite = "guest  ";
        }

        if (correct != -1) textToWrite += "Correct: " + correct + " ";
        if (incorrect != -1) textToWrite += "Incorrect: " + incorrect + " ";
        if (level != -1) textToWrite += "level: " + level + " ";
        if (timeTaken != -1) textToWrite += "Time Taken: " + timeTaken + " ";


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currFileName, true))) {
            writer.write(textToWrite);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<String> readData(String filename) {
        LinkedList<String> scoreList = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            while (line != null) {
                scoreList.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            return new LinkedList<>();
        }
        return scoreList;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
