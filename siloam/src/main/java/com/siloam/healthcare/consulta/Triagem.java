package com.siloam.healthcare.consulta;

import com.siloam.healthcare.pessoas.Funcionario;

public class Triagem extends Passagem {

    private Funcionario _Atendente;
    private ClassificacaoRisco _Risco;

    public Triagem(Funcionario Atendente, ClassificacaoRisco Risco){
        super();
        _Atendente = Atendente;
        _Risco = Risco;
    }

    public Funcionario getAtendente() {
        return _Atendente;
    }

    public ClassificacaoRisco getRisco(){
        return _Risco;
    }
}
