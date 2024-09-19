package org.BatiCuisine.coucheServices;

import org.BatiCuisine.CoucheMetier.Entite.Client;
import org.BatiCuisine.CoucheMetier.Entite.Projet;
import org.BatiCuisine.couchePersistence.repository.ClientRepository;
import org.BatiCuisine.couchePersistence.repository.ProjetRepository;

import java.util.List;
import java.util.Optional;

public class ClientService {

    public final ClientRepository clientRepository=new ClientRepository();
    public final ProjetRepository projetRepository = new ProjetRepository();



    public Client createClient(Client client){
         return clientRepository.create(client);
    }
    public List<Client> Client(){
        return clientRepository.getAll();
    }
    public Projet createProjet(Projet projet){
        return   projetRepository.create(projet);
    }
}
