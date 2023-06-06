package com.table.editabletableview.model;

public class User {

    String id, A, B, C;

    public User(String id, String A, String B, String C) {
        this.id = id;
        this.A = A;
        this.B = B;
        this.C = C;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getVar(String var) {
        if (var.equals("A")) {
            return this.getA();
        }
        if (var.equals("B")) {
            return this.getB();
        }
        if (var.equals("C")) {
            return this.getC();
        }
        else {
            return "";
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", A='" + A + '\'' +
                ", B='" + B + '\'' +
                ", C='" + C + '\'' +
                '}';
    }
}
