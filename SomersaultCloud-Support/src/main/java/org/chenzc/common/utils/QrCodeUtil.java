package org.chenzc.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


import java.io.*;
import java.nio.file.Path;

public class QrCodeUtil {
    public static OutputStream generateQRCodeImage(String text, int width, int height) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

//        TODO
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("a.txt"));
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", bufferedOutputStream);
        return bufferedOutputStream;
    }
}
