package br.inatel.thisismeapi.repositories;

import br.inatel.thisismeapi.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {


}
