import java.util.Comparator;

public class Comparators {
    //rgb 3 item r=s[0] g=s[1] b=s[2]
    public static class LexComparator implements Comparator<Integer[]> {
        public int compare(Integer s1[], Integer s2[]) {

            for (int i = 0; i < s1.length; ++i) {
                if (s1[i].equals(s2[i]) == false) {
                    return s1[i] - s2[i];
                }
            }
            return 0;
        }
    }

    public static class EucComparator implements Comparator<Integer[]> {
        private double calcL2Norm(Integer s[]) {
            double L2Norm = Math.sqrt((s[0] * s[0]) + (s[1] * s[1]) + (s[2] * s[2]));
            return L2Norm;
        }

        public int compare(Integer s1[], Integer s2[]) {
            //s1 L2 norm
            double s1L2Norm = calcL2Norm(s1);
            //s2 L2 norm
            double s2L2Norm = calcL2Norm(s2);

            if (s1L2Norm < s2L2Norm)
                return -1;
            else if (s1L2Norm > s2L2Norm)
                return 1;
            return 0;
        }
    }


    public static class BmxComparator implements Comparator<Integer[]> {
        private int convertBitMix(int r, int g, int b) {
            //calculate bitmix version with bitwise shifting
            return (r & 0x80 | g >> 1 & 0x40 | b >> 2 & 0x20 | r >> 2 & 0x10 | g >> 3 & 0x08 | b >> 5 & 0x04 | r >> 4 & 0x02 | g >> 5 & 0x01) << 16 |
                    (b << 2 & 0x80 | r << 2 & 0x40 | g << 1 & 0x20 | b & 0x10 | r & 0x08 | g >> 1 & 0x04 | b >> 2 & 0x02 | r >> 2 & 0x01) << 8 |
                    (g << 5 & 0x80 | b << 4 & 0x40 | r << 4 & 0x20 | g << 3 & 0x10 | b << 2 & 0x08 | r << 2 & 0x04 | g << 1 & 0x02 | b & 0x01);
        }

        public int compare(Integer s1[], Integer s2[]) {
            int bitMix1 = convertBitMix(s1[0], s1[1], s1[2]);
            int bitMix2 = convertBitMix(s2[0], s2[1], s2[2]);

            if (bitMix1 < bitMix2)
                return -1;
            else if (bitMix1 > bitMix2)
                return 1;
            return 0;
        }
    }
}