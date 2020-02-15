import java.io.*;
public class Main {
    private static int[][] test;
    private static int res = 0;
    private static int res1 = 0;
    private static int res2 = 0;
    private static int time = 0;
    private static int time1 = 0;
    private static int time2 = 0;
    private static int array = 0;
    private static int point = 0;

    public static void main(String[] args) throws IOException {
        FileReader in = new FileReader("input.txt");
        FileWriter out = new FileWriter("output.txt");
        BufferedReader br = new BufferedReader(in);
        BufferedWriter bufferedWriter = new BufferedWriter(out);
        for (String a : br.readLine().split(" ")) {
            if (array == 0)
                array = Integer.parseInt(a);
            else
                point = Integer.parseInt(a);
        }
        int i = 0;
        test = new int[array][2];
        while (true) {
            String a = br.readLine();
            if (a == null)
                break;
            for (String b : a.split(" ")) {
                if (test[i][0] == 0)
                    test[i][0] = Integer.parseInt(b);
                else test[i][1] = Integer.parseInt(b);
            }
            i++;
        }
        sortM(test, 0, test.length - 1);
        res = 0;
        time = 0;
        check2();
        for (i = 0; i < test.length; i++) {
//            check2(i);
            if (res >= point)
                break;
            else {
                try {
                    if (test[i][0] * 1.0 / test[i][1] == test[i + 1][0] * 1.0 / test[i + 1][1])
                        if (check(i))
                            break;
                } catch (IndexOutOfBoundsException ignored) {}
                res += test[i][0];
                time += test[i][1];
            }
        }
        if (res < point&&res1<point)
            bufferedWriter.write(String.valueOf(-1));
        else if(time<time1||time1==0)
            bufferedWriter.write(String.valueOf(time));
        else
            bufferedWriter.write(String.valueOf(time1));
        br.close();
        bufferedWriter.close();
    }

    private static void check2(){
        int[][] hope  = new int [test.length][2];
        for(int j = 0; j<test.length;j++)
        {
            hope[j][0]=test[j][0];
            hope[j][1]=test[j][1];
        }
        for(int[] a:hope){
            if(a[0]==point)
                time1 = a[1];
        }
    }
    private static boolean check(int i) {

        while (test[i][0] * 1.0 / test[i][1] == test[i + 1][0] * 1.0 / test[i + 1][1]) {
            if (res + test[i][0] >= point) {
                res += test[i][0];
                time += test[i][1];
                return true;
            } else
                i++;
        }
        return false;
    }

    private static void print(int[][] a) {
        for (int[] inits : a) {
            System.out.println(inits[0] + "  " + inits[1]);
        }
    }

    private static void merge(int[][] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[][] L = new int[n1][2];
        int[][] R = new int[n2][2];
        System.arraycopy(arr, l, L, 0, n1);
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
        }
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if ((L[i][0] * 1.0) / L[i][1] == (R[j][0] * 1.0) / R[j][1]) {
                if (L[i][0] > R[j][0]) {
                    arr[k] = R[j];
                    j++;
                } else {
                    arr[k] = L[i];
                    i++;
                }
            } else if ((L[i][0] * 1.0) / L[i][1] > (R[j][0] * 1.0) / R[j][1]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    private static void sortM(int[][] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            sortM(arr, l, m);
            sortM(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }
}