package llll;
 
import java.util.Scanner;
 
/**
 * ʹ�ö�ά����ǵݹ�ķ������0/1��������
 */
public class ZeroOnePack {
    // N��ʾ����ĸ�����V��ʾ����������
    int N,V;
    //���ڴ洢ÿ��������������±��1��ʼ
    private int[] weight;
    //�洢ÿ����������棬�±��1��ʼ
    private int[] value;
    //��ά���飬��������ÿ��״̬�µ��������
    private int[][] F;
 
    /**
     * ʹ�÷ǵݹ鷽ʽ�����F[0 .. N][0 .. V]����forѭ�������������
     */
    public void ZeroOnePackNonRecursive() {
        //�Զ�ά����F���г�ʼ��
        for(int j = 0; j <= V; j++) {
            F[0][j] = 0;
        }
 
        //ע��߽����⣬i�Ǵ�1��ʼ�ģ�j�Ǵ�0��ʼ��
        //��ΪF[i - 1][j]��iҪ��1
        for(int i = 1; i <= N; i++) {
            for(int j = 0; j <= V; j++) {
                //�������Ϊj�ı����ŵ��µ�i������
                if(j >= weight[i]) {
                    F[i][j] = Math.max(F[i - 1][j - weight[i]] + value[i], F[i - 1][j]);
                }else {
                    //�Ų��£�ֻ��ѡ�񲻷ŵ�i������
                    F[i][j] = F[i - 1][j];
                }
            }
        }
 
        //��ӡ���н��������Ҫ�����F[N][V]
        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= V; j++) {
                System.out.print(F[i][j] + " ");
            }
            System.out.println();
        }
    }
 
 
    /**
     * ���F[n][m]�������ֵ����ѡȡ�ļ�����Ʒ�ܻ������ֵ����ֻ�����һ�����
     * @param n     ��ʾǰn�����壬n <= N
     * @param v     ��ʾ������������v <= V
     */
    public void printResult(int n, int v) {
        boolean[] isAdd = new boolean[n + 1];
 
        for(int i = n; i >= 1; i--) {
            if(F[i][v] == F[i-1][v])
                isAdd[i] = false;
            else {
                isAdd[i] = true;
                v -= weight[i];
            }
        }
 
        for(int i = 1; i <= n; i++) {
            System.out.print(isAdd[i] + " ");
        }
        System.out.println();
    }
 
    /**
     * �����ʽ��
     5 10
     2 2 6 5 4
     6 3 5 4 6
     * result:15
     * ��һ������������������ܿռ䣻
     * �ڶ�����ÿ������Ŀռ䣻
     * ��������ÿ����������档
     */
    public void init() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        V = sc.nextInt();
 
        //�±��1��ʼ����ʾ��1����Ʒ
        weight = new int[N + 1];
        value = new int[N + 1];
        F= new int[N + 1][V + 1];//ע���� N + 1����Ϊ��Ҫһ����ʼ״̬F[0][0]����ʾǰ0����Ʒ�Ž��ռ�Ϊ0�ı������������
 
        for(int i = 1; i <= N; i++) {
            weight[i] = sc.nextInt();
        }
 
        for(int i = 1; i <= N; i++) {
            value[i] = sc.nextInt();
        }
    }
    public void printResult1(int n, int v) {
        boolean[] isAdd = new boolean[n + 1];
 
        for(int i = n; i >= 1; i--) {
            if(F[i][v] == F[i-1][v])
                isAdd[i] = false;
            else {
                isAdd[i] = true;
                v -= weight[i];
            }
        }
 
        for(int i = 1; i <= n; i++) {
            System.out.print(isAdd[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        ZeroOnePack zop = new ZeroOnePack();
        zop.init();
        zop.ZeroOnePackNonRecursive();
        zop.printResult1(zop.N,zop.V);
    }
}