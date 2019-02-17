package com.a82.antdroid.conv.entity;

public final class Currency {
    private String name;
    private String code;
    private int nominal;
    private double value;

    public Currency() {
    }

    public Currency(String name, String code, int nominal, double value) {
        this.name = name;
        this.code = code;
        this.nominal = nominal;
        this.value = value;
    }

    public static Currency fromValute(Valute source) {
        final Currency result = new Currency();
        result.setName(source.getName());
        result.setCode(source.getCharCode());
        result.setNominal(source.getNominal());
        result.setValue(Double.parseDouble(source.getValue().replace(",", ".")));

        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", nominal=" + nominal +
                ", value=" + value +
                '}';
    }
}
