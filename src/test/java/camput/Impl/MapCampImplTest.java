package camput.Impl;

import camput.Service.MapCampService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
class MapCampImplTest {
    @Autowired
    MapCampService mapCampService;

    @Test
    void test() {
        mapCampService.bestFiveCamp();
    }
}