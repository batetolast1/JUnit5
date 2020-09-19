package io.github.batetolast1.junit5.healthycoderapp;

import java.util.ArrayList;
import java.util.List;

public class CoderDAO {

    public List<Coder> getList() {
        List<Coder> coderList = new ArrayList<>();
        coderList.add(new Coder(1.80, 60.0));
        coderList.add(new Coder(1.82, 98.0));
        coderList.add(new Coder(1.82, 64.7));
        return coderList;
    }
}
