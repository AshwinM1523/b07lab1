import java.io.*;

public class Driver { 
         public static void main(String [] args) { 
        //   Polynomial p = new Polynomial(); 
        //   System.out.println(p.evaluate(3)); 

        //   double [] c1 = {6,0,0,5}; 
        //   Polynomial p1 = new Polynomial(c1); 
        //   double [] c2 = {0,-2,0,0,-9}; 
        //   Polynomial p2 = new Polynomial(c2); 
        //   Polynomial s = p1.add(p2); 

        //   System.out.println("s(0.1) = " + s.evaluate(0.1)); 
        //   if(s.hasRoot(1)) 
        //    System.out.println("1 is a root of s"); 
        //   else 
        //    System.out.println("1 is not a root of s");


        // MY TESTS

        Polynomial p = new Polynomial();
        if(p.coefficients == null){
            System.out.println("p by defualt is 0 polynomial");
        }

        double [] c1 = {3, 2, 1};
        int [] e1 = {3, 2, 1};
        Polynomial p1 = new Polynomial(c1, e1);

        // for(int i = 0; i < e1.length; i++){
        //     System.out.println(p1.expo[i]);
        //     System.out.println(p1.coefficients[i]);
        // }

        // Regular
        // double [] c2 = {3, 2, 1, 6, -3};
        // int [] e2 = {4, 2, 1, 0, 3};

        // ZERO addition Case
        // double [] c2 = {-3, -2, -1};
        // int [] e2 = {3, 2, 1};

        // Adding zero
        double [] c2 = {0};
        int [] e2 = {1};

        Polynomial p2 = new Polynomial(c2, e2);

        Polynomial s = p1.add(p2);
        // if(s.coefficients == null){
        //     System.out.println("s is 0");
        // }
        // else{
        //     for(int i = 0; i < s.coefficients.length; i++){
        //         System.out.println("expo: " + s.expo[i] + " coeff: " + s.coefficients[i]);
        //     }
        // }

        // Polynomial n = s.add(p1);
        // for(int i = 0; i < n.coefficients.length; i++){
        //     System.out.println("expo: " + n.expo[i] + " coeff: " + n.coefficients[i]);
        // }
        // Polynomial zero = new Polynomial();
        // System.out.println("zero(6) = " + zero.evaluate(6));


        // System.out.println("s(2) = " + s.evaluate(2));


        //   if(s.hasRoot(0))
        //    System.out.println("0 is a root of s"); 
        //   else 
        //    System.out.println("0 is not a root of s");


        // MULTIPLY
        
        // Regular
        double [] c3 = {2, 2};
        int [] e3 = {1, 0};

        double [] c4 = {2};
        int [] e4 = {1};

        // Zero
        // double [] c4 = {0};
        // int [] e4 = {1};

        Polynomial p3 = new Polynomial(c3, e3);
        Polynomial p4 = new Polynomial(c4, e4);

        Polynomial t = p3.multiply(p4);

        // if(t.coefficients == null){
        //     System.out.println("t is the 0 polynomial");
        // }
        // else{
        //     for(int i = 0; i < t.coefficients.length; i++){
        //         System.out.println("expo: " + t.expo[i] + " coeff: " + t.coefficients[i]);
        //     }
        // }


        // Reading from file

        File file = new File("input.txt");
        Polynomial p5 = new Polynomial(file);
        if(p5.coefficients == null){
            System.out.println("Input text had 0 polynomial");
        }
        else{
            for(int i = 0; i < p5.coefficients.length; i++){
                System.out.println("expo: " + p5.expo[i] + " coeff: " + p5.coefficients[i]);
            }
        }

        double [] c8 = {2, 1};
        int [] e8 = {2, 0};

        double [] c9 = {0};
        int [] e9 = {1};

        Polynomial p9 = new Polynomial(c8, e8);
        // p9.saveToFile("testFile.txt");

    }
}