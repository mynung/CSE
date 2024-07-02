#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#define ASIZE 3

void show_menu()
{
	printf("1.패턴 출력\n");
	printf("2.2진수 구하기\n");
	printf("3.숫자 놀이\n");
	printf("4.배열\n");
	printf("5.끝내기\n");
	printf("메뉴를 선택하세요:");
}

void print_pattern()
{
	int i, k;
	for (i = 0; i < 7; i++)
	{
		for (k = 0; k < 6 - i; k++)
			printf(" ");
		for (k=0; k < i+1; k++)
			printf("*");
		printf("\n");
	}
}
void binary(int n)
{
	if (!(n / 2)) printf("1");
	else
	{
		binary(n / 2);
		printf("%d", n % 2);
	}
}

void find_binary()
{
	int n;
	printf("\n양의 정수를 입력하세요: ");
	scanf("%d", &n);
	printf("%d의 2진수:", n);
	binary(n);
}

int set_number()
{
	int n = (rand() % 101) - 50;
	return n;
}

void max_min(int* a, int* b)
{
	int tmp;
	if (*a < *b)
	{
		tmp = *a;
		*a = *b;
		*b = tmp;
	}
}

void play_with_number()
{
	int n1, n2;
	n1 = set_number();
	n2 = set_number();
	printf("(변경 전) n1: %d, n2: %d \n", n1, n2);
	max_min(&n1, &n2);
	printf("(변경 후) n1: %d, n2: %d \n", n1, n2);
}

void set_array(int m[][ASIZE])
{
	int* pm = m;
	while(pm <= &m[ASIZE-1][ASIZE-1])
		*pm++ = (rand() % 100) + 1;
}

void print_array(int m[][ASIZE])
{
	int* pm = m;
	while (pm <= &m[ASIZE - 1][ASIZE - 1])
	{
		for (int i = 0; i < ASIZE; i++)
			printf("%3d", *pm++);
		printf("\n");
	}
}

void find_average_min_max(int m[][ASIZE], double* aver, int* min, int* max)
{
	int* pm = m;
	double sum=0.0;
	*min = *pm;
	*max = *pm;
	while (pm <= &m[ASIZE - 1][ASIZE - 1])
	{
		if (*min > *pm) *min = *pm;
		if (*max < *pm) *max = *pm;
		sum += *pm++;
	}
	*aver = sum / (ASIZE * ASIZE);
}

void play_with_array(){
	int num[ASIZE][ASIZE];
	double average;
	int min, max;
	set_array(num);
	print_array(num);
	find_average_min_max(num, &average, &min, &max);
	printf("\n전체 평균: %4.1f, 최솟값: %3d, 최대값: %3d \n", average, min, max);
}

int main()
{
	int i;
	while (1)
	{
		show_menu();
		scanf("%d", &i);
		printf("\n");
		switch (i)
		{
		case 1:
			print_pattern();
			printf("\n");
			break;
		case 2:
			find_binary();
			printf("\n\n");
			break;
		case 3:
			play_with_number();
			printf("\n");
			break;
		case 4:
			play_with_array();
			printf("\n");
			break;
		case 5:
			return 0;
			break;
		default:
			continue;
		}
	}
	return 0;
}