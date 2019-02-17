package com.a82.antdroid.conv.entity;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root
public class ValCurs {

    @ElementList(entry = "Valute", inline = true, required = false)
    private List<Valute> valutes = new ArrayList<>();

    public List<Valute> getValutes() {
        return valutes;
    }
}
