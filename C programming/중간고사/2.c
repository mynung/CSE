//202311264 김민홍
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
int main(void)
{
	int f_year, s_year, i, day;
	//f_year: 시작 연도, s_year: 종료 연도, i:임시함수(시작 연도 담을), day:총 날짜수
	printf("시작 연도를 입력하시오: ");
	scanf("%d", &f_year);
	if (f_year <= 0)
		printf("시작 연도는 양의 정수여야 합니다.");
	else
	{
		printf("종료 연도를 입력하시오: ");
		scanf("%d", &s_year);
		if (s_year <= f_year)
			printf("종료 연도는 시작 연도보다 커야합니다.");
		else //제대로 프로그램 시작
		{
			day = 365 * (s_year - f_year); //보통 년수로 따졌을 때 경우의 날짜수
			i = f_year; 
			while (i <= s_year) //윤년 검사
			{
				if ((i % 4 == 0 && i % 100 != 0) || i % 400 == 0)
				{
					printf("윤년: %d\n", i);
					day += 1; //윤년일때 마다 날짜 1증가
				}
				i++;
			}
			printf("%d/1/1 ~ %d/1/1 사이의 총 날짜수: %d 일", f_year, s_year, day); //마지막 출력
		}
	}
	return 0;
}