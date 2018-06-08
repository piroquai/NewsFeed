package hello;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<Feed1, Long> {
    @Query(value = "SELECT * FROM Feed1 f WHERE f.link like '%nytimes%'",
            nativeQuery=true
    )
    public List<Feed1> findNYT();

    @Query(value = "SELECT * FROM Feed1 f WHERE f.link like '%foxnews%'",
            nativeQuery=true
    )
    public List<Feed1> findFox();

}


