//202311264, ���ȫ, 0439�й�, Programming Assignment # 1, 2023.04.06
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#define unit 60
#define unit2 3600
int main(void)
{
	float time; //time�� �浹�ð��� ���� ������
	int distance, t1, t2; //t1, t2�� ���� ù ��° ������ �� ��° ������ �ӵ��� ������ �Լ���
	printf("�� ���� ������ �Ÿ��� �Է��Ͻÿ�:\n");
	scanf("%d", &distance);
	printf("ù ��° ������ �ӵ�(km/h)�� �Է��Ͻÿ�:\n");
	scanf("%d", &t1);
	printf("�� ��° ������ �ӵ�(km/h)�� �Է��Ͻÿ�:\n");
	scanf("%d", &t2);
	time = (float)distance / (t1 + t2); //�浹������ �ð����� (�Ǽ�����, �ô���)
	printf("�浹���� �� %.3f�� (%d�ð� %d�� %d��)�� �ҿ�˴ϴ�.\n", time * unit, (int)time, (int)(time *unit ) % unit, (int)(time * unit2) % unit);  //�ú��ʸ� ��Ÿ���� ���� ���������� �ٲ���
	printf("ù ��° ������ ����Ÿ��� ��%.2f (km) �Դϴ�.\n", time * t1); 
	printf("�� ��° ������ ����Ÿ��� ��%.2f (km) �Դϴ�.\n", time * t2); 
	return 0;
}