package ru.pashaginas.loftcoin.ui.currency;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import ru.pashaginas.loftcoin.data.Currency;
import ru.pashaginas.loftcoin.databinding.DialogCurrencyBinding;
import ru.pashaginas.loftcoin.util.OnItemClick;
import ru.pashaginas.loftcoin.R;
import ru.pashaginas.loftcoin.data.CurrencyRepo;
import ru.pashaginas.loftcoin.data.CurrencyRepoImpl;

public class CurrencyDialog extends AppCompatDialogFragment {

   private DialogCurrencyBinding binding;

   private CurrencyRepo currencyRepo;

   private CurrencyAdapter adapter;

   private OnItemClick onItemClick;

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      currencyRepo = new CurrencyRepoImpl(requireContext()); //todo to ViewModel
      adapter = new CurrencyAdapter();
   }

   @NonNull
   @Override
   public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
      binding = DialogCurrencyBinding.inflate(getLayoutInflater());
      return new MaterialAlertDialogBuilder(requireActivity())
              .setTitle(R.string.choose_currency)
              .setView(binding.getRoot())
              .create();
   }

   @Override
   public void onActivityCreated(@Nullable Bundle savedInstanceState) {
      super.onActivityCreated(savedInstanceState);
      binding.recycler.setLayoutManager(new LinearLayoutManager(requireActivity()));
      binding.recycler.setAdapter(adapter);
      currencyRepo.availableCurrencies().observe(this, adapter::submitList);
      onItemClick = new OnItemClick(binding.recycler.getContext(), (v) -> {
         final RecyclerView.ViewHolder viewHolder = binding.recycler.findContainingViewHolder(v);
         if (viewHolder != null) {
            final Currency item = adapter.getItem(viewHolder.getAdapterPosition());
            currencyRepo.updateCurrency(item);
         }
         dismissAllowingStateLoss();
      });
      binding.recycler.addOnItemTouchListener(onItemClick);
   }

   @Override
   public void onDestroy() {
      binding.recycler.removeOnItemTouchListener(onItemClick);
      binding.recycler.setAdapter(null);
      super.onDestroy();
   }
}
