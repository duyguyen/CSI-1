package final_assigment;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class UserResult {

    // == instants ==
    private String userName;
    private String average;
    private int numOfWrong;
    private int numOfRight;
    private ArrayList<Chart> charts = new ArrayList<>();
    private ArrayList<Question> questions;

    // == constructor ==
    public UserResult(String userName, ArrayList<Question> questions) {
        this.userName = userName;
        this.questions = questions;
        calculateAverage();
        chartInfo();
    }

    // == public methods ==
    public void calculateAverage() {
        double count = 0;
        for (Question question : questions) {
            if (question.isState()) {
                count++;
            }
        }

        DecimalFormat f = new DecimalFormat("##.00");

        average = f.format((count / questions.size()) * 100);
        setNumOfRight((int) count);
        setNumOfWrong();
    }

    public void setNumOfRight(int numOfRight) {
        this.numOfRight = numOfRight;
    }

    public void setNumOfWrong() {
        this.numOfWrong = questions.size() - numOfRight;
    }

    public String getAverage() {
        return average;
    }

    public String getUserName() {
        return userName;
    }

    public int getNumOfWrong() {
        return numOfWrong;
    }

    public int getNumOfRight() {
        return numOfRight;
    }


    public void drawChartResult() {
        for (Chart chart : charts) {
            System.out.printf("%3s%s", chart.getChapter(), ": ");
            for (int i = 0; i < chart.getStars(); i++) {
                System.out.print("*");
            }
            System.out.printf("%9s", chart.getEvaluate());
            System.out.println();
        }
    }

    public void exportResult() throws IOException {
        try {
            PrintWriter printWriter = new PrintWriter(userName + ".txt", "UTF-8");
            String correction = "";
            int count = 1;

            printWriter.printf("%s%n%s%n%s%n%s%n%n%s%n", "Name: " + getUserName(), "Class: CSCI-1", "Instructor: ","-------------------------------------------------", "*****FINAL QUIZ*****");
            for (Question question : questions) {

                printWriter.println(count + ". " + question.getHeadQuestion());

                for (StringBuilder tail: question.getTailQuestion()){
                    printWriter.println(tail);
                }


                printWriter.println("*Correct Answer: " + question.getCorrectAnswer() + "; Chapter: " + question.getChapter() + "; Section: " + question.getSection());
                correction = (question.isState()) ? "Correct" : "Incorrect";
                printWriter.println("*User Answer: " + question.getUserAnswer() + " - " + correction);
                printWriter.println();

                count++;

            }

            printWriter.println("-------------------------------------------------");
            printWriter.println("***TOTAL***");
            printWriter.println("Average: " + getAverage() + "%");
            printWriter.println("Correct: " + getNumOfRight());
            printWriter.println("Incorrect: " + getNumOfWrong());
            printWriter.println();

            printWriter.println("***EVALUATION CHART***");
            for (Chart chart:charts){
                printWriter.printf("%3s%s", chart.getChapter(), ": ");
                for (int i = 0; i < chart.getStars(); i++) {
                    printWriter.print("*");
                }
                printWriter.printf("%9s", chart.getEvaluate());
                printWriter.println();
            }

            printWriter.close();


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Export denied!");
        } finally {
            System.out.println(userName + ".text has been created.");
        }
    }

    // == private methods ==
    private void chartInfo() {
        String currentChapter = "";
        String nextChapter = "";
        ArrayList<Question> groupQuests = new ArrayList<>();

        for (int i = 0; i < questions.size(); i++) {
            currentChapter = questions.get(i).getChapter().toString();

            // if i reaches to the end, set nextChapter to the chapter of the first question in questionsBank array
            if (i == questions.size() - 1) {
                nextChapter = questions.get(0).getChapter().toString();
            } else {
                nextChapter = questions.get(i + 1).getChapter().toString();
            }

            // check if currentChapter equals nextChapter, add that question to the groupQuest array
            if (currentChapter.equals(nextChapter)) {
                groupQuests.add(questions.get(i));
            } else {
                groupQuests.add(questions.get(i));
                charts.add(new Chart(currentChapter, countStar(groupQuests)));
                groupQuests.clear(); // clear groupQuests array to prepare for the next group of question
            }

        }
    }

    private int countStar(ArrayList<Question> group) {
        int count = 0;
        for (Question question : group) {
            if (question.isState()) {
                count++;
            }
        }
        return count;

    }


}
