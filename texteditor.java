import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

class TextEditor {
    private StringBuilder currentText;
    private Stack<String> undoStack;
    private LinkedList<String> redoQueue;

    public TextEditor() {
        currentText = new StringBuilder();
        undoStack = new Stack<>();
        redoQueue = new LinkedList<>();
    }

    // Fungsi untuk menampilkan semua teks
    public void show() {
        if (currentText.length() == 0) {
            System.out.println("Editor kosong.");
        } else {
            System.out.println("Teks saat ini:");
            System.out.println(currentText.toString());
        }
    }

    // Fungsi untuk menulis teks
    public void write(String text) {
        // Simpan keadaan teks saat ini sebelum menulis
        undoStack.push(currentText.toString());
        redoQueue.clear(); // Kosongkan redo queue saat ada penulisan baru
        currentText.append(text);
        System.out.println("Teks ditambahkan: " + text);
    }

    // Fungsi untuk membatalkan aksi terakhir
    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Tidak ada tindakan untuk dibatalkan.");
            return;
        }
        // Simpan keadaan saat ini ke redo queue
        redoQueue.add(currentText.toString());
        // Kembalikan keadaan sebelumnya
        currentText.setLength(0); // Kosongkan teks saat ini
        currentText.append(undoStack.pop());
        System.out.println("Undo dilakukan. Teks sebelumnya dipulihkan.");
    }

    // Fungsi untuk mengulang tindakan terakhir
    public void redo() {
        if (redoQueue.isEmpty()) {
            System.out.println("Tidak ada tindakan untuk diulang.");
            return;
        }
        // Simpan keadaan saat ini ke undo stack
        undoStack.push(currentText.toString());
        // Pulihkan keadaan lebih baru
        currentText.setLength(0); // Kosongkan teks saat ini
        currentText.append(redoQueue.pollLast());
        System.out.println("Redo dilakukan. Teks saat ini dipulihkan.");
    }
}

public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nPilihan:");
            System.out.println("1. Show Teks");
            System.out.println("2. Write Teks");
            System.out.println("3. Undo");
            System.out.println("4. Redo");
            System.out.println("5. Exit");

            System.out.print("Masukkan pilihan (1/2/3/4/5): ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Membersihkan newline

            switch (choice) {
                case 1:
                    editor.show();
                    break;
                case 2:
                    System.out.print("Masukkan teks yang ingin ditulis: ");
                    String text = scanner.nextLine();
                    editor.write(text);
                    break;
                case 3:
                    editor.undo();
                    break;
                case 4:
                    editor.redo();
                    break;
                case 5:
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
