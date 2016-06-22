package hashing;

/**
 * used to
 * Created by tianjin on 6/22/16.
 */
public class HashBean {

    private String name;

    private double a;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HashBean hashBean = (HashBean) o;

        if (Double.compare(hashBean.a, a) != 0) return false;
        return name != null ? name.equals(hashBean.name) : hashBean.name == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(a);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
