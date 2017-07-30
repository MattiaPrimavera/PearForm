# PearForm - Flexible easy Form creation with Android
Tired of writing over and over again similar code for creating forms ?
**PearForm** allows easily creating **Forms** in Android applications.

# Usage
To create a form, add a `FormView` into your **XML** layout:

```xml
<com.mprimavera.view.FormView
    android:id="@+id/form"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
```

than add the following into your Activity / Fragment `onCreateView` method:

```java
FormBuilder field = mFormView.getFormBuilder();
mFormView
    .add(field.text(ACCOUNT_FIRST_NAME, "Username"))
    .add(field.text(ACCOUNT_LAST_NAME, "Lastname"))
    .add(field.text(ACCOUNT_EMAIL, "Email"))
    .add(field.text(ACCOUNT_ZIP_CODE, "Zipcode"));
    .build();
```

The **ACCOUNT_** constants stand for the **String** keys for the resulting **Bundle** collected by the **FormView**, which acts as the form controller.

## Customize
### Wish to customize your `FormView` ?
A `FormView` is just an extension of `LinearLayout` android widget, which vertically places all declared fields views with a default **divider**.
This implies you can use all `LinearLayout` attributes

### Wanna create your own fields?
Create a class that **extends** `FieldWidget` class, forcing to implement `IField` interface:
- implement `init()` method: takes care of the layout inflation
- add `this.init()` calls to automatically inserted constructors
- **override** the field behaviour: `validate()`, `getValue()`, `prefill()` methods

```java
public class Text extends FieldWidget {
    private TextView mTextView;

    // Constructors
    public Text(Context context) {
        super(context);
        this.init();
    }

    public Text(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public Text(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    public Text(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.init();
    }

    // View inflation
    public void init() {
        inflate(getContext(), R.layout.form_text_field, this);
        mTextView = (TextView) findViewById(R.id.input_text);
    }

    // Field validation
    @Override public boolean validate() { return true; }

    // Field result value getter
    // returns bundle collected by formView.getValue() method
    @Override public Bundle getValue() {
        Bundle bundle = new Bundle();
        bundle.putString(mResultKey, mTextView.getText().toString());
        return bundle;
    }

    // Prefills the field
    @Override public void prefill(Bundle bundle) {
        String text = bundle.getString(mResultKey);
        if(text != null) mTextView.setText(text);
    }
}
```

## Synopsis - Why and What
From an abstract point of view, a **Form** (`FormView` class) is a list of rows or **Fields** (View widgets implementing `IField` interface).  

Every **Field** has its own UI and logic, which consist in `IField` interface implementation:

```java
public interface IField {
    boolean validate();
    Bundle getValue();
    void setResultKey(String key);
    void prefill(Bundle bundle);
}
```

The `FormView` takes care of **Fields**
- instanciation
- validation
- prefill
- result collect

by iterating on the **Fields** list and delegating each field of the same action (`FormView` asks every **Field** to validate and sums up validation results).

This design gives the developer extreme flexibility since the FormView being a LinearLayout means not only we can use all its attributes but also manipulate every field as a simple android view. By defining your own set of Fields, you won't have to rewrite the same boilerplate code for all new forms, and code reuse makes sure the UI is consistent.

`PearForm` is more correctly a `Framework`, since it forces the developer to use the same `Frame` and reuse `Components` in every form creation, still providing for full customization, simply adding a new component. If you like already existing components, you may just wanna **extend** their **IField** functionnalities and keep the rest of the widget code.

To clearify Form creation code, I chose using the **Builder Pattern** within the `FormView`, in order to achieve a simple "declarative" creation.
