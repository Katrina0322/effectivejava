package com.oneapm.chap9;

/**
 * used to
 * Created by tianjin on 8/1/16.
 */
public class NestingInterfaces {
    public class BImp implements A.B{

        @Override
        public void f() {

        }
    }

    class CImp implements A.C{
        @Override
        public void f() {

        }
    }

//    class DImp implements A.D{
//        @Override
//        public void f() {
//
//        }
//    }


    class EImp implements E{
        @Override
        public void g() {

        }
    }

    class EGImp implements E.G{

        @Override
        public void f() {

        }
    }
}
