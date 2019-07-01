package com.example.traincensus;

public class EditModel {

    private String editTextValue;
    private String spinnerValue;
    private String edit_ActualValue;

    public String getEditTextValue() {
        return editTextValue;
    }
    public String getSpinnerValue()
    {
        return spinnerValue;
    }
    public String getEdit_ActualValue()
    { return edit_ActualValue;}
    public void setEditTextValue(String editTextValue) {
        this.editTextValue = editTextValue;
    }
    public void setSpinnerValue(String spinnerValue)
    {
        this.spinnerValue=spinnerValue;
    }
    public void setEdit_ActualValue(String edit_ActualValue)
    {
        this.edit_ActualValue=edit_ActualValue;
    }
}