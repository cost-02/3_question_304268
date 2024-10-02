package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Checksum {

    public static void main(String[] args) {
        File file = new File("percorso/del/tuo/file"); // Sostituisci con il percorso del file desiderato
        try {
            String md5Checksum = getFileChecksum(file);
            System.out.println("MD5 Checksum: " + md5Checksum);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static String getFileChecksum(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        FileInputStream fis = new FileInputStream(file);
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        // Leggi il file e aggiorna il digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        }
        fis.close();

        // Ottieni il checksum
        byte[] bytes = digest.digest();

        // Converte il valore del checksum in esadecimale
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        // Ritorna il valore esadecimale del checksum
        return sb.toString();
    }
}
