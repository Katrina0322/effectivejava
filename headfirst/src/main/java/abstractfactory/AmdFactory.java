package abstractfactory;

/**
 * Created by spark on 11/5/16.
 */
public class AmdFactory implements ComputerFactory {
    @Override
    public Cpu createCpu(int pins) {
        return new AmdCpu(pins);
    }

    @Override
    public Mainboard createMainBoard(int cpuHoles) {
        return new AmdMainboard(cpuHoles);
    }
}
