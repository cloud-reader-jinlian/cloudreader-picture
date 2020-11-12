package com.zkteco.android.IDReader;

import android.graphics.Bitmap;

public class IDCardPhoto {
    public IDCardPhoto() {
    }

    public static Bitmap getIDCardPhoto(byte[] encryptedPhotoInfo) {
        byte[] buf = new byte[WLTService.imgLength];
        return 1 == WLTService.wlt2Bmp(encryptedPhotoInfo, buf) ? IDPhotoHelper.Bgr2Bitmap(buf) : null;
    }

	public static Bitmap getIDCardPhoto(String encryptedPhotoInfo) {
		byte[] buf = new byte[WLTService.imgLength];
		return 1 == WLTService.wlt2Bmp(HexString2Bytes(encryptedPhotoInfo), buf) ? IDPhotoHelper.Bgr2Bitmap(buf) : null;
	}

	private static int parse(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}

	public static byte[] HexString2Bytes(String hexstr) {
		byte[] b = new byte[hexstr.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexstr.charAt(j++);
			char c1 = hexstr.charAt(j++);
			b[i] = (byte) ((parse(c0) << 4) | parse(c1));
		}
		return b;
	}

}
