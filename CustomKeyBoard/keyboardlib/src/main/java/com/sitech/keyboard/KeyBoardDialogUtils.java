package com.sitech.keyboard;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * @author lingxu
 */
public class KeyBoardDialogUtils implements View.OnClickListener {
    private View view;
    private Dialog popWindow;
    private Activity mActivity;
    private EditText contentView;
    private List<String> contentList;
    private KhKeyboardView keyboardUtil;
    private static volatile KeyBoardDialogUtils keyBoardDialogUtils;
    private Handler mHandler = new Handler();

    public static KeyBoardDialogUtils getInstance(Activity context) {
        if (keyBoardDialogUtils == null || keyBoardDialogUtils.getActivity() != context) {
            synchronized (KeyBoardDialogUtils.class) {
                if (keyBoardDialogUtils == null || keyBoardDialogUtils.getActivity() != context) {
                    keyBoardDialogUtils = new KeyBoardDialogUtils(context);
                }
            }
        }
        return keyBoardDialogUtils;
    }


    private KeyBoardDialogUtils(Activity mActivity) {
        this.mActivity = mActivity;
        initPopWindow(mActivity);

    }

    private void initPopWindow(Activity mActivity) {
        try {
            if (contentList == null) {
                contentList = new ArrayList<>();
            }
            if (popWindow == null) {
                view = LayoutInflater.from(mActivity).inflate(R.layout.keyboard_key_board_popu, null);
                view.getBackground().setAlpha(68);
                popWindow = new Dialog(mActivity, R.style.keyboard_popupAnimation);
                view.findViewById(R.id.keyboard_finish).setOnClickListener(this);
                view.findViewById(R.id.keyboard_back_hide).setOnClickListener(this);
            }
            popWindow.setContentView(view);
            popWindow.setCanceledOnTouchOutside(true);
            Window mWindow = popWindow.getWindow();
            if (mWindow != null) {
                mWindow.setWindowAnimations(R.style.keyboard_popupAnimation);
                mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                mWindow.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
                mWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            popWindow.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if (contentView != null && contentView.isFocused()) {
                        contentView.clearFocus();
                    }
                }
            });
            popWindow.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {

                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mActivity.getCurrentFocus().clearFocus();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
            initView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        try {
            if (keyboardUtil == null) {
                keyboardUtil = new KhKeyboardView(mActivity, view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 隐藏系统键盘
     *
     * @param editText
     */
    private void hideSystemSoftKeyboard(EditText editText) {
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= 11) {
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(editText, false);

            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            editText.setInputType(InputType.TYPE_NULL);
        }
        // 如果软键盘已经显示，则隐藏
        try {
            InputMethodManager imm = (InputMethodManager) mActivity.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideSystemSoftKeyboard() {
        try {
            InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void show(int inputType) {
        hideSystemSoftKeyboard();
        popWindow.show();
        keyboardUtil.showKeyboard(inputType);
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                popWindow.show();
//                keyboardUtil.showKeyboard(inputType);
//            }
//        }, 100);
    }

    public void show(final EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        hideSystemSoftKeyboard(editText);
        popWindow.show();
        keyboardUtil.showKeyboard(editText);
    }

    public void dismiss() {
        if (popWindow != null && popWindow.isShowing()) {
            popWindow.dismiss();

        }

    }

    @Override
    public void onClick(View v) {
        try {
            int i = v.getId();
            if (i == R.id.keyboard_finish) {
                hideSystemSoftKeyboard();
                keyboardUtil.hideKeyboard();
                dismiss();

            } else if (i == R.id.keyboard_back_hide) {
                hideSystemSoftKeyboard();
                keyboardUtil.hideKeyboard();
                dismiss();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setLettersListener(KhKeyboardView.AddLettersListener lettersListener) {
        if (keyboardUtil != null && lettersListener != null) {
            keyboardUtil.setLettersListener(lettersListener);
        }
    }

    public void setPreviewEnabled(boolean previewEnabled) {
        if (keyboardUtil != null) {
            keyboardUtil.setPreviewEnabled(previewEnabled);
        }
    }

    public void setWordRandom(boolean wordRandom) {
        if (keyboardUtil != null) {
            keyboardUtil.setWordRandom(wordRandom);
        }
    }

    public void setNumberRandom(boolean numberRandom) {
        if (keyboardUtil != null) {
            keyboardUtil.setNumberRandom(numberRandom);
        }
    }

    public void setSymbolRandom(boolean symbolRandom) {
        if (keyboardUtil != null) {
            keyboardUtil.setSymbolRandom(symbolRandom);
        }
    }

    public void setKeyboardType(String keyboardType) {
        if (TextUtils.isEmpty(keyboardType)) {
            keyboardType = "1";
        }
        if (keyboardUtil != null) {
            keyboardUtil.setKeyboardType(keyboardType);
        }
    }

    public void setDefaultInputText(String text) {
        if (!TextUtils.isEmpty(text) && keyboardUtil != null) {
            keyboardUtil.setDefaultInputText(text);
        }
    }

    public void setCursorPosition(int index) {
        if (index > -1 && keyboardUtil != null) {
            keyboardUtil.setCursorPosition(index);
        }
    }

    private Activity getActivity() {
        return mActivity;
    }
}









