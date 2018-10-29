package com.example.billcalatayud.week1_weekend;

import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.content.res.Configuration;

//import java.util.Random;


//import java.util.Stack;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() + "_tag";

    Button one, two, three, four, five, six, seven, eight, nine, zero;
    Button add, sub, mult, dot, div, ac, eq, sqr, p, s, c, t, oP, cP, fact, perc;

    TextView tInput;
    TextView result;

    OperatorStack opsStack;
    OperandStack numStack;
    PosfixStack posStack;
    double pi = 3.141592653589;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tInput = findViewById(R.id.tInput);
        result = findViewById(R.id.result);


        //numbers
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);

        //operator
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        mult = findViewById(R.id.mult);
        dot = findViewById(R.id.dot);
        div = findViewById(R.id.div);
        ac = findViewById(R.id.ac);
        eq = findViewById(R.id.eq);
        sqr = findViewById(R.id.sqr);
        p=findViewById(R.id.p);
        s = findViewById(R.id.s);
        c = findViewById(R.id.c);
        t = findViewById(R.id.t);
        oP = findViewById(R.id.oP);
        cP = findViewById(R.id.cP);
        fact = findViewById(R.id.fact);
        perc = findViewById(R.id.per);

        //Numbers listeners
        one.setOnClickListener(clicked(one));
        two.setOnClickListener(clicked(two));
        three.setOnClickListener(clicked(three));
        four.setOnClickListener(clicked(four));
        five.setOnClickListener(clicked(five));
        six.setOnClickListener(clicked(six));
        seven.setOnClickListener(clicked(seven));
        eight.setOnClickListener(clicked(eight));
        nine.setOnClickListener(clicked(nine));

        //Operators listeners
        zero.setOnClickListener(clicked(zero));
        add.setOnClickListener(clicked(add));
        sub.setOnClickListener(clicked(sub));
        mult.setOnClickListener(clicked(mult));
        dot.setOnClickListener(clicked(dot));
        div.setOnClickListener(clicked(div));
        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tInput.setText("0");
                result.setText("");
            }
        });

        eq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opsStack = new OperatorStack(tInput.length());

                numStack = new OperandStack(tInput.length());
                posStack = new PosfixStack(tInput.length());
                try{
                    operate();
                }catch (Exception e){

                }
            }
        });

        perc.setOnClickListener(clicked(perc));

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            sqr.setOnClickListener(clicked(sqr));
            p.setOnClickListener(clicked(p));
            s.setOnClickListener(clicked(s));
            c.setOnClickListener(clicked(c));
            t.setOnClickListener((clicked(t)));
            oP.setOnClickListener(clicked(oP));
            cP.setOnClickListener(clicked(cP));
            fact.setOnClickListener(clicked(fact));
        }


    }

    public View.OnClickListener clicked(final Button btn) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tInput.getText().equals("0")) {
                    tInput.setText("");
                }
                //Log.d(TAG, ": "+btn.getText().toString());
                tInput.setText(tInput.getText().toString() + btn.getText().toString());
            }
        };
    }


    private void operate() {

        int j;
        String cad;

        char character;
        int i = 0;
        while (i < tInput.length()) {
            character = tInput.getText().toString().charAt(i);
            if(character == '%'){
                Operator aux = new Operator('%');
                OperaDosUltimos(aux);
            }
            else if (character == 't'){
                Operator aux = new Operator('t');
                i=i+2;
                OperaDosUltimos(aux);
            }
            else if (character == 'c'){
                Operator aux = new Operator('c');
                i=i+2;
                OperaDosUltimos(aux);
            }
            else if(character =='s'){
                Operator aux = new Operator('s');
                i=i+2;
                OperaDosUltimos(aux);
            }
            else if(character == '!'){
                Operator aux = new Operator('!');
                OperaDosUltimos(aux);
            }
            else if (character == '_'){
                Operator aux = new Operator(character);
                Log.d(TAG, numStack.getDatoTope()+" Operator: "+aux.getSimbol());
                OperaDosUltimos(aux);
            } else if (character == 'P' || character == 'p') {
                posStack.push("" + pi);
                numStack.push(pi);
            } else if (EsNumero(character) || EsPunto(character)) {
                cad = "" + character;
                if (i < (tInput.length() - 1)) {
                    j = i + 1;
                    character = tInput.getText().toString().charAt(j);
                    while ((EsNumero(character) || EsPunto(character)) && (j <= (tInput.length() - 1))) {
                        cad = "" + cad + character;
                        i++;
                        j++;
                        if (j < tInput.length()) {
                            character = tInput.getText().toString().charAt(j);
                            Log.d(TAG, "character" + character);
                        }
                    }
                }
                double num = Double.parseDouble(cad);
                posStack.push("" + num);
                numStack.push(num);
            } else {
                Operator aux = new Operator(character);
                if (aux.getSimbol() == ')') {
                    while (opsStack.getJerarquiaTope() != 4) {
                        posStack.push("" + opsStack.getDatoTope());
                        OperaDosUltimos(opsStack.pop());
                    }
                    opsStack.eliminaParentesis();
                } else {
                    if ((aux.getSimbol() == '-') && tInput.getText().toString().charAt(i - 1) == '(') {
                        cad = "";
                        j = i + 1;
                        character = tInput.getText().toString().charAt(j);
                        if (EsNumero(character) || EsPunto(character)) {
                            while ((EsNumero(character) || EsPunto(character)) && (j <= (tInput.length() - 1))) {
                                cad = "" + cad + character;
                                i++;
                                j++;
                                if (j < tInput.length()) {
                                    character = tInput.getText().toString().charAt(j);
                                }
                            }
                            double num = Double.parseDouble("-" + cad);
                            posStack.push("-" + cad);
                            numStack.push(num);
                        }
                    } else {
                        if (opsStack.ValidaVacio()
                                || opsStack.getDatoTope() == '(') {
                            opsStack.push(aux);
                        } else {
                            while (opsStack.getTope() != -1
                                    && opsStack.getJerarquiaTope() != 4
                                    && aux.getJerarquia() <= opsStack.getJerarquiaTope()) {
                                posStack.push("" + opsStack.getDatoTope());
                                OperaDosUltimos(opsStack.pop());
                            }
                            opsStack.push(aux);
                        }
                    }
                }
            }
            i++;
        }

        while (numStack.getTope() != 0) {
            posStack.push("" + opsStack.getDatoTope());
            OperaDosUltimos(opsStack.pop());
        }
        System.out.print("\n\nEl resultado es: " + numStack.getDatoTope());

        result.setText(String.valueOf(numStack.getDatoTope()));
        tInput.setText(String.valueOf(numStack.getDatoTope()));


    }

    boolean EsNumero(char character) {
        return Character.isDigit(character);
    }

    boolean EsPunto(char character) {
        return character == '.';
    }

    void OperaDosUltimos(Operator op) {
        char aux = op.getSimbol();
        double datoEnTope = numStack.pop();

        switch (aux) {
            case '+':
                numStack.push(numStack.pop() + datoEnTope);
                break;
            case '-':
                numStack.push(numStack.pop() - datoEnTope);
                break;
            case '*':
                numStack.push(numStack.pop() * datoEnTope);
                break;
            case '/':
                numStack.push(numStack.pop() / datoEnTope);
                break;
            case '^':
                numStack.push(Math.pow(numStack.pop(), datoEnTope));
                break;
            case '_':
                numStack.push(Math.pow(datoEnTope, 0.5));
                break;
            case 's':
                numStack.push(Math.sin(Math.toRadians(datoEnTope)));
                break;
            case 'c':
                numStack.push(Math.cos(Math.toRadians(datoEnTope)));
                break;
            case 't':
                numStack.push(Math.tan(Math.toRadians(datoEnTope)));
                break;
            case '!':
                numStack.push(factorial(datoEnTope));
                break;
            case '%':
                numStack.push(datoEnTope / 100);
                break;
        }
    }

    static double factorial(double n)
    {
        if (n == 0)
            return 1;

        return n*factorial(n-1);
    }


}
