import java.util.Scanner;  // 연습용올리기 

public class Quiz4 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int num;
		int num1 = 0;
		
		do {
			System.out.println("숫자를 입력하세요");
			num = s.nextInt();
			num1 = (num1 + num);
			} 
		while(num != 0);
		
		System.out.println(num1);
	}

}
