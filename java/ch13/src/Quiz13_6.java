/*문제6
가로가 9, 세로가 3인 int형 2차원 배열을 선언하여 구구단 중 2, 3, 4단을 저장한다.
그리고 제대로 저장이 되었는지 확인하기 위해 출력을 하는 프로그램을 작성하라
*/
public class Quiz13_6 {

	public static void main(String[] args) {
		int[][] arr = new int[3][9];

		for (int i = 2; i < 5; i++) {
			for (int j = 1; j < 10; j++) {
				arr[i - 2][j - 1] = i * j;
				System.out.print(i + "*" + j + "=" + (i * j) + "\t");
				if (j == 9) {
					System.out.println();
				}
			}
		}

	}
}
