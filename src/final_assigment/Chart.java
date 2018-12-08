package final_assigment;

public class Chart {

    // == instants ==
    private String chapter;
    private int stars;
    private String evaluate;

    // == constructor ==
    public Chart(String chapter, int stars){
        this.chapter = chapter;
        this.stars = stars;
        setEvaluate();
    }

    // == public methods ===
    public String getChapter() {
        return chapter;
    }

    public int getStars() {
        return stars;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(){
        if (stars == 3){
            evaluate = "Strong";
        }else if (stars == 2){
            evaluate = "Medium";
        }else if (stars == 1){
            evaluate = "Weak";
        }else {
            evaluate = "No Good!!!";
        }
    }
}
