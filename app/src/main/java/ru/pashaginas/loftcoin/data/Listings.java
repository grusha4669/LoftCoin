package ru.pashaginas.loftcoin.data;

import com.google.auto.value.AutoValue;

import java.util.List;

@AutoValue
public abstract class Listings {
    abstract List<AutoValue_Coin> data();
}
