package stringsort;

/**
 * Created by spark on 11/30/16.
 */
public class KeyIndex {
    /**
     * 使用键索引计数法对学生数组a，以键key进行分组排序
     *
     * @param a
     *            学生数组a
     * @param R
     *            分组个数R
     */
    public static void sort(Student[] a, int R) {
        int N = a.length;
        Student[] aux = new Student[N];
        int[] count = new int[R + 1];

        // 第一步，计算出现的频率
        for (int i = 0; i < N; i++) {
            count[a[i].key() + 1]++;
        }

        //第二步，将频率转换成索引
        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r];
        }

        //第三步， 将元素分类
        for (int i = 0; i < N; i++) {
            aux[count[a[i].key()]++] = a[i];
        }

        // 第四步，回写
        for (int i = 0; i < N; i++) {
            a[i] = aux[i];
        }
    }

    public static void main(String[] args) {
        Student stu1 = new Student("cac", 2);
        Student stu2 = new Student("fnc", 2);
        Student stu3 = new Student("edb", 4);
        Student stu4 = new Student("oel", 0);
        Student stu5 = new Student("akf", 2);
        Student stu6 = new Student("erk", 1);

        Student[] stus = { stu1, stu2, stu3, stu4, stu5, stu6 };
        sort(stus, 5);

        for (int i = 0; i < stus.length; i++) {
            System.out.println(stus[i].toString());
        }
    }
}

class Student {
    private String name;
    private int key;

    public Student(String name, int key) {
        super();
        this.name = name;
        this.key = key;
    }

    public int key() {
        return key;
    }

    @Override
    public String toString() {
        return name + ":" + key;
    }
}
