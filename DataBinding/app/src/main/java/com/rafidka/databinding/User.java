package com.rafidka.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

// Hint: Defining a class that derives from BaseObservable so that changes to it
// are automatically updated in the UI.
public class User extends BaseObservable {
  @Bindable
  public final ObservableField<String> firstName =
      new ObservableField<>();

  @Bindable
  public final ObservableField<String> lastName =
      new ObservableField<>();
}
