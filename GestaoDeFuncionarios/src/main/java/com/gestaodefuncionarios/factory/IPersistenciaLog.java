package com.gestaodefuncionarios.factory;


public interface IPersistenciaLog {
    public void criarArquivo();
    public void gravarCriacaoFuncionario(String nome);
    public void gravarRemocaoFuncionario(String nome);
    public void gravarAlteracaoFuncionario(String nome);
    public void gravarConsultaBonusFuncionario(String nome);
    public void gravarCalculoDeSalarioFuncionario(String nomes);
    public void gravarFalha(String erro);
}
