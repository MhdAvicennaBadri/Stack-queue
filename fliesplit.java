import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FileSplitter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Minta pengguna untuk memasukkan nama file
        System.out.print("Masukkan nama file (beserta ekstensi): ");
        String fileName = scanner.nextLine();

        // Minta pengguna untuk memasukkan ukuran potongan
        System.out.print("Masukkan ukuran potongan dalam karakter: ");
        int chunkSize = scanner.nextInt();

        // Queue untuk menyimpan potongan teks
        Queue<String> chunks = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder currentChunk = new StringBuilder();
            int charRead;
            while ((charRead = br.read()) != -1) {
                currentChunk.append((char) charRead);
                
                // Jika ukuran potongan mencapai batas, masukkan ke dalam Queue
                if (currentChunk.length() >= chunkSize) {
                    chunks.add(currentChunk.toString());
                    currentChunk.setLength(0); // Kosongkan untuk potongan berikutnya
                }
            }

            // Tambahkan potongan terakhir jika ada
            if (currentChunk.length() > 0) {
                chunks.add(currentChunk.toString());
            }

            // Menampilkan potongan-potongan yang ada dalam Queue
            System.out.println("\nPotongan file:");
            while (!chunks.isEmpty()) {
                System.out.println("Potongan: " + chunks.poll());
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        }

        scanner.close();
    }
}
