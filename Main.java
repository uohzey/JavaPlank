import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        int m, n;
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()) {
            n = sc.nextInt();
            m = sc.nextInt();
            int k = 0;
            for(int i = n; i <= m; i++){
                String ii = String.valueOf(i);
                int i1 = ii.charAt(0) - '0', i2 = ii.charAt(1) - '0', i3 = ii.charAt(2) - '0';
                if(i1 * i1 * i1 + i2 * i2 * i2 + i3 * i3 * i3 == i) {
                    k++;
                    if(k > 1){
                        System.out.print(" "+ i);
                    }
                    else{
                        System.out.print(i);
                    }
                }
            }
            if(k == 0) {
                System.out.print("no");
            }
            System.out.println();
        }
        sc.close();
    }
}