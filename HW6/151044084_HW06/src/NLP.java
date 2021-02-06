import java.io.File;
import java.io.IOException;
import java.lang.module.FindException;
import java.util.*;

public class NLP {
    public Word_Map wmap;
    private String[][] termscount; //Total number of terms in the document.

    /*You should not use File_Map class in this file since only word hash map is aware of it.
    In fact, you can define the File_Map class as a nested class in Word_Map,
     but for easy evaluation we defined it separately.
     If you need to access the File_Map instances, write wrapper methods in Word_Map class.
    * */

    /*Reads the dataset from the given dir and created a word map */
    public void readDataset(String dir) throws IOException {
        wmap = new Word_Map();

        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();

        termscount = new String[listOfFiles.length][2];

        Scanner scanner;
        int position;

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                position = 0;
                scanner = new Scanner(new File(dir + listOfFiles[i].getName()));
                String line;
                while (scanner.hasNextLine()) {
                    // read next line
                    line = scanner.nextLine();
                    StringTokenizer st = new StringTokenizer(line);
                    while (st.hasMoreElements()) {
                        String temp = st.nextElement().toString().replaceAll("\\p{Punct}", "");
                        if (!temp.equals("")) {
                            if (wmap.containsKey(temp)) {
                                if (((File_Map) wmap.get(temp)).containsKey(listOfFiles[i].getName())) {
                                    ((ArrayList<Integer>) ((File_Map) wmap.get(temp)).get(listOfFiles[i].getName())).add(position);
                                } else {
                                    ArrayList<Integer> tmp = new ArrayList<Integer>();
                                    tmp.add(position);
                                    ((File_Map) wmap.get(temp)).put(listOfFiles[i].getName(), tmp);
                                }
                            } else {
                                File_Map fileTmp = new File_Map();
                                ArrayList<Integer> tmp = new ArrayList<Integer>();

                                tmp.add(position);
                                fileTmp.put(listOfFiles[i].getName(), tmp);
                                wmap.put(temp, fileTmp);
                            }
                            position++;
                        }
                    }
                }

                scanner.close();
                termscount[i][0] = listOfFiles[i].getName();
                termscount[i][1] = Integer.toString(position);
            }
        }
    }


    /*Finds all the bigrams starting with the given word*/
    public List<String> bigrams(String word) {

        File_Map sourceFile = (File_Map) wmap.get(word);

        if (sourceFile == null)
            throw new FindException("This word cannot find in documents");

        ArrayList<String> fileName;
        ArrayList<List<Integer>> filepositions;
        List<String> resultList = new ArrayList<String>();

        if (sourceFile != null) {

            fileName = sourceFile.fnames;
            filepositions = sourceFile.occurances;
            for (int j = 0; j < fileName.size(); j++) {
                List<Integer> positions = filepositions.get(j);
                for (int k = 0; k < positions.size(); k++) {
                    String result = checkBiGram(fileName.get(j), positions.get(k));
                    if (result != null) {
                        if (!resultList.contains(word + " " + result))
                            resultList.add(word + " " + result);
                    }
                }
            }
        }
        return resultList;
    }

    //helper function
    private String checkBiGram(String filename, int position) {
        for (Object str : wmap.keySet()) {

            File_Map tmp = (File_Map) wmap.get(str);
            ArrayList<String> tmpFileName;
            List<Integer> tmpFilePositions;

            if (tmp != null) {
                tmpFileName = tmp.fnames;

                for (int i = 0; i < tmpFileName.size(); i++) {
                    if (tmpFileName.get(i).equals(filename)) {
                        tmpFilePositions = tmp.occurances.get(i);

                        for (int j = 0; j < tmpFilePositions.size(); j++) {
                            if (tmpFilePositions.get(j).equals(position + 1)) {
                                return (String) str;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    //helper function Number of times term t appears in a document)
    private int countTerm(String filename, String word) {

        File_Map tmp = (File_Map) wmap.get(word);
        ArrayList<String> tmpFileName;

        if (tmp != null) {
            tmpFileName = tmp.fnames;
            for (int i = 0; i < tmpFileName.size(); i++) {
                if (tmpFileName.get(i).equals(filename)) {
                    return tmp.occurances.get(i).size();
                }

            }
        }
        return 0;
    }


    /*Calculates the tfIDF value of the given word for the given file */
    public float tfIDF(String word, String fileName) {

        File_Map tmp = (File_Map) wmap.get(word);

        float TF = 0;
        float IDF = 0;

        boolean flag = true;
        int terms = 0;//Total number of terms in the document.

        for (int i = 0; i < termscount.length && flag; i++) {
            if (termscount[i][0].equals(fileName)) {
                flag = false;
                terms = Integer.parseInt(termscount[i][1]);
            }
        }

        if (terms <= 0 || tmp == null)
            throw new FindException("This word cannot find in documents");

        TF = (float) countTerm(fileName, word) / terms;
        IDF = (float) Math.log(((float) termscount.length / tmp.size()));

        return TF * IDF;
    }

    /*Print the WordMap by using its iterator*/
    public void printWordMap() {
        for (Object obj : wmap) {
            System.out.println(obj);
        }
    }
}
