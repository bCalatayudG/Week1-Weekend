package com.example.billcalatayud.week1_weekend;

public class Operator {

    private char symbol;
    private int jerarquia;

    public Operator(char simb) {
        symbol = simb;
        jerarquia = setJerarquia(simb);
    }


    private int setJerarquia(char simb) {
        if (simb == '+' || simb == '-') {
            return 1;
        }
        if (simb == '*' || simb == '/') {
            return 2;
        }
        if (simb == '^') {
            return 3;
        }
        if (simb == '(') {
            return 4;
        }
        return -1;
    }

    public char getSimbol() {
        return symbol;
    }

    public void listing() {
        //System.out.print(symbol + " ");
    }

    public int getJerarquia() {
        return jerarquia;
    }
}
