package co.grandcircus.MoviesApi.Dao;
/**
 * 
 * @author >>RanaSiroosian<<
 */
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.MoviesApi.Entity.Movies;

public interface MoviesDao extends JpaRepository<Movies, Long> {
	
	List<Movies> findByCategoryIgnoreCase(String movies);

	List<Movies> findByTitleIgnoreCase(String title);
}
