package camput.Service;


import camput.Dto.CategoryDto;
import camput.Dto.MapCampDto;
import camput.Dto.SearchDto;
import camput.domain.CampMap;

import java.util.List;

public interface MapCampService {
    List<MapCampDto> findCamps();
    List<MapCampDto> categoryCamps(CategoryDto categoryDto);
    List<MapCampDto> searchCamp(String searchText);
    List<MapCampDto> bestFiveCamp();
    List<MapCampDto> findAll();
}
