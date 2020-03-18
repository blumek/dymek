package com.blumek.dymek.adapter.idGenerator;


import com.blumek.dymek.domain.port.IdGenerator;

import java.util.UUID;

public class UUIDGenerator implements IdGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
