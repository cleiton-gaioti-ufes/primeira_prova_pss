package com.gestaodefuncionarios.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum Graduacao {

    EnsinoFundamental(0, "Ensino Fundamental"),
    EnsinoMedio(1, "Ensino médio"),
    EnsinoSuperior(2, "Ensino superior"),
    Mestrado(3, "Mestrado"),
    Doutorado(4, "Doutorado");

    private final int codigo;
    private final String descricao;

    private Graduacao(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static List<String> listDescriptions() {
        List<String> cargos = new ArrayList<>();

        for (Graduacao c : Graduacao.values()) {
            cargos.add(c.getDescricao());
        }

        return cargos;
    }

    public static Graduacao toEnum(Integer codigo) {

        if (codigo == null) {
            return null;
        }

        for (Graduacao c : Graduacao.values()) {
            if (codigo.equals(c.getCodigo())) {
                return c;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + codigo);
    }

}
