package com.oneapm.chap10;

/**
 * used to
 * Created by tianjin on 8/1/16.
 */
public class Parcel1 {
    public static void main(String[] args) {
        Parcel1 p = new Parcel1();
        p.ship("Tasmaina");
    }

    public Destination to(String s) {
        return new Destination(s);
    }

    public Contents contents() {
        return new Contents();
    }

    public void ship(String dest) {
        Contents c = contents();
        Destination d = to(dest);
        System.out.println(d.readLabel());
    }

    class Contents {
        private int i = 11;

        public int value() {
            return i;
        }
    }

    class Destination {
        private String label;

        public Destination(String label) {
            this.label = label;
        }

        String readLabel() {
            return label;
        }
    }
}
