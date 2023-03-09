package p42.tp23;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TextViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TextViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Edit text in Transmitter.");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setText( String text ) {
        mText.setValue(text);
    }
}