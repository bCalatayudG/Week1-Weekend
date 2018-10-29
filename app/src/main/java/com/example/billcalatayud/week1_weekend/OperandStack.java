package com.example.billcalatayud.week1_weekend;

class OperandStack {
    private int tope;
    private double []pila;

    public OperandStack(int tam){
        pila = new double[tam];
        tope = -1;
    }

    public boolean ValidaVacio(){
        return tope == -1;
    }

    public void push(double dato){
        tope++;
        pila[tope]=dato;
    }

    public double pop(){
        double aux = pila[tope];
        tope--;
        return aux;
    }

    public double getDatoTope(){
        return pila[tope];
    }
/*
    public void listing(){
        for(int i=0;i<=tope;i++){
            System.out.print(pila[i] + " ");
        }
    }
*/
    public int getTope(){
        return tope;
    }
}