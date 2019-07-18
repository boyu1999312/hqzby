package com.hqzby;

public interface Hours {
    void mai();
}

class HoursM implements Hours{

    @Override
    public void mai() {
        System.out.println("我要买房");
    }

}

class HoursD implements Hours{

    private HoursM hoursM;

    public HoursD(HoursM hoursM) {
        this.hoursM = hoursM;
    }

    @Override
    public void mai() {
        System.out.println("买房前");
        hoursM.mai();
        System.out.println("买房后");
    }



}

