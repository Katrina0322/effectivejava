package abstractfactory;

/**
 * Created by spark on 11/5/16.
 */
public class IntelCpu implements Cpu{
    private int pins = 0;

    public IntelCpu(int pins) {
        this.pins = pins;
    }
    @Override
    public void calculate() {
        System.out.println("Intel CPU的针脚数：" + pins);
    }
}
