package GUI;

import GUI.Constants;


import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


class ScoreHandler {
    static void writeScore(String playerName, int score){
        try(FileWriter writer = new FileWriter(Constants.fileName, true))
        {
            writer.write(playerName+" "+score);
            writer.append("\n");
            writer.flush();
            writer.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    static int readScore(){
        List<String> array = null;
        int highScore = 0;
        try {
            array = Files.readAllLines(Paths.get(Constants.fileName), StandardCharsets.UTF_8);
            for (String line : array) {
                int score = Integer.parseInt(line.split(" ")[1]);
                if (score > highScore)
                    highScore = score;
            }
        } catch (IOException e) {}
        return highScore;
    }
    static HashMap<String,Integer> scoreMap()
    {
        HashMap<String,Integer> result = new HashMap<>();
        List<String> array = null;
        try {
            array = Files.readAllLines(Paths.get(Constants.fileName), StandardCharsets.UTF_8);
            for (String line : array) {
                Integer score = Integer.parseInt(line.split(" ")[1]);
                String name = line.split(" ")[0];
                result.put(name,score);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
