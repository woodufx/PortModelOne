package services.service;

import services.models.MyDate;

public interface MyDateService {
    MyDate getRandomDate();

    MyDate plusHour(MyDate date);
}

