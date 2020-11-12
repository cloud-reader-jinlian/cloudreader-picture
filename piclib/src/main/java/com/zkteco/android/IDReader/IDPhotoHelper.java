package com.zkteco.android.IDReader;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

public class IDPhotoHelper {
	public IDPhotoHelper() {
    }

    public static Bitmap Bgr2Bitmap(byte[] bgrbuf) {
        int width = WLTService.imgWidth;
        int height = WLTService.imgHeight;
        Bitmap bmp = Bitmap.createBitmap(width, height, Config.RGB_565);
        int row = 0;
        int col = width - 1;

        for(int i = bgrbuf.length - 1; i >= 3; i -= 3) {
            int color = bgrbuf[i] & 255;
            color += bgrbuf[i - 1] << 8 & '\uff00';
            color += bgrbuf[i - 2] << 16 & 16711680;
            bmp.setPixel(col--, row, color);
            if(col < 0) {
                col = width - 1;
                ++row;
            }
        }

        return bmp;
    }
}
