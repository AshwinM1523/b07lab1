import java.util.HashMap;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Polynomial{

		double[] coefficients;
		int[] expo;

		public Polynomial(){
			coefficients = null;
			expo = null;
		}

		public Polynomial(double[] co, int[] ex){
			// zero poly
			if(co.length == 1 && co[0] == 0){
				coefficients = null;
				expo = null;
				return;
			}
			coefficients = new double[co.length];
			expo = new int[ex.length];
			for(int i = 0; i < co.length; i++){
				coefficients[i] = co[i];
				expo[i] = ex[i];
			}
		}

		public Polynomial(File f){

			HashMap<Integer, Double> holder = new HashMap<>();
			String text = "";

				try{
					Scanner scan = new Scanner(f);
					text = scan.nextLine();
				}
				catch(Exception e){
					System.out.println("failed");
				}

				char[] textArray = text.toCharArray();
				if(textArray.length == 1 && textArray[0] == 0){
					coefficients = null;
					expo = null;
				}

				String strCoefficient = "";
				String strExpo = "";

				for(int i = 0; i < textArray.length; i++){
					// For first value pos
					if(i == 0 && textArray[i] != '-'){
						strCoefficient = (textArray[i] == '-') ? "-" : "";
						int k = i;
						for(k = i; k < textArray.length && textArray[k] != 'x' && textArray[k] != '-' && textArray[k] != '+'; k++){
							strCoefficient += textArray[k];
						}
						
						if(k < textArray.length && textArray[k] != 'x'){
							holder.put(0, Double.parseDouble(strCoefficient));
						}
						i = k - 1;
					}

					if(textArray[i] == '-' || textArray[i] == '+'){

						strCoefficient = (textArray[i] == '-') ? "-" : "";
						int k;
						int j = 0;
						for(k = i + 1; k < textArray.length && textArray[k] != 'x' && textArray[k] != '-' && textArray[k] != '+'; k++){
							strCoefficient += textArray[k];
							if(k + 1 < textArray.length && textArray[k + 1] == 'x'){
								j = 1;
							}
						}
						if(j == 0){
							holder.put(0, Double.parseDouble(strCoefficient));
						}
						i = k - 1;
					}

					// For expo
					if(textArray[i] == 'x'){
						strExpo = "";
						int k;
						for(k = i + 1; k < textArray.length && textArray[k] != '-' && textArray[k] != '+'; k++){
							strExpo += textArray[k];
						}
						i = k - 1;
						holder.put(Integer.parseInt(strExpo), Double.parseDouble(strCoefficient));
					}
				}

				// Convert back to poly

				coefficients = new double[holder.size()];
				expo = new int[holder.size()];
				int k = 0;
				for(int i : holder.keySet()){
					coefficients[k] = holder.get(i);
					expo[k] = i;
					k++;
				}

		}

		public Polynomial add(Polynomial addArr){
			Polynomial p;
			// Zero cases
			if(addArr.coefficients == null && coefficients == null){
				p = new Polynomial();
				return p;
			}
			else if(addArr.coefficients == null){
				p = new Polynomial(coefficients, expo);
				return p;
			}
			else if(coefficients == null){
				p = new Polynomial(addArr.coefficients, addArr.expo);
				return p;
			}

			HashMap<Integer, Double> PolyAdd = new HashMap<>();
			// Use hashset instead if order returned matters

			// Creates hashmap
			for(int i = 0; i < addArr.expo.length; i++){
				PolyAdd.put(addArr.expo[i], addArr.coefficients[i]);
			}

			// Adds polynomials
			for(int i = 0; i < expo.length; i++){
				if(PolyAdd.containsKey(expo[i])){
					// Remove if addition is 0 else add to value
					if(PolyAdd.get(expo[i]) + coefficients[i] == 0){
						PolyAdd.remove(expo[i]);
					}else{
						PolyAdd.put(expo[i], PolyAdd.get(expo[i]) + coefficients[i]);
					}
				}
				else{
					PolyAdd.put(expo[i], coefficients[i]);
				}
			}
			
			// If added together equals 0, should be null
			if(PolyAdd.size() == 0){
				p = new Polynomial();
				return p;
			}

			return HashToPoly(PolyAdd);

		}

		public double evaluate(double x){
			
			if(coefficients == null){
				return 0;
			}

			int result = 0;
			for(int i = 0; i < expo.length; i++){
				result += coefficients[i] * Math.pow(x, expo[i]); 
			}
			return result;
		}

		public boolean hasRoot(double root){
			double result = evaluate(root);
			if(result == 0){
				return true;
			}
			else{
				return false;
			}
		}

		public Polynomial multiply(Polynomial multiPoly){
			// Zero case
			if(multiPoly.coefficients == null || coefficients == null){
				Polynomial p = new Polynomial();
				return p;
			}

			HashMap<Integer, Double> tempMulti = new HashMap<>();

			for(int i = 0; i < multiPoly.expo.length; i++){
				for(int j = 0; j < expo.length; j++){

					int tempExpo = multiPoly.expo[i] + expo[j];
					double tempCo = multiPoly.coefficients[i] * coefficients[j];

					if(!tempMulti.containsKey(tempExpo)){
						tempMulti.put(tempExpo, tempCo);
					}
					else{
						tempMulti.put(tempExpo, tempMulti.get(tempExpo) + tempCo);
					}
				}
			}
			return HashToPoly(tempMulti);
		}

		public void saveToFile(String strPoly){
			try{
				FileWriter fw = new FileWriter(strPoly, true);

				if(coefficients == null){
					fw.write("0");
					fw.close();
					return;
				}

				for(int i = 0; i < expo.length; i++){
					if(i == 0){
						// System.out.println((expo[i] != 0) ? "x" + expo[i] : "");
						fw.write(String.valueOf((int)Math.round(coefficients[i])));
						fw.append((expo[i] != 0) ? "x" + expo[i] : "");
					}
					else{
						fw.append((coefficients[i] >= 0) ? "+" + (int)Math.round(coefficients[i]) : String.valueOf((int)Math.round(coefficients[i])));
						fw.append((expo[i] != 0) ? "x" + expo[i] : "");
					}
				}
				fw.close();
			}
			catch(IOException e){
				e.printStackTrace();
				System.out.println("Did not work");
			}

		}
		// HELPER
		public Polynomial HashToPoly(HashMap<Integer, Double> p){
			double[] newCoefficients = new double[p.size()];
			int[] newExpo = new int[p.size()];
			int k = 0;
			for(int i : p.keySet()){
				newCoefficients[k] = p.get(i);
				newExpo[k] = i;
				k++;
			}

			Polynomial p1 = new Polynomial(newCoefficients, newExpo);
			return p1;
		}
}