//Example using array objects (no list classes, no arrayList)
import java.io.*;
import java.util.Scanner;

public class Perceptron {
    private String FILENAME = "src/data.txt"; // Pseudo Constant
    private double[ ] inputData;
    private double[ ] weightData;
    private int outputValue;
    private double bias = 0.4;
    private double summation;
    private double threshold;
    private int size_arrays = 0;

    private void get_input_values(){
        BufferedReader user_input = new BufferedReader(new InputStreamReader(System.in));
        inputData = new double[size_arrays];
        System.out.println("Hay "+size_arrays+" pesos en el archivo.");
        for(int i=0; i<size_arrays; i++){
            System.out.print("Escribe el valor de entrada "+ (i+1)+": ");
            try {
                inputData[i] = Double.parseDouble(user_input.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void get_data(){
        try {
            Scanner read = new Scanner (new File(FILENAME));
            read.useDelimiter(" ");
            while(read.hasNext()){
                size_arrays++;
                read.next();
            }
            size_arrays--; //The first value is bias
            weightData = new double[size_arrays];

            Scanner read_again = new Scanner (new File(FILENAME));
            for(int i=0; i<size_arrays+1; i++){
                if(i==0)
                    bias = Double.parseDouble(read_again.next());
                else
                    weightData[i-1] = Double.parseDouble(read_again.next());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int activation_function(double threshold){
        if (threshold <= 0)
            return 0;
        else
            return 1;
    }

     private void perceptron_algorithm(){
        get_data();
        get_input_values();
        for(int i=0; i<size_arrays; i++){
            summation += inputData[i]* weightData[i];
        }
        threshold = summation + bias;
        outputValue = activation_function(threshold);
    }

    private void print_results(){
        System.out.print("Valores de entrada: ");
        for (int i=0; i<size_arrays; i++) {
            System.out.print(inputData[i]);
            System.out.print(" ");
        }
        System.out.println();
        System.out.print("Valores de los pesos: ");
        for (int i=0; i<size_arrays; i++) {
            System.out.print(weightData[i]);
            System.out.print(" ");
        }
        System.out.println();
        System.out.println("Umbral (bias): " + bias);
        System.out.println("Salida: " + outputValue);
    }

    public static void main(String[ ] args){
        System.out.println("Perceptron Simple");
        Perceptron perceptron = new Perceptron();
        perceptron.perceptron_algorithm();
        perceptron.print_results();
        System.out.println("Fin de la práctica");
    }
}