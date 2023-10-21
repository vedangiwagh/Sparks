package com.planning.mealsandrecipes.service;

import com.planning.mealsandrecipes.entity.Client;
import com.planning.mealsandrecipes.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component

public class ClientService {

//    private final ClientRepo clientRepo;

//    @Autowired
//    public ClientService(ClientRepo clientRepo) {
//        this.clientRepo = clientRepo;
//    }

    // Retrieve a list of all clients.
//    public List<Client> getAllClients() {
//        return (List<Client>) clientRepo.findAll();
//    }
}
