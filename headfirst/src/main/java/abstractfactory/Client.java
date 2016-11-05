package abstractfactory;

/**
 * Created by spark on 11/5/16.
 */
public class Client {
    public static void main(String[] args) {
        ComputerFactory factory1 = new InterFactory();
        Cpu cpu1 = factory1.createCpu(10);
        Mainboard mainboard1 = factory1.createMainBoard(20);
        cpu1.calculate();
        mainboard1.installCPU();


        ComputerFactory factory2 = new AmdFactory();
        Cpu cpu2 = factory2.createCpu(10);
        Mainboard mainboard2 = factory2.createMainBoard(20);
        cpu2.calculate();
        mainboard2.installCPU();
    }
}
