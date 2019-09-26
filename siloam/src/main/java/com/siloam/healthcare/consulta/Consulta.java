package com.siloam.healthcare.consulta;

import com.siloam.healthcare.pessoas.Medico;

public class Consulta extends Passagem {

    private Medico _Medico;

    public Consulta(Medico Medico){
        super();
        _Medico = Medico;
    }

    public Medico getMedico(){
        return _Medico;
    }
}
