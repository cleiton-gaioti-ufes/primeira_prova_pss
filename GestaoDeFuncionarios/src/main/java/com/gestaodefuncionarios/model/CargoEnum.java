
package com.gestaodefuncionarios.model;

import java.util.ArrayList;

public enum CargoEnum {
    
    AnalistaDeSistemasDeAutomacao(1, "Analista de Sistemas de Automação"),
    AnalistaDeSuporteTecnico(2, "Analista de Suporte Técnico"),
    CientistaDeDados(3, "Cientista de Dados"),
    DBA(4, "DBA"),
    DesenhistaGraficoDeSuperficie(5, "Desenhista Gráfico de Superfície"),
    DesenvolvedorMobile(6, "Desenvolvedor Mobile"),
    EngenheiroDeDados(7, "Engenheiro de Dados"),
    EngenheiroDeSoftware(8, "Engenheiro de Software"),
    EspecialistaEmSegurancaDaInformacao(9, "Especialista em Segurança da Informação"),
    GerenteDeDesenvolvimentoDeSistemas(10, "Gerente de Desenvolvimento de Sistemas"),
    MonitoradorDeSistemasESuporteAoUsuario(11, "Monitorador de Sistemas e Suporte Ao Usuário"),
    ProgramadorFrontEnd(12, "Programador Front-End"),
    ProgramadorFullStack(13, "Programador FullStack"),
    ProgramadorBackEnd(14, "Programador Back-End");
    
    private final int codigo;
    private final String descricao;
    
    private CargoEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public static ArrayList<String> listDescriptions() {
        ArrayList<String> cargos = new ArrayList<>();
        
        for(CargoEnum c: CargoEnum.values()) {
            cargos.add(c.getDescricao());
        }
        
        return cargos;
    }
    
    public static CargoEnum toEnum(Integer codigo) {
        
        if(codigo == null) {
            return null;
        }
        
        for(CargoEnum c: CargoEnum.values()) {
            if(codigo.equals(c.getCodigo())) {
                return c;
            }
        }
        
        throw new IllegalArgumentException("Id inválido: " + codigo);
    }

    public static CargoEnum toEnum(String descricao) {
        
        if(descricao == null) {
            return null;
        }
        
        for(CargoEnum c: CargoEnum.values()) {
            if(descricao.equals(c.getDescricao())) {
                return c;
            }
        }
        
        throw new IllegalArgumentException("Descrição inválida: " + descricao);
    }
}
