package ru.pashaginas.loftcoin.ui.rates;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import ru.pashaginas.loftcoin.data.Coin;

public interface RatesView {

    void showCoins(@NonNull List<? extends Coin> coins);
    void showError(@NonNull String error);

}
