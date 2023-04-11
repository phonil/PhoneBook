package final_project;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Person { // 주소록에 들어갈 순번,이름,나이,전화번호 보유한 Person 클래스. / 각 객체는 ArrayList로 관리될 것.
	int order;
	String name, age, phoneNumber;

	public Person(int order, String name, String age, String phoneNumber) {
		this.order = order;
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
	}
}

public class finalproject {
	Scanner sc = new Scanner(System.in);
	ArrayList<Person> tel = new ArrayList<Person>(); // 주소록. Person타입 객체들을 모아 관리.
	// 멤버변수
	int sequnce = 1; // 순서. 등록되는 순서이며, 순번이 매겨짐.

	// 메소드
	void print_menu() { // 메뉴화면 출력해주는 메소드
		System.out.println("\n1.연락처 출력 \n2.연락처 등록 \n3.연락처 삭제 \n4.끝내기");
		System.out.print("\n메뉴를 선택하세요: "); // 입력받을 변수는 메인 메소드에.
	}

	String add_juso() { // 이름, 나이, 번호 입력받은 후 순번과 함께 반환. / 메인 메소드에서 파일에 작성될 것.
		System.out.print("이름 입력: ");
		String name = sc.nextLine();
		System.out.print("나이 입력: ");
		String age = sc.nextLine();
		System.out.print("번호 입력: ");
		String phoneNumber = sc.nextLine();
		tel.add(new Person(sequnce, name, age, phoneNumber));
		return String.format("[%d] %s %s %s", sequnce++, name, age, phoneNumber); // 저장할 때마다 sequnce 1씩 증가, 순번이 됨.
	}

	void advanced_delete_function_menu(FileWriter fw) throws IOException { // 고급 삭제 기능 메뉴화면 출력
																			// FileWriter타입 변수를 매개변수로 받음
		System.out.println("1.순번 삭제 \n2.이름 삭제 \n3.번호 삭제 \n4.메인 메뉴로 이동 \n");
		System.out.print("세부 메뉴를 선택하세요: ");
		this.advanced_delete_function(fw); // 고급 삭제 기능 메소드 호출
	}

	void advanced_delete_function(FileWriter fw) throws IOException { // 고급 삭제 기능 메소드
		String a_d_f = sc.nextLine(); // 세부 메뉴 선택
		System.out.println();
		String a_d_n; // 순번
		String a_d_name; // 이름
		String a_d_phoneNum; // 번호
		if (a_d_f.equals("1")) { // 1.순번 삭제 선택 시
			while (true) {
				System.out.print("삭제할 연락처 순번은? ");
				a_d_n = sc.nextLine(); // 순번 입력받기
				if (Integer.parseInt(a_d_n) > tel.size() || Integer.parseInt(a_d_n) <= 0) { // 입력받은 순번이 주소록에 없을 때.
																							// int 타입으로 변환 후 판단했음.
					if (a_d_n.equals("0")) {
						System.out.println("삭제하지 않고 메뉴로 돌아갑니다.\n"); // 0 선택 시 삭제하지 않고 메뉴로 돌아감.
						return;
					} else {
						System.out.println("존재하지 않는 연락처입니다.\n"); // 해당 순번의 연락처가 없을 시.
						continue; // 삭제할 순번 다시 입력받기 위함
					}
				}
				break; // 반복문 탈출 후 진행
			}
			for (int i = 0; i < tel.size(); i++) { // 주소록의 모든 연락처들 확인하기 위한 반복문
				if (tel.get(i).order == Integer.parseInt(a_d_n)) { // 입력받은 순번 발견 시
					System.out.println(tel.get(i).order + "번 연락처가 삭제되었습니다.");
					tel.remove(i); // 해당 순번의 연락처 삭제
					this.sequnce--; // 연락처 1개 삭제 되었으니, 다음 연락처 등록 시 순번 조정하기 위함.
					fw = new FileWriter("juso.txt", false); // 해당 파일 존재 시 덮어쓰기 위함(false)
					for (int k = 0; k < tel.size(); k++) { // tel(주소록)의 모든 연락처를 파일에 작성하기 위한 반복문
						if (k >= i) { // 삭제된 연락처의 순번보다 크거나 같다면 순번 조정한 후 파일에 작성
							fw.write("\n" + String.format("[%d] %s %s %s", tel.get(k).order - 1, tel.get(k).name,
									tel.get(k).age, tel.get(k).phoneNumber) + "\n");
							tel.get(k).order -= 1;
						} else { // 삭제된 연락처의 순번보다 앞에 있으므로 순번조정 필요 x
							fw.write("\n" + String.format("[%d] %s %s %s", tel.get(k).order, tel.get(k).name,
									tel.get(k).age, tel.get(k).phoneNumber) + "\n");
						}
					}
					fw.close(); // 순번 조정된 파일 작성 완료.
				}
			}
		} else if (a_d_f.equals("2")) { // 2.이름 삭제 선택 시
			all: while (true) {
				System.out.print("삭제할 연락처 이름은? ");
				a_d_name = sc.nextLine(); // 삭제할 연락처 이름 입력받기
				for (int i = 0; i <= tel.size(); i++) { // 주소록 모든 객체(연락처)들 확인하기 위한 반복문
					if (tel.get(i).name.equals(a_d_name) == false) { // 입력받은 이름과 연락처의 이름과 다를 때
						if (a_d_name.equals("종료")) { // 입력받은 이름이 "종료"라면
							System.out.println("삭제하지 않고 메뉴로 돌아갑니다.\n"); // 삭제하지 않고 메뉴로 돌아감.
							return;
						} else { // 입력받은 이름과 연락처의 이름이 다르고, "종료"입력받지 않았을 때.
							if (i == tel.size() - 1) { // 모든 연락처를 다 확인해 본 후 (tel.size-1이 마지막 연락처이므로 여기까지 왔다면
														// 입력받은 이름이 연락처에 없다는 것.)
								System.out.println("존재하지 않는 연락처입니다.\n");
								continue all; // all: 표시 된 반복문으로 / 삭제할 이름 다시 입력받기 위함
							}
						}
					} else
						break; // 입력받은 이름과 연락처 속 이름이 같다면 진행을 위해 반복문 탈출
				}
				break; // 반복문 탈출 후 진행
			}
			for (int i = 0; i < tel.size(); i++) { // 주소록 모든 연락처들 확인하기 위한 반복문
				if (tel.get(i).name.equals(a_d_name)) { // 입력받은 이름 발견 시
					System.out.println(a_d_name + " 연락처가 삭제되었습니다.");
					tel.remove(i); // 해당 순번의 연락처 삭제
					this.sequnce--; // 연락처 1개 삭제 되었으니, 다음 연락처 등록 시 순번 조정하기 위함.
					fw = new FileWriter("juso.txt", false); // 해당 파일 존재 시 덮어쓰기 위함(false)
					for (int k = 0; k < tel.size(); k++) { // tel(주소록)의 모든 연락처를 파일에 작성하기 위한 반복문
						if (k >= i) { // 삭제된 연락처의 순번보다 크거나 같다면 순번 조정한 후 파일에 작성
							fw.write("\n" + String.format("[%d] %s %s %s", tel.get(k).order - 1, tel.get(k).name,
									tel.get(k).age, tel.get(k).phoneNumber) + "\n");
							tel.get(k).order -= 1;
						} else { // 삭제된 연락처의 순번보다 앞에 있으므로 순번조정 필요 x
							fw.write("\n" + String.format("[%d] %s %s %s", tel.get(k).order, tel.get(k).name,
									tel.get(k).age, tel.get(k).phoneNumber) + "\n");
						}
					}
					fw.close(); // 순번 조정된 파일 작성 완료.
				}
			}
		} else if (a_d_f.equals("3")) { // 3.번호 삭제 선택 시
			all: while (true) {
				System.out.print("삭제할 연락처 번호는? ");
				a_d_phoneNum = sc.nextLine(); // 삭제할 연락처 번호 입력받기
				for (int i = 0; i <= tel.size(); i++) { // 주소록 모든 객체(연락처)들 확인하기 위한 반복문
					if (tel.get(i).phoneNumber.equals(a_d_phoneNum) == false) { // 입력받은 번호와 연락처의 번호와 다를 때
						if (a_d_phoneNum.equals("종료")) { // 입력받은 이름이 "종료"라면
							System.out.println("삭제하지 않고 메뉴로 돌아갑니다.\n"); // 삭제하지 않고 메뉴로 돌아감.
							return;
						} else { // 입력받은 번호와 연락처의 번호가 다르고, "종료"입력받지 않았을 때.
							if (i == tel.size() - 1) { // 모든 연락처를 다 확인해 본 후 (tel.size-1이 마지막 연락처이므로 여기까지 왔다면
														// 입력받은 번호가 연락처에 없다는 것.)
								System.out.println("존재하지 않는 연락처입니다.\n");
								continue all; // all: 표시 된 반복문으로 / 삭제할 번호 다시 입력받기 위함
							}
						}
					} else
						break; // 입력받은 번호와 연락처 속 번호가 같다면 진행을 위해 반복문 탈출
				}
				break;
			}
			for (int i = 0; i < tel.size(); i++) { // 주소록 모든 연락처들 확인하기 위한 반복문
				if (tel.get(i).phoneNumber.equals(a_d_phoneNum)) { // 입력받은 번호 발견 시
					System.out.println(tel.get(i).order + "번 연락처가 삭제되었습니다.");
					tel.remove(i); // 해당 순번의 연락처 삭제
					this.sequnce--; // 연락처 1개 삭제 되었으니, 다음 연락처 등록 시 순번 조정하기 위함.
					fw = new FileWriter("juso.txt", false); // 해당 파일 존재 시 덮어쓰기 위함(false)
					for (int k = 0; k < tel.size(); k++) { // tel(주소록)의 모든 연락처를 파일에 작성하기 위한 반복문
						if (k >= i) { // 삭제된 연락처의 순번보다 크거나 같다면 순번 조정한 후 파일에 작성
							fw.write("\n" + String.format("[%d] %s %s %s", tel.get(k).order - 1, tel.get(k).name,
									tel.get(k).age, tel.get(k).phoneNumber) + "\n");
							tel.get(k).order -= 1;
						} else { // 삭제된 연락처의 순번보다 앞에 있으므로 순번조정 필요 x
							fw.write("\n" + String.format("[%d] %s %s %s", tel.get(k).order, tel.get(k).name,
									tel.get(k).age, tel.get(k).phoneNumber) + "\n");
						}
					}
					fw.close(); // 순번 조정된 파일 작성 완료.
				}
			}
		} else if (a_d_f.equals("4")) { // 아래의 리턴을 타고 메인 메뉴로 돌아감
			System.out.println("메인 메뉴로 이동합니다.\n");

		} else {
			System.out.print("잘못된 세부 메뉴입니다. 세부 메뉴를 다시 선택하세요: ");
			this.advanced_delete_function(fw); // 세부메뉴 다시 선택받고 작동 위함
		}
		return;
	}

	public static void main(String[] args) throws IOException {
		System.out.println("[기말프로젝트] 60190927 강승우");
		System.out.println("[연락처 관리 프로그램]");

		Scanner sc = new Scanner(System.in);
		finalproject p = new finalproject();
		File file = new File("juso.txt");
		FileWriter fw;
		FileReader fr;
		int ch;

		all: while (true) {
			p.print_menu(); // 메뉴화면 출력
			String n;
			while (true) {
				n = sc.nextLine(); // 메뉴화면에서 수행할 기능 입력받기
				System.out.println();
				if (n.equals("1")) {
					fr = new FileReader(file);
					if ((ch = fr.read()) == -1) { // 파일에 읽을 문자가 없을 경우
						System.out.println("저장된 연락처가 없습니다. \n");

					} else {
						while ((ch = fr.read()) != -1) { // 파일에 읽을 문자 없을 때까지
							System.out.print((char) ch); // 한글자씩 데이터 읽기
						}
						fr.close();
					}
					break; // 1.연락처 출력 기능 수행 후 탈출. / 메뉴화면 출력 반복문으로..
				} else if (n.equals("2")) {
					fw = new FileWriter(file, true); // 파일이 이미 존재한다면 이어쓰기(true)
					fw.write("\n" + p.add_juso() + "\n"); // add_juso 메소드에서 반환받은 값 파일에 작성
					fw.close();
					break; // 2.연락처 등록 기능 수행 후 탈출.
				} else if (n.equals("3")) {
					fr = new FileReader(file);
					if ((ch = fr.read()) == -1) {
						System.out.println("저장된 연락처가 없습니다.");

					} else { // 저장된 연락처가 있을 경우(파일에 작성된 문자가 있을 경우)
						p.advanced_delete_function_menu(fw = new FileWriter(file, true)); // 고급 삭제 기능 메뉴 메소드 호출
					}
					fr.close();
					break; // 3.연락처 삭제 기능 수행 후 탈출.
				} else if (n.equals("4")) {
					System.out.println("종료하겠습니다.");
					break all; // all:while 탈출

				} else {
					System.out.print("잘못된 메뉴입니다. 메뉴를 다시 선택하세요: ");
					continue; // 메뉴 다시 선택
				}
			}
		}
	}

}
