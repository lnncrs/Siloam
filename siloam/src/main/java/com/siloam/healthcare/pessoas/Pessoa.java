package com.siloam.healthcare.pessoas;

import java.util.Date;
import java.util.UUID;

public abstract class Pessoa implements IChaveUnica {

    private UUID _Id;

    public String Nome;
    public Date Nascimento;
    public String Email;
    public String Telefone;

    public Pessoa(){
        _Id = UUID.randomUUID();
    }

    @Override
    public UUID getId() {
        return _Id;
    }
}
