package com.gestaodefuncionarios.factory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PersistenciaLogTxt implements IPersistenciaLog {

    private File file;

    public PersistenciaLogTxt() {
        criarArquivo();
    }

    @Override
    public void gravarCriacaoFuncionario(String nome) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            writer.write("Funcionário " + nome + " adicionado.\n");

            writer.close();
        } catch (IOException e) {

            throw new RuntimeException("Erro ao gravar log");
        }
    }

    @Override
    public void gravarRemocaoFuncionario(String nome) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            writer.write("Funcionário " + nome + " removido.\n");

            writer.close();
        } catch (IOException e) {

            throw new RuntimeException("Erro ao gravar log");
        }  
    }

    @Override
    public void gravarAlteracaoFuncionario(String nome) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            writer.write("Funcionário " + nome + " alterado.\n");

            writer.close();
        } catch (IOException e) {

            throw new RuntimeException("Erro ao gravar log");
        }
    }

    @Override
    public void gravarConsultaBonusFuncionario(String nome) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            writer.write("Bônus consultado para o funcionário " + nome + ".\n");

            writer.close();
        } catch (IOException e) {

            throw new RuntimeException("Erro ao gravar log");
        }
    }

    @Override
    public void gravarCalculoDeSalarioFuncionario(String nomes) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            writer.write("Salário calculado para o(s) funcionário(s): [\n" + nomes + "]\n");

            writer.close();
        } catch (IOException e) {

            throw new RuntimeException("Erro ao gravar log");
        }
    }

    @Override
    public void gravarFalha(String erro) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            writer.write("Falha ao realizar a operação: " + erro + ".\n");

            writer.close();
        } catch (IOException e) {

            throw new RuntimeException("Erro ao gravar log");
        }
    }

    @Override
    public void criarArquivo() {
        File diretorio = new File("logs/");
        
        if(!diretorio.exists()) {
            diretorio.mkdirs();
        }

        file = new File("logs/log.txt");
    }
    
}
