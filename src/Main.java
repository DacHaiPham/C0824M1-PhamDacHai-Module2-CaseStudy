import Controller.SongController;
import Entity.Song;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SongController controller = new SongController();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("===== MENU =====");
            System.out.println("1. Quản lý danh sách bài hát");
            System.out.println("2. Người dùng tìm kiếm bài hát");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                manageSongs(controller, scanner);
            } else if (choice == 2) {
                searchSongs(controller, scanner);
            } else if (choice == 0) {
                System.out.println("Thoát chương trình.");
            } else {
                System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    public static void manageSongs(SongController controller, Scanner scanner) {
        int choice = -1;

        while (choice != 0) {
            System.out.println("===== QUẢN LÝ BÀI HÁT =====");
            System.out.println("1. Thêm bài hát");
            System.out.println("2. Hiển thị danh sách bài hát");
            System.out.println("3. Sửa thông tin bài hát");
            System.out.println("4. Xóa bài hát");
            System.out.println("0. Quay lại");
            System.out.print("Lựa chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("ID: ");
                String id = scanner.nextLine();
                System.out.print("Tên bài hát: ");
                String title = scanner.nextLine();
                System.out.print("Ca sĩ: ");
                String artist = scanner.nextLine();
                System.out.print("Tác giả: ");
                String composer = scanner.nextLine();
                controller.addSong(id, title, artist, composer);
                System.out.println("Thêm bài hát thành công.");
            } else if (choice == 2) {
                List<Song> songs = controller.displayAllSongs();;
                songs.forEach(System.out::println);
            } else if (choice == 3) {
                System.out.print("ID bài hát cần sửa: ");
                String id = scanner.nextLine();
                System.out.print("Tên mới: ");
                String title = scanner.nextLine();
                System.out.print("Ca sĩ mới: ");
                String artist = scanner.nextLine();
                System.out.print("Tác giả mới: ");
                String composer = scanner.nextLine();
                controller.updateSong(id, title, artist, composer);
                System.out.println("Cập nhật thành công.");
            } else if (choice == 4) {
                System.out.print("ID bài hát cần xóa: ");
                String id = scanner.nextLine();
                if (controller.removeSong(String.valueOf(id))) {
                    System.out.println("Xóa thành công.");
                } else {
                    System.out.println("Không tìm thấy bài hát.");
                }
            } else if (choice == 0) {
                System.out.println("Quay lại menu chính.");
            } else {
                System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private static void searchSongs(SongController controller, Scanner scanner) {
        System.out.print("Nhập từ khóa tìm kiếm (theo tên bài hát): ");
        String keyword = scanner.nextLine();
        List<Song> songs = controller.searchByTitle(keyword);
        if (songs.isEmpty()) {
            System.out.println("Không tìm thấy bài hát nào.");
        } else {
            songs.forEach(System.out::println);
        }
    }
}

