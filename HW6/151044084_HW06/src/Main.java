import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        try {
            if (args.length < 1)
                System.out.println("Please insert the command line argument(input file ex. input.txt).");
            else {
                NLP nlp = new NLP();

                nlp.readDataset("dataset\\");

                BufferedReader br = new BufferedReader(new FileReader(args[0]));

                String temp = br.readLine();

                if (temp != null)//if file is empty,don't run
                {
                    do {
                        StringTokenizer st = new StringTokenizer(temp);

                        while (st.hasMoreElements()) {
                            if (st.nextElement().toString().equals("bigram")) {
                                System.out.println(nlp.bigrams(st.nextElement().toString()));
                                System.out.println();
                            } else {
                                System.out.println(nlp.tfIDF(st.nextElement().toString(), st.nextElement().toString()));
                                System.out.println();
                            }
                        }
                        temp = br.readLine();
                    } while (temp != null);
                }
                br.close();
            }
        } catch (Exception e) {
            System.out.println("Please check the command line argument ");
            System.out.println(e);
        }
    }
}