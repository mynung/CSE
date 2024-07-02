//202311264 김민홍
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
int main(void)
{
	int score; //0~100까지의 정수를 담을 score변수
	printf("Enter your score : \n");
	scanf("%d", &score);
	score /= 10; //10으로 나눠 10~0까지의 값 만들기
	switch (score)
	{
	case 10:
	case 9:
		printf("A");
		break;
	case 8:
		printf("B");
		break;
	case 7:
		printf("C");
		break;
	case 6:
		printf("D");
		break;
	default:
		printf("F");
		break;
	}
	return 0;
}