package Hillchipper;

import java.util.ArrayList;

public class Decrypt {
    static String privateKey = Const.keyPair;
    static final Integer encryptKeyPair[][] = Const.decryptKeyPair;

    public static String getAlphabet(Integer num) {
        return privateKey.split("")[num - 1];
    }

    public static String parseFileToNumber(String fileText) {
        fileText = fileText.replace("F", "0");
        return fileText.replaceAll("\\D", "");
    }

    public static String decrypt(String source) {
        String fileToArray[] = source.split("(?<=\\G.{4})");
        ArrayList<ArrayList<Integer>> mapedMatrix = new ArrayList<>();
        mapedMatrix.add(new ArrayList<>());
        mapedMatrix.add(new ArrayList<>());

        ArrayList<Integer> fileToNum = new ArrayList<>();

        for(String ar : fileToArray) {
            fileToNum.add(Integer.parseInt(parseFileToNumber(ar)));
        }

        if(fileToNum.size() % 2 == 1) {
            fileToNum.add(privateKey.length() - 1);
        }

        for(int key = 0; key < fileToNum.size(); key++) {
            if(key < fileToNum.size() / 2)/**/ {
                mapedMatrix.get(0).add(fileToNum.get(key));
            } else {
                mapedMatrix.get(1).add(fileToNum.get(key));
            }
        }

        ArrayList<ArrayList<Integer>> mapedParsedMatrix = new ArrayList<>();
        mapedParsedMatrix.add(new ArrayList<>());
        mapedParsedMatrix.add(new ArrayList<>());

        for(Integer value : mapedMatrix.get(0)) {
            mapedParsedMatrix.get(0).add(value * encryptKeyPair[0][0]);
            mapedParsedMatrix.get(1).add(value * encryptKeyPair[1][0]);
        }

        for(int key = 0; key < mapedMatrix.get(1).size(); key++) {
            Integer value = mapedMatrix.get(1).get(key);
            mapedParsedMatrix.get(0).set(key, mapedParsedMatrix.get(0).get(key) + (value * encryptKeyPair[0][1]));
            mapedParsedMatrix.get(1).set(key, mapedParsedMatrix.get(1).get(key) + (value * encryptKeyPair[1][1]));
        }

        ArrayList<Integer> listingMatrix = new ArrayList<>();
        listingMatrix.addAll(mapedParsedMatrix.get(0));
        listingMatrix.addAll(mapedParsedMatrix.get(1));

        ArrayList<String> fileToKeyMap = new ArrayList<>();

        for(Integer x : listingMatrix) {
            fileToKeyMap.add(getAlphabet(x));
        }

        return String.join("", fileToKeyMap);
    }
}