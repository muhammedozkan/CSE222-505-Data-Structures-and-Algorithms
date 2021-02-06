import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {

        try {
            if (args.length < 1)
                System.out.println("Please insert the command line argument(input file ex. input.txt).");
            else {

                BufferedReader br = new BufferedReader(new FileReader(args[0]));

                String temp = br.readLine();

                if (temp != null)//if file is empty,don't run
                {
                    StringTokenizer st = new StringTokenizer(temp);

                    int vertex = Integer.parseInt(st.nextElement().toString());
                    int relation = Integer.parseInt(st.nextElement().toString());


                    Graph grp = new Graph(vertex);

                    do {
                        temp = br.readLine();
                        st = new StringTokenizer(temp);

                        int source = Integer.parseInt(st.nextElement().toString());
                        int dest = Integer.parseInt(st.nextElement().toString());
                        grp.insert(new Edge(source, dest));

                    } while (--relation != 0);

                    System.out.println("Output: "+grp.numOfPoP());

                    // System.out.println(grp);
                }
                br.close();

            }
        } catch (Exception e) {
            System.out.println("Please check the command line argument ");
            System.out.println(e);
        }


    }
}
