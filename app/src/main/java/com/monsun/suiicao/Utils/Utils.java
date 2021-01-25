package com.monsun.suiicao.Utils;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    public static void showToast(Context mcontext,CharSequence message)
    {
        Toast.makeText(mcontext, message,Toast.LENGTH_SHORT).show();
    }
}
