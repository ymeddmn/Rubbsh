package com.sitech.keyboard;

import android.app.Activity;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.os.SystemClock;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class KhKeyboardView {
    private Activity mContext;
    private View parentView;
    // 字母键盘view
    private KeyboardView mLetterView;
    // 数字键盘View
    private KeyboardView mNumberView;
    // 数字键盘
    private Keyboard mNumberKeyboard;
    // 字母键盘
    private Keyboard mLetterKeyboard;
    // 符号键盘
    private Keyboard mSymbolKeyboard;
    // 是否数字键盘
    private boolean isNumber = true;
    // 是否大写
    public static boolean isUpper = false;
    // 是否是符号
    private boolean isSymbol = false;
    private EditText mEditText;
    private View headerView;
    private List<String> letters;
    private String TAG = "lingxu";
    private AddLettersListener lettersListener;
    private boolean isPreviewEnabled = true;
    private boolean isWordRandom = false;
    private boolean isNumberRandom = false;
    private boolean isSymbolRandom = false;
    // 0 数字键盘 1 所有键盘
    private String keyboardType = "1";
    private String defaultInputText = "";
    private int cursorPosition = -1;

    public void setEditText(EditText text) {
        mEditText = text;
    }

    public KhKeyboardView(Activity context, View view) {
        mContext = context;
        parentView = view;
    }

    private void init() {
        mNumberKeyboard = new Keyboard(mContext, R.xml.keyboard_numbers);
        mLetterKeyboard = new Keyboard(mContext, R.xml.keyboard_word);
        mSymbolKeyboard = new Keyboard(mContext, R.xml.keyboard_symbol);
        mNumberView = parentView.findViewById(R.id.keyboard_view);
        mLetterView = parentView.findViewById(R.id.keyboard_view_2);

//        if("1".equals(keyboardType)) {
        if (isWordRandom) {
            randomKey(mLetterKeyboard, mLetterView, "word");
        } else {
            mLetterView.setKeyboard(mLetterKeyboard);
        }
        mLetterView.setEnabled(true);
        mLetterView.setPreviewEnabled(isPreviewEnabled);
        mLetterView.setOnKeyboardActionListener(listener);
//        }else
        if ("0".equals(keyboardType)) {
            List<Key> keys = mNumberKeyboard.getKeys();
            int index = -1;
            for (int i = 0; i < keys.size(); i++) {
                if (keys.get(i).codes[0] == -2) {
                    index = i;
                    break;
                }
            }
            if (index > -1) {
                mNumberKeyboard.getKeys().remove(index);
            }
        }

        if (isNumberRandom) {
            randomKey(mNumberKeyboard, mNumberView, "number");
        } else {
            mNumberView.setKeyboard(mNumberKeyboard);
        }
        mNumberView.setEnabled(true);
        mNumberView.setPreviewEnabled(false);
        mNumberView.setOnKeyboardActionListener(listener);

        headerView = parentView.findViewById(R.id.keyboard_header);
        letters = new ArrayList<>();
        if (!TextUtils.isEmpty(defaultInputText)) {
            char[] array = defaultInputText.toCharArray();
            for (char c : array) {
                letters.add(String.valueOf(c));
            }
        }
    }

    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) {
            Log.d("primaryCode", "onPress--" + primaryCode);
            if (primaryCode == Keyboard.KEYCODE_SHIFT) {
                mLetterView.setPreviewEnabled(false);
            } else if (primaryCode == Keyboard.KEYCODE_DELETE) {
                mLetterView.setPreviewEnabled(false);
            } else if (primaryCode == 32) {
                mLetterView.setPreviewEnabled(false);
            } else if (primaryCode == -2) {
                mLetterView.setPreviewEnabled(false);
            } else {
                mLetterView.setPreviewEnabled(isPreviewEnabled);
            }
        }

        @Override
        public void onRelease(int primaryCode) {
            Log.d("primaryCode", "onRelease--" + primaryCode);
        }

        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            Log.d("primaryCode", "onKey--" + primaryCode);
            try {
                int start;
                Editable editable = null;
                if (mEditText == null) {
                    start = cursorPosition > -1 ? cursorPosition : letters.size();
                } else {
                    editable = mEditText.getText();
                    start = mEditText.getSelectionStart();
                }
                if (primaryCode == Keyboard.KEYCODE_CANCEL) {
                    // 隐藏键盘
                    hideKeyboard();
                } else if (primaryCode == Keyboard.KEYCODE_DELETE || primaryCode == -35) {
                    // 回退键,删除字符
                    if (editable != null && editable.length() > 0) {
                        if (start > 0) {
                            editable.delete(start - 1, start);
                        }
                    } else {
                        if (letters != null && !letters.isEmpty()) {
                            if (start > 0) {
                                letters.remove(start - 1);
                                if (lettersListener != null) {
                                    String ss = TextUtils.join("", letters);
                                    Log.d(TAG, "onKey deleted: " + ss);
                                    lettersListener.lettersCallBack(ss);
                                }
                            }
                        }
                    }
                } else if (primaryCode == Keyboard.KEYCODE_SHIFT) {
                    // 大小写切换
                    changeKeyboard();
                    mLetterView.setKeyboard(mLetterKeyboard);
                } else if (primaryCode == Keyboard.KEYCODE_MODE_CHANGE) {
                    // 数字与字母键盘互换
                    if (isNumber) {
                        showLetterView();
                        showLetterView2();
                    } else {
                        showNumberView();
                        showNumberView2();
                    }
                } else if (primaryCode == 90001) {
//                  字母与符号切换
                    if (isSymbol) {
                        showLetterView2();
                    } else {
                        showSymbolView();
                    }
                } else {
                    // 输入键盘值
                    if (editable != null) {
                        editable.insert(start, Character.toString((char) primaryCode));
                    } else {
                        letters.add(start, Character.toString((char) primaryCode));
                        if (lettersListener != null) {
                            String ss = TextUtils.join("", letters);
                            Log.d(TAG, "onKey added: " + ss);
                            lettersListener.lettersCallBack(ss);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onText(CharSequence text) {
            Log.d("lingxu ", "onText: " + text.toString());
        }

        @Override
        public void swipeLeft() {

        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void swipeUp() {

        }
    };

    //  字母-符号,显示字母
    private void showLetterView2() {
        if (mLetterView != null) {
            isSymbol = false;
            if (isWordRandom) {
                randomKey(mLetterKeyboard, mLetterView, "word");
            } else {
                mLetterView.setKeyboard(mLetterKeyboard);
            }


        }
    }

    //  字母-符号,显示符号
    private void showSymbolView() {
        try {
            if (mLetterKeyboard != null) {
                isSymbol = true;
                if (isSymbolRandom) {
                    randomKey(mSymbolKeyboard, mLetterView, "symbol");
                } else {
                    mLetterView.setKeyboard(mSymbolKeyboard);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  数字-字母,显示字母键盘
    private void showLetterView() {
        try {
            if (mLetterView != null && mNumberView != null) {
                isNumber = false;
                mLetterView.setVisibility(View.VISIBLE);
                mNumberView.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 数字-字母, 显示数字键盘
    private void showNumberView() {
        try {
            if (mLetterView != null && mNumberView != null) {
                isNumber = true;
                mLetterView.setVisibility(View.INVISIBLE);
                mNumberView.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showNumberView2() {
        try {
            if (mNumberKeyboard != null) {
                if (isNumberRandom) {
                    randomKey(mNumberKeyboard, mNumberView, "number");
                } else {
                    mNumberView.setKeyboard(mNumberKeyboard);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 切换大小写
     */
    private void changeKeyboard() {
        List<Keyboard.Key> keyList = mLetterKeyboard.getKeys();
        if (isUpper) {
            // 大写切换小写
            isUpper = false;
            for (Keyboard.Key key : keyList) {
                if (key.label != null && isLetter(key.label.toString())) {
                    key.label = key.label.toString().toLowerCase();
                    key.codes[0] = key.codes[0] + 32;
                }
            }
        } else {
            // 小写切换成大写
            isUpper = true;
            for (Keyboard.Key key : keyList) {
                if (key.label != null && isLetter(key.label.toString())) {
                    key.label = key.label.toString().toUpperCase();
                    key.codes[0] = key.codes[0] - 32;
                }
            }
        }
    }

    /**
     * 判断是否是字母
     */
    private boolean isLetter(String str) {
        return "abcdefghijklmnopqrstuvwxyz".contains(str.toLowerCase());
    }

    public void hideKeyboard() {
        try {
            int visibility = mLetterView.getVisibility();
            if (visibility == View.VISIBLE) {
                headerView.setVisibility(View.GONE);
                mLetterView.setVisibility(View.GONE);
            }
            visibility = mNumberView.getVisibility();
            if (visibility == View.VISIBLE) {
                headerView.setVisibility(View.GONE);
                mNumberView.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 显示键盘
     *
     * @param editText
     */
    public void showKeyboard(EditText editText) {
        try {
            init();
            this.mEditText = editText;
            int inputText = mEditText.getInputType();
            headerView.setVisibility(View.VISIBLE);
            if ("0".equals(keyboardType)) {
                showNumberView();
                return;
            }
            switch (inputText) {
                case InputType.TYPE_CLASS_NUMBER:
                    showNumberView();
                    break;
                case InputType.TYPE_CLASS_PHONE:
                    showNumberView();
                    break;
                case InputType.TYPE_NUMBER_FLAG_DECIMAL:
                    showNumberView();
                    break;
                default:
                    showLetterView();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showKeyboard(int inputText) {
        try {
            init();
            headerView.setVisibility(View.VISIBLE);
            if ("0".equals(keyboardType)) {
                showNumberView();
                return;
            }
            switch (inputText) {
                case InputType.TYPE_CLASS_NUMBER:
                    showNumberView();
                    break;
                case InputType.TYPE_CLASS_PHONE:
                    showNumberView();
                    break;
                case InputType.TYPE_NUMBER_FLAG_DECIMAL:
                    showNumberView();
                    break;
                default:
                    showLetterView();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface AddLettersListener {
        void lettersCallBack(String letters);
    }

    public void setLettersListener(AddLettersListener lettersListener) {
        this.lettersListener = lettersListener;
    }

    public void setPreviewEnabled(boolean previewEnabled) {
        isPreviewEnabled = previewEnabled;
    }


    private void randomKey(Keyboard keyboard, KeyboardView khKeyboardView, String flag) {
        //键盘所有key集合，包含在keyboard对象中，本方法主要就是修改keyboard中keys集合
        List<Key> keyList = keyboard.getKeys();
        // 不包含功能key的按键集合，可用于输入的，
        List<Key> newKeyList = new ArrayList<>();
        //从配置的键盘中，筛选出对应的key，去除删除 切换 大小写等功能键按钮
        for (int i = 0; i < keyList.size(); i++) {
            Key key = keyList.get(i);
            if (key.label != null && key.label.length() == 1) {
                if ("word".equals(flag)) {
                    //字母键盘中，包含数字，也需要加入进去
                    if (isLetter(key.label.toString()) || isNumeric(key.label.toString())) {
                        newKeyList.add(key);
                    }
                } else if ("number".equals(flag) && isNumeric(key.label.toString())) {
                    newKeyList.add(key);
                } else if ("symbol".equals(flag) && isSymbols(key.label.toString())) {
                    newKeyList.add(key);
                }
            }
        }
        // 这个是可用的按键key的总数，以下所有数据处理，最终的结果都应该等于这个总数
        int count = newKeyList.size();
        // 最终的结果集合
        List<KeyModel> resultList = new ArrayList<>();
        // 临时集合，从newKeyList中，深度复制一个同样的集合，用于处理数据
        LinkedList<Key> temp = new LinkedList<>(Arrays.asList(new Key[newKeyList.size()]));
        // 深度复制
        Collections.copy(temp, newKeyList);

        Random rand = new SecureRandom();
        rand.setSeed(SystemClock.currentThreadTimeMillis());
        // 随机位置生成
        for (int i = 0; i < count; i++) {
            // 获取一个小于等于 count的随机整数
            int num = rand.nextInt(count - i);
            // 获取临时集合中，对应的key
            Key model = temp.get(num);
            // 将获取的key对象，添加到结果集合中
            resultList.add(new KeyModel(model.codes[0], model.label + ""));
            // 将临时集合中对应的Key删除
            temp.remove(num);
        }
        // 如果是字母键盘，由于包含数字，所以需要单独处理
        if ("word".equals(flag)) {
            // 结果集合中的数字集合
            List<KeyModel> numberKeys = new ArrayList<>();
            // 临时集合，结果集合复制而来
            List<KeyModel> temp1 = new ArrayList<>(Arrays.asList(new KeyModel[resultList.size()]));
            // 复制
            Collections.copy(temp1, resultList);
            // 先将数字提出来，在将提出来的数字给塞进去, 这样数字不会和字母混合在一起
            for (int i = 0; i < count; i++) {
                if (isNumeric(temp1.get(i).getLabel())) {
                    // 如果是数字的话，加入到数字集合
                    numberKeys.add(new KeyModel(temp1.get(i).getCode(), temp1.get(i).getLabel()));
                    // 删除结果集合中的相同对象，此处不能用索引i，否则会越界异常
                    resultList.remove(temp1.get(i));
                }
            }
            // 数字集合重新添加到结果集合中
            resultList.addAll(0, numberKeys);
        }
        // 最后一步，修改newKeyList，即keyboard中keys集合
        for (int i = 0; i < count; i++) {
            newKeyList.get(i).label = resultList.get(i).getLabel();
            newKeyList.get(i).codes[0] = resultList.get(i).getCode();
        }
        // 重新加入键盘对象，键盘会更新。
        khKeyboardView.setKeyboard(keyboard);
    }

    /**
     * !@#$%^&*()'"=_:;?~|·+-\/[]{},.<>€￡¥
     *
     * @param str
     * @return
     */
    private boolean isSymbols(String str) {
        return "!@#$%^&*()'\"=_:;?~|·+-\\/[]{},.<>€￡¥".contains(str);
    }

    private boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void setWordRandom(boolean wordRandom) {
        isWordRandom = wordRandom;
    }

    public void setNumberRandom(boolean numberRandom) {
        isNumberRandom = numberRandom;
    }

    public void setSymbolRandom(boolean symbolRandom) {
        isSymbolRandom = symbolRandom;
    }

    public void setKeyboardType(String keyboardType) {
        this.keyboardType = keyboardType;
    }

    public void setDefaultInputText(String defaultInputText) {
        this.defaultInputText = defaultInputText;
    }

    public void setCursorPosition(int cursorPosition) {
        this.cursorPosition = cursorPosition;
    }
}
