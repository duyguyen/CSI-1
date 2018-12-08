package final_assigment;

import java.io.IOException;

public class Driver {

    // main methods
    public static void main(String[] args) throws IOException {
        StructureTest structureTest = new StructureTest();
        structureTest.runTest();

//        Driver driver = new Driver();
//        driver.test();
    }

    public void test(){
        StringBuilder s1 = new StringBuilder("A");
        StringBuilder s2 = new StringBuilder("B");
        String s4 = "A";

        if (s4.equals(s2)){
            System.out.println("true");
        }

    }
}
