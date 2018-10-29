package com.example.billcalatayud.week1_weekend;

public class PosfixStack {


    private String []pila;
    private int tope;


    public PosfixStack(int tam){
        pila = new String[tam];
        tope=-1;
    }


    public void push(String dato){
        tope++;
        pila[tope]=dato;
    }
/*
    public String pop(){
        String aux = pila[tope];
        tope--;
        return aux;
    }

    public String getDatoTope(){
        return pila[tope];
    }

    public void listing(){
        for(int i=0;i<=tope;i++){
            System.out.print(pila[i]+", ");
        }
    }

    public boolean ValidaEspacio(){
        return(tope<(pila.length-1));
    }

    public boolean ValidaVacio(){
        return tope==-1;
    }
    */
}
