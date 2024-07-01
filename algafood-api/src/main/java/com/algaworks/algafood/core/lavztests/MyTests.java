package com.algaworks.algafood.core.lavztests;

import jdk.swing.interop.SwingInterOpUtils;

public class MyTests {
    public static void main(String[] args) {
        DiaDaSemana dia = DiaDaSemana.SEGUNDA;

        System.out.println(dia.getDescricao());
        System.out.println(dia.getMeuNome());
        System.out.println(dia.isDiaUtil());
    }
}
