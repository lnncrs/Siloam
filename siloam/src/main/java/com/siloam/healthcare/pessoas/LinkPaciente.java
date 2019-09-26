package com.siloam.healthcare.pessoas;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LinkPaciente {

    private UUID _Link1;
    private UUID _Link2;

    public LinkPaciente(){
        _Link1 = UUID.randomUUID();
        _Link2 = UUID.randomUUID();
    }

    public ArrayList<UUID> getLink(){
        ArrayList<UUID> result = new ArrayList<UUID>(){{ add(_Link1);add(_Link2); }};
        _Link1 = _Link2;
        _Link2 = UUID.randomUUID();
        return result;
    }
}
