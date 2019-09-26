package com.siloam.healthcare.pessoas;

import java.util.ArrayList;
import java.util.UUID;

public class Paciente extends Pessoa {

    private LinkPaciente _LinkPaciente;

    public Paciente(){
        super();
        _LinkPaciente = new LinkPaciente();
    }

    public ArrayList<UUID> ListarLink(){
        return _LinkPaciente.getLink();
    }

}
