package com.gestaodefuncionarios.factory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

public class PersistenciaLogJson implements IPersistenciaLog {
    private File file;

    public PersistenciaLogJson() {
        criarArquivo();
    }

    @Override
    public void gravarCriacaoFuncionario(String nome) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("Funcionario adicionado", nome);
        jsonObject.put("Funcionario removido", "");
        jsonObject.put("Funcionario alterado", "");
        jsonObject.put("Bônus consultado para o funcionário", "");
        jsonObject.put("Salário calculado para o(s) funcionário(s)", "");
        jsonObject.put("Falha ao realizar a operação", "");

        try {
            var writer = new FileWriter(file, true);

            writer.write(jsonObject.toJSONString());
            writer.close();
        } catch(IOException e) {
            throw new RuntimeException("Erro ao gravar log");
        }
    }

    @Override
    public void gravarRemocaoFuncionario(String nome) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("Funcionario adicionado", "");
        jsonObject.put("Funcionario removido", nome);
        jsonObject.put("Funcionario alterado", "");
        jsonObject.put("Bônus consultado para o funcionário", "");
        jsonObject.put("Salário calculado para o(s) funcionário(s)", "");
        jsonObject.put("Falha ao realizar a operação", "");

        try {
            var writer = new FileWriter(file, true);

            writer.write(jsonObject.toJSONString());
            writer.close();
        } catch(IOException e) {
            throw new RuntimeException("Erro ao gravar log");
        }  
    }

    @Override
    public void gravarAlteracaoFuncionario(String nome) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("Funcionario adicionado", "");
        jsonObject.put("Funcionario removido", "");
        jsonObject.put("Funcionario alterado", nome);
        jsonObject.put("Bônus consultado para o funcionário", "");
        jsonObject.put("Salário calculado para o(s) funcionário(s)", "");
        jsonObject.put("Falha ao realizar a operação", "");

        try {
            var writer = new FileWriter(file, true);

            writer.write(jsonObject.toJSONString());
            writer.close();
        } catch(IOException e) {
            throw new RuntimeException("Erro ao gravar log");
        }     
    }

    @Override
    public void gravarConsultaBonusFuncionario(String nome) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("Funcionario adicionado", "");
        jsonObject.put("Funcionario removido", "");
        jsonObject.put("Funcionario alterado", "");
        jsonObject.put("Bônus consultado para o funcionário", nome);
        jsonObject.put("Salário calculado para o(s) funcionário(s)", "");
        jsonObject.put("Falha ao realizar a operação", "");

        try {
            var writer = new FileWriter(file, true);

            writer.write(jsonObject.toJSONString());
            writer.close();
        } catch(IOException e) {
            throw new RuntimeException("Erro ao gravar log");
        }
    }

    @Override
    public void gravarCalculoDeSalarioFuncionario(String nomes) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("Funcionario adicionado", "");
        jsonObject.put("Funcionario removido", "");
        jsonObject.put("Funcionario alterado", "");
        jsonObject.put("Bônus consultado para o funcionário", "");
        jsonObject.put("Salário calculado para o(s) funcionário(s)", nomes);
        jsonObject.put("Falha ao realizar a operação", "");

        try {
            var writer = new FileWriter(file, true);

            writer.write(jsonObject.toJSONString());
            writer.close();
        } catch(IOException e) {
            throw new RuntimeException("Erro ao gravar log");
        }
    }

    @Override
    public void gravarFalha(String erro) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("Funcionario adicionado", "");
        jsonObject.put("Funcionario removido", "");
        jsonObject.put("Funcionario alterado", "");
        jsonObject.put("Bônus consultado para o funcionário", "");
        jsonObject.put("Salário calculado para o(s) funcionário(s)", "");
        jsonObject.put("Falha ao realizar a operação", erro);

        try {
            var writer = new FileWriter(file, true);

            writer.write(jsonObject.toJSONString());
            writer.close();
        } catch(IOException e) {
            throw new RuntimeException("Erro ao gravar log");
        }   
    }

    @Override
    public void criarArquivo() {
        File diretorio = new File("logs/");
        
        if(!diretorio.exists()) {
            diretorio.mkdirs();
        }

        file = new File("logs/log.json");
    }
    
}
