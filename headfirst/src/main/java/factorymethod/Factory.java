package factorymethod;

/**
 * Created by spark on 11/5/16.
 */
public class Factory {
    /**
     * 反射
     * @param clazz
     * @param <T>
     * @return
     */
    public <T extends IProduct> T createProduct(Class<T> clazz){
        T product = null;
        try {
            product = (T) Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return product;
    }


    /**
     * 使用不同命名的静态方法
     * @return
     */
    public static IProduct createProductA(){
        return new ConcreteProductA();
    }

    public static IProduct createProductB(){
        return new ConcreteProductB();
    }

    /**
     * 根据穿参进行实例化
     * @param name
     * @return
     */
    public static IProduct createProduct(String name){
        IProduct product = null;
        switch (name){
            case "productA":
                product =  new ConcreteProductA();
                break;
            case "productB":
                product = new ConcreteProductB();
                break;
            default:product = null;
        }
        return product;
    }
}
