package com.gestaodefuncionarios.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum Cargo {

    AnalistaDeSistemasDeAutomacao(0, "Analista de Sistemas de Automação"),
    AnalistaDeSuporteTecnico(1, "Analista de Suporte Técnico"),
    CientistaDeDados(2, "Cientista de Dados"),
    DBA(3, "DBA"),
    DesenhistaGraficoDeSuperficie(4, "Desenhista Gráfico de Superfície"),
    DesenvolvedorMobile(5, "Desenvolvedor Mobile"),
    EngenheiroDeDados(6, "Engenheiro de Dados"),
    EngenheiroDeSoftware(7, "Engenheiro de Software"),
    EspecialistaEmSegurancaDaInformacao(8, "Especialista em Segurança da Informação"),
    GerenteDeDesenvolvimentoDeSistemas(9, "Gerente de Desenvolvimento de Sistemas"),
    MonitoradorDeSistemasESuporteAoUsuario(10, "Monitorador de Sistemas e Suporte Ao Usuário"),
    ProgramadorFrontEnd(11, "Programador Front-End"),
    ProgramadorFullStack(12, "Programador FullStack"),
    ProgramadorBackEnd(13, "Programador Back-End");

    private final int codigo;
    private final String descricao;

    private Cargo(int codigo, String descricao) {
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

        for (Cargo c : Cargo.values()) {
            cargos.add(c.getDescricao());
        }

        return cargos;
    }

    public static Cargo toEnum(Integer codigo) {

        if (codigo == null) {
            return null;
        }

        for (Cargo c : Cargo.values()) {
            if (codigo.equals(c.getCodigo())) {
                return c;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + codigo);
    }

}
