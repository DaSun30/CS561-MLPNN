import java.io.*;


public class Main {
    private static final int IMG_WIDTH = 28;
    private static final int IMG_HEIGHT = 28;
    private static final int INPUT_SIZE = IMG_WIDTH * IMG_HEIGHT;
    private static final int OUTPUT_SIZE = 10;

    public static void main(String[] args) {
    }

    public static void init() {
        File trainImageCsv = new File("./data/train_image.csv");
        File trainLabelCsv = new File("./data/train_label.csv");
        File testImageCsv = new File("./data/test_image.csv");

        try{
            BufferedReader br1 = new BufferedReader(new FileReader(trainImageCsv));
            BufferedReader br2 = new BufferedReader(new FileReader(trainLabelCsv));
            BufferedReader br3 = new BufferedReader(new FileReader(testImageCsv));
            String line1 = "", line2 = "", line3 = "";
            int lineCount = 0;

            while ((line1 = br1.readLine()) != null && (line2 = br2.readLine()) != null){
//                System.out.println("line" + lineCount++ + ": " + line);
                double[] input = new double[INPUT_SIZE * INPUT_SIZE];
                double[] output = new double[OUTPUT_SIZE];
                String[] numStringArr = line1.split(",");
                for(int i = 0; i < numStringArr.length; i++) {
                    input[i] = Double.parseDouble(numStringArr[i]);
                    output[i] = Integer.parseInt(line2);
                }
            }

            while((line3 = br3.readLine()) != null) {
                double[] input = new double[INPUT_SIZE * INPUT_SIZE];
                double[] output = new double[OUTPUT_SIZE];
                String[] numStringArr = line3.split(",");
                for(int i = 0; i < numStringArr.length; i++) {
                    input[i] = Double.parseDouble(numStringArr[i]);
                }
            }

            br1.close();
            br2.close();
            br3.close();
        }catch (FileNotFoundException e){
            System.out.println("file not found");
        }catch (IOException e){
            System.out.println("write file error");
        }
    }

    public static void outputCsv() {
        File outputCsv = new File("test_predictions.csv");
        try {
            FileWriter fileWriter = new FileWriter(outputCsv);
            BufferedWriter bw = new BufferedWriter(fileWriter);
//            bw.write();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    *   tool & math functions
    * */
    private static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    private static double sigmoidDeri(double x) {
        return x * (1 - x);
    }

    private static double crossEntropy(double px, double qx) {
        return - (Math.log(qx) * px);
    }

    public class Net {
        private double[][] output;
        private double[][][] weights;
        private double[][] bias;
        private double[][] error_signal;
        private double[][] output_derivative;
        public int inputSize;
        public int netSize;
        public int outputSize;

        public Net(int inputSize, int netSize, int outputSize) {
            this.inputSize = inputSize;
            this.netSize = netSize;
            this.outputSize = outputSize;
        }
    }
}
