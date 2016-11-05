package abstractfactory;

/**
 * Created by spark on 11/5/16.
 */
public class InterFactory implements ComputerFactory {

    @Override
    public Cpu createCpu(int pins) {
        return new IntelCpu(pins);
    }

    @Override
    public Mainboard createMainBoard(int cpuHoles) {
        return new IntelMainboard(cpuHoles);
    }

}
