package part3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PerceptronLearning {
	
	public static List<Instance> instances = new ArrayList<Instance>();
	public static List<Feature> features = new ArrayList<Feature>();
	public static List<ArrayList<Integer>> images = new ArrayList<ArrayList<Integer>>();
	public static List<Integer> perceptron = new ArrayList<Integer>();
	public static int convergence = 0;
	
	
	public static void main(String[] args){
		
		readData(args);

		generateFeatures();
		
		populateImages();

		initialisePerceptron();

		perceptronAlgorithm();
		
		generateResult();
		
		//print weights
		System.out.println("\nWeights:");
		int count = 0;
		for(Integer p : perceptron){
			System.out.print("w"+count+": "+p+" , ");
			count++;
		}
		
		//print features
		System.out.println("\n\nFeatures:");
		count = 1;
		for(Feature f :features){
			System.out.println(count+": "+f.toString());
			count++;
		}
		
	}

	private static void generateResult() {
		int correct = 0;
		int wrong = 0;
		for(ArrayList<Integer> i : images){
			 int output = 0;
			 for(int k = 0; k < i.size(); k++){
				 output += i.get(k)*perceptron.get(k);
			 }
			 String category = "";
			 if(output >= 0){
				 category = "yes";
			 }else if(output < 0){
				 category = "other";
			 }
			 int index = images.indexOf(i);
			 String c = instances.get(index).getCategory();
			 if(category.equals(c)){
				 correct++;
			 }
			 else{
				 wrong++;
			 }
		}
		
		if(convergence != 0){
			System.out.println("Convergence achieved in "+convergence+" Cycles");
		}
		else{
			System.out.println(correct+" images classified correctly");
			System.out.println(wrong+" images classified wrongly");
		}
	}

	private static void perceptronAlgorithm() {
		boolean pCorrect = false;
		int count = 0;
		while(!pCorrect && count < 1000){
			pCorrect = true;
			for(ArrayList<Integer> i :images){
				 int output = 0;
				 for(int k = 0; k < i.size(); k++){
					 output += i.get(k)*perceptron.get(k);
				 }
				 String category = "";
				 if(output >= 0){
					 category = "yes";
				 }else if(output < 0){
					 category = "other";
				 }
				 int index = images.indexOf(i);
				 String c = instances.get(index).getCategory();
				 if(category.equals(c)){
					 
				 }
				 else if(category.equals("yes")){
					 for(int x = 0; x < i.size(); x++){
						 perceptron.set(x, perceptron.get(x)-i.get(x));
					 }
					 pCorrect = false;
				 }
				 else if(category.equals("other")){
					 for(int y = 0; y < i.size(); y++){
						 perceptron.set(y, perceptron.get(y)+i.get(y));
					 }
					 pCorrect = false;
				 }
			}
			count++;
		}
		if(pCorrect){
			convergence = count;
		}
	}

	private static void initialisePerceptron() {
		//initial perceptron weights
		Random r = new Random(1);
		for(int i = 0; i < 51; i++){
			perceptron.add(r.nextInt(5));
		}
	}

	private static void populateImages() {
		for(Instance i : instances){
			ArrayList<Integer> image = new ArrayList<Integer>();
			image.add(1);
			for(Feature f : features){
				image.add(f.getValue(i));
			}
			images.add(image);
		}
	}
	
	private static void generateFeatures() {
		Random r = new Random(1);
		for(int i = 0; i < 50; i++){
			int[] row = new int[4];
			int[] col = new int[4];
			boolean[] sgn = new boolean[4];
			
			
			for(int j = 0; j < 4; j++){
				row[j] = r.nextInt(10);
				col[j] = r.nextInt(10);
				sgn[j] = r.nextBoolean();
			}
			features.add(new Feature(row,col,sgn));
		}
	}

	private static void readData(String[] args) {
		try {
            File file = new File(args[0]);

            Scanner input = new Scanner(file);
            
            java.util.regex.Pattern bit = java.util.regex.Pattern.compile("[01]");

            while (input.hasNext()) {
            	 if (!input.next().equals("P1")) System.out.println("Not a P1 PBM file" );
            	 String category = input.next().substring(1);
            	 int rows = input.nextInt();
            	 int cols = input.nextInt();
            	 boolean[][] image = new boolean[rows][cols];
            	 for (int r=0; r<rows; r++){
            		 for (int c=0; c<cols; c++){
            			 image[r][c] = (input.findWithinHorizon(bit,0).equals("1"));
            		 }
            	 }
            	 instances.add(new Instance(category, image));
            }
            input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
