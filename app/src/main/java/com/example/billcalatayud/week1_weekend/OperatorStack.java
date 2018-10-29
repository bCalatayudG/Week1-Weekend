package com.example.billcalatayud.week1_weekend;

class OperatorStack {

    private int tope;
    private Operator []pila;

    public OperatorStack(int tam){
        pila = new Operator[tam];
        tope = -1;
    }

    public boolean ValidaVacio(){
        return tope == -1;
    }

    public void push(Operator Op){
        tope++;
        pila[tope]=Op;
    }

    public Operator pop(){
        Operator aux = pila[tope];
        tope--;
        return aux;
    }

    public char getDatoTope(){
        return pila[tope].getSimbol();
    }

    public int getJerarquiaTope(){
        return pila[tope].getJerarquia();
    }
/*
    public void listing(){
        for(int i = 0;i<=tope;i++){
            pila[i].listing();
        }
    }
*/
    public int getTope(){
        return tope;
    }

    public void eliminaParentesis(){
        tope--;
    }
}