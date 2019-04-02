package css.com.fuck.utils.inputfilter;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditTextLengthFilter implements InputFilter {
        int maxEn;
        String regEx = "[\\u4e00-\\u9fa5]";

        public EditTextLengthFilter(int maxEn) {
            super();
            this.maxEn = maxEn;
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            int destCount = dest.toString().length()
                    + getChineseCount(dest.toString());
            int sourceCount = source.toString().length()
                    + getChineseCount(source.toString());
            if (destCount + sourceCount > maxEn) {
                int surplusCount = maxEn - destCount;
                String result = "";
                int index = 0;
                while (surplusCount > 0) {
                    char c = source.charAt(index);
                    if (isChinest(c + "")) {
                        if (sourceCount >= 2) {
                            result += c;
                        }
                        surplusCount = surplusCount - 2;
                    } else {
                        result += c;
                        surplusCount = surplusCount - 1;
                    }
                    index++;
                }
                return result;
            } else {
                return source;
            }
        }

        private int getChineseCount(String str) {
            int count = 0;
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            while (m.find()) {
                for (int i = 0; i <= m.groupCount(); i++) {
                    count = count + 1;
                }
            }
            return count;
        }
        private boolean isChinest(String source) {
            return Pattern.matches(regEx, source);
        }
    }