/**
 * This class handles polynomials
 * @author Noor
 * @version 1
*/

public class Polynomial {
    private double[] data;

    public Polynomial(){
        this.data = new double[] {0};
    }

    public Polynomial(double[] data){
        this.data = data;
    }

    public Polynomial add(Polynomial extraPolynomial){
        int numTerms = Math.max(this.data.length, extraPolynomial.data.length);
        double[] newData = new double[numTerms];

        for (int i = 0; i < numTerms; i++){
            if (i >= this.data.length){
                newData[i] = extraPolynomial.data[i];
            }else if (i >= extraPolynomial.data.length){
                newData[i] = this.data[i];
            }else{
                newData[i] = this.data[i] + extraPolynomial.data[i];
            }
        }

        Polynomial result = new Polynomial(newData);
        return result;
    }
	
    public double evaluate(double value){
        double total = 0;
        for (int i = 0; i < this.data.length; i++){
            total += this.data[i] * Math.pow(value, i);
        }

        return total;
    } 

    public Boolean hasRoot(double value){
        return evaluate(value) == 0;
    }
}
