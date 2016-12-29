package com.cst.growthprocess.pinyinpinyin4j;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cst.growthprocess.R;
import com.cst.growthprocess.Utils.ToastDiy;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class Pinyin4jActivity extends AppCompatActivity {

    @InjectView(R.id.edit_cityname)
    EditText mEditCityname;
    @InjectView(R.id.button)
    Button mButton;
    @InjectView(R.id.activity_retrofit_result)
    TextView mResult;
    @InjectView(R.id.fast_match_search)
    ImageView mSearch;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinyin4j);
        ButterKnife.inject(this);
        mContext = this;
        mEditCityname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private HanyuPinyinOutputFormat getPinyinFormat() {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

        // UPPERCASE：大写  (ZHONG)
        // LOWERCASE：小写  (zhong)
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);

        // WITHOUT_TONE：无音标  (zhong)
        // WITH_TONE_NUMBER：1-4数字表示英标  (zhong4)
        // WITH_TONE_MARK：直接用音标符（必须WITH_U_UNICODE否则异常）  (zhòng)
        format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);

        // WITH_V：用v表示ü  (nv)
        // WITH_U_AND_COLON：用"u:"表示ü  (nu:)
        // WITH_U_UNICODE：直接用ü (nü)
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);

        return format;
    }

    @OnClick(R.id.button)
    public void onClick() {
        String name = mEditCityname.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            ToastDiy.Show(mContext, "请输入汉字或者拼音");
            return;
        } else {
            String[] pinyin = new String[0];
            try {
                pinyin = PinyinHelper.toHanyuPinyinStringArray(name.charAt(0), getPinyinFormat());
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
            }

            mResult.setText(pinyin[1]);
        }


    }
}
