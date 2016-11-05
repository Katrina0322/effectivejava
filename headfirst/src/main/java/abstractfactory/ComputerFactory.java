package abstractfactory;

/**
 * Created by spark on 11/5/16.
 */
public interface ComputerFactory {
    Cpu createCpu(int pins);
    Mainboard createMainBoard(int cpuHoles);
}
