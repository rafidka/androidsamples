package com.rafidka.customautocomptextview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

// Hint: This is where we define the class for our custom view.
public class CountryTextView extends AutoCompleteTextView {
  private static final String[] COUNTRIES = new String[] {
      "Belgium", "France", "Italy", "Germany", "Spain"
  };

  public CountryTextView(Context context) {
    super(context);
    initialize();
  }

  public CountryTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initialize();
  }

  public CountryTextView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initialize();
  }

  private void initialize() {
    ArrayAdapter<String> countries = new ArrayAdapter<>(getContext(), R.layout.adapter_item, COUNTRIES);
    setAdapter(countries);
  }
}
