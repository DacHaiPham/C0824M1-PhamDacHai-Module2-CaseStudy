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
                // Hiển thị toàn bộ danh sách bài hát có sẵn
                List<Song> allSongs = controller.getAllSongs();
                System.out.println("+----+----------------------+----------------------+----------------------+");
                System.out.println("| ID |       Tên Bài Hát       |      Ca Sĩ        |       Tác Giả       |");
                System.out.println("+----+----------------------+----------------------+----------------------+");
                for (Song song : allSongs) {
                    System.out.printf("| %-2s | %-20s | %-20s | %-20s |\n",
                            song.getId(), song.getTitle(), song.getArtist(), song.getAuthor());
                }
                System.out.println("+----+----------------------+----------------------+----------------------+");

                // Nhập từ khóa để tìm kiếm
                System.out.print("Nhập tên bài hát để tìm kiếm: ");
                String title = scanner.nextLine();

                // Gọi hàm searchSong để lấy danh sách các bài hát tìm được
                List<Song> results = controller.searchSong(title);

                // Hiển thị kết quả tìm kiếm
                if (results.isEmpty()) {
                    System.out.println("Không tìm thấy bài hát phù hợp.");
                } else {
                    System.out.println("Kết quả tìm kiếm:");
                    System.out.println("+----+----------------------+----------------------+----------------------+");
                    System.out.println("| ID |       Tên Bài Hát       |      Ca Sĩ        |       Tác Giả       |");
                    System.out.println("+----+----------------------+----------------------+----------------------+");
                    for (Song song : results) {
                        System.out.printf("| %-2s | %-20s | %-20s | %-20s |\n",
                                song.getId(), song.getTitle(), song.getArtist(), song.getAuthor());
                    }
                    System.out.println("+----+----------------------+----------------------+----------------------+");
                }

                continue;  // Quay lại vòng lặp chính và hiển thị lại menu
            } else if (choice == 0) {
                System.out.println("Hẹn gặp lại.");
            } else {
                System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    public static void manageSongs(SongController controller, Scanner scanner) {
        int choice;
        do {
            System.out.println("===== QUẢN LÝ BÀI HÁT =====");
            System.out.println("1. Thêm bài hát");
            System.out.println("2. Hiển thị danh sách bài hát");
            System.out.println("3. Sửa thông tin bài hát");
            System.out.println("4. Xóa bài hát");
            System.out.println("0. Quay lại");
            System.out.print("Lựa chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: {
                    boolean continueAdding = true;

                    while (continueAdding) {
                        String id;
                        boolean isDuplicate;
                        do {
                            System.out.print("ID (phải là số): ");
                            id = scanner.nextLine();

                            // Kiểm tra id có phải là số
                            if (!id.matches("\\d+")) {  // Kiểm tra id có phải là chuỗi chỉ chứa các chữ số
                                System.out.println("ID phải là một số. Mời bạn nhập lại.");
                                isDuplicate = true;
                            } else {
                                isDuplicate = false;
                                // Kiểm tra trùng ID
                                for (Song song : controller.getAllSongs()) {
                                    if (song.getId().equals(id)) {
                                        System.out.println("ID đã tồn tại. Mời bạn nhập lại: ");
                                        isDuplicate = true;
                                        break;
                                    }
                                }
                            }
                        } while (isDuplicate);

                        // Nhập tên bài hát, ca sĩ, tác giả với các kiểm tra hợp lệ
                        System.out.print("Tên bài hát: ");
                        String title = getValidatedInput(scanner, "Tên bài hát phải là chữ. Mời bạn nhập lại: ");

                        System.out.print("Ca sĩ: ");
                        String artist = getValidatedInput(scanner, "Ca sĩ phải là chữ. Mời bạn nhập lại: ");

                        System.out.print("Tác giả: ");
                        String author = getValidatedInput(scanner, "Tác giả phải là chữ. Mời bạn nhập lại: ");

                        // Thêm bài hát
                        controller.addSong(id, title, artist, author);
                        System.out.println("Thêm bài hát thành công.");

                        // Hỏi người dùng có muốn tiếp tục thêm bài hát không
                        System.out.print("Bạn có muốn tiếp tục thêm bài hát không? (y/n): ");
                        String continueChoice = scanner.nextLine();
                        if (!continueChoice.equalsIgnoreCase("y")) {
                            continueAdding = false; // Nếu không chọn 'y', thoát khỏi vòng lặp
                        }
                    }
                    break;
                }

                case 2: {
                    List<Song> songs = controller.getAllSongs();
                    if (songs.isEmpty()) {
                        System.out.println("+--------------------------------------+");
                        System.out.println("|         Không có bài hát nào         |");
                        System.out.println("+--------------------------------------+");
                    } else {
                        System.out.println("+----+----------------------+----------------------+----------------------+");
                        System.out.println("| ID |       Tên Bài Hát       |      Ca Sĩ        |       Tác Giả       |");
                        System.out.println("+----+----------------------+----------------------+----------------------+");
                        for (Song song : songs) {
                            System.out.printf("| %-2s | %-20s | %-20s | %-20s |\n",
                                    song.getId(), song.getTitle(), song.getArtist(), song.getAuthor());
                        }
                        System.out.println("+----+----------------------+----------------------+----------------------+");
                    }
                    break;
                }
                case 3: {
                    System.out.print("ID bài hát cần sửa (phải là số): ");
                    String id = scanner.nextLine();

                    while (!id.matches("\\d+")) {
                        System.out.println("ID phải là một số. Mời bạn nhập lại.");
                        System.out.print("ID bài hát cần sửa (phải là số): ");
                        id = scanner.nextLine();
                    }

                    System.out.print("Tên mới: ");
                    String title = scanner.nextLine();
                    System.out.print("Ca sĩ mới: ");
                    String artist = scanner.nextLine();
                    System.out.print("Tác giả mới: ");
                    String author = scanner.nextLine();

                    controller.updateSong(id, title, artist, author);
                    System.out.println("Cập nhật thành công.");

                    // Hiển thị lại danh sách bài hát đã sửa
                    List<Song> songs = controller.getAllSongs();
                    System.out.println("+----+----------------------+----------------------+----------------------+");
                    System.out.println("| ID |       Tên Bài Hát       |      Ca Sĩ        |       Tác Giả       |");
                    System.out.println("+----+----------------------+----------------------+----------------------+");
                    for (Song song : songs) {
                        System.out.printf("| %-2s | %-20s | %-20s | %-20s |\n",
                                song.getId(), song.getTitle(), song.getArtist(), song.getAuthor());
                    }
                    System.out.println("+----+----------------------+----------------------+----------------------+");
                    break;
                }

                case 4: {
                    System.out.print("ID bài hát cần xóa: ");
                    String id = scanner.nextLine();

                    while (!id.matches("\\d+")) {
                        System.out.println("ID phải là một số. Mời bạn nhập lại.");
                        System.out.print("ID bài hát cần xóa (phải là số): ");
                        id = scanner.nextLine();
                    }

                    if (controller.removeSong(id)) {
                        System.out.println("Xóa thành công.");
                        // Hiển thị lại danh sách bài hát
                        List<Song> songs = controller.getAllSongs();
                        System.out.println("+----+----------------------+----------------------+----------------------+");
                        System.out.println("| ID |       Tên Bài Hát       |      Ca Sĩ        |       Tác Giả       |");
                        System.out.println("+----+----------------------+----------------------+----------------------+");
                        for (Song song : songs) {
                            System.out.printf("| %-2s | %-20s | %-20s | %-20s |\n",
                                    song.getId(), song.getTitle(), song.getArtist(), song.getAuthor());
                        }
                        System.out.println("+----+----------------------+----------------------+----------------------+");
                    } else {
                        System.out.println("Không tìm thấy bài hát.");
                    }
                    break;
                }
                case 0:
                    System.out.println("Quay lại menu chính.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 0);
    }

    // Hàm kiểm tra input hợp lệ (chỉ chứa chữ và khoảng trắng)
    private static String getValidatedInput(Scanner scanner, String errorMessage) {
        String input;
        boolean isValid;
        do {
            input = scanner.nextLine();
            isValid = input.matches("[a-zA-ZÀ-ỹ\\s]+");
            if (!isValid) {
                System.out.println(errorMessage);
            }
        } while (!isValid);
        return input;
    }

//    public static void searchSong

}

