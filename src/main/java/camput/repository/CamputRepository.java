package camput.repository;

import camput.domain.Camput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CamputRepository extends JpaRepository<Camput,Long> {

    List<Camput> findTop5ByOrderByTotalStarAvgDesc();
    Camput findByCampName(String campName);
    List<Camput> findAllByCheckDoNm(String doStr);
    //noAvg
    List<Camput> findByCaravanAcceptNotContainsAndCheckDoNmInAndTotalStarAvgAfterAndAnimalCmClNotContains(String check1,List<String> checkDoNm,Long check,String imposable);
    List<Camput> findByGlampingAcceptNotContainsAndCheckDoNmInAndTotalStarAvgAfterAndAnimalCmClNotContains(String check1,List<String> checkDoNm,Long check,String imposable);

    List<Camput> findByCaravanAcceptNotContainsAndCheckDoNmInAndTotalStarAvgAfterAndAnimalCmClContains(String check1,List<String> checkDoNm,Long check,String imposable);
    List<Camput> findByGlampingAcceptNotContainsAndCheckDoNmInAndTotalStarAvgAfterAndAnimalCmClContains(String check1,List<String> checkDoNm,Long check,String imposable);

    List<Camput>  findByCaravanAcceptNotContainsAndCheckDoNmInAndTotalStarAvgAfter(String check1, List<String> locations,Long starAvg);
    List<Camput> findByGlampingAcceptNotContainsAndCheckDoNmInAndTotalStarAvgAfter(String check1, List<String> locations,Long starAvg);
    List<Camput> findByCheckDoNmInAndTotalStarAvgAfter(List<String> locations, Long starAvg);

    List<Camput> findByCheckDoNmInAndTotalStarAvgAfterAndAnimalCmClNotContains(List<String> locations, Long star, String imposable);

    List<Camput> findByCheckDoNmInAndTotalStarAvgAfterAndAnimalCmClContains(List<String> locations, Long star, String imposable);

    ///noLocation
    List<Camput> findByCaravanAcceptNotContainsAndTotalStarAvgAfter(String check1,Long starAvg);
    List<Camput> findByGlampingAcceptNotContainsAndTotalStarAvgAfter(String check1, Long starAvg);

    List<Camput> findByTotalStarAvgAfter(Long starAvg);


    List<Camput> findByGlampingAcceptNotContainsAndTotalStarAvgAfterAndAnimalCmClContains(String s, Long star, String imposable);

    List<Camput> findByTotalStarAvgAfterAndAnimalCmClContains(Long star, String imposable);

    List<Camput> findByCaravanAcceptNotContainsAndTotalStarAvgAfterAndAnimalCmClContains(String s, Long star, String imposable);

}
