package com.gestaodefuncionarios.factory;

import com.gestaodefuncionarios.collection.FuncionarioCollection;
import com.gestaodefuncionarios.model.Funcionario;

public abstract class PersistenciaLog {

    private static void gravarCriacaoFuncionario(IPersistenciaLog persistenciaLog, String nome) {
        persistenciaLog.gravarCriacaoFuncionario(nome);
    }

    private static void gravarRemocaoFuncionario(IPersistenciaLog persistenciaLog, String nome) {
        persistenciaLog.gravarRemocaoFuncionario(nome);
    }

    private static void gravarAlteracaoFuncionario(IPersistenciaLog persistenciaLog, String nome) {
        persistenciaLog.gravarAlteracaoFuncionario(nome);
    }

    private static void gravarConsultaBonusFuncionario(IPersistenciaLog persistenciaLog, String nome) {
        persistenciaLog.gravarConsultaBonusFuncionario(nome);
    }

    private static void gravarCalculoDeSalarioFuncionario(IPersistenciaLog persistenciaLog, String nomes) {
        persistenciaLog.gravarCalculoDeSalarioFuncionario(nomes);
    }

    private static void gravarFalha(IPersistenciaLog persistenciaLog, String erro) {
        persistenciaLog.gravarFalha(erro);
    }

    public static void gravarCriacaoFuncionario(boolean json, String nome) {
        if (json) {
            gravarCriacaoFuncionario(new PersistenciaLogJson(), nome);
        } else {
            gravarCriacaoFuncionario(new PersistenciaLogTxt(), nome);
        }
    }

    public static void gravarRemocaoFuncionario(boolean json, String nome) {
        if (json) {
            gravarRemocaoFuncionario(new PersistenciaLogJson(), nome);
        } else {
            gravarRemocaoFuncionario(new PersistenciaLogTxt(), nome);
        }
    }

    public static void gravarAlteracaoFuncionario(boolean json, String nome) {
        if (json) {
            gravarAlteracaoFuncionario(new PersistenciaLogJson(), nome);
        } else {
            gravarAlteracaoFuncionario(new PersistenciaLogTxt(), nome);
        }
    }

    public static void gravarConsultaBonusFuncionario(boolean json, String nome) {
        if (json) {
            gravarConsultaBonusFuncionario(new PersistenciaLogJson(), nome);
        } else {
            gravarConsultaBonusFuncionario(new PersistenciaLogTxt(), nome);
        }
    }

    public static void gravarCalculoDeSalarioFuncionario(boolean json, FuncionarioCollection funcionarios) {
        var nomes = "\t";

        for(Funcionario f : funcionarios.getFuncionarios()) {
            nomes += f.getNome() + "\n\t";
        }

        if (json) {
            gravarCalculoDeSalarioFuncionario(new PersistenciaLogJson(), nomes);
        } else {
            gravarCalculoDeSalarioFuncionario(new PersistenciaLogTxt(), nomes);
        }
    }

    public static void gravarFalha(boolean json, String erro) {
        if (json) {
            gravarFalha(new PersistenciaLogJson(), erro);
        } else {
            gravarFalha(new PersistenciaLogTxt(), erro);
        }
    }

}
