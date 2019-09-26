package com.siloam.healthcare.exames;

import com.siloam.healthcare.pessoas.IChaveUnica;

import java.util.Date;
import java.util.UUID;

public abstract class Exame implements IChaveUnica {

    private UUID _Id;

    public Date Data;
    public Date Validade;
    public Laudo Laudo;

    public Exame(){
        _Id = UUID.randomUUID();
    }

    @Override
    public UUID getId() {
        return _Id;
    }
}
