package com.longhb.do4life.base;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public abstract class BaseActivity extends AppCompatActivity {
    public void checkText(EditText editText, TextInputLayout textInputLayout, String string) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!checkPass(editText.getId(),editText.getText().toString())) {
                    textInputLayout.setError(string);
                } else {
                    textInputLayout.setErrorEnabled(false);
                }
            }
        });
    }

    protected abstract boolean checkPass(int idEdt,String text);
}
