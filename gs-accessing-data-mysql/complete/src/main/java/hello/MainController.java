package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller    // This means that this class is a Controller
public class MainController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @GetMapping(path = "/choose") // Map ONLY GET Requests
    public @ResponseBody
    Iterable<Feed1> selectChosen(@RequestParam List<Integer> chosen,
                                 @RequestParam(required = false) String search,
                                 @RequestParam(required = false) String filters) {
        Iterable<Feed1> result = userRepository.findBySource(chosen);
        if (filters != null) {
            List<String> filtersList = new ArrayList<>(Arrays.asList(filters.split(",")));
            for (int i = 0; i < filtersList.size(); i++)
                filtersList.set(i, String.format("%%%s%%", filtersList.get(i)));
            int filtersNumber = filtersList.size();
            if (search == null) {
                switch (filtersNumber) {
                    case 1:
                        result = userRepository.findBySourceWith1Filter(chosen, filters);
                        break;
                    case 2:
                        result = userRepository.findBySourceWith2Filters(chosen, filtersList.get(0), filtersList.get(1));
                        break;
                    case 3:
                        result = userRepository.findBySourceWith3Filters(chosen, filtersList.get(0), filtersList.get(1), filtersList.get(2));
                        break;
                }
            } else {
                search = "% " + search + " %";
                switch (filtersNumber) {
                    case 1:
                        result = userRepository.findBySourceWith1FiltersAndSearch(chosen, filters, search);
                        break;
                    case 2:
                        result = userRepository.findBySourceWith2FiltersAndSearch(chosen, filtersList.get(0), filtersList.get(1), search);
                        break;
                    case 3:
                        result = userRepository.findBySourceWith3FiltersAndSearch(chosen, filtersList.get(0), filtersList.get(1), filtersList.get(2), search);
                        break;
                }
            }
        } else {
            if (search == null)
                result = userRepository.findBySource(chosen);
            else {
                search = "% " + search + " %";
                result = userRepository.findBySourceWithSearch(chosen, search);
            }
        }

        return result;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Feed1> getAllNews() {
        return userRepository.findAll();
    }
}
