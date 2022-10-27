/**
 * This class handles polynomials
 * @author Noor
 * @version 1
*/

import java.io.File;
import java.io.FileNotFoundException;
// import java.util.Arrays;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter; 

public class Polynomial {
    public double[] coefficients;
    public int[] exponents;

    public Polynomial(){
        this.coefficients = null;
        this.exponents = null;
    }

    public Polynomial(double[] coefficients, int[] exponents){
        this.coefficients = coefficients;
        this.exponents = exponents;
    }

    public Polynomial(File file) throws IOException{
        Scanner sc = new Scanner(file);
        String equation = sc.nextLine();
        String[] splitByPositive = equation.split("\\+");
        boolean firstIsNegative = equation.charAt(0) == '-';

        for (int i = 0; i < splitByPositive.length; i++){
            String[] splitByNegative = splitByPositive[i].split("-");
            for (int i2=0; i2< splitByNegative.length; i2++){
                String[] values = splitByNegative[i2].split("x");
                double coefficient = Double.parseDouble(values[0]);
                if (i2 > 0 || i==0 && firstIsNegative ){
                    coefficient *= -1;
                }

                int exponent = 0;
                if (values.length > 1){
                    exponent = Integer.parseInt(values[1]);
                }else if (splitByNegative[i2].indexOf("x") > -1){
                    exponent = 1;
                }

                Polynomial proper = this.add(new Polynomial(new double[] {coefficient}, new int[] {exponent}));
                this.coefficients = proper.coefficients;
                this.exponents = proper.exponents;
            }
        }  
        sc.close();
    }

    public Polynomial add(Polynomial extraPolynomial){
        int numTerms = -1;
        if (this.exponents == null && extraPolynomial.exponents == null){
            return new Polynomial();
        }

        if (this.exponents != null){
            for (int i = 0; i < this.exponents.length; i++) numTerms = Math.max(numTerms, this.exponents[i]);
        }
        
        if (extraPolynomial.exponents != null){
            for (int i = 0; i < extraPolynomial.exponents.length; i++) numTerms = Math.max(numTerms, extraPolynomial.exponents[i]);
        }

        numTerms += 1;
        double[] newCoefficients = new double[numTerms];
        for (int i = 0; i < numTerms; i++) newCoefficients[i] = 0;

        
        if (this.exponents != null){
            for (int i = 0; i < this.exponents.length; i++){
                newCoefficients[this.exponents[i]] += this.coefficients[i];
            }
        }

        if (extraPolynomial.exponents != null){
            for (int i = 0; i < extraPolynomial.exponents.length; i++){
                newCoefficients[extraPolynomial.exponents[i]] += extraPolynomial.coefficients[i];
            }
        }

        int numActiveTerms = 0;
        for (int i = 0; i < numTerms; i++){
            if (newCoefficients[i] != 0) numActiveTerms += 1;
        }

        double[] new_coefficients = new double[numActiveTerms];
        int[] new_exponents = new int[numActiveTerms];
        int c = 0;
        for (int i = 0; i < numTerms; i++){
            if (newCoefficients[i] != 0){
                new_coefficients[c] = newCoefficients[i];
                new_exponents[c] = i;
                c += 1;
            }
        }

        Polynomial result = new Polynomial(new_coefficients, new_exponents);
        return result;
    }
	
    public double evaluate(double value){
        double total = 0;
        for (int i = 0; i < this.coefficients.length; i++){
            total += this.coefficients[i] * Math.pow(value, this.exponents[i]);
        }

        return total;
    } 

    public Boolean hasRoot(double value){
        return evaluate(value) == 0;
    }

    public Polynomial multiply(Polynomial extraPolynomial){
        int numTerms = -1;
        if (this.exponents == null || extraPolynomial.exponents == null){
            return new Polynomial();
        }

        for (int i = 0; i < this.exponents.length; i++){
            for (int i2 = 0; i2 < extraPolynomial.exponents.length; i2++){
                numTerms = Math.max(numTerms, this.exponents[i] + extraPolynomial.exponents[i2]);
            }
        }

        numTerms += 1;
        double[] newCoefficients = new double[numTerms];
        for (int i = 0; i < numTerms; i++) newCoefficients[i] = 0;

        for (int i = 0; i < this.exponents.length; i++){
            for (int i2 = 0; i2 < extraPolynomial.exponents.length; i2++){
                newCoefficients[this.exponents[i] + extraPolynomial.exponents[i2]] += this.coefficients[i] * extraPolynomial.coefficients[i2];
            }
        }

        int numActiveTerms = 0;
        for (int i = 0; i < numTerms; i++){
            if (newCoefficients[i] != 0) numActiveTerms += 1;
        }

        double[] new_coefficients = new double[numActiveTerms];
        int[] new_exponents = new int[numActiveTerms];
        int c = 0;
        for (int i = 0; i < numTerms; i++){
            if (newCoefficients[i] != 0){
                new_coefficients[c] = newCoefficients[i];
                new_exponents[c] = i;
                c += 1;
            }
        }

        Polynomial result = new Polynomial(new_coefficients, new_exponents);
        return result;
    }

    public void saveToFile(String filepath) throws IOException{
        //File nf = new File(filepath);
        //nf.createNewFile();
        FileWriter fw = new FileWriter(filepath);
        String equation = "";
        if (this.exponents != null){
            for (int i = 0; i < this.exponents.length; i++){
                if (this.coefficients[i] > 0 && i > 0){
                    equation += "+";
                }
                equation += Double.toString(this.coefficients[i]);
                if (this.exponents[i] != 0){
                    equation += "x";
                    if (this.exponents[i] > 1){
                        equation += Integer.toString(this.exponents[i]);
                    }
                }
            }
        }

        fw.write(equation);
        fw.close();
    }

}
