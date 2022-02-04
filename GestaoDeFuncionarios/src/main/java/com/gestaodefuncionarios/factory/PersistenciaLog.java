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

    public static void gravarCriacaoFuncionario(int log, String nome) {
        if (log == 1) {
            gravarCriacaoFuncionario(new PersistenciaLogJson(), nome);
        } else {
            gravarCriacaoFuncionario(new PersistenciaLogTxt(), nome);
        }
    }

    public static void gravarRemocaoFuncionario(int log, String nome) {
        if (log == 1) {
            gravarRemocaoFuncionario(new PersistenciaLogJson(), nome);
        } else {
            gravarRemocaoFuncionario(new PersistenciaLogTxt(), nome);
        }
    }

    public static void gravarAlteracaoFuncionario(int log, String nome) {
        if (log == 1) {
            gravarAlteracaoFuncionario(new PersistenciaLogJson(), nome);
        } else {
            gravarAlteracaoFuncionario(new PersistenciaLogTxt(), nome);
        }
    }

    public static void gravarConsultaBonusFuncionario(int log, String nome) {
        if (log == 1) {
            gravarConsultaBonusFuncionario(new PersistenciaLogJson(), nome);
        } else {
            gravarConsultaBonusFuncionario(new PersistenciaLogTxt(), nome);
        }
    }

    public static void gravarCalculoDeSalarioFuncionario(int log, FuncionarioCollection funcionarios) {
        var nomes = "\t";

        for(Funcionario f : funcionarios.getFuncionarios()) {
            nomes += f.getNome() + "\n\t";
        }

        if (log == 1) {
            gravarCalculoDeSalarioFuncionario(new PersistenciaLogJson(), nomes);
        } else {
            gravarCalculoDeSalarioFuncionario(new PersistenciaLogTxt(), nomes);
        }
    }

    public static void gravarFalha(int log, String erro) {
        if (log == 1) {
            gravarFalha(new PersistenciaLogJson(), erro);
        } else {
            gravarFalha(new PersistenciaLogTxt(), erro);
        }
    }

}
