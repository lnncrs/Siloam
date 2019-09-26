package com.siloam.healthcare.consulta;

import com.siloam.healthcare.pessoas.Funcionario;

public interface IAtendimento {

    void AdicionarTriagem(Funcionario Atendente, ClassificacaoRisco Risco, String Descricao);
    void AdicionarConsulta(Consulta Consulta);

}
