//202311264 ���ȫ
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
int main(void)
{
	int score; //0~100������ ������ ���� score����
	printf("Enter your score : \n");
	scanf("%d", &score);
	score /= 10; //10���� ���� 10~0������ �� �����
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