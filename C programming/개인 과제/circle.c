//202311264, ���ȫ, 0439�й�, Programming Assignment # 1, 2023.04.06
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
int main(void)
{
	int room_l, room_h, table, interval, human, total; // ���� ���� ����, ���̺��� ������, �ο���, ����, ���ο� ��������
	printf("���� ���� �� ���� ���̸� �Է��Ͻÿ�(����, meters):\n");
	scanf("%d %d", &room_l, &room_h);
	printf("���̺��� ������	���̸� �Է��Ͻÿ�(����, meters):\n");
	scanf("%d", &table);
	printf("���̺� ���� (meter)�� �Է��Ͻÿ�:\n");
	scanf("%d", &interval);
	printf("���̺� �ϳ��� ���� �� �ִ� �ϰ����� �Է��Ͻÿ�:\n");
	scanf("%d", &human);
	total = ((room_l - interval) / (table * 2 + interval)) * ((room_h - interval) / (table * 2 + interval)) * human; //���̺��� ������ ������ ���� ���̺� ���� ����ϰ� �ο����� ���� �� �ο��� ����
	printf("�� �ϰ����� %d �� �Դϴ�.", total);
	return 0;
}