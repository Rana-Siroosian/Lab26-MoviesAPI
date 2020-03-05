package co.grandcircus.MoviesApi;
/**
 * 
 * @author >>RanaSiroosian<<
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.grandcircus.MoviesApi.Dao.MoviesDao;
import co.grandcircus.MoviesApi.Entity.Movies;

@RestController
public class MoviesApiController {

	@Autowired
	private MoviesDao dao;

	@GetMapping("/movies")
	public List<Movies> showMovies(@RequestParam(value = "category", required = false) String category,
			@RequestParam(value="random", required = false) Long random, 
			@RequestParam(value="title",required = false) String title) {

		Random rand = new Random();
		List<Movies> movies = dao.findAll();
		if ((category == null || category.isEmpty()) && (title==null || title.isEmpty()) && random ==null) {

			return  dao.findAll();

		} 
		else if ((random==null) && (title == null || title.isEmpty())){
			List<Movies> moviesByCategory = dao.findByCategoryIgnoreCase(category);

			return  moviesByCategory;
		}
		else if((title==null || title.isEmpty())&& (category==null || category.isEmpty())) {
			int num =0;
			List<Movies> randomPick = new ArrayList<>();
				for (int i=1; i<=random;i++) {
					num = rand.nextInt(movies.size());
					randomPick.add(movies.get(num));
				}
				return randomPick;
			
			
		}
		else {
			return dao.findByTitleIgnoreCase(title);
		}
		

//		if (category == null || category.isEmpty()) {
//			return dao.findAll();
//
//		} else {
//			return dao.findByCategoryIgnoreCase(category);
//		}
		
	}

	@GetMapping("/movies/random")
	public Movies showRandom(@RequestParam(value = "category", required = false) String category) {
		Random rand = new Random();
		if (category == null || category.isEmpty()) {
			List<Movies> movies = dao.findAll();
//			Long randomMovie = (long) rand.nextInt(movies.size())+1;
//			System.out.println(randomMovie + " ** " +movies.size());
//			return dao.getOne(randomMovie);

			int num = rand.nextInt(movies.size());
			return movies.get(num);
			
		} else {
			
			List<Movies> movies = dao.findByCategoryIgnoreCase(category);
			int num = rand.nextInt(movies.size());
			System.out.println(movies.size() + "++" + num);
			System.out.println("movie" + movies.get(num));
			return movies.get(num);
		}

	}
	
	@GetMapping("/categories")
	public Set<String> showCategories(){
		List<Movies> movies = dao.findAll();
		Set<String> categories = new HashSet<>();
		for( Movies cat : movies) {
			categories.add(cat.getCategory());
		}
		return categories;
		
	}
}
