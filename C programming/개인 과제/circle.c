//202311264, 김민홍, 0439분반, Programming Assignment # 1, 2023.04.06
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
int main(void)
{
	int room_l, room_h, table, interval, human, total; // 방의 가로 세로, 테이블의 반지름, 인원수, 간격, 총인원 변수선언
	printf("방의 가로 및 세로 길이를 입력하시오(단위, meters):\n");
	scanf("%d %d", &room_l, &room_h);
	printf("테이블의 반지름	길이를 입력하시오(단위, meters):\n");
	scanf("%d", &table);
	printf("테이블간 간격 (meter)을 입력하시오:\n");
	scanf("%d", &interval);
	printf("테이블 하나에 앉을 수 있는 하객수를 입력하시오:\n");
	scanf("%d", &human);
	total = ((room_l - interval) / (table * 2 + interval)) * ((room_h - interval) / (table * 2 + interval)) * human; //테이블의 지름과 간격을 통해 테이블 수를 계산하고 인원수를 곱해 총 인원을 구함
	printf("총 하객수는 %d 명 입니다.", total);
	return 0;
}