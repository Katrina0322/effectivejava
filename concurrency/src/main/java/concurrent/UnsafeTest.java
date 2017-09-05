package concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * filename: UnsafeTest
 * Description:
 * Author: ubuntu
 * Date: 9/5/17 3:05 PM
 */
public class UnsafeTest {
    public static void main(String[] args) {
        Unsafe unsafe = null;
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
             unsafe = (Unsafe) f.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            Field f1 = Test.class.getDeclaredField("age");
            assert unsafe != null;
            long iFiledAddressShift = unsafe.objectFieldOffset(f1);
            Test test = new Test(26, "aa", "bb", true);
            Object helperArray[] = new Object[1];
            helperArray[0] = test;
            long baseOffset = unsafe.arrayBaseOffset(Object[].class);
            long addressOfSampleClass = unsafe.getLong(helperArray, baseOffset);
            int i = unsafe.getInt(addressOfSampleClass + iFiledAddressShift);
            System.out.println(new StringBuilder().append(" Field I Address:").append(addressOfSampleClass).append("+").append(iFiledAddressShift).append(" Value:").append(i));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    public static void testMemory(Unsafe unsafe){
        assert unsafe != null;
        long allocatedAddress = unsafe.allocateMemory(1L);
        unsafe.putByte(allocatedAddress, (byte) 100);
        byte shortValue = unsafe.getByte(allocatedAddress);
        System.out.println(new StringBuilder().append("Address:").append(allocatedAddress).append(" Value:").append(shortValue));


        allocatedAddress = unsafe.reallocateMemory(allocatedAddress, 8L);
        unsafe.putLong(allocatedAddress, 1024L);
        long longValue = unsafe.getLong(allocatedAddress);
        System.out.println(new StringBuilder().append("Address:").append(allocatedAddress).append(" Value:").append(longValue));

        unsafe.freeMemory(allocatedAddress);
        longValue = unsafe.getLong(allocatedAddress);
        System.out.println(new StringBuilder().append("Address:").append(allocatedAddress).append(" Value:").append(longValue));
    }
}
