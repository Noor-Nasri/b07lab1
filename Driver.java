import java.io.File;
import java.io.FileNotFoundException;
// import java.util.Arrays;
import java.io.IOException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws IOException
    {
        boolean testsOn = true;
        boolean gitHubOn = true;
        boolean attendanceOn = true;
        
        double finalScore = 0.0; // (15 MAX)

        if (attendanceOn)
            System.out.println("Attendance:\n 0.0 marks removed.\n----------------------------------");

        if (gitHubOn)
            System.out.println("GitHub:\n SCORE: 0.5 / 0.5\n----------------------------------");
        System.out.println("Polynomial.java:\n");

        // TEST #1 - Empty Constructor [0.1 marks]
            if (testsOn)
                System.out.println("TEST #1 - Empty Constructor [0.1 marks]");
            Polynomial p0 = new Polynomial();
            if ((p0.coefficients == null || p0.coefficients.length == 0) 
                && (p0.exponents == null || p0.exponents.length == 0))
            {
                if (testsOn) {System.out.println(" PASSED\n");}
                finalScore += 1;
            }
            else if (testsOn)
            {
                System.out.println(" FAILED\n");//: Empty Constructor should initialize coefficient"
                    //+ "and exponent arrays to be 'null' or empty.\n");
            }
            // System.out.println();

        // p1 == 6 -2x^1 + 5x^3
        double[] c1 = {6,-2,5};
        int[] e1 = {0,1,3};
        Polynomial p1 = new Polynomial(c1, e1);

        // p2 == 5 - 3x^2 + 7x^8
        double[] c2 = {5,-3,7};
        int[] e2 = {0,2,8};
        Polynomial p2 = new Polynomial(c2, e2);

        // TEST #2 - Normal Constructor [0.1 marks]
            if (testsOn)
                System.out.println("TEST #2 - Normal Constructor [0.1 marks]");
            boolean passedTest2 = p1.coefficients.length == p1.exponents.length;
            if (p1.coefficients.length == p1.exponents.length)
            {
                for (int i = 0; i < p1.coefficients.length; i++)
                {
                    if (!(
                        (p1.coefficients[i] == 6 && p1.exponents[i] == 0)
                        || (p1.coefficients[i] == -2 && p1.exponents[i] == 1)
                        || (p1.coefficients[i] == 5 && p1.exponents[i] == 3)
                    ))
                    {
                        passedTest2 = false;
                    }
                }
            }
            
            if (passedTest2)
            {
                if (testsOn) {System.out.println(" PASSED\n");}
                finalScore += 1;
            }
            else if (testsOn)
            {
                System.out.println(" FAILED\n");//: Normal Constructor should initialize coefficient"
                    //+ "and exponent arrays to their respective parameters (regardless of order).\n");
            }
            // System.out.println(System.getProperty("user.dir"));


            
        // TEST #3 - File Constructor [0.2 marks]
            if (testsOn)
                System.out.println("TEST #3 - File Constructor [0.2 marks]");
            
            Polynomial filePoly = new Polynomial(new File(System.getProperty("user.dir") + "/poly.txt"));
            boolean passedTest3 = filePoly.coefficients.length == filePoly.exponents.length;
            boolean contains0coeffTest3 = false;
            if (filePoly.coefficients.length == filePoly.exponents.length)
            {
                for (int i = 0; i < filePoly.coefficients.length; i++)
                {
                    if (!(
                        (filePoly.coefficients[i] == 5 && filePoly.exponents[i] == 0)
                        || (filePoly.coefficients[i] == -3 && filePoly.exponents[i] == 2)
                        || (filePoly.coefficients[i] == 7 && filePoly.exponents[i] == 8)
                    ))
                    {
                        if (filePoly.coefficients[i] == 0)
                        {
                            contains0coeffTest3 = true;
                        } else {
                            passedTest3 = false;
                        }
                    }
                }
            }

            if (passedTest3 && !contains0coeffTest3)
            {
                if (isRedundant(filePoly.exponents))
                {
                    if (testsOn) {
                        System.out.println(" PARTIAL-FAIL: Solution cannot contain any redundant exponents.\n"
                            + "                 -0.1 mark penalty.\n");
                    }
                    finalScore += 1;
                } else {
                    if (testsOn) {System.out.println(" PASSED\n");}
                    finalScore += 2;
                }
            }
            else if (passedTest3)
            {
                if (testsOn) {
                    System.out.println(" PARTIAL-FAIL: Solution cannot contain any '0.0' coefficients.\n"
                        + "                 -0.1 mark penalty.\n");
                }
                finalScore += 1;
            }
            else if (testsOn)
            {
                System.out.println(" FAILED\n");
            }

            

        // TEST #4 - hasRoot Method [0.1 marks]
            if (testsOn)
                System.out.println("TEST #4 - hasRoot Method [0.1 marks]");

            double[] hrtCoef = {1,-3,-6,8};
            int[] hrtExp = {3,2,1,0};
            // hrtPoly == x^3 - 3x^2 - 6x + 8 == (x-1)(x+2)(x-4) ==> hrtPoly == 0 at x = 1, -2, 4
            Polynomial hrtPoly = new Polynomial(hrtCoef, hrtExp);

            if (hrtPoly.hasRoot(1)
                && hrtPoly.hasRoot(-2)
                && hrtPoly.hasRoot(4)
            )
            {
                if (testsOn) {System.out.println(" PASSED\n");}
                finalScore += 1;
            }
            else
            {
                System.out.println(" FAILED");
            }

            // finalScore += (
            //     hrtPoly.hasRoot(1)
            //     && hrtPoly.hasRoot(-2)
            //     && hrtPoly.hasRoot(4)
            // ) ? (1) : (0.0);

        // TEST #5 - evaluate Method [0.1 marks]
            if (testsOn)
                System.out.println("TEST #5 - evaluate Method [0.1 marks]");

            double[] evalCoef = {6,-2,5,-9}; 
            int[] evalExp = {0,1,3,4};
            Polynomial evalPoly = new Polynomial(evalCoef, evalExp);

            boolean passedTest5 = evalPoly.evaluate(0.1) == 5.8041;

            if (passedTest5)
            {
                if (testsOn) {System.out.println(" PASSED\n");}
                finalScore += 1;
            }
            else if (testsOn)
            {
                System.out.println(" FAILED\n");//: File Constructor should initialize coefficient"
                    //+ "and exponent arrays from parsed file, but was unsuccessful.\n");
            }

        // TEST #6 - add Method [0.3 marks]
            if (testsOn)
                System.out.println("TEST #6 - add Method [0.3 marks]");

            // p1 == 6 - 2x^1 + 5x^3
            // p2 == 5 - 3x^2 + 7x^8
            // ==> p1 + p2 == 11 - 

            Polynomial sumPoly = p1.add(p2);

            boolean passedTest6 = sumPoly.coefficients.length == sumPoly.exponents.length;
            boolean contains0coefficient = false;
            if (sumPoly.coefficients.length == sumPoly.exponents.length)
            {
                for (int i = 0; i < sumPoly.coefficients.length; i++)
                {
                    if (!(
                        (sumPoly.coefficients[i] == 11 && sumPoly.exponents[i] == 0)
                        || (sumPoly.coefficients[i] == -2 && sumPoly.exponents[i] == 1)
                        || (sumPoly.coefficients[i] == 5 && sumPoly.exponents[i] == 3)
                        || (sumPoly.coefficients[i] == -3 && sumPoly.exponents[i] == 2)
                        || (sumPoly.coefficients[i] == 7 && sumPoly.exponents[i] == 8)
                    ))
                    {
                        if (sumPoly.coefficients[i] == 0)
                        {
                            contains0coefficient = true;
                        }
                        else {
                            passedTest6 = false;
                        }
                    }
                }
            }

            // if (passedTest6)
            // {
            //     if (testsOn) {System.out.println(" PASSED\n");}
            //     finalScore += 3;
            // }
            // else if (testsOn)
            // {
            //     System.out.println(" FAILED\n");//: File Constructor should initialize coefficient"
            //         //+ "and exponent arrays from parsed file, but was unsuccessful.\n");
            // }

            if (passedTest6 && !contains0coefficient)
            {
                if (isRedundant(sumPoly.exponents))
                {
                    if (testsOn) {
                        System.out.println(" PARTIAL-FAIL: Solution cannot contain any redundant exponents.\n"
                            + "                 -0.2 mark penalty.\n");
                    }
                    finalScore += 1;
                } else {
                    if (testsOn) {System.out.println(" PASSED\n");}
                    finalScore += 3;
                }
            }
            else if (passedTest6)
            {
                if (testsOn) {
                    System.out.println(" PARTIAL-FAIL: Solution cannot contain any '0.0' coefficients.\n"
                        + "                 -0.2 mark penalty.\n");
                }
                finalScore += 1;
            }
            else if (testsOn)
            {
                System.out.println(" FAILED\n");
            }


            
        // TEST #7 - multiply Method [0.4 marks]
            if (testsOn)
                System.out.println("TEST #7 - multiply Method [0.4 marks]");

            Polynomial prodPoly = p1.multiply(p2);

            boolean passedTest7 = prodPoly.coefficients.length == prodPoly.exponents.length;
            boolean contains0coeff = false;
            if (prodPoly.coefficients.length == prodPoly.exponents.length)
            {
                for (int i = 0; i < prodPoly.coefficients.length; i++)
                {
                    if (!(
                        (prodPoly.coefficients[i] == 35 && prodPoly.exponents[i] == 11)
                        || (prodPoly.coefficients[i] == -14 && prodPoly.exponents[i] == 9)
                        || (prodPoly.coefficients[i] == 42 && prodPoly.exponents[i] == 8)
                        || (prodPoly.coefficients[i] == -15 && prodPoly.exponents[i] == 5)
                        || (prodPoly.coefficients[i] == 31 && prodPoly.exponents[i] == 3)
                        || (prodPoly.coefficients[i] == -18 && prodPoly.exponents[i] == 2)
                        || (prodPoly.coefficients[i] == -10 && prodPoly.exponents[i] == 1)
                        || (prodPoly.coefficients[i] == 30 && prodPoly.exponents[i] == 0)
                    ))
                    {
                        // System.out.println(prodPoly.coefficients[i]);
                        if (prodPoly.coefficients[i] == 0)
                        {
                            contains0coeff = true;
                        }
                        else
                        {
                            passedTest7 = false;
                        }
                    }
                }
            }

            if (passedTest7 && !contains0coeff)
            {
                if (isRedundant(prodPoly.exponents))
                {
                    if (testsOn) {
                        System.out.println(" PARTIAL-FAIL: Solution cannot contain any redundant exponents.\n"
                            + "                 -0.3 mark penalty.\n");
                    }
                    finalScore += 1;
                } else {
                    if (testsOn) {System.out.println(" PASSED\n");}
                    finalScore += 4;
                }
            }
            else if (passedTest7)
            {
                if (testsOn) {
                    System.out.println(" PARTIAL-FAIL: Solution cannot contain any '0.0' coefficients.\n"
                        + "                 -0.3 mark penalty.\n");
                }
                finalScore += 1;
            }
            else if (testsOn)
            {
                System.out.println(" FAILED\n");
            }

            // System.out.println("prodPoly.coefficients == " + Arrays.toString(prodPoly.coefficients));
            // System.out.println("prodPoly.exponents == " + Arrays.toString(prodPoly.exponents));
            
        // TEST #8 - saveToFile Method [0.2 marks]
            if (testsOn)
                System.out.println("TEST #8 - saveToFile Method [0.2 marks]");

            String pathing = "D:/School/Fall2022/TA-CSCB07/b07lab1/fileoutputtest.txt";
            p1.saveToFile(pathing);

            File fileName = new File(pathing);
            Scanner sc = new Scanner(fileName);
            // System.out.println(sc.hasNextLine() ? sc.nextLine() : "POOPY");
            // System.out.println(sc)

            if (sc.hasNextLine())
            {
                String scNextLine = sc.nextLine();
                // System.out.println(scNextLine.equals("6.0-2.0x+5.0x3"));
                if ((
                    scNextLine.equals("6.0-2.0x+5.0x3")
                    || scNextLine.equals("6.0-2.0x+5x3")
                    || scNextLine.equals("6.0-2x+5.0x3")
                    || scNextLine.equals("6.0-2x+5x3")
                    || scNextLine.equals("6-2.0x+5.0x3")
                    || scNextLine.equals("6-2.0x+5x3")
                    || scNextLine.equals("6-2x+5.0x3")
                    || scNextLine.equals("6-2x+5x3")
                ))
                {
                    if (testsOn) {System.out.println(" PASSED\n");}
                    finalScore += 2;
                }
                else if (testsOn) {
                    System.out.println(" FAILED \n");
                }
            }
            else if (testsOn) {
                System.out.println(" FAILED\n");
            }

            sc.close();

            fileName.delete();
            

        

        // Polynomial s = p1.add(p2);
        
        // System.out.println("s.coefficients == " + Arrays.toString(s.coefficients));
        // System.out.println("s.exponents == " + Arrays.toString(s.exponents));
        // System.out.println("s.toString() == " + s.toString());
        // System.out.println("s(0.1) = " + s.evaluate(0.1));
        // if (s.hasRoot(1)) {
        //     System.out.println("1 is a root of s");
        // } else {
        //     System.out.println("1 is not a root of s");
        // }

        // if (s.hasRoot(1) 
        //     && s.evaluate(0.1) == 5.8041 
        //     && p.evaluate(3) == 0
        // ) {
        //     System.out.println(" Test 1 PASSED!");
        // } else {
        //     System.out.println(" Test 1 FAILED!");
        // }
        
        // TEST #1:
        // hasRoot test (hrt) -- 0.1 / 1.5
        

        // TEST #1:
        // Polynomial

        System.out.println(" SCORE: " + Math.max(Math.min(finalScore/10, 1.5), 0) + " / 1.5\n");
    }

    public static boolean containsVal(double[] arr, double val)
    {
        for (double num : arr)
        {
            if (num == val)
                return true;
        }

        return false;
    }

    public static boolean containsVal(int[] arr, int val)
    {
        for (int num : arr)
        {
            if (num == val)
                return true;
        }

        return false;
    }

    public static int[] cloneArray(int[] arr)
    {
        int[] res = new int[arr.length];

        for (int i = 0; i < arr.length; i++)
        {
            res[i] = arr[i];
        }

        return res;
    }

    public static double[] cloneArray(double[] arr)
    {
        double[] res = new double[arr.length];

        for (int i = 0; i < arr.length; i++)
        {
            res[i] = arr[i];
        }

        return res;
    }

    public static boolean isRedundant(int[] exps)
    {
        boolean containsDupliactes = false;

        for (int i = 0; i < exps.length; i++)
        {
            for (int j=i+1; j < exps.length; j++)
            {
                if (i != j && exps[i] == exps[j])
                    containsDupliactes = true;
            }
        }

        return containsDupliactes;
    }
}
