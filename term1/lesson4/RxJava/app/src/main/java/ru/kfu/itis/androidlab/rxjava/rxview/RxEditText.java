package ru.kfu.itis.androidlab.rxjava.rxview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.EditText;

import rx.Emitter;
import rx.Observable;
import rx.functions.Action1;

public class RxEditText extends EditText {

    @Nullable
    private Emitter<String> mEmitter;

    public RxEditText(Context context) {
        super(context);
    }

    public RxEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RxEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @NonNull
    public Observable<String> observeChanges() {
        return Observable.fromEmitter(new Action1<Emitter<String>>() {
            @Override
            public void call(Emitter<String> asyncEmitter) {
                mEmitter = asyncEmitter;
                asyncEmitter.onNext(getText().toString());
            }
        }, Emitter.BackpressureMode.LATEST);
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (mEmitter != null) {
            mEmitter.onNext(text.toString());
        }
    }
}
