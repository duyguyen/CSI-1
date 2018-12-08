package final_assigment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class StructureTest {

    // == instants==
    private ArrayList<Question> questionsBank = new ArrayList<>();
    private ArrayList<Question> pickedQuest = new ArrayList<>();
    private Random random = new Random();
    private UserResult userResult;


    // == constructor ==
    public StructureTest() throws IOException {
        loadQuestionBank(); // load questionBank.txt to questionBank arrayList
        Collections.sort(questionsBank); // sorting ascending order for questionBank list
        randomQuizQuest(); // random pick 3 questions of each chapter, and build pickedQuest list

    }

    // == public methods ==
    private void loadQuestionBank() throws IOException { // load questionBank.txt to questionBank arrayList


        try {
            File questionBank = new File("src/final_assigment/questionBank.txt");
            Scanner inputFile = new Scanner(questionBank);

            ArrayList<String> components = new ArrayList<>();
            ArrayList<String> tailQuestion = new ArrayList<>();
            String correctAnswer = "";

            while (inputFile.hasNext()) {
                String str = inputFile.nextLine();

                // if hasNext() is not $ -> build component array, prepare for building each question
                if (str.length() > 1) {
                    components.add(str);
                }

                // if hasNext() is $ -> build each component for each question
                if (str.length() == 1) {
                    tailQuestion = buildTailQuest(components); // build question's tail
                    correctAnswer = buildCorrectAnswer(components); // build correct answer of the question
                    Question question = new Question(components.get(0), tailQuestion, correctAnswer, components.get(1), components.get(2)); // create a question object after has all of its components
                    questionsBank.add(question); // add that question object to the questionBank array
                    components.clear(); // clear the component array to prepare for the next building of question
                }
            }

        } catch (IOException e) {
            throw new IllegalArgumentException("File path does't exist.");
        }
    }

    private ArrayList<String> buildTailQuest(ArrayList<String> component) {
        ArrayList<String> tailQuestion = new ArrayList<>();
        for (String tail : component) {
            if (tail.matches("[(].[)].*")) { // regex to define a tail of a question
                tailQuestion.add(tail);
            }
        }

        return tailQuestion;
    }

    private String buildCorrectAnswer(ArrayList<String> component) {
        String correctAnswer = "";
        for (String string : component) {
            if (string.matches("[(].[)].*[<]")) { // regex to define correct answer of a question
                correctAnswer = string;
            }
        }

        return correctAnswer;
    }

    private void buildPickedQuest(ArrayList<Integer> ranums, ArrayList<Question> groupQuest) {
        for (int c = 0; c < ranums.size(); c++) {
            pickedQuest.add(groupQuest.get(ranums.get(c)));
        }
    }

    private void randomQuizQuest() {

        String currentChapter = "";
        String nextChapter = "";
        ArrayList<Question> groupQuests = new ArrayList<>();
        ArrayList<Integer> ranums = new ArrayList<>();

        for (int i = 0; i < questionsBank.size(); i++) {
            currentChapter = questionsBank.get(i).getChapter().toString();

            // if i reaches to the end, set nextChapter to the chapter of the first question in questionsBank array
            if (i == questionsBank.size() - 1) {
                nextChapter = questionsBank.get(0).getChapter().toString();
            } else {
                nextChapter = questionsBank.get(i + 1).getChapter().toString();
            }

            // check if currentChapter equals nextChapter, add that question to the groupQuest array
            if (currentChapter.equals(nextChapter)) {
                groupQuests.add(questionsBank.get(i));
            } else {
                groupQuests.add(questionsBank.get(i));
                ranums = random(groupQuests.size() - 1); // random three numbers in order to pick 3 random questions of each chapter
                buildPickedQuest(ranums, groupQuests); // build pickedQuest array, which will be used for the test
                groupQuests.clear(); // clear groupQuests array to prepare for the next group of question
            }

        }
    }

    private ArrayList<Integer> random(int max) {
        ArrayList<Integer> ranums = new ArrayList<>();
        int min = 0;

        while (ranums.size() < 3) {
            int randomNum = random.nextInt((max - min) + 1) + min;
            if (checkDuplicate(ranums, randomNum)) { // check if duplicating number, then will ignore it
                ranums.add(randomNum);
            }
        }
        return ranums;
    }

    private boolean checkDuplicate(ArrayList<Integer> arr, int num) {
        for (int value : arr) {
            if (value == num) {
                return false;
            }
        }
        return true;
    }

    public void setUserResult(String name) {
        this.userResult = new UserResult(name, pickedQuest);
    }

    // == public methods ==
    public void runTest() throws IOException{ // run test on command line
        String userAnswer = "";
        String studentName = "";
        Scanner scanner = new Scanner(System.in);

        System.out.printf("%5s%s", "*****", "ENTER STUDENT NAME: ");
        studentName = scanner.nextLine();
        System.out.printf("%6s%s%s%6s%n", "***** ", "FINAL QUIZ - ", studentName, " *****");

        int count = 1;
        for (Question question : pickedQuest) {
            System.out.printf("%d%s%s%n", count, ". ", question.getHeadQuestion());
            for (StringBuilder stringBuilder : question.getTailQuestion()) {
                System.out.println(stringBuilder);
            }

            userAnswer = scanner.next().toUpperCase();
            question.setState(userAnswer);
            question.setUserAnswer(userAnswer);

            if (question.isState()) {
                System.out.println("* User Answer: " + question.getUserAnswer() + " - Correct!" + "\n\n");
            } else {
                System.out.println("* User Answer: " + question.getUserAnswer() + " - Incorrect! Correct Answer is: " + question.getCorrectAnswer() + "\n\n");
            }


            count++;
        }

        setUserResult(studentName);
        System.out.println("Name: " + userResult.getUserName());
        System.out.println("Average: " + userResult.getAverage() + "%");
        System.out.println("Num of Right: " + userResult.getNumOfRight());
        System.out.println("Num of Wrong: " + userResult.getNumOfWrong());


        userResult.drawChartResult();
        userResult.exportResult();
//        for (Chart chart: userResult.getCharts()){
//            System.out.println(chart.getChapter() + " : " + chart.getStars());
//        }

//        for (Question quest : pickedQuest) {
//            System.out.println(quest.isState());
//        }


    }


}
