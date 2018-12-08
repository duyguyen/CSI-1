package chap15.file_io_assignment.FILE_IO;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
* Name: Thanh D. Nguyen
* Class: CSCI 2001-91
* Assignment: 4
* Due: 11/3/2018
* Application: FileIO - Create random a dictionary with 100 wordsInDat
* */

public class FileIO {
	
	 // == driver ==
    public static void main(String[] args) throws IOException {

        System.out.printf("%n%s%s%n", "-----", "FILEIO_APPLICATION-----");
        FileIO fileIO = new FileIO(); // constructor loads dictionary.txt to dictionary array,
                                      // and create WordList.txt.

        fileIO.createDictionaryTxt(); // create dictionary.txt.
        fileIO.createDictionaryDat(); //  create dictionary.dat (RandomAccessFile).
        fileIO.createDictionaryTxt2(); // create dictionary2.txt
        fileIO.report(); // report of comparison between dictionary.txt and dictionary2.txt
    }

    // == instances ==
    private ArrayList<String> dictionary = new ArrayList<String>();
    private ArrayList<String> wordList = new ArrayList<String>();
    private ArrayList<String> definitionList = new ArrayList<String>();
    private ArrayList<String> wordsInDat = new ArrayList<String>();
    private ArrayList<String> defsInDat = new ArrayList<String>();
    private int defDatFlag;
    private int[] randNums;

    // == constructor ==
    public FileIO() throws IOException {
        init();
    }

    // == init ==
    private void init() throws IOException {
        loadFile();
        randNums = new int[100];
        pickRandomNums();
        buildWordListAndDefList();
        createWordlist();

    }

    // == public methods ==
    public void report() throws IOException{
        Scanner wordList1 = new Scanner(new FileReader("WordDefinitions.txt"));
        Scanner wordList2 = new Scanner(new FileReader("WordDefinitions2.txt"));
        boolean result = true;

        while(wordList1.hasNext() || wordList2.hasNext()){
            String line1 = wordList1.nextLine().trim();
            String line2 = wordList2.nextLine().trim();

            if (line1 == null || line2 == null){
                result = false;
            }

            if (!line1.equalsIgnoreCase(line2)){
                result = false;
            }

        }

        if (result){
            System.out.println("** COMPARISON REPORT: WordDefinitions.txt and WordDefinitions2.txt are identical.");
        }
    }

    public void createDictionaryTxt2() throws IOException{

        getWordsAndDefsInDat();

        try {
            Scanner readerWordList = new Scanner(new File("WordList.txt"));
            PrintWriter wordDefinitions2 = new PrintWriter("WordDefinitions2.txt", "UTF-8");

            while (readerWordList.hasNext()){
                String[] words = readerWordList.nextLine().split(" ");
                String word = words[1].trim();

                if (compareString(wordsInDat, word)){
                    String str = (defDatFlag + 1) + ". " + word + " " + defsInDat.get(defDatFlag);
                    wordDefinitions2.println(str);
                }
            }
            wordDefinitions2.close();

        }catch (IOException e){
            System.out.println("Error " + e);
        }finally {
            System.out.println("* WordDefinitions2.txt has been created.");
        }

    }

    public void createDictionaryTxt() throws IOException { //  create WordDefinitions.txt
        try{
            PrintWriter printWriter = new PrintWriter("WordDefinitions.txt", "UTF-8");
            for (int i = 0; i < wordList.size(); i++){
                String line = (i + 1) + ". " + wordList.get(i) + " :" + definitionList.get(i);
                printWriter.println(line);
            }
            printWriter.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("File doesn't exist.");
        }finally {
            System.out.println("* WordDefinitions.txt has been created.");
        }
    }

    public void createDictionaryDat() throws IOException{ // create WordDefinitions.dat
        try{
            RandomAccessFile randomAccessFile = new RandomAccessFile("WordDefinitions.dat", "rw");
            for(int i = 0; i < wordList.size(); i++){
                String string = wordList.get(i) + " :" + definitionList.get(i) + "\n";
                randomAccessFile.write(string.getBytes());
            }
            randomAccessFile.close();
        }catch (IOException iox){
            System.out.println("I/O Exception: " + iox);
        }finally {
            System.out.println("* WordDefinitions.dat has been created.");
        }

    }

    public void createWordlist() throws IOException { //  create wordlist.txt

        try {
            PrintWriter printWriter = new PrintWriter("wordlist.txt", "UTF-8");
            for (int i = 0; i < wordList.size(); i++) {
                printWriter.println((i + 1) + ". " + wordList.get(i));
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File doesn't exist");
        }finally {
            System.out.println("* WordList.txt has been created");
        }

    }

    // == private methods ==
    private void getWordsAndDefsInDat() throws IOException{ // distribute words and definitions in dictionary.dat
                                                            // to wordsIndat array and defsInDat array respectively.

        try{
            Scanner readerDatDictionary = new Scanner(new File("WordDefinitions.dat"));
            while (readerDatDictionary.hasNext()){
                String[] datLineSplit = readerDatDictionary.nextLine().split(" ");
                String datWord = datLineSplit[0];
                String datDef = "";
                for (int i = 1; i < datLineSplit.length; i++){
                    datDef += datLineSplit[i] + " ";
                }

                wordsInDat.add(datWord);
                defsInDat.add(datDef);

            }

        }catch (IOException e){
            System.out.println("Error: " + e);
        }

    }

    private boolean compareString(ArrayList<String> arr, String str){ // compare a string with each string
                                                                      // component in an array.

        for (int i = 0; i < arr.size(); i++){
            if (str.equalsIgnoreCase(arr.get(i))){
                defDatFlag = i;
                return true;
            }
        }

        return false;
    }

    private void loadFile() throws IOException { // load dictionary.txt to the dictionary array

        // load wordList to dictionary array
        try{
            File dictionaryFile = new File("src/chap15/file_io_assignment/dictionary.txt");
            Scanner inputFile = new Scanner(dictionaryFile);
            while (inputFile.hasNext()) {
                String str = inputFile.nextLine();
                if (str.length() > 0) {
                    dictionary.add(str);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("File directory doesn't exist");
        }finally {
            System.out.println("* Dictionary.txt has been loaded to dictionary array.");
        }


    }

    private void buildWordListAndDefList() { //  create word list and definition list from dictionary array.
        for (int val : randNums) {
            String str = dictionary.get(val);
            buildWordArray(str);
            buildDefArray(str);

        }
    }

    private void buildDefArray(String str) { // create definition array by splitting.
        int indexOfSpace = str.indexOf(' ');
        if (indexOfSpace != -1) {
            String def = str.substring(indexOfSpace);
            definitionList.add(def);
        }
    }

    private void buildWordArray(String str) { // create word array by splitting.
        int indexOfSpace = str.indexOf(' ');
        if (indexOfSpace != -1) {
            String firstWord = str.substring(0, indexOfSpace);
            wordList.add(firstWord);
        }
    }

    private void pickRandomNums() { // create random 100 numbers for randNums array.
        int i = 0;
        Random randomNums = new Random();

        while (i < randNums.length) {
            int num = randomNums.nextInt(dictionary.size() - 1);
            if (checkDuplicate(num)) {
                randNums[i] = num;
                i++;
            }
        }
    }

    private boolean checkDuplicate(int num) { //  check if those random numbers duplicate or not.
        for (int value : randNums) {
            if (value == num) {
                return false;
            }
        }
        return true;
    }
}