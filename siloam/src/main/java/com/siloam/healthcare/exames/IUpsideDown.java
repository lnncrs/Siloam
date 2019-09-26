package com.siloam.healthcare.exames;

import com.siloam.healthcare.pessoas.Paciente;

import java.util.ArrayList;
import java.util.UUID;

public interface IUpsideDown {

    ArrayList<UUID> ListarExames(Paciente Paciente);
    void InserirExame(Paciente Paciente, Exame Exame);

}
