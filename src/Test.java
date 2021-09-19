import java.io.*;
public class Test {
    public static final String delimiter = ",";
    public static void read(String csvFile) {
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);
//                System.out.println(tempArr[3]);
//                String S1 = tempArr[3];
//                double di = Double.parseDouble(S1);
//                System.out.print(di);

            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public static void main(String[] args) {
        // csv file to read
        String csvFile = "C:\\FoodOrder\\src\\FoodInputs.csv";
        Test.read(csvFile);
    }
}