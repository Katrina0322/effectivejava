package org.microframe.rpc.chap9;

/**
 * used to
 * Created by tianjin on 8/1/16.
 */
public class A {
    private D dRef;

    public D getD() {
        return new DImp2();
    }

    public void receiveD(D d) {
        dRef = d;
        dRef.f();
    }

    interface B {
        void f();
    }

    public interface C {
        void f();
    }

    private interface D {
        void f();
    }

    public class BImp implements B {
        @Override
        public void f() {

        }
    }

    private class BImp2 implements B {

        @Override
        public void f() {

        }
    }

    class CImp implements C {

        @Override
        public void f() {

        }
    }

    private class CImp2 implements C {

        @Override
        public void f() {

        }
    }

    private class DImp implements D {

        @Override
        public void f() {

        }
    }

    public class DImp2 implements D {
        @Override
        public void f() {

        }
    }
}
