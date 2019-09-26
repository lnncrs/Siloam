package com.siloam.healthcare.consulta;

import com.siloam.healthcare.pessoas.Funcionario;
import com.siloam.healthcare.pessoas.IChaveUnica;
import com.siloam.healthcare.pessoas.Paciente;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Atendimento implements IChaveUnica, IAtendimento {

    private UUID _Id;
    private Paciente _Paciente;
    private Date _Entrada;
    private ArrayList<Passagem> _Passagens;

    public Atendimento(Paciente Paciente){
        _Id = UUID.randomUUID();
        _Paciente = Paciente;
        _Entrada = new Date();
        _Passagens = new ArrayList<Passagem>();
    }

    @Override
    public UUID getId() {
        return _Id;
    }

    @Override
    public void AdicionarTriagem(Funcionario Atendente, ClassificacaoRisco Risco, String Descricao) {
        _Passagens.add(new Triagem(Atendente, Risco){{
            AdicionarAnotacao(Descricao);
        }});
    }

    @Override
    public void AdicionarConsulta(Consulta Consulta) {
        _Passagens.add(Consulta);
    }

    public boolean getAlta() {
        return _Passagens.stream().filter(p -> p.getAlta() == true).count() > 0;
    }

    public Paciente getPaciente() {
        return _Paciente;
    }

    public Date getEntrada() {
        return _Entrada;
    }

    public ArrayList<Passagem> getPassagens() {
        return _Passagens;
    }
}
