package final_project;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Person { // �ּҷϿ� �� ����,�̸�,����,��ȭ��ȣ ������ Person Ŭ����. / �� ��ü�� ArrayList�� ������ ��.
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
	ArrayList<Person> tel = new ArrayList<Person>(); // �ּҷ�. PersonŸ�� ��ü���� ��� ����.
	// �������
	int sequnce = 1; // ����. ��ϵǴ� �����̸�, ������ �Ű���.

	// �޼ҵ�
	void print_menu() { // �޴�ȭ�� ������ִ� �޼ҵ�
		System.out.println("\n1.����ó ��� \n2.����ó ��� \n3.����ó ���� \n4.������");
		System.out.print("\n�޴��� �����ϼ���: "); // �Է¹��� ������ ���� �޼ҵ忡.
	}

	String add_juso() { // �̸�, ����, ��ȣ �Է¹��� �� ������ �Բ� ��ȯ. / ���� �޼ҵ忡�� ���Ͽ� �ۼ��� ��.
		System.out.print("�̸� �Է�: ");
		String name = sc.nextLine();
		System.out.print("���� �Է�: ");
		String age = sc.nextLine();
		System.out.print("��ȣ �Է�: ");
		String phoneNumber = sc.nextLine();
		tel.add(new Person(sequnce, name, age, phoneNumber));
		return String.format("[%d] %s %s %s", sequnce++, name, age, phoneNumber); // ������ ������ sequnce 1�� ����, ������ ��.
	}

	void advanced_delete_function_menu(FileWriter fw) throws IOException { // ��� ���� ��� �޴�ȭ�� ���
																			// FileWriterŸ�� ������ �Ű������� ����
		System.out.println("1.���� ���� \n2.�̸� ���� \n3.��ȣ ���� \n4.���� �޴��� �̵� \n");
		System.out.print("���� �޴��� �����ϼ���: ");
		this.advanced_delete_function(fw); // ��� ���� ��� �޼ҵ� ȣ��
	}

	void advanced_delete_function(FileWriter fw) throws IOException { // ��� ���� ��� �޼ҵ�
		String a_d_f = sc.nextLine(); // ���� �޴� ����
		System.out.println();
		String a_d_n; // ����
		String a_d_name; // �̸�
		String a_d_phoneNum; // ��ȣ
		if (a_d_f.equals("1")) { // 1.���� ���� ���� ��
			while (true) {
				System.out.print("������ ����ó ������? ");
				a_d_n = sc.nextLine(); // ���� �Է¹ޱ�
				if (Integer.parseInt(a_d_n) > tel.size() || Integer.parseInt(a_d_n) <= 0) { // �Է¹��� ������ �ּҷϿ� ���� ��.
																							// int Ÿ������ ��ȯ �� �Ǵ�����.
					if (a_d_n.equals("0")) {
						System.out.println("�������� �ʰ� �޴��� ���ư��ϴ�.\n"); // 0 ���� �� �������� �ʰ� �޴��� ���ư�.
						return;
					} else {
						System.out.println("�������� �ʴ� ����ó�Դϴ�.\n"); // �ش� ������ ����ó�� ���� ��.
						continue; // ������ ���� �ٽ� �Է¹ޱ� ����
					}
				}
				break; // �ݺ��� Ż�� �� ����
			}
			for (int i = 0; i < tel.size(); i++) { // �ּҷ��� ��� ����ó�� Ȯ���ϱ� ���� �ݺ���
				if (tel.get(i).order == Integer.parseInt(a_d_n)) { // �Է¹��� ���� �߰� ��
					System.out.println(tel.get(i).order + "�� ����ó�� �����Ǿ����ϴ�.");
					tel.remove(i); // �ش� ������ ����ó ����
					this.sequnce--; // ����ó 1�� ���� �Ǿ�����, ���� ����ó ��� �� ���� �����ϱ� ����.
					fw = new FileWriter("juso.txt", false); // �ش� ���� ���� �� ����� ����(false)
					for (int k = 0; k < tel.size(); k++) { // tel(�ּҷ�)�� ��� ����ó�� ���Ͽ� �ۼ��ϱ� ���� �ݺ���
						if (k >= i) { // ������ ����ó�� �������� ũ�ų� ���ٸ� ���� ������ �� ���Ͽ� �ۼ�
							fw.write("\n" + String.format("[%d] %s %s %s", tel.get(k).order - 1, tel.get(k).name,
									tel.get(k).age, tel.get(k).phoneNumber) + "\n");
							tel.get(k).order -= 1;
						} else { // ������ ����ó�� �������� �տ� �����Ƿ� �������� �ʿ� x
							fw.write("\n" + String.format("[%d] %s %s %s", tel.get(k).order, tel.get(k).name,
									tel.get(k).age, tel.get(k).phoneNumber) + "\n");
						}
					}
					fw.close(); // ���� ������ ���� �ۼ� �Ϸ�.
				}
			}
		} else if (a_d_f.equals("2")) { // 2.�̸� ���� ���� ��
			all: while (true) {
				System.out.print("������ ����ó �̸���? ");
				a_d_name = sc.nextLine(); // ������ ����ó �̸� �Է¹ޱ�
				for (int i = 0; i <= tel.size(); i++) { // �ּҷ� ��� ��ü(����ó)�� Ȯ���ϱ� ���� �ݺ���
					if (tel.get(i).name.equals(a_d_name) == false) { // �Է¹��� �̸��� ����ó�� �̸��� �ٸ� ��
						if (a_d_name.equals("����")) { // �Է¹��� �̸��� "����"���
							System.out.println("�������� �ʰ� �޴��� ���ư��ϴ�.\n"); // �������� �ʰ� �޴��� ���ư�.
							return;
						} else { // �Է¹��� �̸��� ����ó�� �̸��� �ٸ���, "����"�Է¹��� �ʾ��� ��.
							if (i == tel.size() - 1) { // ��� ����ó�� �� Ȯ���� �� �� (tel.size-1�� ������ ����ó�̹Ƿ� ������� �Դٸ�
														// �Է¹��� �̸��� ����ó�� ���ٴ� ��.)
								System.out.println("�������� �ʴ� ����ó�Դϴ�.\n");
								continue all; // all: ǥ�� �� �ݺ������� / ������ �̸� �ٽ� �Է¹ޱ� ����
							}
						}
					} else
						break; // �Է¹��� �̸��� ����ó �� �̸��� ���ٸ� ������ ���� �ݺ��� Ż��
				}
				break; // �ݺ��� Ż�� �� ����
			}
			for (int i = 0; i < tel.size(); i++) { // �ּҷ� ��� ����ó�� Ȯ���ϱ� ���� �ݺ���
				if (tel.get(i).name.equals(a_d_name)) { // �Է¹��� �̸� �߰� ��
					System.out.println(a_d_name + " ����ó�� �����Ǿ����ϴ�.");
					tel.remove(i); // �ش� ������ ����ó ����
					this.sequnce--; // ����ó 1�� ���� �Ǿ�����, ���� ����ó ��� �� ���� �����ϱ� ����.
					fw = new FileWriter("juso.txt", false); // �ش� ���� ���� �� ����� ����(false)
					for (int k = 0; k < tel.size(); k++) { // tel(�ּҷ�)�� ��� ����ó�� ���Ͽ� �ۼ��ϱ� ���� �ݺ���
						if (k >= i) { // ������ ����ó�� �������� ũ�ų� ���ٸ� ���� ������ �� ���Ͽ� �ۼ�
							fw.write("\n" + String.format("[%d] %s %s %s", tel.get(k).order - 1, tel.get(k).name,
									tel.get(k).age, tel.get(k).phoneNumber) + "\n");
							tel.get(k).order -= 1;
						} else { // ������ ����ó�� �������� �տ� �����Ƿ� �������� �ʿ� x
							fw.write("\n" + String.format("[%d] %s %s %s", tel.get(k).order, tel.get(k).name,
									tel.get(k).age, tel.get(k).phoneNumber) + "\n");
						}
					}
					fw.close(); // ���� ������ ���� �ۼ� �Ϸ�.
				}
			}
		} else if (a_d_f.equals("3")) { // 3.��ȣ ���� ���� ��
			all: while (true) {
				System.out.print("������ ����ó ��ȣ��? ");
				a_d_phoneNum = sc.nextLine(); // ������ ����ó ��ȣ �Է¹ޱ�
				for (int i = 0; i <= tel.size(); i++) { // �ּҷ� ��� ��ü(����ó)�� Ȯ���ϱ� ���� �ݺ���
					if (tel.get(i).phoneNumber.equals(a_d_phoneNum) == false) { // �Է¹��� ��ȣ�� ����ó�� ��ȣ�� �ٸ� ��
						if (a_d_phoneNum.equals("����")) { // �Է¹��� �̸��� "����"���
							System.out.println("�������� �ʰ� �޴��� ���ư��ϴ�.\n"); // �������� �ʰ� �޴��� ���ư�.
							return;
						} else { // �Է¹��� ��ȣ�� ����ó�� ��ȣ�� �ٸ���, "����"�Է¹��� �ʾ��� ��.
							if (i == tel.size() - 1) { // ��� ����ó�� �� Ȯ���� �� �� (tel.size-1�� ������ ����ó�̹Ƿ� ������� �Դٸ�
														// �Է¹��� ��ȣ�� ����ó�� ���ٴ� ��.)
								System.out.println("�������� �ʴ� ����ó�Դϴ�.\n");
								continue all; // all: ǥ�� �� �ݺ������� / ������ ��ȣ �ٽ� �Է¹ޱ� ����
							}
						}
					} else
						break; // �Է¹��� ��ȣ�� ����ó �� ��ȣ�� ���ٸ� ������ ���� �ݺ��� Ż��
				}
				break;
			}
			for (int i = 0; i < tel.size(); i++) { // �ּҷ� ��� ����ó�� Ȯ���ϱ� ���� �ݺ���
				if (tel.get(i).phoneNumber.equals(a_d_phoneNum)) { // �Է¹��� ��ȣ �߰� ��
					System.out.println(tel.get(i).order + "�� ����ó�� �����Ǿ����ϴ�.");
					tel.remove(i); // �ش� ������ ����ó ����
					this.sequnce--; // ����ó 1�� ���� �Ǿ�����, ���� ����ó ��� �� ���� �����ϱ� ����.
					fw = new FileWriter("juso.txt", false); // �ش� ���� ���� �� ����� ����(false)
					for (int k = 0; k < tel.size(); k++) { // tel(�ּҷ�)�� ��� ����ó�� ���Ͽ� �ۼ��ϱ� ���� �ݺ���
						if (k >= i) { // ������ ����ó�� �������� ũ�ų� ���ٸ� ���� ������ �� ���Ͽ� �ۼ�
							fw.write("\n" + String.format("[%d] %s %s %s", tel.get(k).order - 1, tel.get(k).name,
									tel.get(k).age, tel.get(k).phoneNumber) + "\n");
							tel.get(k).order -= 1;
						} else { // ������ ����ó�� �������� �տ� �����Ƿ� �������� �ʿ� x
							fw.write("\n" + String.format("[%d] %s %s %s", tel.get(k).order, tel.get(k).name,
									tel.get(k).age, tel.get(k).phoneNumber) + "\n");
						}
					}
					fw.close(); // ���� ������ ���� �ۼ� �Ϸ�.
				}
			}
		} else if (a_d_f.equals("4")) { // �Ʒ��� ������ Ÿ�� ���� �޴��� ���ư�
			System.out.println("���� �޴��� �̵��մϴ�.\n");

		} else {
			System.out.print("�߸��� ���� �޴��Դϴ�. ���� �޴��� �ٽ� �����ϼ���: ");
			this.advanced_delete_function(fw); // ���θ޴� �ٽ� ���ùް� �۵� ����
		}
		return;
	}

	public static void main(String[] args) throws IOException {
		System.out.println("[�⸻������Ʈ] 60190927 ���¿�");
		System.out.println("[����ó ���� ���α׷�]");

		Scanner sc = new Scanner(System.in);
		finalproject p = new finalproject();
		File file = new File("juso.txt");
		FileWriter fw;
		FileReader fr;
		int ch;

		all: while (true) {
			p.print_menu(); // �޴�ȭ�� ���
			String n;
			while (true) {
				n = sc.nextLine(); // �޴�ȭ�鿡�� ������ ��� �Է¹ޱ�
				System.out.println();
				if (n.equals("1")) {
					fr = new FileReader(file);
					if ((ch = fr.read()) == -1) { // ���Ͽ� ���� ���ڰ� ���� ���
						System.out.println("����� ����ó�� �����ϴ�. \n");

					} else {
						while ((ch = fr.read()) != -1) { // ���Ͽ� ���� ���� ���� ������
							System.out.print((char) ch); // �ѱ��ھ� ������ �б�
						}
						fr.close();
					}
					break; // 1.����ó ��� ��� ���� �� Ż��. / �޴�ȭ�� ��� �ݺ�������..
				} else if (n.equals("2")) {
					fw = new FileWriter(file, true); // ������ �̹� �����Ѵٸ� �̾��(true)
					fw.write("\n" + p.add_juso() + "\n"); // add_juso �޼ҵ忡�� ��ȯ���� �� ���Ͽ� �ۼ�
					fw.close();
					break; // 2.����ó ��� ��� ���� �� Ż��.
				} else if (n.equals("3")) {
					fr = new FileReader(file);
					if ((ch = fr.read()) == -1) {
						System.out.println("����� ����ó�� �����ϴ�.");

					} else { // ����� ����ó�� ���� ���(���Ͽ� �ۼ��� ���ڰ� ���� ���)
						p.advanced_delete_function_menu(fw = new FileWriter(file, true)); // ��� ���� ��� �޴� �޼ҵ� ȣ��
					}
					fr.close();
					break; // 3.����ó ���� ��� ���� �� Ż��.
				} else if (n.equals("4")) {
					System.out.println("�����ϰڽ��ϴ�.");
					break all; // all:while Ż��

				} else {
					System.out.print("�߸��� �޴��Դϴ�. �޴��� �ٽ� �����ϼ���: ");
					continue; // �޴� �ٽ� ����
				}
			}
		}
	}

}
