package com.algaworks.algafood.core.lavztests;

import lombok.Getter;

@Getter
public enum DiaDaSemana {
    SEGUNDA("dia de trabalho"),
    TERCA("dia de trabalho"),
    QUARTA("dia de trabalho"),
    QUINTA("dia de trabalho"),
    SEXTA("dia de trabalho"),
    SABADO("dia de descanso"),
    DOMINGO("dia de descanso");

    private String descricao;
    private String meuNome;

    DiaDaSemana(String value) {
        this.descricao = value;
        this.meuNome = "LUCAS AVANZI";
    }

    // Método para verificar se é um dia útil
    public boolean isDiaUtil() {
        return this != SABADO && this != DOMINGO;
    }
}
