//202311264, ���ȫ, 0439�й�, Programming Assignment # 1, 2023.04.06
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
int main(void)
{
	int room_l, room_h, table_l, table_h, interval, human, total; // ���� ���� ����, ���̺��� ���� ����, �ο���, ����, ���ο� ��������
	printf("���� ���� �� ���� ���̸� �Է��Ͻÿ�(����, meters):\n");
	scanf("%d %d", &room_l, &room_h);
	printf("���̺��� ���� �� ���� ���̸� �Է��Ͻÿ�(����, meters):\n");
	scanf("%d %d", &table_l, &table_h);
	printf("���̺� ���� (meter)�� �Է��Ͻÿ�:\n");
	scanf("%d", &interval);
	printf("���̺� �ϳ��� ���� �� �ִ� �ϰ����� �Է��Ͻÿ�:\n");
	scanf("%d", &human);
	total = ((room_l - interval) / (table_l + interval)) * ((room_h - interval)/ (table_h + interval)) * human; //���̺��� ������ ����� ��ü ���밡���� �ο����� ��� ���� ����
	printf("�� �ϰ����� %d �� �Դϴ�.", total);
	return 0;
}

