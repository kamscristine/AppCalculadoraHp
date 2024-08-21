package com.example.projetocalculadorahp;
import static junit.framework.TestCase.assertEquals;

import org.junit.Test;
public class CalculadoraTest {

    @Test
    public void testSetNumero() {
        Calculadora calc = new Calculadora();
        calc.setNumero(4);
        calc.enter();
        assertEquals(4.0, calc.getNumero());
    }

    @Test
    public void testSomarAB() {
        Calculadora calc = new Calculadora();
        calc.setNumero(2);
        calc.enter();
        calc.setNumero(2);
        calc.soma();
        assertEquals(4.0, calc.getNumero());
    }

    @Test
    public void testSubtracaoAB() {
        Calculadora calc = new Calculadora();
        calc.setNumero(5);
        calc.enter();
        calc.setNumero(3);
        calc.subtracao();
        assertEquals(2.0, calc.getNumero());
    }

    @Test
    public void testDivisaoAB() {
        Calculadora calc = new Calculadora();
        calc.setNumero(9);
        calc.enter();
        calc.setNumero(3);
        calc.divisao();
        assertEquals(3.0, calc.getNumero());
    }

    @Test
    public void testMultiplicacaoAB() {
        Calculadora calc = new Calculadora();
        calc.setNumero(2);
        calc.enter();
        calc.setNumero(8);
        calc.multiplicacao();
        assertEquals(16.0, calc.getNumero());
    }

    @Test
    public void ABC() {
        Calculadora calc = new Calculadora();
        calc.setNumero(1);
        calc.enter();
        calc.setNumero(2);
        calc.enter();
        calc.setNumero(3);
        calc.soma();
        assertEquals(5.0, calc.getNumero());
        calc.soma();
        assertEquals(6.0, calc.getNumero());

    }

    @Test
    public void ABCDivisao() {
        Calculadora calc = new Calculadora();
        calc.setNumero(8);
        calc.enter();
        calc.setNumero(16);
        calc.enter();
        calc.setNumero(4);
        calc.divisao();
        assertEquals(4.0, calc.getNumero());
        calc.divisao();
        assertEquals(2.0, calc.getNumero());

    }

    @Test
    public void JurosCompostos() {
        //caso da internet
//        1300, apertar CHS e depois PV;
//        1, apertar i;
//        8 apertar n;
//        depois disso, é só apertar a tecla FV e o resultado será mostrado na tela. Nesse caso será R$ 1407.71.

        Calculadora calc = new Calculadora();
        calc.setPv(1300.0);
        calc.seti(1.0 / 100);
        calc.setn(8.0);
        double valorFuturo = calc.calcularFV();
        assertEquals(1407.71, valorFuturo, 0.01);
    }

}