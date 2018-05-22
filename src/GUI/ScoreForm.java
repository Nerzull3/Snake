package GUI;

import GUI.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.HashMap;

import java.util.*;
import java.util.stream.Collectors;

public class ScoreForm extends JFrame {
    Map<String, Integer> map;

    public ScoreForm() {
        super("HighScore");
        setLocationRelativeTo(null);
        setSize(250,200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        HashMap<String, Integer> map = ScoreHandler.scoreMap();
        this.map = sortByValue(map);
    }
    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(HashMap<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
    public void paint(Graphics g) {
        int number = 1;
        g.drawRect(10,31 ,230,160);
        for (Map.Entry<String, Integer> entry  : map.entrySet()) {
            if (number< Constants.topCount){
                g.drawString(number + ")" + entry.getKey() + " " + entry.getValue(),30,30 + number * 20);
                number += 1;
            }
        }
    }
}
