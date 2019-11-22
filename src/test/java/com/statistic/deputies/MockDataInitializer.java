package com.statistic.deputies;

import static java.time.LocalDate.of;

import java.util.List;
import com.statistic.deputies.entity.Deputy;

public class MockDataInitializer {

    public List<Deputy> add() {
        Deputy deputy1 = new Deputy();
        deputy1.setId(0L);
        deputy1.setRada(9);
        deputy1.setName("Сава Красава");
        deputy1.setParty("Найкраща");
        deputy1.setStartWork(of(2019, 8, 28));
        deputy1.setNationality("українець");
        deputy1.setActivity("фінансовий геній, політик");
        deputy1.setAwards("Медаль «За красоту»");
        deputy1.setEducation("КИМО");

        Deputy deputy2 = new Deputy();
        deputy2.setId(1L);
        deputy2.setRada(9);
        deputy2.setName("Тарас Нормас");
        deputy2.setParty("Нормальна");
        deputy2.setStartWork(of(2019, 8, 28));
        deputy2.setEndWork(of(2019, 8, 28));
        deputy2.setNationality("УКРАЇНА");
        deputy2.setActivity("працівник, політик");
        deputy2.setAwards("Медаль «За нормальність»");

        Deputy deputy3 = new Deputy();
        deputy3.setId(2L);
        deputy3.setRada(9);
        deputy3.setName("Роман Міневич");
        deputy3.setParty("Найкраща");
        deputy3.setStartWork(of(2019, 8, 19));
        deputy3.setEndWork(of(2019, 11, 22));
        deputy3.setNationality("росіянин");
        deputy3.setActivity("відсутня");
        deputy3.setEducation("КНУ");


        Deputy deputy4 = new Deputy();
        deputy4.setId(3L);
        deputy4.setRada(10);
        deputy4.setName("Роман Міневич");
        deputy4.setParty("Нормальна");
        deputy4.setStartWork(of(2019, 11, 25));
        deputy4.setEducation("КНУ");
        deputy4.setAwards("");

        return List.of(deputy1, deputy2, deputy3, deputy4);
    }
}
