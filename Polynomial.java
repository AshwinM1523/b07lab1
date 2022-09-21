public class Polynomial{

		double[] polynomialArr;

		public Polynomial(){
			polynomialArr = new double[1];
		}

		public Polynomial(double[] arr){
			polynomialArr = new double[arr.length];
			for(int i = 0; i < arr.length; i++){
				polynomialArr[i] = arr[i];
			}
		}

		public Polynomial add(Polynomial addArr){
			Polynomial p;

			if(addArr.polynomialArr.length >= polynomialArr.length){
				p = new Polynomial(addArr.polynomialArr);

				for(int i = 0; i < polynomialArr.length; i++){
					p.polynomialArr[i] += polynomialArr[i];
				}
			}
			else{
				p = new Polynomial(polynomialArr);

				for(int i = 0; i < addArr.polynomialArr.length; i++){
					p.polynomialArr[i] += addArr.polynomialArr[i];
				}
			}

			return p;
			
		}

		public double evaluate(double x){
			
			double result = 0;
			if(polynomialArr.length == 1){
				return polynomialArr[0];
			}
			else{
				result = polynomialArr[0];
				for(int i = 1; i < polynomialArr.length; i++){
					result += polynomialArr[i]*Math.pow(x, i);
				}
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

}