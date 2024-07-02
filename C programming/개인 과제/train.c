//202311264, 김민홍, 0439분반, Programming Assignment # 1, 2023.04.06
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#define unit 60
#define unit2 3600
int main(void)
{
	float time; //time은 충돌시간에 대한 변수임
	int distance, t1, t2; //t1, t2는 각각 첫 번째 기차와 두 번째 기차의 속도를 지정한 함수임
	printf("두 기차 사이의 거리를 입력하시오:\n");
	scanf("%d", &distance);
	printf("첫 번째 기차의 속도(km/h)를 입력하시오:\n");
	scanf("%d", &t1);
	printf("두 번째 기차의 속도(km/h)를 입력하시오:\n");
	scanf("%d", &t2);
	time = (float)distance / (t1 + t2); //충돌까지의 시간변수 (실수형태, 시단위)
	printf("충돌까지 총 %.3f분 (%d시간 %d분 %d초)가 소요됩니다.\n", time * unit, (int)time, (int)(time *unit ) % unit, (int)(time * unit2) % unit);  //시분초를 나타내기 위해 정수형으로 바꿔줌
	printf("첫 번째 기차의 운행거리는 총%.2f (km) 입니다.\n", time * t1); 
	printf("두 번째 기차의 운행거리는 총%.2f (km) 입니다.\n", time * t2); 
	return 0;
}