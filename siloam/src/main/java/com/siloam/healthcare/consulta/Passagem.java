package com.siloam.healthcare.consulta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Passagem implements IConsulta {

    private Date _Abertura;
    private Date _Fechamento;
    private boolean _Alta;
    private ArrayList<Anotacao> _Anotacoes;

    public Passagem(){
        _Abertura = new Date();
        _Alta = false;
        _Anotacoes = new ArrayList<Anotacao>();
    }

    public Date getAbertura(){
        return _Abertura;
    }

    public Date getFechamento(){
        return _Fechamento;
    }

    public List<Anotacao> getAnotacoes(){
        return _Anotacoes;
    }

    public boolean getFechada(){
        return (_Fechamento != null);
    }

    public boolean getAlta(){
        return _Alta;
    }


    @Override
    public void Fechar(boolean Alta){
        _Fechamento = new Date();
        _Alta = Alta;
    }

    @Override
    public void AdicionarAnotacao(String Texto){
        _Anotacoes.add(new Anotacao(Texto));
    }

}