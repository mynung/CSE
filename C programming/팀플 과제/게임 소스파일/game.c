#include "common.h"


void game(int * s)
{
	int s_time, j = 0;
	char word[SIZE], input[SIZE], ch;
	srand((unsigned int)time(0));
	t = 0;
	show_string(word, &s_time, *s);
	

	while (char_heart > 0 && boss_heart > 0)
	{
		if (time(0) >= s_time + timeout) { //Ÿ�Ӿƿ� üũ
			t++;
			gotoxy(50, 10);
			printf("�ð��� ����Ǿ����ϴ�!!");
			char_damage();
			Sleep(1000);
			char_heart--;
			show_string(word, &s_time, *s);
			j = 0;
			t--;
		}
		if (_kbhit() == 1) { //Ű �Է��� �������� 
			gotoxy(67 + j, 27);
			ch = _getch();
			if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) //���ĺ� �ҹ��� �Ǵ� �빮�� �Է������� Ȯ��
				if (j < size - 1) {
					printf("%c", ch);
					input[j] = ch;
					j++;
				}

			if (ch == '\b') //�������̽� �ԷµǾ��� ��
				if (j > 0) {
					printf("\b \b");
					input[j] = 0;
					j--;
				}
			if (ch == 13) { //����Ű�� �Է��Ͽ��� ��
				input[j] = '\0';
				if (strcmp(word, input) == 0) {
					t++;
					gotoxy(50, 10);
					printf("�����Դϴ�! ����!!");
					*s += (STD_TIME - (time(0) - s_time)) * 100 * lev;
					boss_heart--;
					bomb(14);
					boss_damage();
					Sleep(1000);
				}
				else {
					t++;
					gotoxy(50, 10);
					printf("���� �����Դϴ٤Ф�");
					char_heart--;
					char_damage();
					Sleep(1000);
				}
				show_string(word, &s_time, *s);
				t--;
				j = 0;
			}
		}

	}
	t = 1;
	system("cls");
}
