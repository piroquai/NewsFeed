package hello;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<Feed1, Long> {

    // added methods that combine choice of portals , exclude filters and search.
// All combinations require separate methods because there is no universal select query
    @Query(value = "SELECT * FROM Feed1 f WHERE f.id IN :chosen", nativeQuery = true
    )
    Iterable<Feed1> findBySource(@Param("chosen") List<Integer> chosen);

    @Query(value = "SELECT * FROM Feed1 f WHERE f.id IN :chosen AND f.content LIKE :search", nativeQuery = true
    )
    Iterable<Feed1> findBySourceWithSearch(@Param("chosen") List<Integer> chosen, @Param("search") String search);

    @Query(value = "SELECT * FROM Feed1 f WHERE f.id IN ?1 AND f.content NOT LIKE ?2" , nativeQuery = true
    )
    Iterable<Feed1> findBySourceWith1Filter(List<Integer> chosen, String f1);

    @Query(value = "SELECT *  FROM Feed1 f WHERE f.id IN ?1 AND f.content NOT LIKE ?2  AND f.content NOT LIKE ?3" , nativeQuery = true
    )
    Iterable<Feed1> findBySourceWith2Filters(List<Integer> chosen, String f1, String f2);

    @Query(value = "SELECT *  FROM Feed1 f WHERE f.id IN ?1 AND f.content NOT LIKE ?2  " +
            "AND f.content NOT LIKE ?3 AND f.content NOT LIKE ?4" , nativeQuery = true
    )
    Iterable<Feed1> findBySourceWith3Filters(List<Integer> chosen, String f1, String f2, String f3);

    @Query(value = "SELECT *  FROM Feed1 f WHERE f.id IN ?1 AND f.content NOT LIKE ?2  " +
            "AND f.content NOT LIKE ?3 AND f.content NOT LIKE ?4 AND f.content LIKE ?5" , nativeQuery = true
    )
    Iterable<Feed1> findBySourceWith3FiltersAndSearch(List<Integer> chosen, String f1, String f2, String f3, String search);

    @Query(value = "SELECT *  FROM Feed1 f WHERE f.id IN ?1 AND f.content NOT LIKE ?2  " +
            "AND f.content NOT LIKE ?3 AND f.content LIKE ?4" , nativeQuery = true
    )
    Iterable<Feed1> findBySourceWith2FiltersAndSearch(List<Integer> chosen, String f1, String f2, String search);

    @Query(value = "SELECT *  FROM Feed1 f WHERE f.id IN ?1 AND f.content NOT LIKE ?2  " +
            "AND f.content LIKE ?3" , nativeQuery = true
    )
    Iterable<Feed1> findBySourceWith1FiltersAndSearch(List<Integer> chosen, String f1, String search);
}


