package com.example.keyboared;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

public class CustomKeyboardService extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView keyboardView;
    private Keyboard keyboard;

    @Override
    public View onCreateInputView() {
        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;

        switch (primaryCode) {
            case Keyboard.KEYCODE_DELETE:
                ic.deleteSurroundingText(1, 0);
                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            default:
                char code = (char) primaryCode;
                ic.commitText(String.valueOf(code), 1);
        }
    }

    @Override
    public void onPress(int primaryCode) {
        // Handle key press if needed
        if (primaryCode != 0) {
            // Vibrate or change color to indicate press
            keyboardView.setPreviewEnabled(true); // Optional
        }
    }

    @Override
    public void onRelease(int primaryCode) {
        // Handle key release if needed
        keyboardView.setPreviewEnabled(false);
    }

    @Override
    public void onText(CharSequence text) {
        getCurrentInputConnection().commitText(text, 1);
    }

    @Override
    public void swipeLeft() {
        // Handle swipe left
    }

    @Override
    public void swipeRight() {
        // Handle swipe right
    }

    @Override
    public void swipeDown() {
        // Handle swipe down
    }

    @Override
    public void swipeUp() {
        // Handle swipe up
    }
}
