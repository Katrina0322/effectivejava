package com.oneapm.chap10;

/**
 * used to
 * Created by tianjin on 8/1/16.
 */
public class Parcel5 {
    public static void main(String[] args) {
        Parcel5 p = new Parcel5();
        Destination d = p.destination("tianjin");
    }

    public Destination destination(String s) {
        class PDestination implements Destination {
            private String label;

            public PDestination(String label) {
                this.label = label;
            }

            @Override
            public String readLabel() {
                return label;
            }
        }
        return new PDestination(s);
    }
}
