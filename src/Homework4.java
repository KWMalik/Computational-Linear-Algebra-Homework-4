import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.lang.Double;
import java.util.Random;

/* Name: Kurry L Tran
 * UNI: klt2127
 * Computational Linear Algebra
 * Homework 4 - Least Squares Estimation
 * 
 *  
 * */

public class Homework4 {

	public static String filename;
	public static double C = 0.0;
	public static double D = 0.0;
	public static double E = 0.0;
	public static int m = 0;
	/**
	 * @param args
	 * @return 
	 */
	
	public static void LeastSquares(int m, double C, double D, double E){
		double y[] = new double[m];
		int time[] = new int[m];
		double bDoubleMatrix[][] = new double[m][1];
		
		double Adouble[][] = new double[m][3];
		
		// Initialize Time
		for(int i = 0; i < time.length; i++){
			time[i] = i;
		}
		
		for(int j = 0; j < y.length; j++){
			
			y[j] = C + D*time[j] + E*time[j]*time[j];
		}
		
		//Generate Perturbed Data
		Random generator = new Random();
		
		for(int i = 0; i < bDoubleMatrix.length; i++){
			
			int r = generator.nextInt(101);
			int sign = generator.nextInt(101);
			
			if(sign %2 == 0)
				bDoubleMatrix[i][0] = y[i] + (double)r/100.00;
			else
				bDoubleMatrix[i][0] = y[i] - (double)r/100.00;
			
		}
		
		for(int i = 0; i < m; i++){
				Adouble[i][0] = 1;
				Adouble[i][1] = time[i];
				Adouble[i][2] = time[i]*time[i];
		}
		
		Matrix A = new Matrix(Adouble);
		Matrix b = new Matrix(bDoubleMatrix);
		Matrix Atrans = A.transpose();
		
		Matrix leftAtA = Atrans.times(A);
		Matrix rightAtb = Atrans.times(b);
		
		Matrix x = leftAtA.solve(rightAtb);
		
		
	}
	
	
	
	public static void main(String[] args) {
		
		
		if(args.length != 0){
			filename = args[0];
		}else 
			filename = "input_file.txt";
		
		System.out.println("Reading Inputs From File: " + filename);

		/* Read Text File From File */
	      try{
	            /* Open File   */                                                                                   
	                                                                                                               
	            FileInputStream fstream = new FileInputStream(filename);

	            /* Get The Object of DataInputStream   */                                                           
	                                                                                                               
	            DataInputStream in = new DataInputStream(fstream);
	            BufferedReader br = new BufferedReader(new InputStreamReader(in));
	            String strLine;
	            int counter = 0;
	            
	            while ((strLine = br.readLine()) != null){
	                // Print the content n the console  
	            	if (counter == 0){
	            		m = Integer.parseInt(strLine);

	            	}
	            	else if (counter == 1 ){
	                	C = Double.parseDouble(strLine);
	                }
	            	else if(counter == 2){
	            		D = Double.parseDouble(strLine);
	            	}
	            	else if(counter == 3){
	            		E = Double.parseDouble(strLine);
	            	}
	            	counter++;
	            }
	            // Close the input stream                                                                         \
	                                                                                                               
	            in.close();
		
			
	      }
	      
	      catch(Exception e){//Catch exception if any                                                           \
              
	            System.err.println("Error: " + e.getMessage());

	        }
	      System.out.println("Number of Samples: "+m);
	      System.out.println("Input Parameters: "+ "C:"+C +" D:"+D +" E:"+ E );
	      LeastSquares(m,C,D,E);
	
	}// end Main Method
	
}
	
