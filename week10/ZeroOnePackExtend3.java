package llll;
 
import java.util.Scanner;
 
/**
 * 0/1����������֣���ǡ��װ�����������Ž�
 */
public class ZeroOnePackExtend3 {
    // N��ʾ����ĸ�����V��ʾ����������
    int N,V;
    //���ڴ洢ÿ��������������±��1��ʼ
    private int[] weight;
    //�洢ÿ����������棬�±��1��ʼ
    private int[] value;
    //����һά���飬��������ÿ��״̬�µ��������
    private int[] F;
    int INF = 9999;
 
    /**
     * ʹ�÷ǵݹ鷽ʽ
     */
    public void ZeroOnePackNonRecursive() {
        //���ڳ�ʼ��������
        F[0] = 0;
        for(int i = 1; i <= V; i++) {
            F[i] = -INF;
        }
 
        //ע��߽����⣬i�Ǵ�1��ʼ��
        for(int i = 1; i <= N; i++) {
            for(int j = V; j >= 0; j--) {
                //�������
                if(j >= weight[i]) {
                    F[j] = Math.max(F[j - weight[i]] + value[i], F[j]);
                }else {
                    //����ʡ��
                    F[j]= F[j];
                }
            }
        }
 
        //��ӡ���н��������Ҫ�����F[V]
        for(int i = 0; i <= V; i++) {
            System.out.print(F[i] + " ");
        }
        System.out.println();
    }
 
 
    /**
     * �����ʽ��
     5 10
     2 2 6 5 4
     6 3 5 4 6
     * result:14
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
        F= new int[V + 1];//ע���� V + 1
 
        for(int i = 1; i <= N; i++) {
            weight[i] = sc.nextInt();
        }
 
        for(int i = 1; i <= N; i++) {
            value[i] = sc.nextInt();
        }
    }
 
    public static void main(String[] args) {
        ZeroOnePackExtend3 zope2 = new ZeroOnePackExtend3();
        zope2.init();
        zope2.ZeroOnePackNonRecursive();
    }
}